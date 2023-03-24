package com.roncoo.education.system.feign.interfaces.vo;

import com.roncoo.education.common.pay.util.AliPayConfig;
import com.roncoo.education.common.pay.util.WxPayConfig;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 支付配置
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
public class PayConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private AliPayConfig aliPayConfig;

    private WxPayConfig wxPayConfig;
}
