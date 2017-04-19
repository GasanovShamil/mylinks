<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${empty user}">
		<c:redirect context="/index" />
	</c:when>
	<c:otherwise>
		<%@ include file="navbarLogout.jsp"%>
	</c:otherwise>
</c:choose>
<br>
<div class="container-fluid">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>ShortUrl</th>
				<th>LongUrl</th>
				<th>Created</th>
				<th>Start</th>
				<th>Expire</th>
				<th>Password</th>
				<th>Clicks</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="url" items="${urlList}">
				<tr data-toggle="collapse" data-target="${url.getHtmlId()}"
					class="accordion-toggle">
					<td>${url.getShortUrl()}</td>
					<td>${url.getLongUrl()}</td>
					<td>${url.getCreateDate()}</td>
					<td>${url.getStartDate()}</td>
					<td>${url.getExpireDate()}</td>
					<td>${url.getPassword()}</td>
					<td>${url.getNbClicks()}</td>
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