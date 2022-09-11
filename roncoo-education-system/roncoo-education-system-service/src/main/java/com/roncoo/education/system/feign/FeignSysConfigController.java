package com.roncoo.education.system.feign;

import com.roncoo.education.common.core.aliyun.Aliyun;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.biz.FeignSysConfigBiz;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.qo.SysConfigEditQO;
import com.roncoo.education.system.feign.interfaces.qo.SysConfigPageQO;
import com.roncoo.education.system.feign.interfaces.qo.SysConfigSaveQO;
import com.roncoo.education.system.feign.interfaces.vo.SysConfigPageVO;
import com.roncoo.education.system.feign.interfaces.vo.SysConfigViewVO;
import com.roncoo.education.system.feign.interfaces.vo.VodConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 系统配置
 *
 * @author wujing
 * @date 2022-08-25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/sys/config")
public class FeignSysConfigController implements IFeignSysConfig {

    @NotNull
    private final FeignSysConfigBiz biz;

    @Override
    public Page<SysConfigPageVO> page(@RequestBody SysConfigPageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody SysConfigSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody SysConfigEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public SysConfigViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

    @Override
    public Map<String, String> getMapByConfigType(@PathVariable(value = "type") Integer configType) {
        return biz.getMapByConfigType(configType);
    }

    @Override
    public Aliyun getAliyun() {
        return biz.getAliyun();
    }

    @Override
    public VodConfig getVod() {
        return biz.getVod();
    }
}
