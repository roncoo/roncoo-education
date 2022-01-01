package com.roncoo.education.system.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.biz.FeignMsgTemplateBiz;
import com.roncoo.education.system.feign.interfaces.IFeignMsgTemplate;
import com.roncoo.education.system.feign.interfaces.qo.MsgTemplateQO;
import com.roncoo.education.system.feign.interfaces.vo.MsgTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 消息模板
 *
 * @author wuyun
 */
@RestController
public class FeignMsgTemplateController extends BaseController implements IFeignMsgTemplate {

    @Autowired
    private FeignMsgTemplateBiz biz;

    @Override
    public Page<MsgTemplateVO> listForPage(@RequestBody MsgTemplateQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody MsgTemplateQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody MsgTemplateQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public MsgTemplateVO getById(@RequestBody Long id) {
        return biz.getById(id);
    }

   /* @Override
    public String getRemarkByActTypeNoticeTypeAndOrgNo(@PathVariable Integer actType, @PathVariable Integer noticeType, @PathVariable String orgNo) {
        return biz.getRemarkByActTypeNoticeTypeAndOrgNo(actType, noticeType, orgNo);
    }

	@Override
	public MsgTemplateVO getByActTypeAndNoticeTypeAndOrgNo(@RequestBody MsgTemplateQO qo) {
		 return biz.getByActTypeAndNoticeTypeAndOrgNo(qo);
	}*/

}
