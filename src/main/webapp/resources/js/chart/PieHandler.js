/**
 * PieHandler Class to draw a pie chart depending on the given data
 * Init with api urls
 * 
 * Author: Lars Schüttemeyer
 */

// PieHandler Class default constructor
function PieHandler (userApiUrl, compareApiUrl) {
	this.userApiUrl = userApiUrl;
	this.compareApiUrl = compareApiUrl;
}

//Extend the pie class prototybe object
PieHandler.prototype = {

	// prepare data and start chart drawing, change html class properties to draw two pies
	draw: function(graphData) {
		var preparedData = this.prepareData(graphData);
		$("#compare-container").show();
		$(".container").addClass("type1of2");
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

	// use highchart lib to draw the chart with the given data
	drawChart: function (graphData, graphValues, container) {
		
	    new Highcharts.Chart({
	        chart: {
	            renderTo: container,
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false
	        },
	        title: {
	            text: graphData.chartText + " (" + graphData.chartUnit + ")"
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
