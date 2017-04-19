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
<div class="container-fluid">
	<div class="alert alert-danger col-md-6 col-md-offset-3">
		${message}
	</div>
</div>
</body>
</html>