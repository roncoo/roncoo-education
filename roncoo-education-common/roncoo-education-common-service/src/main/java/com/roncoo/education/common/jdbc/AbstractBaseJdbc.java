/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.jdbc;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;


/**
 * spirngjdbc 基本支撑类
 *
 * @author hugovon
 * @version 1.0
 */
public abstract class AbstractBaseJdbc {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    /**
     * 获取当前事务最后一次更新的主键值
     */
    public Long getLastId() {
        return jdbcTemplate.queryForObject("select last_insert_id() as id", Long.class);
    }

    /**
     * 获取对象信息
     */
    public <T> T queryForObject(String sql, Class<T> clazz, Object... args) {
        Assert.hasText(sql, "sql 语句不能为空");
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<T>(clazz), args);
    }

    /**
     * 获取对象集合信息
     */
    public <T> List<T> queryForObjectList(String sql, Class<T> clazz, Object... args) {
        Assert.hasText(sql, "sql 语句不能为空");
        return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<T>(clazz));
    }

    /**
     * 分页
     */
    public <T extends Serializable> Page<T> queryForPage(String sql, int pageCurrent, int pageSize, Class<T> clazz) {
        Assert.hasText(sql, "sql 语句不能为空");
        Assert.isTrue(pageCurrent >= 1, "pageNo 必须大于等于1");
        Assert.isTrue(clazz != null, "clazz 不能为空");
        String sqlCount = PageUtil.countSql(sql);
        int count = jdbcTemplate.queryForObject(sqlCount, Integer.class);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        pageSize = PageUtil.checkPageSize(pageSize);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        String sqlList = sql + PageUtil.limitSql(count, pageCurrent, pageSize);
        List<T> list = jdbcTemplate.query(sqlList, new BeanPropertyRowMapper<T>(clazz));
        return new Page<T>(count, totalPage, pageCurrent, pageSize, list);
    }

}
