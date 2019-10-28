package com.roncoo.education.system.service.feign;

import com.roncoo.education.system.feign.interfaces.IFeignSysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.system.feign.qo.SysUserQO;
import com.roncoo.education.system.feign.vo.SysUserVO;
import com.roncoo.education.system.service.feign.biz.FeignSysUserBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 后台用户信息
 *
 * @author wujing
 */
@RestController
public class FeignSysUserController extends BaseController implements IFeignSysUser {

    @Autowired
    private FeignSysUserBiz biz;

    @Override
    public Page<SysUserVO> listForPage(@RequestBody SysUserQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody SysUserQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody SysUserQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public SysUserVO getById(@RequestBody Long id) {
        return biz.getById(id);
    }

}
