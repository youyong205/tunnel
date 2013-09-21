<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<!DOCTYPE html><html>
<head>
<title>软土盾构隧道后台管理系统</title>

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
	$('#pumpingStationList').addClass("active");
	$("#form").validate();
	var type='<s:property value="pumpingStation.lineType"/>';
	$('#lineType').val(type);
});
</script>
</head>
<body>
  <%@include file="./../Head.jsp"%>
  </br>
  </br>
  <div class="container-fluid">
    <div class="row-fluid">
      <%@include file="./../Menu.jsp"%>
      <div class="span10">
			<form action="pumpingStationUpdateSubmit.do" id="form" method="post"  enctype="multipart/form-data">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center">编辑泵房信息</h4></th>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
						<input type="hidden" name="pumpingStation.documentId" value="<s:property value="pumpingStation.documentId"/>"/>
						<input type="hidden" name="pumpingStation.id" value="<s:property value="pumpingStation.id"/>" />
					</tr>
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td width="35%">
							<s:select name="pumpingStation.tunnelId" id="tunnelId"
								onchange="tunnelChanged()" 
								list="tunnels" listKey="id" listValue="name" 
								value="pumpingStation.tunnelId" theme="simple" >
							</s:select>
						</td>
						<td width="15%" style="text-align:right;"><strong class="text-success">选择盾构段</strong></td>
						<td width="35%">
							<s:select name="pumpingStation.tunnelSectionId" id="tunnelSectionId"
								list="tunnelSections" listKey="id" listValue="name"
								value="pumpingStation.tunnelSectionId" theme="simple" >
							</s:select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">名称编号</strong></td>
						<td ><input type="text" size="40" name="pumpingStation.name" value="<s:property value="pumpingStation.name"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">泵房类型</strong></td>
						<td ><input type="text" size="40" name="pumpingStation.type"  value="<s:property value="pumpingStation.type"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">桩号里程</strong></td>
						<td><input type="text" size="40" name="pumpingStation.stakeMileage" value="<s:property value="pumpingStation.stakeMileage"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">位置描述</strong></td>
						<td><input type="text" size="40" name="pumpingStation.position" value="<s:property value="pumpingStation.position"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">线路类型</strong></td>
						<td>
							<select name="pumpingStation.lineType" id="lineType">
								<option value="上行">上行</option>
								<option value="下行">下行</option>
							</select>
						</td>
						<td style="text-align:right;"><strong class="text-success">车道中心线里程(m)</strong></td>
						<td><input type="text" size="40" name="pumpingStation.laneMileage" value="<s:property value="pumpingStation.laneMileage"/>"  class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">盾构中心线里程(m)</strong></td>
						<td><input type="text" size="40" name="pumpingStation.shieldMileage" value="<s:property value="pumpingStation.shieldMileage"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">+0.000对应绝对标高(m)</strong></td>
						<td><input type="text" size="40" name="pumpingStation.absolutEelevation" value="<s:property value="pumpingStation.absolutEelevation"/>"  class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">上传设计制作文档</br>
							<span class='text-error'>多个文档请打zip包</span>
							</br><span class='text-error'>总大小不超过40M</span></strong>
						</td>
						<td>
							<s:if test="pumpingStation.documentId>0">
								已经上传附件:&nbsp;
								<a class='text-error' href="documentDownload.do?documentId=<s:property value="pumpingStation.document.id"/>"><s:property value="pumpingStation.document.name"/></a>
								</br>
							</s:if>
							更换附件<input type="file" name="upload">
						</td>
						<td style="text-align:right;"><strong class="text-success">泵房备注信息</strong></td>
						<td><textarea type="text" rows="5" cols="50"  name="pumpingStation.des" class="{maxlength:512}"><s:property value="pumpingStation.des"/></textarea></td>
					</tr>
					</table>
					<%@include file="./../schedule/ScheduleUpdate.jsp"%>
					<table  class="table table-striped table-bordered table-condensed">
						<tr>
							<td  style="text-align:center;">
								<button  class="btn btn-small btn-primary"  type="submit" >提交</button></td>
						</tr>
					</table>
			</form>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>



