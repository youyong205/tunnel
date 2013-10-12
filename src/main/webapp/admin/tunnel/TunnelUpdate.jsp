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
	$('#type').val("<s:property value="tunnel.type"/>");
	$('#state').val("<s:property value="tunnel.state"/>");
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
						<th colspan='2'><h4 class="text-info text-center">编辑隧道信息</h4></th>
						<input type="hidden" name="tunnel.id" value="<s:property value="tunnel.id"/>" />
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">名称</strong></td>
						<td><input type="text" size="60" name="tunnel.name" value="<s:property value='tunnel.name'/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">类型</strong></td>
						<td>
							<select name="tunnel.type" id="type">
								<option value="1">地铁隧道</option>
								<option value="2">道路隧道</option>
								<option value="3">市政隧道</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">服役等级</strong></td>
						<td>
							<select name="tunnel.state" id='state'>
		    						<option value="A">正常</option>
		    						<option value="B">退化</option>
		    						<option value="C">劣化</option>
		    						<option value="D">恶化</option>
		    						<option value="E">危险</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">邮件通知人</strong></td>
						<td><textarea type="text" rows="5" cols="80"  name="tunnel.email"  class="{maxlength:512}"><s:property value='tunnel.email'/></textarea></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">备注信息</strong></td>
						<td><textarea type="text" rows="5" cols="80"  name="tunnel.des"  class="{maxlength:512}"><s:property value='tunnel.des'/></textarea></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:center;">
							<button  class="btn btn-small btn-primary" type="submit" >提交</button></td>
					</tr>
					</table>
			</form>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>



