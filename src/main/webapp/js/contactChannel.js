function tunnelChanged(){
	tunnelChangedInternal(true);
}

function tunnelChanged(excludeAll){
	tunnelChangedInternal(excludeAll);
}

function tunnelChangedInternal(excluedeAll){
	document.getElementById('componentId').options.length = 0;
	var id = $('#tunnelId').val();
	$.ajax({
		type : "get",
		url : "queryContactChannelListsByTunnelId.do?tunnelId=" + id,
		success : function(data, textStatus) {
			var obj = $('#componentId');
			var value = data.contactChannels;

			if (value != null) {
				if(!excluedeAll){
					obj.append("<option value='0'>ALL</option>");
				}
				for ( var i = 0; i < value.length; i++) {
					obj.append("<option value='" + value[i].id + "'>"
							+ value[i].name + "</option>");
				}
			}
		}
	});
}

