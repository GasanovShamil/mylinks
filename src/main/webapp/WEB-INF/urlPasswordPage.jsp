<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:choose>
		<c:when test="${empty user}">
			<%@ include file="navbarLogin.jsp"%>
		</c:when>
		<c:otherwise>
			<%@ include file="navbarLogout.jsp"%>
		</c:otherwise>
	</c:choose>
	<br>
	<div class="container-fluid">
		<div class="panel-group col-md-10 col-md-offset-1">
			<form action="redirect" method=POST>
				<div class="panel panel-success">
					<div class="panel-heading" align="center">Please enter	password to access this URL</div>
					<div class="panel-body">
						<c:if test="${not empty alert}">
							<div class="alert alert-warning col-md-6 col-md-offset-3">${ alert }</div>
						</c:if>
						<div class="row">
							<div class="col-md-6 col-md-offset-3">
								<div class="input-group">
									<input type="hidden" name="url" value="${ shortUrl }">
									<input type="password" class="form-control" name="urlPassword"	required> 
										<span class="input-group-btn"><button class="btn btn-default" type="submit">Go!</button></span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
