<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>成绩查询--长大一条龙</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

  </head>
  
  <body>
     
  	<form name="query" method="post" action="/homework/query">  	
  			 开课时间：
  			 <select name="kksj">
  			   <c:forEach var="t" items="${timeList }">
  			   		<option value="${t} ">${t}</option>
  			   </c:forEach>
  			 </select>
  			 <br/>
  			课程性质：
  			<select name="kcxz">
  			 <OPTION selected value="">---请选择---</OPTION>
  			 <OPTION value=01>通识教育课</OPTION>
  			 <OPTION value=02>学科大类课</OPTION>
  			 <OPTION value=03>专业课</OPTION>
  			 <OPTION value=04>其它</OPTION>
  			 <OPTION value=05>基础课</OPTION>
  			 <OPTION value=06>公共课</OPTION>
  			 <OPTION value=07>公共基础课</OPTION>
  			 <OPTION value=08>专业基础课</OPTION>
  			 <OPTION value=09>集中性实践教学环节</OPTION>
  			 <OPTION value=10>素质教育课</OPTION>
  			</select>
  			 <br/>
  			课程名称：<input name="kcmc"/><br/>
  			显示方式：
  			<select name="xsfs">
  			<OPTION value="">---请选择---</OPTION> 
  			<OPTION selected value=zhcj>显示最好成绩</OPTION> 
  			<OPTION value=qbcj>显示全部成绩</OPTION>
  			</select>
  			 <br/>
  			课程大类：
  			<select name="kcdl">
  			<OPTION selected value="">---请选择---</OPTION>
  			<OPTION value=1>普通课</OPTION>
  			<OPTION value=2>体育课</OPTION>
  			<OPTION value=3>素质教育选修课</OPTION>
  			<OPTION value=9>其它</OPTION> 
  			</select>
  			<br/>
  			考试时间：<input name="kssj"/> <br/>
  		<input  type="submit" name="submit" value="查询"/> <br/>
    </form>
  </body>
</html>
