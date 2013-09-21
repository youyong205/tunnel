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
	$('#liningRingMeasureList').addClass("active");
	$('#longitudinalFaultList').removeClass("btn-info");
	$('#longitudinalFaultList').addClass("btn-success");
	
	
	var type='<s:property value="longitudinalFault.type"/>';
	$('#type').val(type);
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
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center"><s:property value="actionModule"/>记录详情</h4></th>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
						<input type="hidden" name="liningRingConstructionId" value="<s:property value="liningRingConstructionId"/>"/>
						<input type="hidden" name="longitudinalFault.id" value="<s:property value="longitudinalFault.id"/>" />
					</tr>
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td width="35%">
						<s:select name="longitudinalFault.tunnelId" id="tunnelId"
							onchange="tunnelChangedAndBlock()" 
							list="tunnels" listKey="id" listValue="name" 
							value="longitudinalFault.tunnelId" theme="simple" >
						</s:select></td>
						<td width="10%" style="text-align:right;"><strong class="text-success">选择盾构段</strong></td>
						<td width="40%">
						<s:select name="longitudinalFault.tunnelSectionId" id="tunnelSectionId"
							list="tunnelSections" listKey="id" listValue="name"
							onchange="tunnelSectionChangedAndBlock()" 
							value="longitudinalFault.tunnelSectionId" theme="simple" >
						</s:select> </td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">选择衬砌环</strong></td>
						<td>
							<s:select name="longitudinalFault.liningRingConstructionId" id="liningRingConstructionId"
							list="liningRingConstructions" listKey="id" listValue="name" onchange="liningRingChanged()"
							value="longitudinalFault.liningRingConstructionId" theme="simple" >
							</s:select> 
						</td>
						<td style="text-align:right;"><strong class="text-success">选择所在块</strong></td>
						<td>
							<s:select name="longitudinalFault.blockIndex" id="liningRingBlockId"
							list="liningRingBlocks" listKey="blockIndex" listValue="blockIndex"
							theme="simple" >
							</s:select> 
						</td>
				    </tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">测量时间</strong></td>
						<td>
						<div id="datetimepicker1" class="input-append date">
				            <input name="longitudinalFault.date"  placeholder="检测时间"  class="{required:true,date:true}"
				               data-format="yyyy-MM-dd" type="text"  value="<s:date name="longitudinalFault.date" format="yyyy-MM-dd"/>"   ></input> <span class="add-on"> <i
				               data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
				            </span>
				         </td>
						<td style="text-align:right;"><strong class="text-success">错台量Δz (mm)</strong></td>
						<td><input type="text" name="longitudinalFault.value" class="{required:true,number:true}" value="<s:property value="longitudinalFault.value"/>"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">纵缝错台位置</strong></td>
						<td>
							<select id="type" name="longitudinalFault.type">
								<option value="1">和上一块纵缝错台</option>
								<option value="2">和上一块纵缝错台</option>
							</select>
						</td>
						<td style="text-align:right;"><strong class="text-success">备注信息</strong></td>
						<td><textarea type="text" rows="5" cols="60"  name="longitudinalFault.des" class="{maxlength:512}"><s:property value="longitudinalFault.des"/></textarea></td>
					</tr>
					</table>
			</form>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>



