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
		$(".container").addClass("type1of2").addClass("differentSize");
		$("#compare-container").show();
		this.drawChart(this.prepareData(graphData));
	},
	
	// prepare data to draw chart and set graph description
	prepareData: function (graphData) {
		var monthList = ['Jan', 'Feb', 'Mar', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug', 'Sep', 'Okt', 'Nov', 'Dez'],
			minCO2 = 0,
			maxCO2 = 0,
			userCO2 = 0;
		
		graphData.prepUserData = [];
		graphData.prepBestData = [];
		graphData.prepWorstData = [];
		graphData.dataCategories = [];
		
		for (key in graphData.userData) {
			// convert month integer 1-12 to Jan-Dez
			graphData.dataCategories.push(monthList[graphData.userData[key][4] - 1]);
			
			userCO2 += graphData.userData[key][0];
			minCO2 += graphData.userData[key][1];
			maxCO2 += graphData.userData[key][2];
			
			// save property value for user
			graphData.prepUserData.push(graphData.userData[key][0]);
			graphData.prepBestData.push(graphData.userData[key][1]);
			graphData.prepWorstData.push(graphData.userData[key][2]);

		}
		
		var total = maxCO2 - minCO2,
			advantage = maxCO2 - userCO2;
		
		graphData.savingPotential = parseInt(advantage / total * 100);
		
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
		
		// Meeter
		new Highcharts.Chart({
			
		    chart: {
		        renderTo: 'compare-container',
		        type: 'gauge'
		    },
		    
		    title: {
		        text: 'Ausgeschöpftes <br />CO2-Einsparpotential'
		    },
		    
		    pane: {
		        startAngle: -150,
		        endAngle: 150,
		        background: [{
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#FFF'],
		                    [1, '#333']
		                ]
		            },
		            borderWidth: 0,
		            outerRadius: '109%'
		        }, {
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#333'],
		                    [1, '#FFF']
		                ]
		            },
		            borderWidth: 1,
		            outerRadius: '107%'
		        }, {
		            // default background
		        }, {
		            backgroundColor: '#DDD',
		            borderWidth: 0,
		            outerRadius: '105%',
		            innerRadius: '103%'
		        }]
		    },
		       
		    // the value axis
		    yAxis: {
		        min: 0,
		        max: 100,
		        
		        minorTickInterval: 'auto',
		        minorTickWidth: 1,
		        minorTickLength: 10,
		        minorTickPosition: 'inside',
		        minorTickColor: '#666',
		
		        tickPixelInterval: 30,
		        tickWidth: 2,
		        tickPosition: 'inside',
		        tickLength: 10,
		        tickColor: '#666',
	  
		        labels: {
		            step: 2,
		            rotation: 'auto'
		        },
		        title: {
		            text: '%'
		        },
		        plotBands: [{
		            from: 0,
		            to: 20,
		            color: '#DF5353' // red
		        }, {
		            from: 20,
		            to: 50,
		            color: '#DDDF0D' // yellow
		        }, {
		            from: 50,
		            to: 100,
		            color: '#55BF3B' // green
		        }]        
		    },
		
		    series: [{
		        name: 'Ausgeschöpftes CO2-Einsparpotential',
		        data: [graphData.savingPotential],
		        tooltip: {
		            valueSuffix: ' %'
		        }
		    }]
		
		});
	}
};
	