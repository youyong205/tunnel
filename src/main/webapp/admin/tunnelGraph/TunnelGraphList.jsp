<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/struts-privilege.tld"%>

<!DOCTYPE html><html>
<head>
<title>软土盾构隧道后台管理系统</title>

<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css" >
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.metadata.js"></script>
<script type="text/javascript" src="js/validate.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#tunnelGraphList').addClass('active');
		var lineType = '<s:property  value="lineType"/>';
		$('#lineType').val(lineType);
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
  <div class="container-fluid background-body" >
    <div class="row-fluid">
      <%@include file="./../Menu.jsp"%>
      <div class="span10">
      		<form class="text-right form-inline margin-buttom" action="tunnelGraphList.do" method="post">
			  隧道
			  <s:select name="tunnelId" id="tunnelId"
					list="tunnels" listKey="id" listValue="name" value="tunnelId" theme="simple" >
			 </s:select>
			 线路类型<select name="lineType" id="lineType">
			  	 <option value="上行">上行</option>
			  	 <option value="下行">下行</option>
			  </select>
			  <button type="submit" class="btn btn-success btn-small">查询</button>
			</form>
			<table class="table table-striped table-bordered table-condensed table-hover">
				 <thead><tr>
					<th width="5%">序号</th>
					<th width="20%">隧道名称</th>
					<th width="20%">上下行</th>
					<th width="15%">类型</th>
					<th width="15%">顺序号</th>
					<th width="25%">操作
					<t:privilege res="隧道画图模块:新增">
					<a class="space btn btn-small btn-info" href="tunnelGraphAdd.do?index=<s:property value="index"/>&tunnelId=<s:property value="tunnelId"/>" >新增</a>
					</t:privilege>
					</th>
				</tr></thead><tbody>
				<s:iterator value="tunnelGraphs" status="vs">
					<tr>
					<td><s:property value='#vs.index+1'/></td>
					<td><s:property value="tunnel.name" /></td>
					<td><s:property value="lineType" /></td>
					<td>
						<s:if test="componentType==1">盾构段</s:if>
						<s:elseif test="componentType==2">工作井</s:elseif>
						<s:elseif test="componentType==3">敞开段</s:elseif>
						<s:elseif test="componentType==4">暗埋段</s:elseif>
					</td>
					<td><s:property value="indexNumber"/></td>
					<td>
						<a class="btn btn-small btn-info" href='<s:property value="url"/>'>构件详情</a>
					<t:privilege res="隧道画图模块:详情">
						<a class="btn btn-small btn-success" href="tunnelGraphDetail.do?tunnelGraphId=<s:property value="id"/>&index=<s:property value="index"/>&tunnelId=<s:property value="tunnelIndexId"/>">详情</a>
					</t:privilege>
					<t:privilege res="隧道画图模块:编辑">
						<a class="btn btn-small btn-primary" href="tunnelGraphUpdate.do?tunnelGraphId=<s:property value="id"/>&index=<s:property value="index"/>&tunnelId=<s:property value="tunnelIndexId"/>">编辑</a>
					</t:privilege>
					<t:privilege res="隧道画图模块:删除">
						<a class="delete btn  btn-small btn-danger" href="tunnelGraphDelete.do?tunnelGraphId=<s:property value="id"/>&index=<s:property value="index"/>&tunnelId=<s:property value="tunnelIndexId"/>">删除</a>
					</t:privilege>
					</td>
					</tr>
				</s:iterator></tbody>
			</table>
			<div class="pagination text-center">
			  <ul>
			  	<li><a href="#">共${totalSize}记录&${totalPages}页</a></li>
			    <li><a href="?index=1&tunnelId=<s:property value="tunnelId"/>&lineType=<s:property value="lineType"/>"">首页</a></li>
				    <s:iterator id="item" value="pageIndexs" >
				    	<s:if test="${item == index }">
							<li class="disabled"><a href="?index=${item}&tunnelId=<s:property value="tunnelId"/>&lineType=<s:property value="lineType"/>"">${item}</a></li>
				    	</s:if>
				    	<s:else>
							<li><a href="?index=${item}&tunnelId=<s:property value="tunnelId"/>&lineType=<s:property value="lineType"/>"">${item}</a></li>
				    	</s:else>
				    </s:iterator>
			    <li><a href="?index=${totalPages}&tunnelId=<s:property value="tunnelId"/>&lineType=<s:property value="lineType"/>"">末页</a></li>
			  </ul>
			</div>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
