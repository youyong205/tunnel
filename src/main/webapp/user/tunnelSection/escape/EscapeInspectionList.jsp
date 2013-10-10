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
<script type="text/javascript" src="js/escape.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#tunnelSectionList').addClass('active');
		$('#escape').addClass('active');
	});
</script>
</head>
<body>
  <div class="container">
	<%@include file="./../../Head.jsp"%>
	<div>
		<ul class="breadcrumb">
			<li>当前位置：</li>
			<li>首页<span class="divider">/</span></li>
			<li><a href='userTunnelSectionList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>'>盾构段</a><span class="divider">/</span></li>
			<li><a href="userEscapeList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>">逃生楼梯</a><span class="divider">/</span></li>
			<li class="active">质量检查</li>
		</ul>
	</div>
	<div class='row'>
		<div class='span2'>
			<%@include file="./../../TunnelSectionMenu.jsp"%>
		</div>
		<div class='span10'>
     <div class="row-fluid">
			    <div class="span4">
					<a href="userEscapeList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>" class="btn btn-small  btn-primary btn-info">逃生楼梯列表</a>
	    	  		<a href="userEscapeInspectionList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>" class="btn btn-small btn-primary btn-success" >质量检查</a>
	      			<a href="userEscapeCuringList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>" class="btn btn-small  btn-primary btn-info">养护记录</a>
      			</div>
      			<div class="span8">
		      		<form class="text-right form-inline margin-buttom" method="get">
						<strong>隧道</strong>
						<s:select name="tunnelId" id="tunnelId" onchange="tunnelChanged(false)"
							list="tunnels" listKey="id" listValue="name" value="tunnelId"
							theme="simple">
						</s:select>
						<strong>盾构段</strong>
						<s:select name="tunnelSectionId" id="tunnelSectionId"
							list="tunnelSections" listKey="id" listValue="name" headerKey="0"
							headerValue="ALL" onchange="tunnelSectionChanged(false)"
							value="tunnelSectionId" theme="simple">
						</s:select>
						<strong>逃生楼梯</strong>
						<s:select name="componentId" id="componentId" list="items" listKey="id"
							listValue="name" headerKey="0" headerValue="ALL" value="componentId"
							theme="simple">
						</s:select>
					  <button type="submit" class="btn btn-success btn-small">查询</button>
					</form></div></div>
			<table class="table table-striped table-bordered table-condensed table-hover">
				 <thead><tr>
					<th width="6%">序号</th>
					<th width="20%">逃生楼梯编号</th>
					<th width="10%">检测时间</th>
					<th width="54%">质量描述</th>
					<th width="10%">操作
					</th>
				</tr></thead><tbody>
				<s:iterator value="inspections" status="vs">
					<tr>
					<td><s:property value='#vs.index+1'/></td>
					<td><s:property value="componentName" /></td>
					<td>
						<s:date name="time" format="yyyy-MM-dd"/>
					</td>
					<td><s:property value="description" /></td>
					<td>
						<a class="btn btn-small btn-success" href="userEscapeInspectionDetail.do?inspectionId=<s:property value="id"/>&index=<s:property value="index"/>&tunnelId=<s:property value="tunnelId"/>">详情</a>
					</td>
					</tr>
				</s:iterator></tbody>
			</table>
			<div class="pagination text-center">
			  <ul>
			  	<li><a href="#">共${totalSize}记录&${totalPages}页</a></li>
			    <li><a href="?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=1">首页</a></li>
				    <s:iterator id="item" value="pageIndexs" >
				    	<s:if test="${item == index }">
							<li class="disabled"><a href="?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=${item}">${item}</a></li>
				    	</s:if>
				    	<s:else>
							<li><a href="?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=${item}">${item}</a></li>
				    	</s:else>
				    </s:iterator>
			    <li><a href="?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=${totalPages}">末页</a></li>
			  </ul>
			</div></div>
    <%@include file="./../../Foot.jsp"%>
  </div>
</body>
</html>
