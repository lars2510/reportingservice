/**
 * Balance Handler Class to draw a graph chart depending on the given data
 * Init with api urls
 * 
 * Author: Lars Schüttemeyer
 */

// GraphHandler Class Constructor
function BalanceHandler (userApiUrl, compareApiUrl) {
	this.userApiUrl = userApiUrl;
	this.compareApiUrl = compareApiUrl;
}

// Extend the graph class prototype object
BalanceHandler.prototype = {
		
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
		
		for (key in graphData.userData) {
			// convert month integer 1-12 to Jan-Dez
			graphData.dataCategories.push(monthList[graphData.userData[key][4] - 1]);
			
			// save property value for user
			graphData.prepUserData.push(graphData.userData[key][0]);
			graphData.prepBestData.push(graphData.userData[key][1]);
			graphData.prepWorstData.push(graphData.userData[key][2]);

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
	            text: graphData.text + " für " + graphData.userName
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
	            }
	        },
	        tooltip: {
	            shared: false,
	            crosshairs: true,
	            valueDecimals: 1
	        },
	        credits: {
                enabled: false
            },
            plotOptions: {
                areaspline: {
                    fillOpacity: 0.4
                }
            },
            colors: [
                '#AA4643',
                '#4572A7',  
                '#63bd1e'
            ],
            series: [{
            	name: 'Maximaler Wert',
	            data: graphData.prepWorstData,
                lineWidth: 2,
                dashStyle: 'shortdot',
                marker: {
                    enabled: false
                }
            }, {
            	name: graphData.userName,
 	            data: graphData.prepUserData,
                lineWidth: 3,
                marker: {
                    radius: 5
                }
            }, {
            	name: 'Minimaler Wert',
	            data: graphData.prepBestData,
                lineWidth: 2,
                dashStyle: 'shortdot',
                marker: {
                    enabled: false
                }
            }]
	    });
	}
};
	