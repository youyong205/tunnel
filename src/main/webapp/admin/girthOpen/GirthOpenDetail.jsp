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
	$('#girthOpenList').removeClass("btn-info");
	$('#girthOpenList').addClass("btn-success");
	var serious='<s:property value="girthOpen.serious"/>';
	$('#serious').val(serious);
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
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center"><s:property value="actionModule"/>记录详情</h4></th>
						<input readonly type="hidden" name="index" value="<s:property value="index"/>"/>
						<input readonly type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input readonly type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
						<input readonly type="hidden" name="liningRingConstructionId" value="<s:property value="liningRingConstructionId"/>"/>
						<input readonly type="hidden" name="girthOpen.id" value="<s:property value="girthOpen.id"/>" />
					</tr>
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">隧道</strong></td>
						<td width="35%">
						<s:select name="girthOpen.tunnelId" id="tunnelId"
							onchange="tunnelChangedAndBlock()" 
							list="tunnels" listKey="id" listValue="name" 
							value="girthOpen.tunnelId" theme="simple" >
						</s:select></td>
						<td width="15%" style="text-align:right;"><strong class="text-success">盾构段</strong></td>
						<td width="35%">
						<s:select name="girthOpen.tunnelSectionId" id="tunnelSectionId"
							list="tunnelSections" listKey="id" listValue="name"
							onchange="tunnelSectionChangedAndBlock()" 
							value="girthOpen.tunnelSectionId" theme="simple" >
						</s:select> </td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">衬砌环</strong></td>
						<td>
							<s:select name="girthOpen.liningRingConstructionId" id="liningRingConstructionId"
							list="liningRingConstructions" listKey="id" listValue="name" onchange="liningRingChanged()"
							value="girthOpen.liningRingConstructionId" theme="simple" >
							</s:select> 
						</td>
						<td style="text-align:right;"><strong class="text-success">所在块</strong></td>
						<td>
							<s:select name="girthOpen.blockIndex" id="liningRingBlockId"
							list="liningRingBlocks" listKey="blockIndex" listValue="blockIndex"
							theme="simple" >
							</s:select> 
						</td>
				    </tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">测量时间</strong></td>
						<td>
						<div id="datetimepicker1" class="input-append date">
				            <input readonly name="girthOpen.date"  placeholder="检测时间"  class="{required:true,date:true}"
				               data-format="yyyy-MM-dd" type="text"  value="<s:date name="girthOpen.date" format="yyyy-MM-dd"/>"   ></input> <span class="add-on"> <i
				               data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
				            </span>
				         </td>
						<td style="text-align:right;"><strong class="text-success">测点</strong></td>
						<td><input readonly type="text" name="girthOpen.measuringPoing" class="{required:true,maxlength:64}"  value="<s:property value="girthOpen.measuringPoing"/>"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">环缝张开量δh</strong></td>
						<td><input readonly type="text" name="girthOpen.value" class="{required:true,number:true}" value="<s:property value="girthOpen.value"/>"/></td>
						<td style="text-align:right;"><strong class="text-success">环缝张开位置</strong></td>
						<td>
							<select id="type" name="girthOpen.type">
								<option value="1">和上一环张开</option>
								<option value="2">和下一环张开</option>
							</select>
						</td>
					<tr>
						<td style="text-align:right;"><strong class="text-success">是否出现严重连接缺陷</strong></td>
						<td>
							<select id="serious" name="girthOpen.serious">
								<option value="1">否</option>
								<option value="2">是</option>
							</select>
						</td>
						<td style="text-align:right;"><strong class="text-success">备注信息</strong></td>
						<td><textarea readonly type="text" rows="5" cols="40"  name="girthOpen.des" class="{maxlength:512}"><s:property value="girthOpen.des"/></textarea></td>
					</tr>
					</table>
			</form>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>



