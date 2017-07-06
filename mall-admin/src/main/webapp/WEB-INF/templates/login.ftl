<!DOCTYPE html>
<html>
<head>
<base href="${base}/"/>
<meta charset="UTF-8">
<title>管理中心登录</title>
<link rel="stylesheet" type="text/css" href="resources/css/login.css" />
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="resources/js/login.js"></script>
</head>
<body>
	<div class="main">
		<div class="theme">
	    	<div class="title">特科芯商城后台管理系统</div>
	       <form id="loginForm" action="login.do" method="post" target="loginFrame">
		        <div class="login">
		            <div class="item">
		            	<div class="row">
		                    <div class="text">用户名：</div>
		                    <div class="box"><input type="text" id="username" name="username" class="username"/></div>
		                    <div class="clear"></div>
		                </div>
		                <div class="row" style="margin:15px auto;">
		                    <div class="text">密&nbsp;&nbsp;&nbsp;码：</div>
		                    <div class="box"><input type="password" id="password" name="password" class="password"/></div>
		                    <div class="clear"></div>
		                </div>
		                <div class="row">
		                    <div class="text">验证码：</div>
		                    <div class="box">
		                    	<input type="text" id="validateCode" name="validateCode" class="valicode" maxlength="4" style="width:100px;"/>
		                    </div>
		                    <div class="code">
								<img src="validateCode/code.do" id="validateCodeImg" class="validateCodeImg"/>
		                    </div>
		                    <div class="clear"></div>
		                </div>
		                <div class="row">
		                	<input type="submit" value="登录" class="btn" />
		                	<iframe id="loginFrame" name="loginFrame" ></iframe>
		                </div>
		                <div id="errMsg" class="info"></div>
		            </div>
		        </div>
	       </form>
	    </div>
  	</div>
</body>
</html>