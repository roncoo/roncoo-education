package com.roncoo.education.system.service.admin.biz;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.SmsPlatformEnum;
import com.roncoo.education.common.core.enums.StoragePlatformEnum;
import com.roncoo.education.common.core.enums.VodPlatformEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.video.VodUtil;
import com.roncoo.education.system.dao.SysConfigDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfig;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfigExample;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfigExample.Criteria;
import com.roncoo.education.system.feign.interfaces.vo.VodConfig;
import com.roncoo.education.system.service.admin.req.AdminSysConfigEditReq;
import com.roncoo.education.system.service.admin.req.AdminSysConfigListReq;
import com.roncoo.education.system.service.admin.req.AdminSysConfigPageReq;
import com.roncoo.education.system.service.admin.req.AdminSysConfigSaveReq;
import com.roncoo.education.system.service.admin.resp.AdminSysConfigListResp;
import com.roncoo.education.system.service.admin.resp.AdminSysConfigPageResp;
import com.roncoo.education.system.service.admin.resp.AdminSysConfigViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ADMIN-系统配置
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminSysConfigBiz extends BaseBiz {

    @NotNull
    private final SysConfigDao dao;

    /**
     * 系统配置分页
     *
     * @param req 系统配置分页查询参数
     * @return 系统配置分页查询结果
     */
    public Result<Page<AdminSysConfigPageResp>> page(AdminSysConfigPageReq req) {
        SysConfigExample example = new SysConfigExample();
        Criteria c = example.createCriteria();
        Page<SysConfig> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminSysConfigPageResp> respPage = PageUtil.transform(page, AdminSysConfigPageResp.class);
        return Result.success(respPage);
    }

    /**
     * 系统配置添加
     *
     * @param req 系统配置
     * @return 添加结果
     */
    public Result<String> save(AdminSysConfigSaveReq req) {
        SysConfig record = BeanUtil.copyProperties(req, SysConfig.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 系统配置查看
     *
     * @param id 主键ID
     * @return 系统配置
     */
    public Result<AdminSysConfigViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminSysConfigViewResp.class));
    }

    /**
     * 系统配置修改
     *
     * @param req 系统配置修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminSysConfigEditReq req) {
        SysConfig record = BeanUtil.copyProperties(req, SysConfig.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 系统配置删除
     *
     * @param id ID主键
     * @return 删除结果
     */
    public Result<String> delete(Long id) {
        if (dao.deleteById(id) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    public Result<List<AdminSysConfigListResp>> list(AdminSysConfigListReq req) {
        SysConfigExample example = new SysConfigExample();
        example.setOrderByClause("sort asc, id desc");

        Criteria c = example.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getConfigType())) {
            c.andConfigTypeEqualTo(req.getConfigType());
            if (req.getConfigType().equals(3)) {
                c.andConfigKeyLike(VodPlatformEnum.byCode(Integer.valueOf(dao.getByConfigKey("vodPlatform").getConfigValue())).getTag());
                example.or(example.createCriteria().andConfigKeyLike(PageUtil.rightLike("vod")));
            }
            if (req.getConfigType().equals(4)) {
                c.andConfigKeyLike(StoragePlatformEnum.byCode(Integer.valueOf(dao.getByConfigKey("storagePlatform").getConfigValue())).getTag());
                example.or(example.createCriteria().andConfigKeyLike(PageUtil.rightLike("storage")));
            }
            if (req.getConfigType().equals(5)) {
                c.andConfigKeyLike(SmsPlatformEnum.byCode(Integer.valueOf(dao.getByConfigKey("smsPlatform").getConfigValue())).getTag());
                example.or(example.createCriteria().andConfigKeyLike(PageUtil.rightLike("sms")));
            }
        }
        if (ObjectUtil.isNotEmpty(req.getContentType())) {
            c.andContentTypeEqualTo(req.getContentType());
        }
        if (StrUtil.isNotBlank(req.getConfigName())) {
            c.andConfigNameLike(PageUtil.like(req.getConfigName()));
        }
        if (StrUtil.isNotBlank(req.getConfigKey())) {
            c.andConfigKeyLike(PageUtil.like(req.getConfigKey()));
        }
        if (ObjectUtil.isNotEmpty(req.getConfigShow())) {
            c.andConfigShowEqualTo(req.getConfigShow());
        }
        if (StrUtil.isNotBlank(req.getRemark())) {
            c.andRemarkLike(PageUtil.like(req.getRemark()));
        }

        List<SysConfig> configList = dao.listByExample(example);
        return Result.success(BeanUtil.copyProperties(configList, AdminSysConfigListResp.class));
    }

    public Result<String> init() {
        Map<String, String> configMap = dao.listByExample(new SysConfigExample()).stream().collect(Collectors.toMap(SysConfig::getConfigKey, SysConfig::getConfigValue));
        VodConfig vodConfig = BeanUtil.objToBean(configMap, VodConfig.class);
        // 初始化
        VodUtil.init(vodConfig);
        return Result.success("操作成功");
    }
}
