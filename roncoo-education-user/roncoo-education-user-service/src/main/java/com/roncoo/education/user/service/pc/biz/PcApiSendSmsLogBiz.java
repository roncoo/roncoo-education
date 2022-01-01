package com.roncoo.education.user.service.pc.biz;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.aliyuncs.exceptions.ClientException;
import com.roncoo.education.common.core.aliyun.Aliyun;
import com.roncoo.education.common.core.aliyun.AliyunUtil;
import com.roncoo.education.common.core.base.BaseBiz;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.IsSuccessEnum;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.DateUtil;
import com.roncoo.education.system.feign.interfaces.IFeignSys;
import com.roncoo.education.system.feign.interfaces.vo.SysVO;
import com.roncoo.education.user.dao.PlatformDao;
import com.roncoo.education.user.dao.SendSmsLogDao;
import com.roncoo.education.user.dao.UserDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Platform;
import com.roncoo.education.user.dao.impl.mapper.entity.SendSmsLog;
import com.roncoo.education.user.dao.impl.mapper.entity.SendSmsLogExample;
import com.roncoo.education.user.dao.impl.mapper.entity.SendSmsLogExample.Criteria;
import com.roncoo.education.user.dao.impl.mapper.entity.User;
import com.roncoo.education.user.service.pc.req.SendSmsLogPageREQ;
import com.roncoo.education.user.service.pc.req.SendSmsLogSendREQ;
import com.roncoo.education.user.service.pc.resq.SendSmsLogPageRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@Component
public class PcApiSendSmsLogBiz extends BaseBiz {

    private static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199)\\d{8}$";// 手机号码校验

    @Autowired
    private IFeignSys feignSys;

    @Autowired
    private SendSmsLogDao dao;
    @Autowired
    private PlatformDao platformDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public Result<Page<SendSmsLogPageRESQ>> listForPage(SendSmsLogPageREQ req) {
        SendSmsLogExample example = new SendSmsLogExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getMobile())) {
            c.andMobileEqualTo(req.getMobile());
        }
        if (StringUtils.hasText(req.getBeginGmtCreate())) {
            c.andGmtCreateGreaterThanOrEqualTo(DateUtil.parseDate(req.getBeginGmtCreate(), "yyyy-MM-dd"));
        }
        if (StringUtils.hasText(req.getEndGmtCreate())) {
            c.andGmtCreateLessThanOrEqualTo(DateUtil.addDate(DateUtil.parseDate(req.getEndGmtCreate(), "yyyy-MM-dd"), 1));
        }
        example.setOrderByClause(" id desc ");
        Page<SendSmsLog> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        return Result.success(PageUtil.transform(page, SendSmsLogPageRESQ.class));
    }

    public Result<Integer> send(SendSmsLogSendREQ req) {
        if (StringUtils.isEmpty(req.getMobile())) {
            return Result.error("手机号不能为空");
        }
        // 手机号码校验
        if (!Pattern.compile(REGEX_MOBILE).matcher(req.getMobile()).matches()) {
            return Result.error("手机号码格式不正确");
        }
        User user = userDao.getByMobile(req.getMobile());
        if (ObjectUtil.isNull(user)) {
            return Result.error("用户不存在");
        }
        Platform platform = platformDao.getByClientId(user.getUserSource());
        if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
            return Result.error("平台状态异常");
        }

        if (redisTemplate.hasKey(platform.getClientId() + req.getMobile())) {
            return Result.error("操作过于频繁，请稍后重试（不少于5分钟）");
        }
        SysVO sys = feignSys.getSys();
        if (ObjectUtil.isNull(sys)) {
            return Result.error("未配置系统配置表");
        }
        if (StringUtils.isEmpty(sys.getAliyunAccessKeyId()) || StringUtils.isEmpty(sys.getAliyunAccessKeySecret())) {
            return Result.error("aliyunAccessKeyId或aliyunAccessKeySecret未配置");
        }
        if (StringUtils.isEmpty(sys.getSmsCode()) || StringUtils.isEmpty(sys.getSignName())) {
            return Result.error("smsCode或signName未配置");
        }
        // 创建日志实例
        SendSmsLog sendSmsLog = new SendSmsLog();
        sendSmsLog.setMobile(req.getMobile());
        sendSmsLog.setTemplate(sys.getSmsCode());
        // 随机生成验证码
        sendSmsLog.setSmsCode(RandomUtil.randomNumbers(6));
        try {
            // 发送验证码
            boolean result = AliyunUtil.sendMsg(req.getMobile(), sendSmsLog.getSmsCode(), BeanUtil.copyProperties(sys, Aliyun.class));
            if (result) {
                // 成功发送，验证码存入缓存：5分钟有效
                redisTemplate.opsForValue().set(platform.getClientId() + req.getMobile(), sendSmsLog.getSmsCode(), 5, TimeUnit.MINUTES);
                sendSmsLog.setIsSuccess(IsSuccessEnum.SUCCESS.getCode());
                int results = dao.save(sendSmsLog);
                if (results > 0) {
                    return Result.success(results);
                }
                return Result.error(ResultEnum.USER_SEND_FAIL);
            }
            // 发送失败
            sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
            dao.save(sendSmsLog);
            return Result.error(ResultEnum.USER_SEND_FAIL);
        } catch (ClientException e) {
            sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
            dao.save(sendSmsLog);
            logger.error("发送失败，原因={}", e);
            return Result.error(ResultEnum.USER_SEND_FAIL);
        }
    }

}
