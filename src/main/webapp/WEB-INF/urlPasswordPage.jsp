<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/loginDropdown.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/logoutDropdown.css">
</head>
<body>

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
