package com.roncoo.mybatis.generator.util;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 入口配置文件
 *
 * @author wujing
 * @version V1.0.0
 */
public class ConfigUtil {
    /**
     * 系统文件配置 加载。
     */
    public static Map<String, String> CONFIG = new HashMap<String, String>();

    static {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("config");
            Iterator<?> var2 = bundle.keySet().iterator();
            while (var2.hasNext()) {
                String key = (String) var2.next();
                CONFIG.put(key, bundle.getString(key));
            }
        } catch (Exception e) {
            System.out.println("无法加载配置文件 config.properties，请确认文件是否存在！");
        }
    }

    /**
     * 数据库类名 class
     **/
    public final static String dbClass = "com.mysql.cj.jdbc.Driver";
    /**
     * 数据库链接 url
     **/
    public final static String url = CONFIG.get("db.url");
    /**
     * 项目所在目录
     **/
    public final static String username = CONFIG.get("db.username");
    /**
     * 根目录
     **/
    public final static String password = CONFIG.get("db.password");

    /**
     * 创建人
     **/
    public final static String author = CONFIG.get("author") == null ? "admin" : CONFIG.get("author");

    /**
     * 项目配置
     **/
    public final static String projectPath = CONFIG.get("projectPath");
    public final static String projectName = CONFIG.get("projectName");
    public final static String packagePrefix = CONFIG.get("packagePrefix");
    public final static String packageName = CONFIG.get("packageName") == null ? "" : CONFIG.get("packageName");

    public final static String packagePath = packagePrefix.replace(".", "/");
    public final static String bossPath = projectName.replace(packageName, "boss");

    /**
     * 表名
     **/
    public final static String tableNames = CONFIG.get("tableNames");


}
