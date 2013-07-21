<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>

<!DOCTYPE html><html>
<head>
<title>软土盾构隧道后台管理系统</title>

<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css">
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script src="js/jquery.js" type="text/javascript" ></script>
<script src="js/bootstrap.min.js" type="text/javascript" ></script>
<script src="js/jquery.validate.min.js" type="text/javascript" ></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#form").validate({
			rules : {
				"userName" : {
					required : true
				},"password" : {
					required : true
				}
			},
			messages : {
				"userName" : {
					required : "请输入用户名"
				},"password" : {
					required : "请输入密码"
				}
			}

		});
	});
</script>
</head>
<body>
  <%@include file="./admin/Head.jsp"%>
  </br>
  </br>
  <div class="container-fluid text-center">
  	<form action="login.do" id="form" method="post">
  		<s:actionerror/>
  		<table align="center" class="table table-striped table-bordered">
			<tr>	
				<th colspan='2'><h4 class="text-info text-center">用户登录</h4></th>
			</tr>
			<tr>
				<td width="45%" style="text-align:right;"><strong class="text-success">用户名</strong></td>
				<td ><input type="text" size="30" name="userName"/></td>
			</tr>
			<tr>
				<td style="text-align:right;"><strong class="text-success">密码</strong></td>
				<td><input type="password" size="30" name="password"/></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;">
					<button  class="btn btn-small btn-primary"  type="submit">登录</button></td>
			</tr>
		</table>
	</form>
    <%@include file="./admin/Foot.jsp"%>
  </div>
</body>
</html>
