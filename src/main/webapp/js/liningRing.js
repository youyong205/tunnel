String.prototype.replaceAll  = function(s1,s2){   
return this.replace(new RegExp(s1,"gm"),s2);   
} 

function addBlock() {
	var tr = $('#block').html();
	var trHtml = "<tr class='block' id='block0'>" + tr + "</tr>";
	var maxId = findMaxIndex();
	
	console.log(maxId+"=======");
	var newId = "block"+maxId;
	var newIteratorId="["+maxId+"]";
	trHtml = trHtml.replaceAll("block0",newId);
	trHtml = trHtml.replaceAll("\\[0\\]",newIteratorId);
	$('#blockTable').append(trHtml);
}

function findMaxIndex() {
	var tr = $('.block');
	var max = 0;
	for ( var i = 0; i < tr.length; i++) {
		var item = tr[i];
		var id = item.id;
		var realId = parseInt(id.substr(5));
		if (realId > max) {
			max = realId;
		}
	}
	return max + 1;
}

function removeBlock(id) {
	$('#'+id).remove();
}