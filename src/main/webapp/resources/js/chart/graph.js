function GraphHandler (userApiUrl, compareApiUrl) {
	this.userApiUrl = userApiUrl;
	this.compareApiUrl = compareApiUrl;
}

GraphHandler.prototype = {
		
	draw: function(graphData) {
		this.drawChart(this.prepareData(graphData));
	},
	
	// prepare data to draw chart and set graph description
	prepareData: function (graphData) {
		var dataCategories = [],
			prepUserData = [],
			prepPlatformData = [],
			monthList = ['Jan', 'Feb', 'Mar', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug', 'Sep', 'Okt', 'Nov', 'Dez'];
		
		for (key in graphData.userData) {
			// convert month integer 1-12 to Jan-Dez
			dataCategories.push(monthList[graphData.userData[key][2] - 1]);
			
			// save property value for user
			prepUserData.push(graphData.userData[key][0]);
			
			// save property value for platform sum
			if (graphData.compareData[key]) {
				prepPlatformData.push(graphData.compareData[key][0]);
			} else {
				prepPlatformData.push(0);
			}
		}
		
		graphData.prepUserData = prepUserData;
		graphData.prepPlatformData = prepPlatformData;
		graphData.dataCategories = dataCategories;
		
		return graphData;
	},

	drawChart: function (graphData) {
		
		new Highcharts.Chart({
	        chart: {
	            renderTo: 'container',
	            type: 'line'
	        },
	        title: {
	            text: graphData.chartText
	        },
	        subtitle: {
	            text: 'Monatsansicht für das Jahr ' + graphData.chartYear
	        },
	        xAxis: {
	            categories: graphData.dataCategories
	        },
	        yAxis: {
	            title: {
	                text: graphData.chartText + " (" + graphData.chartUnit + ")"
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
	            name: 'Durchschnitt',
	            data: graphData.prepPlatformData
	        }]
	    });
	}
};
	