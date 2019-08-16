<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/system/sys/save" data-toggle="validate" method="post">
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">修改时间：</label>
            <input type="text" name="gmtModified" value="" placeholder="修改时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">状态(1有效, 0无效)：</label>
            <input type="text" name="statusId" value="" placeholder="状态(1有效, 0无效)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">视频存储平台（1保利威视，2七牛）：</label>
            <input type="text" name="videoType" value="" placeholder="视频存储平台（1保利威视，2七牛）" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">useid：</label>
            <input type="text" name="polyvUseid" value="" placeholder="useid" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">writetoken：</label>
            <input type="text" name="polyvWritetoken" value="" placeholder="writetoken" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">readtoken：</label>
            <input type="text" name="polyvReadtoken" value="" placeholder="readtoken" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">secretkey：</label>
            <input type="text" name="polyvSecretkey" value="" placeholder="secretkey" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">文件存储类型（1阿里云，2七牛）：</label>
            <input type="text" name="fileType" value="" placeholder="文件存储类型（1阿里云，2七牛）" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">access_key_id：</label>
            <input type="text" name="aliyunAccessKeyId" value="" placeholder="access_key_id" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">access_key_secret：</label>
            <input type="text" name="aliyunAccessKeySecret" value="" placeholder="access_key_secret" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">支付通道（1龙果支付，2其他）：</label>
            <input type="text" name="payType" value="" placeholder="支付通道（1龙果支付，2其他）" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">roncoo_key：</label>
            <input type="text" name="roncooKey" value="" placeholder="roncoo_key" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">roncoo_secret：</label>
            <input type="text" name="roncooSecret" value="" placeholder="roncoo_secret" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>