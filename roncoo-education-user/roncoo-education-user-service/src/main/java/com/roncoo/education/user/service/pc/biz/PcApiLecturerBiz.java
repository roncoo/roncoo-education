package com.roncoo.education.user.service.pc.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.user.dao.LecturerAuditDao;
import com.roncoo.education.user.dao.LecturerDao;
import com.roncoo.education.user.dao.LecturerExtDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Lecturer;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerAudit;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerExample;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerExample.Criteria;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerExt;
import com.roncoo.education.user.service.pc.req.LecturerPageREQ;
import com.roncoo.education.user.service.pc.req.LecturerUpdateREQ;
import com.roncoo.education.user.service.pc.req.LecturerViewREQ;
import com.roncoo.education.user.service.pc.resq.LecturerExtViewRESQ;
import com.roncoo.education.user.service.pc.resq.LecturerPageRESQ;
import com.roncoo.education.user.service.pc.resq.LecturerViewRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * 讲师信息
 *
 * @author wujing
 */
@Component
public class PcApiLecturerBiz {

    @Autowired
    private LecturerDao lecturerDao;
    @Autowired
    private LecturerAuditDao lecturerAuditDao;
    @Autowired
    private LecturerExtDao lecturerExtDao;

    public Result<Page<LecturerPageRESQ>> listForPage(LecturerPageREQ req) {
        LecturerExample example = new LecturerExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getLecturerMobile())) {
            c.andLecturerMobileLike(PageUtil.like(req.getLecturerMobile()));
        }
        if (StringUtils.hasText(req.getLecturerName())) {
            c.andLecturerNameLike(PageUtil.rightLike(req.getLecturerName()));
        }
        if (req.getStatusId() != null) {
            c.andStatusIdEqualTo(req.getStatusId());
        }
        example.setOrderByClause(" status_id desc, sort desc, id desc ");
        Page<Lecturer> page = lecturerDao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        Page<LecturerPageRESQ> listPage = PageUtil.transform(page, LecturerPageRESQ.class);
        for (LecturerPageRESQ resq : listPage.getList()) {
            resq.setLecturerProportion(resq.getLecturerProportion().multiply(BigDecimal.valueOf(100)));
        }
        return Result.success(listPage);
    }

    /**
     * 讲师信息查看接口
     *
     * @param req
     */
    public Result<LecturerViewRESQ> view(LecturerViewREQ req) {
        Lecturer record = new Lecturer();
        if (req.getId() != null) {
            record = lecturerDao.getById(req.getId());
        }
        if (req.getLecturerUserNo() != null) {
            record = lecturerDao.getByLecturerUserNoAndStatusId(req.getLecturerUserNo(), StatusIdEnum.YES.getCode());
        }
        if (ObjectUtil.isNull(record)) {
            return Result.error("找不到该讲师信息");
        }
        LecturerViewRESQ vo = BeanUtil.copyProperties(record, LecturerViewRESQ.class);
        if (vo.getLecturerProportion() != null) {
            vo.setLecturerProportion(vo.getLecturerProportion().multiply(BigDecimal.valueOf(100)));
        }
        // 讲师账户信息
        LecturerExt lecturerExt = lecturerExtDao.getByLecturerUserNo(vo.getLecturerUserNo());
        vo.setLecturerExt(BeanUtil.copyProperties(lecturerExt, LecturerExtViewRESQ.class));
        return Result.success(vo);
    }

    /**
     * 更新讲师信息直接生效
     *
     * @param req
     * @return
     */
    @Transactional
    public Result<Integer> update(LecturerUpdateREQ req) {
        if (null == req.getId()) {
            return Result.error("讲师编号不能为空");
        }
        Lecturer lecturer = lecturerDao.getById(req.getId());
        if (ObjectUtil.isNull(lecturer)) {
            return Result.error("找不到该讲师");
        }
        Lecturer record = BeanUtil.copyProperties(req, Lecturer.class);
        if (req.getLecturerProportion() != null) {
            record.setLecturerProportion(req.getLecturerProportion().divide(BigDecimal.valueOf(100)));
        }
        int lecturerNum = lecturerDao.updateById(record);
        if (lecturerNum < 1) {
            throw new BaseException("讲师信息表更新失败");
        }
        int results = lecturerAuditDao.updateById(BeanUtil.copyProperties(record, LecturerAudit.class));
        if (results < 0) {
            return Result.error(ResultEnum.USER_UPDATE_FAIL);
        }
        return Result.success(results);
    }

}
