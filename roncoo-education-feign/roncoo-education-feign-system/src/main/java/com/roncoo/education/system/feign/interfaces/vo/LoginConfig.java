package com.roncoo.education.system.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统配置
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
public class LoginConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公钥
     */
    private String rsaLoginPublicKey;

    /**
     * 私钥
     */
    private String rsaLoginPrivateKey;

}
