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
	$('#coverLossList').removeClass("btn-info");
	$('#coverLossList').addClass("btn-success");
	$("#form").validate();
	$('#datetimepicker1').datetimepicker();

	var type='<s:property value="coverLoss.type"/>';
	$('#type').val(type);
	$('#serious').val(<s:property value="coverLoss.serious"/>);
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
			<form action="coverLossUpdateSubmit.do" id="form" method="post" >
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center">编辑<s:property value="actionModule"/>记录</h4></th>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
						<input type="hidden" name="liningRingConstructionId" value="<s:property value="liningRingConstructionId"/>"/>
						<input type="hidden" name="coverLoss.id" value="<s:property value="coverLoss.id"/>" />
					</tr>
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td width="35%">
						<s:select name="coverLoss.tunnelId" id="tunnelId"
							onchange="tunnelChangedAndBlock()" 
							list="tunnels" listKey="id" listValue="name" 
							value="coverLoss.tunnelId" theme="simple" >
						</s:select></td>
						<td width="15%" style="text-align:right;"><strong class="text-success">选择盾构段</strong></td>
						<td width="35%">
						<s:select name="coverLoss.tunnelSectionId" id="tunnelSectionId"
							list="tunnelSections" listKey="id" listValue="name"
							onchange="tunnelSectionChangedAndBlock()" 
							value="coverLoss.tunnelSectionId" theme="simple" >
						</s:select> </td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">选择衬砌环</strong></td>
						<td>
							<s:select name="coverLoss.liningRingConstructionId" id="liningRingConstructionId"
							list="liningRingConstructions" listKey="id" listValue="name" onchange="liningRingChanged()"
							value="coverLoss.liningRingConstructionId" theme="simple" >
							</s:select> 
						</td>
						<td style="text-align:right;"><strong class="text-success">选择所在块</strong></td>
						<td>
							<s:select name="coverLoss.blockIndex" id="liningRingBlockId"
							list="liningRingBlocks" listKey="blockIndex" listValue="blockIndex"
							theme="simple" >
							</s:select> 
						</td>
				    </tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">测量时间</strong></td>
						<td>
						<div id="datetimepicker1" class="input-append date">
				            <input name="coverLoss.date"  placeholder="检测时间"  class="{required:true,date:true}"
				               data-format="yyyy-MM-dd" type="text"  value="<s:date name="coverLoss.date" format="yyyy-MM-dd"/>"   ></input> <span class="add-on"> <i
				               data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
				            </span>
				         </td>
				         <td style="text-align:right;"><strong class="text-success">类型</strong></td>
						 <td>
							<select name="coverLoss.type" id="type">
								<option value="蜂窝">蜂窝</option>
								<option value="麻面">麻面</option>
								<option value="混凝土起层">混凝土起层</option>
								<option value="剥落">剥落</option>
								<option value="露筋">露筋</option>
								<option value="其他">其他</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">剥落形状</strong></td>
						<td><input type="text" name="coverLoss.shape" value='<s:property value="coverLoss.shape"/>' class="{required:true,{maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">宽度(mm)</strong></td>
						<td><input type="text" name="coverLoss.width" value='<s:property value="coverLoss.width"/>'  class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">厚度(mm)</strong></td>
						<td><input type="text" name="coverLoss.height"value='<s:property value="coverLoss.height"/>'  class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">深度(mm)</strong></td>
						<td><input type="text" name="coverLoss.depth" value='<s:property value="coverLoss.depth"/>'  class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">面积(mm)</strong></td>
						<td><input type="text" name="coverLoss.area" value='<s:property value="coverLoss.area"/>'  class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">是否出现严重问题</strong></td>
						<td>
							<select id="serious" name="coverLoss.serious">
								<option value="1">否</option>
								<option value="2">是</option>
							</select>
					</tr>
					<tr><td style="text-align:right;"><strong class="text-success">备注信息</strong></td>
						<td><textarea type="text" rows="5" cols="40"  name="coverLoss.des" class="{maxlength:512}"><s:property value="coverLoss.des"/></textarea></td>
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



