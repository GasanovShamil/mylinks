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
	
	<div class="panel-group col-md-10 col-md-offset-1">
	<form action="createUrl" method=POST>
    <div class="panel panel-success">
      	<div class="panel-heading" align="center">Create your short URL</div>
      <div class="panel-body">
		<c:if test="${not empty alert}">
			<div class="alert alert-warning col-md-6 col-md-offset-3">
				<p class="text-center">${ alert }</p>
			</div>
		</c:if>
		<c:if test="${not empty shortUrl}">
  				<div class="alert alert-success col-md-6 col-md-offset-3">
						<p class="text-center"><a href="${ shortUrl }">${ shortUrl }</a></p>
			</div>
		</c:if>
				<div class="row">
					<div class="col-md-6 col-md-offset-3">    
						          
						    <div class="input-group">
						      <input type="text" class="form-control" name="longUrl" required>
						      <span class="input-group-btn"><button class="btn btn-default" type="submit">Go!</button></span>
						    </div>
						
					</div>
				</div>
	</div>
    </div>
    <div class="panel panel-default">
      	<div class="panel-heading" align="center">
      	<a data-toggle="collapse" href="#collapse1">Advanced options</a>
		</div>
		<div id="collapse1" class="panel-collapse collapse">
      <div class="panel-body">
			<div class="row col-md-6 col-md-offset-3">
				<div class="col-md-6">
	    			<input type="text" name="personalUrl" class="form-control" placeholder="Personal url">
	 			 </div>
	 			 <div class="col-md-6">
	    			<input type="password" name="urlPassword" class="form-control" placeholder="Password">
	 			 </div>
	 			 <div class="col-md-6">
	    			<input type="number" name="nbClick" class="form-control" placeholder="Click number">
	 			 </div>
	 			 <div class="col-md-6">
	    			<input type="checkbox" name="captcha" class="form-control" value="true">
	 			 </div>
			</div>
			<div class="row col-md-6 col-md-offset-3">
				<div class="col-md-6">
	    			<input type="date" name="startDate" class="form-control" placeholder="Expire Date (jj/mm/aaaa)">
	 			 </div>
				<div class="col-md-6">
	    			<input type="date" name="expireDate" class="form-control" placeholder="Expire Date (jj/mm/aaaa)">
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
