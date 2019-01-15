package com.roncoo.education.web.boss.common;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.roncoo.education.util.annotation.AdminLog;
import com.roncoo.education.util.config.SystemUtil;
import com.roncoo.education.util.tools.JSONUtil;
import com.roncoo.education.web.boss.service.dao.SysLogDao;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysLog;
import com.xiaoleilu.hutool.http.HttpUtil;

/**
 * 系统日志，切面处理类
 */
@Aspect
@Component
public class AspectAdminLog {

	@Autowired
	private SysLogDao dao;

	@Pointcut("@annotation(com.roncoo.education.util.annotation.AdminLog)")
	public void logPointCut() {

	}

	@Before("logPointCut()")
	public void saveSysLog(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		SysLog sysLog = new SysLog();
		AdminLog adminlog = signature.getMethod().getAnnotation(AdminLog.class);
		if (adminlog != null) {
			// 注解上的描述
			sysLog.setOperation(adminlog.value());
		}

		// 请求的参数
		Object[] args = joinPoint.getArgs();
		String params = JSONUtil.toJSONString(args[0]);
		if (params.length() < Integer.valueOf(SystemUtil.CONTENT_LENGTH)) {
			sysLog.setContent(params);
		}

		// 获取IP
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			String ip = HttpUtil.getClientIP(request);
			sysLog.setIp(ip);
			sysLog.setPath(request.getServletPath());
			sysLog.setMethod(request.getMethod());
		} catch (Exception e) {
			e.printStackTrace();
			sysLog.setIp("");
		}

		sysLog.setUserNo(SessionUtil.getUserNo());
		sysLog.setRealName(SessionUtil.getRealName());
		dao.save(sysLog);
	}

}
