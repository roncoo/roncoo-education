package com.roncoo.education.user.service.pc.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.StrUtil;
import com.roncoo.education.user.dao.PlatformDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Platform;
import com.roncoo.education.user.dao.impl.mapper.entity.PlatformExample;
import com.roncoo.education.user.dao.impl.mapper.entity.PlatformExample.Criteria;
import com.roncoo.education.user.service.pc.req.*;
import com.roncoo.education.user.service.pc.resq.PlatformPageRESQ;
import com.roncoo.education.user.service.pc.resq.PlatformViewRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 平台信息
 */
@Component
public class PcApiPlatformBiz {

    @Autowired
    private PlatformDao dao;

    public Result<Page<PlatformPageRESQ>> listForPage(PlatformPageREQ req) {
        PlatformExample example = new PlatformExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getClientName())) {
            c.andClientNameLike(PageUtil.rightLike(req.getClientName()));
        }
        example.setOrderByClause(" status_id desc, sort desc, id desc ");
        Page<Platform> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        return Result.success(PageUtil.transform(page, PlatformPageRESQ.class));
    }

    public Result<Integer> save(PlatformSaveREQ req) {
        if (StringUtils.isEmpty(req.getClientName())) {
            return Result.error("客户端名称不能为空");
        }
        Platform platform = dao.getByClientName(req.getClientName());
        if (ObjectUtil.isNotNull(platform)) {
            return Result.error("客户端名称已添加");
        }
        Platform record = BeanUtil.copyProperties(req, Platform.class);
        record.setClientId("lk" + StrUtil.get32UUID());
        record.setClientSecret(StrUtil.get32UUID());
        int results = dao.save(record);
        if (results < 0) {
            return Result.error(ResultEnum.USER_SAVE_FAIL);
        }
        return Result.success(results);
    }

    public Result<Integer> update(PlatformUpdateREQ req) {
        if (req.getId() == null) {
            return Result.error("ID不能为空");
        }
        Platform record = BeanUtil.copyProperties(req, Platform.class);
        int results = dao.updateById(record);
        if (results < 0) {
            return Result.error(ResultEnum.USER_UPDATE_FAIL);
        }
        return Result.success(results);
    }

    public Result<PlatformViewRESQ> view(PlatformViewREQ req) {
        if (req.getId() == null) {
            return Result.error("ID不能为空");
        }
        Platform record = dao.getById(req.getId());
        if (ObjectUtil.isNull(record)) {
            return Result.error("找不到平台信息");
        }
        return Result.success(BeanUtil.copyProperties(record, PlatformViewRESQ.class));
    }

    public Result<Integer> delete(PlatformDeleteREQ req) {
        if (req.getId() == null) {
            return Result.error("ID不能为空");
        }
        int results = dao.deleteById(req.getId());
        if (results < 0) {
            return Result.error(ResultEnum.USER_DELETE_FAIL);
        }
        return Result.success(results);
    }

}
