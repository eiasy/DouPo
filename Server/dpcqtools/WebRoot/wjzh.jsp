<%@ page language="java" import="java.util.* ,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute" pageEncoding="UTF-8"%>

<script type="text/javascript">

	//选择某个服务器,并返回此服务器在线人数
	function tt() {
		var checkValue=$("#sel").val();
	  	$.getJSON("goods!onlineNumber.action", {serverid:checkValue}, function (data){
			// alert(data);
			if(data == null){
				$("#h4").empty();
				$("#h4").append();
				alert('服务器连接失败!');
			}else{
				var dataObj=eval("("+data+")");//转换为json对象 
				
				$.each(dataObj, function(key,value) { 
				 		$("#h4").empty();
		  				$("#h4").append("在线人数：" + value); 
		 		});
			}
	 	}); 
	}
	
	function closeServer() {
		var checkValue=$("#sel").val();
		var closeServerText = $("#closeServerText").val();
		
	 	if(checkValue < 0) {
		 	alert('请选择服务器');
		 	return;
		}
		if(closeServerText == "" || closeServerText == " "){
		 	alert('请编辑停服公告');
			return;
		}
		if(closeServerText == '在这编辑停服公告, 如：停服维护20分钟, 限26字'){
			alert('请编辑停服公告');
			return;
		}
		if (closeServerText.length > 26) {
			alert('字数超过限制[26字]');
			return;
		}
	 	if(confirm('确定关闭吗?')){
			 $.get("sendmessage!closeServer.action", {serverid:checkValue,closeServerMsg:closeServerText}, function (data){
			 	alert('服务器已关闭!');
			 });
		} else  {
		   return;
		}
	}
</script>

<h2>玩家帐号查询</h2>
<a href="#" onclick="closeServer()" class="bt_red">
	</span> <strong>关闭服务器</strong> <span class="bt_red_r"></span>
</a>

<h4>
	请选择服务器:&nbsp;&nbsp;
	<select id ="sel" onchange="tt()">
	<option value="-1">请选择</option>
	<%  
		Set<Integer> key = GameCoon.serverMap.keySet();
	    for (Iterator<Integer> it = key.iterator();it.hasNext();) {
	         Integer i = (Integer) it.next();
	         ServerAttribute sa =(ServerAttribute)GameCoon.serverMap.get(i); 	
	 %>
	<option value=<%=sa.getId() %> ><%= sa.getId() + "_" + sa.getName() %></option>
	<%} %>
	</select>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="closeServerText" type="text" value="在这编辑停服公告, 如：停服维护20分钟, 限26字" onclick="this.value=''" style="width:300px;"/>
</h4>
<h4 ><div id="h4"/></h4>

<div class="sidebar_search">
	<form>
		<input type="text" name="" class="search_input" value="角色名或帐号"
			onclick="this.value=''" /> <input type="image" class="search_submit"
			src="images/search.png" />
	</form>
</div>

<div class="form">
	<table id="rounded-corner" summary="2007 Major IT Companies' Profit">
		<thead>
			<tr>
				<th width="35" class="rounded-company" scope="col"></th>
				<th width="69" class="rounded" scope="col">属性</th>
				<th width="130" class="rounded" scope="col">值</th>
				<th width="83" class="rounded" scope="col">属性</th>
				<th colspan="2" class="rounded" scope="col">操作</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<td colspan="5" class="rounded-foot-left">&nbsp;</td>
				<td width="72" class="rounded-foot-right">&nbsp;</td>

			</tr>
		</tfoot>
		<tbody>
			<tr>
				<td>&nbsp;</td>
				<td>帐号</td>
				<td>test</td>
				<td>卡牌</td>
				<td colspan="2">查看卡牌</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td>角色名</td>
				<td>test</td>
				<td>背包</td>
				<td colspan="2">查看背包</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td>钻石数</td>
				<td>100</td>
				<td>违规操作</td>
				<td colspan="2">停封帐号</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td>游戏货币</td>
				<td>1000000000</td>
				<td>&nbsp;</td>
				<td colspan="2">禁言帐号</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>体力值</td>
				<td>20</td>
				<td>&nbsp;</td>
				<td colspan="2">&nbsp;</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td>耐力值</td>
				<td>40</td>
				<td>&nbsp;</td>
				<td colspan="2">&nbsp;</td>
			</tr>

		</tbody>
	</table>

</div>

