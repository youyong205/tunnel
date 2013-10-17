<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/struts-privilege.tld"%>

<!DOCTYPE html><html>
<head>
<title>隧道管理信息系统</title>

<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css" >
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.metadata.js"></script>
<script type="text/javascript" src="js/validate.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#tunnelList').addClass('active');
		$('#constructionUnitList').addClass('active');
	});
</script>
</head>
<body>
  <div class="container">
	<%@include file="./../Head.jsp"%>
	<div>
		<ul class="breadcrumb">
			<li>当前位置：</li>
			<li>首页<span class="divider">/</span></li>
			<li><a href="userConstructionUnitList.do?tunnelId=<s:property value="tunnelId"/>">施工单位</a><span class="divider">/</span></li>
			<li class="active">详细信息</li>
		</ul>
	</div>
	<div class="row-fluid">
		<div class='span2'>
			<%@include file="./../TunnelMenu.jsp"%>
		</div>
		<div class='span10'>
			<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='2'><h4 class="text-info text-center">编辑邮件信息</h4></th>
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
						<div id="datetimepicker1" class="input-append date">
				            <input name="mailRecord.time"  placeholder="邮件时间"  value="<s:date name="mailRecord.time" format="yyyy-MM-dd"/>" class="{required:true,date:true}"
				               data-format="yyyy-MM-dd" type="text"></input> <span class="add-on"> <i
				               data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
				            </span>
				         </div></td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">收件人</strong></td>
						<td>
							<input name="mailRecord.receivers" type='text' class="{required:true}" value="<s:property value='mailRecord.receivers'/>"/>
					</td></tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">邮件标题</strong></td>
						<td>
							<input name="mailRecord.title" type='text' class="{required:true}" value="<s:property value='mailRecord.title'/>"/>
					</td></tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">邮件内容</strong></td>
						<td>
							<textarea name="mailRecord.content" cols="80" class="{required:true}" rows="6"><s:property escape="false" value='mailRecord.content'/></textarea>
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
						<td colspan="2" style="text-align:center;">
							<button  class="btn btn-small btn-primary"  type="submit" >提交</button></td>
					</tr>
					</table>
		</div>
	</div>
     
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
