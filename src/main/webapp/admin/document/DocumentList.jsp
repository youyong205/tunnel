<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/struts-privilege.tld"%>

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
		$('#documentList').addClass('active');
		$(".delete").bind("click", function() {
			return confirm("确定要删除此分类吗(不可恢复)？");
		});
		var module = '<s:property  value="module"/>';
		$('#module').val(module);
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
      		<form class="text-right form-inline margin-buttom" action="documentList.do" method="post">
			  模块
			  <s:select name="module" id="module"
						list="documentModules"  value="module" theme="simple" >
				 </s:select>
			  名称<input type="text" name="name" size="30" value='<s:property value="name"/>'/>
			  <button type="submit" class="btn btn-success btn-small">查询</button>
			</form>
			<table class="table table-striped table-bordered table-condensed table-hover">
				 <thead><tr>
					<th width="5%">序号</th>
					<th width="20%">模块名称</th>
					<th width="50%">文档名称</th>
					<th width="15%">时间</th>
					<th width="10%">操作</th>
				</tr></thead><tbody>
				<s:iterator value="documents" status="vs">
					<tr>
					<td><s:property value='#vs.index+1'/></td>
					<td><s:property value="module" /></td>
					<td>
						<s:property value="name" />
					</td>
					<td><s:date name="creationDate" format="yyyy/MM/dd HH:mm" /></td>
					<td>
						<t:privilege res="工程文档模块:详情">
							<a class="btn btn-small btn-primary" href="documentUpdate.do?documentId=<s:property value="id"/>">详情</a>
						</t:privilege>
						<t:privilege res="工程文档模块:下载">
							<a class="btn btn-small btn-primary" href="documentDownload.do?documentId=<s:property value="id"/>">下载</a>
						</t:privilege>
					</td>
					</tr>
				</s:iterator></tbody>
			</table>
			<div class="pagination text-center">
			  <ul>
			  	<li><a href="#">共${totalSize}记录，${totalPages}页</a></li>
			    <li><a href="?index=1&module=<s:property value="module"/>&name=<s:property value="name"/>">首页</a></li>
				    <s:iterator id="item" value="pageIndexs" >
				    	<s:if test="${item == index }">
							<li class="disabled"><a href="?index=${item}&module=<s:property value="module"/>&name=<s:property value="name"/>">${item}</a></li>
				    	</s:if>
				    	<s:else>
							<li><a href="?index=${item}&module=<s:property value="module"/>&name=<s:property value="name"/>">${item}</a></li>
				    	</s:else>
				    </s:iterator>
			    <li><a href="?index=${totalPages}&module=<s:property value="module"/>&name=<s:property value="name"/>">末页</a></li>
			  </ul>
			</div>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
