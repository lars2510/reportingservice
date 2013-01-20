function getPlatformData(userData, keyFigure) {
	$.getJSON('api/platform/figures', {keyFigure: keyFigure}, function(platformData, status) {
		createChart(userData, platformData);
	});
}
	
function getUserData(keyFigure) {
	$.getJSON('api/user/figures', {keyFigure: keyFigure}, function(userData, status) {
		getPlatformData(userData, keyFigure);
	});
}

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

function createChart(userData, platformData) {
	var dataCategories = [],
		dataUser = [],
		dataPlatform = [];
	
	for (key in userData) {
		dataCategories.push(userData[key][1] + "-" + userData[key][2]);
		dataUser.push(userData[key][0]);
		if (platformData[key]) {
			dataPlatform.push(platformData[key][0]);
		} else {
			dataPlatform.push(0);
		}
	}
	console.log(userData);
	console.log(platformData);
	console.log(dataUser);
	console.log(dataPlatform);
	
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
            name: 'Durchschnitt',
            data: dataPlatform
        }]
    });
}

function initHandler() {
	$(".chart-button").click(function() {
		getUserData($(this).data('type'));
	});
}

$(function () {
	
	initHandler();
	    
});