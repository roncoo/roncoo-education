package com.roncoo.education.user.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 行政区域表
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class RegionListDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<RegionDTO> regionList = new ArrayList<>();
}
