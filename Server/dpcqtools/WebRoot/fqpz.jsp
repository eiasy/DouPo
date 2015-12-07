<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<h2>分区配置</h2>

　　<form action="upload!uploadserverlist.action" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit" value="提交文件[服务器列表]">
    </form>

<p>
<div style="width:650px; height:500px; overflow:scroll;">
<table id="rounded-corner">
	<thead>
		<tr>
			<th scope="col" class="rounded-company" style="text-align:center;">编号</th>
			<th scope="col" class="rounded" style="text-align:center;">名称</th>
			<th scope="col" class="rounded" style="text-align:center;">状态</th>
			<th scope="col" class="rounded" style="text-align:center;">IP</th>
			<th scope="col" class="rounded" style="text-align:center;">Port</th>
		</tr>
	</thead>

	<tbody>	
		<s:iterator value="serlist" >
	    	<tr>     	
	            <td align="center"><s:property value="id" /></td>
	            <td align="center"><s:property value="name" /></td>
	            <td align="center"><s:property value="state" /></td>
	            <td align="center"><s:property value="ip" /></td>
	            <td align="center"><s:property value="port" /></td>
	        </tr>
		</s:iterator>   
	</tbody>
</table>
</div>
	
