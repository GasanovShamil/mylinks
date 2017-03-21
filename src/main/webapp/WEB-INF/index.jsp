<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <%@ page session="false" %> --%>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginDropdown.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/logoutDropdown.css">	
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
		<div class="panel panel-success">
			<div class="panel-heading" align="center">
				<h4>
					<b><font color="black" style="font-family: fantasy;">Create your short url:</font> </b>
				</h4>
			</div>
			<div class="container">
			<br>
				<c:if test="${not empty alert}">
    				<div class="alert alert-warning col-md-4 col-md-offset-4">
  						${ alert }
					</div>
				</c:if>
				<c:if test="${not empty succes}">
    				<div class="alert alert-warning col-md-4 col-md-offset-4">
  						${ succes }
					</div>
				</c:if>
				<br>
				<div class="row">
					<div class="col-md-4 col-md-offset-4">    
						<form action="createUrl" method=POST>          
						    <div class="input-group">
						      <input type="text" class="form-control" name="longUrl" >
						      <span class="input-group-btn"><button class="btn btn-default" type="submit">Go!</button></span>
						    </div>
						</form>
					</div>
				</div>
			</div>
			<div class="panel-footer" align="center">
				<font style="color: #111">Copyright @2017 <a href="#">mylinks.com</a>, All Rights Reserved.</font>
			</div>
		</div>
	</div>

</body>
</html>
