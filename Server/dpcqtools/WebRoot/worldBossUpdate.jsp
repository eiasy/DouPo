<%@ page language="java" pageEncoding="utf-8"%>
<%
	String id = request.getParameter("id");
	String downRank = request.getParameter("downRank");
	String upRank = request.getParameter("upRank");
	String rewards = request.getParameter("description");
	rewards = new String(rewards.getBytes("iso-8859-1"), "utf-8");
%>

<script type="text/javascript">

	//更新限时英雄奖励
	function update() {
		var id = $("#id").val();
		var downRank = $("#downRank").val();
		var upRank = $("#upRank").val();
		var rewards = $("#rewards").val();
		var thingCode = $("#thingCode").val();
		var url;
		url = "activities!updateWorldBossList.action";
		$.get(url, {
			id : id,
			thingCode : thingCode,
			rewards : rewards
		}, function(data) {
			var dataObj=eval("("+data+")");
			if (dataObj == null) {
				alert('更新失败');
				return;
			}
			alert(dataObj);
			if (dataObj == '操作成功') {
				$("#right_content").load("worldBoss.jsp",function(){$("#right_content").fadeIn(100);});
			}
		});
	}

	//打开选择物品子窗口
	var w;//open 窗口对象
	var wTimer;//计时器变量, 监听窗口关闭
	function openThingWindow () {
		  var url = 'goods!selectThing.action';//转向网页的地址;
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
        /** @namespace w.closed */
        if(w.closed){
			    window.clearInterval(wTimer);
			    $("#rewards").val(window.thingName);
			    $("#thingCode").val(window.thingCode);
		  }
	}

</script>


<form id='from1' name='from1' onload="">
	<h2>修改资源矿特殊奖励</h2>
	<dl>
		<dt>
			<label>编号</label>
		</dt>
		<dd>
		    <input type="text" id='id' name='id' value="<%=id%>"  readonly/>
		</dd>
	</dl>
	<dl>
		<dt>
			<labe >排名下限</labe>
		</dt>
		<dd>
			<input id='type' value="<%=downRank%>" type="text" name='downRank' readonly>
		</dd>
	</dl>
	<dl>
		<dt>
			<label>排名上限</label>
		</dt>
		<dd>
			<input id='chance' value="<%=upRank%>" type="text" name='upRank' readonly>
		</dd>
	</dl>
	<dl>
		<dt>
			<label>特殊奖励(可多个)</label>
		</dt>
		<dd>
		<textarea id="rewards" name="rewards" style="width:250px;height:120px;" onclick="openThingWindow()" readonly><%=rewards %></textarea>

		</dd>
	</dl>
	<input type="hidden" id='thingCode' name='thingCode'/>

	<a href="#" onclick="update()" class="bt_green"><span class="bt_green_lft"></span><strong>确定修改</strong><span class="bt_green_r"></span> </a>

</form>