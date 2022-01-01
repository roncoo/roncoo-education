package com.roncoo.education.user.service.auth.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.aliyun.Aliyun;
import com.roncoo.education.common.core.aliyun.AliyunUtil;
import com.roncoo.education.common.core.base.BaseBiz;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.feign.interfaces.IFeignSys;
import com.roncoo.education.user.dao.UserExtDao;
import com.roncoo.education.user.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.user.service.auth.bo.AuthUserExtBO;
import com.roncoo.education.user.service.auth.bo.AuthUserExtViewBO;
import com.roncoo.education.user.service.auth.dto.AuthUserExtDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 用户教育信息
 *
 * @author wujing
 */
@Component
public class AuthApiUserExtBiz extends BaseBiz {

    @Autowired
    private UserExtDao userExtDao;

    @Autowired
    private IFeignSys bossSys;

    /**
     * 用户信息查看接口
     *
     * @param userNo
     * @author wuyun
     */
    public Result<AuthUserExtDTO> view(AuthUserExtViewBO authUserExtViewBO) {
        if (ObjectUtil.isNull(authUserExtViewBO)) {
            return Result.error("authUserExtUserNoBO不能为空");
        }
        if (null == authUserExtViewBO.getUserNo()) {
            return Result.error("userNo不能为空");
        }
        UserExt userExt = userExtDao.getByUserNo(authUserExtViewBO.getUserNo());
        if (userExt == null) {
            return Result.error("找不到该用户信息");
        }
        return Result.success(BeanUtil.copyProperties(userExt, AuthUserExtDTO.class));
    }

    /**
     * 用户信息更新接口
     *
     * @param authUserExtBO
     * @author wuyun
     */
    public Result<AuthUserExtDTO> update(AuthUserExtBO authUserExtBO) {
        if (authUserExtBO.getId() == null) {
            return Result.error("Id不能为空");
        }
        if (authUserExtBO.getUserNo() == null) {
            return Result.error("userNo不能为空");
        }

        // 根据传入userNo查找到对应的用户信息
        UserExt userExt = userExtDao.getByUserNo(authUserExtBO.getUserNo());
        if (ObjectUtil.isNull(userExt)) {
            return Result.error("没找到对应的用户信息");
        }
        // 如果修改图片删除阿里云oss上的原图片
        if (!StringUtils.isEmpty(authUserExtBO.getHeadImgUrl())) {
            if (!StringUtils.isEmpty(userExt.getHeadImgUrl()) && !authUserExtBO.getHeadImgUrl().equals(userExt.getHeadImgUrl())) {
                AliyunUtil.delete(userExt.getHeadImgUrl(), BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class));
            }
        }
        userExt = BeanUtil.copyProperties(authUserExtBO, UserExt.class);
        int resultNum = userExtDao.updateByUserNo(userExt);
        if (resultNum > 0) {
            // 修改成功返回用户信息
            UserExt education = userExtDao.getByUserNo(authUserExtBO.getUserNo());
            AuthUserExtDTO dto = BeanUtil.copyProperties(education, AuthUserExtDTO.class);
            return Result.success(dto);
        }
        return Result.error(ResultEnum.USER_UPDATE_FAIL.getDesc());

    }

}
