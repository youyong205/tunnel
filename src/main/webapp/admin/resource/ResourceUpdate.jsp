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
	$('#resourceList').addClass("active");
	$('#resource_resource').val("<s:property value="resource.resource"/>");
	$("#form").validate();
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
			<form action="resourceUpdateSubmit.do" id="form" method="post">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='2'><h4 class="text-info text-center">编辑资源信息</h4></th>
						<input type="hidden" name="resource.id" value="<s:property value="resource.id"/>"/>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">模块名</strong></td>
						<td>
							<s:select name="resource.module" id="module"
									list="modules"  value="resource.module" theme="simple" >
							 </s:select>
						</td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">资源名</strong></td>
						<td>
							<input type="text" size="60" name="resource.name" value="<s:property value="resource.name"/>"  class="{required:true,maxlength:64}"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">简介</strong></td>
						<td colspan='3'><textarea type="text" rows="5" cols="80"  name="resource.des" class="{maxlength:512}"><s:property value="resource.name"/></textarea></td>
						
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



