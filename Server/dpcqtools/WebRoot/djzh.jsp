<%@ page language="java"
	import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">
	
	//解除冻结账号
	function delfrozen(url) {
		if(confirm('确实要解除冻结吗?')){
			dopost(url);
		} else {
			return;
		}
	}

	//查看冻结账号列表
	function showfrozen() {
		$("#tbody").empty();
	 	var serverId=$("#serverlist").val();
	 	if(serverId>0){
		  	$.getJSON("player!showFrozen.action", {serverid:serverId}, function (data){
			  	if(data == null){
					$("#tbody").empty();
					alert('服务器连接失败！');
				}else{
				 	var dataObj=eval("("+data+")");//转换为json对象 
		 			$("#tbody").empty();
				 	$.each(dataObj, function(i,item) {
				 		var url = "&quot;player!frozenFree.action?id="+item.id+"&serverid="+serverId+"&quot;";
				  		$("#tbody").append(
								"<tr>"+
	        					"<td></td>"+
	            				"<td align='center'>"+item.name+"</td>"+
	            				"<td align='center'>"+item.frozenTime+"</td>"+
			            		"<td><a href='#' class='ask' onclick='delfrozen("+url+")' >解除</a></td>"+
			         			"</tr>" 
					    ); 
				 	}); 
				 }
		 	}); 
		} else {
			return;
		}
	}
	
	//冻结账号
	function frozen() {
		var serverId=$("#serverlist").val();
		var name = $("#frozenPlayers").val();
		if(serverId < 0) {
		 	alert('请选择服务器');
		 	return;
		}
		if(name == "" || name == " "){
		 	alert('请填写玩家角色名');
			return;
		}
		if(name == '填写玩家角色名,多个玩家用;号隔开'){
			alert('请填写玩家角色名');
			return;
		}
		
		$.get("player!frozen.action", {serverid:serverId ,name:name}, function (data){
			alert('冻结成功');
			$("#right_content").load("djzh.jsp",function(){$("#right_content").fadeIn(100);});
		});
		
	}

</script>

<h2>冻结账号</h2>

<h4>
	请选择服务器:&nbsp;&nbsp;
	<select id="serverlist" onchange="showfrozen()">
		<option value="-1">请选择</option>
		<%  
			Set<Integer> key = GameCoon.serverMap.keySet();   
	     	for (Iterator<Integer> it = key.iterator();it.hasNext();) {         
		        Integer i = (Integer) it.next();    
		        ServerAttribute sa =(ServerAttribute)GameCoon.serverMap.get(i);
		%>
		<option value=<%=sa.getId() %>><%= (sa.getId() + "_" + sa.getName()) %></option>
		<%} %>
	</select>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="text" id="frozenPlayers" style="width:220px;" value="填写玩家角色名,多个玩家用;号隔开" onclick="this.value=''" />
	&nbsp;&nbsp;&nbsp;
	<button id="frozen" onclick="frozen()">冻结账号</button>
</h4>

<table id="rounded-corner" style="width: 780xp">
	<thead>
		<tr>
			<th scope="col" class="rounded-company"></th>
			<th scope="col" class="rounded">玩家名</th>
			<th scope="col" class="rounded">冻结时间</th>
			<th scope="col" class="rounded-q4">解除冻结</th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<td colspan="3" class="rounded-foot-left">&nbsp;</td>
			<td class="rounded-foot-right">&nbsp;</td>
		</tr>
	</tfoot>
	<tbody id="tbody">
		<%-- <tr>
        	<td></td>
            <td align="center">2014-12-10</td>
            <td align="center">2014-12-10</td>
            <td align="center">30</td>
            <td align="center" >大家好这是跑马灯下面省略一万字...</td>

            <td><a href="#"><img src="images/user_edit.png" alt="" title="" border="0" /></a></td>
            <td><a href="#" class="ask" onclick="dopost('user!delUser.action?id=${id}')"><img src="images/trash.png" alt="" title="" border="0" /></a></td>
        </tr> --%>

	</tbody>
</table>
