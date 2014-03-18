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
<script type="text/javascript" src="js/tunnelGraph.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$('#tunnelGraphList').addClass("active");
	$("#form").validate();
	$('#lineType').val('<s:property value="tunnelGraph.lineType"/>');
	$('#componentType').val('<s:property value="tunnelGraph.componentType"/>');
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
			<form action="tunnelGraphUpdateSubmit.do" id="form" method="post">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='2'><h4 class="text-info text-center">编辑隧道剖面图信息</h4></th>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input type="hidden" name="tunnelGraph.tunnelId" value="<s:property value="tunnelId"/>"/>
						<input type="hidden" name="tunnelGraph.id" value="<s:property value="tunnelGraph.id"/>"/>
						<input type="hidden" name="lineType" value="<s:property value="lineType"/>"/>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td>
							 <s:property value="tunnelGraph.tunnel.name"/>
						</td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">线路类型</strong></td>
						<td>
							<select name="tunnelGraph.lineType" id="lineType">
							  	 <option value="上行">上行</option>
							  	 <option value="下行">下行</option>
							  </select>	
						</td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">隧道构件类型</strong></td>
						<td>
							<select name="tunnelGraph.componentType" id="componentType"  onchange="componentTypeChanged(<s:property value="tunnelId"/>)">
								<option value="1">盾构段</option>
								<option value="2">工作井</option>
								<option value="3">敞开段</option>
								<option value="4">暗埋段</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">选择构件</strong></td>
						<td>
							<s:if test="tunnelGraph.componentType==1">
								<s:select name="tunnelGraph.componentId" id="componentId"
										list="tunnelSections" listKey="id" listValue="name"  theme="simple" >
								</s:select>
							</s:if>
							<s:elseif test="tunnelGraph.componentType==2">
								<s:select name="tunnelGraph.componentId" id="componentId"
										list="workingWells" listKey="id" listValue="name"  theme="simple" >
								</s:select>
							</s:elseif>
							<s:elseif test="tunnelGraph.componentType==3">
								<s:select name="tunnelGraph.componentId" id="componentId"
										list="openSections" listKey="id" listValue="name"  theme="simple" >
								</s:select>
							</s:elseif>
						</td>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">构件在隧道剖面顺序号</strong></td>
						<td>
							<input type="text" name="tunnelGraph.indexNumber" value='<s:property value="tunnelGraph.indexNumber"/>' class="{required:true,number:true}"/>
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



