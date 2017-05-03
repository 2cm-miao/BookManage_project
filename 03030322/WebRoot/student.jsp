<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	
	<link rel="stylesheet" type="text/css" href="statics/css/styles.css">
	

  </head>
  
  <body>
    <table bgcolor="#71CABF">
    	<tr>
    		<td colspan="2"><jsp:include page="head1.jsp"></jsp:include>
    	</tr>
    	<tr>
    		<td height="400"></td>
    	</tr>
    	<tr>
    		<td colspan="2" align="center">
    			<font size="2">南京师范大学：南京市宁海路122号   <br>邮编：210097<br>师教教育研究中心版权所有2010-2015</font>
    		</td>
    	</tr>
    </table>
  </body>
</html>
