<%@ page language="java"
	import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">
	
	//获取周活动
	function getZhouHuoDong() {
		$.get("activities!getZhouHuoDong.action", {}, function (data){
			var dataObj=eval("("+data+")");
			if(dataObj==null) {
				alert('获取列表失败');
				return;
			}
			$("#content").val('');
			$("#content").val(dataObj);
		});
		
	}
	
	//更新周活动
	function updateZhouHuoDong() {
		content = $("#content").val();
		$.get("activities!updateZhouHuoDong.action", {content:content}, function (data){
			var dataObj=eval("("+data+")");
			if(dataObj==null){
				alert('更新失败');
				return;
			}
			alert(data);
		});
	}

</script>

<h2>周活动配置</h2>
<button onclick="getZhouHuoDong()">查询配置</button>
<textarea id="content" name="content" style="width:450px;height:220px;"></textarea>
<button onclick="updateZhouHuoDong()">确定修改</button>
