package com.roncoo.mybatis.generator.internal.type;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class JavaTypeResolverCustomImpl extends JavaTypeResolverDefaultImpl {

    public JavaTypeResolverCustomImpl() {
        super();
        //把数据库的 TINYINT 映射成 Integer
        super.typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
        super.typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP", new FullyQualifiedJavaType(LocalDateTime.class.getName())));
        super.typeMap.put(Types.DATE, new JdbcTypeInformation("DATE", new FullyQualifiedJavaType(LocalDate.class.getName())));
        super.typeMap.put(Types.TIME, new JdbcTypeInformation("TIME", new FullyQualifiedJavaType(LocalTime.class.getName())));
    }
}
