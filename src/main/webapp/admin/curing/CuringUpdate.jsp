<%@ page contentType="text/html; charset=utf-8"%>
<table  class="table table-striped table-bordered table-condensed">
	<tr>	
		<th colspan='4'><h4 class="text-info text-center">编辑<s:property value="module"/>质量检测信息</h4></th>
		<input type="hidden" name="curing.id" value="<s:property value="curing.id"/>" />
		<input type="hidden" name="index" value="<s:property value="index"/>"/>
		<input type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
		<input type="hidden" name="curing.documentId" value="<s:property value="curing.documentId"/>"/>
		<input type="hidden" name="curing.type" value="<s:property value="module"/>"/>
	</tr>
	<tr>
		<td style="text-align:right;"><strong class="text-success">选择隧道</strong></td>
		<td colspan='3'><s:select name="curing.tunnelId" id="tunnelId" onchange="tunnelChanged()"
				list="tunnels" listKey="id" listValue="name" value="curing.tunnelId"  theme="simple" >
			</s:select>
		</td>
	</tr>
	<tr>
		<td style="text-align:right;"><strong class="text-success">名称编号</strong></td>
		<td><s:select name="curing.componentId" id="componentId"
				list="items" listKey="id" listValue="name"  value="curing.componentId" theme="simple" >
		</s:select></td>
		
		<td style="text-align:right;"><strong class="text-success">选择施工单位</strong></td>
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
	           <input name="curing.time"   value='<s:date name="curing.time" format="yyyy-MM-dd" />' class="{required:true,date:true}"
	              data-format="yyyy-MM-dd" type="text"></input> <span class="add-on"> <i
	              data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
	           </span>
	        </div>
	       <td  width="15%" style="text-align:right;"><strong class="text-success">位置描述</strong></td>
		   <td  width="35%">
	           <input name="curing.position" class="{required:true}" value="<s:property value='curing.position'/>"  type="text"></input> 
	       </tr>
	<tr>
		<td style="text-align:right;"><strong class="text-success">上传养护资料<br><span class='text-error'>（多个文档请打zip包）</span></strong></td>
		<td>
			<s:if test="curing.documentId>0">
				已经上传附件:&nbsp;<span class='text-error'><s:property value="curing.document.name"/></span></br>
			</s:if>
			更换附件<input type="file" name="upload">
		</td>
		<td style="text-align:right;"><strong class="text-success">处理措施</strong></td>
		<td><textarea type="text" rows="5" cols="40" name="curing.action" class="{maxlength:512}"><s:property value='curing.action'/></textarea></td>
	</tr>
	<tr>
		<td style="text-align:right;"><strong class="text-success">备注</strong></td>
		<td  colspan='3'><textarea type="text" rows="5" cols="40" name="curing.des" class="{maxlength:512}"><s:property value='curing.des'/></textarea></td>
	</tr>
	<tr>
		<td colspan="4" style="text-align:center;">
			<button  class="btn btn-small btn-primary"  type="submit" >提交</button></td>
	</tr>
</table>
	





