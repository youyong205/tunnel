function tunnelChangedInternal(excluedeAll){
	document.getElementById('tunnelSectionId').options.length = 0;
	var id = $('#tunnelId').val();
	$.ajax({
		type : "get",
		url : "queryAllTunnelSections.do?tunnelId=" + id,
		success : function(data, textStatus) {
			var obj = $('#tunnelSectionId');
			var value = data.tunnelSections;

			if (value != null) {
				if(!excluedeAll){
					obj.append("<option value='0'>_ALL</option>");
				}
				for ( var i = 0; i < value.length; i++) {
					obj.append("<option value='" + value[i].id + "'>"
							+ value[i].name + "</option>");
				}
			}
		}
	})
}

function tunnelChanged() {
	tunnelChangedInternal(false);
}

function tunnelChangedExcluedeAll() {
	tunnelChangedInternal(true);
}

function workingWellTunnelSectionChange(){
	var id = $('#tunnelId').val();
	$.ajax({
		type : "get",
		url : "queryAllTunnelSections.do?tunnelId=" + id,
		success : function(data, textStatus) {
			var value = data.tunnelSections;

			if (value != null) {
				var html = '';
				for ( var i = 0; i < value.length; i++) {
					
					html = html+buildInput(value[i].name,value[i].id,i);
					
					$('#position').html(html);
				}
			}
		}
	})
}

function buildInput(name,value,index){
	var result =  '<input type="checkbox" name="tunnelSectionIdSelect" value="'+ value+ '" id="tunnelSectionIdSelect-"'+index+">";
	result = result + '&nbsp;<label for="tunnelSectionIdSelect-"'+index+ 'class="checkboxLabel">'+name+'</label>&nbsp;';
	
	return result;
}

