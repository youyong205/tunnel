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
	$('#rectangleComponentList').addClass("active");
	var type='<s:property value="rectangleComponent.lineType"/>';
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
						<th colspan='4'><h4 class="text-info text-center">口型构件信息详情</h4></th>
						<input readonly type="hidden" name="index" value="<s:property value="index"/>"/>
						<input readonly type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input readonly type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
						<input readonly type="hidden" name="rectangleComponent.documentId" value="<s:property value="rectangleComponent.documentId"/>"/>
						<input readonly type="hidden" name="rectangleComponent.id" value="<s:property value="rectangleComponent.id"/>" />
					</tr>
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">隧道</strong></td>
						<td width="35%">
							<s:select name="rectangleComponent.tunnelId" id="tunnelId"
								onchange="tunnelChanged()" 
								list="tunnels" listKey="id" listValue="name" 
								value="rectangleComponent.tunnelId" theme="simple" >
							</s:select>
						</td>
						<td width="15%" style="text-align:right;"><strong class="text-success">盾构段</strong></td>
						<td width="35%">
							<s:select name="rectangleComponent.tunnelSectionId" id="tunnelSectionId"
								list="tunnelSections" listKey="id" listValue="name"
								value="rectangleComponent.tunnelSectionId" theme="simple" >
							</s:select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">名称编号</strong></td>
						<td ><input readonly type="text" size="40" name="rectangleComponent.name" value="<s:property value="rectangleComponent.name"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">口型构件类型</strong></td>
						<td ><input readonly type="text" size="40" name="rectangleComponent.type"  value="<s:property value="rectangleComponent.type"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">线路类型</strong></td>
						<td colspan='3'>
							<select name="rectangleComponent.lineType" id="lineType">
								<option value="上行">上行</option>
								<option value="下行">下行</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">开始里程(m)</strong></td>
						<td><input readonly type="text" name="rectangleComponent.startPosition"  value="<s:property value="rectangleComponent.startPosition"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">结束里程(m)</strong></td>
						<td><input readonly type="text" name="rectangleComponent.endPosition"  value="<s:property value="rectangleComponent.endPosition"/>"  class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">设计制作文档</br>
							
							</strong>
						</td>
						<td>
							<s:if test="rectangleComponent.documentId>0">
								
								<a class='text-error' href="documentDownload.do?documentId=<s:property value="rectangleComponent.document.id"/>"><s:property value="rectangleComponent.document.name"/></a>
							</s:if>
						</td>
						<td style="text-align:right;"><strong class="text-success">口型构件备注信息</strong></td>
						<td><textarea readonly type="text" rows="5" cols="40"  name="rectangleComponent.des" class="{maxlength:512}"><s:property value="rectangleComponent.des"/></textarea></td>
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



