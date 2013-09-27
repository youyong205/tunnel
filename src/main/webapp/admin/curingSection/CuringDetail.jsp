<%@ page contentType="text/html; charset=utf-8"%>
<table  class="table table-striped table-bordered table-condensed">
	<tr>
		<th colspan='4'><h4 class="text-info text-center">
				<s:property value="module" />
				养护记录信息详情
			</h4></th>
		<input readonly type="hidden" name="curing.id"value="<s:property value="curing.id"/>" />
		<input readonly type="hidden" name="index" value="<s:property value="index"/>" />
		<input readonly type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>" />
		<input readonly type="hidden" name="tunnelSectionId" value="<s:property value="tunnelSectionId"/>"/>
		<input readonly type="hidden" name="curing.type"value="<s:property value="module"/>" />
	</tr>
	<tr>
		<td style="text-align: right;"><strong class="text-success">隧道</strong></td>
		<td><s:select name="curing.tunnelId" id="tunnelId"
				onchange="tunnelChanged()" list="tunnels" listKey="id"
				listValue="name" value="curing.tunnelId" theme="simple">
			</s:select></td>

		<td style="text-align: right;"><strong class="text-success">盾构段</strong></td>
		<td><s:select name="curing.tunnelSectionId"
				id="tunnelSectionId" onchange="tunnelSectionChanged()"
				list="tunnelSections" listKey="id" listValue="name"
				value="curing.tunnelSectionId" theme="simple">
			</s:select></td>
	</tr>
	<tr>
		<td style="text-align:right;"><strong class="text-success">名称编号</strong></td>
		<td><s:select name="curing.componentId" id="id"
				list="items" listKey="id" listValue="name"  value="curing.componentId" theme="simple" >
		</s:select></td>
		
		<td style="text-align:right;"><strong class="text-success">施工单位</strong></td>
		<td>
			 <s:select name="curing.constructionUnitId" id="id"
					list="constructionUnits" listKey="id" listValue="name"  value="curing.constructionUnitId" theme="simple" >
			</s:select>
		</td>
	</tr>
	<tr>
		<td width="15%" style="text-align:right;"><strong class="text-success">养护时间</strong></td>
		<td width="35%">
		<div id="datetimepicker1" class="input-append date">
	           <input readonly name="curing.time"    value='<s:date name="curing.time" format="yyyy-MM-dd" />' class="{required:true,date:true}"
	              data-format="yyyy-MM-dd" type="text"></input> <span class="add-on"> <i
	              data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
	           </span>
	        </div>
	       <td  width="15%" style="text-align:right;"><strong class="text-success">位置描述</strong></td>
		   <td  width="35%">
	           <input readonly name="curing.position" class="{required:true}"  value="<s:property value='curing.position'/>"  type="text"></input> 
	       </tr>
	<tr>
		<td style="text-align:right;"><strong class="text-success">上传养护资料<br><span class='text-error'>（多个文档请打zip包）</span></strong></td>
		<td>
			<s:if test="curing.documentId>0">
				已经上传附件:&nbsp;<span class='text-error'><s:property value="curing.document.name"/></span></br>
			</s:if>
		</td>
		<td style="text-align:right;"><strong class="text-success">处理措施</strong></td>
		<td><textarea readonly type="text" rows="5" cols="40" name="curing.action"  class="{maxlength:512}"><s:property value='curing.action'/></textarea></td>
	</tr>
	<tr>
		<td style="text-align:right;"><strong class="text-success">备注</strong></td>
		<td  colspan='3'><textarea readonly type="text" rows="5" cols="40" name="curing.des"  class="{maxlength:512}"><s:property value='curing.des'/></textarea></td>
	</tr>
</table>
	





