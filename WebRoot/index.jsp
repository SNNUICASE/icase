<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
   
	<form action="<%=request.getContextPath()%>/deptController/login.do"
		method="post">
		<label>uname</label><input type="text" name="uname"> <label>upass</label><input
			type="text" name="upass"> <input type="submit" value="login">

<!-- 		<a href="<%=request.getContextPath()%>/deptController/save.do">save</a> -->
		<a href="<%=request.getContextPath()%>/deptController/delete.do">delete</a>
	</form>
  <hr color="red">
	<form action="<%=request.getContextPath()%>/deptController/save.do"
		method="post">
		<label>dname</label><input type="text" name="dname"> <label>dloc</label><input
			type="text" name="dloc"> <input type="submit" value="save">


	</form>
  </body>
</html>
