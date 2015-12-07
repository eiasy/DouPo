<%@ page language="java"
	import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">

	//显示招财进宝当前配置
	function showList() {
		$.getJSON("activities!getTreasuresList.action", {}, function (data){
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
       					"<td align='center'>"+item.rank+"</td>"+
						"<td align='center'>"+item.cost+"</td>"+
						"<td align='center'>"+item.obtain+"</td>"+
						"<td align='center'>"+item.weight+"</td>"+
           				"<td><a href='#' onclick='update("+item.id+",&quot;"+item.rank+"&quot;,"+item.cost+","+item.obtain+","+item.weight+");' ><img src='images/user_edit.png' alt='' title='' border='0' /></a></td>"+
       					"</tr>" 
				); 
	 		}); 
		}); 
	}
	
	//更新招财进宝
	function update(id, rank, cost, obtain, weight) {
		var url = 'zhaocaijbUpdate.jsp?id='+id+'&rank='+rank+'&cost='+cost+'&obtain='+obtain+'&weight='+weight;
		$("#right_content").load(url,function(){
			$("#right_content").fadeIn(100);
		});
	}

</script>

<h2>招财进宝活动配置</h2>

<div style = "text-align:right;">
	<button onclick="showList()">查询当前配置</button>
</div>
<p>
<table id="rounded-corner" style="width:780xp;">
	<tr>
		<th scope="col" class="rounded-company"></th>
		<th scope="col" class="rounded">编号</th>
		<th scope="col" class="rounded">第几次抽元宝</th>
		<th scope="col" class="rounded">消耗元宝数</th>
		<th scope="col" class="rounded">获得元宝数</th>
		<th scope="col" class="rounded">权重（概率）</th>
		<th scope="col" class="rounded-q4">修改</th>
	</tr>
</table>

<div style="overflow-x: auto; overflow-y: auto; height:500px; width:650px;">
	<table id="rounded-corner" style="width:780xp;">
		<tbody id="tbody"></tbody>
	</table>
</div>
