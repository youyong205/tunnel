<%@ page contentType="text/html; charset=utf-8"%>
<script type="text/javascript" src="js/bootstrap.datetimepicker.min.js"></script>

<table  class="table table-striped table-bordered table-condensed">
	<tr>	
		<th colspan='4'><h4 class="text-info text-center">施工进度信息</h4></th>
		<input type="hidden" name="schedule.id" value="<s:property value="schedule.id"/>" />
	</tr>
	<tr>
		<td style="text-align:right;"><strong class="text-success">选择施工单位</strong></td>
		<td>
			 <s:select name="schedule.constructionUnitId" 
					list="constructionUnits" listKey="id" listValue="name" value="schedule.constructionUnitId"  theme="simple" >
			</s:select>
		</td>
		<td style="text-align:right;"><strong class="text-success">进度类型</strong></td>
		<td><input type="text" name="schedule.type"  value='<s:property value="schedule.type"/>'  class="{required:true}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" style="text-align:right;"><strong class="text-success">施工开始时间</strong></td>
		<td width="30%">
		<div id="datetimepicker1" class="input-append date">
            <input name="schedule.startTimeStr"  value="<s:property value='schedule.startTimeStr'/>"  class="{required:true,date:true}"
               data-format="yyyy-MM-dd hh:mm" type="text"></input> <span class="add-on"> <i data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
            </span>
         </div></td>
		<td width="15%"style="text-align:right;"><strong class="text-success">施工完成时间</strong></td>
		<td><div id="datetimepicker2" class="input-append date">
            <input name="schedule.endTimeStr"    value="<s:property value='schedule.endTimeStr'/>" class="{required:true,date:true}"
               data-format="yyyy-MM-dd hh:mm" type="text"></input> <span class="add-on"> <i
               data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
            </span>
         </div></td>
	</tr>
	<tr>
		<td width="20%" style="text-align:right;"><strong class="text-success">施工进度备注信息</strong></td>
		<td colspan='3'><textarea type="text" rows="5" cols="80"  name="schedule.des" class="{maxlength:512}"><s:property value="schedule.des"/></textarea></td>
	</tr>
</table>
<script type="text/javascript">
$(document).ready(function() {
	$('#datetimepicker1').datetimepicker();
	$('#datetimepicker2').datetimepicker();
});
</script>

					





