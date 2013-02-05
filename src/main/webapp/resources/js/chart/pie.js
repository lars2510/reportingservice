function PieHandler (userApiUrl, compareApiUrl) {
	this.userApiUrl = userApiUrl;
	this.compareApiUrl = compareApiUrl;
}

PieHandler.prototype = {

	draw: function(graphData) {
		var preparedData = this.prepareData(graphData);
		$("#compare-container").show();
		this.drawChart(preparedData, preparedData.prepUserData, 'container');
		this.drawChart(preparedData, preparedData.prepCompareData, 'compare-container');
	},
	
	// prepare data to draw chart and set graph description
	prepareData: function (graphData) {
		graphData.prepUserData = [];
		graphData.prepCompareData = [];
		
		for (var i = 0; i < graphData.userData.length; i++) {
			graphData.prepUserData.push([graphData.userData[i][2], graphData.userData[i][0]]);	
		}
		
		for (var i = 0; i < graphData.compareData.length; i++) {
			graphData.prepCompareData.push([graphData.compareData[i][2], graphData.compareData[i][0]]);
		}
		
		return graphData;
	},

	drawChart: function (graphData, graphValues, container) {
	    new Highcharts.Chart({
	        chart: {
	            renderTo: container,
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false
	        },
	        title: {
	            text: graphData.chartText
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
	            data: graphValues
	        }]
	    });
	}
};
