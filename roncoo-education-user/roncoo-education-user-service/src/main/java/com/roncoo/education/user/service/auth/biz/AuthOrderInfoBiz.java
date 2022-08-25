package com.roncoo.education.user.service.auth.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.OrderInfoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-订单信息表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthOrderInfoBiz extends BaseBiz {

    @NotNull
    private final OrderInfoDao dao;

}
