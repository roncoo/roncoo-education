<#assign base=request.contextPath />
<div class="bjui-pageContent">
	<form action="${base}/course/courseChapterPeriod/save" data-toggle="validate" method="post">
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">修改时间：</label>
            <input type="text" name="gmtModified" value="" placeholder="修改时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">状态(1:正常，0:禁用)：</label>
            <input type="text" name="statusId" value="" placeholder="状态(1:正常，0:禁用)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课程ID：</label>
            <input type="text" name="courseId" value="" placeholder="课程ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">章节ID：</label>
            <input type="text" name="chapterId" value="" placeholder="章节ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课时名称：</label>
            <input type="text" name="periodName" value="" placeholder="课时名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">课时描述：</label>
            <input type="text" name="periodDesc" value="" placeholder="课时描述" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">是否免费：1免费，0收费：</label>
            <input type="text" name="isFree" value="" placeholder="是否免费：1免费，0收费" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">原价：</label>
            <input type="text" name="periodOriginal" value="" placeholder="原价" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">优惠价：</label>
            <input type="text" name="periodDiscount" value="" placeholder="优惠价" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">购买人数：</label>
            <input type="text" name="countBuy" value="" placeholder="购买人数" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">学习人数：</label>
            <input type="text" name="countStudy" value="" placeholder="学习人数" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">是否存在文档(1存在，0否)：</label>
            <input type="text" name="isDoc" value="" placeholder="是否存在文档(1存在，0否)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">文档名称：</label>
            <input type="text" name="docName" value="" placeholder="文档名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">文档地址：</label>
            <input type="text" name="docUrl" value="" placeholder="文档地址" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">是否存在视频(1存在，0否)：</label>
            <input type="text" name="isVideo" value="" placeholder="是否存在视频(1存在，0否)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">视频编号：</label>
            <input type="text" name="videoNo" value="" placeholder="视频编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">视频名称：</label>
            <input type="text" name="videoName" value="" placeholder="视频名称" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">时长：</label>
            <input type="text" name="videoLength" value="" placeholder="时长" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">视频ID：</label>
            <input type="text" name="videoVid" value="" placeholder="视频ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">阿里云oas：</label>
            <input type="text" name="videoOasId" value="" placeholder="阿里云oas" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">添加</button></li>
    </ul>
</div>