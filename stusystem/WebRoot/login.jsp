<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>

<script language="javascript" type="text/javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    });
});  

 function isnull(){
   var username=document.getElementById("txtUsername");
   var password=document.getElementById("txtPassword");
   if(username.value == ""){
     alert("用户名不能为空");
     return false;   
   }   
    if(password.value == ""){
     alert("密码不能为空");
     return false;   
   }  
   return true;
 }
</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
    <ul>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    
    <ul>
    <form id="frmlogin" name="frmlogin" method="post" action="dologin" >
    <li><input id="txtUsername" name="txtUsername" type="text" value="" class="loginuser" onclick="JavaScript:this.value=''"/></li>
    <li><input id="txtPassword" name="txtPassword" type="password" value="" class="loginpwd" onclick="JavaScript:this.value=''"/></li>
    <li><input id="btulogin" name="btnlogin" type="submit" class="loginbtn" value="登录"  onclick="return isnull();" />
    <label><input name="type" type="radio" value="管理员" checked="checked" />管理员</label>
    <label><input name="type" type="radio" value="教师" />教师</label>
    <label><input name="type" type="radio" value="学生" />学生</label></li>
   
    </form>
    </ul>
    
    </div>
    
    </div>
    
    
    
    <div class="loginbm">作者:赵思琦 &nbsp;&nbsp; <a href="www.327774886@qq.com">合作邮箱:327774886@qq.com&nbsp;&nbsp;</a> 仅供学习交流</div>
	
    
</body>

</html>
