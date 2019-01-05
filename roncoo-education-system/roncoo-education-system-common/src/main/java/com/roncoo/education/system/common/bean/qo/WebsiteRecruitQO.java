package com.roncoo.education.system.common.bean.qo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 招募中心
 *
 * @author YZJ
 */
@Data
@Accessors(chain = true)
public class WebsiteRecruitQO implements Serializable {

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
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 状态(1有效,0无效)
     */
    private Integer statusId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 招募分类(1讲师招募,2代理招募)
     */
    private Integer recruitType;
    /**
     * 招募标题
     */
    private String recruitTitle;
    /**
     * 招募信息
     */
    private String recruitInfo;
    /**
     * 入驻协议
     */
    private String entryAgreement;
}
