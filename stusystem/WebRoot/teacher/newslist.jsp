<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" language="javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});

  function isdel(){
    if(!confirm("确认要删除吗？")){ 
      window.event.returnValue = false; 
    }
  }
</script>


</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">新闻列表</a></li>
		</ul>
	</div>

	<div class="rightinfo">


		<table class="tablelist">
			<thead>
				<tr>

					<th>新闻标题</th>
					<th>新闻类别</th>
					<th>新闻作者</th>
					<th>发布时间</th>

				</tr>
			</thead>

			<tbody>
				<c:forEach items="${requestScope.list}" var="n">
					<tr>
						<td><a href="donews?op=content&id=${n.newsid}">${n.title}</a></td>
						<td>${n.topic.topicname}</td>
						<td>${n.author}</td>
						<td>${n.addtime}</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>


		<div class="pagin">
			<div class="message">
				共<i class="blue">${requestScope.allnum}</i>条记录，当前显示第&nbsp;<i
					class="blue">${requestScope.pageindex}&nbsp;</i>页
			</div>
			<ul class="paginList">
				<c:if test="${requestScope.pageindex>1 }">
					<li class="paginItem"><a
						href="donews?op=list&page=${requestScope.pageindex-1}"><span
							class="pagepre"></span></a></li>
					<li class="paginItem">
					<a href="donews?op=list&page=1">首页</a></li>
				</c:if>
				<c:if test="${requestScope.pageindex<requestScope.pagecount}">
					<li class="paginItem"><a
						href="donews?op=list&page=${requestScope.pagecount}">末页</a></li>
					<li class="paginItem"><a
						href="donews?op=list&page=${requestScope.pageindex+1}"><span
							class="pagenxt"></span></a></li>
				</c:if>
			</ul>
		</div>


		<div class="tip">
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>

			<div class="tipinfo">
				<span><img src="../images/ticon.png" /></span>
				<div class="tipright">
					<p>是否确认对信息的修改 ？</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>

			<div class="tipbtn">
				<input name="" type="button" class="sure" value="确定" />&nbsp; <input
					name="" type="button" class="cancel" value="取消" />
			</div>

		</div>




	</div>

	<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
