<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>游戏管理系统</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/ddaccordion.js"></script>
<script type="text/javascript">
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

<script type="text/javascript" src="js/jconfirmaction.jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.ask').jConfirmAction();
	});
</script>

<script language="javascript" type="text/javascript" src="js/niceforms.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="css/niceforms-default.css" />

</head>
<body>
	<div id="main_container">

		<div class="header_login">
			<div class="logo">
				<a href="#"><img src="images/logo.png" alt="" title=""
					border="0" />
				</a>
			</div>
		</div>

		<div class="login_form">

			<h3>管理员登录</h3>

			<!--a href="#" class="forgot_pass">Forgot password</a-->

			<form action="login"  method="post" class="niceform">

				<fieldset>
					<dl>
						<dt>
							<label for="email">用户名:</label>
						</dt>
						<dd>
							<input type="text" name="username" id="" size="54" />
						</dd>
					</dl>
					<dl>
						<dt>
							<label for="password">密码:</label>
						</dt>
						<dd>
							<input type="password" name="password" id="" size="54" />
						</dd>
					</dl>

					<dl>
						<dt>
							<label></label>
						</dt>
						<!--dd>
                   			 <input type="checkbox" name="interests[]" id="" value="" /><label class="check_label">Remember me</label>
                        </dd-->
					</dl>

					<dl class="submit">
						<dd>
							<a href="index.html"> <input type="submit" name="submit"
								id="submit" value="Enter"
								onclick="window.location.href('index.html')" /> </a>
						</dd>
					</dl>
					<dl>
						<dd><font color="red">${mes}</font></dd>
					</dl>
				</fieldset>
			</form>
		</div>

		<div class="footer_login">
			<div class="left_footer_login">帐号情况请询问相关管理人员       </div>
			<div class="right_footer_login">
				<a href="http://www.huayigame.com/"><img src="images/logos.JPG" alt="" title="" border="0" /> </a>
			</div>
		</div>
		
	</div>
	
</body>
</html>
