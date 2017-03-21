<%@ page contentType="text/html;charset=utf-8" %>
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

<%
/* HttpSession session = request.getSession(false); */
if(/* session == null || */ session.getAttribute("user") == null){%>	
	<%@ include file="navbarLogin.jsp"%>
<%
}else{
%>
	<%@ include file="navbarLogout.jsp"%>
<%}%>


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
				<%! String alert; %>
				<% alert = (String)request.getAttribute("alert"); %>
				<% if (alert != null) {%>
				<div class="alert alert-warning col-md-4 col-md-offset-4">
  					<%= alert%>
				</div>
				<%} %>
				<%! String succes; %>
				<% succes = (String)request.getAttribute("succes"); %>
				<% if (succes != null) {%>
				<div class="alert alert-success col-md-4 col-md-offset-4">
  					<%= succes%>
				</div>
				<%} %>
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
