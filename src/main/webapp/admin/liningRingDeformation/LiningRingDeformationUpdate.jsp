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
<script type="text/javascript" src="js/bootstrap.datetimepicker.min.js"></script>
<script type="text/javascript" src="js/liningRingMeasuring.js"></script>


<script type="text/javascript">
$(document).ready(function() {
	$('#liningRingMeasureList').addClass("active");
	$('#liningRingDeformationList').removeClass("btn-info");
	$('#liningRingDeformationList').addClass("btn-success");
	$("#form").validate();
	$('#datetimepicker1').datetimepicker();
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
     		<%@include file="./../MeasuringHeader.jsp"%>
			<form action="liningRingDeformationUpdateSubmit.do" id="form" method="post">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center">编辑<s:property value="actionModule"/>记录信息</h4></th>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
						<input type="hidden" name="liningRingConstructionId" value="<s:property value="liningRingConstructionId"/>"/>
						<input type="hidden" name="liningRingDeformation.id" value="<s:property value="liningRingDeformation.id"/>" />
					</tr>
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td width="35%">
						<s:select name="liningRingDeformation.tunnelId" id="tunnelId"
							onchange="tunnelChangedExcluedeAll()" 
							list="tunnels" listKey="id" listValue="name" 
							value="liningRingDeformation.tunnelId" theme="simple" >
						</s:select></td>
						<td width="15%" style="text-align:right;"><strong class="text-success">选择盾构段</strong></td>
						<td width="35%">
						<s:select name="liningRingDeformation.tunnelSectionId" id="tunnelSectionId"
							list="tunnelSections" listKey="id" listValue="name"
							onchange="tunnelSectionChanged(true)" 
							value="liningRingDeformation.tunnelSectionId" theme="simple" >
						</s:select> </td>
					</tr>
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">选择衬砌环</strong></td>
						<td width="35%">
							<s:select name="liningRingDeformation.liningRingConstructionId" id="liningRingConstructionId"
							list="liningRingConstructions" listKey="id" listValue="name"
							value="liningRingDeformation.liningRingConstructionId" theme="simple" >
							</s:select> 
						</td>
						<td style="text-align:right;"><strong class="text-success">测量时间</strong></td>
						<td>
						<div id="datetimepicker1" class="input-append date">
				            <input name="liningRingDeformation.date"  value="<s:date name="liningRingDeformation.date" format="yyyy-MM-dd"/>"  class="{required:true,date:true}"
				               data-format="yyyy-MM-dd" type="text"></input> <span class="add-on"> <i
				               data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
				            </span>
				         </td>
				    </tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">测点</strong></td>
						<td><input type="text" name="liningRingDeformation.measuringPoing"  value="<s:property value="liningRingDeformation.measuringPoing"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">本次测值D’(mm)</strong></td>
						<td><input type="text" name="liningRingDeformation.value" value="<s:property value="liningRingDeformation.value"/>" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">长轴变形值(mm)</strong></td>
						<td><input type="text" name="liningRingDeformation.maxLength" class="{required:true,number:true}" value="<s:property value="liningRingDeformation.maxLength"/>" /></td>
						<td style="text-align:right;"><strong class="text-success">短轴变形值(mm)</strong></td>
						<td><input type="text" name="liningRingDeformation.minLength" class="{required:true,number:true}" value="<s:property value="liningRingDeformation.minLength"/>" /></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">长轴偏移角度值(度)</strong></td>
						<td><input type="text" name="liningRingDeformation.angle" class="{required:true,number:true}" value="<s:property value="liningRingDeformation.angle"/>" /></td>
						<td style="text-align:right;"><strong class="text-success">备注信息</strong></td>
						<td ><textarea type="text" rows="5" cols="40"  name="liningRingDeformation.des" class="{maxlength:512}"><s:property value="liningRingDeformation.des"/></textarea></td>
					</tr>
					<tr>
						<td colspan='4' style="text-align:center;">
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



