<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>

<!DOCTYPE html>
<head>
<title>软土盾构隧道后台管理系统</title>

<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css">
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script src="js/jquery.js" type="text/javascript" ></script>
<script src="js/bootstrap.min.js" type="text/javascript" ></script>
<script type="text/javascript" src="js/validate.js"></script>
<script src="js/jquery.validate.min.js" type="text/javascript" ></script>
<script type="text/javascript" src="js/jquery.metadata.js"></script>

</head>
<body>
	 <div class="container">
		<%@include file="./Head.jsp"%>
	  <div class="row-fluid">
      	<div class="span12">
      		<div>
		  		<a class='btn btn-primary' href="tunnelList.do">返回主页</a>
		  	</div>
        	<h2 class="text-center"><img src="img/success.jpeg"></h2>
      </div>
    </div>
    <%@include file="./Foot.jsp"%>
  </div>
</body>
</html>
