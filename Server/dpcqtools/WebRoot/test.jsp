<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<script type="text/javascript">

	function closeWin(){
		 var rv = document.getElementById("test").value;
		 window.opener.window._aaaaa=rv;
		 parent.window.close();
	}
</script>


<body>
		<input id="test"></input>
		<button onclick="closeWin();">确定</button>
</body>
</html>