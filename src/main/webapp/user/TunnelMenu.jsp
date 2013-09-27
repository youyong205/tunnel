<%@ page contentType="text/html; charset=utf-8"%>
<div class="well sidebar-nav">
	<ul class="nav nav-list">
		<li id='baseInfo' class="nav-header"><a href="userTunnelList.do?tunnelId=<s:property value="tunnelId"/>&index=<s:property value="index"/>">基本信息</a></li>
		<li id='baseInfo' class="nav-header"><a href="userTunnelList.do?tunnelId=<s:property value="tunnelId"/>&index=<s:property value="index"/>">服役状态</a></li>
		<li id="contactChannel" class="nav-header"><a href="userContactChannelList.do?tunnelId=<s:property value="tunnelId"/>&index=<s:property value="index"/>">联络通道</a></li>
		<li id="workingWell" class="nav-header"><a href="userWorkingWellList.do?tunnelId=<s:property value="tunnelId"/>&index=<s:property value="index"/>">工作井</a></li>
		<li id="buriedSection" class="nav-header"><a href="userBuriedSectionList.do?tunnelId=<s:property value="tunnelId"/>&index=<s:property value="index"/>">暗埋段</a></li>
		<li id="openSection" class="nav-header"><a href="userOpenSectionList.do?tunnelId=<s:property value="tunnelId"/>&index=<s:property value="index"/>">敞开段</a></li>
		<li id="constructionUnitList" class="nav-header"><a href="userConstructionUnitList.do?tunnelId=<s:property value="tunnelId"/>&index=<s:property value="index"/>">施工单位</a></li>
	</ul>
</div>