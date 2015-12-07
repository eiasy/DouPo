<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()	+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h2>预览界面</h2>
<table id="rounded-corner" summary="2007 Major IT Companies' Profit">
	<thead>
		<tr>
			<th scope="col" class="rounded-company"></th>
			<th scope="col" class="rounded">sql语句</th>
			<th scope="col" class="rounded-q4">删除</th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<td colspan="5" class="rounded-foot-left">&nbsp;</td>
		</tr>
	</tfoot>
	<tbody>
		<s:iterator value="sqlmap" id="pt">
			<tr>
				<td align="center"></td>
				<td align="center">执行数据库:<s:property value="#pt.key" />
				</td>
				<td align="center"></td>
			</tr>
			<s:iterator value="#pt.value" id='sql'>
				<tr>
					<td align="center"></td>
					<td align="center">执行分区:<s:property value="#sql.key" />
					</td>
					<td align="center"></td>
				</tr>
				<s:iterator value="#sql.value" id='updatesql'>
					<tr>
						<td align="center"><input type="checkbox" name="" />
						</td>
						<td align="center"><s:property value="#updatesql" />
						</td>
						<td><a href="#"
							onclick="dopost('upload!delfile.action?date=${date}&name=${name}&exp=${exp}')"
							class="ask"><img src="images/trash.png" alt="" title=""
								border="0" /> </a>
						</td>
					</tr>
				</s:iterator>
			</s:iterator>
		</s:iterator>
	</tbody>
</table>