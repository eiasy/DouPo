<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String id = request.getParameter("id");
	String rank = request.getParameter("rank");
	String cost = request.getParameter("cost");
	String obtain = request.getParameter("obtain");
	String weight = request.getParameter("weight");
%>

<script type="text/javascript">

	//更新巅峰英雄奖励
	function update() {
		var id = $("#id").val();
		var weight = $("#weight").val();
		var cost = $("#cost").val();
		var obtain = $("#obtain").val();
		var url;
		var message;
		url = "activities!updateTreasuresList.action";
		$.get(url, {
			id : id,
			cost : cost,
			obtain : obtain,
			weight : weight
		}, function(data) {
			var dataObj=eval("("+data+")");
			if (dataObj == null) {
				alert('更新失败');
				return;
			}
			alert(dataObj);
			if (dataObj == '操作成功') {
				$("#right_content").load("zhaocaijb.jsp",function(){$("#right_content").fadeIn(100);});
			}
		});
	}
	
</script>


<form id='from1' name='from1'>

	
	<h2>修改招财进宝活动</h2>
	
	<dl>
		<dt>
			<label for="email">编号</label>
		</dt>
		<dd>
		    <input type="text" id='id' name='id' value="<%=id%>"  readonly/> 
		</dd>
	</dl>
	
	<dl>
		<dt>
			<label for="email">第几次抽元宝</label>
		</dt>
		<dd>
			<input id='rank' value="<%=rank%>" type="text" name='rank' readonly>
		</dd>
	</dl>
	<dl>
		<dt>
			<label for="email">消耗元宝数</label>
		</dt>
		<dd>
			<input type="text" id='cost' name='cost' value="<%=cost%>"/> 
		</dd>
	</dl>
	<dl>
		<dt>
			<label for="email">获得元宝数</label>
		</dt>
		<dd>
			<input type="text" id='obtain' name='obtain' value="<%=obtain%>"/> 
		</dd>
	</dl>
	<dl>
		<dt>
			<label for="email">权重</label>
		</dt>
		<dd>
			<input type="text" id='weight' name='weight' value="<%=weight%>"/> 
		</dd>
	</dl>

	<a href="#" onclick="update()" class="bt_green"><span class="bt_green_lft"></span><strong>确定修改</strong><span class="bt_green_r"></span> </a>
		
</form>