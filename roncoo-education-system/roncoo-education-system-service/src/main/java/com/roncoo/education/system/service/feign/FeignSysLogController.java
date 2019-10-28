package com.roncoo.education.system.service.feign;

import com.roncoo.education.system.feign.interfaces.IFeignSysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.system.feign.qo.SysLogQO;
import com.roncoo.education.system.feign.vo.SysLogVO;
import com.roncoo.education.system.service.feign.biz.FeignSysLogBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 后台操作日志表
 *
 * @author wujing
 */
@RestController
public class FeignSysLogController extends BaseController implements IFeignSysLog {

    @Autowired
    private FeignSysLogBiz biz;

    @Override
    public Page<SysLogVO> listForPage(@RequestBody SysLogQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody SysLogQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody SysLogQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public SysLogVO getById(@RequestBody Long id) {
        return biz.getById(id);
    }

}
