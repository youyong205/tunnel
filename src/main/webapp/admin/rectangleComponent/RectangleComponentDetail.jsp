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
	$('#rectangleComponentList').addClass("active");
	$('#rectangleComponent_role').val("<s:property value="rectangleComponent.role"/>");
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
			<form action="rectangleComponentUpdateSubmit.do" id="form" method="post">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='2'><h4 class="text-info text-center">隧道信息</h4></th>
						<input type="hidden" name="rectangleComponent.id" value="<s:property value="rectangleComponent.id"/>" />
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">名称</strong></td>
						<td><input type="text" size="60" name="rectangleComponent.name" readonly value="<s:property value='rectangleComponent.name'/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">简介</strong></td>
						<td><textarea type="text" rows="5" cols="80"  name="rectangleComponent.des" readonly class="{maxlength:512}"><s:property value='rectangleComponent.des'/></textarea></td>
					</tr>
					</table>
			</form>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>



