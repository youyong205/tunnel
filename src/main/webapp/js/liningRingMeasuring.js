function tunnelChanged() {
	tunnelChangedInternal(true, false);
}


function tunnelChangedExcluedeAll() {
	tunnelChangedInternal(true, true);
}

function tunnelChangedInternal(excludeClildAll, excludeNexChildAll) {
	document.getElementById('tunnelSectionId').options.length = 0;
	console.log("===="+excludeClildAll+"=======");
	var id = $('#tunnelId').val();
	$.ajax({
		type : "get",
		url : "queryAllTunnelSections.do?tunnelId=" + id,
		success : function(data, textStatus) {
			var obj = $('#tunnelSectionId');
			var value = data.tunnelSections;

			if (value != null) {
				if (!excludeClildAll) {
					obj.append("<option value='0'>_ALL</option>");
				}
				for ( var i = 0; i < value.length; i++) {
					obj.append("<option value='" + value[i].id + "'>"
							+ value[i].name + "</option>");
				}
			}
			tunnelSectionChanged(excludeNexChildAll);
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
				if (!excludeAll) {
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

function tunnelSectionChangedAndBlock() {
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

function liningRingChanged() {
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

function selectAll() {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == 'checkbox') {
			inputs[i].checked = true;
		}
	}
	var a = $('#selectAll');
	a.attr({
		"href" : "javascript:cancelAll()",
	});
	a.html("取消");
}

function cancelAll() {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == 'checkbox') {
			inputs[i].checked = false;
		}
	}
	var a = $('#selectAll');
	a.attr({
		"href" : "javascript:selectAll()",
	});
	a.html("全选");
}

function checkForm() {
	if (confirm("确认要进行批量删除吗？") == true)
		return true;
	else
		return false;
}


function graphLineChart(title,lable,data){
	Highcharts.setOptions({
	    global: {
	        useUTC: false
	    }
	});
	$('#chart').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text:  title
        },
        credits : {
            enabled:false
        },
        xAxis: {
            type: 'datetime',
            dateTimeLabelFormats: { // don't display the dummy year
                second: '%H:%M:%S',
                minute: '%e. %b %H:%M',
                hour: '%b/%e %H:%M',
                day: '%m-%d',
                week: '%e. %b',
                month: '%y-%m',
                year: '%y'
            }
        },
        yAxis: {
            title: {
                text: lable
            },
            min: 0
        },
        tooltip: {
            formatter: function() {
                    return '<b>'+ this.series.name +'</b><br/>'+
                    Highcharts.dateFormat('%Y-%m-%d', this.x) +' '+lable+' = '+ this.y;
            }
        },
        series:data
    });
}
