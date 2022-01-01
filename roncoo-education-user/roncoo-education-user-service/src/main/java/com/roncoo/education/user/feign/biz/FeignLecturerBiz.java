package com.roncoo.education.user.feign.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.user.dao.LecturerAuditDao;
import com.roncoo.education.user.dao.LecturerDao;
import com.roncoo.education.user.dao.LecturerExtDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Lecturer;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerAudit;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerExample;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerExt;
import com.roncoo.education.user.feign.interfaces.qo.LecturerQO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerExtVO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 讲师信息
 *
 * @author wujing
 */
@Component
public class FeignLecturerBiz {

    @Autowired
    private LecturerDao dao;
    @Autowired
    private LecturerAuditDao lecturerAuditDao;
    @Autowired
    private LecturerExtDao lecturerExtDao;

    public Page<LecturerVO> listForPage(LecturerQO qo) {
        LecturerExample example = new LecturerExample();
        LecturerExample.Criteria c = example.createCriteria();
        Page<Lecturer> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, LecturerVO.class);
    }

    public int save(LecturerQO qo) {
        Lecturer record = BeanUtil.copyProperties(qo, Lecturer.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public LecturerVO getById(Long id) {
        Lecturer record = dao.getById(id);
        LecturerVO vo = BeanUtil.copyProperties(record, LecturerVO.class);
        // 扩展信息
        LecturerExt lecturerExt = lecturerExtDao.getByLecturerUserNo(vo.getLecturerUserNo());
        vo.setLecturerExtVO(BeanUtil.copyProperties(lecturerExt, LecturerExtVO.class));
        return vo;
    }

    @Transactional
    public int updateById(LecturerQO qo) {
        Lecturer record = BeanUtil.copyProperties(qo, Lecturer.class);
        if (qo.getLecturerProportion() != null) {
            record.setLecturerProportion(qo.getLecturerProportion().divide(BigDecimal.valueOf(100)));
        }
        int lecturerNum = dao.updateById(record);
        if (lecturerNum < 1) {
            throw new BaseException("讲师信息表更新失败");
        }
        return lecturerAuditDao.updateById(BeanUtil.copyProperties(record, LecturerAudit.class));
    }

    /**
     * 根据讲师用户编号查找讲师信息
     *
     * @param lecturerUserNo
     * @return
     */
    public LecturerVO getByLecturerUserNo(Long lecturerUserNo) {
        if (lecturerUserNo == null) {
            throw new BaseException("传入的讲师用户编号不能为空");
        }
        Lecturer record = dao.getByLecturerUserNo(lecturerUserNo);
        if (ObjectUtil.isNull(record)) {
            throw new BaseException("找不到讲师信息");
        }
        LecturerVO vo = BeanUtil.copyProperties(record, LecturerVO.class);
        LecturerExt lecturerExt = lecturerExtDao.getByLecturerUserNo(record.getLecturerUserNo());
        LecturerExtVO lecturerExtVO = BeanUtil.copyProperties(lecturerExt, LecturerExtVO.class);
        vo.setLecturerExtVO(lecturerExtVO);
        return vo;
    }

    /**
     * 列出所有讲师信息
     *
     * @author WY
     */
    public List<LecturerVO> listAllForLecturer() {
        List<Lecturer> lecturerList = dao.listByStatusId(StatusIdEnum.YES.getCode());
        return PageUtil.copyList(lecturerList, LecturerVO.class);
    }
}
