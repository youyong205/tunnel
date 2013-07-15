<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>

<!DOCTYPE html><html>
<head>
<title>软土盾构隧道后台管理系统</title>

<link href="css/bootstrap.min.css" rel="stylesheet" user="screen">
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script src="js/jquery-1.7.1.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#userList').addClass("active");
	$("#form").validate({
		rules : {
			"user.userName" : {
				required : true
			},"user.password" : {
				required : true
			},"user.realName" : {
				required : true
			}
		},
		messages : {
			"user.userName" : {
				required : "请输入用户名"
			},"user.password" : {
				required : "请输入密码"
			},"user.realName" : {
				required : "请输入真实姓名"
			}
		}

	});
});
</script>
</head>
<body>
  <%@include file="./../Head.jsp"%>
  </br>
  </br>
  <div class="container-fluid">
    <div class="row-fluid">
      <%@include file="./../Menu.jsp"%>
      <div class="span10">
			<form action="userAddSubmit.do" id="form" method="post">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='2'><h4 class="text-info text-center">新增用户信息</h4></th>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">用户名</strong></td>
						<td><input type="text" size="60" name="user.userName" /></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">密码</strong></td>
						<td><input type="text" size="60" name="user.password" /></td>
					</tr>
					
					<tr>
						<td style="text-align:right;"><strong class="text-success">真实姓名</strong></td>
						<td><input type="text" size="60" name="user.realName" /></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success text-right">用户角色</strong></td>
						<td>
							<select name="user.role">
								<option value="1">普通用户</option>
								<option value="2">数据录入员</option>
								<option value="3">管理员</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:center;">
							<button  class="btn btn-small btn-primary"  type="submit" >提交</button>
					</tr>
					</table>
			</form>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>

