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
		$('#liningRingConstructionList').addClass('active');
		$('#rust').addClass('active');
		$('#datetimepicker1').datetimepicker();
		$('#datetimepicker2').datetimepicker();
		var data = <s:property value='lineChart.jsonString' escape="false"/>;
		
		graphLineChart("锈蚀趋势图","锈蚀面积","(m㎡)",data.series);
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
				href='userLiningRingConstructionList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>'>衬砌环列表</a><span
				class="divider">/</span></li>
			<li class="active"><a
				href='userLiningRingConstructionDetail.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="parentTunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>&index=<s:property value="index"/>'>基本信息</a><span
				class="divider">/</span></li>
			<li class="active">锈蚀</li>
		</ul>
	</div>
	<div class="row-fluid">
		<div class='span2'>
			<%@include file="./../LiningRingMenu.jsp"%>
		</div>
		<div class='span10'>
			<form id="form" class="text-right form-inline margin-buttom"  action="userRustQuery.do" method="post">
					<div>	<strong>从</strong>
						<div id="datetimepicker1" class="input-append date"><input name="start" class="input-small"  placeholder="开始时间" value='<s:date name="start" format="yyyy-MM-dd"/>'  class="{required:true,date:true}"
               data-format="yyyy-MM-dd" type="text"></input> <span class="add-on"> <i
               data-time-icon="icon-time" data-date-icon="icon-calendar"> </i></span>
               </div>
               <div id="datetimepicker2" class="input-append date">
		               <strong>到</strong> <input name="end" class="input-small" placeholder="结束时间"  value='<s:date name="end" format="yyyy-MM-dd"/>' class="{required:true,date:true}"
		               data-format="yyyy-MM-dd" type="text"></input> <span class="add-on"> <i
		               data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
		            </span></div></div>
						<div style="margin-top:5px;">
						<strong>&nbsp;&nbsp;隧道</strong>
						<s:select name="tunnelId" id="tunnelId"
							onchange="tunnelChanged(true,true)"  
							list="tunnels" listKey="id" listValue="name" 
							value="tunnelId" theme="simple" >
						</s:select>
						<strong>盾构段</strong>
						<s:select name="tunnelSectionId" id="tunnelSectionId"
							list="tunnelSections" listKey="id" listValue="name"
							onchange="tunnelSectionChanged(true)"
							value="tunnelSectionId" theme="simple" >
						</s:select>
						<strong>衬砌环</strong>
						<s:select name="liningRingConstructionId" id="liningRingConstructionId"
							list="liningRingConstructions" listKey="id" listValue="name"
							value="liningRingConstructionId" theme="simple" >
						</s:select> 
					  <button type="submit" class="btn btn-success btn-small">查询</button></div>
					</form>
			<div id="chart" style="min-width: 400px; width:90%; height:400px; margin: 0 auto"></div>
		</div>
	</div>
     
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
