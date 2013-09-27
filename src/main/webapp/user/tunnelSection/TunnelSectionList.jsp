<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/struts-privilege.tld"%>

<!DOCTYPE html><html>
<head>
<title>隧道管理信息系统</title>

<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css" >
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
			<li class="active">盾构段列表</li>
		</ul>
			<div class='row'>
				<div class='span2'>
					<%@include file="./../TunnelSectionMenu.jsp"%>
				</div>
				<div class='span10'>
					<form class="text-right form-inline margin-buttom"
						action="userTunnelSectionList.do" method="post">
						<strong>隧道</strong>
						<s:select name="tunnelId" id="tunnelId" list="tunnels"
							listKey="id" listValue="name" value="tunnelId" theme="simple">
						</s:select>
						<button type="submit" class="btn btn-success btn-small">查询</button>
					</form>
					<table
						class="table table-striped table-bordered table-condensed table-hover">
						<thead>
							<tr>
								<th width="10%">序号</th>
								<th width="20%">盾构段编号</th>
								<th width="15%">类型</th>
								<th width="15%">环境</th>
								<th width="20%">服役状态</th>
								<th width="25%">详情</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="tunnelSections" status="vs">
								<tr>
									<td><s:property value='#vs.index+1' /></td>
									<td><s:property value="name" /></td>
									<td><s:property value="type" /></td>
									<td><s:property value="environment" /></td>
									<td>
										<span class='level<s:property value="state"/>'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="state"/></span>
									</td>
									<td><a
										href='userTunnelSectionDetail.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="id"/>&index=<s:property value="index"/>'
										class='btn btn-small btn-info'>详情</a> <a
										href='userTunnelSectionState.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="id"/>&index=<s:property value="index"/>'
										class='btn btn-small btn-info'>详细状态</a></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<div class="pagination text-center">
						<ul>
							<li><a href="#">共${totalSize}记录&${totalPages}页</a></li>
							<li><a
								href="?index=1&tunnelId=<s:property value="tunnelId"/>">首页</a></li>
							<s:iterator id="item" value="pageIndexs">
								<s:if test="${item == index }">
									<li class="disabled"><a
										href="?index=${item}&tunnelId=<s:property value="tunnelId"/>">${item}</a></li>
								</s:if>
								<s:else>
									<li><a
										href="?index=${item}&tunnelId=<s:property value="tunnelId"/>">${item}</a></li>
								</s:else>
							</s:iterator>
							<li><a
								href="?index=${totalPages}&tunnelId=<s:property value="tunnelId"/>">末页</a></li>
						</ul>
					</div>
				</div>
			</div>
			<%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
