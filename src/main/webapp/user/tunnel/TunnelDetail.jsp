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
     <div class="row-fluid">
		<div class='span2'>
			<%@include file="./../TunnelMenu.jsp"%>
		</div>
      <div class='span10'>
      <h4 class="text-center text-error">隧道服务状态</h4>
      
      <div class="row-fluid">
      	<div class="span7">
      	</div>
      	<div class="span5">
      		<svg width='100%' height='50' version='1.1' xmlns='http://www.w3.org/2000/svg'>"
					<text x="0" y="45" fill="black">盾构段</text>
					<rect x="50" y="30" width="50" height="20" style="fill:green;stroke:pink;stroke-width:2;opacity:0.9"></rect>
					<text x="110" y="45" fill="black">工作井</text>
					<rect x="160" y="0" width="20" height="50" style="fill:#F0F8FF;stroke:pink;stroke-width:2;opacity:0.9"></rect>
					<text x="190" y="45" fill="black">敞开段</text>
					<rect x="240" y="30" width="50" height="20" style="fill:#3366FF;stroke:pink;stroke-width:2;opacity:0.9"></rect>
					<text x="300" y="45" fill="black">暗埋段</text>
					<rect x="350" y="30" width="50" height="20" style="fill:#6666FF;stroke:pink;stroke-width:2;opacity:0.9"></rect>
			</svg>
      	</div>
      </div>
      <table style='margin-bottom:0px;' class="table table-striped table-bordered table-condensed">
		<s:iterator value="svgs" status="vs">
			<tr><td width="5%" style='vertical-align:middle'><span class='text-center' > <s:property value="key"/></span></td>
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
						<td width="35%" style="text-align:right;"><strong class="text-success">名称</strong></td>
						<td><s:property value='tunnel.name'/></td>
					</tr>
					<tr>
						<td width="35%" style="text-align:right;"><strong class="text-success">类型</strong></td>
						<td>
							<s:if test="tunnel.type==1">地铁隧道</s:if>
							<s:elseif test="tunnel.type==2">道路隧道</s:elseif>
							<s:elseif test="tunnel.type==3">市政隧道</s:elseif>
						</td>
					</tr>
					<tr>
						<td width="35%" style="text-align:right;"><strong class="text-success">服役状态</strong></td>
						<td><s:property value='tunnel.state'/></td>
					</tr>	
					
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">邮件通知人</strong></td>
						<td><s:property value='tunnel.email'/></td>
					</tr>				
					<tr>
						<td width="35%" style="text-align:right;"><strong class="text-success">备注信息</strong></td>
						<td><s:property value='tunnel.des'/></td>
					</tr>
					</table></div></div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>


