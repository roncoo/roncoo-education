package com.roncoo.education.system.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.biz.FeignSysMenuBiz;
import com.roncoo.education.system.feign.interfaces.IFeignSysMenu;
import com.roncoo.education.system.feign.interfaces.qo.SysMenuQO;
import com.roncoo.education.system.feign.interfaces.vo.SysMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单信息
 *
 * @author wujing
 */
@RestController
public class FeignSysMenuController extends BaseController implements IFeignSysMenu {

    @Autowired
    private FeignSysMenuBiz biz;

    @Override
    public Page<SysMenuVO> listForPage(@RequestBody SysMenuQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody SysMenuQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody SysMenuQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public SysMenuVO getById(@RequestBody Long id) {
        return biz.getById(id);
    }

    @Override
    public List<String> listByUserAndMenu(@RequestBody Long userNo) {
        return biz.listByUserAndMenu(userNo);
    }

}
