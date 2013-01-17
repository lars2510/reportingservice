<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User erstellen</title>
</head>
<body>
	<c:url var="savePassword" value="/api/user/savePassword" />
	<form:form action="${savePassword}" method="post" modelAttribute="userAuthenticationModel" id="userCreateForm">
		<table align="center">
			<tr>
				<td>E-Mail :</td>
				<td><form:input path="userEmail" /></td>
			</tr>
			<tr>
				<td>Passwort :</td>
				<td><form:input path="userPassword" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>