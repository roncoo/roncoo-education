<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/system/msgTemplate/save" data-toggle="validate" method="post">
       <div class="form-group">
            <label class="control-label x85">模&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;板：</label>
            <textarea name="remark" cols="25" rows="3" data-toggle="autoheight" class="form-control autosize">尊敬的{{name}}用户，您有一个{{courseName}}课程抵用券即将过期</textarea>
        </div>
        
        <div class="form-group">
            <label class="control-label x85">&nbsp;</label>
            <span class="fa fa-info-circle red">{{name}}=用户名称</span>
        </div>
        <div class="form-group">
            <label class="control-label x85">&nbsp;</label>
            <span class="fa fa-info-circle red">{{courseName}}=课程名称</span>
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>