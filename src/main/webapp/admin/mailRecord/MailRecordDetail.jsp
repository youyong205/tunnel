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
	$('#type').val("<s:property value="mailRecord.type"/>");
	$('#status').val("<s:property value="mailRecord.status"/>");
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
						<th colspan='2'><h4 class="text-info text-center">邮件信息详情</h4></th>
						<input type="hidden" name="mailRecord.id" value="<s:property value="mailRecord.id"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input type="hidden" name="type" value="<s:property value="type"/>"/>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">邮件类型</strong></td>
						<td>
							<select name="mailRecord.type" id="type">
								<option value="1">日常邮件</option>
								<option value="2">告警邮件</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">隧道</strong></td>
						<td>
							 <s:select name="mailRecord.tunnelId" id="tunnelSectionId"
									list="tunnels" listKey="id" listValue="name" value="mailRecord.tunnelId"  theme="simple" >
							</s:select>
						</td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">邮件时间</strong></td>
						<td>
							<input name="mailRecord.title" readonly type='text' class="{required:true}"   value="<s:date name="mailRecord.time" format="yyyy-MM-dd"/>" />
				        </td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">收件人</strong></td>
						<td>
							<input name="mailRecord.receivers" readonly type='text' class="{required:true}" value="<s:property value='mailRecord.receivers'/>"/>
					</td></tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">邮件标题</strong></td>
						<td>
							<input name="mailRecord.title" readonly type='text' class="{required:true}" value="<s:property value='mailRecord.title'/>"/>
					</td></tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">发送状态</strong></td>
						<td>
							<select name="mailRecord.status" id="status">
								<option value="1">成功</option>
								<option value="2">失败</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">邮件内容</strong></td>
						<td>
							<s:property escape="false" value='mailRecord.content'/>
						</td></tr>
					</table>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>



