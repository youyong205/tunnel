<%@ page contentType="text/html; charset=utf-8"%>
<div class="span2">
	<div class="well sidebar-nav fixed">
		<ul class="nav nav-list">
			<li class="nav-header" id="tunnelSectionList"><a href="userTunnelSectionList.do">盾构段</a></li>
				<li class="nav-child" id="rectangleComponentList" class="nav-child"><a href="rectangleComponentList.do?tunnelId=<s:property value="tunnelId"/>">口型构件</a></li>
				<li class="nav-child" id="plankList" class="nav-child"><a href="plankList.do?tunnelId=<s:property value="tunnelId"/>">车道板</a></li>
				<li class="nav-child" id="bracketList" class="nav-child"><a href="bracketList.do?tunnelId=<s:property value="tunnelId"/>">牛腿</a></li>
				<li class="nav-child" id="saddleWeightList" class="nav-child"><a href="saddleWeightList.do?tunnelId=<s:property value="tunnelId"/>">压重块</a></li>
				<li class="nav-child" id="flueSheetList" class="nav-child"><a href="flueSheetList.do?tunnelId=<s:property value="tunnelId"/>">烟道板</a></li>
			<li class="nav-header" id="pumpingStationList"><a href="pumpingStationList.do?tunnelId=<s:property value="tunnelId"/>">泵房</a></li>
			<li class="nav-header" id="facilityList"><a href="facilityList.do?tunnelId=<s:property value="tunnelId"/>">设备</a></li>
			<li class="nav-header" id="escapeList"><a href="escapeList.do?tunnelId=<s:property value="tunnelId"/>">逃生楼梯</a></li>
			<li class="nav-header" id="linePipeList"><a href="linePipeList.do?tunnelId=<s:property value="tunnelId"/>">预埋管线</a></li>
			<li class="nav-header" id="contactChannelList"><a href="contactChannelList.do?tunnelId=<s:property value="tunnelId"/>">联络通道</a></li>
			<li class="nav-header" id="workingWellList"><a href="workingWellList.do?tunnelId=<s:property value="tunnelId"/>">工作井</a></li>
			<li class="nav-header" id="buriedSectionList"><a href="buriedSectionList.do?tunnelId=<s:property value="tunnelId"/>">暗埋段</a></li>
			<li class="nav-header" id="openSectionList"><a href="openSectionList.do?tunnelId=<s:property value="tunnelId"/>">敞开段</a></li>
			<li class="nav-header">系统信息</li>
			<li class="nav-child" id="tunnelList"><a href="tunnelList.do">隧道信息</a></li>
			<li class="nav-child" id="constructionUnitList"><a href="constructionUnitList.do">工程单位</a></li>
			<li class="nav-child" id="documentList"><a href="documentList.do">工程文档</a></li>
		</ul>
	</div>
</div>
