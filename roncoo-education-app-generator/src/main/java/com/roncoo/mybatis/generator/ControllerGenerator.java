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

    private static Logger log = Logger.getLogger(ControllerGenerator.class);

    /**
     * 执行创建
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        DaoGenerator.main(args);
        ControllerGenerator.runCreate();
    }

    /**
     * 自定义文件生成
     *
     * @return
     */
    private static List<FileOutConfig> focList() {
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.addAll(feignList()); // feign
        focList.addAll(serviceFeignList()); // service.feign
        focList.addAll(serviceApiList()); // service.api
        focList.addAll(serviceDaoList()); // service.dao
        focList.addAll(servicePcList());// service.pc
        focList.addAll(commonList()); // service.common(bo、dto)
        focList.addAll(commonPcList()); // service.common(req、resp)
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
        gc.setFileOverride(true); // 覆盖文件
        gc.setSwagger2(true);// swagger2
        gc.setAuthor(ConfigUtil.author);// 作者
        gc.setOutputDir(ConfigUtil.projectPath);// 输出目录
        mpg.setGlobalConfig(gc);

        // 数据库配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);//
        dsc.setDriverName(ConfigUtil.dbClass);
        dsc.setUrl(ConfigUtil.url);
        dsc.setUsername(ConfigUtil.username);
        dsc.setPassword(ConfigUtil.password);
        dsc.setTypeConvert(typeConvert()); // 自定义数据库表字段转换器
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig sc = new StrategyConfig();
        // 表名生成策略
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        sc.setEntityLombokModel(true);
        sc.setRestControllerStyle(true);
        sc.setEntityTableFieldAnnotationEnable(true);

        if (ConfigUtil.tableNames != null && ConfigUtil.tableNames.trim() != "" && !"%".equals(ConfigUtil.tableNames)) {
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
        MySqlTypeConvert convert = new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                if (fieldType.contains("tinyint")) {
                    if ("tinyint(1)".equalsIgnoreCase(fieldType)) {
                        return DbColumnType.BOOLEAN;
                    } else {
                        return DbColumnType.INTEGER;
                    }
                } else if (fieldType.contains("datetime")) {
                    return DbColumnType.DATE;
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
     * common(req、resp) 模块
     *
     * @return
     */
    private static List<FileOutConfig> commonPcList() {
        final String path = ConfigUtil.projectPath + "/" + ConfigUtil.projectName;

        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/template/common/req/req.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/common/req/" + tableInfo.getEntityName() + "REQ.java";
            }
        });
        focList.add(new FileOutConfig("/template/common/req/reqEdit.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/common/req/" + tableInfo.getEntityName() + "EditREQ.java";
            }
        });
        focList.add(new FileOutConfig("/template/common/req/reqList.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/common/req/" + tableInfo.getEntityName() + "ListREQ.java";
            }
        });
        focList.add(new FileOutConfig("/template/common/req/reqSave.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/common/req/" + tableInfo.getEntityName() + "SaveREQ.java";
            }
        });
        focList.add(new FileOutConfig("/template/common/resp/resp.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/common/resp/" + tableInfo.getEntityName() + "RESP.java";
            }
        });
        focList.add(new FileOutConfig("/template/common/resp/respList.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/common/resp/" + tableInfo.getEntityName() + "ListRESP.java";
            }
        });
        focList.add(new FileOutConfig("/template/common/resp/respView.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/common/resp/" + tableInfo.getEntityName() + "ViewRESP.java";
            }
        });
        return focList;
    }

    /**
     * service pc模块
     *
     * @return
     */
    private static List<FileOutConfig> servicePcList() {
        final String path = ConfigUtil.projectPath + "/" + ConfigUtil.projectName;

        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // api
        focList.add(new FileOutConfig("/template/service/pc/pc.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/" + "/pc/Pc" + tableInfo.getEntityName() + "Controller.java";
            }
        });
        // biz
        focList.add(new FileOutConfig("/template/service/pc/biz/pc.biz.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/" + "/pc/biz/Pc" + tableInfo.getEntityName() + "Biz.java";
            }
        });
        return focList;
    }

    /**
     * web-boss 模块
     *
     * @return
     */
    private static List<FileOutConfig> bossList() {
        final String path = ConfigUtil.projectPath + "/" + ConfigUtil.bossPath + "/" + ConfigUtil.bossPath + "-service";

        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/template/boss/controller.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/web/boss/controller/" + ConfigUtil.packageName + "/" + tableInfo.getEntityName() + "Controller.java";
            }
        });
        focList.add(new FileOutConfig("/template/boss/biz.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/web/boss/biz/" + ConfigUtil.packageName + "/" + tableInfo.getEntityName() + "Biz.java";
            }
        });
        focList.add(new FileOutConfig("/template/boss/ftl/add.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/resources/templates/" + ConfigUtil.packageName + "/" + tableInfo.getEntityPath() + "/" + "add.ftl";
            }
        });

        focList.add(new FileOutConfig("/template/boss/ftl/edit.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/resources/templates/" + ConfigUtil.packageName + "/" + tableInfo.getEntityPath() + "/" + "edit.ftl";
            }
        });

        focList.add(new FileOutConfig("/template/boss/ftl/list.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/resources/templates/" + ConfigUtil.packageName + "/" + tableInfo.getEntityPath() + "/" + "list.ftl";
            }
        });

        focList.add(new FileOutConfig("/template/boss/ftl/view.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/resources/templates/" + ConfigUtil.packageName + "/" + tableInfo.getEntityPath() + "/" + "view.ftl";
            }
        });

        return focList;

    }

    /**
     * feign 工程
     *
     * @return
     */
    private static List<FileOutConfig> feignList() {
        String projectPath = ConfigUtil.projectPath.replace("service", "feign");
        String projectName = ConfigUtil.projectName.replace("service", "feign");
        String packagePath = ConfigUtil.packagePath.replace("service", "feign");
        String packageName = ConfigUtil.packageName.replace("service", "feign");
        final String path = projectPath + "/" + projectName;

        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/template/feign/qo.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + packagePath + "/" + packageName + "/feign/qo/" + tableInfo.getEntityName() + "QO.java";
            }
        });
        focList.add(new FileOutConfig("/template/feign/vo.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + packagePath + "/" + packageName + "/feign/vo/" + tableInfo.getEntityName() + "VO.java";
            }
        });
        focList.add(new FileOutConfig("/template/feign/interfaces.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + packagePath + "/" + packageName + "/feign/interfaces/IFeign" + tableInfo.getEntityName() + ".java";
            }
        });

        return focList;
    }

    /**
     * common(bo、dto) 模块
     *
     * @return
     */
    private static List<FileOutConfig> commonList() {
        final String path = ConfigUtil.projectPath + "/" + ConfigUtil.projectName;

        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/template/common/bo/bo.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/common/bo/" + tableInfo.getEntityName() + "BO.java";
            }
        });
        focList.add(new FileOutConfig("/template/common/dto/dto.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/common/dto/" + tableInfo.getEntityName() + "DTO.java";
            }
        });

        return focList;
    }

    /**
     * service api模块
     *
     * @return
     */
    private static List<FileOutConfig> serviceApiList() {
        final String path = ConfigUtil.projectPath + "/" + ConfigUtil.projectName;

        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // api
        focList.add(new FileOutConfig("/template/service/api/api.java.ftl") {
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
        // authapi
        focList.add(new FileOutConfig("/template/service/api/auth/authApi.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/" + "/api/auth/Auth" + tableInfo.getEntityName() + "Controller.java";
            }
        });

        // ahthbiz
        focList.add(new FileOutConfig("/template/service/api/auth/biz/authApi.biz.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/" + "/api/auth/biz/Auth" + tableInfo.getEntityName() + "Biz.java";
            }
        });
        return focList;
    }

    /**
     * service - dao 模块
     *
     * @return
     */
    private static List<FileOutConfig> serviceDaoList() {
        final String path = ConfigUtil.projectPath + "/" + ConfigUtil.projectName;

        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();

        focList.add(new FileOutConfig("/template/service/dao/impl/impl.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/" + "/dao/impl/" + tableInfo.getEntityName() + "DaoImpl.java";
            }
        });
        focList.add(new FileOutConfig("/template/service/dao/dao.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/" + "/dao/" + tableInfo.getEntityName() + "Dao.java";
            }
        });
        return focList;
    }

    /**
     * service feign模块
     *
     * @return
     */
    private static List<FileOutConfig> serviceFeignList() {
        final String path = ConfigUtil.projectPath + "/" + ConfigUtil.projectName;

        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/template/service/feign/feign.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/feign/Feign" + tableInfo.getEntityName() + "Controller.java";
            }
        });
        focList.add(new FileOutConfig("/template/service/feign/biz/biz.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return path + "/src/main/java/" + ConfigUtil.packagePath + "/" + ConfigUtil.packageName + "/service/feign/biz/Feign" + tableInfo.getEntityName() + "Biz.java";
            }
        });
        return focList;
    }
}
