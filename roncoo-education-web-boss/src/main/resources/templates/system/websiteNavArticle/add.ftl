<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/system/websiteNavArticle/save" data-toggle="validate" method="post" enctype="multipart/form-data">
	<input type="hidden" name="navId" value="${bean.navId}">
		<fieldset>
            <legend>站点信息</legend>
            <div class="form-group">
                <label class="control-label x85">文章图片：</label>
                <input type="file" name="artPicFile" />
            </div>
            <br/>
            <div class="form-group">
                <label class="control-label x85">文章标题：</label>
                <input name="artTitle" size="80" value="" />
            </div>
            <br/>
            <div class="form-group">
                <label class="control-label x85">文章描述：</label>
                <div style="margin-left: 90px; width:800px" id="articleAddEditor" upload-url="${base}/uploadPic"></div>
            	<textarea id="wnaAddArtDesc" name="artDesc" style="display:none" style="width:100%; height:200px;"></textarea>
            </div>
        </fieldset>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>
<script>initEdit('#articleAddEditor','${base}/upload');</script>