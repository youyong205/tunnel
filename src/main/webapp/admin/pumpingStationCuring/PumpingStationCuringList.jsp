<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/struts-privilege.tld"%>

<!DOCTYPE html><html>
<head>
<title>软土盾构质量检测后台管理系统</title>

<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css" >
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.metadata.js"></script>
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript" src="js/pumpingStation.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#pumpingStationList').addClass('active');
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
		      		<t:privilege res="泵房模块:详情">
						<a href="pumpingStationList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="parentTunnelSectionId"/>" class="btn btn-small  btn-primary btn-info">基本信息</a>
	    	  		</t:privilege>
	    	  		<t:privilege res="泵房质量检测模块:详情">
	    	  			<a href="pumpingStationInspectionList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="parentTunnelSectionId"/>" class="btn btn-small btn-primary btn-info">质量检查</a>
					</t:privilege>
					<t:privilege res="泵房养护记录模块:详情">
	      				<a href="pumpingStationCuringList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="parentTunnelSectionId"/>" class="btn btn-small  btn-primary btn-success">养护记录</a>
      				</t:privilege>
      			</div>
      			<div class="span8">
      				<form class="text-right form-inline margin-buttom" action="pumpingStationCuringList.do" method="post">
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
						<strong>泵房</strong>
						<s:select name="componentId" id="componentId" list="items" listKey="id"
							listValue="name" headerKey="0" headerValue="ALL" value="componentId"
							theme="simple">
						</s:select>
					  <button type="submit" class="btn btn-success btn-small">查询</button>
					</form>
      			</div>
      		</div>
			<table class="table table-striped table-bordered table-condensed table-hover">
				 <thead><tr>
					<th width="5%">序号</th>
					<th width="15%">泵房名称</th>
					<th width="7%">养护时间</th>
					<th width="13%">相关附件</th>
					<th width="45%">养护措施</th>
					<th width="15%">操作
					<t:privilege res="泵房质量检测模块:新增">
						<a class="space btn btn-small btn-info" href="pumpingStationCuringAdd.do?index=<s:property value="index"/>&tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="parentTunnelSectionId"/>">新增</a>
					</t:privilege>
					</th>
				</tr></thead><tbody>
				<s:iterator value="curings" status="vs">
					<tr>
					<td><s:property value='#vs.index+1'/></td>
					<td><s:property value="componentName" /></td>
					<td>
						<s:date name="time" format="yyyy-MM-dd"/>
					</td>
					<td>
						<a href="documentDownload.do?documentId=<s:property value="document.id"/>"><s:property value="document.name"/></a>
					</td>
					<td><s:property value="action" /></td>
					<td>
					<t:privilege res="泵房质量检测模块:详情">
						<a class="btn btn-small btn-success" href="pumpingStationCuringDetail.do?curingId=<s:property value="id"/>&index=<s:property value="index"/>&tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="parentTunnelSectionId"/>">详情</a>
					</t:privilege>
					<t:privilege res="泵房质量检测模块:编辑">
						<a class="btn btn-small btn-primary" href="pumpingStationCuringUpdate.do?curingId=<s:property value="id"/>&index=<s:property value="index"/>&tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="parentTunnelSectionId"/>">编辑</a>
					</t:privilege>
					<t:privilege res="泵房质量检测模块:删除">
						<a class="delete btn  btn-small btn-danger" href="pumpingStationCuringDelete.do?curingId=<s:property value="id"/>&index=<s:property value="index"/>&tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="parentTunnelSectionId"/>">删除</a>
					</t:privilege>
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
			</div>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
