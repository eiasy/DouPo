<%@ page language="java" import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	Map<Integer ,String> goodstype = (Map<Integer ,String>) session.getAttribute("tabletype");
%>

<script type="text/javascript">

	function validate () {
		var serverId = $("#serverlist").val();
	  	$.getJSON("server!validateChannel.action", {serverId:serverId}, function (data){
	  		if (data == null) {
	  			alert('服务器连接失败!');
	  		}
	  	});
	} 

	function tt(id , goodsid) {
		var checkValue=$("#"+id).val();
		var gid ="goods"+goodsid;
		$("#"+gid+ " option").remove();//清除下拉选说有选项
		$.getJSON("goods!sel.action", {id:checkValue}, function (data){
			if(data == null){
				alert('服务器连接失败!');
			} else {
				var dataObj=eval("("+data+")");
				for(var i = 0; i < data.length; i++){
					$("#"+gid).append("<option value='"+dataObj[i].id+"'>"+dataObj[i].name+"</option>");
				    //alert(dataObj[i].id+"=="+dataObj[i].name);
				}
			}
		});
		$("#mun"+goodsid).val('1');
	}
	
	function sbumit(){
		var type = $('input:radio:checked').val();
		var serverId=$("#serverlist").val();
		var goods = "";
		var name = $("#gamename").val();
		var content = $("#content").val();
	 	if(serverId < 0) {
		 	alert('请选择服务器');
		 	return;
		}
	 	if(type == 1){
			 if(name == "" || name == " "){
			 	alert('请填写玩家角色名');
				return;
			 }
			 if(name == '填写玩家角色名多个玩家用;号相隔'){
				alert('请填写玩家角色名');
				return;
			 }
		 }
		 if(type == 2){
		 	name ="";	 
		 }
	
		for(var i=1;i<10;++i){
			var typeid=$("#sel"+i).val();
			if(typeid > 0){
				var goodsid=$("#goods"+i).val();
				var mun=$("#mun"+i).val();
				goods = goods + typeid + '_'+goodsid+'_'+mun+';';
			}
		}
		
		
		var se=confirm("是否确认?");
		if (se==false) {
			return;
		}
		
 		//alert(goods);
		//alert('name =' + name);
		$.get("goods!provide.action", {serverid:serverId ,name:name,goods:goods,content:content}, function (data){
			alert(data);
		});
	}
	
	function lookThingCode () {
		var goods = "";
		for(var i=1;i<10;++i){
			var typeid=$("#sel"+i).val();
			if(typeid > 0){
				var goodsid=$("#goods"+i).val();
				var mun=$("#mun"+i).val();
				goods = goods + typeid + '_'+goodsid+'_'+mun+';';
			}
		}
		goods = goods.substring(0, goods.length - 1);
		document.getElementById("thingCode").innerHTML = "<b>"+goods+"</b>";
	}
	
	
</script>
<script type="text/javascript">
	
</script>

<h2>单人发放物品</h2>

请选择服务器:&nbsp;&nbsp;
	<select id ="serverlist" onchange="validate()">
		<option value="-1">请选择</option>
		<%  Set<Integer> key = GameCoon.serverMap.keySet();   
		     for (Iterator<Integer> it = key.iterator();it.hasNext();) {         
		         Integer i = (Integer) it.next();
		         ServerAttribute sa =(ServerAttribute)GameCoon.serverMap.get(i); 	
		 %>
		<option value=<%=sa.getId() %> ><%= (sa.getId() + "_" + sa.getName()) %></option>
		<%} %>
	</select>
<p></p>

<div class="sidebar_search">
	<input type="text" id = "gamename" name="" class="search_input" value="填写玩家角色名多个玩家用;号相隔" onclick="this.value=''" />
</div>
<input type="radio" name="type"  value="1" checked="checked"  /><label class="check_label">单人玩家</label>
<br/>
<textarea  id = "content" name=""  rows="3"  cols="40"  >系统补偿</textarea>
<p></p>

<table id="rounded-corner">
	<thead>
		<tr>
			<th scope="col" class="rounded-company">编号</th>
			<th scope="col" class="rounded">物品表</th>
			<th scope="col" class="rounded">物品</th>
			<th scope="col" class="rounded-q4">数量</th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<!-- <td colspan="6" class="rounded-foot-left"><em>点击添加或删除按钮可以改变发放物品个数</em> </td>-->
		</tr>
	</tfoot>
	<tbody>
		<%
			for (int i = 1; i <= 10; i++) {
		%>
		<tr>
			<td align="center"><%=i%></td>
			<td align="center">
				<select id="<%="sel"+i %>" onchange="tt(this.id ,<%=i %>)">
						<option value=-1>请选择</option>
						<%
							for (Object o : goodstype.keySet()) {
						%><option value=<%=o%>><%=goodstype.get(o)%>
						</option>
						<%
							}
						%>
				</select>
			</td>
			<td align="center">
				<select id="<%="goods"+i %>">
					<option value=-1>请选择</option>
				</select>
			</td>
			<td align="center"><input id="<%="mun"+i %>"  size="5" /> </td>
		</tr>
		<%
			}
		%>

	</tbody>
</table>

<%-- <a href="#" class="bt_blue" onclick="addtr()"><span
	class="bt_green_lft"></span><strong>添加</strong><span class="bt_green_r"></span>
</a> --%>
<%--<a href="#" class="bt_red" onclick="deltr()"><span
	class="bt_green_lft"></span><strong>删除</strong><span class="bt_green_r"></span>
</a> --%>
<a href="#" onclick="lookThingCode()">查看所选物品代码</a> <div id="thingCode"></div>
<a href="#" class="bt_green" onclick="sbumit()"><span
	class="bt_green_lft"></span><strong>确认发放</strong><span
	class="bt_green_r"></span> </a>