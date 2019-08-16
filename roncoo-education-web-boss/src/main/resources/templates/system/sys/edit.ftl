<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/system/sys/update" data-toggle="validate" method="post">
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
            <label class="control-label x85">状态(1有效, 0无效)：</label>
            <input type="text" name="statusId" value="${bean.statusId!}" placeholder="状态(1有效, 0无效)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">视频存储平台（1保利威视，2七牛）：</label>
            <input type="text" name="videoType" value="${bean.videoType!}" placeholder="视频存储平台（1保利威视，2七牛）" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">useid：</label>
            <input type="text" name="polyvUseid" value="${bean.polyvUseid!}" placeholder="useid" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">writetoken：</label>
            <input type="text" name="polyvWritetoken" value="${bean.polyvWritetoken!}" placeholder="writetoken" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">readtoken：</label>
            <input type="text" name="polyvReadtoken" value="${bean.polyvReadtoken!}" placeholder="readtoken" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">secretkey：</label>
            <input type="text" name="polyvSecretkey" value="${bean.polyvSecretkey!}" placeholder="secretkey" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">文件存储类型（1阿里云，2七牛）：</label>
            <input type="text" name="fileType" value="${bean.fileType!}" placeholder="文件存储类型（1阿里云，2七牛）" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">access_key_id：</label>
            <input type="text" name="aliyunAccessKeyId" value="${bean.aliyunAccessKeyId!}" placeholder="access_key_id" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">access_key_secret：</label>
            <input type="text" name="aliyunAccessKeySecret" value="${bean.aliyunAccessKeySecret!}" placeholder="access_key_secret" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">支付通道（1龙果支付，2其他）：</label>
            <input type="text" name="payType" value="${bean.payType!}" placeholder="支付通道（1龙果支付，2其他）" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">roncoo_key：</label>
            <input type="text" name="roncooKey" value="${bean.roncooKey!}" placeholder="roncoo_key" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">roncoo_secret：</label>
            <input type="text" name="roncooSecret" value="${bean.roncooSecret!}" placeholder="roncoo_secret" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>