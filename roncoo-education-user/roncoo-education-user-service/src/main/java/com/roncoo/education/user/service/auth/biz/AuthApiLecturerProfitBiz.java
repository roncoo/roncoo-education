package com.roncoo.education.user.service.auth.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.BaseBiz;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ProfitStatusEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.SignUtil;
import com.roncoo.education.user.dao.LecturerDao;
import com.roncoo.education.user.dao.LecturerExtDao;
import com.roncoo.education.user.dao.LecturerProfitDao;
import com.roncoo.education.user.dao.UserExtDao;
import com.roncoo.education.user.dao.impl.mapper.entity.*;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerProfitExample.Criteria;
import com.roncoo.education.user.service.auth.bo.AuthLecturerProfitPageBO;
import com.roncoo.education.user.service.auth.bo.AuthLecturerProfitSaveBO;
import com.roncoo.education.user.service.auth.dto.AuthLecturerProfitPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * 讲师提现日志表
 *
 * @author wujing
 */
@Component
public class AuthApiLecturerProfitBiz extends BaseBiz {

    @Autowired
    private LecturerProfitDao lecturerProfitDao;
    @Autowired
    private LecturerExtDao lecturerExtDao;
    @Autowired
    private LecturerDao lecturerDao;
    @Autowired
    private UserExtDao userExtDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 讲师提现记录分页列出接口
     *
     * @param authLecturerProfitPageBO
     * @author wuyun
     */
    public Result<Page<AuthLecturerProfitPageDTO>> list(AuthLecturerProfitPageBO authLecturerProfitPageBO) {
        if (authLecturerProfitPageBO.getLecturerUserNo() == null) {
            return Result.error("lecturerUserNo不能为空");
        }
        LecturerProfitExample example = new LecturerProfitExample();
        Criteria c = example.createCriteria();
        c.andLecturerUserNoEqualTo(authLecturerProfitPageBO.getLecturerUserNo());
        example.setOrderByClause(" id desc ");
        Page<LecturerProfit> page = lecturerProfitDao.listForPage(authLecturerProfitPageBO.getPageCurrent(), authLecturerProfitPageBO.getPageSize(), example);
        Page<AuthLecturerProfitPageDTO> dtoPage = PageUtil.transform(page, AuthLecturerProfitPageDTO.class);
        for (AuthLecturerProfitPageDTO dto : dtoPage.getList()) {
            if (!StringUtils.isEmpty(dto.getBankCardNo())) {
                dto.setBankCardNo(dto.getBankCardNo().substring(0, 6) + "****" + dto.getBankCardNo().substring(12, dto.getBankCardNo().length()));
            }
        }
        return Result.success(dtoPage);
    }

    /**
     * 讲师申请提现接口
     *
     * @param authLecturerProfitSaveBO
     * @author wuyun
     */
    @Transactional
    public Result<Integer> save(AuthLecturerProfitSaveBO authLecturerProfitSaveBO) {
        // 查询用户信息
        UserExt userExt = userExtDao.getByUserNo(authLecturerProfitSaveBO.getLecturerUserNo());
        if (ObjectUtil.isNull(userExt) || StatusIdEnum.NO.getCode().equals(userExt.getStatusId())) {
            return Result.error("该用户不存在");
        }
        // 查询讲师信息
        Lecturer lecturer = lecturerDao.getByLecturerUserNo(authLecturerProfitSaveBO.getLecturerUserNo());
        if (ObjectUtil.isNull(lecturer) || StatusIdEnum.NO.getCode().equals(lecturer.getStatusId())) {
            return Result.error("该讲师不存在");
        }
        // 查询用户账户信息
        LecturerExt lecturerExt = lecturerExtDao.getByLecturerUserNo(authLecturerProfitSaveBO.getLecturerUserNo());
        if (ObjectUtil.isNull(lecturerExt) || StatusIdEnum.NO.getCode().equals(lecturerExt.getStatusId())) {
            return Result.error("该账户不存在");
        }
        if (lecturerExt.getEnableBalances().compareTo(authLecturerProfitSaveBO.getExtractMoney()) < 0) {
            return Result.error("账户余额不足");
        }
        if (StringUtils.isEmpty(lecturerExt.getBankCardNo())) {
            return Result.error("银行卡未绑定");
        }
        if (!StringUtils.hasText(authLecturerProfitSaveBO.getSmsCode())) {
            return Result.error("输入的验证码不能为空!");
        }

        String redisSmsCode = redisTemplate.opsForValue().get(authLecturerProfitSaveBO.getClientId() + userExt.getMobile());
        if (StringUtils.isEmpty(redisSmsCode)) {
            return Result.error("验证码已失效!");
        }

        String sign = SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances());
        if (sign.equals(lecturerExt.getSign())) {
            // 先存日志，再操作
            LecturerProfit lecturerProfitInfo = BeanUtil.copyProperties(lecturerExt, LecturerProfit.class);
            lecturerProfitInfo.setGmtCreate(null);
            lecturerProfitInfo.setProfitStatus(ProfitStatusEnum.CONFIRMING.getCode());

            // 讲师收入
            BigDecimal lecturerProfit = authLecturerProfitSaveBO.getExtractMoney().multiply(lecturer.getLecturerProportion());
            // 平台收入
            BigDecimal platformProfit = authLecturerProfitSaveBO.getExtractMoney().subtract(lecturerProfit);

            lecturerProfitInfo.setLecturerProfit(lecturerProfit);// 讲师收入 = 提现金额 * 讲师分润比例
            lecturerProfitInfo.setPlatformProfit(platformProfit);// 平台收入 = 提现金额 - 讲师收入
            lecturerProfitDao.save(lecturerProfitInfo);

            // 减少账户可提现金额,增加账号冻结金额,生成新的签名防篡改
            lecturerExt.setEnableBalances(lecturerExt.getEnableBalances().subtract(authLecturerProfitSaveBO.getExtractMoney()));// 账户可提现金额减去申请的金额
            lecturerExt.setFreezeBalances(lecturerExt.getFreezeBalances().add(authLecturerProfitSaveBO.getExtractMoney()));// 账号冻结金额加上申请的金额
            lecturerExt.setSign(SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances()));
            return Result.success(lecturerExtDao.updateById(lecturerExt));
        } else {
            logger.error("签名为：{}，{}", sign, lecturerExt.getSign());
            return Result.error("账户异常，请联系客服");
        }
    }

}
