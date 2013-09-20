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
	$('#liningRingConstructionList').addClass("active");
	$("#form").validate();
	var type='<s:property value="liningRingConstruction.lineType"/>';
	$('#lineType').val(type);
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
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center">衬砌环信息详情</h4></th>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
						<input type="hidden" name="liningRingConstruction.documentId" value="<s:property value="liningRingConstruction.documentId"/>"/>
						<input type="hidden" name="liningRingConstruction.id" value="<s:property value="liningRingConstruction.id"/>" />
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">横断面变形状态</strong></td>
						<td><input type="text" name="liningRingConstruction.deformationState" value="<s:property value="liningRingConstruction.deformationState"/>" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">纵断面变形状态</strong></td>
						<td><input type="text" name="liningRingConstruction.LongitudinalDeformationState" value="<s:property value="liningRingConstruction.LongitudinalDeformationState"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">环缝张开状态</strong></td>
						<td><input type="text" name="liningRingConstruction.girthOpenState" value="<s:property value="liningRingConstruction.girthOpenState"/>" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">纵缝张开状态</strong></td>
						<td><input type="text" name="liningRingConstruction.LongitudinalOpenState" value="<s:property value="liningRingConstruction.LongitudinalOpenState"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">环缝错台状态</strong></td>
						<td><input type="text" name="liningRingConstruction.girthFaultState" value="<s:property value="liningRingConstruction.girthFaultState"/>" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">纵缝错台状态</strong></td>
						<td><input type="text" name="liningRingConstruction.LongitudinalFaultState" value="<s:property value="liningRingConstruction.LongitudinalFaultState"/>"  class="{required:true,maxlength:64}"/></td>
					</tr><tr>
						<td style="text-align:right;"><strong class="text-success">保护层损失状态</strong></td>
						<td><input type="text" name="liningRingConstruction.coverLossState" value="<s:property value="liningRingConstruction.coverLossState"/>" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">裂缝状态</strong></td>
						<td><input type="text" name="liningRingConstruction.cracksState" value="<s:property value="liningRingConstruction.cracksState"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
				</table>
				<table class="table table-striped table-bordered table-condensed">
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td width="35%">
							<s:select name="liningRingConstruction.tunnelId" id="tunnelId"
								onchange="tunnelChangedExcluedeAll()" 
								list="tunnels" listKey="id" listValue="name" 
								value="liningRingConstruction.tunnelId" theme="simple" >
							</s:select>
						</td>
						<td width="10%" style="text-align:right;"><strong class="text-success">选择盾构段</strong></td>
						<td width="40%">
							<s:select name="liningRingConstruction.tunnelSectionId" id="tunnelSectionId"
								list="tunnelSections" listKey="id" listValue="name"
								value="liningRingConstruction.tunnelSectionId" theme="simple" >
							</s:select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">衬砌环编号</strong></td>
						<td><input type="text" name="liningRingConstruction.name" value="<s:property value="liningRingConstruction.name"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">衬砌环类型</strong></td>
						<td>
							<s:select name="liningRingConstruction.liningRingId" 
							list="liningRings" listKey="id" listValue="name" 
							theme="simple" >
						</s:select></td>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">线路类型</strong></td>
						<td colspa='3'>
							<select name="liningRingConstruction.lineType">
								<option value="上行">上行</option>
								<option value="下行">下行</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片拼装定位角(°)</strong></td>
						<td><input type="text" name="liningRingConstruction.positionAngle" value="<s:property value="liningRingConstruction.positionAngle"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">计算人员</strong></td>
						<td><input type="text" name="liningRingConstruction.computingStaff" value="<s:property value="liningRingConstruction.computingStaff"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">检查人员</strong></td>
						<td><input type="text" name="liningRingConstruction.inspectors" value="<s:property value="liningRingConstruction.inspectors"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">测量人员</strong></td>
						<td><input type="text" name="liningRingConstruction.surveyors" value="<s:property value="liningRingConstruction.surveyors"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">横径（m）</strong></td>
						<td><input type="text" name="liningRingConstruction.diameter" value="<s:property value="liningRingConstruction.diameter"/>"  class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">竖径（m）</strong></td>
						<td><input type="text" name="liningRingConstruction.verticalDiameter" value="<s:property value="liningRingConstruction.verticalDiameter"/>"  class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片平面偏差</strong></td>
						<td><input type="text" name="liningRingConstruction.planeDeviation" value="<s:property value="liningRingConstruction.planeDeviation"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片高程偏差</strong></td>
						<td><input type="text" name="liningRingConstruction.elevationDeviation" value="<s:property value="liningRingConstruction.elevationDeviation"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙上</strong></td>
						<td><input type="text" name="liningRingConstruction.gapUp" value="<s:property value="liningRingConstruction.gapUp"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙下</strong></td>
						<td><input type="text" name="liningRingConstruction.gapDown" value="<s:property value="liningRingConstruction.gapDown"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙左</strong></td>
						<td><input type="text" name="liningRingConstruction.gapLeft" value="<s:property value="liningRingConstruction.gapLeft"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙右</strong></td>
						<td><input type="text" name="liningRingConstruction.gapRight" value="<s:property value="liningRingConstruction.gapRight"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙1</strong></td>
						<td><input type="text" name="liningRingConstruction.gap1" value="<s:property value="liningRingConstruction.gap1"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙2</strong></td>
						<td><input type="text" name="liningRingConstruction.gap2" value="<s:property value="liningRingConstruction.gap2"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙3</strong></td>
						<td><input type="text" name="liningRingConstruction.gap3" value="<s:property value="liningRingConstruction.gap3"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙4</strong></td>
						<td><input type="text" name="liningRingConstruction.gap4" value="<s:property value="liningRingConstruction.gap4"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙5</strong></td>
						<td><input type="text" name="liningRingConstruction.gap5" value="<s:property value="liningRingConstruction.gap5"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙6</strong></td>
						<td><input type="text" name="liningRingConstruction.gap6" value="<s:property value="liningRingConstruction.gap6"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙7</strong></td>
						<td><input type="text" name="liningRingConstruction.gap7" value="<s:property value="liningRingConstruction.gap7"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙8</strong></td>
						<td><input type="text" name="liningRingConstruction.gap8" value="<s:property value="liningRingConstruction.gap8"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">左上</strong></td>
						<td><input type="text" name="liningRingConstruction.leftUp" value="<s:property value="liningRingConstruction.leftUp"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">左下</strong></td>
						<td><input type="text" name="liningRingConstruction.leftDown" value="<s:property value="liningRingConstruction.leftDown"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">右上</strong></td>
						<td><input type="text" name="liningRingConstruction.rightUp" value="<s:property value="liningRingConstruction.rightUp"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">右下</strong></td>
						<td><input type="text" name="liningRingConstruction.rightDown" value="<s:property value="liningRingConstruction.rightDown"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">简介</strong></td>
						<td colspan='3'><textarea type="text" rows="5" cols="60"  name="liningRingConstruction.des" class="{maxlength:512}"><s:property value="liningRingConstruction.des"/></textarea></td>
					</tr>
					</table>
					<%@include file="./../schedule/ScheduleDetail.jsp"%>
			</form>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>



