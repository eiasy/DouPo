<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<form id = 'from1' name = 'from1' action="user!addUser.action">
 <h2>修改密码</h2>
      <dl>
         <dt>
           <label for="email">用户名:</label></dt>
         <dd><input type="text" name="username" id="" size="54" /></dd>
     </dl>
      <dl>
         <dt>
           <label for="email">密码:</label></dt>
         <dd><input type="text" name="password" id="" size="54" /></dd>
     </dl>
    
      <a href="#" onclick="dopost('user!updateUserPassword.action?id=${id}')" class="bt_green"><span class="bt_green_lft"></span><strong>修改密码</strong><span class="bt_green_r"></span></a>

</form>