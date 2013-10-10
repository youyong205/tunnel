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
<script type="text/javascript" src="js/openSection.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#openSectionList').addClass('active');
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
			<div class="row-fluid"><div class="span6">
		      		<t:privilege res="敞开段模块:详情">
					<a href="openSectionList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small  btn-primary btn-info ">基本信息</a>
	    	  		</t:privilege>
	    	  		<t:privilege res="敞开段质量检测模块:详情">
	    	  		<a href="openSectionInspectionList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small btn-primary btn-success">质量检查</a>
					</t:privilege>
					<t:privilege res="敞开段养护记录模块:详情">
	      			<a href="openSectionCuringList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small  btn-primary btn-info">养护记录</a>
      				</t:privilege>
      			</div>
      			<div class="span6"><form class="text-right form-inline margin-buttom" action="openSectionInspectionList.do" method="post">
			  隧道
			  <s:select name="tunnelId" id="tunnelId"  onchange="tunnelChanged(false)"
					list="tunnels" listKey="id" listValue="name" value="tunnelId" theme="simple" >
			  </s:select>敞开段
			  <s:select name="componentId" id="componentId"
			  		headerKey="0" headerValue="ALL"
					list="items" listKey="id" listValue="name" value="componentId" theme="simple" >
			  </s:select>
			  <input type="hidden" name="process" value="true"/>
			  <button type="submit" class="btn btn-success btn-small">查询</button>
			</form></div></div>
			<table class="table table-striped table-bordered table-condensed table-hover">
				 <thead><tr>
					<th width="5%">序号</th>
					<th width="20%">敞开段编号</th>
					<th width="10%">检测时间</th>
					<th width="50%">质量描述</th>
					<th width="15%">操作
					<t:privilege res="敞开段质量检测模块:新增">
						<a class="space btn btn-small btn-info" href="openSectionInspectionAdd.do?index=<s:property value="index"/>&tunnelId=<s:property value="tunnelId"/>" >新增</a>
					</t:privilege>
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
					<t:privilege res="敞开段质量检测模块:详情">
						<a class="btn btn-small btn-success" href="openSectionInspectionDetail.do?inspectionId=<s:property value="id"/>&index=<s:property value="index"/>&tunnelId=<s:property value="tunnelId"/>">详情</a>
					</t:privilege>
					<t:privilege res="敞开段质量检测模块:编辑">
						<a class="btn btn-small btn-primary" href="openSectionInspectionUpdate.do?inspectionId=<s:property value="id"/>&index=<s:property value="index"/>&tunnelId=<s:property value="tunnelId"/>">编辑</a>
					</t:privilege>
					<t:privilege res="敞开段质量检测模块:删除">
						<a class="delete btn  btn-small btn-danger" href="openSectionInspectionDelete.do?inspectionId=<s:property value="id"/>&index=<s:property value="index"/>&tunnelId=<s:property value="tunnelId"/>">删除</a>
					</t:privilege>
					</td>
					</tr>
				</s:iterator></tbody>
			</table>
			<div class="pagination text-center">
			  <ul>
			  	<li><a href="#">共${totalSize}记录&${totalPages}页</a></li>
			    <li><a href="?index=1&tunnelId=<s:property value="tunnelId"/>">首页</a></li>
				    <s:iterator id="item" value="pageIndexs" >
				    	<s:if test="${item == index }">
							<li class="disabled"><a href="?index=${item}&tunnelId=<s:property value="tunnelId"/>">${item}</a></li>
				    	</s:if>
				    	<s:else>
							<li><a href="?index=${item}&tunnelId=<s:property value="tunnelId"/>">${item}</a></li>
				    	</s:else>
				    </s:iterator>
			    <li><a href="?index=${totalPages}&tunnelId=<s:property value="tunnelId"/>">末页</a></li>
			  </ul>
			</div>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
