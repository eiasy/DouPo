<%@ page language="java" import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>游戏管理系统</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	
	function validate() {
		$("#but").removeAttr("disabled");
		$("#h").empty();
		$("#resultlist").removeAttr("style");
		$("#resultlist").empty();
	}
	
	function sub () {
		var start = $("#serverlist1").val();
		var end = $("#serverlist2").val();
		if (start == -1 || end == -1) {
			alert('请选择要更新的服务器');
			return;
		}
		if (start > end) {
			alert('开始服务器不能大于结束服务器');
			return;
		}
		
		$("#but").attr("disabled","true");
		$("#h").empty();
		$("#resultlist").attr("style","border:1px solid blue;overflow:auto;width:400px; height:400px;");
		$("#h").append("<font color='red'>正在执行......</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='lookresult();'>查看结果</button>"); 
		
		$.getJSON("server!updateDict.action", {start:start, end:end}, function (data){
	  		if (data == null) {
	  			alert('上传的更新文件,格式不正确!');
	  		} else {
	  			$("#h").empty();
	  			$("#resultlist").attr("style","border:1px solid blue;overflow:auto;width:400px; height:400px;");
		  		$("#h").append("<font color='red'>执行完毕!</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='lookresult();'>查看结果</button>");
	  		}
	  	});
	}
	
	function lookresult () {
		$.getJSON("server!lookResult.action", function (data){
	  		if (data != null) {
	  			$("#resultlist").empty();
				$("#resultlist").append("<b>" + data + "</b>");
	  		}
	  	});
	}
	
</script>

<style>
	body{ text-align:center} 
	.div{ margin:0 auto; width:400px; height:100px;} 
</style>

</head>
<body>
	    <div class="div" style="background:white;height:800px;width:700px;margin-top:25px;">
	    	<p>&nbsp;</p>
	    	<b><font color="green">文件上传成功</font></b> &nbsp;<a href="javascript:window.close()" >关闭窗口</a>
	  		<p>&nbsp;</p>
	  		<p></p>
	  		选择要更新的服务器：
		  		<select id ="serverlist1" onchange="validate()">
					<option value="-1">请选择</option>
					<%  Set<Integer> key = GameCoon.serverMap.keySet();   
					     for (Iterator<Integer> it = key.iterator();it.hasNext();) {         
					         Integer i = (Integer) it.next();
					         ServerAttribute sa =(ServerAttribute)GameCoon.serverMap.get(i); 	
					 %>
					<option value=<%=sa.getId() %> ><%= (sa.getId() + "_" + sa.getName()) %></option>
					<%} %>
				</select>
				<b><strong>===</strong></b>
				<select id ="serverlist2" onchange="validate()">
					<option value="-1">请选择</option>
					<%  Set<Integer> key1 = GameCoon.serverMap.keySet();   
					     for (Iterator<Integer> it = key1.iterator();it.hasNext();) {         
					         Integer i = (Integer) it.next();
					         ServerAttribute sa =(ServerAttribute)GameCoon.serverMap.get(i);
					 %>
					<option value=<%=sa.getId() %> ><%= (sa.getId() + "_" + sa.getName()) %></option>
					<%} %>
				</select>
	  		 &nbsp;
	  		 <button id="but" name="but" onclick="sub()">确定更新</button>
	  		 <h4><div id="h"></div></h4>
	  		 <div class="div" style="width:400px; height:400px;overflow:auto;" id="resultlist"></div>
	 	</div>
</body>
</html>