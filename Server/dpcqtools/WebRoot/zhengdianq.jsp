<%@ page language="java"
	import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">

	//显示整点抢列表
	function showList() {
		$.getJSON("activities!getGrabTheHourList.action", {}, function (data){
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
       					"<td align='center'>"+item.type+"</td>"+
						"<td align='center'>"+item.buyCount+"</td>"+
						"<td align='center'>"+item.rank+"</td>"+
						"<td align='center'>"+item.things+"</td>"+
						"<td align='center'>"+item.buyType+"</td>"+
						"<td align='center'>"+item.buyValue+"</td>"+
						"<td align='center'>"+item.originalValue+"</td>"+
           				"<td><a href='#' onclick='update("+item.id+",&quot;"+item.things+"&quot;,"+item.type+","+item.buyCount+","+item.buyType+","+item.buyValue+","+item.originalValue+","+item.rank+");' ><img src='images/user_edit.png' alt='' title='' border='0' /></a></td>"+
       					"</tr>" 
				); 
	 		}); 
		}); 
	}
	
	//更新整点抢
	function update(id, things, type, buyCount, buyType, buyValue, originalValue, rank) {
	alert(things);
		var url = 'zhengdianqUpdate.jsp?id='+id+'&type='+type+'&things='+things+'&buyCount='+buyCount+'&buyType='+buyType+'&buyValue='+buyValue+'&originalValue='+originalValue+'&rank='+rank;
		$("#right_content").load(url,function(){
			$("#right_content").fadeIn(100);
		});
	}

</script>

<h2>整点抢活动配置</h2>

<div style = "text-align:right;">
	<button onclick="showList()">查询当前配置</button>
</div>
<p>
<table id="rounded-corner" style="width:780xp;">
	<tr>
		<th scope="col" class="rounded-company"></th>
		<th scope="col" class="rounded">编号</th>
		<th scope="col" class="rounded">星期几</th>
		<th scope="col" class="rounded">全服购买次数</th>
		<th scope="col" class="rounded">排序</th>
		<th scope="col" class="rounded">物品(单个)</th>
		<th scope="col" class="rounded">购买类型[1-元宝 2-银币]</th>
		<th scope="col" class="rounded">现价</th>
		<th scope="col" class="rounded">原价</th>
		<th scope="col" class="rounded-q4">修改</th>
	</tr>
</table>

<div style="overflow-x: auto; overflow-y: auto; height:500px; width:650px;">
	<table id="rounded-corner" style="width:780xp;">
		<tbody id="tbody"></tbody>
	</table>
</div>
