package com.roncoo.education.common.elasticsearch;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 讲师信息
 *
 * @author fengyw
 */
@Document(indexName = EsLecturer.LECTURER)
@Data
@Accessors(chain = true)
public class EsLecturer implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String LECTURER = "rc_es_lecturer";

    @Id
    private Long id;
    /**
     * 状态
     */
    private Integer statusId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 讲师名称
     */
    private String lecturerName;
    /**
     * 讲师手机
     */
    private String lecturerMobile;
    /**
     * 讲师职位
     */
    private String lecturerPosition;
    /**
     * 讲师头像
     */
    private String lecturerHead;

}
