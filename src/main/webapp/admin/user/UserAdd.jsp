<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>

<!DOCTYPE html><html>
<head>
<title>软土盾构隧道后台管理系统</title>

<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css" >
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.metadata.js"></script>
<script type="text/javascript" src="js/validate.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$('#userList').addClass("active");
	$("#form").validate();
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
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">用户名</strong></td>
						<td>
							<input type="text" size="60" name="user.userName"  class="{required:true,maxlength:64}"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">密码</strong></td>
						<td><input type="text" size="60" name="user.password"  class="{required:true,maxlength:64}"/></td>
					</tr>
					
					<tr>
						<td style="text-align:right;"><strong class="text-success">真实姓名</strong></td>
						<td><input type="text" size="60" name="user.realName"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success text-right">用户角色</strong></td>
						<td>
							<s:checkboxlist list="roles"
								listKey="id" listValue="name" name="roleIdSelect" theme="simple">
							</s:checkboxlist>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:center;">
							<button  class="btn btn-small btn-primary"  type="submit" >提交</button></td>
					</tr>
					</table>
			</form>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>

