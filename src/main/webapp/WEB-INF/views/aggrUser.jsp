<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Nachhaltige Mobilität</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">

		<link rel="stylesheet" href="<c:url value='/resources/css/normalize.css'/>" type="text/css" />
		<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>" type="text/css" />
		<script type="text/javascript" src="<c:url value='/resources/js/lib/jquery.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/resources/js/lib/highcharts.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/resources/js/lib/exporting.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/resources/js/chart.js'/>"></script>
	</head>
	<body>
		<ul class="nav">
			<li class="chart-button" data-type="avgEcoImpact">EcoImpact</li>
			<li class="chart-button" data-type="avgCosts">Costs</li>
			<li class="chart-button" data-type="avgDistance">Distance</li>
			<li class="chart-button" data-type="avgTime">Time</li>
			<li class="chart-button" data-type="avgTimeUsable">TimeUsable</li>
		</ul>
		<div id="loader"></div>
		<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
	</body>
</html>
