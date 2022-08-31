package com.roncoo.education.user.feign.interfaces.qo;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户信息
 *
 * @author wujing
 * @date 2022-08-31
 */
@Data
@Accessors(chain = true)
public class UsersSaveQO implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * 状态(1:正常，0:禁用)
     */
    private Integer statusId;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 密码盐
     */
    private String mobileSalt;

    /**
     * 登录密码
     */
    private String mobilePsw;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户性别(1男，2女，3保密)
     */
    private Integer userSex;

    /**
     * 用户年龄
     */
    private Integer userAge;

    /**
     * 用户头像
     */
    private String userHead;

    /**
     * 备注
     */
    private String remark;

}
