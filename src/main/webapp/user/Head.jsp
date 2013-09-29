<%@ page contentType="text/html; charset=utf-8"%>
<div class="navbar navbar-inverse" style="margin-top:5px;">
	<div class="navbar-inner navbar-title">
		<a class="brand" href="userTunnelList.do">隧道管理信息系统</a>
		<div class="nav-collapse collapse">
			<ul class="nav">
				<li id="tunnelList" class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">隧道<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="userTunnelList.do?tunnelId=<s:property value="tunnelId"/>&index=<s:property value="index"/>">基本信息</a></li>
						<li><a href="userTunnelList.do?tunnelId=<s:property value="tunnelId"/>&index=<s:property value="index"/>">服役状态</a></li>
						<li><a href="userContactChannelList.do?tunnelId=<s:property value="tunnelId"/>">联络通道</a></li>
						<li><a href="userWorkingWellList.do?tunnelId=<s:property value="tunnelId"/>">工作井</a></li>
						<li><a href="userBuriedSectionList.do?tunnelId=<s:property value="tunnelId"/>">暗埋段</a></li>
						<li><a href="userOpenSectionList.do?tunnelId=<s:property value="tunnelId"/>">敞开段</a></li>
						<li><a href="userConstructionUnitList.do?tunnelId=<s:property value="tunnelId"/>">施工单位</a></li>
					</ul></li>
				<li id="tunnelSectionList" class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">盾构段<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="userTunnelSectionList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">基本信息</a></li>
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
						<li><a href="userLiningRingConstructionList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="id"/>&index=<s:property value="index"/>">基本信息</a></li>
						<li><a href="">横断面变形</a></li>
						<li><a href="">纵断面变形</a></li>
						<li><a href="">环缝张开</a></li>
						<li><a href="">纵缝张开</a></li>
						<li><a href="">环缝错台</a></li>
						<li><a href="">纵缝错台</a></li>
						<li><a href="">保护层损失</a></li>
						<li><a href="">裂缝</a></li>
						<li><a href="">沉降</a></li>
						<li><a href="">渗漏水</a></li>
						<li><a href="">锈蚀</a></li>
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