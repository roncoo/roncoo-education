package com.roncoo.education.course.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.OrderPayQO;
import com.roncoo.education.course.common.bean.vo.OrderPayVO;
import com.roncoo.education.util.base.Page;

/**
 * 订单支付信息表 
 *
 * @author wujing
 */
public interface BossOrderPay {
	
	@RequestMapping(value = "/boss/course/orderPay/listForPage", method = RequestMethod.POST)
	Page<OrderPayVO> listForPage(@RequestBody OrderPayQO qo);
	
	@RequestMapping(value = "/boss/course/orderPay/save", method = RequestMethod.POST)
	int save(@RequestBody OrderPayQO qo);
	
	@RequestMapping(value = "/boss/course/orderPay/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);
	
	@RequestMapping(value = "/boss/course/orderPay/update", method = RequestMethod.PUT)
	int updateById(@RequestBody OrderPayQO qo);
	
	@RequestMapping(value = "/boss/course/orderPay/get/{id}", method = RequestMethod.GET)
	OrderPayVO getById(@PathVariable(value = "id") Long id);
	
}
