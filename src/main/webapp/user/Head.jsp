<%@ page contentType="text/html; charset=utf-8"%>
<div class="navbar navbar-inverse" style="margin-top:5px;">
	<div class="navbar-inner navbar-title">
		<a class="brand" href="userTunnelList.do">盾构隧道建设与运营安全数据系统</a>
		<div class="nav-collapse collapse">
			<ul class="nav">
				<li id="tunnelList" class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">隧道<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="userTunnelList.do?tunnelId=<s:property value="tunnelId"/>&index=<s:property value="index"/>">隧道信息</a></li>
						<li><a style="padding:3px 30px" href="userContactChannelList.do?tunnelId=<s:property value="tunnelId"/>">联络通道</a></li>
						<li><a style="padding:3px 30px" href="userWorkingWellList.do?tunnelId=<s:property value="tunnelId"/>">工作井</a></li>
						<li><a style="padding:3px 30px"  href="userBuriedSectionList.do?tunnelId=<s:property value="tunnelId"/>">暗埋段</a></li>
						<li><a style="padding:3px 30px"  href="userOpenSectionList.do?tunnelId=<s:property value="tunnelId"/>">敞开段</a></li>
						<li><a href="userConstructionUnitList.do?tunnelId=<s:property value="tunnelId"/>">施工单位</a></li>
						<li><a href="userMailRecordList.do?tunnelId=<s:property value="tunnelId"/>&index=<s:property value="index"/>">系统邮件</a></li>
					</ul></li>
				<li id="tunnelSectionList" class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">盾构段<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="userTunnelSectionList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">盾构段信息</a></li>
						<li><a href="userTunnelSectionState.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>&typeDes=综合">服役状态</a></li>
						<li class="nav-header">同步构件</li>
						<li><a style="padding:3px 30px" href="userRectangleComponentList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">口型构件</a></li>
						<li><a style="padding:3px 30px" href="userPlankList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">车道板</a></li>
						<li><a style="padding:3px 30px" href="userBracketList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">牛腿</a></li>
						<li><a style="padding:3px 30px" href="userSaddleWeightList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">压重块</a></li>
						<li><a style="padding:3px 30px" href="userFlueSheetList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">烟道板</a></li>
						<li><a href="userPumpingStationList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">泵房</a></li>
						<li><a href="userFacilityList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">设备</a></li>
						<li><a href="userEscapeList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">逃生楼梯</a></li>
						<li><a href="userLinePipeList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">预埋管线</a></li>
					</ul></li>
				<li id="liningRingConstructionList" class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">衬砌环<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="userLiningRingConstructionList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="id"/>&index=<s:property value="index"/>">衬砌环信息</a></li>
						<li id='' class="nav-header">构件状态</li>
						<li><a style="padding:3px 30px" href="userLiningRingDeformationQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">横断面变形</a></li>
						<li><a style="padding:3px 30px" href="userLiningRingLongitudinalDeformationQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">纵断面变形</a></li>
						<li><a style="padding:3px 30px" href="userCoverLossQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">保护层损失</a></li>
						<li><a style="padding:3px 30px" href="userCracksQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">裂缝状态</a></li>
						<li class="nav-header">连接状态</li>
						<li><a style="padding:3px 30px" href="userGirthOpenQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">环缝张开</a></li>
						<li><a style="padding:3px 30px" href="userLongitudinalOpenQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">纵缝张开</a></li>
						<li><a style="padding:3px 30px" href="userGirthFaultQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">环缝错台</a></li>
						<li><a style="padding:3px 30px" href="userLongitudinalFaultQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">纵缝错台</a></li>
						<li class="nav-header">其他状态</li>
						<li><a style="padding:3px 30px" href="userSettlementQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">沉降</a></li>
						<li><a style="padding:3px 30px" href="userSeepageQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">渗漏水</a></li>
						<li><a style="padding:3px 30px" href="userRustQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">锈蚀</a></li>
										
					</ul></li>
				</ul>
			<p class="navbar-text pull-right">
					<s:if test="null==#session.user">
						<a href="#myModal" data-toggle="modal">登陆</a>
					</s:if>
					<s:else>
						欢迎您，<a href="#" class="navbar-link">${session.user.userName}</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="logout.do?role=user" >登出</a>
					</s:else>
				</p>
		</div>
	</div>
</div>

<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <form action="login.do" method="post">
	<table  class="table table-striped table-bordered table-condensed table-hover">
		<tr>	
			<th colspan='2'><h4 class="text-info text-center">用户登录</h4></th>
			<input type='hidden' name='role' value='user'/>
		</tr>
		<tr>
			<td style="text-align:right;"><strong class="text-success">用户名</strong></td>
			<td><input type="text" size="40" name="userName" /></td>
		</tr>
		<tr>
			<td style="text-align:right;"><strong class="text-success">密码</strong></td>
			<td><input type="text" size="40" name="password" /></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center;">
				<button  class="btn btn-small btn-primary"  type="submit" >登录</button></td>
		</tr>
	</table></form>
</div>