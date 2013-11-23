function componentTypeChanged(tunnelId){
	var type = parseInt($('#componentType').val());
	
	if(type==1){
		$.ajax({
			type : "get",
			url : "queryAllTunnelSections.do?tunnelId=" + tunnelId,
			success : function(data, textStatus) {
				var obj = $('#componentId');
				var value = data.tunnelSections;
				document.getElementById('componentId').options.length = 0;
				
				if (value != null) {
					for ( var i = 0; i < value.length; i++) {
						obj.append("<option value='" + value[i].id + "'>"
								+ value[i].name + "</option>");
					}
				}
			}
		});
	}else if(type==2){
		$.ajax({
			type : "get",
			url : "queryWorkingWellListsByTunnelId.do?tunnelId=" + tunnelId,
			success : function(data, textStatus) {
				var obj = $('#componentId');
				var value = data.workingWells;
				document.getElementById('componentId').options.length = 0;
				
				if (value != null) {
					for ( var i = 0; i < value.length; i++) {
						obj.append("<option value='" + value[i].id + "'>"
								+ value[i].name + "</option>");
					}
				}
			}
		});
	}else if(type==3){
		$.ajax({
			type : "get",
			url : "queryOpenSectionListsByTunnelId.do?tunnelId=" + tunnelId,
			success : function(data, textStatus) {
				var obj = $('#componentId');
				var value = data.openSections;
				document.getElementById('componentId').options.length = 0;
				if (value != null) {
					for ( var i = 0; i < value.length; i++) {
						obj.append("<option value='" + value[i].id + "'>"
								+ value[i].name + "</option>");
					}
				}
			}
		});
	}
}

