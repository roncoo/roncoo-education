package com.roncoo.education.system.service.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 头部导航
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class NavBarListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 导航集合
     */
    @ApiModelProperty(value = "导航集合")
    private List<NavBarDTO> list = new ArrayList<>();
}
