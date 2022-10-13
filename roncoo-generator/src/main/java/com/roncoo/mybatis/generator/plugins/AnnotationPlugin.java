package com.roncoo.mybatis.generator.plugins;

import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 注解插件
 */
public class AnnotationPlugin extends PluginAdapter {
    private String annotationClassName;
    private String annotationName;

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        annotationClassName = String.valueOf(properties.getProperty("annotationClass"));
        annotationName = String.valueOf(properties.getProperty("annotationName"));
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType importedType = new FullyQualifiedJavaType(annotationClassName);
        interfaze.addImportedType(importedType);
        interfaze.addAnnotation(annotationName);
        return true;
    }

}
