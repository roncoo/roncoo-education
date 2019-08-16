<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/system/msg/save" data-toggle="validate" method="post">
        <div class="form-group">
            <label class="control-label x85">标题：</label>
            <input type="text" name="msgTitle" value="" placeholder="短信标题" size="20">
        </div>
        </br>
        <div class="form-group">
            <label class="control-label x85">定时发送：</label>
			<input type="radio" name="isTimeSend" class="dsfs" value=1 data-toggle="icheck" data-label="开启"/>
			<input type="radio" name="isTimeSend" class="dsfs" value=0 data-toggle="icheck" data-label="关闭" checked="true" />
             &nbsp;&nbsp;&nbsp;<span class="fa fa-info-circle red"> 是否开启定时发送</span>
        </div>
        </br>
        <div class="form-group dssj" style="display:none; position:relative; z-index:9999">
            <label class="control-label x85">提醒时间：</label>
            <input type="text" name="sendTimeString" data-toggle="datepicker" data-pattern="yyyy-MM-dd HH:mm:ss" size="20">
        </div>
        </br>
		<div class="form-group">
            <label for="statusId" class="control-label x85">是否置顶：</label>
            <input type="radio" name="isTop" class="actCouponAdd" value=1 data-toggle="icheck" data-label="是"/>
			<input type="radio" name="isTop" class="actCouponAdd" value=0 data-toggle="icheck" data-label="否" checked="true" />
        </div>
        </br>
		<div class="form-group">
        	<label class="control-label x85">内容：</label>
            <div style="margin-left: 90px; width:800px" id="msgAddEditor"></div>
        	<textarea id="msgAddText" style="display:none" name="msgText" style="width:100%; height:200px;"></textarea>
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>

<script type="text/javascript">
	initEdit('#msgAddEditor','${base}/upload');
	$('.dsfs').on('ifChecked', function(event) {
		var isTimeSend = $(this).val();
		if (isTimeSend == 1) {
			$(".dssj").show();
		} else {
			$(".dssj").hide();
		}
	});
</script>