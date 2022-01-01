package com.roncoo.education.user.service.pc.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.BaseBiz;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ProfitStatusEnum;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.DateUtil;
import com.roncoo.education.common.core.tools.SignUtil;
import com.roncoo.education.user.dao.LecturerDao;
import com.roncoo.education.user.dao.LecturerExtDao;
import com.roncoo.education.user.dao.LecturerProfitDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Lecturer;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerExt;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerProfit;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerProfitExample;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerProfitExample.Criteria;
import com.roncoo.education.user.service.pc.req.LecturerProfitBatchREQ;
import com.roncoo.education.user.service.pc.req.LecturerProfitPageREQ;
import com.roncoo.education.user.service.pc.req.LecturerProfitUpdateREQ;
import com.roncoo.education.user.service.pc.resq.LecturerProfitPageRESQ;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PcApiLecturerProfitBiz extends BaseBiz {

    @Autowired
    private LecturerProfitDao dao;
    @Autowired
    private LecturerDao lecturerDao;
    @Autowired
    private LecturerExtDao lecturerExtDao;

    /**
     * 分页列出讲师信息提现信息
     *
     * @param req
     * @return
     */
    public Result<Page<LecturerProfitPageRESQ>> listForPage(LecturerProfitPageREQ req) {
        LecturerProfitExample example = new LecturerProfitExample();
        Criteria c = example.createCriteria();
        // 将页面的讲师名称为条件查询出对应的讲师ID，再将讲师ID作为查询条件
        if (StringUtils.isNotEmpty(req.getLecturerName())) {
            List<Lecturer> lecturerList = lecturerDao.getByLecturerName(req.getLecturerName());
            if (ObjectUtil.isNull(lecturerList)) {
                c.andLecturerUserNoEqualTo(0L);
            } else {
                for (Lecturer lecturer : lecturerList) {
                    c.andLecturerUserNoEqualTo(lecturer.getLecturerUserNo());
                }
            }
        }
        if (req.getProfitStatus() != null) {
            c.andProfitStatusEqualTo(req.getProfitStatus());
        }
        if (StringUtils.isNotEmpty(req.getBeginDate())) {
            c.andGmtCreateGreaterThanOrEqualTo(DateUtil.parseDate(req.getBeginDate(), "yyyy-MM-dd"));
        }
        if (StringUtils.isNotEmpty(req.getEndDate())) {
            c.andGmtCreateLessThanOrEqualTo(DateUtil.addDate(DateUtil.parseDate(req.getEndDate(), "yyyy-MM-dd"), 1));
        }
        example.setOrderByClause(" profit_status asc , id desc ");
        Page<LecturerProfit> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        Page<LecturerProfitPageRESQ> listForPage = PageUtil.transform(page, LecturerProfitPageRESQ.class);
        // 讲师信息
        if (!listForPage.getList().isEmpty()) {
            for (LecturerProfitPageRESQ resq : listForPage.getList()) {
                // 查找讲师信息
                Lecturer lecturer = lecturerDao.getByLecturerUserNo(resq.getLecturerUserNo());
                if (ObjectUtil.isNotNull(lecturer)) {
                    resq.setLecturerName(lecturer.getLecturerName());
                    resq.setLecturerMobile(lecturer.getLecturerMobile());
                }
            }
        }
        return Result.success(listForPage);
    }

    public Result<Integer> update(LecturerProfitUpdateREQ req) {
        LecturerProfit lecturerProfit = dao.getById(req.getId());
        if (ObjectUtil.isNull(lecturerProfit)) {
            return Result.error("讲师提现日志信息不存在");
        }
        if (req.getProfitStatus() == null) {
            return Result.error("分润状态不能为空");
        }

        // 审核成功时进来
        if (ProfitStatusEnum.SUCCESS.getCode().equals(req.getProfitStatus())) {
            // 根据传入讲师用户编号获取讲师账户信息
            LecturerExt lecturerExt = lecturerExtDao.getByLecturerUserNo(lecturerProfit.getLecturerUserNo());
            if (ObjectUtil.isNull(lecturerExt)) {
                return Result.error("讲师账户信息不存在");
            }

            String sign = SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances());
            if (!sign.equals(lecturerExt.getSign())) {
                logger.error("签名为：{}，{}", sign, lecturerExt.getSign());
                return Result.error("签名出错");
            }

            lecturerExt.setHistoryMoney(lecturerExt.getHistoryMoney().add(lecturerExt.getFreezeBalances()));// 已提金额 = 原来的已提金额 + 冻结金额
            lecturerExt.setFreezeBalances(BigDecimal.ZERO);
            lecturerExt.setSign(SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances()));
            int lecturerExtNum = lecturerExtDao.updateById(lecturerExt);
            if (lecturerExtNum < 1) {
                return Result.error("讲师账户信息更新失败");
            }
        }
        LecturerProfit record = BeanUtil.copyProperties(req, LecturerProfit.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.USER_UPDATE_FAIL);
    }

    /**
     * 批量更新提现状态
     *
     * @param req
     * @return
     */
    @Transactional
    public Result<Integer> batch(LecturerProfitBatchREQ req) {

        if (req.getId().isEmpty()) {
            return Result.error("主键集合不能为空");
        }
        if (req.getProfitStatus() == null) {
            return Result.error("分润状态不能为空");
        }

        for (Long id : req.getId()) {
            LecturerProfit lecturerProfit = dao.getById(id);
            if (ObjectUtil.isNull(lecturerProfit)) {
                return Result.error("讲师提现日志信息不存在");
            }
            // 审核成功时进来
            if (ProfitStatusEnum.SUCCESS.getCode().equals(req.getProfitStatus())) {
                // 根据传入讲师用户编号获取讲师账户信息
                LecturerExt lecturerExt = lecturerExtDao.getByLecturerUserNo(lecturerProfit.getLecturerUserNo());
                if (ObjectUtil.isNull(lecturerExt)) {
                    return Result.error("讲师账户信息不存在");
                }

                String sign = SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances());
                if (!sign.equals(lecturerExt.getSign())) {
                    logger.error("签名为：{}，{}", sign, lecturerExt.getSign());
                    return Result.error("签名出错");
                }

                lecturerExt.setHistoryMoney(lecturerExt.getHistoryMoney().add(lecturerExt.getFreezeBalances()));// 已提金额 = 原来的已提金额 + 冻结金额
                lecturerExt.setFreezeBalances(BigDecimal.ZERO);
                lecturerExt.setSign(SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances()));
                int lecturerExtNum = lecturerExtDao.updateById(lecturerExt);
                if (lecturerExtNum < 1) {
                    return Result.error("讲师账户信息更新失败");
                }
            }
            LecturerProfit record = BeanUtil.copyProperties(req, LecturerProfit.class);
            record.setId(id);
            dao.updateById(record);
        }

        return Result.success(1);
    }

}
