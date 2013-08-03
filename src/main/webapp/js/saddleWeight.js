function tunnelChanged(){
	document.getElementById('tunnelSectionId').options.length = 0;
	var id = $('#tunnelId').val();
	$.ajax({
		type : "get",
		url : "queryAllTunnelSections.do?tunnelId=" + id,
		success : function(data, textStatus) {
			var obj = $('#tunnelSectionId');
			var value = data.tunnelSections;

			if (value != null) {
				for ( var i = 0; i < value.length; i++) {
					obj.append("<option value='" + value[i].id + "'>"
							+ value[i].name + "</option>");
				}
			}
			tunnelSectionChanged();
		}
	});
}

function tunnelSectionChanged(){
	document.getElementById('componentId').options.length = 0;
	var tunnelId = $('#tunnelId').val();
	var tunnelSectionId=$('#tunnelSectionId').val();
	$.ajax({
		type : "get",
		url : "queryAllSaddleWeights.do?tunnelId=" + tunnelId+"&tunnelSectionId="+tunnelSectionId,
		success : function(data, textStatus) {
			var obj = $('#componentId');
			var value = data.saddleWeights;

			if (value != null) {
				for ( var i = 0; i < value.length; i++) {
					obj.append("<option value='" + value[i].id + "'>"
							+ value[i].name + "</option>");
				}
			}
		}
	})
}