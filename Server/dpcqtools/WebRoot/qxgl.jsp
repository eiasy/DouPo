<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<SCRIPT type="text/javascript">
	
	var setting = {
		check : {
			enable : true
		},
		data : {
			simpleData : {
				enable : true
			}
		}
	};

	var zNodes = [ {
			id : 1,
			pId : 0,
			name : "运营管理",
			value : 0,
			open : true
		}, {
			id : 11,
			pId : 1,
			name : "玩家帐号",
			value : 1,
			open : true
		}, {
			id : 12,
			pId : 1,
			name : "游戏活动",
			value : 2,
			open : true
		}, {
			id : 13,
			pId : 1,
			name : "相关配置",
			value : 3,
			open : true
		}, {
			id : 14,
			pId : 1,
			name : "发放物品",
			value : 4,
			open : true
		},{
			id : 15,
			pId : 1,
			name : "跑马灯管理",
			value : 4,
			open : true
		},{
			id : 16,
			pId : 1,
			name : "活动开关",
			value : 4,
			open : true
		}, {
			id : 2,
			pId : 0,
			name : "客服管理",
			value : 0,
			open : true
		}, {
			id : 21,
			pId : 2,
			name : "玩家帐号",
			value : 1,
			open : true
		}, {
			id : 3,
			pId : 0,
			name : "运维管理",
			value : 0,
			open : true
		}, {
			id : 31,
			pId : 3,
			name : "分区配置",
			value : 1,
			open : true
		}, {
			id : 32,
			pId : 3,
			name : "数据相关",
			value : 2,
			open : true
		}, {
			id : 33,
			pId : 3,
			name : "配置相关",
			value : 3,
			open : true
		}, {
			id : 4,
			pId : 0,
			name : "系统管理",
			value : 0,
			open : true
		}, {
			id : 41,
			pId : 4,
			name : "账户管理",
			value : 1,
			open : true
		}, {
			id : 42,
			pId : 4,
			name : "权限管理",
			value : 2,
			open : true
		}, {
			id : 43,
			pId : 4,
			name : "help",
			value : 3,
			open : true
		},
	];

	var code;

	function setCheck() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"), type = {
			"Y" : "ps",
			"N" : "ps"
		};
		;
		zTree.setting.check.chkboxType = type;
		showCode('setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };');
	}
	
	function showCode(str) {
		if (!code)
			code = $("#code");
		code.empty();
		code.append("<li>" + str + "</li>");
	}

	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		setCheck();

	});
</SCRIPT>

<script type="text/javascript">
	
	function onCheck(e, treeId, treeNode) {
		var userid = $("#user").val();
		if (userid < 1) {
			alert('请选择要分配权限的帐号');
			return;
		}
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree.getCheckedNodes(true), v = "";
		for ( var i = 0, l = nodes.length; i < l; i++) {
			/* v += nodes[i].name + ",";
			alert(nodes[i].value);   //获取选中节点的id值 */
			/* document.getElementById('test').value=nodes[i].id; */
			v += nodes[i].id + ",";

		}
		$.get("user!addQxgl.action", {id:userid ,compet:v}, function (data){
			alert('修改成功');
		});
	}
</script>

<h2>权限管理</h2>

<form id='from1' name='from1' >
	<h4>
		请选择帐号:&nbsp;&nbsp;
		<select id="user">
			<option value="-1">请选择</option>
			<s:iterator value="userlist">
				<option value="<s:property value='id' />"> <s:property value="name" /> </option>
			</s:iterator>
		</select>
	</h4>
	
	<h2>权限管理</h2>
	<ul id="treeDemo" class="ztree"></ul>
	<input type="button" id="test" value="分配权限" onclick="onCheck();" />
</form>
