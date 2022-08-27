package com.roncoo.education.course.test;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.JSUtil;
import com.roncoo.education.course.service.api.biz.ApiCategoryBiz;
import com.roncoo.education.course.service.api.resp.ApiCategoryResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class CategoryTest extends BaseTest {

    @Autowired
    private ApiCategoryBiz apiCategoryBiz;

    @Test
    public void list() {
        Result<List<ApiCategoryResp>> res = apiCategoryBiz.list();
        log.info("Category={}", JSUtil.toJsonString(res.getData()));
    }
}
