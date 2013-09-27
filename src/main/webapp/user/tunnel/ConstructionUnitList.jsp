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
		$('#constructionUnitList').addClass('active');
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
			<li><a href="userConstructionUnitList.do?tunnelId=<s:property value="tunnelId"/>">施工单位</a><span class="divider">/</span></li>
			<li class="active">详细信息</li>
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
					<th width="82%">名称</th>
					<th width="10%">操作
					</th>
				</tr></thead><tbody>
				<s:iterator value="constructionUnits" status="vs">
					<tr>
					<td><s:property value='#vs.index+1'/></td>
					<td><s:property value="name" /></td>
					<td>
						<a class="btn btn-small btn-success" href="userConstructionUnitDetail.do?constructionUnitId=<s:property value="id"/>&index=<s:property value="index"/>">详情</a>
					</td>
					</tr>
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
