package com.roncoo.education.course.callback;

import com.roncoo.education.common.tools.JsonUtil;
import com.roncoo.education.common.video.impl.priyun.req.PrivateYunVodAuth;
import com.roncoo.education.common.video.impl.priyun.req.PrivateYunVodUpload;
import com.roncoo.education.course.callback.biz.PriYunCallbackBiz;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotNull;

/**
 * 领课云
 *
 * @author wujing
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/course/callback/priyun")
public class PriYunCallbackController {

    @NotNull
    private final PriYunCallbackBiz biz;

    @RequestMapping(value = "/vod/upload", method = {RequestMethod.GET, RequestMethod.POST})
    public String vodUpload(@RequestBody PrivateYunVodUpload vodUpload) {
        log.warn("领课云--上传状态回调--回调参数：{}", JsonUtil.toJsonString(vodUpload));
        return biz.vodUpload(vodUpload);
    }

    @RequestMapping(value = "/vod/auth", method = {RequestMethod.GET, RequestMethod.POST})
    public String auth(@RequestBody PrivateYunVodAuth vodAuth) {
        log.warn("领课云--播放授权地址--回调参数：{}", JsonUtil.toJsonString(vodAuth));
        return biz.auth(vodAuth);
    }

}
