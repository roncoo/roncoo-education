<#include "/macro/base.ftl" />
<div class="bjui-pageContent">
	<form action="${base}/course/orderInfo/course/manualOrder" data-toggle="validate" method="post"  data-alertmsg="false">
		<div class="form-group">
			<label class="control-label x85">课程名称：</label>
			<input type="text" name="courseName" data-rule="required" value="请选择课程名称" size="25" data-toggle="lookup" data-url="${base}/course/orderInfo/course/courseList" data-width="1000" data-height="734">
			<input type="hidden" name="courseId" size="25" minlength="3" maxlength="128" class="required readonly" />
		</div>
		<div class="form-group">
			<label class="control-label x85">学员手机：</label>
			<input type="text" name="mobile" data-rule="required" value="请选择会员账号" size="25" data-toggle="lookup" data-url="${base}/course/orderInfo/course/userList" data-width="1000" data-height="734">
			<input type="hidden" name="userNo" size="25" minlength="3" maxlength="128" class="required readonly" />
			</div>

		<div class="form-group">
			<label class="control-label x85">课程金额：</label>
			<input type="text" name="PricePaid" size="25" value="0" class="required number" />
		</div>
		<div class="form-group">
			<label class="control-label x85">备注内容：</label>
			<textarea name="Remark" class="required" data-toggle="autoheight" cols="25">后台手工绑定</textarea>
		</div>
		<div class="form-group">
            <label class="control-label x85">&nbsp;</label>
            <span class="fa fa-info-circle red"> 手工录单功能，请谨慎使用</span>
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li>
			<button type="button" class="btn-close" data-icon="close">关闭</button>
		</li>
		<li>
			<button type="submit" class="btn-default" data-icon="save">保存</button>
		</li>
	</ul>
</div>

