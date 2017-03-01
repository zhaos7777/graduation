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
			<li><a href="#">新闻列表</a></li>
			<li><a href="#">新闻修改</a></li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>基本信息</span>
		</div>

		<ul class="forminfo">
			<form id="frmlogin" name="frmlogin" method="post"
				action="donews?op=doedit"">
				<input type="hidden" name="newsid" value="${news.newsid }"/>
				<input type="hidden" name="tid" value="${news.topic.topicid }"/>
				<li><label>所属主题</label>
				<div class="vocation">
				<select name="selTopic"id="selTopic" class="select3">
						<option value="0">请选择</option>
						<c:forEach items="${requestScope.topics}" var="t">
							<option value="${t.topicid }" <c:if test="${news.topic.topicid==t.topicid}">selected="selected"</c:if>>${t.topicname}</option>
						</c:forEach>
				</select></div></li>
				<li><label>标题名称</label><input id="title" name="title"
					type="text" class="dfinput" value="${news.title }"/><i>请输入新闻标题</i></li>
				<li><label>作者</label><input id="author" name="author"
					type="text" class="dfinput" value="${news.author}" /><i>请输入作者</i></li>
				<li><label>新闻内容</label>
				<textarea id="context" name="context" rows="20" cols="10">${news.content }</textarea></li>

				<li><label>&nbsp;</label><input name="button" type="submit"
					class="btn" value="确认修改" onclick="return isnull();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					name="reset" type="reset" class="btn" value="重置"/></li>
			</form>
		</ul>


	</div>


</body>

</html>

