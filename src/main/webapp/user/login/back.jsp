<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/struts-privilege.tld"%>

<!DOCTYPE html><html>
<head>
<title>隧道管理信息系统</title>

<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css" >
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.metadata.js"></script>
<script type="text/javascript" src="js/validate.js"></script>

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
  <div class="container">
	<%@include file="./../Head.jsp"%>
    <div class="row-fluid">
      <div class="span12"> 
		<div class='text-center'>
	  		<a class='btn btn-primary' href="userTunnelList.do">返回主页</a>
	  		<a class='btn btn-danger' href="${requestUrl}"><span id="tS">4 秒后</span>返回上次请求页面</a>
	  	</div>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
