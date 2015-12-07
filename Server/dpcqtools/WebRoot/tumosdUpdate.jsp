<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String id = request.getParameter("id");
	String type = request.getParameter("type");
	String rank = request.getParameter("rank");
	String needbossIntegral = request.getParameter("needbossIntegral");
	String things = request.getParameter("things");
	things = new String(things.getBytes("iso-8859-1"), "utf-8");
	String name = request.getParameter("name");
	name = new String(name.getBytes("iso-8859-1"), "utf-8");
%>

<script type="text/javascript">

	//更新屠魔商店
	function update() {
		var id = $("#id").val();
		var name = $("#name").val();
		var rank = $("#rank").val();
		var needbossIntegral = $("#needbossIntegral").val();
		var things = $("#things").val();
		var thingCode = $("#thingCode").val();
		var url;
		var message;
		url = "activities!updateBossShopList.action";
		$.get(url, {
			id : id,
			thingCode : thingCode,
			name : name,
			rank : rank,
			needbossIntegral : needbossIntegral,
			things : things
		}, function(data) {
			var dataObj=eval("("+data+")");
			if (dataObj == null) {
				alert('更新失败');
				return;
			}
			alert(dataObj);
			if (dataObj == '操作成功') {
				$("#right_content").load("tumosd.jsp",function(){$("#right_content").fadeIn(100);});
			}
		});
	}
	
	//打开选择物品子窗口
	var w;//open 窗口对象
	var wTimer;//计时器变量, 监听窗口关闭
	function openThingWindow () {
		  var url = 'goods!selectThing.action';//转向网页的地址;
		 // var url = 'test.jsp';//转向网页的地址;
		  var name;//网页名称，可为空;
		  var iWidth = 625;//弹出窗口的宽度;
		  var iHeight = 485;//弹出窗口的高度;
		  var iTop = (window.screen.availHeight-30-iHeight)/2;//获得窗口的垂直位置;
		  var iLeft = (window.screen.availWidth-10-iWidth)/2;//获得窗口的水平位置;
		  w = window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
		  if (w) {
		    window.w.focus();//判断窗口是否打开,如果打开,窗口前置
		  }
		  wTimer=window.setInterval("wisclosed()",200);
	}

	//监听字窗口是否关闭
	function wisclosed(){
		  if(w.closed){
			    window.clearInterval(wTimer);
			    $("#things").val(window.thingName);
			    $("#thingCode").val(window.thingCode);
		  }
	}
	
</script>


<form id='from1' name='from1' onload="">
	
	<h2>修改屠魔商店物品</h2>
	
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
			<label for="email">名称</label>
		</dt>
		<dd>
			<input id='name' value="<%= name%>" type="text" name='name'>
		</dd>
	</dl>
	<dl>
		<dt>
			<label for="email">物品(可多个)</label>
		</dt>
		<dd>
		<textarea id="things" name="things" style="width:250px;height:120px;" onclick="openThingWindow()" readonly><%=things %></textarea>
		</dd>
	</dl>
	<dl>
		<dt>
			<label for="email">需要积分</label>
		</dt>
		<dd>
			<input id='needbossIntegral' value="<%= needbossIntegral%>" name='needbossIntegral' type="text">
		</dd>
	</dl>
	
	<dl>
		<dt>
			<label for="email">类型</label>
		</dt>
		<dd>
			<input id='type' value="<%= type%>" name='type' type="text" readonly>
		</dd>
	</dl>
	
	<dl>
		<dt>
			<label for="email">排序</label>
		</dt>
		<dd>
			<input id='rank' value="<%= rank%>" name='rank' type="text">
		</dd>
	</dl>
	
	
	<input type="hidden" id='thingCode' name='thingCode'/> 

	<a href="#" onclick="update()" class="bt_green"><span class="bt_green_lft"></span><strong>确定修改</strong><span class="bt_green_r"></span> </a>
		
</form>