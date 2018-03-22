<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>成绩显示--长大一条龙</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

  </head>
<style>
td th{width:100px; align:"center";}
tr{ text-align:center;}
</style>
  <body >
    <c:set var="list" value="${ scoreList}"></c:set>
    <table  >
      <tr>
    	<th>&nbsp;&nbsp;序号&nbsp;&nbsp;</th>
    	<th>课程名称</th>
    	<th>&nbsp;&nbsp;成绩&nbsp;&nbsp;</th>
    	<th>成绩标志</th>
    	<th>考试性质</th>
    	<th>补重学期</th>
    	<th>课程性质</th>
    	<th>课程类别</th>
    	<th>&nbsp;&nbsp;学时&nbsp;&nbsp;</th>
    	<th>&nbsp;&nbsp;学分&nbsp;&nbsp;</th>   	
    	<th>开课学期</th>
    	<th>&nbsp;&nbsp;备注&nbsp;&nbsp;</th>
    	<th>课程大类</th>
    	<th>通识选修类别</th>
    	<th>考试时间</th>
     </tr>
	    <c:forEach var="li" items="${scoreList}"><tr><td>${ li.id}</td><td>${ li.kcmc}</td><td>${ li.score}</td><td>${ li.cjbz}</td>
	    <td>${ li.ksxz}</td><td>${ li.bcxq}</td><td>${ li.kcxz}</td><td>${ li.kclb}</td><td>${ li.xs}</td><td>${ li.xf}</td>
	    <td>${ li.kkxq}</td><td>${ li.bz}</td><td>${ li.kcdl}</td><td>${ li.tsxlb}</td><td>${ li.kssj}</td></tr></c:forEach>
	</table>  
  </body>
</html>
