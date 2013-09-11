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
<script type="text/javascript" src="js/liningRing.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$('#liningRingList').addClass("active");
	$("#form").validate();
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
					<thread><tr>	
						<th colspan='4'><h4 class="text-info text-center">衬砌环信息</h4></th>
						<input type="hidden" name="liningRing.id" value="<s:property value="liningRing.id"/>"/>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
					</tr></thread><tbody>
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">衬砌环名称</strong></td>
						<td width="35%"><input type="text" size="30" name="liningRing.name" readonly class="{required:true,maxlength:64}" value="<s:property value="liningRing.name"/>"/></td>
						<td width="15%" style="text-align:right;"><strong class="text-success">衬砌环类型</strong></td>
						<td width="35%"><input type="text" size="30" name="liningRing.type" readonly  class="{required:true,maxlength:64}" value="<s:property value="liningRing.type"/>"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片形式</strong></td>
						<td><input type="text" size="30" name="liningRing.pipeShape" readonly  class="{required:true,maxlength:64}" value="<s:property value="liningRing.pipeShape"/>"/></td>
						<td style="text-align:right;"><strong class="text-success">管片数量</strong></td>
						<td><input type="text" size="30" name="liningRing.pipeNumber" readonly   class="{required:true,digits:true}" value="<s:property value="liningRing.pipeNumber"/>"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片厚度(m)</strong></td>
						<td><input type="text" size="30" name="liningRing.pipeThickness" readonly   class="{required:true,number:true}" value="<s:property value="liningRing.pipeThickness"/>"/></td>
						<td style="text-align:right;"><strong class="text-success">管片宽度(m)</strong></td>
						<td><input type="text" size="30" name="liningRing.pipeWidth"  readonly  class="{required:true,number:true}" value="<s:property value="liningRing.pipeWidth"/>"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">楔形量</strong></td>
						<td><input type="text" size="30" name="liningRing.wedgeNumber" readonly  class="{required:true,digits:true}"  value="<s:property value="liningRing.wedgeNumber"/>"/></td>
						<td style="text-align:right;"><strong class="text-success">环初始偏移角度</strong></td>
						<td><input type="text" size="30" name="liningRing.angle" class="{required:true,digits:true}" value="<s:property value="liningRing.angle"/>"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">描述</strong></td>
						<td colspan='3'><textarea type="text" rows="5" cols="60"  name="liningRing.des"  readonly class="{maxlength:512}"><s:property value="liningRing.des"/></textarea></td>
					</tr>
						</tbody>
				</table>
					<table id="blockTable"  class="table table-striped table-bordered table-condensed">
						<thead>
						<tr>	
							<th colspan='8'>衬砌环块信息</th>
						</tr></thead><tbody>
						<s:iterator value="blocks" status="vs">
						<tr class="block" id="block0">
							<td width="5%"  style="text-align:right;"><strong class="text-success">顺序</strong></td>
							<td width="20%" ><input type="text" size="10" name="blocks[${vs.index}].blockIndex"  readonly value='<s:property value="blockIndex"/>'  class="{required:true,digits:true}"/></td>
							<td width="5%"  style="text-align:right;"><strong class="text-success">角度</strong></td>
							<td width="20%" ><input type="text" size="10" name="blocks[${vs.index}].angle"  readonly value='<s:property value="angle"/>'  class="{required:true,number:true,range:[0,360]}"/></td>
							<td width="5%"  style="text-align:right;"><strong class="text-success">颜色</strong></td>
							<td width="20%" ><input type="text" size="10" name="blocks[${vs.index}].color"  readonly value='<s:property value="color"/>' required/></td>
							<td width="5%"  style="text-align:right;"><strong class="text-success">描述</strong></td>
							<td width="15%" ><textarea type="text" rows="3" cols="40"  readonly  name="blocks[${vs.index}].des" class="{maxlength:512}"><s:property value="des"/></textarea></td>
						</tr>
						</s:iterator>
						</tbody>
					</table>
			</form>
			<table style="display:none">
				<tr id="block">
					<td width="5%" style="text-align:right;"><strong class="text-success">顺序</strong></td>
					<td width="20%" ><input type="text" size="10" name="blocks[0].blockIndex" class="{required:true,digits:true}"/></td>
					<td width="5%"  style="text-align:right;"><strong class="text-success">角度</strong></td>
					<td width="20%" ><input type="text" size="10" name="blocks[0].angle" class="{required:true,number:true,range:[0,360]}"/></td>
					<td width="5%"  style="text-align:right;"><strong class="text-success">颜色</strong></td>
					<td width="20%" ><input type="text" size="10" name="blocks[0].color" required/></td>
					<td width="5%"  style="text-align:right;"><strong class="text-success">描述</strong></td>
					<td width="15%" ><textarea type="text" rows="3" cols="40"  name="blocks[0].des"></textarea></td>
					<td width="5%" ><a href="javascript:removeBlock('block0')" class="btn btn-small btn-danger">删除</a></td>
				</tr>
			</table>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>



