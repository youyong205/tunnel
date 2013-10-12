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

<script src="chart/raphael-min.js" type="text/javascript"></script>
<script src="chart/venus.js" type="text/javascript"></script>
<script src="chart/common.js" type="text/javascript"></script>
<script src="chart/customevent.js" type="text/javascript"></script>
<script src="chart/svgchart_rebuild.js" type="text/javascript"></script>
<script src="chart/pie.js" type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#liningRingConstructionList').addClass('active');
		$('#liningRingBaseInfo').addClass('active');
		
		var graph = <s:property value="liningRingGraph.gsonString" escape="false"/>;
	    var _angle = graph.angle;
	    var _data = graph.blocks;
   		var _opt = {
	        pie:{
	            animation:"simultaneous",
	            hollow: 88,
	            showText:false,
	            rotate: _angle,
	            duration:2000,
	            stroke:{
	                'stroke-width':2,
	                'stroke':'#dfdfdf'
	            },
	            radius:100
	        },
	        legend:{}
	    };
	    new Venus.SvgChart("liningRing", _data, _opt);
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
			<li class="active"><a
				href='userLiningRingConstructionList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>'>衬砌环列表</a><span
				class="divider">/</span></li>
			<li class="active">详细信息</li>
		</ul>
	</div>
	<div class='row'>
		<div class='span2'>
			<%@include file="./../LiningRingMenu.jsp"%>
		</div>
		<div class='span10'>
			<div class="tabbable text-error" id="content"> <!-- Only required for left/right tabs -->
  			<ul class="nav nav-tabs">
   			 	<li class="active"><a href="#tab1"id="tab1Href" data-toggle="tab"><strong>衬砌环信息</strong></a></li>
   			 	<li class=""><a href="#tab2" id="tab2Href" data-toggle="tab"><strong>施工数据</strong></a></li>
   			 	<li class=""><a href="#tab3" id="tab3Href" data-toggle="tab"><strong>施工进度</strong></a></li>
  			</ul>
  			<div class="tab-content">
	    		<div class="tab-pane active" id="tab1">
		    			<div style="width:500px; height: 220px;" id="liningRing"></div>
	    				<div>
	    					<h4 class='text-error text-center'>构件状态【第一块和第二块张开量、错台显示在在第一块的状态上，以此类推】</h4>
			    			<table class="table table-striped table-bordered table-condensed table-hover">
			    				<tr><th style='text-align:right'>指标</td><th>综合指标</th>
			    				<s:iterator value="liningRingConstruction.girthOpenRingState.states" status="vs">
			    					<th>第<s:property value='#vs.index+1'/>块</th>
			    				</s:iterator></tr>
			    				<tr><td style='text-align:right'><a class='btn btn-small btn-primary' href="">横断面变形</a></td>
	    							<td>
				    					<s:if test="liningRingConstruction.deformationState == 'A'.toString()">a</s:if>
										<s:elseif test="liningRingConstruction.deformationState =='B'.toString()">b</s:elseif>
										<s:elseif test="liningRingConstruction.deformationState =='C'.toString()">c</s:elseif>
										<s:elseif test="liningRingConstruction.deformationState =='D'.toString()">d</s:elseif>
										<s:elseif test="liningRingConstruction.deformationState =='E'.toString()">e</s:elseif>
										<s:else>-</s:else>
			    					</td>
	    						
	    						<tr><td style='text-align:right'><a class='btn btn-small btn-primary' href="">纵断面变形</a></td>
	    							<td>
				    					<s:if test="liningRingConstruction.longitudinalDeformationState == 'A'.toString()">a</s:if>
										<s:elseif test="liningRingConstruction.longitudinalDeformationState =='B'.toString()">b</s:elseif>
										<s:elseif test="liningRingConstruction.longitudinalDeformationState =='C'.toString()">c</s:elseif>
										<s:elseif test="liningRingConstruction.longitudinalDeformationState =='D'.toString()">d</s:elseif>
										<s:elseif test="liningRingConstruction.longitudinalDeformationState =='E'.toString()">e</s:elseif>
										<s:else>-</s:else>
			    					</td>
	    						<tr><td style='text-align:right'><a class='btn btn-small btn-primary' href="">环缝错台</a></td>
	    							<td>
				    					<s:if test="liningRingConstruction.girthFaultState == 'A'.toString()">a</s:if>
										<s:elseif test="liningRingConstruction.girthFaultState =='B'.toString()">b</s:elseif>
										<s:elseif test="liningRingConstruction.girthFaultState =='C'.toString()">c</s:elseif>
										<s:elseif test="liningRingConstruction.girthFaultState =='D'.toString()">d</s:elseif>
										<s:elseif test="liningRingConstruction.girthFaultState =='E'.toString()">e</s:elseif>
										<s:else>-</s:else>
			    					</td>
	    						<tr><td style='text-align:right'><a class='btn btn-small btn-primary' href="">裂缝状态</a></td>
			    					<td>
				    					<s:if test="liningRingConstruction.cracksRingState.state == 'A'.toString()">a</s:if>
										<s:elseif test="liningRingConstruction.cracksRingState.state =='B'.toString()">b</s:elseif>
										<s:elseif test="liningRingConstruction.cracksRingState.state =='C'.toString()">c</s:elseif>
										<s:elseif test="liningRingConstruction.cracksRingState.state =='D'.toString()">d</s:elseif>
										<s:elseif test="liningRingConstruction.cracksRingState.state =='E'.toString()">e</s:elseif>
										<s:else>-</s:else>
			    					</td>
			    				<s:iterator value="liningRingConstruction.cracksRingState.states">
			    					<td>
				    					<s:if test="state == 'A'.toString()">a</s:if>
										<s:elseif test="state =='B'.toString()">b</s:elseif>
										<s:elseif test="state =='C'.toString()">c</s:elseif>
										<s:elseif test="state =='D'.toString()">d</s:elseif>
										<s:elseif test="state =='E'.toString()">e</s:elseif>
										<s:else>-</s:else>
			    					</td>
			    				</s:iterator></tr>
			    				<tr><td style='text-align:right'><a class='btn btn-small btn-primary' href="">环缝张开</a></td>
			    				<td>
				    					<s:if test="liningRingConstruction.girthOpenRingState.state == 'A'.toString()">a</s:if>
										<s:elseif test="liningRingConstruction.girthOpenRingState.state =='B'.toString()">b</s:elseif>
										<s:elseif test="liningRingConstruction.girthOpenRingState.state =='C'.toString()">c</s:elseif>
										<s:elseif test="liningRingConstruction.girthOpenRingState.state =='D'.toString()">d</s:elseif>
										<s:elseif test="liningRingConstruction.girthOpenRingState.state =='E'.toString()">e</s:elseif>
										<s:else>-</s:else>
			    					</td>
			    				<s:iterator value="liningRingConstruction.girthOpenRingState.states">
			    					<td>
				    					<s:if test="state == 'A'.toString()">a</s:if>
										<s:elseif test="state =='B'.toString()">b</s:elseif>
										<s:elseif test="state =='C'.toString()">c</s:elseif>
										<s:elseif test="state =='D'.toString()">d</s:elseif>
										<s:elseif test="state =='E'.toString()">e</s:elseif>
										<s:else>-</s:else>
			    					</td>
			    				</s:iterator></tr>
			    				<tr><td style='text-align:right'><a class='btn btn-small btn-primary' href="">纵缝张开</a></td>
			    				<td>
				    					<s:if test="liningRingConstruction.longitudinalOpenRingState.state == 'A'.toString()">a</s:if>
										<s:elseif test="liningRingConstruction.longitudinalOpenRingState.state =='B'.toString()">b</s:elseif>
										<s:elseif test="liningRingConstruction.longitudinalOpenRingState.state =='C'.toString()">c</s:elseif>
										<s:elseif test="liningRingConstruction.longitudinalOpenRingState.state =='D'.toString()">d</s:elseif>
										<s:elseif test="liningRingConstruction.longitudinalOpenRingState.state =='E'.toString()">e</s:elseif>
										<s:else>-</s:else>
			    					</td>
			    					<s:iterator value="liningRingConstruction.longitudinalOpenRingState.states">
			    					<td>
				    					<s:if test="state == 'A'.toString()">a</s:if>
										<s:elseif test="state =='B'.toString()">b</s:elseif>
										<s:elseif test="state =='C'.toString()">c</s:elseif>
										<s:elseif test="state =='D'.toString()">d</s:elseif>
										<s:elseif test="state =='E'.toString()">e</s:elseif>
										<s:else>-</s:else>
			    					</td>
			    				</s:iterator></tr>
			    				<tr><td style='text-align:right'><a class='btn btn-small btn-primary' href="">纵缝错台</a></td>
			    				<td>
				    					<s:if test="liningRingConstruction.longitudinalFaultRingState.state == 'A'.toString()">a</s:if>
										<s:elseif test="liningRingConstruction.longitudinalFaultRingState.state =='B'.toString()">b</s:elseif>
										<s:elseif test="liningRingConstruction.longitudinalFaultRingState.state =='C'.toString()">c</s:elseif>
										<s:elseif test="liningRingConstruction.longitudinalFaultRingState.state =='D'.toString()">d</s:elseif>
										<s:elseif test="liningRingConstruction.longitudinalFaultRingState.state =='E'.toString()">e</s:elseif>
										<s:else>-</s:else>
			    					</td>
			    					
			    				<s:iterator value="liningRingConstruction.longitudinalFaultRingState.states">
			    					<td>
				    					<s:if test="state == 'A'.toString()">a</s:if>
										<s:elseif test="state =='B'.toString()">b</s:elseif>
										<s:elseif test="state =='C'.toString()">c</s:elseif>
										<s:elseif test="state =='D'.toString()">d</s:elseif>
										<s:elseif test="state =='E'.toString()">e</s:elseif>
										<s:else>-</s:else>
			    					</td>
			    				</s:iterator></tr>
	    						<tr><td style='text-align:right'><a class='btn btn-small btn-primary' href="">保护层损失</a></td>
			    				<td>
				    					<s:if test="liningRingConstruction.coverLossRingState.state == 'A'.toString()">a</s:if>
										<s:elseif test="liningRingConstruction.coverLossRingState.state =='B'.toString()">b</s:elseif>
										<s:elseif test="liningRingConstruction.coverLossRingState.state =='C'.toString()">c</s:elseif>
										<s:elseif test="liningRingConstruction.coverLossRingState.state =='D'.toString()">d</s:elseif>
										<s:elseif test="liningRingConstruction.coverLossRingState.state =='E'.toString()">e</s:elseif>
										<s:else>-</s:else>
			    					</td>
			    				<s:iterator value="liningRingConstruction.coverLossRingState.states">
			    					<td>
				    					<s:if test="state == 'A'.toString()">a</s:if>
										<s:elseif test="state =='B'.toString()">b</s:elseif>
										<s:elseif test="state =='C'.toString()">c</s:elseif>
										<s:elseif test="state =='D'.toString()">d</s:elseif>
										<s:elseif test="state =='E'.toString()">e</s:elseif>
										<s:else>-</s:else>
			    					</td>
			    				</s:iterator></tr>
	    					</table>
	    				</div>
	    			</div>
	    		<div class="tab-pane" id="tab2">
	    			<table class="table table-striped table-bordered table-condensed">
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">所属隧道</strong></td>
						<td width="30%">
							<s:select name="liningRingConstruction.tunnelId" id="tunnelId"
								onchange="tunnelChangedExcluedeAll()" 
								list="tunnels" listKey="id" listValue="name" 
								value="liningRingConstruction.tunnelId" theme="simple" >
							</s:select>
						</td>
						<td width="20%" style="text-align:right;"><strong class="text-success">所属盾构段</strong></td>
						<td width="30%">
							<s:select name="liningRingConstruction.tunnelSectionId" id="tunnelSectionId"
								list="tunnelSections" listKey="id" listValue="name"
								value="liningRingConstruction.tunnelSectionId" theme="simple" >
							</s:select>
						</td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">衬砌环编号</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.name" value="<s:property value="liningRingConstruction.name"/>"  class="{required:true,maxlength:64}"/></td>
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
						<td><input readonly type="text" name="liningRingConstruction.sequence" 
						 value="<s:property value="liningRingConstruction.sequence"/>" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片拼装定位角(°)</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.positionAngle" value="<s:property value="liningRingConstruction.positionAngle"/>" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">计算人员</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.computingStaff" value="<s:property value="liningRingConstruction.computingStaff"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">检查人员</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.inspectors" value="<s:property value="liningRingConstruction.inspectors"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">测量人员</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.surveyors" value="<s:property value="liningRingConstruction.surveyors"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">横径（m）</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.diameter" value="<s:property value="liningRingConstruction.diameter"/>"  class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">竖径（m）</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.verticalDiameter" value="<s:property value="liningRingConstruction.verticalDiameter"/>"  class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片平面偏差</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.planeDeviation" value="<s:property value="liningRingConstruction.planeDeviation"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片高程偏差</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.elevationDeviation" value="<s:property value="liningRingConstruction.elevationDeviation"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙上</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.gapUp" value="<s:property value="liningRingConstruction.gapUp"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙下</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.gapDown" value="<s:property value="liningRingConstruction.gapDown"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙左</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.gapLeft" value="<s:property value="liningRingConstruction.gapLeft"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙右</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.gapRight" value="<s:property value="liningRingConstruction.gapRight"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙1</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.gap1" value="<s:property value="liningRingConstruction.gap1"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙2</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.gap2" value="<s:property value="liningRingConstruction.gap2"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙3</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.gap3" value="<s:property value="liningRingConstruction.gap3"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙4</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.gap4" value="<s:property value="liningRingConstruction.gap4"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙5</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.gap5" value="<s:property value="liningRingConstruction.gap5"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙6</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.gap6" value="<s:property value="liningRingConstruction.gap6"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙7</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.gap7" value="<s:property value="liningRingConstruction.gap7"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙8</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.gap8" value="<s:property value="liningRingConstruction.gap8"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">左上</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.leftUp" value="<s:property value="liningRingConstruction.leftUp"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">左下</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.leftDown" value="<s:property value="liningRingConstruction.leftDown"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">右上</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.rightUp" value="<s:property value="liningRingConstruction.rightUp"/>"  class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">右下</strong></td>
						<td><input readonly type="text"  name="liningRingConstruction.rightDown" value="<s:property value="liningRingConstruction.rightDown"/>"  class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">备注信息</strong></td>
						<td colspan='3'><textarea readonly  type="text" rows="5" cols="40"  name="liningRingConstruction.des" class="{maxlength:512}"><s:property value="liningRingConstruction.des"/></textarea></td>
					</tr>
					</table>
	    		</div>
	    		<div class="tab-pane" id="tab3">
	    			<%@include file="./../../admin/schedule/ScheduleDetail.jsp"%>
	    		</div>
	    	</div></div>
		</div>
	</div>
     
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
