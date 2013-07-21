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
	$('#tunnelList').addClass("active");
	$('#tunnel_role').val("<s:property value="tunnel.role"/>");
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
			<form action="tunnelUpdateSubmit.do" id="form" method="post">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='2'><h4 class="text-info text-center">新增盾构段信息</h4></th>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td><s:property value="tunnelSection.tunnel.name"/></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">盾构段编号</strong></td>
						<td><input type="text" size="60" name="tunnelSection.name" value="<s:property value="tunnelSection.name"/>" required/></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">盾构段类型</strong></td>
						<td><input type="text" size="60" name="tunnelSection.type" value="<s:property value="tunnelSection.type"/>" required/></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">盾构段简介</strong></td>
						<td><textarea type="text" rows="5" cols="80"  name="tunnelSection.des"><s:property value="tunnelSection.des"/></textarea></td>
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



