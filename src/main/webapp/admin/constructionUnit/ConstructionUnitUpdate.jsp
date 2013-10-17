<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<!DOCTYPE html><html>
<head>
<title>软土盾构施工单位后台管理系统</title>

<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css" >
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.metadata.js"></script>
<script type="text/javascript" src="js/validate.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$('#constructionUnitList').addClass("active");
	$('#constructionUnit_role').val("<s:property value="constructionUnit.role"/>");
	$("#form").validate();
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
			<form action="constructionUnitUpdateSubmit.do" id="form" method="post">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='2'><h4 class="text-info text-center">编辑施工单位信息</h4></th>
						<input type="hidden" name="constructionUnit.id" value="<s:property value="constructionUnit.id"/>" />
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">名称</strong></td>
						<td><input type="text" size="60" name="constructionUnit.name"  value="<s:property value='constructionUnit.name'/>"  class="{required:true,maxlength:128}"/></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">类型</strong></td>
						<td><input type="text" size="60" name="constructionUnit.type"  value="<s:property value='constructionUnit.type'/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">地址</strong></td>
						<td><input type="text" size="60" name="constructionUnit.address"  value="<s:property value='constructionUnit.address'/>" class="{required:true,maxlength:128}"/></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">施工人员</strong></td>
						<td><input type="text" size="60" name="constructionUnit.workers"  value="<s:property value='constructionUnit.workers'/>" class="{required:true,maxlength:128}"/></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">联系电话</strong></td>
						<td><input type="text" size="60" name="constructionUnit.phone"  value="<s:property value='constructionUnit.phone'/>" class="{required:true,maxlength:128}"/></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">备注信息</strong></td>
						<td><textarea type="text" rows="5" cols="80"  name="constructionUnit.des"  class="{maxlength:512}"><s:property value='constructionUnit.des'/></textarea></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:center;">
							<button  class="btn btn-small btn-primary" type="submit" >提交</button></td>
					</tr>
					</table>
			</form>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>



