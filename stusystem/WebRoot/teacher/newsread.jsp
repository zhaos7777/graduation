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
<script type="text/javascript" language="javascript">
	function isnull() {
		var selTopic = document.getElementById("selTopic");
		var title = document.getElementById("title");
		var author = document.getElementById("author");
		var context = document.getElementById("context");

		if (selTopic.value == "0") {
			alert("必须选择主题");
			return false;
		}
		if (title.value == "") {
			alert("标题名称不能是空的");
			return false;
		}
		if (author.value == "") {
			alert("作者不能是空的");
			return false;
		}if (context.value == "") {
			alert("新闻内容不能是空的");
			return false;
		}

		return true;
	}
</script>

<script type="text/javascript">
    KE.show({
        id : 'context',
        cssPath : '../index.css'
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
			<li><a href="#">信息阅读</a></li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>基本信息</span>
		</div>

		<ul class="forminfo">
			<form id="frmlogin" name="frmlogin" >
				<li>信息名称:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${news.title }</li>
				<li>信息类别:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${news.topic.topicname }</li>
				<li>作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${news.author }</li>
				<li>新闻内容:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${news.content }</li>
			</form>
		</ul>


	</div>


</body>

</html>

