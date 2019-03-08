/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.base;

import java.io.DataInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.roncoo.education.util.enums.AdvTargetEnum;
import com.roncoo.education.util.enums.AuditStatusEnum;
import com.roncoo.education.util.enums.CategoryTypeEnum;
import com.roncoo.education.util.enums.ChannelTypeEnum;
import com.roncoo.education.util.enums.CourseCategoryEnum;
import com.roncoo.education.util.enums.CourseTypeEnum;
import com.roncoo.education.util.enums.ExtractStatusEnum;
import com.roncoo.education.util.enums.FileTypeEnum;
import com.roncoo.education.util.enums.IsDefaultEnum;
import com.roncoo.education.util.enums.IsEnableStatisticsEnum;
import com.roncoo.education.util.enums.IsFreeEnum;
import com.roncoo.education.util.enums.IsPayEnum;
import com.roncoo.education.util.enums.IsPutawayEnum;
import com.roncoo.education.util.enums.IsSendEnum;
import com.roncoo.education.util.enums.IsShowServiceEnum;
import com.roncoo.education.util.enums.IsSuccessEnum;
import com.roncoo.education.util.enums.IsTimeSendEnum;
import com.roncoo.education.util.enums.MsgTypeEnum;
import com.roncoo.education.util.enums.NavEnum;
import com.roncoo.education.util.enums.OrderStatusEnum;
import com.roncoo.education.util.enums.PayTypeEnum;
import com.roncoo.education.util.enums.PlatformEnum;
import com.roncoo.education.util.enums.ProfitStatusEnum;
import com.roncoo.education.util.enums.SexEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.enums.SysTypeEnum;
import com.roncoo.education.util.enums.TradeStatusEnum;
import com.roncoo.education.util.enums.TradeTypeEnum;
import com.roncoo.education.util.enums.UserTypeEnum;
import com.roncoo.education.util.enums.VideoStatusEnum;
import com.roncoo.education.util.enums.VideoTypeEnum;
import com.roncoo.education.util.enums.ZoneLocationEnum;
import com.roncoo.education.util.tools.JSONUtil;

/**
 * 控制基础类，所以controller都应该继承这个类
 * 
 * @author wujing
 */
public class BaseController extends Base {

	public static final String TEXT_UTF8 = "text/html;charset=UTF-8";
	public static final String JSON_UTF8 = "application/json;charset=UTF-8";
	public static final String XML_UTF8 = "application/xml;charset=UTF-8";

	public static final int OK = 200;
	public static final int ER = 300;
	public static final int TO = 301;
	public static final boolean CLOSE = true;
	public static final boolean OPEN = false;

	@ModelAttribute
	public void enums(ModelMap modelMap) {
		modelMap.put("advTargetEnums", AdvTargetEnum.values());
		modelMap.put("auditStatusEnums", AuditStatusEnum.values());
		modelMap.put("categoryTypeEnums", CategoryTypeEnum.values());
		modelMap.put("channelTypeEnums", ChannelTypeEnum.values());
		modelMap.put("courseCategoryEnums", CourseCategoryEnum.values());
		modelMap.put("courseTypeEnums", CourseTypeEnum.values());
		modelMap.put("isEnableStatisticsEnums", IsEnableStatisticsEnum.values());
		modelMap.put("isFreeEnums", IsFreeEnum.values());
		modelMap.put("isPayEnums", IsPayEnum.values());
		modelMap.put("isPutawayEnums", IsPutawayEnum.values());
		modelMap.put("isShowServiceEnums", IsShowServiceEnum.values());
		modelMap.put("navEnum", NavEnum.values());
		modelMap.put("orderStatusEnums", OrderStatusEnum.values());
		modelMap.put("payTypeEnums", PayTypeEnum.values());
		modelMap.put("profitList", ExtractStatusEnum.values());
		modelMap.put("profitStatusEnums", ProfitStatusEnum.values());
		modelMap.put("platformEnums", PlatformEnum.values());
		modelMap.put("sexEnums", SexEnum.values());
		modelMap.put("statusIdEnums", StatusIdEnum.values());
		modelMap.put("tradeStatusEnums", TradeStatusEnum.values());
		modelMap.put("tradeTypeEnums", TradeTypeEnum.values());
		modelMap.put("userTypeEnums", UserTypeEnum.values());
		modelMap.put("videoStatusEnums", VideoStatusEnum.values());
		modelMap.put("zoneLocationEnums", ZoneLocationEnum.values());
		modelMap.put("videoTypeEnums", VideoTypeEnum.values());
		modelMap.put("sysTypeEnums", SysTypeEnum.values());
		modelMap.put("fileTypeEnums", FileTypeEnum.values());
		modelMap.put("isDefaultEnums", IsDefaultEnum.values());
		modelMap.put("isSendEnums", IsSendEnum.values());
		modelMap.put("isTimeSendEnums", IsTimeSendEnum.values());
		modelMap.put("msgTypeEnums", MsgTypeEnum.values());
		modelMap.put("isSuccessEnums", IsSuccessEnum.values());
	}

