/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.es;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 分页
 * z
 *
 * @param <T>
 * @author wujing
 */
public final class EsPageUtil<T extends Serializable> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(EsPageUtil.class);

    private static final long serialVersionUID = 1L;

    private EsPageUtil() {
    }

    public static <T extends Serializable> Page<T> transform(SearchHits<?> searchHits, int pageCurrent, int pageSize, Class<T> classType) {
        Page<T> pb = new Page<>();
        try {
            pb.setList(copyList(searchHits.getSearchHits(), classType));
        } catch (Exception e) {
            logger.error("transform error", e);
        }
        pb.setPageCurrent(pageCurrent);
        pb.setPageSize(pageSize);
        pb.setTotalCount((int) searchHits.getTotalHits());
        pb.setTotalPage(PageUtil.countTotalPage(pb.getTotalCount(), pageSize));
        return pb;
    }

    /**
     * @param source
     * @param clazz
     * @return
     */
    public static <T> List<T> copyList(List<? extends SearchHit<?>> source, Class<T> clazz) {
        if (source == null || source.size() == 0) {
            return Collections.emptyList();
        }
        List<T> res = new ArrayList<>(source.size());
        for (SearchHit sh : source) {
            try {
                T t = clazz.newInstance();
                BeanUtils.copyProperties(sh.getContent(), t);
                Map<String, List<String>> ml = sh.getHighlightFields();
                for (Map.Entry<String, List<String>> entry : ml.entrySet()) {
                    //获取字段
                    Field field = clazz.getDeclaredField(entry.getKey());
                    //修改作用域
                    field.setAccessible(true);
                    //设置值
                    field.set(t, highlightFieldToString(entry.getValue()));
                }
                res.add(t);
            } catch (Exception e) {
                logger.error("copyList error", e);
            }
        }
        return res;
    }

    private static String highlightFieldToString(List<String> field) {
        StringBuilder sb = new StringBuilder();
        for (String text : field) {
            sb.append(text);
        }
        return sb.toString();
    }

}
