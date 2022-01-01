package com.roncoo.education.user.service.auth.biz;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.roncoo.education.common.core.base.BaseBiz;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.AuditStatusEnum;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.NOUtil;
import com.roncoo.education.common.core.tools.SignUtil;
import com.roncoo.education.common.core.tools.StrUtil;
import com.roncoo.education.user.dao.LecturerAuditDao;
import com.roncoo.education.user.dao.LecturerExtDao;
import com.roncoo.education.user.dao.UserDao;
import com.roncoo.education.user.dao.UserExtDao;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerAudit;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerExt;
import com.roncoo.education.user.dao.impl.mapper.entity.User;
import com.roncoo.education.user.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.user.service.auth.bo.AuthLecturerAuditBO;
import com.roncoo.education.user.service.auth.bo.AuthLecturerAuditSaveBO;
import com.roncoo.education.user.service.auth.bo.AuthLecturerAuditViewBO;
import com.roncoo.education.user.service.auth.dto.AuthLecturerAuditViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
@Component
public class AuthApiLecturerAuditBiz extends BaseBiz {

    @Autowired
    private LecturerAuditDao lecturerAuditDao;
    @Autowired
    private LecturerExtDao lecturerExtDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserExtDao userExtDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 讲师信息修改接口
     *
     * @param authLecturerAuditBO
     * @author wuyun
     */
    public Result<Integer> update(AuthLecturerAuditBO authLecturerAuditBO) {
        LecturerAudit record = BeanUtil.copyProperties(authLecturerAuditBO, LecturerAudit.class);
        record.setAuditStatus(AuditStatusEnum.WAIT.getCode());
        record.setAuditOpinion("");
        int resultNum = lecturerAuditDao.updateById(record);
        if (resultNum > 0) {
            return Result.success(resultNum);
        }
        return Result.error(ResultEnum.USER_UPDATE_FAIL.getDesc());
    }

    /**
     * 讲师信息查看接口
     *
     * @param authLecturerAuditViewBO
     * @author wuyun
     */
    public Result<AuthLecturerAuditViewDTO> view(AuthLecturerAuditViewBO authLecturerAuditViewBO) {
        if (null == authLecturerAuditViewBO.getLecturerUserNo()) {
            return Result.error("讲师编号不能为空");
        }
        LecturerAudit lecturerAudit = lecturerAuditDao.getByLecturerUserNo(authLecturerAuditViewBO.getLecturerUserNo());
        if (lecturerAudit == null) {
            return Result.error("找不到该讲师");
        }
        return Result.success(BeanUtil.copyProperties(lecturerAudit, AuthLecturerAuditViewDTO.class));
    }

    /**
     * 讲师申请接口
     *
     * @param authLecturerAuditSaveBO
     * @author wuyun
     */
    @Transactional
    public Result<Integer> save(AuthLecturerAuditSaveBO authLecturerAuditSaveBO) {
        if (StringUtils.isEmpty(authLecturerAuditSaveBO.getLecturerMobile())) {
            return Result.error("lecturerMobile不能为空");
        }
        // 手机号去空处理
        authLecturerAuditSaveBO.setLecturerMobile(authLecturerAuditSaveBO.getLecturerMobile().trim());
        // 手机号码校验
        if (!Pattern.compile(REGEX_MOBILE).matcher(authLecturerAuditSaveBO.getLecturerMobile()).matches()) {
            return Result.error("手机号码格式不正确");
        }

        // 根据传入的手机号与正常状态获取用户信息
        UserExt userExt = userExtDao.getByMobile(authLecturerAuditSaveBO.getLecturerMobile());
        if (ObjectUtil.isNull(userExt) || StatusIdEnum.NO.getCode().equals(userExt.getStatusId())) {
            // 新用户
            if (StringUtils.isEmpty(authLecturerAuditSaveBO.getCode())) {
                return Result.error("验证码不能为空");
            }
            // 验证码校验
            String redisSmsCode = redisTemplate.opsForValue().get(authLecturerAuditSaveBO.getClientId() + authLecturerAuditSaveBO.getLecturerMobile());
            if (StringUtils.isEmpty(redisSmsCode)) {
                return Result.error("验证码过期，请重新获取");
            }
            if (!redisSmsCode.equals(authLecturerAuditSaveBO.getCode())) {
                return Result.error("验证码不正确,重新输入");
            }
            // 密码校验
            if (!authLecturerAuditSaveBO.getMobilePsw().equals(authLecturerAuditSaveBO.getRepassword())) {
                return Result.error("密码不一致");
            }
            // 用户不存在，注册用户
            userExt = register(authLecturerAuditSaveBO);
        }

        // 老用户
        LecturerAudit lecturerAudit = lecturerAuditDao.getByLecturerUserNo(userExt.getUserNo());
        if (ObjectUtil.isNotNull(lecturerAudit)) {
            if (AuditStatusEnum.WAIT.getCode().equals(lecturerAudit.getAuditStatus())) {
                return Result.error(ResultEnum.LECTURER_REQUISITION_WAIT.getDesc());
            }
            if (AuditStatusEnum.SUCCESS.getCode().equals(lecturerAudit.getAuditStatus())) {
                return Result.error(ResultEnum.LECTURER_REQUISITION_YET.getDesc());
            }
            if (AuditStatusEnum.FAIL.getCode().equals(lecturerAudit.getAuditStatus())) {
                // 更新讲师审核表为待审核状态
                return Result.success(updateLecturer(lecturerAudit));
            }
        }

        // 添加讲师-只是添加审核表，需要审核
        return Result.success(addLecturer(authLecturerAuditSaveBO, userExt));
    }

