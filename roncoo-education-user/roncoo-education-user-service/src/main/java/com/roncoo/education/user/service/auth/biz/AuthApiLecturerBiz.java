package com.roncoo.education.user.service.auth.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.user.dao.LecturerDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Lecturer;
import com.roncoo.education.user.service.api.bo.LecturerViewBO;
import com.roncoo.education.user.service.api.dto.LecturerViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 讲师信息
 *
 * @author wujing
 */
@Component
public class AuthApiLecturerBiz {

    @Autowired
    private LecturerDao lecturerDao;

    /**
     * 讲师信息查看接口
     *
     * @param lecturerUserNo
     * @author wuyun
     */
    public Result<LecturerViewDTO> view(LecturerViewBO lecturerViewBO) {
        if (null == lecturerViewBO.getLecturerUserNo()) {
            return Result.error("讲师编号不能为空");
        }
        Lecturer lecturer = lecturerDao.getByLecturerUserNo(lecturerViewBO.getLecturerUserNo());
        if (ObjectUtil.isNull(lecturer)) {
            return Result.error("找不到该讲师");
        }
        return Result.success(BeanUtil.copyProperties(lecturer, LecturerViewDTO.class));
    }
}
