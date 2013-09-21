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
			<form action="buriedSectionAddSubmit.do" id="form" method="post"   
				enctype="multipart/form-data">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center">新增暗埋段信息</h4></th>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td colspan='3'>
							 <s:select name="buriedSection.tunnelId" id="buriedSectionId"
									list="tunnels" listKey="id" listValue="name"  theme="simple" >
							</s:select>
						</td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">暗埋段编号</strong></td>
						<td width="30%"><input type="text" name="buriedSection.name" class="{required:true,maxlength:64}"/></td>
						<td width="15%" style="text-align:right;"><strong class="text-success">暗埋段类型</strong></td>
						<td width="35%" ><input type="text" name="buriedSection.type" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">开始里程(m)</strong></td>
						<td><input type="text" name="buriedSection.startPosition" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">结束里程(m)</strong></td>
						<td><input type="text" name="buriedSection.endPosition" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">起始地板标高(m)</strong></td>
						<td><input type="text" name="buriedSection.startFloor" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">围护结构</strong></td>
						<td><input type="text" name="buriedSection.envelope" class="{required:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">终止底板标高(m)</strong></td>
						<td><input type="text" name="buriedSection.endFloor" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">内衬墙厚度(m)</strong></td>
						<td><input type="text" name="buriedSection.linedWallThickness" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">底板厚度(m)</strong></td>
						<td><input type="text" name="buriedSection.floorThickness" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">起始净空高度(m)</strong></td>
						<td><input type="text" name="buriedSection.startHeadroom" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">顶板厚度(m)</strong></td>
						<td><input type="text" name="buriedSection.roofThickness" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">终止净空高度(m)</strong></td>
						<td><input type="text" name="buriedSection.endHeadroom" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">上传设计制作文档</br>
							<span class='text-error'>多个文档请打zip包</span>
						</br><span class='text-error'>总大小不超过40M</span></strong></td>
						<td ><input type="file" name="upload" class="{required:true}"></td>
						<td style="text-align:right;"><strong class="text-success">暗埋段备注信息</strong></td>
						<td colspan='3'><textarea type="text" rows="5" cols="40"  name="buriedSection.des" class="{maxlength:512}"></textarea></td>
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

