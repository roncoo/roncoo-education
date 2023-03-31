package ${cfg.packagePrefix}.${cfg.packageName!}.service.admin.resp;

<#list table.fields as field>
    <#if field.propertyType == "Date" || field.propertyType == "LocalDateTime">
        import com.fasterxml.jackson.annotation.JsonFormat;
        <#break>
    </#if>
</#list>
<#list table.importPackages as pkg>
    <#if !pkg?contains('com.baomidou') && !pkg?contains('activerecord.AbstractModel')>
        import ${pkg};
    </#if>
</#list>
<#if swagger2>
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
    import lombok.Data;
    import lombok.experimental.Accessors;
</#if>

/**
* <p>
    * ADMIN-${table.comment!}
    * </p>
*
* @author ${author}
*/
<#if entityLombokModel>
    @Data
    @Accessors(chain = true)
</#if>
<#if swagger2>
    @ApiModel(description = "ADMIN-${table.comment!}查看")
</#if>
public class Admin${entity}ViewResp implements Serializable {

private static final long serialVersionUID = 1L;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.propertyName !='createUserSysNo' && field.propertyName !='createTime'
    && field.propertyName !='updateUserSysNo' && field.propertyName !='updateTime'
    && field.propertyName !='delUserSysNo' && field.propertyName !='delTime'
    && field.propertyName !='delFlag' && field.propertyName !='version'>
        <#if field.comment!?length gt 0>
            <#if field.propertyType == "Date" || field.propertyType == "LocalDateTime">
                @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            </#if>
            <#if swagger2>
                @ApiModelProperty(value = "${field.comment}")
            <#else>
                /**
                * ${field.comment}
                */
            </#if>
        </#if>
        private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
<#------------  END 字段循环遍历  ---------->
<#if !entityLombokModel>
    <#list table.fields as field>
        <#if field.propertyType == "boolean">
            <#assign getprefix="is"/>
        <#else>
            <#assign getprefix="get"/>
        </#if>
        public ${field.propertyType} ${getprefix}${field.capitalName}() {
        return ${field.propertyName};
        }

        <#if entityBuilderModel>
            public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        <#else>
            public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        </#if>
        this.${field.propertyName} = ${field.propertyName};
        <#if entityBuilderModel>
            return this;
        </#if>
        }
    </#list>
</#if>
<#if entityColumnConstant>
    <#list table.fields as field>
        public static final String ${field.name?upper_case} = "${field.name}";

    </#list>
</#if>
<#if activeRecord>
    @Override
    protected Serializable pkVal() {
    <#if keyPropertyName??>
        return this.${keyPropertyName};
    <#else>
        return null;
    </#if>
    }

</#if>
<#if !entityLombokModel>
    @Override
    public String toString() {
    return "${entity}{" +
    <#list table.fields as field>
        <#if field_index==0>
            "${field.propertyName}=" + ${field.propertyName} +
        <#else>
            ", ${field.propertyName}=" + ${field.propertyName} +
        </#if>
    </#list>
    "}";
    }
</#if>
}
