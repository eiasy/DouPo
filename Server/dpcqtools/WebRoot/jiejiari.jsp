<%@ page language="java"
	import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">

	//显示节假日当前配置
	function showList() {
		$.getJSON("activities!getActivityHolidayList.action", {}, function (data){
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
						"<td align='center'>"+item.startTime+"</td>"+
						"<td align='center'>"+item.endTime+"</td>"+
						"<td align='center'>"+item.multiple+"</td>"+
           				"<td><a href='#' onclick='update("+item.id+",&quot;"+item.name+"&quot;,"+"&quot;"+item.startTime+"&quot;,&quot;"+item.endTime+"&quot;"+","+item.multiple+");' ><img src='images/user_edit.png' alt='' title='' border='0' /></a></td>"+
       					"</tr>" 
				); 
	 		}); 
		}); 
	}
	
	//更新节假日
	function update(id, name, startTime, endTime, multiple) {
		var sta = escape(startTime);
		var endt = escape(endTime);
		var url = 'jiejiariUpdate.jsp?id='+id+'&name='+name+'&startTime='+sta+'&endTime='+endt+'&multiple='+multiple;
		$("#right_content").load(url,function(){
			$("#right_content").fadeIn(100);
		});
	}

</script>

<h2>节假日活动配置</h2>

<div style = "text-align:right;">
	<button onclick="showList()">查询当前配置</button>
</div>
<p>
<table id="rounded-corner" style="width:780xp;">
	<tr>
		<th scope="col" class="rounded-company"></th>
		<th scope="col" class="rounded">编号</th>
		<th scope="col" class="rounded">活动名称</th>
		<th scope="col" class="rounded">开始时间</th>
		<th scope="col" class="rounded">结束时间</th>
		<th scope="col" class="rounded">倍数/折扣</th>
		<th scope="col" class="rounded-q4">修改</th>
	</tr>
</table>

<div style="overflow-x: auto; overflow-y: auto; height:500px; width:650px;">
	<table id="rounded-corner" style="width:780xp;">
		<tbody id="tbody"></tbody>
	</table>
</div>
