<%@ page language="java"
	pageEncoding="utf-8"%>

<script type="text/javascript">

	//世界BOSS奖励产出配置
	function showList() {
		$.getJSON("activities!getWorldBossList.action", {}, function (data){
			if(data == null) {
				alert('获取列表失败');
			}
			 var dataObj=eval("("+data+")");//转换为json对象
	 		 $("#tbody").empty();
			 $.each(dataObj, function(i,item) {
				 $("#tbody").append(
						"<tr align='justify'>"+
       					"<td >"+item.id+"</td>"+
       					"<td >"+item.downRank+"</td>"+
						"<td >"+item.upRank+"</td>"+
						"<td style='text-align:center;'>"+item.description+"</td>"+
           				"<td style='text-align:center;'><a href='#' onclick='update("+item.id+",&quot;"+item.description+"&quot;,"+item.downRank+","+item.upRank+");'><img src='images/user_edit.png' alt='' title='' border='0' /></a></td>"+
       					"</tr>"
				);
	 		});
		});
	}

	//世界BOSS奖励产出配置
	function update(id, description,downRank, upRank ) {
//		alert("能不能进呀这里")
		var url = 'worldBossUpdate.jsp?id='+id+'&downRank='+downRank+'&upRank='+upRank+'&description='+description;
		$("#right_content").load(url,function(){
			$("#right_content").fadeIn(100);
		});
	}


</script>

<h2>世界BOSS奖励</h2>

<div style = "text-align:right;">
	<button onclick="showList()">查询当前配置</button>
</div>
<p>
<table id="rounded-corner" style="length:780xp;">
	<tr>
		<th align='justify' style="text-align:center;" scope="col" class="rounded">编号</th>
		<th align='justify' style="text-align:center;" scope="col" class="rounded">排名下限</th>
		<th align='justify' style="text-align:center;" scope="col" class="rounded">排名上限</th>
		<th align='justify' style="text-align:center;" scope="col" class="rounded">特殊奖励(可多个)</th>
		<th align='justify' style="text-align:center;" scope="col" class="rounded-q4">修改</th>
	</tr>
</table>

<div style="overflow-x: auto; overflow-y: auto; height:500px; width:650px;">
	<table id="rounded-corner" style="length:780xp;">
		<tbody id="tbody"></tbody>
	</table>
</div>
