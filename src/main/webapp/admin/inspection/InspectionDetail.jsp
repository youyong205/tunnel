<%@ page contentType="text/html; charset=utf-8"%>
<table  class="table table-striped table-bordered table-condensed">
	<tr>	
		<th colspan='4'><h4 class="text-info text-center"><s:property value="tunnel.name"/>详情<s:property value="module"/>质量检测信息</h4></th>
		<input readonly type="hidden" name="inspection.id" value="<s:property value="inspection.id"/>" />
		<input readonly type="hidden" name="index" value="<s:property value="index"/>"/>
		<input readonly type="hidden" name="inspection.tunnelId" value="<s:property value="tunnel.id"/>"/>
		<input readonly type="hidden" name="inspection.type" value="<s:property value="module"/>"/>
	</tr>
	<tr>
		<td style="text-align:right;"><strong class="text-success">名称编号</strong></td>
		<td><s:select name="inspection.componentId" id="id"
				list="items" listKey="id" listValue="name"  value="inspection.componentId" theme="simple" >
		</s:select></td>
		
		<td style="text-align:right;"><strong class="text-success">施工单位</strong></td>
		<td>
			 <s:select name="inspection.constructionUnitId" id="id"
					list="constructionUnits" listKey="id" listValue="name"  value="inspection.constructionUnitId" theme="simple" >
			</s:select>
		</td>
	</tr>
	<tr>
		<td width="20%" style="text-align:right;"><strong class="text-success">检测时间</strong></td>
		<td width="20%">
		<div id="datetimepicker1" class="input-append date">
            <input readonly name="inspection.time"  value='<s:date name="inspection.time" format="yyyy-MM-dd" />' class="{required:true,date:true}"
               data-format="yyyy-MM-dd" type="text"></input> <span class="add-on"> <i
               data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
            </span>
         </div>
        <td  width="20%" style="text-align:right;"><strong class="text-success">处理时间</strong></td>
		<td  width="20%">
		<div id="datetimepicker2" class="input-append date">
            <input readonly name="inspection.actionTime"   value='<s:date name="inspection.actionTime" format="yyyy-MM-dd" />'  class="{required:true,date:true}"
               data-format="yyyy-MM-dd" type="text"></input> <span class="add-on"> <i
               data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
            </span>
         </div>
        </tr>
	<tr>
		<td style="text-align:right;"><strong class="text-success">质量描述</strong></td>
		<td><textarea readonly type="text" rows="5" cols="40"  name="inspection.description" class="{maxlength:512}"><s:property value="inspection.description"/></textarea></td>
	<td style="text-align:right;"><strong class="text-success">处理措施</strong></td>
		<td><textarea readonly type="text" rows="5" cols="40"  name="inspection.action" class="{maxlength:512}"><s:property value="inspection.action"/></textarea></td>
	</tr>
	<tr>
		<td style="text-align:right;"><strong class="text-success">备注</strong></td>
		<td><textarea readonly type="text" rows="5" cols="40"  name="inspection.des" class="{maxlength:512}"><s:property value="inspection.des"/></textarea></td>
	</tr>
	</table>





