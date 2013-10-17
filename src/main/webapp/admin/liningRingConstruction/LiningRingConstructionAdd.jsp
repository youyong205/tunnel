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
			<form action="liningRingConstructionAddSubmit.do" id="form" method="post" enctype="multipart/form-data">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center">新增衬砌环信息</h4></th>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
					</tr>
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
						<td width="35%">
						<s:select name="liningRingConstruction.tunnelId" id="tunnelId"
							onchange="tunnelChangedExcluedeAll()" 
							list="tunnels" listKey="id" listValue="name" 
							value="tunnelId" theme="simple" >
						</s:select></td>
						<td width="15%" style="text-align:right;"><strong class="text-success">选择盾构段</strong></td>
						<td width="35%">
						<s:select name="liningRingConstruction.tunnelSectionId" id="tunnelSectionId"
							list="tunnelSections" listKey="id" listValue="name"
							value="tunnelSectionId" theme="simple" >
						</s:select> </td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">名称编号</strong></td>
						<td><input type="text" name="liningRingConstruction.name" class="{required:true,maxlength:64}"/></td>
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
						<td width="20%" style="text-align:right;"><strong class="text-success">衬砌环顺序号</strong></td>
						<td><input type="text" name="liningRingConstruction.sequence" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片拼装定位角(°)</strong></td>
						<td><input type="text" name="liningRingConstruction.positionAngle" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">计算人员</strong></td>
						<td><input type="text" name="liningRingConstruction.computingStaff" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">检查人员</strong></td>
						<td><input type="text" name="liningRingConstruction.inspectors" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">测量人员</strong></td>
						<td><input type="text" name="liningRingConstruction.surveyors" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">横径（m）</strong></td>
						<td><input type="text" name="liningRingConstruction.diameter" class="{required:true,number:true}"/></td>
						<td style="text-align:right;"><strong class="text-success">竖径（m）</strong></td>
						<td><input type="text" name="liningRingConstruction.verticalDiameter" class="{required:true,number:true}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片平面偏差</strong></td>
						<td><input type="text" name="liningRingConstruction.planeDeviation" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片高程偏差</strong></td>
						<td><input type="text" name="liningRingConstruction.elevationDeviation" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙上</strong></td>
						<td><input type="text" name="liningRingConstruction.gapUp" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙下</strong></td>
						<td><input type="text" name="liningRingConstruction.gapDown" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙左</strong></td>
						<td><input type="text" name="liningRingConstruction.gapLeft" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙右</strong></td>
						<td><input type="text" name="liningRingConstruction.gapRight" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙1</strong></td>
						<td><input type="text" name="liningRingConstruction.gap1" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙2</strong></td>
						<td><input type="text" name="liningRingConstruction.gap2" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙3</strong></td>
						<td><input type="text" name="liningRingConstruction.gap3" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙4</strong></td>
						<td><input type="text" name="liningRingConstruction.gap4" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙5</strong></td>
						<td><input type="text" name="liningRingConstruction.gap5" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙6</strong></td>
						<td><input type="text" name="liningRingConstruction.gap6" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片间隙7</strong></td>
						<td><input type="text" name="liningRingConstruction.gap7" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">管片间隙8</strong></td>
						<td><input type="text" name="liningRingConstruction.gap8" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">左上</strong></td>
						<td><input type="text" name="liningRingConstruction.leftUp" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">左下</strong></td>
						<td><input type="text" name="liningRingConstruction.leftDown" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">右上</strong></td>
						<td><input type="text" name="liningRingConstruction.rightUp" class="{required:true,maxlength:64}"/></td>
						<td style="text-align:right;"><strong class="text-success">右下</strong></td>
						<td><input type="text" name="liningRingConstruction.rightDown" class="{required:true,maxlength:64}"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">备注信息</strong></td>
						<td colspan='3'><textarea type="text" rows="5" cols="40"  name="liningRingConstruction.des" class="{maxlength:512}"></textarea></td>
					</tr>
					</table>
					<%@include file="./../schedule/ScheduleAdd.jsp"%>
					<table   class="table table-striped table-bordered table-condensed">
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

