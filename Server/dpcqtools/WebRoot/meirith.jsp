<%@ page language="java"
	import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">

	//显示每日特惠列表
	function showList() {
		$.getJSON("activities!getDailyDealsList.action", {}, function (data){
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
       					"<td align='center'>"+item.day+"</td>"+
						"<td align='center'>"+item.thingsName+"</td>"+
						"<td align='center'>"+item.things+"</td>"+
						"<td align='center'>"+item.price+"</td>"+
           				"<td><a href='#' onclick='update("+item.id+",&quot;"+item.things+"&quot;,&quot;"+item.thingsName+"&quot;,&quot;"+item.price+"&quot;,"+item.day+");' ><img src='images/user_edit.png' alt='' title='' border='0' /></a></td>"+
       					"</tr>" 
				); 
	 		}); 
		}); 
	}
	
	//更新每日特惠列表
	function update(id, things, thingsName, price, day) {
		var url = 'meirithUpdate.jsp?id='+id+'&things='+things+'&price='+price+'&thingsName='+thingsName+'&day='+day;
		$("#right_content").load(url,function(){
			$("#right_content").fadeIn(100);
		});
	}

</script>

<h2>获得每日特惠列表</h2>

<div style = "text-align:right;">
	<button onclick="showList()">查询当前配置</button>
</div>
<p>
<table id="rounded-corner" style="width:780xp;">
	<tr>
		<th scope="col" class="rounded-company"></th>
		<th scope="col" class="rounded">编号</th>
		<th scope="col" class="rounded">天</th>
		<th scope="col" class="rounded">礼包名称</th>
		<th scope="col" class="rounded">礼包物品(固定6个)</th>
		<th scope="col" class="rounded">物品价格(固定6个)</th>
		<th scope="col" class="rounded-q4">修改</th>
	</tr>
</table>

<div style="overflow-x: auto; overflow-y: auto; height:500px; width:650px;">
	<table id="rounded-corner" style="width:780xp;">
		<tbody id="tbody"></tbody>
	</table>
</div>
