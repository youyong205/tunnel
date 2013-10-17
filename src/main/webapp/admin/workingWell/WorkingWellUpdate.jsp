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
	$('#workingWellList').addClass("active");
	$("#form").validate();
	$('i[tips]').popover();
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
			<form action="workingWellUpdateSubmit.do" id="form" method="post"
			enctype="multipart/form-data">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center">编辑工作井信息</h4></th>
						<input type="hidden" name="workingWell.id" value="<s:property value="workingWell.id"/>"/>
						<input type="hidden" name="workingWell.documentId" value="<s:property value="workingWell.documentId"/>"/>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td >
							 <s:select name="workingWell.tunnelId" id="tunnelId" onchange="workingWellTunnelSectionChange()"
									list="tunnels" listKey="id" listValue="name"  theme="simple" >
							</s:select>
						</td>
						<td style="text-align:right;"><strong class="text-success">烟道夹层标高</strong></td>
						<td><input type="text" name="workingWell.eleationMezzanine"  value="<s:property value="workingWell.eleationMezzanine"/>" class="{required:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">工作井位置</strong>
							<i tips="" data-trigger="hover" class="icon-question-sign" data-toggle="popover" data-placement="top" data-content="位置表示此工作井在某个盾构段后面，如果是上下行，可能需要选择两个盾构段！"></i>
						</td>
						<td colspan='3' id="position">
							<s:checkboxlist list="tunnelSections"
								listKey="id" listValue="name" value="tunnelSectionIdSelect" name="tunnelSectionIdSelect" theme="simple">
							</s:checkboxlist>
						</td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">工作井编号</strong></td>
						<td width="30%"><input type="text" name="workingWell.name"  value="<s:property value="workingWell.name"/>" class="{required:true,maxlength:64}"/></td>
						<td width="15%" style="text-align:right;"><strong class="text-success">工作井类型</strong></td>
						<td width="35%" ><input type="text" name="workingWell.type" value="<s:property value="workingWell.type"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">开始里程(m)</strong></td>
						<td><input type="text" name="workingWell.startPosition"  value="<s:property value="workingWell.startPosition"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">结束里程(m)</strong></td>
						<td><input type="text" name="workingWell.endPosition"  value="<s:property value="workingWell.endPosition"/>" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">下一层标高标高(m)</strong></td>
						<td><input type="text" name="workingWell.eleationOne"  value="<s:property value="workingWell.eleationOne"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">下二层标高标高(m)</strong></td>
						<td><input type="text" name="workingWell.eleationTwo"  value="<s:property value="workingWell.eleationTwo"/>" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">下三层标高标高(m)</strong></td>
						<td><input type="text" name="workingWell.eleationThree"  value="<s:property value="workingWell.eleationThree"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">下四层标高标高(m)</strong></td>
						<td><input type="text" name="workingWell.eleationFour"  value="<s:property value="workingWell.eleationFour"/>" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">上传设计制作设计附件</br>
						<span class='text-error'>多个文档请打zip包</span>
						</br><span class='text-error'>总大小不超过40M</span>
						</strong></td>
						<td>
							<s:if test="workingWell.documentId>0">
								已经上传附件:&nbsp;
								<a class='text-error' href="documentDownload.do?documentId=<s:property value="workingWell.document.id"/>"><s:property value="workingWell.document.name"/></a>
								</br>
							</s:if>
							更换附件<input type="file" name="upload">
						</td>
						<td style="text-align:right;"><strong class="text-success">工作井备注信息</strong></td>
						<td><textarea type="text" rows="5" cols="40"  name="workingWell.des" class="{maxlength:512}"><s:property value="workingWell.des"/></textarea></td>
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



