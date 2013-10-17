<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<!DOCTYPE html><html>
<head>
<title>软土盾构隧道后台管理系统</title>

<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css" >
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#logList').addClass("active");
	$('#log_role').val("<s:property value="log.role"/>");
});
</script>
</head>
<body>
  <%@include file="./../Head.jsp"%>
  </br>
  </br>
  <div class="container-fluid background-body" >
    <div class="row-fluid">
      <%@include file="./../Menu.jsp"%>
      <div class="span10">
		<table  class="table table-striped table-bordered table-condensed">
			<tr>
				<th colspan='2'><h4 class="text-info text-center">操作日志详细信息</h4></th>
			</tr>
			<tr>
				<td width="20%" style="text-align:right;"><strong class="text-success">模块名称</strong></td>
				<td><s:property value='log.module'/></td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right;"><strong class="text-success">操作名称</strong></td>
				<td><s:property value='log.operation'/></td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right;"><strong class="text-success">操作员</strong></td>
				<td><s:property value='log.user.realName'/></td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right;"><strong class="text-success">操作详情</strong></td>
				<td><s:property value='log.detail'/></td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right;"><strong class="text-success">操作时间</strong></td>
				<td><s:date name="log.creationDate" format="yyyy/MM/dd HH:mm" /></td>
			</tr>
			</table>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>



