var JinengoChart = {
	
	// get user data for a given figure
	getUserData: function (keyFigure) {
		$.getJSON('api/user/figures', {keyFigure: keyFigure}, function(userData, status) {
			JinengoChart.getPlatformData(userData, keyFigure);
		});
	},
	
	// get data for overall platform user for a given figure
	getPlatformData: function (userData, keyFigure) {
		$.getJSON('api/platform/figures', {keyFigure: keyFigure}, function(platformData, status) {
			JinengoChart.prepareData(userData, platformData);
		});
	},
	
	// prepare data to draw chart and set graph description
	prepareData: function (userData, platformData) {
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
		var chart = JinengoChart.createChart(dataUser, dataPlatform, dataCategories);
	},
	
	// Draw chart with jinengo api data
	createChart: function(dataUser, dataPlatform, dataCategories) {
		$('#loader').hide();
		$('#container').show();
		return new Highcharts.Chart({
	        chart: {
	            renderTo: 'container',
	            type: 'line',
	            marginRight: 130,
	            marginBottom: 25
	        },
	        title: {
	            text: 'Eco-Impact Jinengo User im Vergleich zur Referenzgruppe',
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
	                text: 'Eco-Impact'
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
	}
};


$(function () {
	// init click event listener
	$(".chart-button").click(function() {
		$('#loader').show();
		$('#container').hide();
		JinengoChart.getUserData($(this).data('type'));
	});
	    
});