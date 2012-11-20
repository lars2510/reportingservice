<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
<c:forEach var="person" items="${p}">
     ${person.firstName}
     ${person.gender}
     <br />
</c:forEach>
	 
</h1>


</body>
</html>
