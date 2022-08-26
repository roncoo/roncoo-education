package ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.qo;

<#list table.importPackages as pkg>
<#if !pkg?contains('com.baomidou') && !pkg?contains('activerecord.AbstractModel')>
import ${pkg};
</#if>
</#list>
<#if entityLombokModel>
import lombok.Data;
import lombok.experimental.Accessors;
</#if>

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
<#if entityLombokModel>
@Data
@Accessors(chain = true)
</#if>
public class ${entity}PageQO implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private int pageCurrent;

    /**
     * 每页记录数
     */
    private int pageSize;

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.propertyName !='createUserSysNo' && field.propertyName !='createTime'
        && field.propertyName !='updateUserSysNo' && field.propertyName !='updateTime'
        && field.propertyName !='delUserSysNo' && field.propertyName !='delTime'
        && field.propertyName !='delFlag' && field.propertyName !='version'>
    /**
     * ${field.comment}
     */
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
}
