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
		$('#tunnelSectionList').addClass('active');
	});
</script>
</head>
<body>
  <%@include file="../Head.jsp"%>
  </br>
  </br>
  <div class="container-fluid">
    <div class="row-fluid">
      <%@include file="./../Menu.jsp"%>
      <div class="span10">
      		<form class="text-right form-inline margin-buttom" action="userTunnelSectionList.do" method="post">
			  隧道
			<s:select name="tunnelId" id="tunnelId" onchange="tunnelChangedExcluedeAll()"
					list="tunnels" listKey="id" listValue="name" value="tunnelId" theme="simple" >
			</s:select>
			<strong>选择盾构段</strong>
			<s:select name="tunnelSectionId" id="tunnelSectionId"
				list="tunnelSections" listKey="id" listValue="name"
				headerKey="0" headerValue="ALL"
				value="tunnelSectionId" theme="simple" >
			</s:select> 
			<button type="submit" class="btn btn-success btn-small">查询</button>
			</form>
			
			
      </div>
    </div>
    <%@include file="../Foot.jsp"%>
  </div>
</body>
</html>
