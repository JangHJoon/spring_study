<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list.jsp</title>
</head>
<body>
	
	<a href="dummy">dummy</a>
	
<br>

<a href="goInsert"> ���� </a>


<table>

	<tr>
	
		<th>id</th>
		<th>name</th>
		<th>age</th>
		<th>subject</th>
	
	</tr>
	
<c:choose>
	<c:when test="${empty list || list == null }">
		<tr>
			<td colspan="4">no body </td>
		</tr>
	</c:when>
	<c:otherwise>	
		<c:forEach items="${list}" var="t">
			<tr>
				<td>${t.id }</td>
				<td>${t.name }</td>
				<td>${t.age }</td>
				<td>${t.subject }</td>			
			</tr>		
		</c:forEach>
	</c:otherwise>
</c:choose>
		
		


</table>

</body>
</html>