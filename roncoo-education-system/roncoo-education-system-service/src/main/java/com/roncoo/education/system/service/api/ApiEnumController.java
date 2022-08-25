package com.roncoo.education.system.service.api;

import cn.hutool.core.util.StrUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.EnumUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * 获取枚举信息
 *
 * @author liaoh
 */
@RestController
@RequestMapping(value = "/system/api/enum")
public class ApiEnumController {

    private static final String DEFAULT_ENUM_NAME = "name";
    private static final String DEFAULT_PREFIX = "com.roncoo.education.common.core.enums.";

    @ApiOperation(value = "枚举通用接口", notes = "返回枚举信息")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<ArrayList> getEnumInfo(@RequestParam(name = "name") String enumName) {
        if (StrUtil.isBlank(enumName)) {
            return Result.error("请输入要获取的枚举名称");
        }
        String className = new StringBuffer(DEFAULT_PREFIX).append(enumName).toString();
        try {
            Class clazz = Class.forName(className);
            return Result.success(new ArrayList<>(EnumUtil.toList(clazz, DEFAULT_ENUM_NAME)));
        } catch (ClassNotFoundException e) {
            return Result.error("获取枚举失败");
        }
    }

}
