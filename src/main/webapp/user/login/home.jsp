<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/struts-privilege.tld"%>

<!DOCTYPE html><html>
<head>
<title>隧道管理信息系统</title>

<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css" >
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.metadata.js"></script>
<script type="text/javascript" src="js/validate.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
</head>
<body>
  <div class="container">
	<%@include file="./../Head.jsp"%>
    <div class="row-fluid">
      <div class="span12"> 
		<form action="login.do" id="form" method="post">
	  		<s:actionerror/>
	  		<table align="center" class="table table-striped table-bordered">
				<tr>	
					<th colspan='2'><h4 class="text-info text-center">用户登录</h4></th>
				</tr>
				<input type='hidden' name="requestUrl" value='<s:property value="requestUrl"/>'/>
				<input type='hidden' name='role' value='user'/>
				<tr>
					<td width="45%" style="text-align:right;"><strong class="text-success">用户名</strong></td>
					<td ><input type="text" size="30" name="userName" required/></td>
				</tr>
				<tr>
					<td style="text-align:right;"><strong class="text-success">密码</strong></td>
					<td><input type="password" size="30" name="password" required/></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;">
						<button  class="btn btn-small btn-primary"  type="submit">登录</button></td>
				</tr>
			</table>
		</form>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
