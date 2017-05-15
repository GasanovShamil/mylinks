<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/logoutDropdown.css">
	<script src='https://www.google.com/recaptcha/api.js'></script>	
	<script src="${pageContext.request.contextPath}/js/frontUtils.js"></script>
	<script src="${pageContext.request.contextPath}/js/fileUpload.js"></script>
  </head>
<body>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span> 
      </button>
			<a class="navbar-brand" href="#">MyLinks</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
		<ul class="nav navbar-nav">
			<li><a href="/mylinks">Create URL</a></li>
			<li><a href="/mylinks/manageUrl">My URLs</a></li>
			<li><a href="/mylinks/csvManager">Upload CSV</a></li>
			<c:if test="${ user.isAdmin() }">
    				<li><a href="#">Admin</a></li>
			</c:if>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span> 
					<strong>${ user.name }</strong> <span	class="glyphicon glyphicon-chevron-down"></span>
			</a>
				<ul class="dropdown-menu">
					<li>
						<div class="navbar-login">
							<div class="row">
								<div class="col-lg-4">
									<p class="text-center">
										<span class="glyphicon glyphicon-user icon-size"></span>
									</p>
								</div>
								<div class="col-lg-8">
									<p class="text-left">
										<strong>${ user.name } ${ user.surname }</strong>
									</p>
									<p class="text-left small">${ user.email }</p>
									<p class="text-left">
										<a href="#" class="btn btn-primary btn-block btn-sm">Profile settings</a>
									</p>
								</div>
							</div>
						</div>
					</li>
					<li class="divider"></li>
					<li>
						<div class="navbar-login navbar-login-session">
							<div class="row">
								<div class="col-lg-12">
									<p>
										<a href="logout" class="btn btn-danger btn-block">Disconnect</a>
									</p>
								</div>
							</div>
						</div>
					</li>
				</ul></li>
		</ul>
		</div>
	</div>
</nav>


