package com.roncoo.education.system.service.feign;

import com.roncoo.education.system.feign.interfaces.IFeignSysMenuRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.system.feign.qo.SysMenuRoleQO;
import com.roncoo.education.system.feign.vo.SysMenuRoleVO;
import com.roncoo.education.system.service.feign.biz.FeignSysMenuRoleBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 菜单角色关联表
 *
 * @author wujing
 */
@RestController
public class FeignSysMenuRoleController extends BaseController implements IFeignSysMenuRole {

    @Autowired
    private FeignSysMenuRoleBiz biz;

    @Override
    public Page<SysMenuRoleVO> listForPage(@RequestBody SysMenuRoleQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody SysMenuRoleQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody SysMenuRoleQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public SysMenuRoleVO getById(@RequestBody Long id) {
        return biz.getById(id);
    }

}
