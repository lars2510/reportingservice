<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>" type="text/css" />
	<script src="<c:url value='/resources/js/jquery.js'/>"></script>
	<script type="text/javascript">
		$(function() {
			$.getJSON('api/user/data/peter', {lastName: 'meyer'}, function(data, status) {
				$("#user").html("Aktueller Nutzer: " + data.firstName + " " + data.lastName);	
			});
		});
	</script>
</head>
<body>
	<div id="user"></div>
</body>
</html>
