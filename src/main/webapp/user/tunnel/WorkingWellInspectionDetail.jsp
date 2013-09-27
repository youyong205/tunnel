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
	$(document).ready(function() {
		$('#tunnelList').addClass('active');
		$('#workingWell').addClass('active');
	});
</script>
</head>
<body>
  <div class="container">
	<%@include file="./../Head.jsp"%>
	<div>
		<ul class="breadcrumb">
			<li>当前位置：</li>
			<li>首页<span class="divider">/</span></li>
			<li><a href="userWorkingWellList.do?tunnelId=<s:property value="tunnelId"/>">工作井</a><span class="divider">/</span></li>
			<li class="active"><a href="userWorkingWellInspectionList.do?tunnelId=<s:property value="tunnelId"/>">养护记录</a><span class="divider">/</span></li>
			<li class="active">详细信息</li>
		</ul>
	</div>
	<div class='row'>
		<div class='span2'>
			<%@include file="./../TunnelMenu.jsp"%>
		</div>
		<div class='span10'>
			<div class="row-fluid">
			    <div class="span12">
					<a href="userWorkingWellList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small  btn-primary btn-info">工作井列表</a>
    	  			<a href="userWorkingWellInspectionList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small btn-primary btn-success ">质量检查</a>
      				<a href="userWorkingWellInspectionList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small  btn-primary  btn-info">养护记录</a>
      			</div>
			</div>
			
			<%@include file="./../../admin/inspection/InspectionDetail.jsp"%>
		</div>
	</div>
     
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
