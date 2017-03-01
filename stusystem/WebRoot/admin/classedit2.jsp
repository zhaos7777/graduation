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
<script type="text/javascript" src="../editor/kindeditor.js"></script>

<script type="text/javascript" >
	function isnull() {
		var selTeacher = document.getElementById("selTeacher");
		var classname = document.getElementById("classname");

		if (selTeacher.value == "0") {
			alert("必须选择教师");
			return false;
		}
		if (classname.value == "") {
			alert("班级名称不能是空的");
			return false;
		}


		return true;
	}
</script>

<script type="text/javascript">
    KE.show({
        id : 'content7',
        cssPath : './index.css'
    });
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
			<li><a href="#">班级列表</a></li>
			<li><a href="#">班级修改</a></li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>基本信息</span>
		</div>

		<ul class="forminfo">
			<form id="frmlogin" name="frmlogin" method="post"
				action="doclass?op=doedit&pid=${requestScope.pid}">
				<input name="classid" type="hidden" value="${c.class_id}"/>
				<input name="tid" type="hidden" value="${requestScope.tid }"/>
				<input name="selpid" type="hidden" value="${requestScope.selpid }"/>
				
				<li><label>专业编号</label> 
				<div class="vocation">
				<select name="selTeacher" id="selTeacher" class="select3">
						<option value="0">请选择</option>
						<c:forEach items="${requestScope.t}" var="t">
							<option value="${t.teacher_id}" <c:if test="${t.teacher_id==c.teacher.teacher_id}">selected="selected"</c:if>>${t.teacher_name}</option>
						</c:forEach>
				</select>
				</div>
				</li>
				<li><label>班级名称</label><input id="classname" name="classname"
					type="text" class="dfinput" value="${c.class_name}"/><i>请输入班级的名称</i></li>
				<li><label>&nbsp;</label><input name="button" type="submit"
					class="btn" value="确认修改" onclick="return isnull();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					name="reset" type="reset" class="btn" value="重置"/></li>
			</form>
		</ul>


	</div>


</body>

</html>

