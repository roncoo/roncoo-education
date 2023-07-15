package com.roncoo.education.user.feign.biz;


import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.UsersDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Users;
import com.roncoo.education.user.feign.interfaces.vo.UsersVO;
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
public class FeignUsersBiz extends BaseBiz {

    @NotNull
    private final UsersDao dao;

    public UsersVO getById(Long id) {
        Users record = dao.getById(id);
        return BeanUtil.copyProperties(record, UsersVO.class);
    }

    public Map<Long, UsersVO> listByIds(List<Long> userIdList) {
        List<Users> usersList = dao.listByIds(userIdList);
        if (CollUtil.isNotEmpty(usersList)) {
            return usersList.stream().collect(Collectors.toMap(item -> item.getId(), item -> BeanUtil.copyProperties(item, UsersVO.class)));
        }
        return new HashMap<>();
    }
}
