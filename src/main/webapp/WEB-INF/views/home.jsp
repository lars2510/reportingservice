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
	<div class="wrapper">
		<div class="head">
			<div class="left">
				<h2></h2>
				<span></span>
			</div>
		</div>
		<div class="nav-wrapper">
			<ul class="nav">
				<li class="categorie" data-type="monthSum">
					<div class="headline">CO2-Emission</div>
					<div class="subnav">
						<ul>
							<li class="btn monthSum" data-type="sumEcoImpact" data-unit="Gramm">Monatliche CO2-Emission</li>
							<li class="btn monthAvg" data-type="sumEcoImpact" data-unit="Gramm">Durchschnitlliche CO2-Emission</li>
							<li class="btn balance" data-type="sumEcoImpact" data-unit="Gramm">CO2-Bilanz</li>
							<li class="btn transportation" data-type="sumEcoImpact" data-unit="CO2-Emission in Gramm">Anteil der Verkehrsmittel</li>
						</ul>
					</div>
				</li>
				<li class="categorie">
					<div class="headline">Reisekosten</div>
					<div class="subnav">
						<ul>
							<li class="btn monthSum" data-type="sumCosts" data-unit="€">Monatliche Kosten</li>
							<li class="btn monthAvg" data-type="sumCosts" data-unit="€">Durchschnittliche Kosten</li>
							<li class="btn transportation" data-type="sumCosts" data-unit="Reisekosten in €">Anteil der Verkehrsmittel</li>
						</ul>			
					</div>
				</li>
				<li class="categorie" data-type="transportation">
					<div class="headline">Reisestrecke</div>
					<div class="subnav">
						<ul>
							<li class="btn monthSum" data-type="sumDistance" data-unit="Kilometer">Zurückgelegte Strecke</li>
							<li class="btn transportation" data-type="sumDistance" data-unit="Reisestrecke in Kilometer">Anteil der Verkehrsmittel</li>
						</ul>			
					</div>
				</li>
				<li class="categorie" data-type="transportation">
					<div class="headline">Reisezeit</div>
					<div class="subnav">
						<ul>
							<li class="btn monthSum" data-type="sumTime" data-unit="Minuten">Monatliche Reisezeit</li>
							<li class="btn monthSum" data-type="sumTimeUsable" data-unit="Minuten">Monatliche nutzbare Zeit</li>
							<li class="btn transportation" data-type="sumTime" data-unit="Reisezeit in Minuten">Anteil der Verkehrsmittel</li>
						</ul>			
					</div>
				</li>
			</ul>
			<div class="friend-list-hd right">
				<div class="headline">Vergleiche dich mit deinen Freunden</div>
				<ul id="friend-list">
				</ul>
			</div>
		</div>
		
		<div id="loader"></div>
		<div class="container-wrapper">
			<div id="container" class="container"></div>
			<div id="compare-container" class="container" style="display:none;"></div>
		</div>
		<div class="logout">
			<c:url value="/j_spring_security_logout" var="logoutUrl" />
			<a href="${logoutUrl}">Abmelden</a>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value='/resources/js/lib/jquery.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/lib/highcharts.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/lib/highcharts-more.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/chart/GraphHandler.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/chart/BalanceHandler.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/chart/PieHandler.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/JinengoChart.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/logic.js'/>"></script>
</body>
</html>
