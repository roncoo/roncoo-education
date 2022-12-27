package com.roncoo.education.user.dao.impl;

import cn.hutool.core.date.DateUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.common.jdbc.AbstractBaseJdbc;
import com.roncoo.education.user.dao.LogLoginDao;
import com.roncoo.education.user.dao.impl.mapper.LogLoginMapper;
import com.roncoo.education.user.dao.impl.mapper.entity.LogLogin;
import com.roncoo.education.user.dao.impl.mapper.entity.LogLoginExample;
import com.roncoo.education.user.service.admin.resp.AdminStatLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 用户登录日志 服务实现类
 *
 * @author wujing
 * @date 2022-08-25
 */
@Repository
@RequiredArgsConstructor
public class LogLoginDaoImpl extends AbstractBaseJdbc implements LogLoginDao {

    @NotNull
    private final LogLoginMapper mapper;

    @Override
    public int save(LogLogin record) {
        if (record.getId() == null) {
            record.setId(IdWorker.getId());
        }
        return this.mapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(LogLogin record) {
        record.setGmtCreate(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public LogLogin getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<LogLogin> page(int pageCurrent, int pageSize, LogLoginExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<LogLogin> listByExample(LogLoginExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(LogLoginExample example) {
        return this.mapper.countByExample(example);
    }

    @Override
    public List<AdminStatLogin> statByDate(int date) {
        String sql = "select DATE_FORMAT(gmt_create, '%Y-%m-%d') as dates, count(*) as logins, login_status from log_login where login_status>0 and gmt_create>? GROUP BY dates,login_status order by dates asc";
        return this.queryForObjectList(sql, AdminStatLogin.class, DateUtil.offsetDay(new Date(), date));
    }
}
