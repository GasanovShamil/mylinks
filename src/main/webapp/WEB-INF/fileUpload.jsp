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

	<div class="panel panel-success">
		<div class="panel-heading" align="center">Create your short URL</div>
		
		<div class="panel-body">
			<form action="csvManager" method="post" enctype="multipart/form-data">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">

						<div class="input-group">
			                <label class="input-group-btn">
			                    <span class="btn btn-primary">
			                        Browse&hellip; <input type="file" name="file" size="50" style="display: none;"/> 
			                    </span>
			                </label>
			                <input type="text" class="form-control" readonly>
			                <span class="input-group-btn"><button
									class="btn btn-primary" type="submit">Go!</button></span>
			            </div>
					</div>
				</div>
			</form>
		</div>
		
		
		<div class="panel-footer" align="center">
			<p>CSV format: "personalUrlString","longUrlString","startDateString(YYYY-MM-DD)","expireDateString(YYYY-MM-DD)","urlPasswordString","captcha(true/false)","nbClickString"</p>

		</div>
	</div>
	</div>
	</body>
	</html>