<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/struts-privilege.tld"%>

<!DOCTYPE html><html>
<head>
<title>软土盾构养护记录后台管理系统</title>

<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css" >
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.metadata.js"></script>
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript" src="js/contactChannel.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#contactChannelList').addClass('active');
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
			<div class="row-fluid">
			    <div class="span6">
		      		<t:privilege res="联络通道模块:详情">
					<a href="contactChannelList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small  btn-primary btn-info">基本信息</a>
	    	  		</t:privilege>
	    	  		<t:privilege res="联络通道质量检测模块:详情">
	    	  		<a href="contactChannelInspectionList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small btn-primary btn-info btn-info" >质量检查</a>
					</t:privilege>
					<t:privilege res="联络通道养护记录模块:详情">
	      			<a href="contactChannelCuringList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small  btn-primary btn-success">养护记录</a>
      				</t:privilege>
      			</div>
      			<div class="span6"><form class="text-right form-inline margin-buttom" action="contactChannelCuringList.do" method="post">
			  隧道
			  <s:select name="tunnelId" id="tunnelId"  onchange="tunnelChanged(false)"
					list="tunnels" listKey="id" listValue="name" value="tunnelId" theme="simple" >
			  </s:select>
			  联络通道
			  <s:select name="componentId" id="componentId"
			  		headerKey="0" headerValue="ALL"
					list="items" listKey="id" listValue="name" value="componentId" theme="simple" >
			  </s:select>
			  <button type="submit" class="btn btn-success btn-small">查询</button>
			</form></div></div>
			<table class="table table-striped table-bordered table-condensed table-hover">
				 <thead><tr>
					<th width="5%">序号</th>
					<th width="20%">联络通道编号</th>
					<th width="10%">养护时间</th>
					<th width="50%">处理措施</th>
					<th width="15%">操作
					<t:privilege res="联络通道养护记录模块:新增">
						<a class="space btn btn-small btn-info" href="contactChannelCuringAdd.do?index=<s:property value="index"/>&tunnelId=<s:property value="tunnelId"/>" >新增</a>
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
					<td><s:property value="action" /></td>
					<td>
					<t:privilege res="联络通道养护记录模块:详情">
						<a class="btn btn-small btn-success" href="contactChannelCuringDetail.do?curingId=<s:property value="id"/>&index=<s:property value="index"/>&tunnelId=<s:property value="tunnelId"/>">详情</a>
					</t:privilege>
					<t:privilege res="联络通道养护记录模块:编辑">
						<a class="btn btn-small btn-primary" href="contactChannelCuringUpdate.do?curingId=<s:property value="id"/>&index=<s:property value="index"/>&tunnelId=<s:property value="tunnelId"/>">编辑</a>
					</t:privilege>
					<t:privilege res="联络通道养护记录模块:删除">
						<a class="delete btn  btn-small btn-danger" href="contactChannelCuringDelete.do?curingId=<s:property value="id"/>&index=<s:property value="index"/>&tunnelId=<s:property value="tunnelId"/>">删除</a>
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
