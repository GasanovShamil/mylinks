<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ include file="header.jsp"%>

<div class="container">
    <h1>Edit Profile</h1>
  	<hr>
	<div class="row">

      
      <!-- edit form column -->
      <div class="col-md-9 personal-info">
      <c:if test="${not empty message}">
        <div class="alert alert-success alert-dismissable">
          <a class="panel-close close" data-dismiss="alert">×</a> 
          <p class="text-center">${ message }</p>
        </div>
        </c:if>
         <c:if test="${not empty alert}">
        <div class="alert alert-warning alert-dismissable">
          <a class="panel-close close" data-dismiss="alert">×</a> 
          <p class="text-center">${ alert }</p>
        </div>
        </c:if>
        <h3>Personal info</h3>
        
        <form class="form-horizontal" action="account" role="form" method="post">
          <div class="form-group">
            <label class="col-lg-3 control-label">First name:</label>
            <div class="col-lg-8">
              <input class="form-control" name="name" type="text" value="${ user.name }" required>
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Last name:</label>
            <div class="col-lg-8">
              <input class="form-control" name="surname" type="text" value="${ user.surname }" required>
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Account type:</label>
            <div class="col-lg-8">
              <input class="form-control" type="text" value="${ user.accountType }" disabled>
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Email:</label>
            <div class="col-lg-8">
              <input class="form-control" type="text" value=" ${ user.email }" disabled>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Password:</label>
            <div class="col-md-8">
              <input class="form-control" name="password" type="password" value="">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Confirm password:</label>
            <div class="col-md-8">
              <input class="form-control" name="repassword" type="password" value="">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label"></label>
            <div class="col-md-8">
              <input type="submit" class="btn btn-primary" value="Save Changes">
              <span></span>
              <input type="reset" class="btn btn-default" value="Cancel">
            </div>
          </div>
        </form>
      </div>
  </div>
</div>
<hr>