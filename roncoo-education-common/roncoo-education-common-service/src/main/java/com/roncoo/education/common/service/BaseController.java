/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.service;

import com.roncoo.education.common.core.base.Base;
import com.roncoo.education.common.core.enums.*;
import com.roncoo.education.common.core.tools.JSUtil;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.io.DataInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.TreeMap;

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
            if (null != in) {
                try {
                    in.close();// 关闭数据流
                } catch (IOException e) {
                }
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
            return new TreeMap<>(JSUtil.parseObject(getString(request), TreeMap.class));
        }
        return paramMap;
    }

}
