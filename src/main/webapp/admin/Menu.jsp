<%@ page contentType="text/html; charset=utf-8"%>
<div class="span2" id="adminMenu">
	<div class="well sidebar-nav">
		<ul class="nav nav-list">
			<li class="nav-header" id="tunnelList"><a href="tunnelList.do">隧道信息</a></li>
			<li class="nav-child" id="tunnelSectionList"><a href="tunnelSectionList.do">盾构段</a></li>
			<li class="nav-child2"  id="liningRingConstructionList"><a href="liningRingConstructionList.do??tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>">衬砌环</a></li>
			<li id="liningRingMeasureList" class="nav-child3"><a href="liningRingDeformationList.do?tunnelId=<s:property value="tunnelId"/>">病害检测</a></li>
			<li id="liningRingConstructionInspectionList" class="nav-child3"><a href="liningRingConstructionInspectionList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>">施工质量</a></li>
			<li id="liningRingConstructionCuringList" class="nav-child3"><a href="liningRingConstructionCuringList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>">养护记录</a></li>
			<li class="nav-child2" >同步构件</li>
			<li id="rectangleComponentList" class="nav-child3"><a href="rectangleComponentList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>">口型构件</a></li>
			<li id="plankList" class="nav-child3"><a href="plankList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>">车道板</a></li>
			<li id="bracketList" class="nav-child3"><a href="bracketList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>">牛腿</a></li>
			<li id="saddleWeightList" class="nav-child3"><a href="saddleWeightList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>">压重块</a></li>
			<li id="flueSheetList" class="nav-child3"><a href="flueSheetList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>">烟道板</a></li>
			<li class="nav-child2" id="pumpingStationList"><a href="pumpingStationList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>">泵房</a></li>
			<li class="nav-child2" id="facilityList"><a href="facilityList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>">设备</a></li>
			<li class="nav-child2" id="escapeList"><a href="escapeList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>">逃生楼梯</a></li>
			<li class="nav-child2" id="linePipeList"><a href="linePipeList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>">预埋管线</a></li>
			<li class="nav-child" id="contactChannelList"><a href="contactChannelList.do?tunnelId=<s:property value="tunnelId"/>">联络通道</a></li>
			<li class="nav-child" id="workingWellList"><a href="workingWellList.do?tunnelId=<s:property value="tunnelId"/>">工作井</a></li>
			<li class="nav-child" id="buriedSectionList"><a href="buriedSectionList.do?tunnelId=<s:property value="tunnelId"/>">暗埋段</a></li>
			<li class="nav-child" id="openSectionList"><a href="openSectionList.do?tunnelId=<s:property value="tunnelId"/>">敞开段</a></li>
			<li class="nav-header" id="tunnelGraphList"><a href="tunnelGraphList.do">隧道剖面</a></li>
			<li class="nav-header">基础信息</li>
			<li class="nav-child" id="liningRingList"><a href="liningRingList.do">衬砌环</a></li>
			<li class="nav-child" id="constructionUnitList"><a href="constructionUnitList.do">工程单位</a></li>
			<li class="nav-child" id="documentList"><a href="documentList.do">工程文档</a></li>
			<li class="nav-child" id="mailRecordList"><a href="mailRecordList.do">系统邮件</a></li>
			<li class="nav-header">系统管理</li>
			<li id="userList" class="nav-child"><a href="userList.do">用户管理</a></li>
			<li id="roleList" class="nav-child"><a href="roleList.do">角色管理</a></li>
			<li id="resourceList" class="nav-child"><a href="resourceList.do">资源管理</a></li>
			<li id="logList" class="nav-child"><a href="logList.do">操作日志</a></li>
		</ul>
	</div>
</div>
