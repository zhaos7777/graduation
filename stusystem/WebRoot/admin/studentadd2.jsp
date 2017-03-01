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

		var name = document.getElementById("name");
		var age = document.getElementById("age");
		var home = document.getElementById("home");
		var idcard = document.getElementById("idcard");
        var selClass = document.getElementById("selClass");

		if (name.value == "") {
			alert("姓名不能是空的");
			return false;
		}
		if (age.value == "") {
			alert("年龄不能是空的");
			return false;
		}
		if (idcard.value == "") {
			alert("身份证号码不能是空的");
			return false;
		}
		if (home.value == "") {
			alert("籍贯不能是空的");
			return false;
		}

		if (selClass.value == "0") {
			alert("必须选择班级");
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
			<span>基本信息</span>
		</div>

		<ul class="forminfo">
			<form id="frmlogin" name="frmlogin" method="post"
				action="dostudent?op=doadd&professionid=${pro.pro_id}">
                <li><label>姓名</label><input id="name" name="name"
					type="text" class="dfinput" /><i>请输入姓名</i></li>
				<li><label>性别</label><cite>
				<c:forEach items="${requestScope.s}" var="s">
				<input id="sex" name="sex" type="radio" value="${s.sex_id}" <c:if test="${s.sex_id==1}"> checked="checked"</c:if> />${s.sex_name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</c:forEach>
				<li><label>年龄</label><input id="age" name="age"
					type="text" class="dfinput" /><i>请输入年龄</i></li>
				<li><label>身份证号</label><input id="id" name="id"
					type="text" class="dfinput" /><i>请输入身份证号码</i></li>
				<li><label>籍贯</label><input id="home" name="home"
					type="text" class="dfinput" /><i>请输入户籍所在地</i></li>
				<li><label>所属专业</label></br>${pro.pro_name}</li>	
				<li><label>所属班级</label>
				<div class="vocation">
				 <select name="selClass" id="selClass" class="select3">
						<option value="0">请选择</option>
						<c:forEach items="${requestScope.c}" var="c">
							<option value="${c.class_id }"> ${c.class_name}</option>
						</c:forEach>
				</select></div></li>

				<li><label>&nbsp;</label><input name="button" type="submit"
					class="btn" value="确认添加" onclick="return isnull();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					name="reset" type="reset" class="btn" value="重置"/></li>
			</form>
		</ul>


	</div>


</body>

</html>

