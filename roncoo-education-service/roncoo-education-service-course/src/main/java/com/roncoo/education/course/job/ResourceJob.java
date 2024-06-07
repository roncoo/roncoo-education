package com.roncoo.education.course.job;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.enums.ResourceTypeEnum;
import com.roncoo.education.common.core.enums.VideoStatusEnum;
import com.roncoo.education.course.callback.biz.VodCommonBiz;
import com.roncoo.education.course.dao.ResourceDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Resource;
import com.roncoo.education.course.dao.impl.mapper.entity.ResourceExample;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.VideoConfig;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 资源状态处理
 *
 * @author fengyw
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ResourceJob {

    @NotNull
    private final ResourceDao resourceDao;
    @NotNull
    private final IFeignSysConfig feignSysConfig;
    @NotNull
    private final VodCommonBiz vodCommonBiz;

    /**
     * 建议：每10分执行一次
     */
    @XxlJob("resourceJobHandler")
    public void resource() {
        // 查找需要处理的视频，针对音视频，在处理状态，超过1小时的
        ResourceExample example = new ResourceExample();
        ResourceExample.Criteria c = example.createCriteria();
        c.andResourceTypeIn(Arrays.asList(ResourceTypeEnum.VIDEO.getCode(), ResourceTypeEnum.AUDIO.getCode()));
        c.andVideoStatusEqualTo(VideoStatusEnum.WAIT.getCode());
        c.andGmtCreateLessThanOrEqualTo(LocalDateTime.now().plusHours(-1));
        List<Resource> list = resourceDao.listByExample(example);
        if (CollUtil.isNotEmpty(list)) {
            VideoConfig videoConfig = feignSysConfig.getVideo();
            for (Resource resource : list) {
                vodCommonBiz.completeUpload(resource.getVideoVid(), videoConfig);
            }
        }
        XxlJobHelper.handleSuccess("完成");
    }

}
