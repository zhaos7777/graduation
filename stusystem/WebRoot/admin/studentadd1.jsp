<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link href="../css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="../js/select-ui.min.js"></script>
<script type="text/javascript" language="javascript">
	function isnull() {
		var selPro = document.getElementById("selPro");

        if (selPro.value == "0") {
			alert("必须选择专业");
			return false;
		}
	
		return true;
	}
</script>

<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
});
</script>
</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">学生添加</a></li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>专业选择</span>
		</div>

		<ul class="forminfo">
			<form id="frmlogin" name="frmlogin" method="post"
				action="dostudent?op=toadd2&pid=${requestScope.pid}">
               
				<li><label>所属专业</label>
				<div class="vocation">
				<select name="selPro" id="selPro" class="select2">
						<option value="0">请选择</option>
						<c:forEach items="${requestScope.pro}" var="p">
							<option value="${p.pro_id }"> ${p.pro_name}</option>
						</c:forEach>
				</select></div></li>

				<li><label>&nbsp;</label><input name="button" type="submit"
					class="btn" value="下一步" onclick="return isnull();" />
			</form>
		</ul>


	</div>


</body>

</html>

