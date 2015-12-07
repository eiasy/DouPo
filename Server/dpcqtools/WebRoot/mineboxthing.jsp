<%@ page language="java"
	pageEncoding="utf-8"%>

<script type="text/javascript">

	//资源矿特殊奖励产出配置
	function showList() {
		$.getJSON("activities!getMineBoxThingList.action", {}, function (data){
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
						"<td align='center'>"+item.chance+"</td>"+
						"<td align='center'>"+item.thing+"</td>"+
           				"<td><a href='#' onclick='update("+item.id+",&quot;"+item.thing+"&quot;,"+item.type+","+item.chance+");'><img src='images/user_edit.png' alt='' title='' border='0' /></a></td>"+
       					"</tr>"
				);
	 		});
		});
	}

	//资源矿特殊奖励产出配置
	function update(id, thing,type, chance ) {
//		alert("能不能进呀这里")
		var url = 'mineboxthingUpdate.jsp?id='+id+'&type='+type+'&chance='+chance+'&thing='+thing;
		$("#right_content").load(url,function(){
			$("#right_content").fadeIn(100);
		});
	}


</script>

<h2>资源矿特殊奖励</h2>

<div style = "text-align:right;">
	<button onclick="showList()">查询当前配置</button>
</div>
<p>
<table id="rounded-corner" style="length:780xp;">
	<tr>
		<th scope="col" class="rounded-company"></th>
		<th scope="col" class="rounded">编号</th>
		<th scope="col" class="rounded">类别</th>
		<th scope="col" class="rounded">权重</th>
		<th scope="col" class="rounded">特殊奖励(可多个)</th>
		<th scope="col" class="rounded-q4">修改</th>
	</tr>
</table>

<div style="overflow-x: auto; overflow-y: auto; height:500px; width:650px;">
	<table id="rounded-corner" style="length:780xp;">
		<tbody id="tbody"></tbody>
	</table>
</div>
