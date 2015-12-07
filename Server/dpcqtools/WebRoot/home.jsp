<%@ page language="java" import="java.util.*,com.hygame.dpcq.db.dao.model.User,com.hygame.dpcq.db.dao.model.MenuCom,com.hygame.dpcq.db.dao.daoimp.UserComImp"  pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	User u = (User) session.getAttribute("user");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理工具主页面</title>
<link rel="stylesheet" type="text/css" href="css/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />

<script type="text/javascript" src="js/clockp.js"></script>
<script type="text/javascript" src="js/clockh.js"></script> 
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/ddaccordion.js"></script>
<script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="js/jquery.ztree.excheck-3.5.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
	$.ajaxSetup({
		cache: false //关闭AJAX相应的缓存
	});

	ddaccordion.init({
		headerclass: "submenuheader", //Shared CSS class name of headers group
		contentclass: "submenu", //Shared CSS class name of contents group
		revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
		mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
		collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
		defaultexpanded: [], //index of content(s) open by default [index1, index2, etc] [] denotes no content
		onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
		animatedefault: false, //Should contents open by default be animated into view?
		persiststate: true, //persist state of opened contents within browser session?
		toggleclass: ["", ""], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
		togglehtml: ["suffix", "<img src='images/plus.gif' class='statusicon' />", "<img src='images/minus.gif' class='statusicon' />"], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
		animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
		oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
			//do nothing
		},
		onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
			//do nothing
		}
	});
</script>

<script src="js/jquery.jclock-1.2.0.js.txt" type="text/javascript"></script>
<script type="text/javascript" src="js/jconfirmaction.jquery.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('.ask').jConfirmAction();
	});
</script>

<script type="text/javascript">
	$(function($) {
	    $('.jclock').jclock();
	});
</script>

<script language="javascript" type="text/javascript" charset="UTF-8">   
	
	function jump(name){
		var  url = name+'.jsp';
		$("#right_content").load(url,function(){$("#right_content").fadeIn(100);});
	}
	
	//上传文件提交
	/* function upload(){
		$("#form2").submit(); 
	} */

	function dopost(url){
		var value =encodeURI(encodeURI(url));
		//alert(value);
		$.ajax({
		      type: "post",     
		      url: value, 
		      data:$('#from1').serialize(),
		      success: function(data) {
		     	 $("#right_content").html(data);
		      },
		      error: function(xhr,msg,e) {
		      }
		});
	}
	
	function reconnection(){
		$.get("sendmessage!reconnection.action", function (data){
			alert('服务器以重连');
		});
	}
</script>

<script language="javascript" type="text/javascript" src="js/niceforms.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="css/niceforms-default.css" />

</head>
<body>
	<div id="main_container">
		<div class="header">
		    <div class="logo">
		    	<a href="#"><img src="images/logo.png" alt="" title="" border="0" /></a>
		    </div>
		    <div class="right_header">Welcome <%=u.getUsername() %>, <a href="#" onclick="jump('xgmm')" >修改密码</a>| <a href="login!logout.action" class="logout">登出</a></div>
		    <div class="jclock"></div>
	    </div>
	    
	    <div class="main_content">
	         <div class="menu">
	        	 <ul/>
	         </div> 
	    <div class="center_content">  
		    <div class="left_content">
		      	<div class="sidebarmenu">
		           <%
		          	UserComImp ic = new UserComImp();
		          	Map<Integer ,List<MenuCom>> map = ic.getuserCom(u);
		            Set<Integer> key = map.keySet();   
					for (Iterator<Integer> it = key.iterator();it.hasNext();) {       
			        	Integer i = (Integer) it.next();    
			         	List<MenuCom> list =(List<MenuCom>)map.get(i); 	
		            %>  
		            <a class="menuitem submenuheader" href=""><%=list.get(0).getName() %></a>
		            <div class="submenu">
		            <ul>
			           <% for(int n = 1 ; n<list.size() ;n++ ) {%>
			                    <li><a href="#" onclick="<%=list.get(n).getUrl()%>"><%=list.get(n).getName() %> </a></li>
			                   <!--  <li><a href="#" onclick="dopost('upload!showfile.action')">游戏活动</a></li>
			                    <li><a href="#" onclick="jump('xgpz')">相关配置</a></li>
			                    <li><a href="#" onclick="dopost('goods!selestGoods.action')">发放物品</a></li>
			                    <li><a href="#" onclick="jump('pmdgl')">跑马灯管理</a></li>
			                    <li><a href="#" onclick="jump('hdkg')">活动开关</a></li> -->
			             <%}%>
		            </ul>
		            </div>
		            <%}%>       
		                    <!--li><a href="">Sidebar submenu</a></li>
		                    <li><a href="">Sidebar submenu</a></li-->
		      
		              <!--   <a class="menuitem submenuheader" href="" >客服管理</a>
		                <div class="submenu">
		                    <ul>
		                    <li><a href="#" onclick="jump('wjzh')">玩家帐号</a></li>
		                    li><a href="">Sidebar submenu</a></li>
		                    <li><a href="">Sidebar submenu</a></li>
		                    <li><a href="">Sidebar submenu</a></li>
		                    <li><a href="">Sidebar submenu</a></li
		                    </ul>
		                </div>
		                <a class="menuitem submenuheader" href="">运维管理</a>
		                <div class="submenu">
		                    <ul>
		                    <li><a href="#" onclick="dopost('server!showServerAttribute.action')">分区配置</a></li>
		                    <li><a href="#" onclick="jump('sjxg')">数据相关</a></li>
		                    <li><a href="#" onclick="jump('pzxg')">配置相关</a></li>
		                    li><a href="">Sidebar submenu</a></li>
		                    <li><a href="">Sidebar submenu</a></li
		                    </ul>
		                </div>
		                 <a class="menuitem submenuheader" href="">系统管理</a>
		                <div class="submenu">
		                    <ul>
		                    <li><a href="#" onclick="dopost('user!findUserAll.action')">账户管理</a></li>
		                    <li><a href="#" onclick="dopost('user!findQxgl.action')">权限管理</a></li>
		                    <li><a href="#" onclick="jump('help')">help</a></li>
		                    li><a href="">Sidebar submenu</a></li>
		                    <li><a href="">Sidebar submenu</a></li
		                    </ul>
		                </div> -->
		                <!--a class="menuitem" href="">User Reference</a>
		                <a class="menuitem" href="">Blue button</a>
		                <a class="menuitem_green" href="">Green button</a>
		                <a class="menuitem_red" href="">Red button</a-->
		        </div>
		      <div class="sidebar_box"></div>
		    </div>  
		 <div id="right_content" class="right_content">  </div> <!-- end of right content--> 
	                    
	  </div>   <!--end of center content -->               
	    <div class="clear"></div> </div> <!--end of main content-->
	    <div class="footer">
	    	<div class="left_footer"></div>
	    	<div class="right_footer"><a href="http://www.huayigame.com/"><img src="images/logos.JPG" alt="" title="" border="0" /></a></div>
	    </div>
	</div>		
</body>
</html>