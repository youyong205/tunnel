<%@ page contentType="text/html; charset=utf-8"%>
<div class="well sidebar-nav">
	<ul class="nav nav-list">
		<li id='baseInfo' class="nav-header"><a href="userTunnelList.do?tunnelId=<s:property value="tunnelId"/>&index=<s:property value="index"/>">基本信息</a></li>
		<li id="contactChannel"><a style="padding-left:25px" href="userContactChannelList.do?tunnelId=<s:property value="tunnelId"/>&index=<s:property value="index"/>">联络通道</a></li>
		<li id="workingWell"><a style="padding-left:25px" href="userWorkingWellList.do?tunnelId=<s:property value="tunnelId"/>&index=<s:property value="index"/>">工作井</a></li>
		<li id="buriedSection"><a style="padding-left:25px" href="userBuriedSectionList.do?tunnelId=<s:property value="tunnelId"/>&index=<s:property value="index"/>">暗埋段</a></li>
		<li id="openSection"><a style="padding-left:25px" href="userOpenSectionList.do?tunnelId=<s:property value="tunnelId"/>&index=<s:property value="index"/>">敞开段</a></li>
		<li id="constructionUnitList" class="nav-header"><a href="userConstructionUnitList.do?tunnelId=<s:property value="tunnelId"/>&index=<s:property value="index"/>">施工单位</a></li>
	</ul>
</div>