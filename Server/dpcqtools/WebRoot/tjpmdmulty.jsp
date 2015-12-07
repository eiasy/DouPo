<%@ page language="java"
	import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script type="text/javascript">

	function pmdcz() {
		
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var inteval = $("#inteval").val();
		var content = $("#content").val();
		var start = $("#serverlist1").val();
		var end = $("#serverlist2").val();
		if (start == -1 || end == -1) {
			alert('请选择要添加的服务器');
			return;
		}
		if (start > end) {
			alert('开始服务器不能大于结束服务器');
			return;
		}
		var url;
		var message;
		var d1 = new Date(startTime.replace(/\-/g, "\/"));
		var d2 = new Date(endTime.replace(/\-/g, "\/"));
		var d3 = new Date();
		if (!/^[1-9]\d*$/.test(inteval)) {
			alert('请填入大于等于30的整数');
			return;
		}
		if(inteval<30){
			alert('不能小于30秒');
			return;
		}
		if (d1 > d2) {
			alert('开始时间不能大于结束时间');
			return;
		}
		if (d3 > d2) {
			alert('当前时间不能大于结束时间');
			return;
		}
		url = "marquee!addMarqueeMulty.action";
		message = '添加成功';
		var id = 0;
		$.get(url, {
			start : start,
			end : end,
			startTime : startTime,
			endTime : endTime,
			inteval : inteval,
			content : content
		}, function(data) {
			var dataObj=eval("("+data+")");
			alert(dataObj);
			if (dataObj == '全部添加成功') {
				$("#right_content").load("pmdgl.jsp",function(){$("#right_content").fadeIn(100);});
			}
		});
	}
</script>

<form id='from1' name='from1'>
	<h2>多服添加跑马灯</h2>
	
		选择要添加的服务器：
 	<select id ="serverlist1">
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
	<select id ="serverlist2">
		<option value="-1">请选择</option>
		<%  Set<Integer> key1 = GameCoon.serverMap.keySet();   
		     for (Iterator<Integer> it = key1.iterator();it.hasNext();) {         
		         Integer i = (Integer) it.next();
		         ServerAttribute sa =(ServerAttribute)GameCoon.serverMap.get(i);
		 %>
		<option value=<%=sa.getId() %> ><%= (sa.getId() + "_" + sa.getName()) %></option>
		<%} %>
	</select>
	
	
	<dl>
		<dt>
			<label for="email">开始时间:</label>
		</dt>
		<dd>
			<input id='startTime' name='startTime'
				class="Wdate" type="text"
				onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
		</dd>
	</dl>
	<dl>
		<dt>
			<label for="email">结束时间:</label>
		</dt>
		<dd>
			<input id='endTime' name='endTime'
				class="Wdate" type="text"
				onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
		</dd>
	</dl>
	<dl>
		<dt>
			<label for="email">间隔时间:</label>
		</dt>
		<dd>
			<input id='inteval' type="text" name="inteval"
				id="" size="10" />(秒)
		</dd>
	</dl>
	<dl>
		<dt>
			<label for="email">内容:</label>
		</dt>
		<dd>
			<textarea id='content' name='content' cols="30" rows="5"></textarea>
		</dd>
	</dl>
	<a href="#" onclick="pmdcz()" class="bt_green"><span class="bt_green_lft"></span><strong>确认</strong><span class="bt_green_r"></span> </a>
</form>