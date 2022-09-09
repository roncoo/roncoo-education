package com.roncoo.education.user.feign.biz;


import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.LecturerDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Lecturer;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerExample;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerExample.Criteria;
import com.roncoo.education.user.feign.interfaces.qo.LecturerEditQO;
import com.roncoo.education.user.feign.interfaces.qo.LecturerPageQO;
import com.roncoo.education.user.feign.interfaces.qo.LecturerSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerPageVO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 讲师信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignLecturerBiz extends BaseBiz {

    @NotNull
    private final LecturerDao dao;

    public Page<LecturerPageVO> page(LecturerPageQO qo) {
        LecturerExample example = new LecturerExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<Lecturer> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, LecturerPageVO.class);
    }

    public int save(LecturerSaveQO qo) {
        Lecturer record = BeanUtil.copyProperties(qo, Lecturer.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(LecturerEditQO qo) {
        Lecturer record = BeanUtil.copyProperties(qo, Lecturer.class);
        return dao.updateById(record);
    }

    public LecturerViewVO getById(Long id) {
        Lecturer record = dao.getById(id);
        return BeanUtil.copyProperties(record, LecturerViewVO.class);
    }

    public Map<Long, String> listByIds(List<Long> lecturerIdList) {
        List<Lecturer> lecturerList = dao.listByIds(lecturerIdList);
        if (CollUtil.isNotEmpty(lecturerList)) {
            return lecturerList.stream().collect(Collectors.toMap(Lecturer::getId, Lecturer::getLecturerName));
        }
        return new HashMap<>();
    }
}
