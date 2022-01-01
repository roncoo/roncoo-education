package com.roncoo.education.system.service.api.biz;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.ArrayListUtil;
import com.roncoo.education.system.dao.NavBarDao;
import com.roncoo.education.system.dao.impl.mapper.entity.NavBar;
import com.roncoo.education.system.service.api.dto.NavBarDTO;
import com.roncoo.education.system.service.api.dto.NavBarListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 头部导航
 *
 * @author wuyun
 */
@Component
public class ApiNavBarBiz {

    @Autowired
    private NavBarDao navBarDao;

    public Result<NavBarListDTO> list() {
        List<NavBar> list = navBarDao.getByStatusId(StatusIdEnum.YES.getCode());
        NavBarListDTO dto = new NavBarListDTO();
        dto.setList(ArrayListUtil.copy(list, NavBarDTO.class));
        return Result.success(dto);
    }

}
