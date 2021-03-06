<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/struts-privilege.tld"%>

<!DOCTYPE html><html>
<head>
<title>软土盾构衬砌环变形检测后台管理系统</title>

<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css" >
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.metadata.js"></script>
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript" src="js/liningRingMeasuring.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#liningRingMeasureList').addClass('active');
		$('#girthOpenList').removeClass("btn-info");
		$('#girthOpenList').addClass("btn-success");
		$(".delete").bind("click", function() {
			return confirm("确定要删除此分类吗(不可恢复)？");
		});
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
      		<div class="row-fluid">
      			<div class="span12">
      				<form class="text-right form-inline margin-buttom" action="girthOpenList.do" method="post">
						<strong>选择隧道</strong>
						<s:select name="tunnelId" id="tunnelId"
							onchange="tunnelChanged()"  
							list="tunnels" listKey="id" listValue="name" 
							value="tunnelId" theme="simple" >
						</s:select>
						<strong>选择盾构段</strong>
						<s:select name="tunnelSectionId" id="tunnelSectionId"
							list="tunnelSections" listKey="id" listValue="name"
							onchange="tunnelSectionChanged(false)"
							value="tunnelSectionId" theme="simple" >
						</s:select>
						<strong>选择衬砌环</strong>
						<s:select name="liningRingConstructionId" id="liningRingConstructionId"
							list="liningRingConstructions" listKey="id" listValue="name"
							headerKey="0" headerValue="ALL"
							value="liningRingConstructionId" theme="simple" >
						</s:select> 
					  <button type="submit" class="btn btn-success btn-small">查询</button>
					</form>
      			</div>
      		</div>
      		<form action="girthOpenBatchDelete.do" id="form" method="post" onsubmit="return checkForm();">
					<input type="hidden" name="index" value="<s:property value="index"/>"/>
					<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
					<input type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
					<input type="hidden" name="liningRingConstructionId" value="<s:property value="liningRingConstructionId"/>"/>
				<table class="table table-striped table-bordered table-condensed table-hover">
				 <thead><tr>
					<th width="8%"><a id="selectAll" href="javascript:selectAll()" class="btn btn-small btn-primary">全选</a></th>
					<th width="5%">序号</th>
					<th width="27%">检测时间</th>
					<th width="20%">所在块</th>
					<th width="20%">环缝张开量δh</th>
					<th width="20%">
					<t:privilege res="衬砌环环缝张开检测模块:新增">
						<a class="btn btn-small btn-info" href="girthOpenAdd.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="parentTunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>&index=<s:property value="index"/>" >新增</a>
						<a class="btn btn-small btn-info" href="girthOpenBatchAdd.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="parentTunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>&index=<s:property value="index"/>" >导入</a>
					</t:privilege>
					<t:privilege res="衬砌环环缝张开检测模块:删除">
						<button  class="btn btn-small btn-danger" type="submit">批量删除</button></td>
					</t:privilege>
					</th>
				</tr></thead><tbody>
				<s:iterator value="girthOpens" status="vs">
					<tr>
					<td><input type="checkbox" name="deleteId" value="<s:property value='id'/>"/></td>
					<td><s:property value='#vs.index+1'/></td>
					<td><s:date name="date" format="yyyy-MM-dd"/></td>
					<td>第<s:property value='blockIndex'/>块</td>
					<td><s:property value='value'/></td>
					<td>
					<t:privilege res="衬砌环环缝张开检测模块:详情">
						<a class="btn btn-small btn-success" href="girthOpenDetail.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="parentTunnelSectionId"/>&girthOpenId=<s:property value="id"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>&index=<s:property value="index"/>">详情</a>
					</t:privilege>
					<t:privilege res="衬砌环环缝张开检测模块:编辑">
						<a class="btn btn-small btn-primary" href="girthOpenUpdate.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="parentTunnelSectionId"/>&girthOpenId=<s:property value="id"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>&index=<s:property value="index"/>">编辑</a>
					</t:privilege>
					<t:privilege res="衬砌环环缝张开检测模块:删除">
						<a class="delete btn  btn-small btn-danger" href="girthOpenDelete.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="parentTunnelSectionId"/>&girthOpenId=<s:property value="id"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>&index=<s:property value="index"/>">删除</a>
					</t:privilege>
					</td>
					</tr>
				</s:iterator></tbody>
			</table>
			</form>
			<div class="pagination text-center">
			  <ul>
			  	<li><a href="#">共${totalSize}记录&${totalPages}页</a></li>
			    <li><a href="?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>&index=1">首页</a></li>
				    <s:iterator id="item" value="pageIndexs" >
				    	<s:if test="${item == index }">
							<li class="disabled"><a href="?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>&index=${item}">${item}</a></li>
				    	</s:if>
				    	<s:else>
							<li><a href="?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>&index=${item}">${item}</a></li>
				    	</s:else>
				    </s:iterator>
			    <li><a href="?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>&index=${totalPages}">末页</a></li>
			  </ul>
			</div>
    </div>
  </div>
    <%@include file="./../Foot.jsp"%>
</body>
</html>
