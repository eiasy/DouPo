<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String id = request.getParameter("id");
	String serverid = request.getParameter("serverid");
	String startTime = null == request.getParameter("startTime") ? "" : request.getParameter("startTime");
	String endTime = null == request.getParameter("endTime") ? "" : request.getParameter("endTime");
	String inteval = null == request.getParameter("inteval") ? "" : request.getParameter("inteval");
	String content = null == request.getParameter("content") ? "" : new String (request.getParameter("content").getBytes("iso-8859-1"), "utf-8");
	//String url = null==id?"marquee!addMarquee.action":"marquee!updateMarquee.action";
%>
<script type="text/javascript">

	function pmdcz() {
		var checkValue = $("#serverid").val();
		var id = $("#id").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var inteval = $("#inteval").val();
		var content = $("#content").val();
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
		if (id > 0) {

			url = "marquee!updateMarquee.action";
			message = '修改成功';
			var id = $("#id").val();
		} else {
			url = "marquee!addMarquee.action";
			message = '添加成功';
			var id = 0;
		}
		$.get(url, {
			serverid : checkValue,
			id : id,
			startTime : startTime,
			endTime : endTime,
			inteval : inteval,
			content : content
		}, function(data) {
			alert(message);
		});
	}
</script>

<form id='from1' name='from1'>
	<input type="hidden" id='id' name='id' value="<%=id%>">
	<input type="hidden" id='serverid' name='serverid' value="<%=serverid%>">
	<h2><%=null == id ? "添加跑马灯" : "修改跑马灯"%></h2>
	<dl>
		<dt>
			<label for="email">开始时间:</label>
		</dt>
		<dd>
			<input id='startTime' value="<%=startTime%>" name='startTime'
				class="Wdate" type="text"
				onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
		</dd>
	</dl>
	<dl>
		<dt>
			<label for="email">结束时间:</label>
		</dt>
		<dd>
			<input value="<%=endTime%>" id='endTime' name='endTime'
				class="Wdate" type="text"
				onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
		</dd>
	</dl>
	<dl>
		<dt>
			<label for="email">间隔时间:</label>
		</dt>
		<dd>
			<input id='inteval' type="text" value="<%=inteval%>" name="inteval"
				id="" size="10" />(秒)
		</dd>
	</dl>
	<dl>
		<dt>
			<label for="email">内容:</label>
		</dt>
		<dd>
			<textarea id='content' name='content' cols="30" rows="5"><%=content%></textarea>
		</dd>
	</dl>
	<a href="#" onclick="pmdcz()" class="bt_green"><span class="bt_green_lft"></span><strong>确认</strong><span class="bt_green_r"></span> </a>
</form>