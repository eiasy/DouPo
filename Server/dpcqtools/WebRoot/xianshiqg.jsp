<%@ page language="java"
	import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">

	//显示限时抢购列表
	function showList() {
		$.getJSON("activities!getLimitShoppingList.action", {}, function (data){
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
						"<td align='center'>"+item.thing+"</td>"+
						"<td align='center'>"+item.limitNum+"</td>"+
						"<td align='center'>"+item.oldPrice+"</td>"+
						"<td align='center'>"+item.newPrice+"</td>"+
						"<td align='center'>"+item.moneyType+"</td>"+
           				"<td><a href='#' onclick='update("+item.id+",&quot;"+item.thing+"&quot;,"+item.limitNum+","+item.oldPrice+","+item.newPrice+","+item.moneyType+");' ><img src='images/user_edit.png' alt='' title='' border='0' /></a></td>"+
       					"</tr>" 
				); 
	 		}); 
		}); 
	}
	
	//更新限时抢购数据
	function update(id, thing, limitNum, oldPrice, newPrice, moneyType) {
		var url = 'xianshiqgUpdate.jsp?id='+id+'&limitNum='+limitNum+'&thing='+thing+'&oldPrice='+oldPrice+'&newPrice='+newPrice+'&moneyType='+moneyType;
		$("#right_content").load(url,function(){
			$("#right_content").fadeIn(100);
		});
	}

</script>

<h2>限时抢购活动配置</h2>

<div style = "text-align:right;">
	<button onclick="showList()">查询当前配置</button>
</div>
<p>
<table id="rounded-corner" style="width:780xp;">
	<tr>
		<th scope="col" class="rounded-company"></th>
		<th scope="col" class="rounded">编号</th>
		<th scope="col" class="rounded">物品(单个)</th>
		<th scope="col" class="rounded">限购次数</th>
		<th scope="col" class="rounded">原价</th>
		<th scope="col" class="rounded">现价</th>
		<th scope="col" class="rounded">货币类型 [1-金币 2-银币]</th>
		<th scope="col" class="rounded-q4">修改</th>
	</tr>
</table>

<div style="overflow-x: auto; overflow-y: auto; height:500px; width:650px;">
	<table id="rounded-corner" style="width:780xp;">
		<tbody id="tbody"></tbody>
	</table>
</div>
