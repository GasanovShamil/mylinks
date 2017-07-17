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
	<div id="security"></div>
	<div id="browser-by-country"></div>
	<h4 class="jqplot-title text-center))">Popularity</h4>
	<div id="popularity"></div>
</div>
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/stats/d3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stats/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stats/jqplot.donutRenderer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stats/jqplot.barRenderer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stats/jqplot.categoryAxisRenderer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stats/jqplot.highlighter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stats/jqplot.pointLabels.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stats/generateChart.js"></script>

<script type="text/javascript">
	$.ajaxSetup({ cache: false });
	
	$.post("http://localhost:8080/mylinks/stats", function(data) {
		var stats = $.parseJSON(data);
		
 		var security = stats["security"];
		if (security != null && security.length > 0)
			generateDonutChart('security', 'Security', security);
 
 		var browserByCountry = stats["browserByCountry"];
		if (browserByCountry != null && browserByCountry.length > 0)
			generateGroupBarChart('browser-by-country', 'Browser by country', browserByCountry);

		var popularity = stats["popularity"];
/*
		var popularity = [];
		popularity.push({'Rank' : 1, 'Short url' : '11111', 'Long url' : '1111111111', 'Number of visits' : 42, 'Most visited by' : 'France'});
		popularity.push({'Rank' : 2, 'Short url' : '22222', 'Long url' : '2222222222', 'Number of visits' : 2, 'Most visited by' : 'Italy'});
		popularity.push({'Rank' : 3, 'Short url' : '33333', 'Long url' : '3333333333', 'Number of visits' : 1, 'Most visited by' : 'France'});
*/
		if (popularity != null && popularity.length > 0)
			generateTable('popularity', popularity);
	});
</script>

</html>