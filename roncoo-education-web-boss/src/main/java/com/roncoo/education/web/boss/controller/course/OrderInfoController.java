package com.roncoo.education.web.boss.controller.course;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.education.course.common.bean.qo.CourseQO;
import com.roncoo.education.course.common.bean.qo.OrderInfoQO;
import com.roncoo.education.user.common.bean.qo.UserExtQO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.web.boss.biz.course.OrderInfoBiz;

/**
 * 订单信息表
 *
 * @author wujing
 */
@Controller
@RequestMapping(value = "/course/orderInfo")
public class OrderInfoController extends BaseController {

	private final static String TARGETID = "course-orderInfo";

	@Autowired
	private OrderInfoBiz biz;

	@RequestMapping(value = "/list")
	public void list(@ModelAttribute OrderInfoQO qo, ModelMap modelMap) {
		modelMap.put("page", biz.listForPage(qo));
		modelMap.put("countMap", biz.countIncome(qo));
		modelMap.put("bean", qo);
	}

	@RequestMapping(value = "/add")
	public void add() {

	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute OrderInfoQO qo) {
		if (biz.save(qo) > 0) {
			return success(TARGETID);
		}
		return error("添加失败");
	}

	@ResponseBody
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam(value = "id") Long id) {
		if (biz.deleteById(id) > 0) {
			return delete(TARGETID);
		}
		return error("删除失败");
	}

	@RequestMapping(value = "/edit")
	public void edit(@ModelAttribute OrderInfoQO qo, ModelMap modelMap) {
		modelMap.put("bean", qo);
	}

	@ResponseBody
	@RequestMapping(value = "/update")
	public String update(@ModelAttribute OrderInfoQO qo) {
		if (biz.updateById(qo) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}

	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", biz.getById(id));
	}

	/**
	 * 报表导出
	 * 
	 * @author YZJ
	 * @param qo
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public void orderExport(@ModelAttribute OrderInfoQO orderInfoQO, HttpServletResponse response) throws IOException {
		biz.orderExport(orderInfoQO, response);
	}

	/**
	 * 手工录单
	 * 
	 * @author wuyun
	 */
	@ResponseBody
	@RequestMapping(value = "/course/manualOrder")
	public String manualOrder(OrderInfoQO qo) {
		if (biz.manualOrder(qo) > 0) {
			return success(TARGETID);
		}
		return error("录单失败");
	}

	/**
	 * 跳转到课程添加页面
	 */
	@RequestMapping(value = "/course/add")
	public void courseAdd() {

	}

	/**
	 * 课程列表
	 */
	@RequestMapping(value = "/course/courseList")
	public void courseList(CourseQO courseQO, ModelMap modelMap) {
		modelMap.put("bean", courseQO);
		modelMap.put("page", biz.courselistForPage(courseQO));
	}

	/**
	 * 用户列表
	 */
	@RequestMapping(value = "/course/userList")
	public void userList(UserExtQO userExtQO, ModelMap modelMap) {
		modelMap.put("bean", userExtQO);
		modelMap.put("page", biz.userExtlistForPage(userExtQO));
	}

}