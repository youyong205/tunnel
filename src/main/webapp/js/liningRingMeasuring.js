function tunnelChanged() {
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
	})
}


function tunnelChangedExcluedeAll() {
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
			tunnelSectionChanged(true);
		}
	})
}

function tunnelSectionChanged(excludeAll) {
	document.getElementById('liningRingConstructionId').options.length = 0;
	var id = $('#tunnelSectionId').val();
	$.ajax({
		type : "get",
		url : "queryAllLiningRingConstruction.do?tunnelSectionId=" + id,
		success : function(data, textStatus) {
			var obj = $('#liningRingConstructionId');
			var value = data.liningRingConstructions;

			if (value != null) {
				if(!excludeAll){
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

function tunnelChangedAndBlock() {
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
			tunnelSectionChangedAndBlock();
		}
	})
}

function tunnelSectionChangedAndBlock(){
	document.getElementById('liningRingConstructionId').options.length = 0;
	var id = $('#tunnelSectionId').val();
	$.ajax({
		type : "get",
		url : "queryAllLiningRingConstruction.do?tunnelSectionId=" + id,
		success : function(data, textStatus) {
			var obj = $('#liningRingConstructionId');
			var value = data.liningRingConstructions;

			if (value != null) {
				for ( var i = 0; i < value.length; i++) {
					obj.append("<option value='" + value[i].id + "'>"
							+ value[i].name + "</option>");
				}
			}
			liningRingChanged();
		}
	});
}

function liningRingChanged(){
	document.getElementById('liningRingBlockId').options.length = 0;
	var id = $('#liningRingConstructionId').val();
	$.ajax({
		type : "get",
		url : "queryAllLiningRingBlocks.do?liningRingConstructionId=" + id,
		success : function(data, textStatus) {
			var obj = $('#liningRingBlockId');
			var value = data.liningRingBlocks;

			if (value != null) {
				for ( var i = 0; i < value.length; i++) {
					obj.append("<option value='" + value[i].id + "'>"
							+ value[i].blockIndex + "</option>");
				}
			}
		}
	})
}

function selectAll(){
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == 'checkbox') {
			inputs[i].checked = true;			
		}
	}
	var a  = $('#selectAll');
	a.attr(
		{
			"href":"javascript:cancelAll()",
		}
	);
	a.html("取消");
}

function cancelAll(){
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == 'checkbox') {
			inputs[i].checked = false;			
		}
	}
	var a  = $('#selectAll');
	a.attr(
		{
			"href":"javascript:selectAll()",
		}
	);
	a.html("全选");
}

function checkForm()
{
  if(confirm("确认要进行批量删除吗？")==true)   
    return true;
  else  
    return false;
}   

