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
		$('#baseInfo').addClass('active');
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
			<li class="active"><a href='userTunnelList.do'>隧道列表</a><span class="divider">/</span></li>
			<li class="active">隧道详情</li>
		</ul>
	</div>
     <div class='row'>
		<div class='span2'>
			<%@include file="./../TunnelMenu.jsp"%>
		</div>
      <div class='span10'>
      <h4 class="text-center text-error">隧道服务状态</h4>
      <table style='margin-bottom:0px;' class="table table-striped table-bordered table-condensed table-hover">
		<s:iterator value="svgs" status="vs">
			<tr>
				<td  style="padding:6px 4px 0px 4px;"><s:property value="value" escape="false" /></td></tr>
		</s:iterator>
		</table>
		<br/>
		 
		<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<input readonly type="hidden" name="tunnel.id" value="<s:property value="tunnel.id"/>" />
						<input readonly type="hidden" name="index" value="<s:property value="index"/>"/>
					</tr>
					<tr>
						<td width="40%" style="text-align:right;"><strong class="text-success">名称</strong></td>
						<td><s:property value='tunnel.name'/></td>
					</tr>
					<tr>
						<td width="40%" style="text-align:right;"><strong class="text-success">类型</strong></td>
						<td>
							<s:if test="tunnel.type==1">地铁隧道</s:if>
							<s:elseif test="tunnel.type==2">道路隧道</s:elseif>
							<s:elseif test="tunnel.type==3">市政隧道</s:elseif>
						</td>
					</tr>
					<tr>
						<td width="40%" style="text-align:right;"><strong class="text-success">服役状态</strong></td>
						<td><s:property value='tunnel.state'/></td>
					</tr>	
					
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">邮件通知人</strong></td>
						<td><s:property value='tunnel.email'/></td>
					</tr>				
					<tr>
						<td width="40%" style="text-align:right;"><strong class="text-success">备注信息</strong></td>
						<td><s:property value='tunnel.des'/></td>
					</tr>
					</table></div></div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>


