package com.roncoo.education.system.feign.interfaces.qo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台操作日志表
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class SysLogQO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private int pageCurrent;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 操作人
     */
    private Long userNo;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 用户操作
     */
    private String operation;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求路径
     */
    private String path;
    /**
     * 请求参数
     */
    private String content;
}
