<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>

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
		$('#tunnelList').addClass('active');
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
			<table class="table table-striped table-bordered table-condensed table-hover">
				 <thead><tr>
					<th width="5%">序号</th>
					<th width="85%">名称</th>
					<th width="10%">操作<a class="space btn btn-small btn-success" href="tunnelAdd.do?index=<s:property value="index"/>" >新增</a></th>
				</tr></thead><tbody>
				<s:iterator value="tunnels" status="vs">
					<tr>
					<td><s:property value='#vs.index+1'/></td>
					<td><s:property value="name" /></td>
					<td><a class="btn btn-small btn-primary" href="tunnelUpdate.do?tunnelId=<s:property value="id"/>&index=<s:property value="index"/>">编辑</a>
						<a class="delete btn  btn-small btn-danger" href="tunnelDelete.do?tunnelId=<s:property value="id"/>&index=<s:property value="index"/>">删除</a></td>
					</tr>
				</s:iterator></tbody>
			</table>
			<div class="pagination text-center">
			  <ul>
			  	<li><a href="#">共${totalSize}记录，${totalPages}页</a></li>
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
