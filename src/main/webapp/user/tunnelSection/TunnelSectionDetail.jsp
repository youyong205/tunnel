<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/struts-privilege.tld"%>

<!DOCTYPE html>
<html>
<head>
<title>隧道管理信息系统</title>

<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css">
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.metadata.js"></script>
<script type="text/javascript" src="js/validate.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#tunnelSectionList').addClass('active');
		$('#tunnelSectionBaseInfo').addClass('active');

		var environment = '<s:property value="tunnelSection.environment"/>';
		$('#environment').val(environment);
		var state = '<s:property value="tunnelSection.state"/>';
		$('#state').val(state);
		$('#type').val('<s:property value="tunnelSection.type"/>');
	});
</script>
</head>
<body>
	<div class="container">
		<%@include file="./../Head.jsp"%>
		<div>
			<ul class="breadcrumb">
				<li>当前位置：</li>
				<li>首页<span class="divider">/</span></li>
				<li class="active"><a href='userTunnelSectionList.do?tunnelId=<s:property value="tunnelId"/>'>盾构段</a><span class="divider">/</span></li>
				<li class="active">详细信息</li>
			</ul>
		</div>
		<div class="row-fluid">
			<div class='span2'>
				<%@include file="./../TunnelSectionMenu.jsp"%>
			</div>
			<div class='span10'>
				<table class="table table-striped table-bordered table-condensed">
			<tr>
				<th colspan='2'><h4 class="text-info text-center">盾构段信息</h4></th>
				<input readonly type="hidden" name="index"
					value="<s:property value="index"/>" />
				<input readonly type="hidden" name="tunnelId"
					value="<s:property value="tunnelId"/>" />
				<input readonly type="hidden" name="tunnelSection.id"
					value="<s:property value="tunnelSection.id"/>" />
			</tr>
			<tr>
				<td width="30%" style="text-align: right;"><strong
					class="text-success">所属隧道</strong></td>
				<td><s:select name="tunnelSection.tunnelId"
						id="tunnelSectionId" list="tunnels" listKey="id" listValue="name"
						value="tunnelSection.tunnelId" theme="simple">
					</s:select></td>
			</tr>
			<tr>
				<td width="30%" style="text-align: right;"><strong
					class="text-success">盾构段编号</strong></td>
				<td><input readonly type="text" size="60" name="tunnelSection.name"
					 value="<s:property value="tunnelSection.name"/>"
					class="{required:true,maxlength:64}" /></td>
			</tr>
			<tr>
				<td width="30%" style="text-align: right;"><strong
					class="text-success">盾构段类型</strong></td>
				<td><select name="tunnelSection.type" id="type">
								<option value="-">-</option>
								<option value="上行">上行</option>
								<option value="下行">下行</option>
							</select></td>
			</tr>
			<tr>
				<td width="30%" style="text-align: right;"><strong
					class="text-success">盾构段环境</strong></td>
				<td><select id="environment" name="tunnelSection.environment">
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
						<option value="D">D</option>
						<option value="E">E</option>
				</select></td>
			</tr>
			<tr>
				<td width="30%" style="text-align: right;"><strong
					class="text-success">服役等级</strong></td>
				<td><select id="state" name="tunnelSection.state">
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
						<option value="D">D</option>
						<option value="E">E</option>
				</select></td>
			</tr>
			<tr>
				<td width="30%" style="text-align: right;"><strong
					class="text-success">盾构段外径</strong></td>
				<td><input readonly type="text" size="60" 
					name="tunnelSection.externalDiameter"
					value="<s:property value="tunnelSection.externalDiameter"/>"
					class="{required:true,number:true}" /></td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right;"><strong class="text-success">盾构段顺序号</strong></td>
				<td><input readonly type="text" size="60" name="tunnelSection.sequence" class="{required:true,number:true}" value="<s:property value="tunnelSection.sequence"/>"/></td>
			</tr>
			<tr>
				<td width="30%" style="text-align: right;"><strong
					class="text-success">盾构段备注信息</strong></td>
				<td><textarea readonly type="text" rows="5" cols="80" 
						name="tunnelSection.des" class="{maxlength:512}"><s:property value="tunnelSection.des" /></textarea></td>
			</tr>
		</table>
			</div>
		</div>
			
		<%@include file="./../Foot.jsp"%>
	</div>
</body>
</html>
