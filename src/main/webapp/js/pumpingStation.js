function tunnelChanged(excluedeAll) {
	if (excluedeAll == undefined) {
		tunnelChangedInternal(true);
	} else {
		tunnelChangedInternal(excluedeAll);
	}
}

function tunnelSectionChanged(excluedeAll) {
	if (excluedeAll == undefined) {
		tunnelSectionChangedInternal(true);
	} else {
		tunnelSectionChangedInternal(excluedeAll);
	}
}

function tunnelChangedInternal(excluedeAll) {
	document.getElementById('tunnelSectionId').options.length = 0;
	var id = $('#tunnelId').val();
	$.ajax({
		type : "get",
		url : "queryAllTunnelSections.do?tunnelId=" + id,
		success : function(data, textStatus) {
			var obj = $('#tunnelSectionId');
			var value = data.tunnelSections;

			if (value != null) {
				if (!excluedeAll) {
					obj.append("<option value='0'>ALL</option>");
				}
				for ( var i = 0; i < value.length; i++) {
					obj.append("<option value='" + value[i].id + "'>"
							+ value[i].name + "</option>");
				}
			}
			$("#tunnelSectionId").change();
		}
	});
}

function tunnelSectionChangedInternal(excluedeAll) {
	document.getElementById('componentId').options.length = 0;
	var tunnelId = $('#tunnelId').val();
	var tunnelSectionId = $('#tunnelSectionId').val();
	$.ajax({
		type : "get",
		url : "queryAllPumpingStations.do?tunnelId=" + tunnelId
				+ "&tunnelSectionId=" + tunnelSectionId,
		success : function(data, textStatus) {
			var obj = $('#componentId');
			var value = data.pumpingStations;

			if (value != null) {
				if (!excluedeAll) {
					obj.append("<option value='0'>ALL</option>");
				}
				for ( var i = 0; i < value.length; i++) {
					obj.append("<option value='" + value[i].id + "'>"
							+ value[i].name + "</option>");
				}
			}
		}
	})
}