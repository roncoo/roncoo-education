package com.roncoo.education.user.service.pc.biz;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.roncoo.education.common.core.base.*;
import com.roncoo.education.common.core.enums.AuditStatusEnum;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.enums.UserTypeEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.NOUtil;
import com.roncoo.education.common.core.tools.SignUtil;
import com.roncoo.education.common.core.tools.StrUtil;
import com.roncoo.education.user.dao.*;
import com.roncoo.education.user.dao.impl.mapper.entity.*;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerAuditExample.Criteria;
import com.roncoo.education.user.service.pc.req.*;
import com.roncoo.education.user.service.pc.resq.LecturerAuditPageRESQ;
import com.roncoo.education.user.service.pc.resq.LecturerAuditViewRESQ;
import com.roncoo.education.user.service.pc.resq.LecturerExtViewRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

@Component
public class PcApiLecturerAuditBiz extends BaseBiz {

    @Autowired
    private LecturerAuditDao lecturerAuditDao;
    @Autowired
    private LecturerDao lecturerDao;
    @Autowired
    private LecturerExtDao lecturerExtDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserExtDao userExtDao;

    public Result<Page<LecturerAuditPageRESQ>> listForPage(LecturerAuditPageREQ req) {
        LecturerAuditExample example = new LecturerAuditExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getLecturerMobile())) {
            c.andLecturerMobileEqualTo(req.getLecturerMobile());
        }
        if (StringUtils.hasText(req.getLecturerName())) {
            c.andLecturerNameLike(PageUtil.rightLike(req.getLecturerName()));
        }
        if (req.getAuditStatus() != null) {
            c.andAuditStatusEqualTo(req.getAuditStatus());
        } else {
            c.andAuditStatusNotEqualTo(AuditStatusEnum.SUCCESS.getCode());
        }
        if (req.getStatusId() != null) {
            c.andStatusIdEqualTo(req.getStatusId());
        }
        example.setOrderByClause(" audit_status asc, status_id desc, sort desc, id desc ");
        Page<LecturerAudit> page = lecturerAuditDao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        return Result.success(PageUtil.transform(page, LecturerAuditPageRESQ.class));
    }

    /**
     * 添加讲师
     *
     * @param req
     * @return
     */
    @Transactional
    public Result<Integer> save(LecturerAuditSaveREQ req) {
        if (StringUtils.isEmpty(req.getLecturerMobile())) {
            return Result.error("手机号不能为空");
        }
        // 手机号去空处理
        String mobile = req.getLecturerMobile().trim();
        // 手机号码校验
        if (!Pattern.compile(REGEX_MOBILE).matcher(mobile).matches()) {
            return Result.error("手机号码格式不正确");
        }
        // 根据传入手机号获取用户信息(讲师的用户信息)
        UserExt userExt = userExtDao.getByMobile(mobile);
        // 1、用户不存在，注册用户
        if (ObjectUtil.isNull(userExt)) {
            if (StringUtils.isEmpty(req.getMobilePsw())) {
                return Result.error("密码不能为空");
            }
            if (!req.getConfirmPasswd().equals(req.getMobilePsw())) {
                return Result.error("两次密码不一致，请重试！");
            }
            // 注册用户
            userExt = register(req, mobile);
        }

        // 2、添加讲师
        LecturerAudit lecturerAudit = lecturerAuditDao.getByLecturerUserNo(userExt.getUserNo());
        // 校验讲师是否存在
        if (ObjectUtil.isNotNull(lecturerAudit)) {
            // 讲师存在
            if (AuditStatusEnum.SUCCESS.getCode().equals(lecturerAudit.getAuditStatus())) {
                // 审核成功
                return Result.error(ResultEnum.LECTURER_REQUISITION_YET);
            } else if (AuditStatusEnum.WAIT.getCode().equals(lecturerAudit.getAuditStatus())) {
                // 待审核
                return Result.error(ResultEnum.LECTURER_REQUISITION_WAIT);
            } else {
                return Result.error(ResultEnum.LECTURER_REQUISITION_FAIL);
            }
        } else {
            // 讲师不存在
            int results = lecturerInfo(req, userExt);
            if (results < 0) {
                return Result.error(ResultEnum.USER_SAVE_FAIL);
            }
            return Result.success(results);
        }
    }

    /**
     * 讲师审核修改
     *
     * @param req
     * @return
     */
    public Result<Integer> update(LecturerAuditUpdateREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        LecturerAudit lecturerAudit = lecturerAuditDao.getById(req.getId());
        if (ObjectUtil.isNull(lecturerAudit)) {
            return Result.error("找不到讲师信息");
        }
        LecturerAudit record = BeanUtil.copyProperties(req, LecturerAudit.class);
        record.setAuditStatus(AuditStatusEnum.WAIT.getCode());
        int results = lecturerAuditDao.updateById(record);
        if (results < 0) {
            return Result.error(ResultEnum.USER_UPDATE_FAIL);
        }
        return Result.success(results);
    }

    @Transactional
    public Result<Integer> audit(LecturerAuditAuditREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        if (StringUtils.isEmpty(req.getAuditStatus())) {
            return Result.error("auditStatus不能为空");
        }
        LecturerAudit lecturerAudit = lecturerAuditDao.getById(req.getId());
        if (ObjectUtil.isNull(lecturerAudit)) {
            return Result.error("找不到讲师信息");
        }
        if (AuditStatusEnum.SUCCESS.getCode().equals(req.getAuditStatus())) {
            // 查找讲师信息表，是否存在该讲师
            Lecturer lecturer = lecturerDao.getByLecturerUserNo(lecturerAudit.getLecturerUserNo());
            if (ObjectUtil.isNull(lecturer)) {
                // 插入
                lecturer = BeanUtil.copyProperties(lecturerAudit, Lecturer.class);
                lecturer.setGmtCreate(null);
                lecturer.setGmtModified(null);
                lecturerDao.save(lecturer);
            } else {
                // 更新
                lecturer = BeanUtil.copyProperties(lecturerAudit, Lecturer.class);
                lecturer.setGmtCreate(null);
                lecturer.setGmtModified(null);
                lecturerDao.updateById(lecturer);
            }
            // 查找用户信息是否存在
            UserExt userExt = userExtDao.getByUserNo(lecturer.getLecturerUserNo());
            if (ObjectUtil.isNull(userExt)) {
                return Result.error("获取不到用户信息");
            }
            // 存在更新为讲师类型
            userExt.setUserType(UserTypeEnum.LECTURER.getCode());
            userExtDao.updateById(userExt);
        }
        LecturerAudit record = BeanUtil.copyProperties(req, LecturerAudit.class);
        int results = lecturerAuditDao.updateById(record);
        if (results < 0) {
            return Result.error(ResultEnum.USER_LECTURER_AUDIT);
        }
        return Result.success(results);
    }

    public Result<LecturerAuditViewRESQ> view(LecturerAuditViewREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        LecturerAudit record = lecturerAuditDao.getById(req.getId());
        if (ObjectUtil.isNull(record)) {
            return Result.error("找不到讲师编号");
        }
        LecturerAuditViewRESQ resq = BeanUtil.copyProperties(record, LecturerAuditViewRESQ.class);
        // 查找讲师账户信息
        LecturerExt lecturerExt = lecturerExtDao.getByLecturerUserNo(resq.getLecturerUserNo());
        resq.setLecturerExt(BeanUtil.copyProperties(lecturerExt, LecturerExtViewRESQ.class));
        return Result.success(resq);
    }

    /**
     * 添加用户信息
     */
    private UserExt register(LecturerAuditSaveREQ req, String mobile) {
        // 用户基本信息
        User user = new User();
        user.setUserNo(NOUtil.getUserNo());
        user.setMobile(mobile);
        user.setMobileSalt(StrUtil.get32UUID());
        user.setMobilePsw(DigestUtil.sha1Hex(user.getMobileSalt() + req.getMobilePsw()));
        userDao.save(user);

        // 用户教育信息
        UserExt userExt = new UserExt();
        userExt.setUserNo(user.getUserNo());
        userExt.setMobile(user.getMobile());
        userExt.setNickname(req.getLecturerName());
        userExtDao.save(userExt);
        return userExt;
    }

    /**
     * 添加讲师信息
     */
    private int lecturerInfo(LecturerAuditSaveREQ req, UserExt userExt) {
        // 插入讲师信息
        LecturerAudit infoAudit = BeanUtil.copyProperties(req, LecturerAudit.class);
        if (!StringUtils.isEmpty(userExt.getHeadImgUrl())) {
            infoAudit.setHeadImgUrl(userExt.getHeadImgUrl());
        }
        infoAudit.setLecturerUserNo(userExt.getUserNo());
        infoAudit.setLecturerProportion(LECTURER_DEFAULT_PROPORTION);// 设置讲师默认分成百分之70
        int infoAuditNum = lecturerAuditDao.save(infoAudit);
        if (infoAuditNum < 1) {
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

    public Result<Integer> check(LecturerAuditCheckMobileREQ req) {
        if (StringUtils.isEmpty(req.getLecturerMobile())) {
            return Result.error("手机号不能为空");
        }
        // 手机号去空处理
        String mobile = req.getLecturerMobile().trim();
        // 手机号码校验
        if (!Pattern.compile(REGEX_MOBILE).matcher(mobile).matches()) {
            return Result.error("手机号码格式不正确");
        }
        // 根据传入手机号获取用户信息(讲师的用户信息)
        UserExt userExt = userExtDao.getByMobile(mobile);
        // 1、用户不存在，注册用户
        if (ObjectUtil.isNull(userExt)) {
            return Result.success(501);
        }
        // 2、添加讲师
        LecturerAudit lecturerAudit = lecturerAuditDao.getByLecturerUserNo(userExt.getUserNo());
        // 校验讲师是否存在
        if (ObjectUtil.isNotNull(lecturerAudit)) {
            // 讲师存在
            if (AuditStatusEnum.SUCCESS.getCode().equals(lecturerAudit.getAuditStatus())) {
                // 审核成功
                return Result.success(503);
            } else if (AuditStatusEnum.WAIT.getCode().equals(lecturerAudit.getAuditStatus())) {
                // 待审核
                return Result.success(502);
            } else {
                return Result.success(506);
            }
        }
        // 讲师不存在
        return Result.success(1);
    }
}
