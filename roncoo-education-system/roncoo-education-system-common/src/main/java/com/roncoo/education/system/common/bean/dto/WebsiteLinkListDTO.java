package com.roncoo.education.system.common.bean.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站点友情链接
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class WebsiteLinkListDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<WebsiteLinkDTO> WebsiteLinkList;
	
}
