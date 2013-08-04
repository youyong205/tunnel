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
	$('#saddleWeightList').addClass("active");
	$("#form").validate();
	var type='<s:property value="saddleWeight.lineType"/>';
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
						<th colspan='4'><h4 class="text-info text-center">编辑压重块信息</h4></th>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
						<input type="hidden" name="saddleWeight.documentId" value="<s:property value="saddleWeight.documentId"/>"/>
						<input type="hidden" name="saddleWeight.id" value="<s:property value="saddleWeight.id"/>" />
					</tr>
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td width="35%">
							<s:select name="saddleWeight.tunnelId" id="tunnelId"
								onchange="tunnelChanged()" 
								list="tunnels" listKey="id" listValue="name" 
								value="saddleWeight.tunnelId" theme="simple" >
							</s:select>
						</td>
						<td width="10%" style="text-align:right;"><strong class="text-success">选择盾构段</strong></td>
						<td width="40%">
							<s:select name="saddleWeight.tunnelSectionId" id="tunnelSectionId"
								list="tunnelSections" listKey="id" listValue="name"
								value="saddleWeight.tunnelSectionId" theme="simple" >
							</s:select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">名称编号</strong></td>
						<td ><input type="text" size="40" name="saddleWeight.name" readonly value="<s:property value="saddleWeight.name"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">压重块类型</strong></td>
						<td ><input type="text" size="40" name="saddleWeight.type"  readonly  value="<s:property value="saddleWeight.type"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">线路类型</strong></td>
						<td colspan='3'>
							<select name="saddleWeight.lineType" id="lineType">
								<option value="上行">上行</option>
								<option value="下行">下行</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">混凝土强度</strong></td>
						<td><input type="text" size="40" name="saddleWeight.concreteStrength" readonly  value="<s:property value="saddleWeight.concreteStrength"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">钢筋强度</strong></td>
						<td><input type="text" size="40" name="saddleWeight.reinforcementStrength" readonly   value="<s:property value="saddleWeight.reinforcementStrength"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">开始里程(m)</strong></td>
						<td><input type="text" name="saddleWeight.startPosition" readonly   value="<s:property value="saddleWeight.startPosition"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">结束里程(m)</strong></td>
						<td><input type="text" name="saddleWeight.endPosition"  readonly  value="<s:property value="saddleWeight.endPosition"/>"  class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">上传设计制作文档</br>
							<span class='text-error'>多个文档请打zip包</span>
							</br><span class='text-error'>总大小不超过40M</span></strong>
						</td>
						<td>
							<s:if test="saddleWeight.documentId>0">
								已经上传附件:&nbsp;
								<a class='text-error' href="documentDownload.do?documentId=<s:property value="saddleWeight.document.id"/>"><s:property value="saddleWeight.document.name"/></a>
							</s:if>
						</td>
						<td style="text-align:right;"><strong class="text-success">压重块简介</strong></td>
						<td><textarea type="text" rows="5" cols="60"  readonly  name="saddleWeight.des" class="{maxlength:512}"><s:property value="saddleWeight.des"/></textarea></td>
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



