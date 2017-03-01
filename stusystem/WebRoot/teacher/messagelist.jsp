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
			<li><a href="#">信息列表</a></li>
		</ul>
	</div>

	<div class="rightinfo">


		<table class="tablelist">
			<thead>
				<tr>
                    <th>信息编号</th>
					<th>信息标题</th>
					<th>作者</th>
					<th>发布时间</th>
                    <th>操作</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${requestScope.list}" var="m">
					<tr>
					    <td>${m.id}</td>
						<td>${m.title}</td>
						<td>${loginuser.name}老师</td>
						<td>${m.addtime}</td>
						<td><a href="domessage?op=toedit&id=${m.id}" class="tablelink">修改</a>&nbsp;&nbsp; &nbsp;&nbsp;
                            <a href="domessage?op=dodel&id=${m.id}" class="tablelink" onclick="return isdel();">删除</a></td>
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
						href="domessage?op=list&page=${requestScope.pageindex-1}"><span
							class="pagepre"></span></a></li>
					<li class="paginItem">
					<a href="domessage?op=list&page=1">首页</a></li>
				</c:if>
				<c:if test="${requestScope.pageindex<requestScope.pagecount}">
					<li class="paginItem"><a
						href="domessage?op=list&page=${requestScope.pagecount}">末页</a></li>
					<li class="paginItem"><a
						href="domessage?op=list&page=${requestScope.pageindex+1}"><span
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
