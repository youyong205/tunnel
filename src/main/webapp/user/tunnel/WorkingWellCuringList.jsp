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
<script type="text/javascript" src="js/workingWell.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#tunnelList').addClass('active');
		$('#workingWell').addClass('active');
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
			<li><a href="userWorkingWellList.do?tunnelId=<s:property value="tunnelId"/>">工作井</a><span class="divider">/</span></li>
			<li class="active">养护记录</li>
		</ul>
	</div>
	<div class='row'>
		<div class='span2'>
			<%@include file="./../TunnelMenu.jsp"%>
		</div>
		<div class='span10'>
			<div class="row-fluid">
			    <div class="span6">
					<a href="userWorkingWellList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small  btn-primary btn-info">工作井列表</a>
    	  			<a href="userWorkingWellInspectionList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small btn-primary btn-info">质量检查</a>
      				<a href="userWorkingWellCuringList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small  btn-primary btn-success ">养护记录</a>
      			</div>
				<div class="span6"><form class="text-right form-inline margin-buttom" action="userWorkingWellCuringList.do" method="post">
				  隧道
				  <s:select name="tunnelId" id="tunnelId"  onchange="tunnelChanged(false)"
						list="tunnels" listKey="id" listValue="name" value="tunnelId" theme="simple" >
				  </s:select>
				  工作井
				  <s:select name="componentId" id="componentId"
				  		headerKey="0" headerValue="ALL"
						list="items" listKey="id" listValue="name" value="componentId" theme="simple" >
				  </s:select>
				  <button type="submit" class="btn btn-success btn-small">查询</button>
				</form></div></div>
			<table class="table table-striped table-bordered table-condensed table-hover">
				 <thead><tr>
					<th width="6%">序号</th>
					<th width="20%">工作井编号</th>
					<th width="10%">养护时间</th>
					<th width="50%">处理措施</th>
					<th width="10%">操作</th>
				</tr></thead><tbody>
				<s:iterator value="curings" status="vs">
					<tr>
					<td><s:property value='#vs.index+1'/></td>
					<td><s:property value="componentName" /></td>
					<td>
						<s:date name="time" format="yyyy-MM-dd"/>
					</td>
					<td><s:property value="action" /></td>
					<td>
					<t:privilege res="工作井养护记录模块:详情">
						<a class="btn btn-small btn-success" href="userWorkingWellCuringDetail.do?curingId=<s:property value="id"/>&index=<s:property value="index"/>&tunnelId=<s:property value="tunnelId"/>">详情</a>
					</t:privilege>
					</td>
					</tr>
				</s:iterator></tbody>
			</table>
			<div class="pagination text-center">
			  <ul>
			  	<li><a href="#">共${totalSize}记录&${totalPages}页</a></li>
			    <li><a href="?tunnelId=<s:property value="tunnelId"/>&index=1">首页</a></li>
				    <s:iterator id="item" value="pageIndexs" >
				    	<s:if test="${item == index }">
							<li class="disabled"><a href="?tunnelId=<s:property value="tunnelId"/>&index=${item}">${item}</a></li>
				    	</s:if>
				    	<s:else>
							<li><a href="?tunnelId=<s:property value="tunnelId"/>&index=${item}">${item}</a></li>
				    	</s:else>
				    </s:iterator>
			    <li><a href="?tunnelId=<s:property value="tunnelId"/>&index=${totalPages}">末页</a></li>
			  </ul>
			</div>
		</div>
	</div>
     
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
