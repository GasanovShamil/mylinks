<c:choose>
	<c:when test="${empty user}">
		<%@ include file="templates/navbarLogin.jsp"%>
	</c:when>
	<c:otherwise>
		<%@ include file="templates/navbarLogout.jsp"%>
	</c:otherwise>
</c:choose>