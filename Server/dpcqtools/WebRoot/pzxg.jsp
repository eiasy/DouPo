<%@ page language="java" import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	
	function validate () {
		var serverId = $("#serverid").val();
	  	$.getJSON("server!validateChannel.action", {serverId:serverId}, function (data){
	  		if (data == null) {
	  			alert('服务器连接失败!');
	  		}
	  	});
	} 

	function sbumit1(){
		var type = $('input:radio:checked').val();
		var checkValue=$("#serverid").val();
		if(checkValue < 0) {
		 	alert('请选择服务器');
		 	return;
		} else {
		 	$.get("operation!isCache.action", {serverid:checkValue ,type:type}, function (data){
				alert('发送成功');
			});
		}
	}
	
	function sbumit2() {
		var checkValue=$("#serverid").val();
		var dbname=$("#dbname").val();
		if(checkValue < 0) {
		 	alert('请选择服务器');
		 	return;
		 } else {
		 	$.get("operation!dictionary.action", {serverid:checkValue ,dbname:dbname}, function (data){
				alert('发送成功');
			});
		 }
	}
	
	function sbumit3() {
		var checkValue=$("#serverid").val();
		var bossValue=$("#bossValue").val();
		if(checkValue < 0) {
		 	alert('请选择服务器');
		 	return;
		 } else {
		 	$.get("operation!worldBoss.action", {serverid:checkValue ,bossValue:bossValue}, function (data){
				alert('发送成功');
			});
		 }
	}
	
	function sbumit6() {
		var checkValue=$("#serverid").val();
		var chatCd=$("#chatCd").val();
		if(checkValue < 0) {
		 	alert('请选择服务器');
		 	return;
		 } else {
		 	$.get("operation!chatCd.action", {serverid:checkValue ,chatCd:chatCd}, function (data){
				alert('发送成功');
			});
		 }
	}
	
	function sbumit7() {
		var checkValue=$("#serverid").val();
		var loginEnvState=$("#loginEnvState").val();
		if(checkValue < 0) {
		 	alert('请选择服务器');
		 	return;
		 } else {
		 	$.get("operation!loginEnvState.action", {serverid:checkValue ,loginEnvState:loginEnvState}, function (data){
				alert('发送成功');
			});
		 }
	}
	
	function sbumit8() {
		var checkValue=$("#serverid").val();
		var openServerFund=$("#openServerFund").val();
		if(checkValue < 0) {
		 	alert('请选择服务器');
		 	return;
		 } else {
		 	$.get("operation!openServerFund.action", {serverid:checkValue ,openServerFund:openServerFund}, function (data){
				alert('发送成功');
			});
		 }
	}
	
	function sbumit9() {
		var checkValue=$("#serverid").val();
		var pushData=$("#pushData").val();
		if(checkValue < 0) {
		 	alert('请选择服务器');
		 	return;
		 } else {
		 	$.get("operation!pushData.action", {serverid:checkValue ,pushData:pushData}, function (data){
				alert('发送成功');
			});
		 }
	}
	
	
	function sbumit10() {
		var checkValue=$("#serverid").val();
		var plName=$("#plName").val();
		if(checkValue < 0) {
		 	alert('请选择服务器');
		 	return;
		 } else {
		 	$.get("operation!plName.action", {serverid:checkValue ,plName:plName}, function (data){
				alert('发送成功');
			});
		 }
	}
	
	function sbumit11() {
		var checkValue=$("#serverid").val();
		var clearNHour=$("#clearNHour").val();
		if(checkValue < 0) {
		 	alert('请选择服务器');
		 	return;
		 } else {
		 	$.get("operation!clearNHour.action", {serverid:checkValue ,clearNHour:clearNHour}, function (data){
				alert('发送成功');
			});
		 }
	}
	
	function sbumit12() {
		var checkValue=$("#serverid").val();
		var maxRegedit=$("#maxRegedit").val();
		if(checkValue < 0) {
		 	alert('请选择服务器');
		 	return;
		 } else {
		 	$.get("operation!maxRegedit.action", {serverid:checkValue ,maxRegedit:maxRegedit}, function (data){
				alert('发送成功');
			});
		 }
	}
	
	function sbumit4() {
		var checkValue=$("#serverid").val();
		var maxOnlineNum=$("#maxOnlineNum").val();
		if(checkValue < 0) {
		 	alert('请选择服务器');
		 	return;
		 } else {
		 	$.get("operation!maxOnlineNum.action", {serverid:checkValue ,maxOnlineNum:maxOnlineNum}, function (data){
				alert('发送成功');
			});
		 }
	}
	
   function sbumit14() {
		var checkValue=$("#serverid").val();
		if(checkValue < 0) {
		 	alert('请选择服务器');
		 	return;
		 } else {
			 $.getJSON("operation!currMaxRegeditNum.action", {serverid:checkValue}, function (data){
				if(data == null){
					$("#h4").empty();
					$("#h4").append();
					alert('服务器连接失败!');
				}else{
					var dataObj=eval("("+data+")");//转换为json对象 
					
					$.each(dataObj, function(key,value) { 
					 		$("#h4").empty();
			  				$("#h4").append("当前设置：" + value); 
			 		});
				}
		 	}); 
		 }
	}
	
	function sbumit5() {
		var checkValue=$("#serverid").val();
		var playerName=$("#playerName").val();
		var rmb=$("#rmb").val();
		if(checkValue < 0) {
		 	alert('请选择服务器');
		 	return;
		 } else {
		 	$.get("operation!recharge.action", {serverid:checkValue, playerName:playerName, rmb:rmb}, function (data){
				alert('发送成功');
			});
		 }
	}
	
	function sbumit13() {
		var checkValue=$("#serverid").val();
		var monthCardPname=$("#monthCardPname").val();
		var monthCardStartTime=$("#monthCardStartTime").val();
		if(checkValue < 0) {
		 	alert('请选择服务器');
		 	return;
		 } else {
		 	$.get("operation!monthCard.action", {serverid:checkValue, monthCardPname:monthCardPname, monthCardStartTime:monthCardStartTime}, function (data){
				alert('发送成功');
			});
		 }
	}
	
	function sbumit14() {
		var checkValue=$("#serverid").val();
		var goldMonthCardPname=$("#goldMonthCardPname").val();
		var goldMonthCardStartTime=$("#goldMonthCardStartTime").val();
		if(checkValue < 0) {
		 	alert('请选择服务器');
		 	return;
		 } else {
		 	$.get("operation!goldMonthCard.action", {serverid:checkValue, goldMonthCardPname:goldMonthCardPname, goldMonthCardStartTime:goldMonthCardStartTime}, function (data){
				alert('发送成功');
			});
		 }
	}
	
	function serverListUpdate() {
		var serverListType=$("#type").val();
		var serverList=$("#serverList").val();
		$.post("operation!updateServerList.action", {serverList:serverList,serverListType:serverListType}, function (data){
			alert('修改成功');
			$("#right_content").load("pzxg.jsp",function(){$("#right_content").fadeIn(100);});
		});
	}
	
	function serverList() {
		var serverListType=$("#type").val();
	  	$.getJSON("operation!getServerList.action", {serverListType:serverListType}, function (data){
			//alert(data);
			var dataObj=eval("("+data+")");//转换为json对象 
			$.each(dataObj, function(key,value) {
					//document.getElementById("serverList").innerHTML="";
					//document.getElementById("serverList").innerHTML=value;
			 		$("#serverList").empty();
	  				$("#serverList").append(value); 
	 		});
	 	}); 
	}
	
