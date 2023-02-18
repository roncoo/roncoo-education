package com.roncoo.education.course.service.api.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.enums.VideoStatusEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.JSUtil;
import com.roncoo.education.common.polyv.PolyvVodUtil;
import com.roncoo.education.common.polyv.vod.CallbackEventTypeEnum;
import com.roncoo.education.common.polyv.vod.CallbackVodUpload;
import com.roncoo.education.common.polyv.vod.resp.PolyvVideoInfoResp;
import com.roncoo.education.course.dao.ResourceDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Resource;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.VodConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 课程分类
 *
 * @author wujing
 */
@Slf4j
@Component
public class ApiCallbackBiz {

    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private IFeignSysConfig feignSysConfig;

    public String polyvUpload(CallbackVodUpload callbackVodUpload) {
        VodConfig vodConfig = feignSysConfig.getVod();
        String sign = PolyvVodUtil.getCallbackSign(BeanUtil.beanToStringMap(callbackVodUpload), vodConfig.getPolyvSecretKey());
        if (!callbackVodUpload.getSign().equals(sign)) {
            log.error("保利威视--点播上传回调--验签失败 {}", JSUtil.toJsonString(callbackVodUpload));
            return FAIL;
        }

        if (CallbackEventTypeEnum.PASS.getCode().equals(callbackVodUpload.getType())) {
            // 视频审核完成处理
            List<PolyvVideoInfoResp> responseList = PolyvVodUtil.getVideoInfo(Collections.singletonList(callbackVodUpload.getVid()), vodConfig.getPolyvUserId(), vodConfig.getPolyvSecretKey());
            if (CollUtil.isNotEmpty(responseList)) {
                PolyvVideoInfoResp videoResponse = responseList.get(0);
                Resource resource = resourceDao.getByVideoVid(callbackVodUpload.getVid());
                if (ObjectUtil.isNotEmpty(resource)) {
                    resource.setVideoLength(videoResponse.getBasicInfo().getDuration().toString());
                    resource.setVideoStatus(VideoStatusEnum.SUCCES.getCode());
                    resourceDao.updateById(resource);
                }
            }
        }
        return SUCCESS;
    }
}
