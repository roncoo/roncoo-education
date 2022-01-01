package com.roncoo.education.user.feign.interfaces.qo;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class UserExtEchartsQO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> dateList;

}
