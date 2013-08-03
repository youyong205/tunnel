<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<!DOCTYPE html>
<head>
<title>软土盾构隧道后台管理系统</title>

<link href="css/bootstrap.min.css" rel="stylesheet" user="screen">
<link rel='stylesheet' type='text/css' href='css/admin.css' />
</head>
<body>
  <%@include file="./Head.jsp"%>
  </br>
  </br>
  <div class="container-fluid">
    <div class="row-fluid">
      <%@include file="./Menu.jsp"%>
      <div class="span10">
        <h2 class="text-center"><img src="img/error404.png"></h2>
        <s:property value="exception.message"/>
         <s:property value="exceptionStack"/>
      </div>
    </div>
    <%@include file="./Foot.jsp"%>
  </div>
</body>
</html>
