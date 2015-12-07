<%@ page language="java"
	import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">

	function update(id, name, smallUiId, startTime, endTime, isNew, isForevery, isView, rank) {
	 	var checkValue=$("#sel").val();
	 	if(checkValue > 0) {
		 	var sta = escape(startTime);
		 	var endt = escape(endTime);
		 	var name = encodeURI(name);
			var url = 'hdxg.jsp?id='+id+'&serverid='+checkValue+'&name='+name+'&smallUiId='+smallUiId+'&startTime='+sta+'&endTime='+endt+'&isNew='+isNew+'&isForevery='+isForevery+'&isView='+isView+'&rank='+rank;
			$("#right_content").load(url,function(){
				$("#right_content").fadeIn(100);
			});
		} else {
			alert('请选择大区');
			return;
		}
	}

	function showactivities() {
		 var checkValue=$("#sel").val();
	 	 if(checkValue>0){
			  $.getJSON("activities!showActivities.action", {serverid:checkValue}, function (data){
				  if(data == null){
						$("#tbody").empty();
						alert('服务器连接失败！');
					}else{
						// alert(data);
						 var dataObj=eval("("+data+")");//转换为json对象 
				 		 $("#tbody").empty();
						 $.each(dataObj, function(i,item) { 
					 		 var name = item.name;
					 		 var smallUiId = item.smallUiId;
					 		 var startTime = item.startTime == undefined ? "": item.startTime;
					 		 var endTime = item.endTime == undefined ? "" : item.endTime;
					 		 var isNew = item.isNew == 1 ? "是" : "否";
					 		 var isForevery = item.isHotKey == 1 ? "是" : "否";
					 		 var isView = item.isView == 1 ? "是" : "否";
					 		 var rank = item.rank;
							 $("#tbody").append(
									"<tr>"+
		        					"<td></td>"+
		        					"<td align='center'>"+name+"</td>"+
		        					"<td align='center'>"+smallUiId+"</td>"+
		            				"<td align='center'>"+startTime+"</td>"+
		            				"<td align='center'>"+endTime+"</td>"+
		            				"<td align='center'>"+isNew+"</td>"+
		            				"<td align='center'>"+isForevery+"</td>"+
									"<td align='center'>"+isView+"</td>"+
									"<td align='center'>"+rank+"</td>"+
		            				"<td><a href='#' onclick='update("+item.id+",&quot;"+item.name+"&quot;,"+item.smallUiId+",&quot;"+item.startTime+"&quot;,&quot;"+item.endTime+"&quot;,"+item.isNew+","+item.isHotKey+","+item.isView+","+item.rank+")' ><img src='images/user_edit.png' alt='' title='' border='0' /></a></td>"+
		        					"</tr>" 
							); 
				 		}); 
			 		}
			 }); 
		} else {
		  return;
		}
	}
</script>

<h2>活动管理</h2>

<h4>
	请选择服务器:&nbsp;&nbsp;
	<select id="sel" onchange="showactivities()">
	<option value="-1">请选择</option>
		<% 
		 Set<Integer> key = GameCoon.serverMap.keySet();   
	     for (Iterator<Integer> it = key.iterator();it.hasNext();) {         
	         Integer i = (Integer) it.next();    
	         ServerAttribute sa =(ServerAttribute)GameCoon.serverMap.get(i); 	
	 	%>
		<option value=<%=sa.getId() %>><%= sa.getName() %></option>
		<%} %>
	</select>
</h4>

<table id="rounded-corner" style="width:780xp;">
	<tr>
		<th scope="col" class="rounded-company"></th>
		<th scope="col" class="rounded">名称</th>
		<th scope="col" class="rounded">图片</th>
		<th scope="col" class="rounded">开始时间</th>
		<th scope="col" class="rounded">结束时间</th>
		<th scope="col" class="rounded">新活动</th>
		<th scope="col" class="rounded">是否有快捷图标</th>
		<th scope="col" class="rounded">显示</th>
		<th scope="col" class="rounded">排序</th>
		<th scope="col" class="rounded-q4">修改</th>
	</tr>
</table>

<div style="overflow-x: auto; overflow-y: auto; height:500px; width:650px;">
	<table id="rounded-corner" style="width:780xp;">
		<tbody id="tbody"></tbody>
	</table>
</div>

