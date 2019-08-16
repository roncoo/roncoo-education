/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.boss.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 添加Java代码中的注释
 */
public class AddComments {

	private static int count = 0;

	public static void main(String[] args) {
		clearComment("D:\\workspace\\roncoo-education"); // 删除目录下所有java文件注释
	}

	/**
	 * @param charset
	 *            文件编码
	 * @param file
	 *            文件
	 */
	public static void addComment(File file, String charset) {
		try {
			// 递归处理文件夹
			if (!file.exists()) {
				return;
			}

			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (File f : files) {
					addComment(f, charset); // 递归调用
				}
				return;
			} else if (!file.getName().endsWith(".java")) {
				// 非java文件直接返回
				return;
			}
			System.out.println("-----开始处理文件：" + file.getAbsolutePath());

			// 根据对应的编码格式读取
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
			StringBuffer content = new StringBuffer();
			String tmp = null;
			while ((tmp = reader.readLine()) != null) {
				content.append(tmp);
				content.append("\n");
			}
			reader.close();
			String target = content.toString();
			String s = target;
			if (target.indexOf("package") != -1) {
				s = 	"/**\n" + 
						" * Copyright 2015-现在 广州市领课网络科技有限公司\n" + 
						" */\n" + 
						target.substring(target.indexOf("package"));
			}
			
			//String s = target.replaceAll("\\/\\/[^\\n]*|\\/\\*([^\\*^\\/]*|[\\*^\\/*]*|[^\\**\\/]*)*\\*+\\/", "");
			System.out.println(s);
			// 使用对应的编码格式输出
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
			out.write(s);
			out.flush();
			out.close();
			//reader.close();
			count++;
			System.out.println("-----文件处理完成---" + count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void clearComment(String filePath, String charset) {
		addComment(new File(filePath), charset);
	}

	public static void clearComment(String filePath) {
		addComment(new File(filePath), "UTF-8");
	}

	public static void clearComment(File file) {
		addComment(file, "UTF-8");
	}

}
