<div class="bjui-pageContent">
	<div class="bjui-doc">
		<h4>课程详情</h4>
		<div class="row">
			<div class="col-md-6">
				<blockquote class="point">
					<p>
						<img src="${bean.courseLogo}" width="380" />
					</p>
				</blockquote>
			</div>
			<div class="col-md-6">
				<blockquote class="point">
					<p>
						<label>标题：</label>${bean.courseName}
					</p>
					<p>
						<label>原价：</label>${bean.courseOriginal!'0'}元 &nbsp; <label>优惠价：</label>${bean.courseDiscount!'0'}元
					</p>
					<p>
						<label>购买人数：</label>${bean.countBuy}人&nbsp;<label>学习人数：</label>${bean.countStudy}人
					</p>
					<p>
						<label>讲师名称：</label><#list lecturerList as lecturer><#if bean.lecturerUserNo?? && bean.lecturerUserNo == lecturer.lecturerUserNo>${lecturer.lecturerName}</#if></#list>
						&nbsp;<label>所属分类：</label>${bean.categoryName1!}--${bean.categoryName2!}--${bean.categoryName3!}
					</p>
					<p>
						<label>课程状态：</label> <#list statusIdEnums as em><#if bean.statusId?? && bean.statusId==em.code><span class="${em.color}">${em.desc}</span></#if></#list>
						&nbsp;<label>是否收费：</label> <#list isFreeEnums as em><#if bean.isFree?? && bean.isFree == em.code> <span class="${em.color}">${em.desc}</span></#if></#list>
					</p>
					<p>
						<label>上下架：</label> <#list isPutawayEnums as em><#if bean.isPutaway?? && bean.isPutaway==em.code><span class="${em.color}">${em.desc}</span></#if></#list>
						&nbsp;<label>总课时数：</label>${bean.periodTotal!}
					</p>
				</blockquote>
			</div>
		</div>
		
		<h4>课程章节--课时</h4>
		<div class="row">
			<div class="col-md-12">
                <blockquote class="point">
                    <#list bean.courseChapterVOList! as chapter>
                                                                    【${chapter_index+1}】 ${chapter.chapterName!}
                        <br/>
                        <br/>
                        <#list chapter.courseChapterPeriodVOList! as period>
                        &nbsp;&nbsp;&nbsp;&nbsp;|--课时：${period.periodName!}
                        <br/>
                        <br/>
                        </#list>
                    </#list>
                </blockquote>
            </div>
		</div>
	</div>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li>
			<button type="button" class="btn-close">关闭</button>
		</li>
		<li></li>
	</ul>
</div>