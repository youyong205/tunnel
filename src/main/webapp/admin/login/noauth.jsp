<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>

<!DOCTYPE html><html>
<head>
<title>软土盾构隧道后台管理系统</title>

<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css">
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script src="js/jquery.js" type="text/javascript" ></script>
<script src="js/bootstrap.min.js" type="text/javascript" ></script>
<script type="text/javascript" src="js/validate.js"></script>
<script src="js/jquery.validate.min.js" type="text/javascript" ></script>
<script type="text/javascript" src="js/jquery.metadata.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#form").validate();
	});
</script>
</head>
<body>
  <%@include file="./../Head.jsp"%>
  </br>
  </br>
  <div class="container-fluid text-center">
  	<form action="login.do" id="form" method="post">
  		<s:actionerror/>
  		<h2 class='text-error'>您没有足够权限，请向系统管理员申请！</h2>
	</form>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
