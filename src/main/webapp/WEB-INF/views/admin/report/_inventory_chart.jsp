<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load("current", {
		packages : [ "corechart" ]
	});
	google.charts.setOnLoadCallback(drawChart);
	function drawChart() {
		var data = google.visualization.arrayToDataTable([
				[ 'Loại', 'Giá trị' ], 
				<c:forEach var="e" items="${data }">
					["${e[0]}", ${e[2]}],
				</c:forEach>
		]);

		var options = {
			title : 'Doanh thu theo loại',
			is3D : true,
		};

		var chart = new google.visualization.PieChart(document
				.getElementById('piechart_3d'));
		chart.draw(data, options);
	}
</script>
</head>
<body>
	<div id="piechart_3d" style="width: 1000px; height: 1000px;"></div>
</body>
</html>