package com.roncoo.education.util.tools;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PDFUtil {

	/**
	 * 水印
	 */
	public static void setWatermark(MultipartFile src, File dest, String waterMarkName, int permission) throws DocumentException, IOException {
		PdfReader reader = new PdfReader(src.getInputStream());
		PdfStamper stamper = new PdfStamper(reader, new BufferedOutputStream(new FileOutputStream(dest)));
		int total = reader.getNumberOfPages() + 1;
		PdfContentByte content;
		BaseFont base = BaseFont.createFont();
		for (int i = 1; i < total; i++) {
			content = stamper.getOverContent(i);// 在内容上方加水印
			content.beginText();
			content.setTextMatrix(70, 200);
			content.setFontAndSize(base, 30);
			content.setColorFill(BaseColor.GRAY);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 300, 400, 45);
			content.endText();
		}
		stamper.close();
	}
}