package com.roncoo.education.course.test;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.JSUtil;
import com.roncoo.education.course.service.api.biz.ApiZoneBiz;
import com.roncoo.education.course.service.api.resp.ApiZoneResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
public class ZoneTest extends BaseTest {

    @Resource
    private ApiZoneBiz apiZoneBiz;

    @Test
    public void list() {
        Result<List<ApiZoneResp>> result = apiZoneBiz.list();
        log.info("zone={}", JSUtil.toJsonString(result.getData()));
    }
}
