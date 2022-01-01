package com.roncoo.education.system.service.pc.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.MsgTemplateDao;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgTemplate;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgTemplateExample;
import com.roncoo.education.system.service.pc.req.*;
import com.roncoo.education.system.service.pc.resq.MsgTemplatePageRESQ;
import com.roncoo.education.system.service.pc.resq.MsgTemplateViewRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 消息模板
 */
@Component
public class PcApiMsgTemplateBiz {

    @Autowired
    private MsgTemplateDao dao;

    public Result<Page<MsgTemplatePageRESQ>> list(MsgTemplatePageREQ req) {
        MsgTemplateExample example = new MsgTemplateExample();
        example.setOrderByClause(" status_id desc, sort desc, id desc ");
        Page<MsgTemplate> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        return Result.success(PageUtil.transform(page, MsgTemplatePageRESQ.class));
    }

    public Result<Integer> save(MsgTemplateSaveREQ req) {
        if (StringUtils.isEmpty(req.getTitle())) {
            return Result.error("标题不能为空");
        }
        MsgTemplate record = BeanUtil.copyProperties(req, MsgTemplate.class);
        int results = dao.save(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
    }

    public Result<Integer> delete(MsgTemplateDeleteREQ req) {
        if (req.getId() == null) {
            return Result.error("ID不能为空");
        }
        int result = dao.deleteById(req.getId());
        if (result < 0) {
            return Result.error(ResultEnum.SYSTEM_DELETE_FAIL);
        }
        return Result.success(result);
    }

    public Result<Integer> update(MsgTemplateUpdeteREQ req) {
        if (req.getId() == null) {
            return Result.error("ID不能为空");
        }
        MsgTemplate record = BeanUtil.copyProperties(req, MsgTemplate.class);
        int result = dao.updateById(record);
        if (result < 0) {
            return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
        }
        return Result.success(result);
    }

    public Result<MsgTemplateViewRESQ> view(MsgTemplateViewREQ req) {
        if (req.getId() == null) {
            return Result.error("ID不能为空");
        }

        MsgTemplate msgTemplate = dao.getById(req.getId());
        if (ObjectUtil.isNull(msgTemplate)) {
            return Result.error("找不到消息模板信息");
        }
        return Result.success(BeanUtil.copyProperties(msgTemplate, MsgTemplateViewRESQ.class));
    }

}
