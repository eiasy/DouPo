<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<h2>分区配置</h2>

<table id="rounded-corner">
	<thead>
		<tr>
			<th scope="col" class="rounded-company">编号</th>
			<th scope="col" class="rounded">分区名</th>
			<th scope="col" class="rounded">ip</th>
			<th scope="col" class="rounded">端口号</th>
			<th scope="col" class="rounded">DB名</th>
			<th scope="col" class="rounded">DB密码</th>
			<th scope="col" class="rounded">DBip</th>
			<th scope="col" class="rounded">DB端口</th>
			<th scope="col" class="rounded">修改</th>
			<th scope="col" class="rounded">删除</th>
		</tr>
	</thead>

	<tbody>	
		<s:iterator value="serlist" >
	    	<tr>     	
	            <td align="center"><s:property value="id" /></td>
	            <td align="center"><s:property value="servername" /></td>
	            <td align="center"><s:property value="serverip" /></td>
	            <td align="center"><s:property value="serverport" /></td>
	            <td align="center"><s:property value="dbname" /></td>
	            <td align="center"><s:property value="dbpassword" /></td>
	            <td align="center"><s:property value="dbip" /></td>
	            <td align="center"><s:property value="dbport" /></td>
	            <td><a href="#"><img src="images/user_edit.png" alt="" title="" border="0" /></a></td>
	            <td><a href="#" class="ask" onclick="dopost('server!delServerAttribute.action?id=${id}')"><img src="images/trash.png" alt="" title="" border="0" /></a></td>
	        </tr>
		</s:iterator>   
	</tbody>
	
</table>

<a href="#" class="bt_green" onclick="jump('tjfq')"><span class="bt_green_lft"></span><strong>添加分区</strong><span class="bt_green_r"></span></a>
	
