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

<script type="text/javascript">
	$(document).ready(function() {
		$('#mailRecordList').addClass('active');
		$('#type').val("<s:property value="type"/>");
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
			<form class="text-right form-inline margin-buttom" action="mailRecordList.do" method="post">
				邮件类型<select name="type" id="type">
					<option value="0">ALL</option>
					<option value="1">告警邮件</option>
					<option value="2">日常邮件</option>
				</select>
				隧道<s:select name="tunnelId" id="tunnelSectionId"
						headerKey="0" headerValue="ALL"
						list="tunnels" listKey="id" listValue="name" value="tunnelId"  theme="simple" >
				</s:select>
		       <button type="submit" class="btn btn-success btn-small">查询</button>
			</form>
			<table class="table table-striped table-bordered table-condensed table-hover">
				<thead><tr>
					<th width="8%">序号</th>
					<th width="15%">隧道名称</th>
					<th width="10%">邮件类型</th>
					<th width="10%">发送时间</th>
					<th width="42%">邮件标题</th>
					<th width="15%">操作</th>
				</tr></thead><tbody>
				<s:iterator value="mailRecords" status="vs">
					<tr>
					<td><s:property value='#vs.index+1'/></td>
					<td><s:property value="tunnel.name" /></td>
					<td>
						<s:if test="type==2">
							日常邮件
						</s:if>
						<s:else>
							告警邮件
						</s:else>
					</td>
					<td><s:date name="time" format="yyyy-MM-dd"/></td>
					<td><s:property value="title" /></td>
					<td>
						<t:privilege res="邮件模块:详情">
							<a class="btn btn-small btn-success" href="mailRecordUpdate.do?mailRecordId=<s:property value="id"/>&index=<s:property value="index"/>">详情</a>
						</t:privilege>
						<t:privilege res="邮件模块:编辑">
							<a class="btn btn-small btn-primary" href="mailRecordUpdate.do?mailRecordId=<s:property value="id"/>&index=<s:property value="index"/>">编辑</a>
						</t:privilege>
						<t:privilege res="邮件模块:删除">
							<a class="delete btn  btn-small btn-danger" href="mailRecordDelete.do?mailRecordId=<s:property value="id"/>&index=<s:property value="index"/>">删除</a>
						</t:privilege>
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
