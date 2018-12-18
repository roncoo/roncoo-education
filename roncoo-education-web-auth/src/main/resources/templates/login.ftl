<#assign base=request.contextPath /> 
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta content="blank" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <title>领课网络运营后台</title>
  <link href="${base}/css/login.css" rel="stylesheet"/>
</head>
<body>
  <div class="login_all">
    <div class="login_con">
      <div class="login_con_r">
        <h6><i></i>用户登录</h6>
        <form action="${base}/login?clientId=${bean.clientId}&responseType=${bean.responseType}&redirectUri=${bean.redirectUri}" method="post">
          <ul class="login_from">
            <li><i></i><input type="text" name="mobile" placeholder="请输入手机号码" /></li>
            <li class="info">${msg!''}</li>
            <#if list??>
            <li>
              <i></i>
              <select name="orgNo">
                <option value="">请选择平台</option>
                <#list list.orgInfoListVO as vo>
                <option value="${vo.orgNo}">${vo.orgName}</option>
                </#list>
              </select>
            </li>
            <li class="info">请选择要登录的平台</li>
            </#if>
            <li><i></i><input type="password" name="password" placeholder="请输入密码" /></li>
            <li class="info"></li>
            <!--
            <li>
              <i></i>
              <input type="text" class="codename" name="code" placeholder="请输入验证码" />
              <img id="randomCode" class="code" src="${base}/code/888" title="领课网络" alt="随机验证码"/>
            </li>
            <li class="info">${msg!''}</li>
            -->
            <li class="login_btn"><input type="submit" value="登录" /></li>
          </ul>
        </form>
      </div>
    </div>
    <div class="copyright">Copyright © 2015-现在 广州市领课网络科技有限公司版权所有</div>
  </div>
  
  <script type="text/javascript" src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
  <script src="${base}/js/login.js"></script>
</body>
</html>