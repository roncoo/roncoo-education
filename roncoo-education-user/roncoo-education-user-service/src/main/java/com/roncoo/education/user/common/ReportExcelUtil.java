package com.roncoo.education.user.common;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.feign.interfaces.vo.LecturerProfitVO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

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
        String[] names = {"讲师名称", "银行卡号", "银行名称", "银行开户名", "讲师分润（元）", "平台分润（元）", "时间"};// 表头
        Integer[] widths = {25, 15, 15, 25, 25, 25, 25};// 列宽

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
}
