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
	$('#contactChannelList').addClass("active");
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
			<form action="contactChannelUpdateSubmit.do" id="form" method="post"
			enctype="multipart/form-data">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center">编辑联络通道信息</h4></th>
						<input type="hidden" name="process" value="true"/>
						<input type="hidden" name="contactChannel.id" value="<s:property value="contactChannel.id"/>"/>
						<input type="hidden" name="contactChannel.documentId" value="<s:property value="contactChannel.documentId"/>"/>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td>
							 <s:select name="contactChannel.tunnelId" id="contactChannelId"
									list="tunnels" listKey="id" listValue="name"  theme="simple" >
							</s:select>
						</td>
						<td style="text-align:right;"><strong class="text-success">所属土层层序</strong></td>
						<td><input type="text" name="contactChannel.soilOrder"  value="<s:property value="contactChannel.soilOrder"/>" class="{required:true}"/></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">联络通道编号</strong></td>
						<td width="30%"><input type="text" name="contactChannel.name"  value="<s:property value="contactChannel.name"/>" class="{required:true,maxlength:64}"/></td>
						<td width="15%" style="text-align:right;"><strong class="text-success">联络通道类型</strong></td>
						<td width="35%" ><input type="text" name="contactChannel.type" value="<s:property value="contactChannel.type"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">上行线隧道里程(m)</strong></td>
						<td><input type="text" name="contactChannel.upLine"  value="<s:property value="contactChannel.upLine"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">上行线隧道中心标高(m)</strong></td>
						<td><input type="text" name="contactChannel.upCenter"  value="<s:property value="contactChannel.upCenter"/>" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">下行线隧道里程(m)</strong></td>
						<td><input type="text" name="contactChannel.downLine"  value="<s:property value="contactChannel.downLine"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">下行线隧道中心标高(m)</strong></td>
						<td><input type="text" name="contactChannel.downCenter"  value="<s:property value="contactChannel.downCenter"/>" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">上传设计制作设计附件</br><span class='text-error'>（多个文档请打zip包）</span></strong></td>
						<td>
							<s:if test="contactChannel.documentId>0">
								已经上传附件:&nbsp;
								<a class='text-error' href="documentDownload.do?documentId=<s:property value="contactChannel.document.id"/>"><s:property value="contactChannel.document.name"/></a>
								</br>
							</s:if>
							更换附件<input type="file" name="upload">
						</td>
						<td style="text-align:right;"><strong class="text-success">联络通道备注信息</strong></td>
						<td><textarea type="text" rows="5" cols="40" name="contactChannel.des" class="{maxlength:512}"><s:property value="contactChannel.des"/></textarea></td>
					</tr>
				</table>
				<%@include file="./../schedule/ScheduleUpdate.jsp"%>
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



