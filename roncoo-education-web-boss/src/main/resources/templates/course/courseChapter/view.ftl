<div class="bjui-pageContent">
	<div class="bjui-doc">
		<h4>所属课程:【${bean.courseName!}】</h4>
		<div class="row">
			<div class="col-md-12">
				<blockquote class="point">
                        ${bean.chapterName!} -【<#if bean.isFree!?string == '1'>免费</#if><#if bean.isFree!?string == '0'>${bean.fabPrice!}元</#if>】
                        <br/>
                        <br/>
                        <#list bean.periodInfoVOList! as period>
                        &nbsp;&nbsp;&nbsp;&nbsp;|--课时：${period.periodName!}
                        -【<#if period.isFree! == 1>免费</#if><#if period.isFree! == 0>${period.fabPrice!}元</#if>】
                        <br/><br/>
                        </#list>
                </blockquote>
			</div>
		</div>
	</div>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li>
			<button type="button" class="btn-close">关闭</button>
		</li>
		<li></li>
	</ul>
</div>