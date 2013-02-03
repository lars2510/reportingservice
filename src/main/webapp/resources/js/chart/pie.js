function PieHandler (userApiUrl, compareApiUrl) {
	this.userApiUrl = userApiUrl;
	this.compareApiUrl = compareApiUrl;
}

PieHandler.prototype = {

	draw: function(graphData) {
		this.drawChart(this.prepareData(graphData));
	},
	
	// prepare data to draw chart and set graph description
	prepareData: function (graphData) {
		graphData.prepUserData = [];
		graphData.prepPlatformData = [];
		
		for (var i = 0; i < graphData.userData.length; i++) {

			// save property value for user
			graphData.prepUserData.push([graphData.userData[i][2], graphData.userData[i][0]]);
			
			// save property value for compare data
			if (graphData.compareData[i]) {
				graphData.prepPlatformData.push([graphData.compareData[i][2], graphData.compareData[i][0]]);
			} else {
				graphData.prepPlatformData.push(0);
			}
		}
		return graphData;
	},

	drawChart: function (graphData) {
	    new Highcharts.Chart({
	        chart: {
	            renderTo: 'container',
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false
	        },
	        title: {
	            text: 'Verteilung ' + graphData.chartText
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage}%</b>',
	            percentageDecimals: 1
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    color: '#333',
	                    connectorColor: '#333',
	                    formatter: function() {
	                        return '<b>'+ this.point.name +'</b>: '+ this.percentage.toFixed(2) +' %';
	                    }
	                },
	                showInLegend: true
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: graphData.chartText,
	            data: graphData.prepUserData
	        }]
	    });
	}
};
