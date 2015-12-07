<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 <h2>账户管理</h2>   
  <table id="rounded-corner" >
    <thead>
    	<tr>
        	<th scope="col" class="rounded-company"></th>
            <th scope="col" class="rounded">帐号名称</th>
            <th scope="col" class="rounded">限制登录ip</th>
            <th scope="col" class="rounded">工号</th>
            <th scope="col" class="rounded">申请时间</th>
            <th scope="col" class="rounded">冻结</th>
            <th scope="col" class="rounded-q4">删除</th>
        </tr>
    </thead>
        <tfoot>
    	<tr>
        	<td colspan="6" class="rounded-foot-left">&nbsp;</td>
        	<td class="rounded-foot-right">&nbsp;</td>
        </tr>
    </tfoot>
    <tbody>
    <s:iterator value="userlist" >
    	<tr>
        	<td></td>
            <td align="center"><s:property value="username" /></td>
            <td align="center"><s:property value="name" /></td>
            <td align="center"><s:property value="ip" /></td>
            <td align="center"><s:property value="password" /></td>
            <td><a href="#"><img src="images/user_edit.png" alt="" title="" border="0" /></a></td>
            <td><a href="#" class="ask" onclick="dopost('user!delUser.action?id=${id}')"><img src="images/trash.png" alt="" title="" border="0" /></a></td>
        </tr>
     </s:iterator>         
    </tbody>
</table>
     <a href="#"  class="bt_green" onclick="jump('tjzh')"><span class="bt_green_lft"></span><strong>添加帐号</strong><span class="bt_green_r"></span></a>