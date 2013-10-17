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
<script type="text/javascript" src="js/tunnel.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#tunnelSectionList').addClass('active');
		var type = '<s:property value='type'/>';
		if(type==null||type.length==0){
			$('#tunnelSectionState').addClass('active');
		}else{
			$('#'+type).addClass('active');
		}
	});
</script>
<style type="text/css">
td{
	padding:6px 4px 0px 4px;
}
</style>
</head>
<body>
	<div class="container">
		<%@include file="./../Head.jsp"%>
		<div>
			<ul class="breadcrumb">
				<li>当前位置：</li>
				<li>首页<span class="divider">/</span></li>
				<li class="active"><a
					href='userTunnelSectionList.do?tunnelId=<s:property value="tunnelId"/>'>盾构段</a><span
					class="divider">/</span></li>
				<li class="active">服役状态</li>
			</ul>
		</div>
		<div class="row-fluid">
			<div class="span2">
				<%@include file="./../TunnelSectionMenu.jsp"%>
			</div>
			<div class="span10" style='margin-left:10px;'>
				<form class="text-right form-inline margin-buttom"
					action="userTunnelSectionState.do" method="post">
					<strong>隧道</strong>
					<s:select name="tunnelId" id="tunnelId" onchange="tunnelChangedExcluedeAll()"
						list="tunnels" listKey="id" listValue="name" value="tunnelId"
						theme="simple">
					</s:select>
					<strong>盾构段</strong>
					<s:select name="tunnelSectionId" id="tunnelSectionId"
						list="tunnelSections" listKey="id" listValue="name"
						value="tunnelSectionId" theme="simple">
					</s:select>
					<button type="submit" class="btn btn-success btn-small">查询</button>
				</form>
				<div>
					<h5 class=' text-center'>上行线【<span class="text-error">服役状态</span>】
						<s:iterator value="upCounts" status="vs">
							</span><span class="level<s:property value="key" />">&nbsp;&nbsp;</span>
							<s:if test="key == 'A'.toString()">i</s:if>
							<s:elseif test="key =='B'.toString()">ii</s:elseif>
							<s:elseif test="key =='C'.toString()">iii</s:elseif>
							<s:elseif test="key =='D'.toString()">iv</s:elseif>
							<s:elseif test="key =='E'.toString()">v</s:elseif>
							
							-<s:property value="value"/>
						</s:iterator>
					</h5>
					<table style='margin-bottom:0px;' class="table table-striped table-bordered table-condensed table-hover">
					<s:iterator value="upSvgs" status="vs">
						<tr><td style="text-align:center;padding:6px 4px 0px 4px;" class='text-success' width="8%"><s:property value="key" /></td>
							<td  style="padding:6px 4px 0px 4px;"><s:property value="value" escape="false" /></td></tr>
					</s:iterator>
					</table>
				</div>
				<div>
					<h5 class=' text-center'>下行线【<span class="text-error">服役状态】
					<s:iterator value="downCounts" status="vs">
						<span class="level<s:property value="key" />"><s:property value="key" />&nbsp;&nbsp;</span>
						<s:if test="key == 'A'.toString()">i</s:if>
							<s:elseif test="key =='B'.toString()">ii</s:elseif>
							<s:elseif test="key =='C'.toString()">iii</s:elseif>
							<s:elseif test="key =='D'.toString()">iv</s:elseif>
							<s:elseif test="key =='E'.toString()">v</s:elseif>-<s:property value="value"/>
					</s:iterator>
					</h5>
					<table class="table table-striped table-bordered table-condensed table-hover">
					<s:iterator value="downSvgs" status="vs">
						<tr><td  style="text-align:center;padding:6px 4px 0px 4px;" class='text-success' width="8%"><s:property value="key" /></td>
							<td style="padding:6px 4px 0px 4px;"><s:property value="value" escape="false" /></td></tr>
					</s:iterator>
					</table>
				</div>
			</div>
		</div>
		<%@include file="./../Foot.jsp"%>
	</div>
</body>
</html>
