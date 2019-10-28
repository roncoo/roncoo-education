package com.roncoo.education.system.service.feign;

import com.roncoo.education.system.feign.interfaces.IFeignSysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.system.feign.qo.SysRoleQO;
import com.roncoo.education.system.feign.vo.SysRoleVO;
import com.roncoo.education.system.service.feign.biz.FeignSysRoleBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 角色信息
 *
 * @author wujing
 */
@RestController
public class FeignSysRoleController extends BaseController implements IFeignSysRole {

    @Autowired
    private FeignSysRoleBiz biz;

    @Override
    public Page<SysRoleVO> listForPage(@RequestBody SysRoleQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody SysRoleQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody SysRoleQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public SysRoleVO getById(@RequestBody Long id) {
        return biz.getById(id);
    }

}
