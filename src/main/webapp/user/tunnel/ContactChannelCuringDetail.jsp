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
		$('#contactChannel').addClass('active');
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
			<li><a href="userContactChannelList.do?tunnelId=<s:property value="tunnelId"/>">联络通道</a><span class="divider">/</span></li>
			<li class="active"><a href="userContactChannelCuringList.do?tunnelId=<s:property value="tunnelId"/>">养护记录</a><span class="divider">/</span></li>
			<li class="active">详细信息</li>
		</ul>
	</div>
	<div class="row-fluid">
		<div class='span2'>
			<%@include file="./../TunnelMenu.jsp"%>
		</div>
		<div class='span10'>
			<div class="row-fluid">
			    <div class="span12">
					<a href="userContactChannelList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small  btn-primary btn-info">联络通道列表</a>
    	  			<a href="userContactChannelInspectionList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small btn-primary btn-info">质量检查</a>
      				<a href="userContactChannelCuringList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small  btn-primary btn-success ">养护记录</a>
      			</div>
			</div>
			
			<%@include file="./../../admin/curing/CuringDetail.jsp"%>
		</div>
	</div>
     
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
