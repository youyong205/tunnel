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
		$('#mailRecordList').addClass('active');
		$('#type').val("<s:property value="type"/>");
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
			<li class="active">系统邮件</li>
		</ul>
	</div>
	<div class="row-fluid">
		<div class='span2'>
			<%@include file="./../TunnelMenu.jsp"%>
		</div>
		<div class='span10'>
			<form class="text-right form-inline margin-buttom" action="userMailRecordList.do" method="post">
				邮件类型<select name="type" id="type">
					<option value="0">ALL</option>
					<option value="1">日常邮件</option>
					<option value="2">告警邮件</option>
				</select>
				隧道
				 <s:select name="tunnelId" id="tunnelSectionId" headerKey="0"
							headerValue="ALL"
						list="tunnels" listKey="id" listValue="name" value="tunnelId"  theme="simple" >
				</s:select>
		       <button type="submit" class="btn btn-success btn-small">查询</button>
			</form>
			<table class="table table-striped table-bordered table-condensed table-hover">
				 <thead><tr>
					<th width="8%">序号</th>
					<th width="15%">隧道名称</th>
					<th width="15%">邮件类型</th>
					<th width="15%">发送时间</th>
					<th width="37%">邮件标题</th>
					<th width="10%">操作</th>
				</tr></thead><tbody>
				<s:iterator value="mailRecords" status="vs">
					<tr>
					<td><s:property value='#vs.index+1'/></td>
					<td><s:property value="tunnel.name" /></td>
					<td>
						<s:if test="type==1">
							日常邮件
						</s:if>
						<s:else>
							告警邮件
						</s:else>
					</td>
					<td><s:date name="time" format="yyyy-MM-dd"/></td>
					<td><s:property value="title" /></td>
					<td>
						<a class="btn btn-small btn-success" href="userMailRecordDetail.do?mailRecordId=<s:property value="id"/>&index=<s:property value="index"/>">详情</a>
					</td>
					</tr>
				</s:iterator></tbody>
			</table>
			<div class="pagination text-center">
			  <ul>
			  	<li><a href="#">共${totalSize}记录&${totalPages}页</a></li>
			    <li><a href="?tunnelId=<s:property value="tunnelId"/>&type=<s:property value="type"/>&index=1">首页</a></li>
				    <s:iterator id="item" value="pageIndexs" >
				    	<s:if test="${item == index }">
							<li class="disabled"><a href="?tunnelId=<s:property value="tunnelId"/>&type=<s:property value="type"/>&index=${item}">${item}</a></li>
				    	</s:if>
				    	<s:else>
							<li><a href="?tunnelId=<s:property value="tunnelId"/>&type=<s:property value="type"/>&index=${item}">${item}</a></li>
				    	</s:else>
				    </s:iterator>
			    <li><a href="?tunnelId=<s:property value="tunnelId"/>&type=<s:property value="type"/>&index=${totalPages}">末页</a></li>
			  </ul>
			</div>
		</div>
	</div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
