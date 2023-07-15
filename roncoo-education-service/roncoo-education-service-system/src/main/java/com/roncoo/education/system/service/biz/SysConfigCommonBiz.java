package com.roncoo.education.system.service.biz;

import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.SysConfigDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfig;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfigExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 点播直播统计
 *
 * @author wujing
 */
@Slf4j
@Component
public class SysConfigCommonBiz extends BaseBiz {

    @Autowired
    private SysConfigDao dao;

    public <T> T getSysConfig(Class<T> clazz) {
        List<SysConfig> sysConfigs = dao.listByExample(new SysConfigExample());
        Map<String, String> map = sysConfigs.stream().collect(Collectors.toMap(SysConfig::getConfigKey, SysConfig::getConfigValue));
        return BeanUtil.objToBean(map, clazz);
    }
}
