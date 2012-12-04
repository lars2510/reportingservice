$(function () {
	var userId = 4201,
		keyFigure = "avgEcoImpact";
	
	$.getJSON('api/user/chartdata/' + userId, {keyFigure: keyFigure}, function(data, status) {
		createChart(data);
	});
	
	function handleData(data) {
		var res = {},
			key,
			resArray = [];
		
		for ( var i = 0; i < data.length; i++) {
			key = data[i].year + "-" + data[i].month;
			
			if (key in res) {
				res[key] += parseInt(data[i].avgEcoImpact);
			} else {
				res[key] = parseInt(data[i].avgEcoImpact);
			}
			
		}
		
		for (var obj in res) {
			resArray.push(res[obj]);
		}
		return resArray;
	}
	
	function createChart(data) {
		var dataCategories = [],
			dataUser = [];
		
		for (key in data) {
			dataCategories.push(data[key][1] + "-" + data[key][2]);
			dataUser.push(data[key][0]);
		}
		console.log(data);
		console.log(dataUser);
		var chart = new Highcharts.Chart({
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
	            name: 'Referenzgruppe',
	            data: [15000, 21000, 18000]
	        }]
	    });
	}
    
});