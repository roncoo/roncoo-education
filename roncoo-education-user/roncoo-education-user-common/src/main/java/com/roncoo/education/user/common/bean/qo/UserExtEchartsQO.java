package com.roncoo.education.user.common.bean.qo;


import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserExtEchartsQO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> dateList;
	
}
