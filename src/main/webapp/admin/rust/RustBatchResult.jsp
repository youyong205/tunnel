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
	$('#rustList').removeClass("btn-info");
	$('#rustList').addClass("btn-success");
	$("#form").validate();
	$('#datetimepicker1').datetimepicker();
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
				<table  class="table table-striped table-bordered table-condensed table-hover">
					<tr>	
						<td colspan='2'><h4 class="text-info text-center">批量新增<s:property value="actionModule"/>记录结果</h4></td>
						<input type="hidden" name="index" value="<s:property value="index"/>"/>
						<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
						<input type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
						<input type="hidden" name="liningRingConstructionId" value="<s:property value="liningRingConstructionId"/>"/>
					</tr>
					<tr>
						<td width='20%' style="text-align:right;"><strong class="text-success">成功新增记录条数</strong></td>
						<td>
							<s:property value="batchInsertResult.success"/>
				         </td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">失败新增记录条数</strong></td>
						<td>
							<s:property value="batchInsertResult.fail"/>
				         </td>
					</tr>
					<tr>
						<td style="text-align:right;"><strong class="text-success">excel中错误行</strong></td>
						<td>
							<s:property value="batchInsertResult.failLines"/>
				         </td>
					</tr>
					<tr>
						<td colspan='2' style="text-align:center;">
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

