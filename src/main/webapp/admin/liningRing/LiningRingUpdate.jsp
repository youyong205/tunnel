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
	$('i[tips]').popover();
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
			<form action="liningRingUpdateSubmit.do" id="form" class="text-center" method="post">
				<table  class="table table-striped table-bordered table-condensed">
					<thread><tr>	
						<th colspan='4'><h4 class="text-info text-center">编辑衬砌环信息</h4></th>
						<input type="hidden" name="liningRing.id" value="<s:property value="liningRing.id"/>"/>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
					</tr></thread><tbody>
					<tr>
						<td width="15%" style="text-align:right;"><strong class="text-success">衬砌环名称</strong></td>
						<td width="35%"><input type="text" size="30" name="liningRing.name" class="{required:true,maxlength:64}" value="<s:property value="liningRing.name"/>"/></td>
						<td width="15%" style="text-align:right;"><strong class="text-success">衬砌环类型</strong></td>
						<td width="35%"><input type="text" size="30" name="liningRing.type" class="{required:true,maxlength:64}" value="<s:property value="liningRing.type"/>"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片形式</strong></td>
						<td><input type="text" size="30" name="liningRing.pipeShape" class="{required:true,maxlength:64}" value="<s:property value="liningRing.pipeShape"/>"/></td>
						<td style="text-align:right;"><strong class="text-success">管片数量</strong></td>
						<td><input type="text" size="30" name="liningRing.pipeNumber"  class="{required:true,digits:true}" value="<s:property value="liningRing.pipeNumber"/>"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">管片厚度(m)</strong></td>
						<td><input type="text" size="30" name="liningRing.pipeThickness"  class="{required:true,number:true}" value="<s:property value="liningRing.pipeThickness"/>"/></td>
						<td style="text-align:right;"><strong class="text-success">管片宽度(m)</strong></td>
						<td><input type="text" size="30" name="liningRing.pipeWidth"  class="{required:true,number:true}" value="<s:property value="liningRing.pipeWidth"/>"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">楔形量</strong></td>
						<td><input type="text" size="30" name="liningRing.wedgeNumber" class="{required:true,digits:true}"  value="<s:property value="liningRing.wedgeNumber"/>"/></td>
						<td style="text-align:right;"><strong class="text-success">环初始偏移角度</strong>
								<i tips="" data-trigger="hover" class="icon-question-sign" data-toggle="popover" data-placement="top" data-content="环默认第一块的初始角度是在水平右侧，如果您想调整环到左右对称，请自行计算偏移角度！"></i>
						</td>
						<td><input type="text" size="30" name="liningRing.angle" class="{required:true,number:true}" value="<s:property value="liningRing.angle"/>"/></td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">描述</strong></td>
						<td colspan='3'><textarea type="text" rows="5" cols="40"  name="liningRing.des" class="{maxlength:512}"><s:property value="liningRing.des"/></textarea></td>
					</tr>
						</tbody>
				</table>
					<table id="blockTable"  class="table table-striped table-bordered table-condensed">
						<thead>
						<tr>	
							<th colspan='8'>衬砌环块信息</th>
							<th colspan='1' width="5%"><a href="javascript:addBlock();" class="btn btn-small btn-success">新增</a></th>
						</tr></thead><tbody>
						<s:iterator value="blocks" status="vs">
							<tr class="block" id="block<s:property value='#vs.index'/>">
								<td width="5%"  style="text-align:right;"><strong class="text-success">顺序</strong></td>
								<td width="20%" ><input type="text" size="10" name="blocks[${vs.index}].blockIndex" value='<s:property value="blockIndex"/>'  class="{required:true,digits:true}"/></td>
								<td width="5%"  style="text-align:right;"><strong class="text-success">角度</strong></td>
								<td width="20%" ><input type="text" size="10" name="blocks[${vs.index}].angle" value='<s:property value="angle"/>'  class="{required:true,number:true,range:[0,360]}"/></td>
								<td width="5%"  style="text-align:right;"><strong class="text-success">颜色</strong></td>
								<td width="20%" ><input type="text" size="10" name="blocks[${vs.index}].color" value='<s:property value="color"/>' required/></td>
								<td width="5%"  style="text-align:right;"><strong class="text-success">描述</strong></td>
								<td width="15%" ><textarea type="text" rows="3" cols="30"  name="blocks[${vs.index}].des" class="{maxlength:512}"><s:property value="des"/></textarea></td>
								<td width="5%" ><a href="javascript:removeBlock('block${vs.index}')" class="btn btn-small btn-danger">删除</a></td>
							</tr>
						</s:iterator>
						</tbody>
					</table>
					<button  class="btn btn-small btn-primary" type="submit" >提交</button></td>
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
					<td width="15%" ><textarea type="text" rows="3" cols="30"  name="blocks[0].des"></textarea></td>
					<td width="5%" ><a href="javascript:removeBlock('block0')" class="btn btn-small btn-danger">删除</a></td>
				</tr>
			</table>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>



