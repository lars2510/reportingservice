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
		<li class="categorie" data-type="monthSum">
			<div class="headline">Monatsverbrauch</div>
			<div class="subnav monthSum">
				<ul>
					<li class="btn" data-type="sumEcoImpact" data-unit="Gramm">CO2-Emission</li>
					<li class="btn" data-type="sumCosts" data-unit="€">Kosten</li>
					<li class="btn" data-type="sumDistance" data-unit="Kilometer">Entfernung</li>
					<li class="btn" data-type="sumTime" data-unit="Minuten">Reisezeit</li>
					<li class="btn" data-type="sumTimeUsable" data-unit="Minuten">Nutzbare Zeit</li>
				</ul>
			</div>
		</li>
		<li class="categorie" data-type="monthAvg">
			<div class="headline">Monatsschnitt</div>
			<div class="subnav monthAvg">
				<ul>
					<li class="btn" data-type="sumEcoImpact" data-unit="Gramm">CO2-Emission</li>
					<li class="btn" data-type="sumCosts" data-unit="€">Kosten</li>
					<li class="btn" data-type="sumDistance" data-unit="Kilometer">Entfernung</li>
				</ul>			
			</div>
		</li>
		<li class="categorie" data-type="transportation">
			<div class="headline">Verkehrsmittel</div>
			<div class="subnav transportation">
				<ul>
					<li class="btn" data-type="sumEcoImpact" data-unit="Gramm">CO2-Emission</li>
					<li class="btn" data-type="sumCosts" data-unit="€">Kosten</li>
					<li class="btn" data-type="sumDistance" data-unit="Kilometer">Entfernung</li>
					<li class="btn" data-type="sumTime" data-unit="Minuten">Reisezeit</li>
				</ul>			
			</div>
		</li>
	</ul>

	<div id="loader"></div>
	<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
	<p><c:url value="/j_spring_security_logout" var="logoutUrl" />
	<a href="${logoutUrl}">Log Out</a></p>
	
	<script type="text/javascript" src="<c:url value='/resources/js/lib/jquery.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/lib/highcharts.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/lib/exporting.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/chart/graph.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/chart/bar.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/chart/pie.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/jinengoChart.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/logic.js'/>"></script>
</body>
</html>
