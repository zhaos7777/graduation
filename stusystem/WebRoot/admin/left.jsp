<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>管理菜单</div>
    
    <dl class="leftmenu">
        
    <dd>
    <div class="title">
    <span><img src="../images/leftico02.png" /></span>信息首页
    </div>
    	<ul class="menuson">
        <li class="active"><cite></cite><a href="index.jsp" target="rightFrame">首页信息</a><i></i></li>
        <li><cite></cite><a href="donews?op=list" target="rightFrame">信息列表</a><i></i></li>
        <li><cite></cite><a href="donews?op=toadd" target="rightFrame">信息发布</a><i></i></li>
        <li><cite></cite><a href="dotopic?op=list" target="rightFrame">主题列表</a><i></i></li>
        <li><cite></cite><a href="topicadd.jsp" target="rightFrame">主题添加</a><i></i></li>
        </ul>    
    </dd>
        
    
    <dd>
    <div class="title">
    <span><img src="../images/leftico01.png" /></span>专业管理
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="doprofession?op=list" target="rightFrame">专业列表</a><i></i></li>
        <li><cite></cite><a href="professionadd.jsp"  target="rightFrame">专业添加</a><i></i></li>
        </ul>     
    </dd> 
    
    <dd>
    <div class="title">
    <span><img src="../images/leftico01.png" /></span>班级管理
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="doclass?op=list" target="rightFrame">班级列表</a><i></i></li>
        <li><cite></cite><a href="doclass?op=toadd1" target="rightFrame">班级添加</a><i></i></li>
        </ul>     
    </dd> 
    
    
    <dd><div class="title">
    <span><img src="../images/leftico03.png" /></span>学生管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="dostudent?op=list" target="rightFrame">学生列表</a><i></i></li>
        <li><cite></cite><a href="dostudent?op=toadd1" target="rightFrame">学生添加</a><i></i></li>
    </ul>    
    </dd>  
    
    <dd><div class="title"><span><img src="../images/leftico04.png" /></span>教师管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="doteacher?op=list" target="rightFrame">教师列表</a><i></i></li>
        <li><cite></cite><a href="doteacher?op=toadd" target="rightFrame">教师添加</a><i></i></li>
    </ul>
    </dd>   
    
    <dd><div class="title">
    <span><img src="../images/leftico03.png" /></span>我的信息</div>
    <ul class="menuson">
        <li><cite></cite><a href="#">个人信息</a><i></i></li>
        <li><cite></cite><a href="#">密码修改</a><i></i></li>

    </ul>    
    </dd>  
    
    </dl>
    
</body>
</html>
