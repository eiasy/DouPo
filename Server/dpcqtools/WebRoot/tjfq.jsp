<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<form id = 'from1' name = 'from1' action="server!addServerAttribute.action">
 <h2>添加分区</h2>
       <dl>
	        <dt><label for="email">分区名称:</label></dt>
	        <dd><input type="text" name="servername" id="" size="54" /></dd>
       </dl>
       <dl>
          <dt><label for="email">ip:</label></dt>
          <dd><input type="text" name="serverip" id="" size="54" /></dd>
       </dl>
       <dl>
          <dt><label for="email">端口:</label></dt>
          <dd><input type="text" name="serverport" id="" size="54" /></dd>
       </dl>
       <dl>
          <dt> <label for="email">数据库名:</label></dt>
          <dd><input type="text" name="dbname" id="" size="54" /></dd>
       </dl>
       <dl>
          <dt><label for="email">数据库密码:</label></dt>
          <dd><input type="text" name="dbpassword" id="" size="54" /></dd>
       </dl>
       <dl>
          <dt><label for="email">数据库ip:</label></dt>
          <dd><input type="text" name="dbip" id="" size="54" /></dd>
      </dl>
       <dl>
          <dt><label for="email">数据库端口:</label></dt>
          <dd><input type="text" name="dbport" id="" size="54" /></dd>
      </dl>
      <a href="#" onclick="dopost('server!addServerAttribute.action')" class="bt_green"><span class="bt_green_lft"></span><strong>确认添加</strong><span class="bt_green_r"></span></a>
</form>