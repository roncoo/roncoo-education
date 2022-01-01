package com.roncoo.education.system.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.biz.FeignSysRoleUserBiz;
import com.roncoo.education.system.feign.interfaces.IFeignSysRoleUser;
import com.roncoo.education.system.feign.interfaces.qo.SysRoleUserQO;
import com.roncoo.education.system.feign.interfaces.vo.SysRoleUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色用户关联表
 *
 * @author wujing
 */
@RestController
public class FeignSysRoleUserController extends BaseController implements IFeignSysRoleUser {

    @Autowired
    private FeignSysRoleUserBiz biz;

    @Override
    public Page<SysRoleUserVO> listForPage(@RequestBody SysRoleUserQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody SysRoleUserQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody SysRoleUserQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public SysRoleUserVO getById(@RequestBody Long id) {
        return biz.getById(id);
    }

}
