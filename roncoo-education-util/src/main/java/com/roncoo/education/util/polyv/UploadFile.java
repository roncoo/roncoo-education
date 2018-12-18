/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.polyv;

import java.io.Serializable;

public class UploadFile implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title;
	
	private String tag;
	
	private String desc;
	
	private Long cataid;
	
	private String watermark;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Long getCataid() {
		return cataid;
	}

	public void setCataid(Long cataid) {
		this.cataid = cataid;
	}

	public String getWatermark() {
		return watermark;
	}

	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}

}
