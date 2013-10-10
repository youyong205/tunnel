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
	$('#mailRecordList').addClass("active");
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
			<form action="mailRecordAddSubmit.do" id="form" method="post">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='2'><h4 class="text-info text-center">新增邮件信息</h4></th>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">邮件类型</strong></td>
						<td>
							<select name="mailRecord.type">
								<option value="1">日常邮件</option>
								<option value="2">告警邮件</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">隧道ID</strong></td>
						<td>
							<input name="mailRecord.tunnelId" type='text'/>
					</td></tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">邮件时间</strong></td>
						<td>
							<input name="mailRecord.time" type='text'/>
					</td></tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">邮件接受者</strong></td>
						<td>
							<input name="mailRecord.receivers" type='text'/>
					</td></tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">邮件标题</strong></td>
						<td>
							<input name="mailRecord.title" type='text'/>
					</td></tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">邮件内容</strong></td>
						<td>
							<input name="mailRecord.content" type='text'/>
					</td></tr><tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">发送状态</strong></td>
						<td>
							<select name="mailRecord.status">
								<option value="1">成功</option>
								<option value="2">失败</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:center;">
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

