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
<script type="text/javascript" src="js/highcharts.js"></script>
<script type="text/javascript" src="js/liningRingMeasuring.js"></script>
<script type="text/javascript" src="js/bootstrap.datetimepicker.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#tunnelSectionList').addClass('active');
		$('#seepage').addClass('active');
		var chart = <s:property value='generalChart.jsonString' escape="false"/>;
		graphChart("chart1","上行线渗漏水趋势图","测量值","(mm)",chart);

		var chart2 = <s:property value='generalChart2.jsonString' escape="false"/>;
		graphChart("chart2","下行线渗漏水趋势图","测量值","(mm)",chart2);
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
				<li class="active"><a
					href='userTunnelSectionList.do?tunnelId=<s:property value="tunnelId"/>'>盾构段</a><span
					class="divider">/</span></li>
				<li class="active">渗漏水</li>
			</ul>
		</div>
		<div class="row">
			<div class="span2">
				<%@include file="./../TunnelSectionMenu.jsp"%>
			</div>
			<div class="span10" style='margin-left:10px;'>
				<form class="text-right form-inline margin-buttom" method="get">
					<strong>选择隧道</strong>
					<s:select name="tunnelId" id="tunnelId" onchange="tunnelChangedExcluedeAll()"
						list="tunnels" listKey="id" listValue="name" value="tunnelId"
						theme="simple">
					</s:select>
					<strong>选择盾构段</strong>
					<s:select name="tunnelSectionId" id="tunnelSectionId"
						list="tunnelSections" listKey="id" listValue="name"
						value="tunnelSectionId" theme="simple">
					</s:select>
					<button type="submit" class="btn btn-success btn-small">查询</button>
				</form>
				<div id="chart1" style="min-width: 400px; width:90%; height:400px; margin: 0 auto"></div>
				<div id="chart2" style="min-width: 400px; width:90%; height:400px; margin: 0 auto"></div>
			</div>
		</div>
		<%@include file="./../Foot.jsp"%>
     
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
