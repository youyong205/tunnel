<%@ page contentType="text/html; charset=utf-8"%>
<div class="well sidebar-nav">
	<ul class="nav nav-list">
		<li id='liningRingBaseInfo' class="nav-header"><a href="userLiningRingConstructionList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="id"/>&index=<s:property value="index"/>">衬砌环信息</a></li>
		<li id='' class="nav-header">构件状态</li>
		<li id='liningRingDeformation' ><a style="padding-left:25px" href="userLiningRingDeformationQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">横断面变形</a></li>
		<li id='liningRingDeformation' ><a style="padding-left:25px" href="userLiningRingLongitudinalDeformationQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">纵断面变形</a></li>
		<li id='liningRingDeformation' ><a style="padding-left:25px" href="userCoverLossQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">保护层损失</a></li>
		<li id='liningRingDeformation' ><a style="padding-left:25px" href="userCracksQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">裂缝状态</a></li>
		<li class="nav-header">连接状态</li>
		<li id='liningRingDeformation' ><a style="padding-left:25px" href="userGirthOpenQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">环缝张开</a></li>
		<li id='liningRingDeformation' ><a style="padding-left:25px" href="userLongitudinalOpenQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">纵缝张开</a></li>
		<li id='liningRingDeformation' ><a style="padding-left:25px" href="userGirthFaultQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">环缝错台</a></li>
		<li id='liningRingDeformation' ><a style="padding-left:25px" href="userLongitudinalGirthFaultQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">纵缝错台</a></li>
		<li class="nav-header">其他状态</li>
		<li id='liningRingDeformation' ><a style="padding-left:25px" href="userSettlementQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">沉降</a></li>
		<li id='liningRingDeformation' ><a style="padding-left:25px" href="userSeepageQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">渗漏水</a></li>
		<li id='liningRingDeformation' ><a style="padding-left:25px" href="userRustQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">锈蚀</a></li>
	</ul>
</div>