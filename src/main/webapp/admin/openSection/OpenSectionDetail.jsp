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

<script type="text/javascript">
$(document).ready(function() {
	$('#openSectionList').addClass("active");
	
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
						<th colspan='4'><h4 class="text-info text-center">敞开段信息详情</h4></th>
						<input readonly type="hidden" name="openSection.id" value="<s:property value="openSection.id"/>"/>
						<input readonly type="hidden" name="openSection.documentId" value="<s:property value="openSection.documentId"/>"/>
						<input readonly type="hidden" name="index" value="<s:property value="index"/>"/>
						<input readonly type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">隧道</strong></td>
						<td>
							 <s:select name="openSection.tunnelId" id="openSectionId"
									list="tunnels" listKey="id" listValue="name"  theme="simple" >
							</s:select>
						</td>
						<td style="text-align:right;"><strong class="text-success">围护结构</strong></td>
						<td ><input readonly type="text" name="openSection.envelope"  value="<s:property value="openSection.envelope"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">敞开段编号</strong></td>
						<td width="30%"><input readonly type="text" name="openSection.name"  value="<s:property value="openSection.name"/>" class="{required:true,maxlength:64}"/></td>
						<td width="15%" style="text-align:right;"><strong class="text-success">敞开段类型</strong></td>
						<td width="35%" ><input readonly type="text" name="openSection.type"  value="<s:property value="openSection.type"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">开始里程(m)</strong></td>
						<td><input readonly type="text" name="openSection.startPosition"  value="<s:property value="openSection.startPosition"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">结束里程(m)</strong></td>
						<td><input readonly type="text" name="openSection.endPosition"  value="<s:property value="openSection.endPosition"/>" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">起始地板标高(m)</strong></td>
						<td><input readonly type="text" name="openSection.startFloor"  value="<s:property value="openSection.startFloor"/>"  class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">起始路拱顶标高(m)</strong></td>
						<td><input readonly type="text" name="openSection.startRoadDome"  value="<s:property value="openSection.startRoadDome"/>"  class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">终止底板标高(m)</strong></td>
						<td><input readonly type="text" name="openSection.endFloor"  value="<s:property value="openSection.endFloor"/>"  class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">终止路拱顶标高(m)</strong></td>
						<td><input readonly type="text" name="openSection.endRoadDome"  value="<s:property value="openSection.endRoadDome"/>"  class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">坡顶搅拌桩底标高(m)</strong></td>
						<td><input readonly type="text" name="openSection.crestPileBottom"  value="<s:property value="openSection.crestPileBottom"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">平台搅拌桩底标高(m)</strong></td>
						<td><input readonly type="text" name="openSection.platformPileBottom"   value="<s:property value="openSection.endPosition"/>"  class="{required:true,number:true}"/></td>
					</tr>
					<tr>	
						<td style="text-align:right;"><strong class="text-success">设计制作文档</br>
						
						</strong></td>
						<td>
							<s:if test="openSection.documentId>0">
								
								<a class='text-error' href="documentDownload.do?documentId=<s:property value="openSection.document.id"/>"><s:property value="openSection.document.name"/></a>
							</s:if>
						</td>
						<td style="text-align:right;"><strong class="text-success">敞开段备注信息</strong></td>
						<td><textarea readonly type="text" rows="5" cols="40"  name="openSection.des" class="{maxlength:512}"><s:property value="openSection.des"/></textarea></td>
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



