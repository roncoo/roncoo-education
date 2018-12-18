<#include "/macro/base.ftl" />
<div class="bjui-pageHeader" style="background: #FFF;">
	<div style="padding: 0 15px;">
		<h4 style="margin-bottom: 20px;">
			运营后台 <small>领课提供技术支持</small>
		</h4>
	</div>
</div>
<div class="bjui-pageContent">
    <div class="row" style="margin:0;padding:0 15px;border-bottom: 1px #c3ced5 solid;">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading"><h3 class="panel-title"><i class="fa fa-bar-chart-o fa-fw"></i>订单/用户 -【<a href="${base!''}/course/orderInfo/list" data-toggle="navtab" data-id="order-info" data-title="订单列表">订单列表</a>】-【<a href="${base!''}/user/userExt/list" data-toggle="navtab" data-id="oauth2-user" data-title="会员列表">会员列表</a>】</h3></div>
                <div class="panel-body">
                    <div style="mini-width:400px;height:280px" data-toggle="echarts" data-type="bar,line" data-url="${base!''}/orderUser"></div>
                </div>
            </div>
        </div>
    </div>

    <div class="row" style="margin:0;padding:15px;">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading"><h3 class="panel-title">地址汇总</h3></div>
                <div class="panel-body">
                                                         运营平台：<a href="http://cloud.roncoo.com/" target="_blank">http://cloud.roncoo.com/</a>
                    <br/>
                </div>
            </div>
        </div>
    </div>
</div>