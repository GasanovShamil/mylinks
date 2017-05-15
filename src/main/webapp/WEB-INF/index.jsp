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
						<div class="checkbox">
								<label><input type="checkbox" id="passwordCheckbox" name="passwordCheckbox" value="true"> Secure with password </label>			
							</div>
							<div class="collapse" id="crypt">			
					    			<div class="input-group col-md-4">
					      				<span class="input-group-btn">
					        				<button class="btn btn-default" id="refresh" type="button"><span class="glyphicon glyphicon-refresh"></span></button>
					      				</span>
					      				<input type="text" class="form-control input-sm" id="urlPassword" name="urlPassword" placeholder="Password value="">
					    			</div><!-- /input-group --> 			
							</div>	
					</div>
				</div>
	</div>
    </div>
    
    <c:choose>
    <c:when test="${empty user}">
        <div class="alert alert-info col-md-6 col-md-offset-3">
				<p class="text-center">Please sign in or sign up to use advanced options!</p>
			</div>
    </c:when>    
    <c:otherwise>  
    <div class="panel panel-default">
      	<div class="panel-heading accordion-toggle" align="center" data-toggle="collapse" data-target="#collapse1" role="button">
      	Advanced options
		</div>
		<div id="collapse1" class="panel-collapse collapse">
      <div class="panel-body">
      		<div class="form-group row col-md-6 col-md-offset-3">
				<div class="col-md-6">
					<label for="startDate">From: </label>
	    			<input type="date" id="startDate"name="startDate" class="form-control">
	 			 </div>
				<div class="col-md-6">
				<label for="endDate">Until: </label>
	    			<input type="date" id="endDate" name="expireDate" class="form-control">
	 			 </div>
	 			 
			</div>
			<div class="form-group row col-md-6 col-md-offset-3">
				<div class="col-md-6">
	    			<input type="text" name="personalUrl" class="form-control" placeholder="Personal url">
	 			 </div>
	 			 <div class="col-md-6">
	    			<input type="number" name="nbClick" class="form-control" placeholder="Click number">
	 			 </div>
	 			 <div class="checkbox col-md-6">
	 			 <label>
				 	<input type="checkbox" id="#checkbox" name="captchaCheckbox"  value="true">Captcha</label>
	 			 </div>
			</div>
			
		</div>
    </div>
    </div>
      
    </c:otherwise>
</c:choose>
    
    </form>
  </div>
  
  </div>
</body>
</html>
