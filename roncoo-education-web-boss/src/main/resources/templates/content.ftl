<#include "/macro/base.ftl" />
<div class="bjui-pageHeader" style="background: #FFF;">
	<div style="padding: 0 15px;">
		<h4 style="margin-bottom: 20px;">
			<small>开源协议：MIT | 官网：<a href="https://edu.roncoo.net/" target="_blank">https://edu.roncoo.net/</a> </small>
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
                <div class="panel-heading"><h3 class="panel-title">商业版演示环境</h3></div>
                <div class="panel-body">
                    前端演示：<a href="http://roncoo.vicp.net/" target="_blank">http://roncoo.vicp.net/</a>
                    <br/>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading"><h3 class="panel-title">开源版演示环境</h3></div>
                <div class="panel-body">
                    前端演示：<a href="http://edu.os.roncoo.com/" target="_blank">http://edu.os.roncoo.com/</a>
                    <br/>
                </div>
            </div>
        </div>
    </div>
</div>