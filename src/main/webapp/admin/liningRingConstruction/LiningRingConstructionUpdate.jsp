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
			<form action="liningRingConstructionUpdateSubmit.do" id="form" method="post" >
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center">编辑衬砌环施工信息</h4></th>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input type="hidden" name="scheduleId" value="<s:property value="liningRingConstruction.scheduleId"/>"/>
						<input type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
						<input type="hidden" name="liningRingConstruction.documentId" value="<s:property value="liningRingConstruction.documentId"/>"/>
						<input type="hidden" name="liningRingConstruction.id" value="<s:property value="liningRingConstruction.id"/>" />
					</tr>
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td width="35%">
							<s:select name="liningRingConstruction.tunnelId" id="tunnelId"
								onchange="tunnelChangedExcluedeAll()" 
								list="tunnels" listKey="id" listValue="name" 
								value="liningRingConstruction.tunnelId" theme="simple" >
							</s:select>
						</td>
						<td width="15%" style="text-align:right;"><strong class="text-success">选择盾构段</strong></td>
						<td width="35%">
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
						<td>
							<select name="liningRingConstruction.lineType">
								<option value="上行">上行</option>
								<option value="下行">下行</option>
							</select>
						</td>
						<td style="text-align:right;"><strong class="text-success">衬砌环顺序号</strong></td>
						<td><input type="text" name="liningRingConstruction.sequence" 
						 value="<s:property value="liningRingConstruction.sequence"/>" class="{required:true,number:true}"/></td>
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
						<td style="text-align:right;"><strong class="text-success">备注信息</strong></td>
						<td colspan='3'><textarea type="text" rows="5" cols="40"  name="liningRingConstruction.des" class="{maxlength:512}"><s:property value="liningRingConstruction.des"/></textarea></td>
					</tr>
					</table>
		    		<table class="table table-striped table-bordered table-condensed table-hover">
		    				<tr>	
								<th colspan='20'><h4 class="text-info text-center">服役状态信息</h4></th>
							</tr>
		    				<tr><th style='text-align:right'>指标</td><th>综合指标</th>
		    				<s:iterator value="liningRingConstruction.girthOpenRingState.states" status="vs">
		    					<th>第<s:property value='#vs.index+1'/>块</th>
		    				</s:iterator></tr>
		    				<script type="text/javascript">
								$(document).ready(function() {
									$('#deformationState').val('<s:property value="liningRingConstruction.deformationState"/>');
									$('#longitudinalDeformationState').val('<s:property value="liningRingConstruction.longitudinalDeformationState"/>');
									$('#girthFaultState').val('<s:property value="liningRingConstruction.girthFaultState"/>');
									
									<s:iterator value="liningRingConstruction.cracksRingState.states" status="vs">
										$('#cracksState'+<s:property value="#vs.index"/>).val('<s:property value="state"/>');
				    				</s:iterator>
				    				<s:iterator value="liningRingConstruction.girthOpenRingState.states" status="vs">
										$('#girthOpenState'+<s:property value="#vs.index"/>).val('<s:property value="state"/>');
				    				</s:iterator>
				    				<s:iterator value="liningRingConstruction.girthFaultRingState.states" status="vs">
										$('#girthFaultState'+<s:property value="#vs.index"/>).val('<s:property value="state"/>');
			    					</s:iterator>
				    				<s:iterator value="liningRingConstruction.longitudinalOpenRingState.states" status="vs">
										$('#longitudinalOpenState'+<s:property value="#vs.index"/>).val('<s:property value="state"/>');
				    				</s:iterator>
				    				<s:iterator value="liningRingConstruction.longitudinalFaultRingState.states" status="vs">
										$('#longitudinalFaultState'+<s:property value="#vs.index"/>).val('<s:property value="state"/>');
				    				</s:iterator>
				    				<s:iterator value="liningRingConstruction.coverLossRingState.states" status="vs">
										$('#coverLossState'+<s:property value="#vs.index"/>).val('<s:property value="state"/>');
				    				</s:iterator>
								});
								</script>
		    				<tr><td style='text-align:right'>横断面变形</td>
		    					<td colspan='20'>
		    					<select name="liningRingConstruction.deformationState" id="deformationState">
		    						<option value="-">未检测</option>
		    						<option value="A">正常</option>
		    						<option value="B">退化</option>
		    						<option value="C">劣化</option>
		    						<option value="D">恶化</option>
		    						<option value="E">危险</option>
		    					</select>
		    					</td></tr>
    						<tr><td style='text-align:right'>纵断面变形</td>
    							<td colspan='20' >
    							<select name="liningRingConstruction.longitudinalDeformationState" id="longitudinalDeformationState">
		    						<option value="-">未检测</option>
		    						<option value="A">正常</option>
		    						<option value="B">退化</option>
		    						<option value="C">劣化</option>
		    						<option value="D">恶化</option>
		    						<option value="E">危险</option>
		    					</select>
    							</td></tr>
		    				<tr><td style='text-align:right'>环缝张开</td>
		    				<td><span class="level<s:property value="liningRingConstruction.girthOpenRingState.state"/>"><s:property value="liningRingConstruction.girthOpenRingState.state"/></span></td>
		    				<s:iterator value="liningRingConstruction.girthOpenRingState.states" status="vs">
		    					<td>
		    						<select name="girthOpenState" id="girthOpenState<s:property value='#vs.index'/>">
			    						<option value="-">未检测</option>
			    						<option value="A">正常</option>
			    						<option value="B">退化</option>
			    						<option value="C">劣化</option>
			    						<option value="D">恶化</option>
			    						<option value="E">危险</option>
			    					</select>
		    					<span class="level<s:property value="state"/>"><s:property value="state"/></span></td>
		    				</s:iterator></tr>
		    				<tr><td style='text-align:right'>环缝错台</td>
    							<td><span class="level<s:property value="liningRingConstruction.girthFaultRingState.state"/>"><s:property value="liningRingConstruction.girthFaultRingState.state"/></span></td>
		    					<s:iterator value="liningRingConstruction.girthFaultRingState.states" status="vs">
		    					<td>
		    						<select name="girthFaultState" id="girthFaultState<s:property value='#vs.index'/>">
			    						<option value="-">未检测</option>
			    						<option value="A">正常</option>
			    						<option value="B">退化</option>
			    						<option value="C">劣化</option>
			    						<option value="D">恶化</option>
			    						<option value="E">危险</option>
			    					</select>
		    					<span class="level<s:property value="state"/>"><s:property value="state"/></span></td>
		    				</s:iterator></tr>
		    				<tr><td style='text-align:right'>纵缝张开</td>
		    				<td><span class="level<s:property value="liningRingConstruction.longitudinalOpenRingState.state"/>"><s:property value="liningRingConstruction.longitudinalOpenRingState.state"/></span></td>
		    				<s:iterator value="liningRingConstruction.longitudinalOpenRingState.states" status="vs">
		    					<td>
		    						<select name="longitudinalOpenState" id="longitudinalOpenState<s:property value='#vs.index'/>">
			    						<option value="-">未检测</option>
			    						<option value="A">正常</option>
			    						<option value="B">退化</option>
			    						<option value="C">劣化</option>
			    						<option value="D">恶化</option>
			    						<option value="E">危险</option>
			    					</select>
		    					<span class="level<s:property value="state"/>"><s:property value="state"/></span></td>
		    				</s:iterator></tr>
		    				<tr><td style='text-align:right'>纵缝错台</td>
		    				<td><span class="level<s:property value="liningRingConstruction.longitudinalFaultRingState.state"/>"><s:property value="liningRingConstruction.longitudinalFaultRingState.state"/></span></td>
		    				<s:iterator value="liningRingConstruction.longitudinalFaultRingState.states" status="vs">
		    					<td>
		    						<select name="longitudinalFaultState" id="longitudinalFaultState<s:property value='#vs.index'/>">
			    						<option value="-">未检测</option>
			    						<option value="A">正常</option>
			    						<option value="B">退化</option>
			    						<option value="C">劣化</option>
			    						<option value="D">恶化</option>
			    						<option value="E">危险</option>
			    					</select>
			    					
		    					<span class="level<s:property value="state"/>"><s:property value="state"/></span></td>
		    				</s:iterator></tr>
		    				<tr><td style='text-align:right'>裂缝状态</td>
		    				<td><span class="level<s:property value="liningRingConstruction.cracksRingState.state"/>"><s:property value="liningRingConstruction.cracksRingState.state"/></span></td>
		    				<s:iterator value="liningRingConstruction.cracksRingState.states" status="vs">
		    						<td><select name="cracksState" id="cracksState<s:property value='#vs.index'/>">
			    						<option value="-">未检测</option>
			    						<option value="A">正常</option>
			    						<option value="B">退化</option>
			    						<option value="C">劣化</option>
			    						<option value="D">恶化</option>
			    						<option value="E">危险</option>
			    					</select>
			    					<span class="level<s:property value="state"/>"><s:property value="state"/></span>
		    					</td>
		    				</s:iterator></tr>
    						<tr><td style='text-align:right'>保护层损失</td>
    						<td><span class="level<s:property value="liningRingConstruction.coverLossRingState.state"/>"><s:property value="liningRingConstruction.coverLossRingState.state"/></span></td>
		    				<s:iterator value="liningRingConstruction.coverLossRingState.states" status="vs">
		    					<td>
		    						<select name="coverLossState" id="coverLossState<s:property value='#vs.index'/>">
			    						<option value="-">未检测</option>
			    						<option value="A">正常</option>
			    						<option value="B">退化</option>
			    						<option value="C">劣化</option>
			    						<option value="D">恶化</option>
			    						<option value="E">危险</option>
			    					</select>
		    					<span class="level<s:property value="state"/>"><s:property value="state"/></span></td>
		    				</s:iterator></tr>
    					</table>
					<%@include file="./../schedule/ScheduleUpdate.jsp"%>
					<table  class="table table-striped table-bordered table-condensed">
						<tr>
							<td  style="text-align:center;">
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



