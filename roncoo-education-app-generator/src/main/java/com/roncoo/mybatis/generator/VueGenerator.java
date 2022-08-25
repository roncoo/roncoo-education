package com.roncoo.mybatis.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.roncoo.mybatis.generator.engine.ViewTemplateEngine;
import com.roncoo.mybatis.generator.util.ConfigUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页面生成代码主入口
 *
 * @author wujing
 * @version V1.0.0
 */
public class VueGenerator {

    private static final Logger log = Logger.getLogger(VueGenerator.class);

    /**
     * 执行创建
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        VueGenerator.runCreate();
    }

    /**
     * 执行创建
     */
    public static void runCreate() {
        log.debug("准备开始执行创建所有文件...");
        log.debug("DbConfig.url = " + ConfigUtil.url);
        log.debug("DbConfig.dbClass = " + ConfigUtil.dbClass);
        log.debug("DbConfig.username = " + ConfigUtil.username);
        log.debug("DbConfig.password = " + ConfigUtil.password);

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 覆盖文件
        gc.setFileOverride(true);
        // swagger2
        gc.setSwagger2(true);
        // 作者
        gc.setAuthor(ConfigUtil.author);
        // 输出目录
        gc.setOutputDir(ConfigUtil.projectPath);
        mpg.setGlobalConfig(gc);

        // 数据库配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName(ConfigUtil.dbClass);
        dsc.setUrl(ConfigUtil.url);
        dsc.setUsername(ConfigUtil.username);
        dsc.setPassword(ConfigUtil.password);
        // 自定义数据库表字段转换器
        dsc.setTypeConvert(typeConvert());
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig sc = new StrategyConfig();
        // 表名生成策略
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        sc.setEntityLombokModel(true);
        sc.setRestControllerStyle(true);
        sc.setEntityTableFieldAnnotationEnable(true);
        if (ConfigUtil.tableNames != null && ConfigUtil.tableNames.trim() != "") {
            sc.setInclude(ConfigUtil.tableNames.split(","));
        }
        mpg.setStrategy(sc);

        TemplateConfig tc = new TemplateConfig();
        // 如果设置 空 OR Null 将不生成该模块， 这里全部设置为空，改为由自定义模板来生成。
        tc.setController(null).setEntity(null).setMapper(null).setXml(null).setService(null).setServiceImpl(null);
        // 自定义模板配置
        mpg.setTemplate(tc);

        // 自定义模板
        mpg.setCfg(cfg());
        mpg.setTemplateEngine(new ViewTemplateEngine());
        mpg.execute();

    }

    /**
     * 自定义数据库表字段转换器
     */
    private static MySqlTypeConvert typeConvert() {
        MySqlTypeConvert convert = new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                if (fieldType.contains("tinyint")) {
                    if ("tinyint(1)".equalsIgnoreCase(fieldType)) {
                        return DbColumnType.BOOLEAN;
                    } else {
                        return DbColumnType.INTEGER;
                    }
                }
                return super.processTypeConvert(globalConfig, fieldType);
            }
        };
        return convert;
    }

    /**
     * 自定义模板设置
     */
    private static InjectionConfig cfg() {
        InjectionConfig ic = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("projectName", ConfigUtil.projectName);
                map.put("packagePrefix", ConfigUtil.packagePrefix);
                map.put("packageName", ConfigUtil.packageName);
                this.setMap(map);
            }
        };
        ic.setFileOutConfigList(focList());
        return ic;
    }

    /**
     * 自定义文件生成
     *
     * @return
     */
    private static List<FileOutConfig> focList() {
        List<FileOutConfig> focList = new ArrayList<>();
        // request_js
        focList.addAll(requestList());
        return focList;
    }

    /**
     * web-boss 模块
     *
     * @return
     */
    private static List<FileOutConfig> requestList() {
        final String path = ConfigUtil.projectPath + "/src";

        List<FileOutConfig> focList = new ArrayList<>();

        // 请求JS
        focList.add(new FileOutConfig("template/view/api/request.js.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/api/" + ConfigUtil.packageName + ".js";
            }
        });

        // 路由JS
        focList.add(new FileOutConfig("template/view/router/router.js.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/router/" + ConfigUtil.packageName + "Router.txt";
            }
        });

        // 生成页面
        focList.add(new FileOutConfig("template/view/page/index.vue.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/views/" + ConfigUtil.packageName + "/" + tableInfo.getEntityPath() + "/index.vue";
            }
        });
        focList.add(new FileOutConfig("template/view/page/add.vue.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/views/" + ConfigUtil.packageName + "/" + tableInfo.getEntityPath() + "/add/index.vue";
            }
        });
        focList.add(new FileOutConfig("template/view/page/edit.vue.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/views/" + ConfigUtil.packageName + "/" + tableInfo.getEntityPath() + "/edit/index.vue";
            }
        });
        focList.add(new FileOutConfig("template/view/page/list.vue.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/views/" + ConfigUtil.packageName + "/" + tableInfo.getEntityPath() + "/list/index.vue";
            }
        });
        return focList;

    }
}