	/**
	 * 重定向
	 * 
	 * @param format
	 * @param arguments
	 * @return
	 */
	public static String redirect(String format, Object... arguments) {
		return new StringBuffer("redirect:").append(MessageFormat.format(format, arguments)).toString();
	}

	public static String getString(HttpServletRequest request) {
		DataInputStream in = null;
		try {
			in = new DataInputStream(request.getInputStream());
			byte[] buf = new byte[request.getContentLength()];
			in.readFully(buf);
			return new String(buf, "UTF-8");
		} catch (Exception e) {
			return "";
		} finally {
			if (null != in)
				try {
					in.close();// 关闭数据流
				} catch (IOException e) {
				}
		}
	}

	@SuppressWarnings("unchecked")
	public static TreeMap<String, Object> getParamMap(HttpServletRequest request) {
		TreeMap<String, Object> paramMap = new TreeMap<>();
		Map<String, String[]> map = request.getParameterMap();
		for (String key : map.keySet()) {
			paramMap.put(key, map.get(key)[0]);
		}
		if (paramMap.isEmpty()) {
			return new TreeMap<>(JSONUtil.parseObject(getString(request), TreeMap.class));
		}
		return paramMap;
	}

	/**
	 * 成功提示，关闭当前对话框，并对tabid进行刷新
	 * 
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * 
	 * @return json字符串
	 */
	protected static String success(String targetId) {
		return bjui(OK, CLOSE, targetId, "操作成功");
	}

	/**
	 * 成功提示，关闭当前对话框，并对tabid进行刷新
	 * 
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * @param message
	 *            提示信息
	 * 
	 * @return json字符串
	 */
	protected static String success(String targetId, String message) {
		return bjui(OK, CLOSE, targetId, message);
	}

	/**
	 * 删除成功提示，不关闭当前对话框
	 * 
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * @return
	 */
	protected static String delete(String targetId) {
		return delete(targetId, "操作成功");
	}

	/**
	 * 删除成功提示，不关闭当前对话框
	 * 
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * @param message
	 *            提示信息
	 * @return
	 */
	protected static String delete(String targetId, String message) {
		return bjui(OK, OPEN, targetId, message);
	}

	/**
	 * 信息提示，不关闭当前对话框
	 * 
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * @param message
	 *            提示信息
	 * @return
	 */
	protected static String ties(String message) {
		return bjui(OK, OPEN, "", message);
	}

	/**
	 * 错误提示，不关闭当前对话框，自定义提示信息
	 * 
	 * @param message
	 *            提示信息
	 * @return
	 */
	protected static String error(String message) {
		return bjui(ER, OPEN, "", message);
	}

	/**
	 * 错误提示，不关闭当前对话框，自定义提示信息
	 * 
	 * @param message
	 *            提示信息
	 * @return
	 */
	protected static String error(BindingResult bindingResult) {
		StringBuilder sb = new StringBuilder();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			sb.append(fieldError.getDefaultMessage()).append("<br/>");
		}
		return error(sb.toString());
	}

	/**
	 */
	protected static String redirectForBJUI(String forward) {
		Bjui bj = new Bjui();
		bj.setStatusCode(OK);
		bj.setForward(forward);
		return JSONUtil.toJSONString(bj);
	}

	/**
	 */
	private static String bjui(int statusCode, Boolean closeCurrent, String targetId, String message) {
		Bjui bj = new Bjui();
		bj.setStatusCode(statusCode);
		bj.setCloseCurrent(closeCurrent);
		bj.setTabid(targetId);
		bj.setMessage(message);
		return JSONUtil.toJSONString(bj);
	}

}
