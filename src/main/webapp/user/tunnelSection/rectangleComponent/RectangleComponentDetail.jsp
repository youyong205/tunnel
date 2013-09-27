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
			<li class="active">基本信息</li>
		</ul>
	</div>
	<div class='row'>
		<div class='span2'>
			<%@include file="./../TunnelMenu.jsp"%>
		</div>
		<div class='span10'>
			<div class="row-fluid">
			    <div class="span12">
					<a href="userContactChannelList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small  btn-primary btn-success">联络通道列表</a>
    	  			<a href="userContactChannelInspectionList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small btn-primary btn-info">质量检查</a>
      				<a href="userContactChannelCuringList.do?tunnelId=<s:property value="tunnelId"/>" class="btn btn-small  btn-primary btn-info">养护记录</a>
      			</div>
      		</div>
			<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center">联络通道信息</h4></th>
						<input readonly type="hidden" name="contactChannel.id" value="<s:property value="contactChannel.id"/>"/>
						<input readonly type="hidden" name="contactChannel.documentId" value="<s:property value="contactChannel.documentId"/>"/>
						<input readonly type="hidden" name="index" value="<s:property value="index"/>"/>
						<input readonly type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td>
							 <s:select name="contactChannel.tunnelId" id="contactChannelId"
									list="tunnels" listKey="id" listValue="name"  theme="simple" >
							</s:select>
						</td>
						<td style="text-align:right;"><strong class="text-success">所属土层层序</strong></td>
						<td><input readonly type="text" name="contactChannel.soilOrder"   value="<s:property value="contactChannel.soilOrder"/>" class="{required:true}"/></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">联络通道编号</strong></td>
						<td width="30%"><input readonly type="text" name="contactChannel.name"    value="<s:property value="contactChannel.name"/>" class="{required:true,maxlength:64}"/></td>
						<td width="15%" style="text-align:right;"><strong class="text-success">联络通道类型</strong></td>
						<td width="35%" ><input readonly type="text" name="contactChannel.type"   value="<s:property value="contactChannel.type"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">上行线隧道里程(m)</strong></td>
						<td><input readonly type="text" name="contactChannel.upLine"    value="<s:property value="contactChannel.upLine"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">上行线隧道中心标高(m)</strong></td>
						<td><input readonly type="text" name="contactChannel.upCenter"    value="<s:property value="contactChannel.upCenter"/>" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">下行线隧道里程(m)</strong></td>
						<td><input readonly type="text" name="contactChannel.downLine"    value="<s:property value="contactChannel.downLine"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">下行线隧道中心标高(m)</strong></td>
						<td><input readonly type="text" name="contactChannel.downCenter"    value="<s:property value="contactChannel.downCenter"/>" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">设计制作设计附件</br><span class='text-error'>（多个文档请打zip包）</span></strong></td>
						<td>
							<s:if test="contactChannel.documentId>0">
								已经上传附件:&nbsp;
								<a class='text-error' href="documentDownload.do?documentId=<s:property value="contactChannel.document.id"/>"><s:property value="contactChannel.document.name"/></a>
							</s:if>
						</td>
						<td style="text-align:right;"><strong class="text-success">联络通道备注信息</strong></td>
						<td><textarea readonly type="text" rows="5" cols="40" name="contactChannel.des"  class="{maxlength:512}"><s:property value="contactChannel.des"/></textarea></td>
					</tr>
				</table>
				<%@include file="./../../admin/schedule/ScheduleDetail.jsp"%>
		</div>
	</div>
     
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
