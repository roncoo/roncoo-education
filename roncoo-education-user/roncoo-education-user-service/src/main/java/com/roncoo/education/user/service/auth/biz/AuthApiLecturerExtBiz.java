package com.roncoo.education.user.service.auth.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.SignUtil;
import com.roncoo.education.user.dao.LecturerExtDao;
import com.roncoo.education.user.dao.UserExtDao;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerExt;
import com.roncoo.education.user.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.user.service.auth.bo.AuthLecturerExtViewBO;
import com.roncoo.education.user.service.auth.bo.AuthUserExtBankBO;
import com.roncoo.education.user.service.auth.dto.AuthLecturerExtViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * 讲师账户信息表
 *
 * @author wujing
 */
@Component
public class AuthApiLecturerExtBiz {

    @Autowired
    private LecturerExtDao lecturerExtDao;
    @Autowired
    private UserExtDao userExtDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 账户信息详情接口
     *
     * @param authUserExtViewBO
     * @author wuyun
     */
    public Result<AuthLecturerExtViewDTO> view(AuthLecturerExtViewBO uthLecturerExtViewBO) {
        UserExt userExt = userExtDao.getByUserNo(uthLecturerExtViewBO.getLecturerUserNo());
        if (ObjectUtil.isNull(userExt)) {
            return Result.error("用户信息异常");
        }
        LecturerExt lecturerExt = lecturerExtDao.getByLecturerUserNo(uthLecturerExtViewBO.getLecturerUserNo());
        if (ObjectUtil.isNull(lecturerExt)) {
            // 没账户信息，创建一个，兼容之前的业务逻辑
            lecturerExt = new LecturerExt();
            lecturerExt.setLecturerUserNo(userExt.getUserNo());
            lecturerExt.setTotalIncome(BigDecimal.valueOf(0));
            lecturerExt.setHistoryMoney(BigDecimal.valueOf(0));
            lecturerExt.setEnableBalances(BigDecimal.valueOf(0));
            lecturerExt.setFreezeBalances(BigDecimal.valueOf(0));
            lecturerExt.setSign(SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances()));
            lecturerExtDao.save(lecturerExt);
        }
        AuthLecturerExtViewDTO dto = BeanUtil.copyProperties(lecturerExt, AuthLecturerExtViewDTO.class);

        if (!StringUtils.isEmpty(dto.getBankCardNo())) {
            dto.setBankCardNo(dto.getBankCardNo().substring(0, 6) + "****" + dto.getBankCardNo().substring(14));
        }
        return Result.success(dto);
    }

    /**
     * 更新用户银行卡信息
     *
     * @param authUserExtBankBO
     * @author wuyun
     */
    @Transactional
    public Result<Integer> updateBackInfo(AuthUserExtBankBO authUserExtBankBO) {
        if (authUserExtBankBO.getLecturerUserNo() == null) {
            return Result.error("账号异常，请联系客服");
        }
        if (StringUtils.isEmpty(authUserExtBankBO.getBankCardNo())) {
            return Result.error("输入的银行卡号码不能为空!");
        }
        if (StringUtils.isEmpty(authUserExtBankBO.getBankName())) {
            return Result.error("输入的开户行名称不能为空!");
        }
        if (StringUtils.isEmpty(authUserExtBankBO.getBankBranchName())) {
            return Result.error("输入的开户支行名不能为空!");
        }
        if (StringUtils.isEmpty(authUserExtBankBO.getBankUserName())) {
            return Result.error("输入的开户名不能为空!");
        }
        if (StringUtils.isEmpty(authUserExtBankBO.getBankIdCardNo())) {
            return Result.error("输入的身份证号码不能为空!");
        }
        if (StringUtils.isEmpty(authUserExtBankBO.getSmsCode())) {
            return Result.error("输入的验证码不能为空!");
        }
        if (StringUtils.isEmpty(authUserExtBankBO.getMobile())) {
            return Result.error("手机号码不能为空!");
        }
        if (StringUtils.isEmpty(authUserExtBankBO.getClientId())) {
            return Result.error("clientId不能为空!");
        }
        // 查找账户信息
        LecturerExt lecturerExt = lecturerExtDao.getByLecturerUserNo(authUserExtBankBO.getLecturerUserNo());
        if (ObjectUtil.isNull(lecturerExt) && StatusIdEnum.YES.getCode().equals(lecturerExt.getStatusId())) {
            return Result.error("找不到账户信息");
        }

        String redisSmsCode = redisTemplate.opsForValue().get(authUserExtBankBO.getClientId() + authUserExtBankBO.getMobile());
        if (StringUtils.isEmpty(redisSmsCode)) {
            return Result.error("验证码已失效!");
        }

        LecturerExt account = BeanUtil.copyProperties(authUserExtBankBO, LecturerExt.class);
        account.setId(lecturerExt.getId());

        int resultNum = lecturerExtDao.updateById(account);
        if (resultNum < 1) {
            return Result.error(ResultEnum.USER_UPDATE_FAIL.getDesc());
        }
        return Result.success(resultNum);
    }

}
