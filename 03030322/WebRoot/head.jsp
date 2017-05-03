<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图书管理系统-管理员</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="statics/css/styles.css">
	
  </head>
  
  <body>
    <table width="898" height="120">
    	<tr>
			<td rowspan="2">
				<img alt="" src="statics/image/工大抠好的图.png" width="289" height="120">
			</td> 
			<td colspan="7"> 
				<h1 width="609" height="80">图书管理系统</h1> 
			</td> 	
    	</tr>
    	<tr>
    		<td>
    			<a>图书查询</a>
    		</td>
    		<td>
    			<a>借书查询</a>
    		</td>
    		<td>
    			<a>借书</a>
    		</td>
    		<td>
    			<a>还书</a>
    		</td>
    		<td>
    			<a>读者管理</a>
    		</td>
    		<td>
    			<a>图书管理</a>
    		</td>
    		<td>
    			<a>关于</a>
    		</td>
    	</tr>
    </table>
  </body>
</html>
