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
<script type="text/javascript" src="js/tunnel.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#liningRingConstructionList').addClass('active');
		$('#liningRingBaseInfo').addClass('active');
	});
</script>
</head>
<body>
  <div class="container">
	<%@include file="./../../Head.jsp"%>
	<div>
		<ul class="breadcrumb">
			<li>当前位置：</li>
			<li>首页<span class="divider">/</span></li>
			<li><a href='userTunnelSectionList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>'>盾构段</a><span class="divider">/</span></li>
			<li><a href="userLiningRingConstructionList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>">衬砌环</a><span class="divider">/</span></li>
			<li class="active">基本信息</li>
		</ul>
	</div>
	<div class="row-fluid">
		<div class='span2'>
			<%@include file="./../../LiningRingMenu.jsp"%>
		</div>
		<div class='span10'>
			<div class="row-fluid">
			    <div class="span12">
					<a href="userLiningRingConstructionList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>" class="btn btn-small  btn-primary btn-success">衬砌环列表</a>
    	  			<a href="userLiningRingConstructionInspectionList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>" class="btn btn-small btn-primary btn-info">质量检查</a>
      				<a href="userLiningRingConstructionCuringList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>" class="btn btn-small  btn-primary btn-info">养护记录</a>
      			</div>
      		</div>
      			<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center">衬砌环信息详情</h4></th>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">隧道</strong></td>
						<td width="30%">
							<s:select name="liningRingConstruction.tunnelId" id="tunnelId"
								onchange="tunnelChanged()" 
								list="tunnels" listKey="id" listValue="name" 
								value="liningRingConstruction.tunnelId" theme="simple" >
							</s:select>
						</td>
						<td width="20%" style="text-align:right;"><strong class="text-success">盾构段</strong></td>
						<td width="30%">
							<s:select name="liningRingConstruction.tunnelSectionId" id="tunnelSectionId"
								list="tunnelSections" listKey="id" listValue="name"
								value="liningRingConstruction.tunnelSectionId" theme="simple" >
							</s:select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">名称编号</strong></td>
						<td ><input readonly type="text" name="liningRingConstruction.name" value="<s:property value="liningRingConstruction.name"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">衬砌环类型</strong></td>
						<td ><input readonly type="text" name="liningRingConstruction.type"  value="<s:property value="liningRingConstruction.type"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">线路类型</strong></td>
						<td colspan='3'>
							<select name="liningRingConstruction.lineType" id="lineType">
								<option value="上行">上行</option>
								<option value="下行">下行</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">开始里程(m)</strong></td>
						<td><input readonly type="text" name="liningRingConstruction.startPosition"  value="<s:property value="liningRingConstruction.startPosition"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">结束里程(m)</strong></td>
						<td><input readonly type="text" name="liningRingConstruction.endPosition"  value="<s:property value="liningRingConstruction.endPosition"/>"  class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">设计制作文档</br>
						</td>
						<td>
							<s:if test="liningRingConstruction.documentId>0">
								<a class='text-error' href="documentDownload.do?documentId=<s:property value="liningRingConstruction.document.id"/>"><s:property value="liningRingConstruction.document.name"/></a>
							</s:if>
						</td>
						<td style="text-align:right;"><strong class="text-success">衬砌环备注信息</strong></td>
						<td><textarea readonly type="text" rows="5" cols="30"  name="liningRingConstruction.des" class="{maxlength:512}"><s:property value="liningRingConstruction.des"/></textarea></td>
					</tr>
					</table>
					<%@include file="./../../../admin/schedule/ScheduleDetail.jsp"%>
		</div>
	</div>
     
    <%@include file="./../../Foot.jsp"%>
  </div>
</body>
</html>
