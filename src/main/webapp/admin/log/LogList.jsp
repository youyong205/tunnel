<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>

<!DOCTYPE html><html>
<head>
<title>软土盾构隧道后台管理系统</title>

<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css" >
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#logList').addClass('active');
		$(".delete").bind("click", function() {
			return confirm("确定要删除此分类吗(不可恢复)？");
		});
		var operation = '<s:property  value="operation"/>';
		$('#operation').val(operation);
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
      		<form class="text-right form-inline margin-buttom" action="logSearch.do" method="post">
			  模块
			  	 <s:select name="module" id="module"
						list="modules"  value="module" theme="simple" >
				 </s:select>
			  类型<select name="operation" id="operation">
			  	 <option value="">ALL</option>
			  	 <option value="新增">新增</option>
			  	 <option value="修改">修改</option>
			  	 <option value="删除">删除</option>
			  </select>
			  <button type="submit" class="btn btn-success btn-small">查询</button>
			</form>
			<table class="table table-striped table-bordered table-condensed table-hover">
				 <thead><tr>
					<th width="5%">序号</th>
					<th width="30%">模块名称</th>
					<th width="25%">操作名称</th>
					<th width="20%">时间</th>
					<th width="15%">操作员</th>
					<th width="5%">操作</th>
				</tr></thead><tbody>
				<s:iterator value="logs" status="vs">
					<tr>
					<td><s:property value='#vs.index+1'/></td>
					<td><s:property value="module" /></td>
					<td><s:property value="operation" /></td>
					<td><s:date name="creationDate" format="yyyy/MM/dd HH:mm" /></td>
					<td><a href="userUpdate.do?userId=<s:property value="userId"/>"><s:property value="user.realName"/></a></td>
					<td><a class="btn btn-small btn-primary" href="logUpdate.do?logId=<s:property value="id"/>">详情</a></td>
					</tr>
				</s:iterator></tbody>
			</table>
			<div class="pagination text-center">
			  <ul>
			  	<li><a href="#">共${totalSize}记录，${totalPages}页</a></li>
			    <li><a href="?index=1&module=<s:property value="module"/>&operation=<s:property value="operation"/>">首页</a></li>
				    <s:iterator id="item" value="pageIndexs" >
				    	<s:if test="${item == index }">
							<li class="disabled"><a href="?index=${item}&module=<s:property value="module"/>&operation=<s:property value="operation"/>">${item}</a></li>
				    	</s:if>
				    	<s:else>
							<li><a href="?index=${item}&module=<s:property value="module"/>&operation=<s:property value="operation"/>">${item}</a></li>
				    	</s:else>
				    </s:iterator>
			    <li><a href="?index=${totalPages}&module=<s:property value="module"/>&operation=<s:property value="operation"/>">末页</a></li>
			  </ul>
			</div>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
