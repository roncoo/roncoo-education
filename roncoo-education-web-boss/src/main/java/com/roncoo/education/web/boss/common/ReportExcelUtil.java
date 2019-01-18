package com.roncoo.education.web.boss.common;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.roncoo.education.course.common.bean.qo.OrderInfoQO;
import com.roncoo.education.course.common.bean.vo.OrderInfoVO;
import com.roncoo.education.course.common.bean.vo.OrderReportVO;
import com.roncoo.education.course.feign.IBossOrderInfo;
import com.roncoo.education.user.common.bean.vo.LecturerProfitVO;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.base.Page;

/**
 * @author WY
 */
public final class ReportExcelUtil {

	private final static Logger logger = LoggerFactory.getLogger(ReportExcelUtil.class);

	public static void exportExcelForLecturerProfit(HttpServletResponse response, Page<LecturerProfitVO> result) throws IOException {
		// 创建一个workbook 对应一个excel文件
		final SXSSFWorkbook workBook = new SXSSFWorkbook();
		SXSSFSheet sheet = workBook.createSheet("讲师分润报表");

		// 列名和列宽
		String[] names = { "讲师名称", "银行卡号", "银行名称", "银行开户名", "讲师分润（元）", "平台分润（元）", "时间" };// 表头
		Integer[] widths = { 25, 15, 15, 25, 25, 25, 25 };// 列宽

		// 创建第一行
		SXSSFRow row = sheet.createRow(0);

		// 设置第一行样式
		CellStyle headStyle = workBook.createCellStyle();
		headStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);// 水平居中
		headStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中

		// 设置第一行字体
		Font headFont = workBook.createFont();
		headFont.setBold(true);
		headStyle.setFont(headFont);

		// 设置第一行单元格内容、单元格样式
		for (int i = 0; i < names.length; i++) {
			SXSSFCell cell = row.createCell(i);
			cell.setCellValue(names[i]);
			cell.setCellStyle(headStyle);
			sheet.setColumnWidth(i, widths[i] * 256);
		}

