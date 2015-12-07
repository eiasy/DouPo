<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<h2>更新字典表</h2>
　　<form action="upload!uploadfile.action" method="post" enctype="multipart/form-data" target="a">
        <input type="file" name="file"><p><p><br><br>
        <input type="submit" value="提交文件[字典表]">
    </form>
