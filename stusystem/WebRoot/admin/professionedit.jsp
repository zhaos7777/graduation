<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript">
  function isnull(){
   var username=document.getElementById("proname");
   var password=document.getElementById("proabb");

   if(username.value == ""){
     alert("专业全称不能是空的");
     return false;   
   }   
    if(password.value == ""){
     alert("专业简称不能是空的");
     return false;   
   }  
  
   return true;
 }
</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">专业列表</a></li>
    <li><a href="#">专业修改</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    
    <ul class="forminfo">
    <form id="frmlogin" name="frmlogin" method="post" action="doprofession?op=doedit" ">
    <input type="hidden" name="id" value="${pro.pro_id }" />
    <li><label>专业全称</label><input id="proname" name="proname" type="text" value="${pro.pro_name }" class="dfinput" /><i>请输入专业的全称</i></li>
    <li><label>专业简称</label><input id="proabb" name="proabb" type="text" value="${pro.pro_abb }" class="dfinput" /><i>不超过四个字</i></li>
    <li><label>&nbsp;</label><input name="button" type="submit" class="btn" value="确认修改" onclick="return isnull();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="reset" type="reset" class="btn" value="重置"/></li>
    </form>
    </ul>
    
    
    </div>


</body>

</html>

