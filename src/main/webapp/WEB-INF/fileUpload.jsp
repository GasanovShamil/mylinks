<%@ page contentType="text/html;charset=utf-8" %>
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
	<br />
	<p>CSV format:</p>
	<p> "personalUrlString","longUrlString","startDateString","expireDateString","urlPasswordString","captcha","nbClickString"</p>
	<form action="csvManager" method="post"	enctype="multipart/form-data">
		<input type="file" name="file" size="50" /> <br /> <input
			type="submit" value="Upload File" />
	</form>
	</div>
</body>
</html>