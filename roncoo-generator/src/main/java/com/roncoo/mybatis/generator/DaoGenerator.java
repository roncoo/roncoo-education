package com.roncoo.mybatis.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * dao层代码
 *
 * @author wujing
 * @version V1.0.0
 */
public class DaoGenerator {

	/**
	 * 执行创建
	 *
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Start mybatis generator ...");
		List<String> warnings = new ArrayList<>();
		boolean overwrite = true;
		File configFile = new File(DaoGenerator.class.getResource("/mybatis-config.xml").getFile());
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		System.out.println("Finish mybatis generator");
	}

}
