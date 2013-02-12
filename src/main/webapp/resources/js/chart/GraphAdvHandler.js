/**
 * GraphHandler Class to draw a graph chart depending on the given data
 * Init with api urls
 * 
 * Author: Lars Schüttemeyer
 */

// GraphHandler Class Constructor
function GraphHandler (userApiUrl, compareApiUrl) {
	this.userApiUrl = userApiUrl;
	this.compareApiUrl = compareApiUrl;
}

// Extend the graph class prototype object
GraphHandler.prototype = {
		
	// prepare data and start chart drawing
	draw: function(graphData) {
		this.drawChart(this.prepareData(graphData));
	},
	
	// prepare data to draw chart and set graph description
	prepareData: function (graphData) {
		var monthList = ['Jan', 'Feb', 'Mar', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug', 'Sep', 'Okt', 'Nov', 'Dez'];
		
		graphData.prepUserData = [];
		graphData.prepBestData = [];
		graphData.prepWorstData = [];
		graphData.dataCategories = [];
		
		debugger;
		
		for (key in graphData.compareData) {
			// convert month integer 1-12 to Jan-Dez
			graphData.dataCategories.push(monthList[graphData.compareData[key][4] - 1]);
			
			// save property value for user
			graphData.prepUserData.push(graphData.compareData[key][0]);
			graphData.prepBestData.push(graphData.compareData[key][1]);
			graphData.prepWorstData.push(graphData.compareData[key][2]);

		}
		return graphData;
	},

	// use highchart lib to draw the chart with the given data
	drawChart: function (graphData) {
		
		new Highcharts.Chart({
	        chart: {
	            renderTo: 'container',
	            type: 'line'
	        },
	        title: {
	            text: graphData.text
	        },
	        subtitle: {
	            text: 'Maximal mögiche Werte für das Jahr ' + graphData.year
	        },
	        xAxis: {
	            categories: graphData.dataCategories
	        },
	        yAxis: {
	            title: {
	                text: graphData.chartText + " (" + graphData.unit + ")"
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            shared: true,
	            crosshairs: true,
	            valueDecimals: 1
	        },
	        series: [{
	            name: 'Jinengo User',
	            data: graphData.prepUserData,
	            lineWidth: 4,
	            marker: {
	                radius: 4
	            }
	        }, {
	            name: 'Maximale Variante',
	            data: graphData.prepBestData
	        }, {
	            name: 'Minimale Variante',
	            data: graphData.prepWorstData
	        }]
	    });
	}
};
	