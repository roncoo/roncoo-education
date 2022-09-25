package com.roncoo.education.system.feign.biz;


import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.aliyun.Aliyun;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.SysConfigDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfig;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfigExample;
import com.roncoo.education.system.feign.interfaces.vo.SysConfigViewVO;
import com.roncoo.education.system.feign.interfaces.vo.VodConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统配置
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignSysConfigBiz extends BaseBiz {

    @NotNull
    private final SysConfigDao dao;


    public Aliyun getAliyun() {
        SysConfigExample example = new SysConfigExample();
        List<SysConfig> sysConfigs = dao.listByExample(example);
        Map<String, String> map = sysConfigs.stream().collect(Collectors.toMap(SysConfig::getConfigKey, SysConfig::getConfigValue));
        return BeanUtil.objToBean(map, Aliyun.class);
    }

    public VodConfig getVod() {
        SysConfigExample example = new SysConfigExample();
        List<SysConfig> sysConfigs = dao.listByExample(example);
        Map<String, String> map = sysConfigs.stream().collect(Collectors.toMap(SysConfig::getConfigKey, SysConfig::getConfigValue));
        return BeanUtil.objToBean(map, VodConfig.class);
    }

    public Map<String, String> getMapByConfigType(Integer configType) {
        SysConfigExample example = new SysConfigExample();
        example.createCriteria().andConfigTypeEqualTo(configType);
        List<SysConfig> sysConfigs = dao.listByExample(example);
        return sysConfigs.stream().collect(Collectors.toMap(SysConfig::getConfigKey, SysConfig::getConfigValue));
    }

    public SysConfigViewVO getByConfigKey(String configKey) {
        SysConfigExample example = new SysConfigExample();
        example.createCriteria().andConfigKeyEqualTo(configKey);
        List<SysConfig> sysConfigs = dao.listByExample(example);
        if (CollUtil.isNotEmpty(sysConfigs)) {
            return BeanUtil.copyProperties(sysConfigs.get(0), SysConfigViewVO.class);
        }
        return null;
    }
}
