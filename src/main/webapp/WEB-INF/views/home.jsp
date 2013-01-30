<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>Report</title>
	<meta name="description" content="Report deiner wichtigsten Kennzahlen">
    <meta name="viewport" content="width=device-width">
	<link rel="stylesheet" href="<c:url value='/resources/css/normalize.css'/>" type="text/css" />
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>" type="text/css" />
</head>
<body>
	<ul class="nav">
		<li class="figure-button" data-type="sumEcoImpact">CO2-Emission</li>
		<li class="figure-button" data-type="sumCosts">Kosten</li>
		<li class="figure-button" data-type="sumDistance">Entfernung</li>
		<li class="figure-button" data-type="sumTime">Reisezeit</li>
		<li class="figure-button" data-type="sumTimeUsable">Nutzbare Zeit</li>
	</ul>
	<ul class="nav">
		<li class="pie-button">Verkehrsmittel</li>
	</ul>
	<div id="loader"></div>
	<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
	<p><c:url value="/j_spring_security_logout" var="logoutUrl" />
	<a href="${logoutUrl}">Log Out</a></p>
	
	<script type="text/javascript" src="<c:url value='/resources/js/lib/jquery.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/lib/highcharts.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/lib/exporting.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/charts/graph.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/logic.js'/>"></script>
</body>
</html>
