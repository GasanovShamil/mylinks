<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ include file="WEB-INF/header.jsp"%>

<div class="container-fluid">
	<div class="alert alert-danger col-md-6 col-md-offset-3 text-center">


		<c:choose>
			<c:when test="${not empty alert}">
				<p>There is no such url ${alert} !</p>
			</c:when>
			<c:otherwise>
       			<p>Url format not valid !</p>
			</c:otherwise>
		</c:choose>
	</div>
</div>
</body>
</html>