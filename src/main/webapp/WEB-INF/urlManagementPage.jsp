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
					<td>${url.getExpireDate()}</td>
					<td>${url.getPassword()}</td>
					<td>${url.getNbClicks()}</td>
				</tr>
				<tr>
					<td colspan="12" class="hiddenRow">
						<div class="accordian-body collapse" id="${url.getShortUrl()}">
							<form action="manageUrl" method="post">
								<div class="row col-md-8 col-md-offset-2">
									<div class="col-md-2">
										<input type="hidden" name="shortUrl" value="${url.getShortUrl()}">
										<input type="password" name="urlPassword" class="form-control" placeholder="Password">
									</div>

									<div class="col-md-2">
										<input type="date" name="expireDate" class="form-control"
											placeholder="Expire Date (jj/mm/aaaa)">
									</div>
									<div class="col-md-2">
										<input type="text" name="nbClick" class="form-control"
											placeholder="Click number">
									</div>
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