		// 从第二行开始遍历出分润记录表的数据，再写入单元格
		SXSSFRow row1 = sheet.createRow(1);
		int r = 1;
		for (LecturerProfitVO bean : result.getList()) {
			row1 = sheet.createRow(r++);
			row1.createCell(0).setCellValue(bean.getLecturerVO().getLecturerName());
			row1.createCell(1).setCellValue(bean.getBankCardNo());
			row1.createCell(2).setCellValue(bean.getBankName());
			row1.createCell(3).setCellValue(bean.getBankUserName());
			row1.createCell(4).setCellValue(bean.getLecturerProfit().doubleValue());
			row1.createCell(5).setCellValue(bean.getPlatformProfit().doubleValue());
			row1.createCell(6).setCellValue(new SimpleDateFormat("yyyy/MM/dd").format(bean.getGmtCreate()));
		}
		try {
			workBook.write(response.getOutputStream());
			response.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response.getOutputStream() != null)
				response.getOutputStream().close();
			if (workBook != null)
				workBook.close();
		}
	}

	public static void exportExcelForOrderInfo(HttpServletResponse response, IBossOrderInfo bossOrderInfo, OrderInfoQO orderInfoQO) throws IOException {
		Integer max_count = 50000;// 最大导出记录数
		Integer introwaccess = 100;// 内存中缓存记录行数
		Integer pageSize = 1000;// 每次获取的记录数
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");// 日期格式化
		// 列名和列宽
		String[] names = { "讲师", "课程名称", "销售(套)", "小计(元)", "备注" };// 表头
		Integer[] widths = { 10, 20, 8, 8, 12 };// 列宽
		// 列名和列宽
		String[] rowNames = { "序号", "订单编号", "课程名称", "金额(元)", "销售日期", "备注" };// 表头
		Integer[] rowWidths = { 5, 15, 15, 8, 10, 12 };// 列宽
		Page<OrderInfoVO> page = bossOrderInfo.listForPage(orderInfoQO);
		// 判断导出记录数是否符合条件
		if (page.getTotalCount() <= 0) {
			throw new BaseException("没有对应的订单信息");
		}
		if (max_count.compareTo(page.getTotalCount()) < 0) {
			throw new BaseException("导出记录数大于" + max_count + "条");
		}
		// 进行讲师课程汇总
		List<OrderReportVO> orderSumQOList = bossOrderInfo.listForReport(orderInfoQO);
		// 统计讲师
		Set<Long> lecturerUserNoSet = new HashSet<>();
		for (OrderReportVO o : orderSumQOList) {
			lecturerUserNoSet.add(o.getLecturerUserNo());
		}
		// 统计每个讲师的课程
		Map<Long, List<OrderReportVO>> courseMap = new HashMap<>();
		Map<Long, String> lecturerNameMap = new HashMap<>();
		for (Long l : lecturerUserNoSet) {
			List<OrderReportVO> countCourseList = new ArrayList<>();
			for (OrderReportVO o : orderSumQOList) {
				if (l.equals(o.getLecturerUserNo())) {
					countCourseList.add(o);
				}
			}
			courseMap.put(l, countCourseList);
			lecturerNameMap.put(l, countCourseList.get(0).getLecturerName());
		}
		// 创建一个excel文件
		final SXSSFWorkbook workbook = new SXSSFWorkbook(introwaccess);
		SXSSFSheet sheet = workbook.createSheet("课程总销售明细");
		int rowIndex = 0;// 当前sheet行数
		ServletOutputStream outputStream = null;

		// 创建第一行
		SXSSFRow row = sheet.createRow(rowIndex);
		row.setHeight((short) (25 * 20));// 设置高度
		SXSSFCell cell = row.createCell(0);
		// 设置表头样式
		cell.setCellValue("课程销售汇总");
		CellStyle headStyle = workbook.createCellStyle();
		headStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);// 水平居中
		headStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
		// 设置字体
		Font headFont = workbook.createFont();
		headFont.setBold(true);
		headStyle.setFont(headFont);

		cell.setCellStyle(headStyle);

		// 合并第一行单元格
		int nameLength = names.length;
		CellRangeAddress cra = new CellRangeAddress(0, 0, 0, nameLength - 1);
		sheet.addMergedRegion(cra);

		// 设置列名
		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeight((short) (25 * 15));
		for (int i = 0; i < nameLength; i++) {
			cell = row.createCell(i);
			cell.setCellValue(names[i]);
			cell.setCellStyle(headStyle);
			sheet.setColumnWidth(i, widths[i] * 512);
		}

		CellStyle bodyStyle = workbook.createCellStyle();
		bodyStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);// 水平
		bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
		BigDecimal countMoney = new BigDecimal("0.00");
		// 写入销售汇总信息
		for (Entry<Long, List<OrderReportVO>> l : courseMap.entrySet()) {
			List<OrderReportVO> list = l.getValue();
			boolean isMerge = true;
			for (OrderReportVO o : list) {
				rowIndex++;
				row = sheet.createRow(rowIndex);
				row.setHeight((short) (25 * 15));
				row.setRowStyle(bodyStyle);

				// 合并单元格
				if (isMerge) {
					row.createCell(0).setCellValue(o.getLecturerName());
					if (list.size() > 1) {
						CellRangeAddress nameCra = new CellRangeAddress(rowIndex, (rowIndex + list.size() - 1), 0, 0);
						sheet.addMergedRegion(nameCra);
					}
					isMerge = false;
				}
				row.createCell(1).setCellValue(o.getCourseName());
				row.createCell(2).setCellValue(o.getCourseCount());
				row.createCell(3).setCellValue(o.getCountPaidprice().doubleValue());
				// 每当前行数达到设置的值就刷新数据到硬盘，以清理内存
				if (rowIndex % introwaccess == 0) {
					try {
						sheet.flushRows();
					} catch (IOException e) {
						logger.error("导出数据，刷新数据到硬盘失败");
					}
				}
				countMoney = countMoney.add(o.getCountPaidprice());
			}
		}
		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeight((short) (25 * 15));
		row.createCell(0).setCellValue("合计");
		row.createCell(3).setCellValue(countMoney.doubleValue());

		// 分sheet导出每个讲师的订单信息
		Integer rowNameLength = rowNames.length;
		for (Entry<Long, List<OrderReportVO>> l : courseMap.entrySet()) {
			StringBuilder sheetName = new StringBuilder();
			sheetName.append("（").append(lecturerNameMap.get(l.getKey())).append("）销售明细");
			sheet = workbook.createSheet(sheetName.toString());
			rowIndex = 0;

			// 创建第一行
			row = sheet.createRow(rowIndex);
			row.setHeight((short) (25 * 20));// 设置高度
			cell = row.createCell(0);
			// 设置表头样式
			StringBuilder cellName = new StringBuilder();
			cellName.append("（").append(lecturerNameMap.get(l.getKey())).append("）课程销售明细");
			cell.setCellValue(cellName.toString());
			cell.setCellStyle(headStyle);

			// 合并第一行单元格
			cra = new CellRangeAddress(0, 0, 0, rowNameLength - 1);
			sheet.addMergedRegion(cra);

			// 设置列名
			rowIndex++;
			row = sheet.createRow(rowIndex);
			row.setHeight((short) (25 * 15));
			for (int i = 0; i < rowNameLength; i++) {
				cell = row.createCell(i);
				cell.setCellValue(rowNames[i]);
				cell.setCellStyle(headStyle);
				sheet.setColumnWidth(i, rowWidths[i] * 512);
			}

			orderInfoQO.setLecturerUserNo(l.getKey());
			orderInfoQO.setPageSize(pageSize);
			boolean isGet = true;
			int no = 1;
			countMoney = new BigDecimal("0.00");
			BigDecimal lecturerCountMoney = new BigDecimal("0.00");
			do {
				page = bossOrderInfo.listForPage(orderInfoQO);
				if (page.getPageCurrent() == page.getTotalPage()) {
					isGet = false;
				}
				for (OrderInfoVO info : page.getList()) {
					rowIndex++;
					row = sheet.createRow(rowIndex);
					row.setHeight((short) (25 * 15));
					row.createCell(0).setCellValue(no++);
					row.createCell(1).setCellValue(info.getOrderNo().toString());
					row.createCell(2).setCellValue(info.getCourseName());
					row.createCell(3).setCellValue(info.getPricePaid().doubleValue());
					if (info.getPayTime() != null) {
						row.createCell(4).setCellValue(dateFormat.format(info.getPayTime()));
					}
					// 每当行数达到设置的值就刷新数据到硬盘,以清理内存
					if (rowIndex % introwaccess == 0) {
						try {
							sheet.flushRows();
						} catch (IOException e) {
							logger.error("导出数据,刷新数据到硬盘失败！");
						}
					}
					countMoney = countMoney.add(info.getPricePaid());
					lecturerCountMoney = lecturerCountMoney.add(info.getLecturerProfit());
				}
			} while (isGet);

			rowIndex++;
			row = sheet.createRow(rowIndex);
			row.setHeight((short) (25 * 15));
			row.createCell(0).setCellValue("合计");
			row.createCell(3).setCellValue(countMoney.doubleValue());

			rowIndex++;
			row = sheet.createRow(rowIndex);
			row.setHeight((short) (25 * 15));
			row.createCell(0).setCellValue("讲师收益");
			row.createCell(3).setCellValue(lecturerCountMoney.doubleValue());
			cra = new CellRangeAddress(rowIndex, rowIndex, 0, 1);
			sheet.addMergedRegion(cra);
		}

		// 写入数据
		try {
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
		} catch (IOException e) {
			logger.error("excel导出写入失败！", e);
		} finally {
			if (outputStream != null)
				outputStream.close();
			if (workbook != null)
				workbook.close();
		}
	}
}