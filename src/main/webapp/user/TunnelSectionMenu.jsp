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
		<li id='tunnelSectionState' class="nav-header"><a href="userTunnelSectionState.do?tunnelId=<s:property value="tunnelId"/>&tunnelSectionId=<s:property value="tunnelSectionId"/>&index=<s:property value="index"/>">服役状态</a></li>
		<li class="nav-header">同步构件</li>
		<li><a class='subChild' style="padding-left:30px" href="">口型构件</a></li>
		<li><a class='subChild' style="padding-left:30px" href="">车道板</a></li>
		<li><a class='subChild' style="padding-left:30px" href="">牛腿</a></li>
		<li><a class='subChild' style="padding-left:30px" href="">压重块</a></li>
		<li><a class='subChild' style="padding-left:30px" href="">烟道板</a></li>
		<li class="nav-header"><a href="">泵房</a></li>
		<li class="nav-header"><a href="">设备</a></li>
		<li class="nav-header"><a href="">逃生楼梯</a></li>
		<li class="nav-header"><a href="">预埋管线</a></li>
	</ul>
</div>