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
		$('#tunnelList').addClass('active');
		$('#baseInfo').addClass('active');
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
			<li class="active">联络通道</li>
		</ul>
	</div>
	<div class='row'>
		<div class='span2'>
			<%@include file="./../TunnelMenu.jsp"%>
		</div>
      <div class='span10'> 
		<table class="table table-striped table-bordered table-condensed table-hover">
				 <thead><tr>
					<th width="8%">序号</th>
					<th width="20%">类型</th>
					<th width="20%">名称</th>
					<th width="27%">服役状态</th>
					<th>详情</th>
				</tr></thead><tbody>
				<s:iterator value="tunnels" status="vs">
					<tr>
					<td><s:property value='#vs.index+1'/></td>
					<td>
						<s:if test="type==1">地铁隧道</s:if>
						<s:elseif test="type==2">道路隧道</s:elseif>
						<s:elseif test="type==3">市政隧道</s:elseif>
					</td>
					<td><s:property value="name" /></td>
					<td><span class='level<s:property value="state"/>'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="state"/></span>
					</td>
					<td>
					<a class='btn btn-small btn-info' href='userTunnelDetail.do?tunnelId=<s:property value="id"/>&index=<s:property value="index"/>'>详情</a>
					<a class='btn btn-small btn-info' href='userTunnelSectionList.do?tunnelId=<s:property value="id"/>&index=<s:property value="index"/>'>盾构段</a>
					<a class='btn btn-small btn-info' href='userTunnelDetail.do?tunnelId=<s:property value="id"/>&index=<s:property value="index"/>'>服役状态</a>
					</td></tr>
				</s:iterator></tbody>
			</table>
			<div class="pagination text-center">
			  <ul>
			  	<li><a href="#">共${totalSize}记录&${totalPages}页</a></li>
			    <li><a href="?index=1">首页</a></li>
				    <s:iterator id="item" value="pageIndexs" >
				    	<s:if test="${item == index }">
							<li class="disabled"><a href="?index=${item}">${item}</a></li>
				    	</s:if>
				    	<s:else>
							<li><a href="?index=${item}">${item}</a></li>
				    	</s:else>
				    </s:iterator>
			    <li><a href="?index=${totalPages}">末页</a></li>
			  </ul>
			</div>
      </div>
      	</div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
