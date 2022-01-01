package com.roncoo.education.system.feign.interfaces.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class SysMenuQO implements Serializable {

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
     * 状态(1:正常，0:禁用)
     */
    private Integer statusId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 父ID
     */
    private Long parentId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单路径
     */
    private String menuUrl;
    /**
     * 接口URL
     */
    @ApiModelProperty(value = "接口URL")
    private String apiUrl;
    /**
     * 菜单图标
     */
    private String menuIcon;
    /**
     * 是否显示(1: 显示；0:不显示)
     */
    @ApiModelProperty(value = "是否显示(1: 显示；0:不显示)")
    private Integer hiddenType;
    /**
     * 备注
     */
    private String remark;
}
