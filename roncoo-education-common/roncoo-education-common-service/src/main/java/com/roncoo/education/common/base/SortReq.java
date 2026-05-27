package com.roncoo.education.common.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 排序信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class SortReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer sort;
}
