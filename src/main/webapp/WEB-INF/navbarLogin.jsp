<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
      <a class="navbar-brand" href="/mylinks">MyLinks</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
    <ul class="nav navbar-nav">
      <li><a href="/mylinks">Home</a></li>
      <li><a href="#">Page 1</a></li>
      <li><a href="#">Page 2</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>  <b>Sign up</b> <span class="caret"></span></a>
			<ul id="login-dp" class="dropdown-menu">
				<li>
					 <div class="row">
							<div class="col-md-12">
								<form class="form" role="form" method="post" action="createUser" accept-charset="UTF-8" id="login-nav">
										<div class="form-group">
											 <label class="sr-only" for="name">Name</label>
											 <input type="text" name="name" class="form-control" id="name" placeholder="Name" required>
										</div>
										<div class="form-group">
											 <label class="sr-only" for="surname">Surname</label>
											 <input type="text" name="surname" class="form-control" id="surname" placeholder="Surname" required>
										</div>
										<div class="form-group">
											 <label class="sr-only" for="inputEmail">Email address</label>
											 <input type="email" name="login" class="form-control" id="inputEmail" placeholder="Email address" required>
										</div>
										<div class="form-group">
											 <label class="sr-only" for="inputPassword2">Password</label>
											 <input type="password" name="password" class="form-control" id="inputPassword2" placeholder="Password" required>
										</div>
										<div class="form-group">
											 <label class="sr-only" for="inputPassword3">Repeat password</label>
											 <input type="password" name="repassword" class="form-control" id="inputPassword3" placeholder="Repeat password" required>
										</div>
										<div class="form-group">
											 <button type="submit" class="btn btn-primary btn-block">Sign up</button>
										</div>
								 </form>
							</div>
					 </div>
				</li>
			</ul>
        </li>
      <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-log-in"></span>  <b>Login</b> <span class="caret"></span></a>
			<ul id="login-dp" class="dropdown-menu">
				<li>
					 <div class="row">
							<div class="col-md-12">
								<form class="form" role="form" method="post" action="login" accept-charset="UTF-8" id="login-nav">
										<div class="form-group">
											 <label class="sr-only" for="exampleInputEmail2">Email address</label>
											 <input type="email" name="login" class="form-control" id="exampleInputEmail2" placeholder="Email address" required>
										</div>
										<div class="form-group">
											 <label class="sr-only" for="exampleInputPassword2">Password</label>
											 <input type="password" name="password" class="form-control" id="exampleInputPassword2" placeholder="Password" required>
                                             <div class="help-block text-right"><a href="">Forget the password ?</a></div>
										</div>
										<div class="form-group">
											 <button type="submit" class="btn btn-primary btn-block">Sign in</button>
										</div>
								 </form>
							</div>
					 </div>
				</li>
			</ul>
        </li>
    </ul>
  </div>
  </div>
</nav>