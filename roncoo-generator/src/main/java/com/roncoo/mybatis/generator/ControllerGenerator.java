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
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.roncoo.mybatis.generator.util.ConfigUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成代码主入口
 *
 * @author wujing
 * @version V1.0.0
 */
public class ControllerGenerator {

    private static final Logger log = Logger.getLogger(ControllerGenerator.class);

    /**
     * 执行创建
     *
     * @param args 请求参数
     */
    public static void main(String[] args) {
        ControllerGenerator.runCreate();
    }

    /**
     * 自定义文件生成
     *
     * @return 文件输出配置
     */
    private static List<FileOutConfig> focList() {
        List<FileOutConfig> focList = new ArrayList<>();

        // service.dao
        focList.addAll(serviceDaoList());

        // service.api
        //focList.addAll(serviceApiList());

        // service.auth
        focList.addAll(serviceAuthList());

        // service.admin
        focList.addAll(serviceAdminList());

        // feign
        //focList.addAll(feignList());

        // service.feign
        //focList.addAll(serviceFeignList());
        return focList;
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
        if (ConfigUtil.tableNames != null && !"".equals(ConfigUtil.tableNames.trim()) && !"%".equals(ConfigUtil.tableNames.trim())) {
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
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

    }

    /**
     * 自定义数据库表字段转换器
     */
    private static MySqlTypeConvert typeConvert() {
        return new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                if (fieldType.contains("tinyint")) {
                    if ("tinyint(1)".equalsIgnoreCase(fieldType)) {
                        return DbColumnType.BOOLEAN;
                    } else {
                        return DbColumnType.INTEGER;
                    }
                } /*else if (fieldType.contains("datetime") || fieldType.contains("datetime")) {
                    return DbColumnType.DATE;
                } else if (fieldType.contains("date")) {
                    return DbColumnType.DATE;
                }*/
                return super.processTypeConvert(globalConfig, fieldType);
            }
        };
    }

    /**
     * 自定义模板设置
     */
    private static InjectionConfig cfg() {
        InjectionConfig ic = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
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
     * service - dao 模块
     *
     * @return 文件输出配置
     */
    private static List<FileOutConfig> serviceDaoList() {
        final String path = ConfigUtil.projectPath + "/" + ConfigUtil.projectName;

        List<FileOutConfig> focList = new ArrayList<>();

        focList.add(new FileOutConfig("/template/service/dao/impl/impl.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/dao/impl/" + tableInfo.getEntityName() + "DaoImpl.java";
            }
        });
        focList.add(new FileOutConfig("/template/service/dao/dao.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/dao/" + tableInfo.getEntityName() + "Dao.java";
            }
        });
        return focList;
    }

    /**
     * service api模块
     *
     * @return 文件输出配置
     */
    private static List<FileOutConfig> serviceApiList() {
        final String path = ConfigUtil.projectPath + "/" + ConfigUtil.projectName;

        List<FileOutConfig> focList = new ArrayList<>();
        // api
        focList.add(new FileOutConfig("/template/service/api/api.controller.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/" + "/api/Api" + tableInfo.getEntityName() + "Controller.java";
            }
        });
        // biz
        focList.add(new FileOutConfig("/template/service/api/biz/api.biz.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/" + "/api/biz/Api" + tableInfo.getEntityName() + "Biz.java";
            }
        });
        // req
        focList.add(new FileOutConfig("/template/service/api/api.req.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/" + "/api/req/Api" + tableInfo.getEntityName() + "Req.java";
            }
        });
        // resp
        focList.add(new FileOutConfig("/template/service/api/api.resp.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/" + "/api/resp/Api" + tableInfo.getEntityName() + "Resp.java";
            }
        });
        return focList;
    }

    /**
     * service api模块
     *
     * @return 文件输出配置
     */
    private static List<FileOutConfig> serviceAuthList() {
        final String path = ConfigUtil.projectPath + "/" + ConfigUtil.projectName;

        List<FileOutConfig> focList = new ArrayList<>();
        // api
        focList.add(new FileOutConfig("/template/service/auth/auth.controller.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/" + "/auth/Auth" + tableInfo.getEntityName() + "Controller.java";
            }
        });
        // biz
        focList.add(new FileOutConfig("/template/service/auth/biz/auth.biz.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/" + "/auth/biz/Auth" + tableInfo.getEntityName() + "Biz.java";
            }
        });
        // req
        focList.add(new FileOutConfig("/template/service/auth/auth.req.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/" + "/auth/req/Auth" + tableInfo.getEntityName() + "Req.java";
            }
        });
        // resp
        focList.add(new FileOutConfig("/template/service/auth/auth.resp.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/" + "/auth/resp/Auth" + tableInfo.getEntityName() + "Resp.java";
            }
        });
        return focList;
    }

    /**
     * service Admin模块
     *
     * @return 文件输出配置
     */
    private static List<FileOutConfig> serviceAdminList() {
        final String path = ConfigUtil.projectPath + "/" + ConfigUtil.projectName;

        List<FileOutConfig> focList = new ArrayList<>();
        // api
        focList.add(new FileOutConfig("/template/service/admin/admin.controller.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/admin/Admin" + tableInfo.getEntityName() + "Controller.java";
            }
        });
        // biz
        focList.add(new FileOutConfig("/template/service/admin/biz/admin.biz.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/admin/biz/Admin" + tableInfo.getEntityName() + "Biz.java";
            }
        });

        // req
        focList.add(new FileOutConfig("/template/service/admin/admin.req.save.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/admin/req/Admin" + tableInfo.getEntityName() + "SaveReq.java";
            }
        });
        focList.add(new FileOutConfig("/template/service/admin/admin.req.page.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/admin/req/Admin" + tableInfo.getEntityName() + "PageReq.java";
            }
        });
        focList.add(new FileOutConfig("/template/service/admin/admin.req.edit.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/admin/req/Admin" + tableInfo.getEntityName() + "EditReq.java";
            }
        });

        // resp
        focList.add(new FileOutConfig("/template/service/admin/admin.resp.view.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/admin/resp/Admin" + tableInfo.getEntityName() + "ViewResp.java";
            }
        });
        focList.add(new FileOutConfig("/template/service/admin/admin.resp.page.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/admin/resp/Admin" + tableInfo.getEntityName() + "PageResp.java";
            }
        });
        return focList;
    }

    /**
     * feign 工程
     *
     * @return 文件输出配置
     */
    private static List<FileOutConfig> feignList() {
        String projectPath = ConfigUtil.projectPath.replace("service", "feign");
        String projectName = ConfigUtil.projectName.replace("service", "feign");
        String packagePath = ConfigUtil.packagePath.replace("service", "feign");
        String packageName = ConfigUtil.packageName.replace("service", "feign");
        final String path = projectPath + "/" + projectName;

        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/template/feign/interfaces.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + packagePath + "/" + packageName + "/feign/interfaces/IFeign" + tableInfo.getEntityName() + ".java";
            }
        });

        // qo
        focList.add(new FileOutConfig("/template/feign/qo.page.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + packagePath + "/" + packageName + "/feign/interfaces/qo/" + tableInfo.getEntityName() + "PageQO.java";
            }
        });
        focList.add(new FileOutConfig("/template/feign/qo.save.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + packagePath + "/" + packageName + "/feign/interfaces/qo/" + tableInfo.getEntityName() + "SaveQO.java";
            }
        });
        focList.add(new FileOutConfig("/template/feign/qo.edit.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + packagePath + "/" + packageName + "/feign/interfaces/qo/" + tableInfo.getEntityName() + "EditQO.java";
            }
        });

        // vo
        focList.add(new FileOutConfig("/template/feign/vo.page.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + packagePath + "/" + packageName + "/feign/interfaces/vo/" + tableInfo.getEntityName() + "PageVO.java";
            }
        });
        focList.add(new FileOutConfig("/template/feign/vo.view.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + packagePath + "/" + packageName + "/feign/interfaces/vo/" + tableInfo.getEntityName() + "ViewVO.java";
            }
        });
        return focList;
    }

    /**
     * service feign模块
     *
     * @return 文件输出配置
     */
    private static List<FileOutConfig> serviceFeignList() {
        final String path = ConfigUtil.projectPath + "/" + ConfigUtil.projectName;

        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/template/service/feign/feign.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/feign/Feign" + tableInfo.getEntityName() + "Controller.java";
            }
        });
        focList.add(new FileOutConfig("/template/service/feign/biz/biz.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/feign/biz/Feign" + tableInfo.getEntityName() + "Biz.java";
            }
        });
        return focList;
    }
}
