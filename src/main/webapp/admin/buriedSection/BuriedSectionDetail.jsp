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
	$('#buriedSectionList').addClass("active");
	
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
						<th colspan='4'><h4 class="text-info text-center">暗埋段信息</h4></th>
						<input readonly type="hidden" name="buriedSection.id" value="<s:property value="buriedSection.id"/>"/>
						<input readonly type="hidden" name="buriedSection.documentId" value="<s:property value="buriedSection.documentId"/>"/>
						<input readonly type="hidden" name="index" value="<s:property value="index"/>"/>
						<input readonly type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">隧道</strong></td>
						<td colspan='3'>
							 <s:select name="buriedSection.tunnelId" id="buriedSectionId"
									list="tunnels" listKey="id" listValue="name"  theme="simple" >
							</s:select>
						</td>
						
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">暗埋段编号</strong></td>
						<td width="30%"><input readonly type="text" name="buriedSection.name"   value="<s:property value="buriedSection.name"/>" class="{required:true,maxlength:64}"/></td>
						<td width="15%" style="text-align:right;"><strong class="text-success">暗埋段类型</strong></td>
						<td width="35%" ><input readonly type="text" name="buriedSection.type"   value="<s:property value="buriedSection.type"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
										<tr>
						<td style="text-align:right;"><strong class="text-success">开始里程(m)</strong></td>
						<td><input readonly type="text" name="buriedSection.startPosition"   value="<s:property value="buriedSection.startPosition"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">结束里程(m)</strong></td>
						<td><input readonly type="text" name="buriedSection.endPosition"   value="<s:property value="buriedSection.endPosition"/>"  class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">起始地板标高(m)</strong></td>
						<td><input readonly type="text" name="buriedSection.startFloor"    value="<s:property value="buriedSection.startFloor"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">围护结构</strong></td>
						<td><input readonly type="text" name="buriedSection.envelope"    value="<s:property value="buriedSection.envelope"/>" class="{required:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">终止底板标高(m)</strong></td>
						<td><input readonly type="text" name="buriedSection.endFloor"    value="<s:property value="buriedSection.endFloor"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">内衬墙厚度(m)</strong></td>
						<td><input readonly type="text" name="buriedSection.linedWallThickness"    value="<s:property value="buriedSection.linedWallThickness"/>" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">底板厚度(m)</strong></td>
						<td><input readonly type="text" name="buriedSection.floorThickness"    value="<s:property value="buriedSection.floorThickness"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">起始净空高度(m)</strong></td>
						<td><input readonly type="text" name="buriedSection.startHeadroom"    value="<s:property value="buriedSection.startHeadroom"/>" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">顶板厚度(m)</strong></td>
						<td><input readonly type="text" name="buriedSection.roofThickness"    value="<s:property value="buriedSection.roofThickness"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">终止净空高度(m)</strong></td>
						<td><input readonly type="text" name="buriedSection.endHeadroom"    value="<s:property value="buriedSection.endHeadroom"/>" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">上传设计制作设计附件</br>
						
						</strong></td>
						<td>
							<s:if test="buriedSection.documentId>0">
								
								<a class='text-error' href="documentDownload.do?documentId=<s:property value="buriedSection.document.id"/>"><s:property value="buriedSection.document.name"/></a>
							</s:if>
						</td>
						<td style="text-align:right;"><strong class="text-success">暗埋段备注信息</strong></td>
						<td><textarea readonly type="text" rows="5" cols="40"    name="buriedSection.des" class="{maxlength:512}"><s:property value="buriedSection.des"/> </textarea></td>
					</tr>
				</table>
				<%@include file="./../schedule/ScheduleDetail.jsp"%>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>



