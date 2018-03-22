<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>登陆--长大一条龙</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

  </head>
  
  <body>
  	<form name="login" method="post" action="/homework/login">
  	<a>账号：</a><input name="userName" type="text"/><br/>
  	<a>密码：</a><input name="userPassword" type="password"/><br/>
  	<input name="submit" type="submit" value="登陆"/>
  	</form>
  </body>
</html>
