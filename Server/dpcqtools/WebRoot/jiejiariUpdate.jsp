<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String startTime = request.getParameter("startTime");
	String endTime = request.getParameter("endTime");
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	name = new String(name.getBytes("iso-8859-1"), "utf-8");
	String multiple = request.getParameter("multiple");
%>

<script type="text/javascript">

	//更新节假日奖励
	function update() {
		var id = $("#id").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var multiple = $("#multiple").val();
		var url;
		var message;
		url = "activities!updateActivityHolidayList1.action";
		$.get(url, {
			id : id,
			startTime : startTime,
			endTime : endTime,
			multiple : multiple
		}, function(data) {
			var dataObj=eval("("+data+")");
			if (dataObj == null) {
				alert('更新失败');
				return;
			}
			alert(dataObj);
			if (dataObj == '操作成功') {
				$("#right_content").load("jiejiari.jsp",function(){$("#right_content").fadeIn(100);});
			}
		});
	}
	
</script>


<form id='from1' name='from1'>

	
	<h2>修改节假日活动</h2>
	
	<dl>
		<dt>
			<label for="email">编号</label>
		</dt>
		<dd>
		    <input type="text" id='id' name='id' value="<%=id%>"  readonly/> 
		</dd>
	</dl>
	
	<dl>
		<dt>
			<label for="email">活动名称</label>
		</dt>
		<dd>
			<input id='name' value="<%=name%>" type="text" name='name' readonly>
		</dd>
	</dl>
	<dl>
		<dt>
			<label for="email">开始时间:</label>
		</dt>
		<dd>
			<input id='startTime' value="<%=startTime %>" name='startTime' class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
		</dd>
	</dl>
	<dl>
		<dt>
			<label for="email">结束时间:</label>
		</dt>
		<dd>
			<input value="<%=endTime %>" id='endTime' name='endTime' class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
		</dd>
	</dl>
	<dl>
		<dt>
			<label for="email">倍数/折扣</label>
		</dt>
		<dd>
			<input type="text" id='multiple' name='multiple' value="<%=multiple%>"/> 
		</dd>
	</dl>

	<a href="#" onclick="update()" class="bt_green"><span class="bt_green_lft"></span><strong>确定修改</strong><span class="bt_green_r"></span> </a>
		
</form>