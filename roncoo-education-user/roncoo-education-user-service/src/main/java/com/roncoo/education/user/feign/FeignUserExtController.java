package com.roncoo.education.user.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.biz.FeignUserExtBiz;
import com.roncoo.education.user.feign.interfaces.IFeignUserExt;
import com.roncoo.education.user.feign.interfaces.qo.UserExtEchartsQO;
import com.roncoo.education.user.feign.interfaces.qo.UserExtQO;
import com.roncoo.education.user.feign.interfaces.vo.UserEchartsVO;
import com.roncoo.education.user.feign.interfaces.vo.UserExtVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户教育信息
 *
 * @author wujing
 */
@RestController
public class FeignUserExtController extends BaseController implements IFeignUserExt {

    @Autowired
    private FeignUserExtBiz biz;

    @Override
    public Page<UserExtVO> listForPage(@RequestBody UserExtQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody UserExtQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody UserExtQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public UserExtVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

    @Override

    public UserExtVO getByUserNo(@PathVariable(value = "userNo") Long userNo) {
        return biz.getByUserNo(userNo);
    }

    @Override
    public List<UserEchartsVO> sumByCounts(@RequestBody UserExtEchartsQO userExtEchartsQO) {
        return biz.sumByCounts(userExtEchartsQO);
    }

    @Override
    public void cachUserForMsg() {
        biz.cachUserForMsg();
    }

}
