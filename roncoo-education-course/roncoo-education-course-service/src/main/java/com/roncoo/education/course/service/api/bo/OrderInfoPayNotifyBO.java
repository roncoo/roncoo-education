/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 订单异步通知
 *
 * @author forest
 */
@Data
@Accessors(chain = true)
public class OrderInfoPayNotifyBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long serialNumber;


    private int orderStatus;

}
