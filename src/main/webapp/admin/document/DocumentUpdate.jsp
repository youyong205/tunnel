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
	$('#documentList').addClass("active");
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
		<table  class="table table-striped table-bordered table-condensed">
			<tr>
				<th colspan='2'><h4 class="text-info text-center">操作日志详细信息</h4></th>
			</tr>
			<tr>
				<td width="20%" style="text-align:right;"><strong class="text-success">模块名称</strong></td>
				<td><s:property value='document.module'/></td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right;"><strong class="text-success">文档名称</strong></td>
				<td><s:property value='document.name'/></td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right;"><strong class="text-success">文档类型</strong></td>
				<td><s:property value='document.type'/></td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right;"><strong class="text-success">文档相对路径</strong></td>
				<td><s:property value='document.path'/></td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right;"><strong class="text-success">文档绝对路径</strong></td>
				<td><s:property value='document.absolutePath'/></td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right;"><strong class="text-success">文档描述</strong></td>
				<td><textarea type="text" rows="5" cols="80"  name="document.des"><s:property value='document.des'/></textarea></td>
				</td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right;"><strong class="text-success">上传时间</strong></td>
				<td><s:date name="document.creationDate" format="yyyy/MM/dd HH:mm" /></td>
			</tr>
			</table>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>



