package com.roncoo.education.user.feign.biz;


import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.LecturerDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Lecturer;
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
