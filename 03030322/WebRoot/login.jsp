<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图书管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body bgcolor="#71CABF">
  	<s:form action="login" method="post" theme="simple">
  		<table>
  			<caption>用户登录</caption>
  			<tr>
  				<td>登录名<s:textfield name="login.name" size="20"/></td>
  			</tr>
  			<tr>
  				<td>密&nbsp;码<s:password name="login.password" size="20"/></td>
  			</tr>
  			<tr>
  				<td>
					<s:submit value="登录"/>
					<s:reset value="重置"/>
				</td>
  			</tr>
  			<!-- 验证失败信息或验证错误信息在这里显示 -->
  			<tr>
  				<td>
  					<font color="red"><s:fielderror/><s:property value="message"/></font>
  				</td>
  			</tr>
  		</table>
  	</s:form>
  </body>
</html>
