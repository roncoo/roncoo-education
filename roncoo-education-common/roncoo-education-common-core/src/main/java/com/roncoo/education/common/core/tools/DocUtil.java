package com.roncoo.education.common.core.tools;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.InputStream;

/**
 * 文档工具
 *
 * @author LYQ
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DocUtil {

    /**
     * 获取文档页数
     *
     * @param fileName 文档名称
     * @param is       文件流
     * @return 文档页数
     */
    public static int getDocPageCount(String fileName, InputStream is) {
        if (is == null) {
            return 0;
        }

        try {
            // txt
            if (fileName.endsWith(".txt")) {
                return 1;
            }
            // excel 97~2003
            if (fileName.endsWith(".xls")) {
                HSSFWorkbook workbook = new HSSFWorkbook(is);
                int sheetNums = workbook.getNumberOfSheets();
                if (sheetNums > 0) {
                    return workbook.getSheetAt(0).getRowBreaks().length + 1;
                }
                return 0;
            }
            // excel 新版
            if (fileName.endsWith(".xlsx")) {
                XSSFWorkbook xwb = new XSSFWorkbook(is);
                int sheetNums = xwb.getNumberOfSheets();
                if (sheetNums > 0) {
                    return xwb.getSheetAt(0).getRowBreaks().length + 1;
                }
                return 0;
            }
            // word 97~2003
            if (fileName.endsWith(".doc")) {
                HWPFDocument wordDoc = new HWPFDocument(is);
                return wordDoc.getSummaryInformation().getPageCount();
            }
            // word 新版
            if (fileName.endsWith(".docx")) {
                XWPFDocument wordDoc = new XWPFDocument(is);
                return wordDoc.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
            }

            // ppt 97~2003
            if (fileName.endsWith(".ppt")) {
                HSLFSlideShow slideShow = new HSLFSlideShow(is);
                return slideShow.getSlides().size();
            }
            // ppt 新版
            if (fileName.endsWith(".pptx")) {
                XMLSlideShow slideShow = new XMLSlideShow(is);
                return slideShow.getSlides().size();
            }

            // pdf
            if (fileName.endsWith(".pdf")) {
                PDDocument doc = PDDocument.load(is);
                return doc.getNumberOfPages();
            }
        } catch (Exception e) {
            log.info("获取文档页数失败！", e);
        }
        return 0;
    }
}