    /**
     * 更新讲师信息
     */
    private int updateLecturer(LecturerAudit lecturerAudit) {
        lecturerAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
        int lecturerAuditNum = lecturerAuditDao.updateById(lecturerAudit);
        if (lecturerAuditNum < 1) {
            throw new BaseException("更新讲师信息失败");
        }
        return lecturerAuditNum;
    }

    /**
     * 插入讲师信息
     */
    private int addLecturer(AuthLecturerAuditSaveBO authLecturerAuditSaveBO, UserExt userExt) {
        // 插入讲师信息
        LecturerAudit infoAudit = BeanUtil.copyProperties(authLecturerAuditSaveBO, LecturerAudit.class);
        if (!StringUtils.isEmpty(userExt.getHeadImgUrl())) {
            infoAudit.setHeadImgUrl(userExt.getHeadImgUrl());
        }
        infoAudit.setLecturerUserNo(userExt.getUserNo());
        infoAudit.setLecturerProportion(LECTURER_DEFAULT_PROPORTION);// 讲师默认分成
        int lecturerAuditNum = lecturerAuditDao.save(infoAudit);
        if (lecturerAuditNum < 1) {
            throw new BaseException("讲师信息表新增失败");
        }

        // 插入讲师账户
        LecturerExt lecturerExt = new LecturerExt();
        lecturerExt.setLecturerUserNo(infoAudit.getLecturerUserNo());
        lecturerExt.setTotalIncome(BigDecimal.ZERO);
        lecturerExt.setHistoryMoney(BigDecimal.ZERO);
        lecturerExt.setEnableBalances(BigDecimal.ZERO);
        lecturerExt.setFreezeBalances(BigDecimal.ZERO);
        lecturerExt.setSign(SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances()));
        int lecturerExtNum = lecturerExtDao.save(lecturerExt);
        if (lecturerExtNum < 1) {
            throw new BaseException("讲师账户表新增失败");
        }

        return lecturerExtNum;
    }

    /**
     * 添加用户信息
     */
    private UserExt register(AuthLecturerAuditSaveBO authLecturerAuditSaveBO) {
        // 用户基本信息
        User user = new User();
        user.setUserNo(NOUtil.getUserNo());
        user.setMobile(authLecturerAuditSaveBO.getLecturerMobile());
        user.setMobileSalt(StrUtil.get32UUID());
        user.setMobilePsw(DigestUtil.sha1Hex(user.getMobileSalt() + authLecturerAuditSaveBO.getMobilePsw()));
        user.setUserSource(authLecturerAuditSaveBO.getClientId());
        int userNum = userDao.save(user);
        if (userNum < 1) {
            throw new BaseException("用户信息表新增失败");
        }

        // 用户教育信息
        UserExt userExt = new UserExt();
        userExt.setUserNo(user.getUserNo());
        userExt.setMobile(user.getMobile());
        userExt.setNickname(authLecturerAuditSaveBO.getLecturerName());
        int userExtNum = userExtDao.save(userExt);
        if (userExtNum < 1) {
            throw new BaseException("用户教育信息表新增失败");
        }

        return userExt;
    }

}
