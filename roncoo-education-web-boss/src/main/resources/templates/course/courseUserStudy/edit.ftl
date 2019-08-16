<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/course/courseUserStudy/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="${bean.gmtCreate!}" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">修改时间：</label>
            <input type="text" name="gmtModified" value="${bean.gmtModified!}" placeholder="修改时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">状态(1:正常，0:禁用)：</label>
            <input type="text" name="statusId" value="${bean.statusId!}" placeholder="状态(1:正常，0:禁用)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课程ID：</label>
            <input type="text" name="courseId" value="${bean.courseId!}" placeholder="课程ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">用户编号：</label>
            <input type="text" name="userNo" value="${bean.userNo!}" placeholder="用户编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">总课时数：</label>
            <input type="text" name="periodTotal" value="${bean.periodTotal!}" placeholder="总课时数" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">已学习课时数：</label>
            <input type="text" name="periodStudy" value="${bean.periodStudy!}" placeholder="已学习课时数" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>