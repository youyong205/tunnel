<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/struts-privilege.tld"%>

<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>软土盾构隧道后台管理系统</title>
<link rel="stylesheet" type='text/css' href="css/bootstrap.min.css" >
<link rel='stylesheet' type='text/css' href='css/admin.css' />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="chart/raphael-min.js" type="text/javascript"></script>
<script src="chart/venus.js" type="text/javascript"></script>
<script src="chart/common.js" type="text/javascript"></script>
<script src="chart/customevent.js" type="text/javascript"></script>
<script src="chart/svgchart_rebuild.js" type="text/javascript"></script>
<script src="chart/pie.js" type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#liningRingList').addClass('active');
		$(".delete").bind("click", function() {
			return confirm("确定要删除此分类吗(不可恢复)？");
		});
	    
	    <s:iterator  value="lingRingGraphs" status="vs">
		    var graph = <s:property value="gsonString" escape="false"/>;
		    var _angle = graph.angle;
		    var _data = graph.blocks;
	   		var _opt = {
		        pie:{
		            animation:"simultaneous",
		            hollow: 80,
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
		    new Venus.SvgChart("pie<s:property value="#vs.index"/>", _data, _opt);
	    </s:iterator>
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
			<table class="table table-striped table-bordered table-condensed table-hover">
				 <thead><tr>
					<th colspan='4' style="text-align:right;padding-right:15px;">操作
						<t:privilege res="衬砌环模块:新增">
						<a class="space btn btn-small btn-info" href="liningRingAdd.do?index=<s:property value="index"/>" >新增</a>
						</t:privilege>
					</th>
				</tr></thead><tbody>
				<s:iterator value="liningRings" status="vs">
					<s:if test="#vs.odd == true">
						<tr>
					</s:if>
					<td>
						<div>【<s:property value="type"/>】<s:property value="name"/></div>
						<div style="width:400px; height: 250px;" id="pie<s:property value="#vs.index"/>"></div>
					</td>
					<td>
						<t:privilege res="衬砌环模块:详情">
						<a class="btn btn-small btn-success" href="liningRingDetail.do?liningRingId=<s:property value="id"/>&index=<s:property value="index"/>">详情</a>
						</t:privilege>
						<t:privilege res="衬砌环模块:编辑">
						<a class="btn btn-small btn-primary" href="liningRingUpdate.do?liningRingId=<s:property value="id"/>&index=<s:property value="index"/>">编辑</a>
						</t:privilege>
						<t:privilege res="衬砌环模块:删除">
						<a class="delete btn  btn-small btn-danger" href="liningRingDelete.do?liningRingId=<s:property value="id"/>&index=<s:property value="index"/>">删除</a>
						</t:privilege>
					</td>
					<s:if test="#vs.even ==true  || vs.last">
						</tr>
					</s:if>
				</s:iterator></tbody>
			</table>
			<div class="pagination text-center">
			  <ul>
			  	<li><a href="#">共${totalSize}记录，${totalPages}页</a></li>
			    <li><a href="?index=1">首页</a></li>
				    <s:iterator id="item" value="pageIndexs" >
				    	<s:if test="${item == index }">
							<li class="disabled"><a href="?index=${item}">${item}</a></li>
				    	</s:if>
				    	<s:else>
							<li><a href="?index=${item}">${item}</a></li>
				    	</s:else>
				    </s:iterator>
			    <li><a href="?index=${totalPages}">末页</a></li>
			  </ul>
			</div>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>
