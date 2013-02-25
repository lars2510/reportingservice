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
		
		graphData.dataCategories = [];
		graphData.prepUserData = [];
		graphData.prepPlatformData = [];
		
		for (key in graphData.userData) {
			// convert month integer 1-12 to Jan-Dez
			graphData.dataCategories.push(monthList[graphData.userData[key][2] - 1]);
			
			// save property value for user
			graphData.prepUserData.push(graphData.userData[key][0]);
			
			// save property value for platform sum
			if (graphData.compareData[key]) {
				graphData.prepPlatformData.push(graphData.compareData[key][0]);
			} else {
				graphData.prepPlatformData.push(0);
			}
		}
		
		return graphData;
	},

	// use highchart lib to draw the chart with the given data
	drawChart: function (graphData) {
		
		new Highcharts.Chart({
	        chart: {
	            renderTo: 'container',
	            type: 'spline'
	        },
	        title: {
	            text: graphData.text
	        },
	        subtitle: {
	            text: 'Jahr: ' + graphData.year
	        },
	        xAxis: {
	            categories: graphData.dataCategories
	        },
	        yAxis: {
	            title: {
	                text: graphData.text + " (" + graphData.unit + ")"
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
	            name: graphData.userName,
	            data: graphData.prepUserData,
	            lineWidth: 4,
	            marker: {
	                radius: 4
	            }
	        }, {
	            name: graphData.friendName ? graphData.friendName : 'Durchschnitt',
	            data: graphData.prepPlatformData
	        }]
	    });
	}
};
	