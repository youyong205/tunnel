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
	function gid(id) { return document.getElementById ? document.getElementById(id) : null; }
	function timeDesc() {
		if (all <= 0) {
			self.location = "${requestUrl}";
		}
		var obj = gid("tS");
		if (obj) obj.innerHTML = all + " 秒后";
		all--;
	}
	var all = 3;
	if (all > 0) window.setInterval("timeDesc();", 1000);
</script>
</head>
<body>
  <%@include file="./../Head.jsp"%>
  </br>
  </br>
  <div class="container-fluid background-body" >
  	 <div class="row-fluid">
      <%@include file="./../Menu.jsp"%>
      <div class="span10 text-center">
	  	<div>
	  		<a class='btn btn-primary' href="tunnelList.do">返回主页</a>
	  		<a class='btn btn-danger' href="${requestUrl}"><span id="tS">4 秒后</span>返回上次请求页面</a>
	  	</div>
	  </div></div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
