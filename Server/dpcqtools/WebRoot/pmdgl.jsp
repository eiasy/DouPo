<%@ page language="java"
	import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">

	function add() {
		 var checkValue=$("#sel").val();
		 if(checkValue > 0) {
		 	var  url = 'tjpmd.jsp?serverid='+checkValue;
			$("#right_content").load(url,function(){$("#right_content").fadeIn(100);});
		 } else {
			alert('请选择大区');
			return;
		}
	}
	
	function addMulty() {
	 	var  url = 'tjpmdmulty.jsp';
		$("#right_content").load(url,function(){$("#right_content").fadeIn(100);});
	}
	
	function update(id,startTime,endTime,inteval,content) {
	 	var checkValue=$("#sel").val();
	 	if(checkValue > 0){
		 	var sta = escape(startTime);
		 	var endt = escape(endTime);
		 	var cont = encodeURI(content);
			var  url = 'tjpmd.jsp?id='+id+'&serverid='+checkValue+'&startTime='+sta+'&endTime='+endt+'&inteval='+inteval+'&content='+cont;
		//	alert(url);
			$("#right_content").load(url,function(){$("#right_content").fadeIn(100);});
		} else {
			alert('请选择大区');
			return;
		}
	}
	
	function delmarquee(url) {
		if(confirm('确实要删除该内容吗?')){
			dopost(url);
		} else {
			return;
		}
	}

	function showmarquee() {
	 	var checkValue=$("#sel").val();
	 	if(checkValue>0){
		  	$.getJSON("marquee!showMarquee.action", {serverid:checkValue}, function (data){
			  	if(data == null){
					$("#tbody").empty();
					alert('服务器连接失败！');
				}else{
				 	// alert(data);
				 	var dataObj=eval("("+data+")");//转换为json对象 
		 			$("#tbody").empty();
				 	$.each(dataObj, function(i,item) {
				 		var url = "&quot;marquee!delMarquee.action?id="+item.id+"&serverid="+checkValue+"&quot;";
	
						//	var urlupdate = "&quot;marquee!delMarquee.action?id="+item.id+"&quot;"
						// 	alert(url);
						 	//	$("#h4").append("在线人数："
						 		/* alert(item.id);
						 		alert(item.startTime);
						 		alert(item.endTime);
						 		alert(item.inteval);
						 		alert(item.content); */
						 		
				  		$("#tbody").append(
								"<tr>"+
	        					"<td></td>"+
	            				"<td align='center'>"+item.startTime+"</td>"+
	            				"<td align='center'>"+item.endTime+"</td>"+
	           				    "<td align='center'>"+item.inteval+"</td>"+
	            				"<td align='center' >"+item.content+"</td>"+
	            				"<td><a href='#'><img src='images/user_edit.png'  onclick='update("+item.id+",&quot;"+item.startTime+"&quot;,&quot;"+item.endTime+"&quot;,"+item.inteval+",&quot;"+item.content+"&quot;)'  alt='' title='' border='0' /></a></td>"+
			            		"<td><a href='#' class='ask'   onclick='delmarquee("+url+")' ><img src='images/trash.png' alt='' title='' border='0' /></a></td>"+
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

<h2>跑马灯管理</h2>

<h4>
	请选择服务器:&nbsp;&nbsp;
	<select id="sel" onchange="showmarquee()">
		<option value="-1">请选择</option>
		<%  
			Set<Integer> key = GameCoon.serverMap.keySet();   
	     	for (Iterator<Integer> it = key.iterator();it.hasNext();) {         
		        Integer i = (Integer) it.next();    
		        ServerAttribute sa =(ServerAttribute)GameCoon.serverMap.get(i); 	
		%>
		<option value=<%=sa.getId() %>><%= (sa.getId() + "_" + sa.getName()) %></option>
		<%} %>
	</select>
	<div align="right"><button onclick="addMulty()">多服添加跑马灯</button></div>
</h4>

<table id="rounded-corner" style="width: 780xp">
	<thead>
		<tr>
			<th scope="col" class="rounded-company"></th>
			<th scope="col" class="rounded">开始时间</th>
			<th scope="col" class="rounded">结束时间</th>
			<th scope="col" class="rounded">间隔时间（秒）</th>
			<th scope="col" class="rounded">内容</th>
			<th scope="col" class="rounded">修改</th>
			<th scope="col" class="rounded-q4">删除</th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<td colspan="6" class="rounded-foot-left">&nbsp;</td>
			<td class="rounded-foot-right">&nbsp;</td>
		</tr>
	</tfoot>
	<tbody id="tbody">
		<%-- <tr>
        	<td></td>
            <td align="center">2014-12-10</td>
            <td align="center">2014-12-10</td>
            <td align="center">30</td>
            <td align="center" >大家好这是跑马灯下面省略一万字...</td>

            <td><a href="#"><img src="images/user_edit.png" alt="" title="" border="0" /></a></td>
            <td><a href="#" class="ask" onclick="dopost('user!delUser.action?id=${id}')"><img src="images/trash.png" alt="" title="" border="0" /></a></td>
        </tr> --%>

	</tbody>
</table>

<a href="#" class="bt_green" onclick="add()"><span class="bt_green_lft"></span> <strong>添加跑马灯</strong> <span class="bt_green_r"></span> </a>