</script>

<h2>配置相关</h2>

请选择服务器:&nbsp;&nbsp;
	<select id ="serverid"  onchange="validate()">
		<option value="-1">请选择</option>
		<%  
			Set<Integer> key = GameCoon.serverMap.keySet();   
	    	for (Iterator<Integer> it = key.iterator();it.hasNext();) {         
	        Integer i = (Integer) it.next();    
	        ServerAttribute sa =(ServerAttribute)GameCoon.serverMap.get(i); 	
	 	%>
		<option value=<%=sa.getId() %> ><%= sa.getName() %></option>
		<%} %>
	</select>
<p/>
服务器数据缓存控制:&nbsp;&nbsp;
<input type="radio" name="type"  value="0" checked="checked"  /><label class="check_label">不使用缓存</label>
<input type="radio" name="type"  value="1" /><label class="check_label">使用缓存</label>&nbsp;&nbsp;
<input name='submit1' type="button" onclick="sbumit1()" value="确认" />
<p>

刷新字典表:&nbsp;&nbsp;<input id= 'dbname' name='tb' type="text" style="width:450px"/>&nbsp;&nbsp;  <input name='submit2' type="button"  value="确认刷新" onclick="sbumit2()" />
<p>
世界Boss血量:&nbsp;&nbsp;<input id= 'bossValue' name='tb3' type="text" style="width:150px"/>&nbsp;&nbsp;  <input name='submit3' type="button"  value="确认" onclick="sbumit3()" />
<p>
<p>
聊天cd 时间:&nbsp;&nbsp;<input id= 'chatCd' name='tb4' type="text" style="width:150px"/>&nbsp;&nbsp;  <input name='submit6' type="button"  value="确认" onclick="sbumit6()" />
<p>
是否打开非验证模式[0-否  1-是]:&nbsp;&nbsp;<input id= 'loginEnvState' name='tb4' type="text" style="width:150px"/>&nbsp;&nbsp;  <input name='submit7' type="button"  value="确认" onclick="sbumit7()" />
<p>
设置购买开服基金总人数:&nbsp;&nbsp;<input id= 'openServerFund' name='tb4' type="text" style="width:150px"/>&nbsp;&nbsp;  <input name='submit8' type="button"  value="确认" onclick="sbumit8()" />
<p>
是否向客服工具推送数据 0-否 1-是:&nbsp;&nbsp;<input id= 'pushData' name='tb4' type="text" style="width:150px"/>&nbsp;&nbsp;  <input name='submit9' type="button"  value="确认" onclick="sbumit9()" />
<p>
清除下线几个小时的玩家缓存对象,默认2个小时:&nbsp;&nbsp;<input id= 'clearNHour' name='tb4' type="text" style="width:150px"/>&nbsp;&nbsp;  <input name='submit11' type="button"  value="确认" onclick="sbumit11()" />
<p>
设置允许的最大注册数:&nbsp;&nbsp;<input id= 'maxRegedit' name='tb4' type="text" style="width:150px"/>&nbsp;&nbsp;  <input name='submit12' type="button"  value="确认" onclick="sbumit12()" /> &nbsp;&nbsp;&nbsp;&nbsp; <input name='submit14' type="button"  value="查询当前设置的最大注册数" onclick="sbumit14()" /><h4 ><div id="h4"/></h4>
<p>
给玩家去引导    &nbsp;&nbsp;   请输入玩家名字：&nbsp;&nbsp;<input id= 'plName' name='tb4' type="text" style="width:150px"/>&nbsp;&nbsp;  <input name='submit10' type="button"  value="确认" onclick="sbumit10()" />
<p>
最大同时在线人数:&nbsp;&nbsp;<input id= 'maxOnlineNum' type="text" style="width:150px"/>&nbsp;&nbsp;  <input name='submit4' type="button"  value="确认" onclick="sbumit4()" />
<p>
角色名:&nbsp;&nbsp;<input id= 'playerName' type="text" style="width:150px"/>&nbsp;&nbsp;充值金额:&nbsp;&nbsp;<input id= 'rmb' type="text" style="width:150px"/>&nbsp;&nbsp;  <input name='submit5' type="button"  value="确认充值" onclick="sbumit5()" />
<p>
白银月卡：&nbsp;&nbsp;     输入角色名:&nbsp;&nbsp;<input id= monthCardPname type="text" style="width:150px"/>&nbsp;&nbsp;选择购买月卡时间:&nbsp;&nbsp;<input id='monthCardStartTime' value="" name='monthCardStartTime' class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"> &nbsp;&nbsp;  <input name='submit13' type="button"  value="确定添加" onclick="sbumit13()" />
<p>
黄金月卡：&nbsp;&nbsp;     输入角色名:&nbsp;&nbsp;<input id= goldMonthCardPname type="text" style="width:150px"/>&nbsp;&nbsp;选择购买月卡时间:&nbsp;&nbsp;<input id='goldMonthCardStartTime' value="" name='goldMonthCardStartTime' class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"> &nbsp;&nbsp;  <input name='submit14' type="button"  value="确定添加" onclick="sbumit14()" />

<!-- 
<b>-------------------------------  配置服务器列表  -------------------------</b>
<p>
选择分区 [QQ/微信]:&nbsp;&nbsp;
	<select id ="type" onchange="serverList()">
		<option value="-1">请选择</option>
		<option value="1">QQ</option>
		<option value="2">微信</option>
	</select>
<p>
<textarea id = "serverList" name=""  rows="20"  cols="40"  ></textarea>
<p>
<button onclick="serverListUpdate()">确认修改[服务器列表]</button>
 -->
