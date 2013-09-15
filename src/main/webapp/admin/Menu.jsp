<%@ page contentType="text/html; charset=utf-8"%>
<div class="span2">
	<div class="well sidebar-nav fixed">
		<ul class="nav nav-list">
			<li class="nav-header" id="tunnelList"><a href="tunnelList.do">隧道信息</a></li>
			<li class="nav-header" id="tunnelSectionList"><a href="tunnelSectionList.do">盾构段</a></li>
			<li class="nav-header"  id="liningRingConstructionList"><a href="liningRingConstructionList.do??tunnelId=<s:property value="tunnelId"/>">衬砌环</a></li>
			<li id="liningRingMeasureList" class="nav-child"><a href="liningRingDeformationList.do?tunnelId=<s:property value="tunnelId"/>">病害检测</a></li>
			<li id="" class="nav-child"><a href="#">养护记录</a></li>
			<li id="" class="nav-child"><a href="#">病害状态</a></li>
			<li class="nav-header" >同步构件</li>
			<li id="rectangleComponentList" class="nav-child"><a href="rectangleComponentList.do?tunnelId=<s:property value="tunnelId"/>">口型构件</a></li>
			<li id="plankList" class="nav-child"><a href="plankList.do?tunnelId=<s:property value="tunnelId"/>">车道板</a></li>
			<li id="bracketList" class="nav-child"><a href="bracketList.do?tunnelId=<s:property value="tunnelId"/>">牛腿</a></li>
			<li id="saddleWeightList" class="nav-child"><a href="saddleWeightList.do?tunnelId=<s:property value="tunnelId"/>">压重块</a></li>
			<li id="flueSheetList" class="nav-child"><a href="flueSheetList.do?tunnelId=<s:property value="tunnelId"/>">烟道板</a></li>
			<li class="nav-header" id="pumpingStationList"><a href="pumpingStationList.do?tunnelId=<s:property value="tunnelId"/>">泵房</a></li>
			<li class="nav-header" id="facilityList"><a href="facilityList.do?tunnelId=<s:property value="tunnelId"/>">设备</a></li>
			<li class="nav-header" id="escapeList"><a href="escapeList.do?tunnelId=<s:property value="tunnelId"/>">逃生楼梯</a></li>
			<li class="nav-header" id="linePipeList"><a href="linePipeList.do?tunnelId=<s:property value="tunnelId"/>">预埋管线</a></li>
			<li class="nav-header" id="contactChannelList"><a href="contactChannelList.do?tunnelId=<s:property value="tunnelId"/>">联络通道</a></li>
			<li class="nav-header" id="workingWellList"><a href="workingWellList.do?tunnelId=<s:property value="tunnelId"/>">工作井</a></li>
			<li class="nav-header" id="buriedSectionList"><a href="buriedSectionList.do?tunnelId=<s:property value="tunnelId"/>">暗埋段</a></li>
			<li class="nav-header" id="openSectionList"><a href="openSectionList.do?tunnelId=<s:property value="tunnelId"/>">敞开段</a></li>
			<li class="nav-header">基础信息</li>
			<li class="nav-child" id="liningRingList"><a href="liningRingList.do">衬砌环</a></li>
			<li class="nav-child" id="constructionUnitList"><a href="constructionUnitList.do">工程单位</a></li>
			<li class="nav-child" id="documentList"><a href="documentList.do">工程文档</a></li>
			<li class="nav-header">系统管理</li>
			<li id="userList" class="nav-child"><a href="userList.do">用户管理</a></li>
			<li id="roleList" class="nav-child"><a href="roleList.do">角色管理</a></li>
			<li id="resourceList" class="nav-child"><a href="resourceList.do">资源管理</a></li>
			<li id="logList" class="nav-child"><a href="logList.do">操作日志</a></li>
		</ul>
	</div>
</div>
