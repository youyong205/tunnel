<%@ page contentType="text/html; charset=utf-8"%>
<style>
	.subChild{
		font-size:small;
		padding:3px 20px;
	}
</style>
<div class="well sidebar-nav">
	<ul class="nav nav-list">
		<li id='tunnelSectionBaseInfo' class="nav-header"><a href="userTunnelSectionList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">基本信息</a></li>
		<li id='tunnelSectionState' class="nav-header"><a href="userTunnelSectionState.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>&typeDes=综合">服役状态</a></li>
		<li id='deformation'><a style="padding-left:25px" href="userTunnelSectionState.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>&type=deformation&typeDes=横断面变形">横断面变形</a></li>
		<li id='longitudinalDeformation'><a style="padding-left:25px" href="userTunnelSectionState.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>&type=longitudinalDeformation&typeDes=纵断面变形">纵断面变形</a></li>
		<li id='coverLoss'><a id='coverLoss' style="padding-left:25px" href="userTunnelSectionState.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>&type=coverLoss&typeDes=保护层损失">保护层损失</a></li>
		<li id='cracks'><a style="padding-left:25px" href="userTunnelSectionState.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>&type=cracks&typeDes=裂缝状态">裂缝状态</a></li>
		<li id='openSection'><a style="padding-left:25px" href="userTunnelSectionState.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>&type=openSection&typeDes=环缝张开">环缝张开</a></li>
		<li id='longitudinalOpen'><a style="padding-left:25px" href="userTunnelSectionState.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>&type=longitudinalOpen&typeDes=纵缝张开">纵缝张开</a></li>
		<li id='girthFault'><a style="padding-left:25px" href="userTunnelSectionState.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>&type=girthFault&typeDes=环缝错台">环缝错台</a></li>
		<li id='longitudinalFault'><a style="padding-left:25px" href="userTunnelSectionState.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>&type=longitudinalFault&typeDes=纵缝错台">纵缝错台</a></li>
		<li class="nav-header">同步构件</li>
		<li id='rectangleComponent'><a class='subChild' style="padding-left:30px" href="userRectangleComponentList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">口型构件</a></li>
		<li id='plank'><a class='subChild' style="padding-left:30px" href="userPlankList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">车道板</a></li>
		<li id='bracket'><a class='subChild' style="padding-left:30px" href="userBracketList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">牛腿</a></li>
		<li id='saddleWeight'><a class='subChild' style="padding-left:30px" href="userSaddleWeightList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">压重块</a></li>
		<li id='flueSheet'><a class='subChild' style="padding-left:30px" href="userFlueSheetList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">烟道板</a></li>
		<li id='pumpingStation'><a class='subChild' href="userPumpingStationList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">泵房</a></li>
		<li id='facility'><a class='subChild' href="userFacilityList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">设备</a></li>
		<li id='escape'><a class='subChild' href="userEscapeList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">逃生楼梯</a></li>
		<li id='linePipe'><a class='subChild' href="userLinePipeList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">预埋管线</a></li>
	</ul>
</div>