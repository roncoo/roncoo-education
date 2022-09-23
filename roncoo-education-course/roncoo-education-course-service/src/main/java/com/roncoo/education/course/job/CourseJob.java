package com.roncoo.education.course.job;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.enums.PutawayEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.es.EsCourse;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 同步课程数据到es
 *
 * @author fengyw
 */
@Slf4j
@Component
public class CourseJob {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 每天凌晨5点执行一次
     */
    @Scheduled(cron = "0 0 05 * * ?")
    public void course() {
        CourseExample example = new CourseExample();
        example.createCriteria().andStatusIdEqualTo(StatusIdEnum.YES.getCode()).andIsPutawayEqualTo(PutawayEnum.UP.getCode());
        List<Course> courseList = courseDao.listByExample(example);
        if (CollUtil.isNotEmpty(courseList)) {
            List<IndexQuery> queries = new ArrayList<>();
            for (Course course : courseList) {
                EsCourse esCourse = BeanUtil.copyProperties(course, EsCourse.class);
                IndexQuery query = new IndexQueryBuilder().withObject(esCourse).build();
                queries.add(query);
            }
            elasticsearchRestTemplate.indexOps(EsCourse.class).delete();
            elasticsearchRestTemplate.bulkIndex(queries, IndexCoordinates.of(EsCourse.COURSE));
        }
    }

}
