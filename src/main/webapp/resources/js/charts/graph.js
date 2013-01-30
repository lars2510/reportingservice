// Draw chart with jinengo api data
var graphChart = function(graphData) {
	
	new Highcharts.Chart({
        chart: {
            renderTo: 'container',
            type: 'line'
        },
        title: {
            text: graphData.chartText + ' im Vergleich zum Durchschnitt'
        },
        subtitle: {
            text: 'Monatsansicht für das Jahr ' + graphData.chartYear
        },
        xAxis: {
            categories: graphData.dataCategories
        },
        yAxis: {
            title: {
                text: graphData.chartText
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
        series: [{
            name: 'Jinengo User',
            data: graphData.dataUser,
            lineWidth: 4,
            marker: {
                radius: 4
            }
        }, {
            name: 'Durchschnitt',
            data: graphData.dataPlatform
        }]
    });
	
};