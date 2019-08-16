<div class="bjui-pageContent">
	<div class="bjui-doc">
		
		<h4>课程基本信息</h4>
		<div class="row">
			<div class="col-md-6">
				<blockquote class="point">
					<p>
						<img src="${bean.courseLogo!}" height="100" />
					</p>
				</blockquote>
			</div>
			<div class="col-md-6">
				<blockquote class="point">
					<p>
						<label>所属专区：</label><#list zoneList as zone><#if zoneId?? && zoneId == zone.id>${zone.zoneName!}</#if></#list>
					</p>
					<p>
						<label>标题：</label>${bean.courseName!}
					</p>
					<p>
						<label>课程分类：</label><#list courseCategoryEnums as em><#if bean.categoryType?? && bean.categoryType == em.code> <span class="${em.color}">${em.desc}</span></#if></#list>&nbsp;
					</p>
					<p>
						<label>原价：</label>${bean.courseOriginal!'0'}元 &nbsp; <label>优惠价：</label>${bean.courseDiscount!'0'}元
					</p>
					<p>
						<label>购买人数：</label>${bean.countBuy!}人&nbsp;<label>学习人数：</label>${bean.countStudy!}人
					</p>
					<p>
						<label>讲师名称：</label><#list lecturerList as lecturer><#if bean.lecturerUserNo?? && bean.lecturerUserNo == lecturer.lecturerUserNo>${lecturer.lecturerName}</#if></#list>
						&nbsp;
						<label>所属分类：</label> ${bean.categoryName1!}--${bean.categoryName2!}--${bean.categoryName3!}
					</p>
					<p>
						<label>课程状态：</label><#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list>
						&nbsp;
						<label>是否收费：</label> <#list isFreeEnums as em><#if bean.isFree?? && bean.isFree == em.code> <span class="${em.color}">${em.desc}</span></#if></#list>
					</p>
					<P>
						<label>上下架：</label><#list isPutawayEnums as em><#if bean.isPutaway?? && bean.isPutaway==em.code><span class="${em.color}">${em.desc}</span></#if></#list>
					</P>
				</blockquote>
			</div>
		</div>
		
		<h4>章节--课时</h4>
		<div class="row">
			<div class="col-md-12">
				<blockquote class="point">
						
					<#list bean.courseChapterVOList! as chapter>
						${chapter_index+1}、<label>${chapter.chapterName!}-
						(
							<#if chapter.isFree!?string == '1'>免费</#if>
							<#if chapter.isFree!?string == '0'>优惠价:${chapter.chapterDiscount!}元 - 原价:${chapter.chapterOriginal!}元</#if>
						)
						</label><br/>
						<#list chapter.courseChapterVOList! as period>
								&nbsp;&nbsp;&nbsp;&nbsp;|--课时：${period.periodName!}
                                -
                                <#if period.isVideo! == 1><b class="green">有视频</b></#if><#if period.isVideo == 2><b class="red">无视频</b></#if>
                                -
                                <#if period.isDoc == 1>文档:${period.docName!}</#if>
                                -
                                (
                                <#if period.isFree! == 1>免费</#if>
                                <#if period.isFree! == 0>优惠价:${period.fabPrice!}元 - 原价:${period.orgPrice!}元</#if>
                                )
                                <br/><br/>
						</#list>
					</#list>
				</blockquote>
			</div>
		</div>

        <#if bean.courseIntroduce??>
		<h4>课程简介</h4>
		<div class="row">
			<div class="col-md-12">
				<blockquote class="point">
					<textarea data-toggle="kindeditor" data-toggle="autoheight" data-items="preview">${bean.courseIntroduce!}</textarea>
				</blockquote>
			</div>
		</div>
        </#if>
	</div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
    </ul>
</div>