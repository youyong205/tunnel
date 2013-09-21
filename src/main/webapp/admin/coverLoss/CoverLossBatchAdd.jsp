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
			<form action="coverLossBatchAddSubmit.do" id="form" method="post" enctype="multipart/form-data">
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<td colspan='2'><h4 class="text-info text-center">批量新增<s:property value="actionModule"/>记录</h4></td>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
						<input type="hidden" name="liningRingConstructionId" value="<s:property value="liningRingConstructionId"/>"/>
					</tr>
					<tr>
						<td style="text-align:right;" width="40%"><strong class="text-success">上传批量文件</strong></td>
						<td>
							<input type="file" name="upload" class="{required:true}"/>
				         </td>
					</tr>
					<tr>
						<td colspan='2' style="text-align:center;">
							<button  class="btn btn-small btn-primary"  type="submit" >提交</button></td>
					</tr>
				</table>
				<table class="table table-striped table-bordered table-condensed">
					<tr><td colspan='2'><h4 class="text-info text-center">导入excel的模板示例【<span class='text-error'>仅支持2003-2007版本格式，请转换到此版本，中间不要有空行</span>】</h4></td></tr>
					<tr><th width="20%">列号</th><th>字段说明</th></tr>
					<tr><td>第一列</td><td>衬砌环唯一编号</td></tr>
					<tr><td>第二列</td><td>所在块</td></tr>
					<tr><td>第三列</td><td>测量时间，时间格式为<span class='text-error'>（2001-01-01）</span></td></tr>
					<tr><td>第四列</td><td>类型<span class='text-error'>（蜂窝、麻面、混凝土起层、剥落、露筋，其他）</span></td></tr>
					<tr><td>第五列</td><td>剥落形状</td></tr>
					<tr><td>第六列</td><td>宽度(mm)</td></tr>
					<tr><td>第七列</td><td>高度(mm)</td></tr>
					<tr><td>第八列</td><td>深度(mm)</td></tr>
					<tr><td>第九列</td><td>面积(mm)</td></tr>
					<tr><td>第十列</td><td>备注信息</td></tr>
				</table>
				<table class="table table-striped table-bordered table-condensed">
					<tr><th>衬砌环唯一名称</th><th>所在块</th><th>测量时间</th><th>类型</th><th>剥落形状</th><th>宽度(mm)</th><th>高度(mm)</th><th>深度(mm)</th><th>面积(mm)</th><th>备注信息</th></tr>
					<tr><td>衬砌环001</td><td>蜂窝</td><td>2001-01-01</td><td>1</td><td>圆形</td><td>1</td><td>2</td><td>3</td><td>4</td><td>暂无说明</td></tr>
					<tr><td>衬砌环001</td><td>蜂窝</td><td>2001-01-02</td><td>1</td><td>圆形</td><td>1</td><td>2</td><td>3</td><td>4</td><td>暂无说明</td></tr>
					<tr><td>衬砌环001</td><td>蜂窝</td><td>2001-01-03</td><td>1</td><td>圆形</td><td>1</td><td>2</td><td>3</td><td>4</td><td>暂无说明</td></tr>
					<tr><td>衬砌环001</td><td>麻面</td><td>2001-01-04</td><td>1</td><td>圆形</td><td>1</td><td>2</td><td>3</td><td>4</td><td>暂无说明</td></tr>
					<tr><td>衬砌环001</td><td>麻面</td><td>2001-01-05</td><td>1</td><td>圆形</td><td>1</td><td>2</td><td>3</td><td>4</td><td>暂无说明</td></tr>
					<tr><td>衬砌环001</td><td>麻面</td><td>2001-01-06</td><td>1</td><td>圆形</td><td>1</td><td>2</td><td>3</td><td>4</td><td>暂无说明</td></tr>
					<tr><td>衬砌环001</td><td>混凝土起层</td><td>2001-01-07</td><td>1</td><td>圆形</td><td>1</td><td>2</td><td>3</td><td>4</td><td>暂无说明</td></tr>
					<tr><td>衬砌环001</td><td>混凝土起层</td><td>2001-01-08</td><td>1</td><td>圆形</td><td>1</td><td>2</td><td>3</td><td>4</td><td>暂无说明</td></tr>
					<tr><td>衬砌环001</td><td>混凝土起层</td><td>2001-01-09</td><td>1</td><td>圆形</td><td>1</td><td>2</td><td>3</td><td>4</td><td>暂无说明</td></tr>
					<tr><td>衬砌环001</td><td>混凝土起层</td><td>2001-01-10</td><td>1</td><td>圆形</td><td>1</td><td>2</td><td>3</td><td>4</td><td>暂无说明</td></tr>
				</table>
			</form>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>

