<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.table-responsive>.table>tbody>tr>td{
    vertical-align: middle;
}
</style>
<c:choose>
	<c:when test="${empty user}">
		<c:redirect context="/index" />
	</c:when>
	<c:otherwise>
		<%@ include file="navbarLogout.jsp"%>
	</c:otherwise>
</c:choose>
<br>
<div class="container-fluid table-responsive">
	<table class="table table-striped ">
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
				<tr>
					<td colspan="12" class="hiddenRow">
						<div class="accordian-body collapse" id="${url.getShortUrl()}">
							<form class="form-inline" action="manageUrl" method="post">
								<input type="hidden" name="shortUrl" value="${url.getShortUrl()}">
								
									<div class="form-group">
									<label for="startDate">From:</label>
										<input type="date" id="startDate" name="startDate" class="form-control">
									</div>
									<div class="form-group">
										<label for="expireDate">Until:</label>
										<input type="date" id="expireDate" name="expireDate" class="form-control">
									</div>
									<div class="form-group">									
										<input type="password" name="urlPassword" class="form-control" placeholder="Password">
									</div>
									<div class="form-group">
										<input type="text" name="nbClick" class="form-control" placeholder="Click number">
									</div>
									<div class="form-group">
										<label><input type="checkbox" id="#checkbox" name="captchaCheckbox"  value="true">Captcha</label>
									</div>
									<button class="btn btn-default" type="submit">Update!</button>
							</form>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>