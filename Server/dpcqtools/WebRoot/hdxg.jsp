<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String id = request.getParameter("id");
	String serverid = request.getParameter("serverid");
	String name = request.getParameter("name");
	String smallUiId = request.getParameter("smallUiId");
	String startTime = "undefined".equals( request.getParameter("startTime"))?"":request.getParameter("startTime");
	String endTime = "undefined".equals( request.getParameter("endTime"))?"":request.getParameter("endTime");
	String isNew = request.getParameter("isNew");
	String isForevery = request.getParameter("isForevery");
	String isView = request.getParameter("isView");
	String rank = request.getParameter("rank");
%>

<script type="text/javascript">

	function hdxg() {
		var checkValue = $("#serverid").val();
		var id = $("#id").val();
		var name = $("#name").val();
		var smallUiId = $("#smallUiId").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var isNew = $("#isNew").val();
		var isForevery = $("#isForevery").val();
		var isView = $("#isView").val();
		var rank = $("#rank").val();
		var url;
		var message;
		
		//if(isForevery == 1) {
		//	if(endTime!=""&&startTime!=""){
			//	alert('如果是永久活动的话开始结束日期必须为空');
			//	return;
			//}
		//}
		
		//alert('acc' + startTime);
		
		//alert('a' + startTime);
		
		//if(endTime != "" && startTime != ""){
		//	var d1 = new Date(startTime.replace(/\-/g, "\/"));
		//	var d2 = new Date(endTime.replace(/\-/g, "\/"));
		//	var d3 = new Date();
		//	if (d1 > d2) {
		//		alert('开始时间不能大于结束时间');
		//		return;
		//	}
		//	if (d3 > d2) {
			//	alert('当前时间不能大于结束时间');
			//	return;
		//	}
	//	}
		//alert(startTime);
		//alert(endTime);
		if((endTime=="" && startTime=="") || (endTime != "" && startTime != "")){
			if (id > 0) {
				url = "activities!updateActivities.action";
				message = '修改成功';
				var id = $("#id").val();
				$.get(url, {
					serverid : checkValue,
					id : id,
					name : name,
					smallUiId : smallUiId,
					startTime : startTime,
					endTime : endTime,
					isNew : isNew,
					isForevery : isForevery,
					isView : isView,
					rank : rank
				}, function(data) {
					alert(message);
				});
			} else {
				return;
			}
		} else {
			alert('起始结束时间请同时填写或不填');
			return;
		}
	}
</script>


<form id='from1' name='from1'>
	<input type="hidden" id='id' name='id' value="<%=id%>"> 
	<input type="hidden" id='serverid' name='serverid' value="<%=serverid%>">
	
	<h2>修改活动</h2>
	
	<dl>
		<dt>
			<label for="email">名称:</label>
		</dt>
		<dd>
			<input id='name' value="<%=new String(name.getBytes("iso-8859-1"), "utf-8") %>" type="text" name='name'>
		</dd>
	</dl>
	<dl>
		<dt>
			<label for="email">图片</label>
		</dt>
		<dd>
			<input id='smallUiId' value="<%=smallUiId %>" name='smallUiId' type="text">
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
			<label for="email">新活动</label>
		</dt>
		<dd>
			<select name ='isNew' id='isNew' >
				<option <%if("1".equals(isNew)) {%>selected<%} %> value="1">是</option>
				<option <%if("0".equals(isNew)) {%>selected<%} %>  value="0">否</option>
			</select>
		</dd>
	</dl>
	
	<dl>
		<dt>
			<label for="email">是否有快捷图标</label>
		</dt>
		<dd>
			<select  name ='isForevery' id='isForevery' ><option <%if("1".equals(isForevery)) {%>selected<%} %>  value="1">是</option><option <%if("0".equals(isForevery)) {%>selected<%} %>  value="0">否</option></select>
		</dd>
	</dl>
		<dl>
		<dt>
			<label for="email">显示</label>
		</dt>
		<dd>
			<select  name ='isView' id='isView'><option <%if("1".equals(isView)) {%>selected<%} %>  value="1" >是</option><option <%if("0".equals(isView)) {%>selected<%} %>  value="0">否</option></select>
		</dd>
	</dl>
	
	<dl>
		<dt>
			<label for="email">排序</label>
		</dt>
		<dd>
			<input id='rank' value="<%=rank %>" type="text" name='rank'>
		</dd>
	</dl>

	<a href="#" onclick="hdxg()" class="bt_green"><span class="bt_green_lft"></span><strong>确认</strong><span class="bt_green_r"></span> </a>
		
</form>