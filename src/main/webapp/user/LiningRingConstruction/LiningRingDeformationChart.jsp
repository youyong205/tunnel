<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts-tags.tld"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/struts-privilege.tld"%>

<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body>
			<h5>椭圆长轴方向变形 <span class="text-error"> <s:property value="liningRingDeformation.maxLength"/>mm</span>，短轴方向变形  <span class="text-error"><s:property value="liningRingDeformation.minLength"/>mm</span></h5>
			<h5>变形放大 <span class="text-error">100倍</span>，椭圆水平偏移角度 <span class="text-error"><s:property value="liningRingDeformation.angle"/></span></h5>
		<s:property value="svgModel" escape="false"/>
</body>
</html>
