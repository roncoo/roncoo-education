package com.roncoo.education.user.job;

import com.roncoo.education.user.service.admin.biz.AdminLecturerBiz;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 同步讲师数据到es
 *
 * @author fengyw
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LecturerJob {

    @NotNull
    private final AdminLecturerBiz adminLecturerBiz;

    /**
     * 建议：每天凌晨5点执行一次
     */
    @XxlJob("courseJobHandler")
    public void course() {
        adminLecturerBiz.syncEs();
        XxlJobHelper.handleSuccess("完成");
    }

}
