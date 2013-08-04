<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/struts-privilege.tld"%>

<!DOCTYPE html><html>
<head>
<title>软土盾构逃生楼梯后台管理系统</title>

<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css" >
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.metadata.js"></script>
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript" src="js/tunnel.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#escapeList').addClass('active');
		$(".delete").bind("click", function() {
			return confirm("确定要删除此分类吗(不可恢复)？");
		});
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
      		<div class="row-fluid">
      			<div class="span4">
		      		<t:privilege res="逃生楼梯模块:详情">
					<a href="escapeList.do" class="btn btn-small  btn-primary btn-success">基本信息</a>
	    	  		</t:privilege>
	    	  		<t:privilege res="逃生楼梯质量检测模块:详情">
	    	  		<a href="escapeInspectionList.do" class="btn btn-small btn-primary btn-info">质量检查</a>
					</t:privilege>
					<t:privilege res="逃生楼梯养护记录模块:详情">
	      			<a href="escapeCuringList.do" class="btn btn-small  btn-primary btn-info">养护记录</a>
      				</t:privilege>
      			</div>
      			<div class="span8">
      				<form class="text-right form-inline margin-buttom" action="escapeList.do" method="post">
						<strong>选择隧道</strong>
						<s:select name="tunnelId" id="tunnelId"
							onchange="tunnelChanged()"  
							list="tunnels" listKey="id" listValue="name" 
							value="tunnelId" theme="simple" >
						</s:select>
						<strong>选择盾构段</strong>
						<s:select name="tunnelSectionId" id="tunnelSectionId"
							list="tunnelSections" listKey="id" listValue="name"
							headerKey="0" headerValue="ALL"
							value="tunnelSectionId" theme="simple" >
						</s:select> 
					  <button type="submit" class="btn btn-success btn-small">查询</button>
					</form>
      			</div>
      		</div>
			<table class="table table-striped table-bordered table-condensed table-hover">
				 <thead><tr>
					<th width="5%">序号</th>
					<th width="20%">逃生楼梯编号</th>
					<th width="10%">逃生楼梯类型</th>
					<th width="20%">施工开始时间</th>
					<th width="20%">施工结束时间</th>
					<th width="10%">设计制作文档</th>
					<th width="15%">操作
					<t:privilege res="逃生楼梯模块:新增">
						<a class="space btn btn-small btn-info" href="escapeAdd.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="parentTunnelSectionId"/>&index=<s:property value="index"/>" >新增</a>
					</t:privilege>
					</th>
				</tr></thead><tbody>
				<s:iterator value="escapes" status="vs">
					<tr>
					<td><s:property value='#vs.index+1'/></td>
					<td><s:property value="name" /></td>
					<td><s:property value="type" /></td>
					<td><s:property value="schedule.startTimeStr" /></td>
					<td><s:property value="schedule.endTimeStr" /></td>
					<td>
						<a href="documentDownload.do?documentId=<s:property value="document.id"/>"><s:property value="document.name"/></a>
					</td>
					<td>
					<t:privilege res="逃生楼梯模块:详情">
						<a class="btn btn-small btn-success" href="escapeDetail.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="parentTunnelSectionId"/>&escapeId=<s:property value="id"/>&index=<s:property value="index"/>">详情</a>
					</t:privilege>
					<t:privilege res="逃生楼梯模块:编辑">
						<a class="btn btn-small btn-primary" href="escapeUpdate.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="parentTunnelSectionId"/>&escapeId=<s:property value="id"/>&index=<s:property value="index"/>">编辑</a>
					</t:privilege>
					<t:privilege res="逃生楼梯模块:删除">
						<a class="delete btn  btn-small btn-danger" href="escapeDelete.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="parentTunnelSectionId"/>&escapeId=<s:property value="id"/>&index=<s:property value="index"/>">删除</a>
					</t:privilege>
					</td>
					</tr>
				</s:iterator></tbody>
			</table>
			<div class="pagination text-center">
			  <ul>
			  	<li><a href="#">共${totalSize}记录，${totalPages}页</a></li>
			    <li><a href="?index=1">首页</a></li>
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
			</div>
    </div>
  </div>
    <%@include file="./../Foot.jsp"%>
</body>
</html>
