<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/system/msg/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		</br>
		<div class="form-group">
            <label class="control-label x85">标题：</label>
            <input type="text" name="msgTitle" value="${bean.msgTitle!}" placeholder="短信标题" size="20" <#if bean.isSend?? && bean.isSend == 1> readonly="readonly"</#if>>
        </div>
         <div class="form-group">
            <label for="statusId" class="control-label x85">是否置顶：</label>
            <#list isDefaultEnums as em2>
            <input type="radio" name="isTop" value="${em2.code}" <#if bean.statusId?? && bean.isTop==em2.code>checked</#if> data-toggle="icheck" data-label="${em2.desc}" size="20">
            </#list>
        </div>
        <div class="form-group">
        	<label class="control-label x85">内容：</label>
        	<div style="margin-left: 90px; width:800px" id="msgEditEditor">
        		<p>${bean.msgText!}</p>
        	</div>
        	<textarea id="msgEditText" style="display:none" name="msgText" style="width:100%; height:200px;"></textarea>
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>
<script>initEdit('#msgEditEditor','${base}/upload');</script>
