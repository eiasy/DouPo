<%@ page language="java"
	import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<h2>物品日志信息</h2>
<p>

<a href="#" ><h2><b></b><h2></a>

<center><a href="http://120.132.84.159:10005/log_search" target="_blank"><span id="blink"><b>点我查询</b></span></a></center>
<script type = "text/javascript" >
function blinklink() {
    if (!document.getElementById('blink').style.color) {
        document.getElementById('blink').style.color = "red";
    }
    if (document.getElementById('blink').style.color == "red") {
        document.getElementById('blink').style.color = "#FFFFFF";
    } else {
        document.getElementById('blink').style.color = "red";
    }
    timer = setTimeout("blinklink()", 500);
}
function stoptimer() {
    clearTimeout(timer);
} 
blinklink();
</script>