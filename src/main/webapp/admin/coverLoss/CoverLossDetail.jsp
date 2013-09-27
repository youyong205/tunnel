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
	$('#coverLossList').removeClass("btn-info");
	$('#coverLossList').addClass("btn-success");
	

	var type='<s:property value="coverLoss.type"/>';
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
						<input readonly type="hidden" name="index" value="<s:property value="index"/>"/>
						<input readonly type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input readonly type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
						<input readonly type="hidden" name="liningRingConstructionId" value="<s:property value="liningRingConstructionId"/>"/>
						<input readonly type="hidden" name="coverLoss.id" value="<s:property value="coverLoss.id"/>" />
					</tr>
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">隧道</strong></td>
						<td width="35%">
						<s:select name="coverLoss.tunnelId" id="tunnelId"
							onchange="tunnelChangedAndBlock()" 
							list="tunnels" listKey="id" listValue="name" 
							value="coverLoss.tunnelId" theme="simple" >
						</s:select></td>
						<td width="10%" style="text-align:right;"><strong class="text-success">盾构段</strong></td>
						<td width="40%">
						<s:select name="coverLoss.tunnelSectionId" id="tunnelSectionId"
							list="tunnelSections" listKey="id" listValue="name"
							onchange="tunnelSectionChangedAndBlock()" 
							value="coverLoss.tunnelSectionId" theme="simple" >
						</s:select> </td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">衬砌环</strong></td>
						<td>
							<s:select name="coverLoss.liningRingConstructionId" id="liningRingConstructionId"
							list="liningRingConstructions" listKey="id" listValue="name" onchange="liningRingChanged()"
							value="coverLoss.liningRingConstructionId" theme="simple" >
							</s:select> 
						</td>
						<td style="text-align:right;"><strong class="text-success">所在块</strong></td>
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
				            <input readonly name="coverLoss.date"  placeholder="检测时间"  class="{required:true,date:true}"
				               data-format="yyyy-MM-dd" type="text"  value="<s:date name="coverLoss.date" format="yyyy-MM-dd"/>"   ></input> <span class="add-on"> <i
				               data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
				            </span>
				         </td>
				         <td style="text-align:right;"><strong class="text-success">类型</strong></td>
						 <td>
							<select name="coverLoss.type"  id="type">
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
						<td><input readonly type="text" name="coverLoss.shape" value='<s:property value="coverLoss.shape"/>' class="{required:true,{maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">宽度(mm)</strong></td>
						<td><input readonly type="text" name="coverLoss.width" value='<s:property value="coverLoss.width"/>'  class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">高度(mm)</strong></td>
						<td><input readonly type="text" name="coverLoss.height"value='<s:property value="coverLoss.height"/>'  class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">深度(mm)</strong></td>
						<td><input readonly type="text" name="coverLoss.depth" value='<s:property value="coverLoss.depth"/>'  class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">面积(mm)</strong></td>
						<td><input readonly type="text" name="coverLoss.area" value='<s:property value="coverLoss.area"/>'  class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">备注信息</strong></td>
						<td><textarea readonly type="text" rows="5" cols="40"  name="coverLoss.des" class="{maxlength:512}"><s:property value="coverLoss.des"/></textarea></td>
					</tr>
					</table>
			</form>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>



