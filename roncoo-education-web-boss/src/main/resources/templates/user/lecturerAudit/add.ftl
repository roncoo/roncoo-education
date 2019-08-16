<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/user/lecturerAudit/save" data-toggle="validate" method="post">
		<div class="form-group">
            <label class="control-label x85">*&nbsp;讲师名称：</label>
            <input type="text" name="lecturerName" value="" placeholder="讲师名称" size="20" data-rule="required">
        </div>
		<div class="form-group">
            <label class="control-label x85">*&nbsp;手机号码：</label>
            <input type="text" name="lecturerMobile" id="lecturerMobile" value="" placeholder="手机号码" size="20"> <input type="button" id="verification" value="验证" onclick="test();"/>
        </div>
		<div class="form-group">
            <label class="control-label x85">讲师邮箱：</label>
            <input type="text" name="lecturerEmail" value="" placeholder="讲师邮箱" size="20" data-rule="required">
        </div>
        <div class="form-group" style="display:none" id="mobilePsw">
            <label class="control-label x85">*&nbsp;密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
            <input type="password" name="mobilePsw" value="" placeholder="密码" size="20">
        </div>
        <div class="form-group" style="display:none" id="confirmPasswd">
            <label class="control-label x85">*&nbsp;确认密码：</label>
            <input type="password" name="confirmPasswd" value="" placeholder="确认密码" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" id="add" disabled="true" class="btn-default">添加</button></li>
    </ul>
</div>

<script>
	function test(){
            $.ajax({
                url:"${base}/user/lecturerAudit/checkUserAndLecturer",
                type:"post",
                data:{lecturerMobile:$("#lecturerMobile").val()},
                dataType:"text",
                success:function(data){
                	var jsonData = JSON.parse(data);
                	if(jsonData.message=="手机号码格式不正确"){
                		alert("手机号码格式不正确");
                	}
                	//用户不存在
                	if(jsonData.checkUserAndLecturer==1){
                		alert("验证通过");
                		$("#mobilePsw").show();
                		$("#confirmPasswd").show();
                		$("#verification").hide();
                		$("#add").attr("disabled",false);
                	}
                	//用户存在,讲师不存在
                	if(jsonData.checkUserAndLecturer==2){
                		alert("验证通过");
                		$("#verification").hide();
                		$("#add").attr("disabled",false);
                	}
                	//用户存在,讲师存在
                	if(jsonData.checkUserAndLecturer==3){
                		alert("讲师已存在");
                	}
                }
            });        
        }
</script>