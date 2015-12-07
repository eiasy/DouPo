<%@ page language="java"
	import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">

	//显示天魂墓地掉落列表
	function showList() {
		$.getJSON("activities!getSoulActivityChapterList.action", {}, function (data){
			if(data == null) {
				alert('获取列表失败');
			}
			 var dataObj=eval("("+data+")");//转换为json对象 
	 		 $("#tbody").empty();
			 $.each(dataObj, function(i,item) {
			 	var diffcult = '';
			 	if (item.difficultyBarrierId == 1010){
			 		diffcult = '简单';
			 	}
			 	if (item.difficultyBarrierId == 1011){
			 		diffcult = '普通';
			 	}
			 	if (item.difficultyBarrierId == 1012){
			 		diffcult = '困难';
			 	}
				 $("#tbody").append(
						"<tr>"+
       					"<td></td>"+
       					"<td align='center'>"+item.id+"</td>"+
       					"<td align='center'>"+item.weekDay+"</td>"+
						"<td align='center'>"+diffcult+"</td>"+
						"<td align='center'>"+item.things+"</td>"+
           				"<td><a href='#' onclick='update("+item.id+",&quot;"+item.things+"&quot;,"+item.weekDay+","+item.difficultyBarrierId+");' ><img src='images/user_edit.png' alt='' title='' border='0' /></a></td>"+
       					"</tr>" 
				); 
	 		}); 
		}); 
	}
	
	//更新巅峰强者-排名奖励列表
	function update(id, things, weekDay, difficultyBarrierId) {
		var diffcult = '';
	 	if (difficultyBarrierId == 1010){
	 		diffcult = '简单';
	 	}
	 	if (difficultyBarrierId == 1011){
	 		diffcult = '普通';
	 	}
	 	if (difficultyBarrierId == 1012){
	 		diffcult = '困难';
	 	}
		var url = 'tianhunmdUpdate.jsp?id='+id+'&weekDay='+weekDay+'&diffcult='+diffcult+'&things='+things;
		$("#right_content").load(url,function(){
			$("#right_content").fadeIn(100);
		});
	}

</script>

<h2>天魂墓地掉落数据</h2>

<div style = "text-align:right;">
	<button onclick="showList()">查询当前配置</button>
</div>
<p>
<table id="rounded-corner" style="width:780xp;">
	<tr>
		<th scope="col" class="rounded-company"></th>
		<th scope="col" class="rounded">编号</th>
		<th scope="col" class="rounded">星期几</th>
		<th scope="col" class="rounded">难度</th>
		<th scope="col" class="rounded">人物魂魄(单个)</th>
		<th scope="col" class="rounded-q4">修改</th>
	</tr>
</table>

<div style="overflow-x: auto; overflow-y: auto; height:500px; width:650px;">
	<table id="rounded-corner" style="width:780xp;">
		<tbody id="tbody"></tbody>
	</table>
</div>
