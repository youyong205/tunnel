<%@ page contentType="text/html; charset=utf-8"%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href="index.do">隧道管理信息系统</a>
			<div class="nav-collapse collapse">
				<p class="navbar-text pull-right">
					登陆 <a href="#" class="navbar-link">${session.user.userName}</a>
				</p>
				<ul class="nav">
					<li class="active"><a href="#">Home</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>