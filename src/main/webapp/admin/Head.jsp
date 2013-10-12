<%@ page contentType="text/html; charset=utf-8"%>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href="userTunnelList.do">盾构隧道建设与运营安全数据系统-后台管理</a>
			<div class="nav-collapse collapse">
				<p class="navbar-text pull-right">
					<s:if test="null==#session.user">
						<a href="#myModal" data-toggle="modal">登陆</a>
					</s:if>
					<s:else>
						欢迎您，<a href="#" class="navbar-link">${session.user.userName}</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="logout.do" >登出</a>
					</s:else>
				</p>
			</div>
		</div>
	</div>
</div>
<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <form action="login.do" method="post">
	<table  class="table table-striped table-bordered table-condensed table-hover">
		<tr>	
			<th colspan='2'><h4 class="text-info text-center">用户登录</h4></th>
		</tr>
		<tr>
			<td style="text-align:right;"><strong class="text-success">用户名</strong></td>
			<td><input type="text" size="40" name="userName" /></td>
		</tr>
		<tr>
			<td style="text-align:right;"><strong class="text-success">密码</strong></td>
			<td><input type="text" size="40" name="password" /></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center;">
				<button  class="btn btn-small btn-primary"  type="submit" >登录</button></td>
		</tr>
	</table></form>
</div>