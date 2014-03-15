<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/struts-privilege.tld"%>

<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body>
		<h5 class="text-error">椭圆长轴方向变形<s:property value="liningRingDeformation.maxLength"/>mm，短轴方向变形<s:property value="liningRingDeformation.minLength"/>mm</h5>
		<h5 class="text-error">变形放大100倍，椭圆偏移角度<s:property value="liningRingDeformation.angle"/></h5>
		<s:property value="svgModel" escape="false"/>
</body>
</html>
