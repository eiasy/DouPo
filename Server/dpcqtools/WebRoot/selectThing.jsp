<%@ page language="java" import="java.util.*,com.hygame.dpcq.coon.GameCoon ,com.hygame.dpcq.db.dao.model.ServerAttribute" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	Map<Integer ,String> goodstype = (Map<Integer ,String>) session.getAttribute("tabletype");
%>

<link rel="stylesheet" type="text/css" href="css/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />

<script type="text/javascript" src="js/clockp.js"></script>
<script type="text/javascript" src="js/clockh.js"></script> 
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/ddaccordion.js"></script>
<script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="js/jquery.ztree.excheck-3.5.js"></script>

<script type="text/javascript">
	
	//获得子下拉框的内容
	function tt(id , goodsid) {
		var checkValue=$("#"+id).val();
		var gid ="goods"+goodsid;
		$("#"+gid+ " option").remove();//清除下拉选说有选项
		$.getJSON("goods!sel.action", {id:checkValue}, function (data){
			if(data == null){
				alert('服务器连接失败!');
			} else {
				var dataObj=eval("("+data+")");
				for(var i = 0; i < data.length; i++){
					$("#"+gid).append("<option value='"+dataObj[i].id+"'>"+dataObj[i].name+"</option>");
				    //alert(dataObj[i].id+"=="+dataObj[i].name);
				}
			}
		});
		$("#mun"+goodsid).val('1');
	}
	
	//确定选中后将文本和code值发给父窗口
	function lookThingCode () {
		var goods = "";
		var goodCode = "";
		for(var i=1;i<10;++i){
			var typeid=$("#sel"+i).val();
			if(typeid > 0){
				var code=$("#goods"+i).val();
				var name=$("#goods"+i).find("option:selected").text(); 
				var mun=$("#mun"+i).val();
				name = name.substring(4, name.length);
				goods = goods + name+'_'+mun+';';
				goodCode = goodCode + typeid + '_'+code+'_'+mun+';';
			}
		}
		goods = goods.substring(0, goods.length - 1);
		goodCode = goodCode.substring(0, goodCode.length - 1);
		window.opener.window.thingName=goods;
		window.opener.window.thingCode=goodCode;
	    parent.window.close();
	}
	
</script>

<table id="rounded-corner">
	<thead>
		<tr>
			<th scope="col" class="rounded-company">编号</th>
			<th scope="col" class="rounded">物品表</th>
			<th scope="col" class="rounded">物品</th>
			<th scope="col" class="rounded-q4">数量</th>
		</tr>
	</thead>
	<tbody>
		<%
			for (int i = 1; i <= 10; i++) {
		%>
		<tr>
			<td align="center"><%=i%></td>
			<td align="center">
				<select id="<%="sel"+i %>" onchange="tt(this.id ,<%=i %>)">
						<option value=-1>请选择</option>
						<%
							for (Object o : goodstype.keySet()) {
						%><option value=<%=o%>><%=goodstype.get(o)%>
						</option>
						<%
							}
						%>
				</select>
			</td>
			<td align="center">
				<select id="<%="goods"+i %>">
					<option value=-1>请选择</option>
				</select>
			</td>
			<td align="center"><input id="<%="mun"+i %>"  size="5" /> </td>
		</tr>
		<%
			}
		%>

	</tbody>
</table>
<a href="#" class="bt_green" onclick="lookThingCode()"><span class="bt_green_lft"></span><strong>确定所选</strong><span class="bt_green_r"></span> </a>
	
	