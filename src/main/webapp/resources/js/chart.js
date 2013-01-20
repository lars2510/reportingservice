JinengoChart = function(keyFigure, chartYear, chartText) {
	
	// get user data for a given figure
	this.getUserData = function () {
		$.getJSON('api/user/figures', {keyFigure: keyFigure, year: chartYear}, function(userData, status) {
			getPlatformData(userData);
		});
	};
	
	// get data for overall platform user for a given figure
	var getPlatformData = function (userData) {
		$.getJSON('api/platform/figures', {keyFigure: keyFigure, year: chartYear}, function(platformData, status) {
			prepareData(userData, platformData);
		});
	};
	
	// prepare data to draw chart and set graph description
	var prepareData = function (userData, platformData) {
		var dataCategories = [],
			dataUser = [],
			dataPlatform = [];
		
		for (key in userData) {
			dataCategories.push(userData[key][2] + "-" + userData[key][1]);
			dataUser.push(userData[key][0]);
			if (platformData[key]) {
				dataPlatform.push(platformData[key][0]);
			} else {
				dataPlatform.push(0);
			}
		}
		$('#loader').hide();
		$('#container').show();
		createChart(dataUser, dataPlatform, dataCategories);
	};
	
	// Draw chart with jinengo api data
	var createChart = function(dataUser, dataPlatform, dataCategories) {
		new Highcharts.Chart({
	        chart: {
	            renderTo: 'container',
	            type: 'line',
	            marginRight: 130,
	            marginBottom: 25
	        },
	        title: {
	            text: chartText + ' im Vergleich zum Durchschnitt',
	            x: -20 //center
	        },
	        subtitle: {
	            text: 'Monatsansicht',
	            x: -20
	        },
	        xAxis: {
	            categories: dataCategories
	        },
	        yAxis: {
	            title: {
	                text: chartText
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            shared: true,
	            crosshairs: true
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'top',
	            x: -10,
	            y: 100,
	            borderWidth: 0
	        },
	        series: [{
	            name: 'Jinengo User',
	            data: dataUser,
	            lineWidth: 4,
	            marker: {
	                radius: 4
	            }
	        }, {
	            name: 'Durchschnitt',
	            data: dataPlatform
	        }]
	    });
	};
};


$(function () {
	// init event listener
	$(".chart-button").click(function() {
		$('#loader').show();
		$('#container').hide();
		var jinengoChart = new JinengoChart($(this).data('type'), '2012', $(this).text());
		jinengoChart.getUserData();
	});
	
	// init start chart @params: figure, year, description
	var jinengoChart = new JinengoChart('avgEcoImpact', '2012', 'Eco-Impact');
	jinengoChart.getUserData();
});