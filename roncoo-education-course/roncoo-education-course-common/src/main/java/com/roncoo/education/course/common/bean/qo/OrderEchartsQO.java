package com.roncoo.education.course.common.bean.qo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderEchartsQO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> dateList;

}