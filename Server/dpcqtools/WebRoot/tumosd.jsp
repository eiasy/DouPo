<%@ page language="java"
	import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">

	//显示屠魔商店列表
	function showList() {
		$.getJSON("activities!getBossShopList.action", {}, function (data){
			if(data == null) {
				alert('获取列表失败');
			}
			 var dataObj=eval("("+data+")");//转换为json对象 
	 		 $("#tbody").empty();
			 $.each(dataObj, function(i,item) { 
				 $("#tbody").append(
						"<tr>"+
       					"<td></td>"+
       					"<td align='center'>"+item.id+"</td>"+
       					"<td align='center'>"+item.name+"</td>"+
						"<td align='center'>"+item.things+"</td>"+
						"<td align='center'>"+item.needbossIntegral+"</td>"+
						"<td align='center'>"+item.type+"</td>"+
						"<td align='center'>"+item.rank+"</td>"+
           				"<td><a href='#' onclick='update("+item.type+","+item.rank+","+item.id+",&quot;"+item.things+"&quot;,&quot;"+item.name+"&quot;,"+item.needbossIntegral+");' ><img src='images/user_edit.png' alt='' title='' border='0' /></a></td>"+
       					"</tr>" 
				); 
	 		}); 
		}); 
	}
	
	//更新屠魔商店列表
	function update(type, rank, id, things, name, needbossIntegral) {
		var url = 'tumosdUpdate.jsp?id='+id+'&type='+type+'&rank='+rank+'&things='+things+'&name='+name+'&needbossIntegral='+needbossIntegral;
		$("#right_content").load(url,function(){
			$("#right_content").fadeIn(100);
		});
	}

</script>

<h2>屠魔商店配置</h2>

<div style = "text-align:right;">
	<button onclick="showList()">查询当前配置</button>
</div>
<p>
<table id="rounded-corner" style="width:780xp;">
	<tr>
		<th scope="col" class="rounded-company"></th>
		<th scope="col" class="rounded">编号</th>
		<th scope="col" class="rounded">名称</th>
		<th scope="col" class="rounded">物品(单个)</th>
		<th scope="col" class="rounded">需要积分</th>
		<th scope="col" class="rounded">类型（1-魂魄 2-装备碎片 3-材料）</th>
		<th scope="col" class="rounded">排序</th>
		<th scope="col" class="rounded-q4">修改</th>
	</tr>
</table>

<div style="overflow-x: auto; overflow-y: auto; height:500px; width:650px;">
	<table id="rounded-corner" style="width:780xp;">
		<tbody id="tbody"></tbody>
	</table>
</div>
