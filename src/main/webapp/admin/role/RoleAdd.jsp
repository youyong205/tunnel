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
	$('#roleList').addClass("active");
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
			<form action="roleAddSubmit.do" id="form" method="post">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='3'><h4 class="text-info text-center">新增角色信息</h4></th>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">用户名</strong></td>
						<td colspan='2'>
							<input type="text" size="60" name="role.name"  class="{required:true,maxlength:64}"/>
						</td>
					</tr>
					<s:iterator value="moduleResources" status="vs">
						<tr >
						<td width="20%" style="text-align:right;"><strong class="text-success text-right">模块权限</strong></td>
						<td width="10%" style="text-align:right;"><strong class="text-success text-right"><s:property value="key"/></strong></td>
						<td width="75%">
							<s:checkboxlist list="value.resources"
								listKey="id" listValue="name" name="resourceIdSelect" theme="simple">
							</s:checkboxlist>
						</td>
					</tr>
					</s:iterator>
					<tr>
						<td style="text-align:right;"><strong class="text-success">简介</strong></td>
						<td colspan='2'><textarea type="text" rows="5" cols="80"  name="role.des" class="{maxlength:512}"></textarea></td>
					</tr>
					<tr>
						<td colspan="3" style="text-align:center;">
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

