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
			<li><a href="/mylinks">Home</a></li>
			<li><a href="#">URLs</a></li>
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


