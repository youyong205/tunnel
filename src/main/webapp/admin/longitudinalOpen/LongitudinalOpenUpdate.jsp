®<%@ page contentType="text/html; charset=utf-8"%>
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
	$('#longitudinalOpenList').removeClass("btn-info");
	$('#longitudinalOpenList').addClass("btn-success");
	$("#form").validate();
	$('#datetimepicker1').datetimepicker();

	var type='<s:property value="longitudinalOpen.type"/>';
	$('#type').val(type);
	var serious='<s:property value="longitudinalOpen.serious"/>';
	$('#serious').val(serious);
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
     		<%@include file="./../MeasuringHeader.jsp"%>
			<form action="longitudinalOpenUpdateSubmit.do" id="form" method="post" >
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center">编辑<s:property value="actionModule"/>记录</h4></th>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
						<input type="hidden" name="liningRingConstructionId" value="<s:property value="liningRingConstructionId"/>"/>
						<input type="hidden" name="longitudinalOpen.id" value="<s:property value="longitudinalOpen.id"/>" />
					</tr>
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td width="35%">
						<s:select name="longitudinalOpen.tunnelId" id="tunnelId"
							onchange="tunnelChangedAndBlock()" 
							list="tunnels" listKey="id" listValue="name" 
							value="longitudinalOpen.tunnelId" theme="simple" >
						</s:select></td>
						<td width="10%" style="text-align:right;"><strong class="text-success">选择盾构段</strong></td>
						<td width="40%">
						<s:select name="longitudinalOpen.tunnelSectionId" id="tunnelSectionId"
							list="tunnelSections" listKey="id" listValue="name"
							onchange="tunnelSectionChangedAndBlock()" 
							value="longitudinalOpen.tunnelSectionId" theme="simple" >
						</s:select> </td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">选择衬砌环</strong></td>
						<td>
							<s:select name="longitudinalOpen.liningRingConstructionId" id="liningRingConstructionId"
							list="liningRingConstructions" listKey="id" listValue="name" onchange="liningRingChanged()"
							value="longitudinalOpen.liningRingConstructionId" theme="simple" >
							</s:select> 
						</td>
						<td style="text-align:right;"><strong class="text-success">选择所在块</strong></td>
						<td>
							<s:select name="longitudinalOpen.blockIndex" id="liningRingBlockId"
							list="liningRingBlocks" listKey="blockIndex" listValue="blockIndex"
							theme="simple" >
							</s:select> 
						</td>
				    </tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">测量时间</strong></td>
						<td>
						<div id="datetimepicker1" class="input-append date">
				            <input name="longitudinalOpen.date"  placeholder="检测时间"  class="{required:true,date:true}"
				               data-format="yyyy-MM-dd" type="text"  value="<s:date name="longitudinalOpen.date" format="yyyy-MM-dd"/>"   ></input> <span class="add-on"> <i
				               data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
				            </span>
				         </td>
						<td style="text-align:right;"><strong class="text-success">纵缝张开量δz</strong></td>
						<td><input type="text" name="longitudinalOpen.value" class="{required:true,number:true}" value="<s:property value="longitudinalOpen.value"/>"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">纵缝张开位置</strong></td>
						<td>
							<select id="type" name="longitudinalOpen.type">
								<option value="1">和上一块张开</option>
								<option value="2">和下一块张开</option>
							</select>
						</td>
						<td style="text-align:right;"><strong class="text-success">是否出现严重连接缺陷</strong></td>
						<td>
							<select id="serious" name="longitudinalOpen.serious">
								<option value="1">否</option>
								<option value="2">是</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">简介</strong></td>
						<td><textarea type="text" rows="5" cols="60"  name="longitudinalOpen.des" class="{maxlength:512}"><s:property value="longitudinalOpen.des"/></textarea></td>
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



