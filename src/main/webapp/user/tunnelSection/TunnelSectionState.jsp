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
		$('#tunnelSectionState').addClass('active');
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
				<li class="active"><a
					href='userTunnelSectionList.do?tunnelId=<s:property value="tunnelId"/>'>盾构段列表</a><span
					class="divider">/</span></li>
				<li class="active">服役状态</li>
			</ul>
		</div>
		<div class="row">
			<div class="span2">
				<%@include file="./../TunnelSectionMenu.jsp"%>
			</div>
			<div class="span10">
				<form class="text-right form-inline margin-buttom"
					action="userTunnelSectionState.do" method="post">
					<strong>选择隧道</strong>
					<s:select name="tunnelId" id="tunnelId" onchange="tunnelChangedExcluedeAll()"
						list="tunnels" listKey="id" listValue="name" value="tunnelId"
						theme="simple">
					</s:select>
					<strong>选择盾构段</strong>
					<s:select name="tunnelSectionId" id="tunnelSectionId"
						list="tunnelSections" listKey="id" listValue="name"
						value="tunnelSectionId" theme="simple">
					</s:select>
					<button type="submit" class="btn btn-success btn-small">查询</button>
				</form>
				<div style='padding-left:40px;'>
					<h5 class='text-error text-center'>上行线盾构隧道状态
						<s:iterator value="upCounts" status="vs">
							<span class="level<s:property value="key" />"><s:property value="key" /></span>
							<s:property value="value"/>
						</s:iterator>
					</h5>
					<s:iterator value="upSvgs" status="vs">
						<span><s:property value="key" /></span>衬砌环服役状态
						<s:property value="value" escape="false" />
					</s:iterator>
				</div>
				<div style='padding-left:40px;'>
					<h5 class='text-error text-center'>下行线盾构隧道状态
					<s:iterator value="downCounts" status="vs">
						<span class="level<s:property value="key" />"><s:property value="key" /></span>
						<s:property value="value"/>
					</s:iterator>
					</h5>
					<s:iterator value="downSvgs" status="vs">
						<span><s:property value="key" /></span>衬砌环服役状态
						<s:property value="value" escape="false" />
					</s:iterator>
				</div>
			</div>
		</div>
		<%@include file="./../Foot.jsp"%>
	</div>
</body>
</html>
