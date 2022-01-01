package com.roncoo.education.user.feign.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.BaseBiz;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.enums.ProfitStatusEnum;
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
import com.roncoo.education.user.feign.interfaces.qo.LecturerProfitQO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerProfitVO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 讲师提现日志表
 *
 * @author wujing
 */
@Component
public class FeignLecturerProfitBiz extends BaseBiz {

    @Autowired
    private LecturerProfitDao dao;
    @Autowired
    private LecturerDao lecturerDao;
    @Autowired
    private LecturerExtDao lecturerExtDao;

    public Page<LecturerProfitVO> listForPage(LecturerProfitQO qo) {
        LecturerProfitExample example = new LecturerProfitExample();
        Criteria c = example.createCriteria();
        // 将页面的讲师名称为条件查询出对应的讲师ID，再将讲师ID作为查询条件
        if (StringUtils.isNotEmpty(qo.getLecturerName())) {
            List<Lecturer> lecturerList = lecturerDao.getByLecturerName(qo.getLecturerName());
            if (ObjectUtil.isNull(lecturerList)) {
                c.andLecturerUserNoEqualTo(0L);
            } else {
                for (Lecturer lecturer : lecturerList) {
                    c.andLecturerUserNoEqualTo(lecturer.getLecturerUserNo());
                }
            }
        }
        if (qo.getProfitStatus() != null) {
            c.andProfitStatusEqualTo(qo.getProfitStatus());
        }
        if (StringUtils.isNotEmpty(qo.getBeginDate())) {
            c.andGmtCreateGreaterThanOrEqualTo(DateUtil.parseDate(qo.getBeginDate(), "yyyy-MM-dd"));
        }
        if (StringUtils.isNotEmpty(qo.getEndDate())) {
            c.andGmtCreateLessThanOrEqualTo(DateUtil.addDate(DateUtil.parseDate(qo.getEndDate(), "yyyy-MM-dd"), 1));
        }
        example.setOrderByClause(" profit_status asc , id desc ");
        Page<LecturerProfit> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        Page<LecturerProfitVO> pageVO = PageUtil.transform(page, LecturerProfitVO.class);
        // 讲师信息
        if (!pageVO.getList().isEmpty()) {
            for (LecturerProfitVO vo : pageVO.getList()) {
                Lecturer lecturer = lecturerDao.getByLecturerUserNo(vo.getLecturerUserNo());
                vo.setLecturerVO(BeanUtil.copyProperties(lecturer, LecturerVO.class));
            }
        }
        return pageVO;
    }

    public int save(LecturerProfitQO qo) {
        LecturerProfit record = BeanUtil.copyProperties(qo, LecturerProfit.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public LecturerProfitVO getById(Long id) {
        LecturerProfit record = dao.getById(id);
        return BeanUtil.copyProperties(record, LecturerProfitVO.class);
    }

    @Transactional
    public int updateById(LecturerProfitQO qo) {

        LecturerProfit lecturerProfit = dao.getById(qo.getId());
        if (ObjectUtil.isNull(lecturerProfit)) {
            throw new BaseException("讲师提现日志信息不存在");
        }

        // 审核成功时进来
        if (ProfitStatusEnum.SUCCESS.getCode().equals(qo.getProfitStatus())) {
            // 根据传入讲师用户编号获取讲师账户信息
            LecturerExt lecturerExt = lecturerExtDao.getByLecturerUserNo(lecturerProfit.getLecturerUserNo());
            if (ObjectUtil.isNull(lecturerExt)) {
                throw new BaseException("讲师账户信息不存在");
            }

            String sign = SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances());
            if (!sign.equals(lecturerExt.getSign())) {
                logger.error("签名为：{}，{}", sign, lecturerExt.getSign());
            }

            lecturerExt.setHistoryMoney(lecturerExt.getHistoryMoney().add(lecturerExt.getFreezeBalances()));// 已提金额 = 原来的已提金额 + 冻结金额
            lecturerExt.setFreezeBalances(BigDecimal.ZERO);
            lecturerExt.setSign(SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances()));
            int lecturerExtNum = lecturerExtDao.updateById(lecturerExt);
            if (lecturerExtNum < 1) {
                throw new BaseException("讲师账户信息更新失败");
            }
        }

        LecturerProfit record = BeanUtil.copyProperties(qo, LecturerProfit.class);
        return dao.updateById(record);
    }

}
