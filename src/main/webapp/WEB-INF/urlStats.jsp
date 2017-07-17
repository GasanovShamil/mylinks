<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.table-responsive>.table>tbody>tr>td{
    vertical-align: middle;
}
</style>
<%@ include file="header.jsp" %>
<br>
<div class="container-fluid table-responsive">
	<div id="used-left-ratio"></div>
	<div id="evolution-over-time"></div>
</div>
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/stats/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stats/jqplot.highlighter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stats/jqplot.pieRenderer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stats/jqplot.dateAxisRenderer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stats/generateChart.js"></script>

<script type="text/javascript">
	$.ajaxSetup({ cache: false });
	
	$.post("http://localhost:8080/mylinks/stats?url=${statsUrl}", function(data) {
		var stats = $.parseJSON(data);
		
		var usedLeftRatio = stats["usedLeftRatio"];
		if (usedLeftRatio != null && usedLeftRatio.length > 0)
			generatePieChart('used-left-ratio', 'Clicks Used/Left Ratio', usedLeftRatio);

		var evolutionOverTime = stats["evolutionOverTime"];
		if (evolutionOverTime != null && evolutionOverTime.length > 0)
			generateDateAxes('evolution-over-time', 'Click Evolution Over Time', evolutionOverTime);
	});
</script>

</html>