<%@ page contentType="text/html; charset=utf-8"%>
<table  class="table table-striped table-bordered table-condensed">
	<tr>	
		<th colspan='4'><h4 class="text-info text-center"><span class='text-error'><s:property value="tunnel.name"/></span> <s:property value="module"/>质量检测信息</h4></th>
		<input readonly type="hidden" name="curing.id" value="<s:property value="curing.id"/>" />
		<input readonly type="hidden" name="index" value="<s:property value="index"/>"/>
		<input readonly type="hidden" name="tunnelId" value="<s:property value="tunnelId"/>"/>
		<input readonly type="hidden" name="curing.tunnelId" value="<s:property value="tunnel.id"/>"/>
		<input readonly type="hidden" name="curing.type" value="<s:property value="module"/>"/>
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
		<td width="20%" style="text-align:right;"><strong class="text-success">养护时间</strong></td>
		<td width="30%">
		<div id="datetimepicker1" class="input-append date">
	           <input readonly name="curing.time"    value='<s:date name="curing.time" format="yyyy-MM-dd" />' class="{required:true,date:true}"
	              data-format="yyyy-MM-dd" type="text"></input> <span class="add-on"> <i
	              data-time-icon="icon-time" data-date-icon="icon-calendar"> </i>
	           </span>
	        </div>
	       <td  width="20%" style="text-align:right;"><strong class="text-success">位置描述</strong></td>
		   <td  width="30%">
	           <input readonly name="curing.position" class="{required:true}"  value="<s:property value='curing.position'/>"  type="text"></input> 
	       </tr>
	<tr>
		<td style="text-align:right;"><strong class="text-success">养护资料<br></strong></td>
		<td>
			<s:if test="curing.documentId>0">
				
				<a class='text-error' href="documentDownload.do?documentId=<s:property value="curing.document.id"/>"><s:property value="curing.document.name"/></a>
				</br>
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
	





