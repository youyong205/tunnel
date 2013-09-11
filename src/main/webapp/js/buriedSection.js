function tunnelChanged(){
	document.getElementById('componentId').options.length = 0;
	var id = $('#tunnelId').val();
	$.ajax({
		type : "get",
		url : "queryBuriedSectionListsByTunnelId.do?tunnelId=" + id,
		success : function(data, textStatus) {
			var obj = $('#componentId');
			var value = data.buriedSections;

			if (value != null) {
				for ( var i = 0; i < value.length; i++) {
					obj.append("<option value='" + value[i].id + "'>"
							+ value[i].name + "</option>");
				}
			}
		}
	});
}
