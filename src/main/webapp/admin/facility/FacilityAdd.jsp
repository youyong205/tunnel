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
	$('#facilityList').addClass("active");
	$("#form").validate();
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
			<form action="facilityAddSubmit.do" id="form" method="post" enctype="multipart/form-data">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center">新增设备信息</h4></th>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
					</tr>
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td width="35%">
						<s:select name="facility.tunnelId" id="tunnelId"
							onchange="tunnelChanged()" 
							list="tunnels" listKey="id" listValue="name" 
							value="tunnelId" theme="simple" >
						</s:select></td>
						<td width="10%" style="text-align:right;"><strong class="text-success">选择盾构段</strong></td>
						<td width="40%">
						<s:select name="facility.tunnelSectionId" id="tunnelSectionId"
							list="tunnelSections" listKey="id" listValue="name"
							value="tunnelSectionId" theme="simple" >
						</s:select> </td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">名称编号</strong></td>
						<td><input type="text" size="40" name="facility.name" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">设备类型</strong></td>
						<td><input type="text" size="40" name="facility.type" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">桩号里程</strong></td>
						<td><input type="text" size="40" name="facility.stakeMileage" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">位置描述</strong></td>
						<td><input type="text" size="40" name="facility.position" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">线路类型</strong></td>
						<td>
							<select name="facility.lineType">
								<option value="上行">上行</option>
								<option value="下行">下行</option>
							</select>
						</td>
						<td style="text-align:right;"><strong class="text-success">管养单位ID</strong></td>
						<td>
							<s:select name="facility.constructionUnitId" id="id"
									list="constructionUnits" listKey="id" listValue="name"  theme="simple" >
							</s:select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">关联环号</strong></td>
						<td><input type="text" size="40" name="facility.liningRingId" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">箱号</strong></td>
						<td><input type="text" size="40" name="facility.boxNumber" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">长度</strong></td>
						<td><input type="text" size="40" name="facility.width" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">高度</strong></td>
						<td><input type="text" size="40" name="facility.height" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">上传设计制作文档</br>
						<span class='text-error'>多个文档请打zip包</span>
						</br><span class='text-error'>总大小不超过40M</span></strong></td>
						<td ><input type="file" name="upload" class="{required:true}"></td>
						<td style="text-align:right;"><strong class="text-success">设备备注信息</strong></td>
						<td><textarea type="text" rows="5" cols="60"  name="facility.des" class="{maxlength:512}"></textarea></td>
					</tr>
					</table>
					<%@include file="./../schedule/ScheduleAdd.jsp"%>
					<table   class="table table-striped table-bordered table-condensed">
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

