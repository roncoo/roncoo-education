<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/user/lecturer/update" data-toggle="validate" method="post">
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
            <label class="control-label x85">讲师用户编号：</label>
            <input type="text" name="lecturerUserNo" value="${bean.lecturerUserNo!}" placeholder="讲师用户编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">讲师名称：</label>
            <input type="text" name="lecturerName" value="${bean.lecturerName!}" placeholder="讲师名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">讲师手机：</label>
            <input type="text" name="lecturerMobile" value="${bean.lecturerMobile!}" placeholder="讲师手机" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">讲师邮箱：</label>
            <input type="text" name="lecturerEmail" value="${bean.lecturerEmail!}" placeholder="讲师邮箱" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">头像：</label>
            <input type="text" name="headImgUrl" value="${bean.headImgUrl!}" placeholder="头像" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">讲师分成比例：</label>
            <input type="text" name="lecturerProportion" value="${bean.lecturerProportion!}" placeholder="讲师分成比例" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>