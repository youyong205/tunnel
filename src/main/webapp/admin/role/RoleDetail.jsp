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

<script type="text/javascript">
$(document).ready(function() {
	$('#roleList').addClass("active");
	$('#role_role').val("<s:property value="role.role"/>");
	
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
				<table  class="table table-striped table-bordered table-condensed">
					<tr>	
						<th colspan='4'><h4 class="text-info text-center">角色信息详情</h4></th>
						<input readonly type="hidden" name="role.id" value="<s:property value="role.id"/>"/>
						<input readonly type="hidden" name="index" value="<s:property value="index"/>"/>
					</tr>
					<tr>
						<td width="20%" style="text-align:right;"><strong class="text-success">用户名</strong></td>
						<td colspan='3'>
							<input readonly type="text" size="60" name="role.name"  value="<s:property value="role.name"/>"  class="{required:true,maxlength:64}"/>
						</td>
					</tr>
					<s:iterator value="moduleResources" status="vs">
						<s:if test="#vs.index mod 2 ==0">
							<tr>
						</s:if>
							<td style="text-align:right;"><strong class="text-success text-right"><s:property value="key"/></strong></td>
							<td>
								<s:iterator value="value.resources">
									<s:if test="checked == true">
										<input readonly type="checkbox" name="resourceIdSelect"  checked value='<s:property value="id"/>' id="<s:property value="key"/>_<s:property value="name"/>"/>
									</s:if>
									<s:else>
										<input readonly type="checkbox" name="resourceIdSelect"  value='<s:property value="id"/>' id="<s:property value="key"/>_<s:property value="name"/>"/>
									</s:else>
										<label for="<s:property value="key"/>_<s:property value="name"/>"><s:property value="name"/></label>
								</s:iterator>
							</td>
						<s:if test="#vs.index % 2 ==1 ||#vs.last">
							</tr>
						</s:if>
					</s:iterator>
					<tr>
						<td style="text-align:right;"><strong class="text-success">备注信息</strong></td>
						<td  colspan='3'><textarea readonly type="text" rows="5" cols="80"   name="role.des" class="{maxlength:512}"><s:property value="role.name"/></textarea></td>
						
					</tr>
					</table>
			</form>
      </div>
    </div>
    <%@include file="./../Foot.jsp"%>
  </div>
</body>
</html>



