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
		var name = document.getElementById("name");
		var age = document.getElementById("age");
		

		if (selPro.value == "0") {
			alert("必须选择专业");
			return false;
		}
		if (name.value == "") {
			alert("班级名称不能是空的");
			return false;
		}
		if (age.value == "") {
			alert("带班教师不能是空的");
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
			<li><a href="#">教师列表</a></li>
			<li><a href="#">教师修改</a></li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>基本信息</span>
		</div>

		<ul class="forminfo">
			<form id="frmlogin" name="frmlogin" method="post"
				action="doteacher?op=doedit">
				<input type="hidden" name="pid" value="${requestScope.pid }" />
				<input type="hidden" name="id" value="${t.teacher_id }"/>
				<li>
				<label>所属专业</label>
                <div class="vocation">
				<select name="selPro" id="selPro" class="select2">
						<option value="0">请选择</option>
						<c:forEach items="${requestScope.pro}" var="p">
							<option value="${p.pro_id }" <c:if test="${p.pro_id==t.pro.pro_id}">selected="selected"</c:if> >${p.pro_name}</option>
						</c:forEach>
				</select>
				</div>
				</li>
				<li><label>姓名</label><input id="name" name="name"
					type="text" class="dfinput" value="${t.teacher_name }"/><i>请输入您的姓名</i></li>
				<li><label>年龄</label><input id="age"
					name="age" type="text" class="dfinput" value="${t.teacher_age }" /><i>请输入你的年龄</i></li>
				<li><label>性别</label><cite>
				<c:forEach items="${requestScope.sex}" var="s">
				<input id="sex" name="sex" type="radio" value="${s.sex_id}" <c:if test="${s.sex_id==t.sex.sex_id}"> checked="checked"</c:if> />${s.sex_name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</c:forEach>	
				<li><label>&nbsp;</label><input name="button" type="submit"
					class="btn" value="确认修改" onclick="return isnull();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					name="reset" type="reset" class="btn" value="重置"/></li>
			</form>
		</ul>


	</div>


</body>

</html>

