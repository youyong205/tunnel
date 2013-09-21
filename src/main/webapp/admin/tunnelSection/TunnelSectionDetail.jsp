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
	$('#tunnelSectionList').addClass("active");
	
	var type='<s:property value="tunnelSection.environment"/>';
	$('#environment').val(type);
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
			<form action="tunnelSectionUpdateSubmit.do" id="form" method="post">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='2'><h4 class="text-info text-center">盾构段信息</h4></th>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input type="hidden" name="tunnelSection.id" value="<s:property value="tunnelSection.id"/>"/>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td>
							<s:select name="tunnelSection.tunnelId" id="tunnelSectionId"
									list="tunnels" listKey="id" listValue="name"  value="tunnelSection.tunnelId" theme="simple" >
							</s:select>
						</td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">盾构段编号</strong></td>
						<td><input type="text" size="60" name="tunnelSection.name" readonly value="<s:property value="tunnelSection.name"/>" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">盾构段类型</strong></td>
						<td><input type="text" size="60" name="tunnelSection.type" readonly value="<s:property value="tunnelSection.type"/>" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">盾构段环境</strong></td>
						<td>
							<select id="environment" name="tunnelSection.environment">
								<option value="A">A</option>
								<option value="B">B</option>
								<option value="C">C</option>
								<option value="D">D</option>
								<option value="E">E</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">盾构段外径</strong></td>
						<td><input type="text" size="60" name="tunnelSection.externalDiameter" value="<s:property value="tunnelSection.externalDiameter"/>" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">盾构段备注信息</strong></td>
						<td><textarea type="text" rows="5" cols="80" readonly name="tunnelSection.des" class="{maxlength:512}"><s:property value="tunnelSection.des"/></textarea></td>
					</tr>
				</table>
			</form>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>



