package com.roncoo.education.user.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * ADMIN-用户信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class UsersVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String mobile;

    private String nickname;

    private Integer userSex;

    private LocalDate userAge;

    private String userHead;

}
