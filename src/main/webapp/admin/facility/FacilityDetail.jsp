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
	
	var type='<s:property value="facility.lineType"/>';
	$('#lineType').val(type);
});
</script>
</head>
<body>
  <%@include file="./../Head.jsp"%>
  </br>
  </br>
  <div class="container-fluid background-body" >
    <div class="row-fluid">
      <%@include file="./../Menu.jsp"%>
      <div class="span10">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center">编辑设备信息</h4></th>
						<input readonly type="hidden" name="index" value="<s:property value="index"/>"/>
						<input readonly type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input readonly type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
						<input readonly type="hidden" name="facility.documentId" value="<s:property value="facility.documentId"/>"/>
						<input readonly type="hidden" name="facility.id" value="<s:property value="facility.id"/>" />
					</tr>
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">隧道</strong></td>
						<td width="35%">
							<s:select name="facility.tunnelId" id="tunnelId"
								onchange="tunnelChanged()" 
								list="tunnels" listKey="id" listValue="name" 
								value="facility.tunnelId" theme="simple" >
							</s:select>
						</td>
						<td width="15%" style="text-align:right;"><strong class="text-success">盾构段</strong></td>
						<td width="35%">
							<s:select name="facility.tunnelSectionId" id="tunnelSectionId"
								list="tunnelSections" listKey="id" listValue="name"
								value="facility.tunnelSectionId" theme="simple" >
							</s:select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">名称编号</strong></td>
						<td ><input readonly type="text" size="40" name="facility.name"  value="<s:property value="facility.name"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">设备类型</strong></td>
						<td ><input readonly type="text" size="40" name="facility.type"    value="<s:property value="facility.type"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">桩号里程</strong></td>
						<td><input readonly type="text" size="40" name="facility.stakeMileage"   value="<s:property value="facility.stakeMileage"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">位置描述</strong></td>
						<td><input readonly type="text" size="40" name="facility.position"   value="<s:property value="facility.position"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">线路类型</strong></td>
						<td>
							<select name="facility.lineType" id="lineType">
								<option value="上行">上行</option>
								<option value="下行">下行</option>
							</select>
						</td>
						<td style="text-align:right;"><strong class="text-success">管养单位ID</strong></td>
						<td><input readonly type="text" size="40" name="facility.constructionUnitId"    value="<s:property value="facility.constructionUnitId"/>" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">关联环号</strong></td>
						<td><input readonly type="text" size="40" name="facility.liningRingId"    value="<s:property value="facility.liningRingId"/>" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">箱号</strong></td>
						<td><input readonly type="text" size="40" name="facility.boxNumber"    value="<s:property value="facility.boxNumber"/>" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">长度</strong></td>
						<td><input readonly type="text" size="40" name="facility.width"    value="<s:property value="facility.width"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">高度</strong></td>
						<td><input readonly type="text" size="40" name="facility.height"    value="<s:property value="facility.height"/>"  class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">设计制作文档</br>
							
							</strong>
						</td>
						<td>
							<s:if test="facility.documentId>0">
								
								<a class='text-error' href="documentDownload.do?documentId=<s:property value="facility.document.id"/>"><s:property value="facility.document.name"/></a>
							</s:if>
						</td>
						<td style="text-align:right;"><strong class="text-success">设备备注信息</strong></td>
						<td><textarea readonly type="text" rows="5" cols="40"  name="facility.des"    class="{maxlength:512}"><s:property value="facility.des"/></textarea></td>
					</tr>
					</table>
					<%@include file="./../schedule/ScheduleDetail.jsp"%>
			</form>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>



