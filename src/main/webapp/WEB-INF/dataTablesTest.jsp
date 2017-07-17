<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.table-responsive>.table>tbody>tr>td{
    vertical-align: middle;
}
</style>
<%@ include file="header.jsp"%>
<br>
<div class="container-fluid table-responsive">
	<table class="table table-striped " id="urlTable">
		<thead>
			<tr>
				<th style="vertical-align:middle!important; width: 6% ">ShortUrl</th>
				<th style="vertical-align:middle; width: 50%">LongUrl</th>
				<th style="vertical-align:middle; width: 8%">Created</th>
				<th style="vertical-align:middle; width: 8%">Start</th>
				<th style="vertical-align:middle; width: 8%">Expire</th>
				<th style="vertical-align:middle; width: 8%">Password</th>
				<th style="vertical-align:middle; width: 4%">Clicks</th>
				<th style="vertical-align:middle; width: 4%">Captcha</th>
				<th style="vertical-align:middle; width: 4%">Edit</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="url" items="${urlList}">
				<tr>
					<td>${url.getShortUrl()}</td>
					<td><a href="${url.getLongUrl()}">${url.getLongUrl()}</a></td>
					<td>${url.getCreateDate()}</td>
					<td>${url.getStartDate()}</td>
					<td>${url.getExpireDate()}</td>
					<td>${url.getPassword()}</td> 
					<td>${url.getNbClicks()}</td>
					<td>${url.isCaptcha()}</td>
					<td><button type="button" class="btn btn-info accordion-toggle"  data-toggle="collapse" data-target="${url.getHtmlId()}"><span class="glyphicon glyphicon-edit"></span></button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>