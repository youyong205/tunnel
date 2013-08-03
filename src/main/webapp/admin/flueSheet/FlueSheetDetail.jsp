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
	$('#flueSheetList').addClass("active");
	$('#flueSheet_role').val("<s:property value="flueSheet.role"/>");
	$("#form").validate();
	var type='<s:property value="flueSheet.lineType"/>';
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
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center">口型构件信息详情</h4></th>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
						<input type="hidden" name="flueSheet.documentId" value="<s:property value="flueSheet.documentId"/>"/>
						<input type="hidden" name="flueSheet.id" value="<s:property value="flueSheet.id"/>" />
					</tr>
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td width="35%">
							<s:select name="flueSheet.tunnelId" id="tunnelId"
								onchange="tunnelChanged()" 
								list="tunnels" listKey="id" listValue="name" 
								value="flueSheet.tunnelId" theme="simple" >
							</s:select>
						</td>
						<td width="10%" style="text-align:right;"><strong class="text-success">选择盾构段</strong></td>
						<td width="40%">
							<s:select name="flueSheet.tunnelSectionId" id="tunnelSectionId"
								list="tunnelSections" listKey="id" listValue="name"
								value="flueSheet.tunnelSectionId" theme="simple" >
							</s:select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">名称编号</strong></td>
						<td ><input type="text" size="40" name="flueSheet.name" value="<s:property value="flueSheet.name"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">烟道板类型</strong></td>
						<td ><input type="text" size="40" name="flueSheet.type"  value="<s:property value="flueSheet.type"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">线路类型</strong></td>
						<td colspan='3'>
							<select name="flueSheet.lineType" id="lineType">
								<option value="上行">上行</option>
								<option value="下行">下行</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">开始里程(m)</strong></td>
						<td><input type="text" name="flueSheet.startPosition" readonly value="<s:property value="flueSheet.startPosition"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">结束里程(m)</strong></td>
						<td><input type="text" name="flueSheet.endPosition" readonly value="<s:property value="flueSheet.endPosition"/>"  class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">上传设计制作文档</br>
							<span class='text-error'>多个文档请打zip包</span>
							</br><span class='text-error'>总大小不超过40M</span></strong>
						</td>
						<td>
							<s:if test="flueSheet.documentId>0">
								已经上传附件:&nbsp;<span class='text-error'><s:property value="flueSheet.document.name"/></span></br>
							</s:if>
						</td>
						<td style="text-align:right;"><strong class="text-success">口型构件简介</strong></td>
						<td><textarea type="text" rows="5" cols="60" readonly name="flueSheet.des" class="{maxlength:512}"><s:property value="flueSheet.des"/></textarea></td>
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



