<#include "/macro/base.ftl" />
<!doctype html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes">
<title>龙果</title>
<link type="text/css" rel="stylesheet" href="roncoo/css/login.css">
</head>
<body>
	<div class="login_box">
      <div class="login_l_img"><img src="roncoo/images/login-img.png"></div>
      <div class="login">
          <div class="login_logo"><a href="#"><img src="roncoo/images/login_logo.png"></a></div>
          <div class="login_name">
               <p>后台管理系统</p>
          </div>
          <form method="post" action="${base}/login">
              <input name="username" type="text" placeholder="用户名" >
              <input name="password" type="password" placeholder="密码" >
              <input value="登录" type="submit">
          </form>
      </div>
      <div class="copyright">广州市领课网络科技有限公司 版权所有©2016-2018 | 粤ICP备16009964号</div>
</div>
</body>
</html>