function generatePieChart(container, title, data) {
	$('#' + container).empty();

	var plot = jQuery.jqplot(container, [ data ], {
		title : title,
		seriesDefaults : {
			renderer : jQuery.jqplot.PieRenderer,
			rendererOptions : {
				fill : false,
				showDataLabels : true,
				sliceMargin : 4,
				lineWidth : 5,
				dataLabels : 'value'
			}
		},
		legend : {
			show : true,
			location : 'ne'
		}
	});
}

function generateDateAxes(container, title, data) {
	$('#' + container).empty();

	var date = new Date(data[0][0]), y = date.getFullYear(), m = date
			.getMonth();
	var minimum = new Date(y, m, 1);

	var plot = $.jqplot(container, [ data ], {
		title : title,
		axes : {
			xaxis : {
				renderer : $.jqplot.DateAxisRenderer,
				tickOptions : {
					formatString : '%b&nbsp;%#d'
				},
				min : minimum,
				tickInterval : '1 month'
			}
		},
		highlighter : {
			show : true
		},
		cursor : {
			show : false
		}
	});
}

function generateDonutChart(container, title, data) {
	var plot = $.jqplot(container, [ data[0], data[1] ], {
		title : title,
		seriesDefaults : {
			renderer : $.jqplot.DonutRenderer,
			rendererOptions : {
				sliceMargin : 3,
				startAngle : -90,
				showDataLabels : true,
				dataLabels : 'label'
			}
		}
	});
}

function generateGroupBarChart(container, title, data) {
	var plot = $.jqplot(container, data, {
		title : title,
		seriesDefaults : {
			renderer : $.jqplot.BarRenderer,
			pointLabels : {
				show : true,
				location : 'e',
				edgeTolerance : -15
			},
			shadowAngle : 135,
			rendererOptions : {
				barDirection : 'horizontal'
			}
		},
		 series:[
	            {label:'Chrome'},{label:'Mozilla'},
	            {label:'Safari'},{label:'Internet Explorer'}
	            ],

	             legend: {
	                  show: true,
	                  location: 'e',
	                fontSize: '11px'
	            }, 
		axes : {
			yaxis : {
				renderer : $.jqplot.CategoryAxisRenderer
			}
		}
	});
}

function generateTable(container, data) {
	var columns = ['Rank', 'Short url', 'Long url', 'Number of visits', 'Most visited by']
	
	var table = d3.select('#' + container).append('table').attr("class", "table table-stripped")
	var thead = table.append('thead')
	var	tbody = table.append('tbody');

	thead.append('tr')
		.selectAll('th')
		.data(columns)
		.enter()
		.append('th')
		.text(function (column) {
			return column;
		});

	var rows = tbody.selectAll('tr')
	.data(data)
	.enter()
	.append('tr');

	var cells = rows.selectAll('td')
		.data(function (row) {
			return columns.map(function (column) {
				return {column: column, value: row[column]};
			});
		})
		.enter()
		.append('td')
		.text(function (d) {
			if (d.column != 'Most visited by')
				return d.value;
		})
		.filter(function (d) {
			return (d.column == 'Most visited by' && d.value !='');
		})
		.append(function(d) {
			var flag = 'flags/' + d.value + '.png';
			
			var image = document.createElement("img");
			image.setAttribute('src', flag);
			image.setAttribute('title', d.value);
			image.setAttribute("width", "30px");
			image.setAttribute("height", "30px");
			
			return image;
		});

	return table;
}