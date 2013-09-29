<%@ page contentType="text/html; charset=utf-8"%>
<div class="well sidebar-nav">
	<ul class="nav nav-list">
		<li id='liningRingBaseInfo' class="nav-header"><a href="userLiningRingConstructionList.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="id"/>&index=<s:property value="index"/>">衬砌环信息</a></li>
		<li id='' class="nav-header">构件状态</li>
		<li id='liningRingDeformation' ><a style="padding-left:25px" href="userLiningRingDeformationQuery.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&liningRingConstructionId=<s:property value="liningRingConstructionId"/>">横断面变形</a></li>
		<li><a  style="padding-left:25px" href="">纵断面变形</a></li>
		<li><a  style="padding-left:25px" href="">保护层损失</a></li>
		<li><a  style="padding-left:25px" href="">裂缝状态</a></li>
		<li class="nav-header">连接状态</li>
		<li><a  style="padding-left:25px" href="">环缝张开</a></li>
		<li><a  style="padding-left:25px" href="">纵缝张开</a></li>
		<li><a  style="padding-left:25px" href="">环缝错台</a></li>
		<li><a  style="padding-left:25px" href="">纵缝错台</a></li>
		<li class="nav-header">其他状态</li>
		<li><a  style="padding-left:25px" href="">沉降</a></li>
		<li><a  style="padding-left:25px" href="">渗漏水</a></li>
		<li><a  style="padding-left:25px" href="">锈蚀</a></li>
	</ul>
</div>