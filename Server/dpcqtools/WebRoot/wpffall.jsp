<%@ page language="java" import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	Map<Integer ,String> goodstype = (Map<Integer ,String>) session.getAttribute("tabletype");
%>

<script type="text/javascript">

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
		var goods = "";
		var content = $("#content").val();
	
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
		
		$.get("goods!provideAll.action", {start:start ,end:end , goods:goods,content:content}, function (data){
			var dataObj=eval("("+data+")");
			alert(dataObj);
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

<h2>全服发放物品</h2>

	选择要发放的服务器：
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
<p></p>

<div>
	<input type="text" id = "gamename" name=""  value="填写玩家角色名多个玩家用;号相隔" style="display:none;" onclick="this.value=''" />
</div>
<input type="radio" name="type"  value="2" checked="checked"  /><label class="check_label">全服发放</label><br/>
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
<a href="#" onclick="lookThingCode()">查看所选物品代码</a> <div id="thingCode"></div>
<a href="#" class="bt_green" onclick="sbumit()"><span
	class="bt_green_lft"></span><strong>确认发放</strong><span
	class="bt_green_r"></span> </a>