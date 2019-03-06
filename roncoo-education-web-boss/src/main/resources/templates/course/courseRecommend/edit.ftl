<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/course/courseRecommend/update" data-toggle="validate" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${bean.id}"/>
		
		
		
		</table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>
