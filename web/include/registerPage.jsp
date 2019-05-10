<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

	
<script>
$(function(){
	
	<c:if test="${!empty msg}">
	$("span.errorMessage").html("${msg}");
	$("div.registerErrorMessageDiv").css("visibility","visible");		
	</c:if>
	
	function $(elementId){
  return document.getElementById(elementId).value;
    }
function divId(elementId){
  return document.getElementById(elementId);
    }
	
	/*昵称验证*/    
function checkName(){
  var name=$("name");
  var nameId=divId("name_prompt");
   nameId.innerHTML="";        
    if(0==$("#name").val().length){
       nameId.innerHTML="昵称不能为空";
    return false;
      }
      nameId.innerHTML="昵称验证通过";
      return true;
    }
	
	
	/*验证手机号码*/
function checkMobile(){
    var mobile=$("mobile");
    var mobileId=divId("mobile_prompt");
    var regMobile=/^((13[0-9])|(14[579])|(15([0-3,5-9]))|(16[6])|(17[0135678])|(18[0-9]|19[89]))\\d{8}$/;
    /*test() 方法用于检测一个字符串是否匹配某个模式.
            返回一个 Boolean 值，它指出在被查找的字符串中是否匹配给出的正则表达式。*/
    if(0==$("#mobile").val().length){
        mobileId.innerHTML="手机号不能为空";  
        return false;
    }else if(regMobile.test(mobile)==false){
        mobileId.innerHTML="手机号格式不正确，请重新输入";
        return false;
    }else{
        mobileId.innerHTML="手机号验证通过";
        return true;
    }
        
    }
	
	/*密码验证*/    
function checkPwd(){
  var pwd=$("password");
  var pwdId=divId("pwd_prompt");
   pwdId.innerHTML="";    
  var reg=/^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)(?![a-zA-z\d]+$)(?![a-zA-z!@#$%^&*]+$)(?![\d!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*].{6,16}$/;    
    if(0==$("#password").val().length){
       pwdId.innerHTML="密码不能为空";
    }else if(reg.test(pwd)==false){
       pwdId.innerHTML="密码不符合规范，请重新输入!";
    return false;
    }else{
       pwdId.innerHTML="密码通过验证";
       return true;
    }
    }
	
	/*重新输入密码验证*/
	function checkRepwd(){
  var repwd=$("repeatpassword");
  var pwd=$("password");
  var repwdId=divId("repwd_prompt");
   repwdId.innerHTML="";
    if(0==$("#repeatpassword").val().length){
       repwdId.innerHTML="重复输入密码不能为空";
    }else if(pwd!=repwd){
       repwdId.innerHTML="与第一次输入的密码不一致";
    return false;
    }else{
       repwdId.innerHTML="重复输入密码验证通过";
       return true;
    }
      
    }
	
	
	$(".registerForm").submit(function(){
	
	    if(checkName()&checkMobile()&checkPwd()&checkRepwd()){
	        return true;
	    }
	    /*
		if(0==$("#name").val().length){
			$("span.errorMessage").html("请输入昵称");
			$("div.registerErrorMessageDiv").css("visibility","visible");			
			return false;
		}
		if(0==$("#mobile").val().length){
			$("span.errorMessage").html("请输入手机号");
			$("div.registerErrorMessageDiv").css("visibility","visible");			
			return false;
		}		
		if(0==$("#password").val().length){
			$("span.errorMessage").html("请输入密码");
			$("div.registerErrorMessageDiv").css("visibility","visible");			
			return false;
		}		
		if(0==$("#repeatpassword").val().length){
			$("span.errorMessage").html("请输入重复密码");
			$("div.registerErrorMessageDiv").css("visibility","visible");			
			return false;
		}		
		if($("#password").val() !=$("#repeatpassword").val()){
			$("span.errorMessage").html("重复密码不一致");
			$("div.registerErrorMessageDiv").css("visibility","visible");			
			return false;
		}*/		

		return false;
	});
})
</script>



<form method="post" action="foreregister" class="registerForm">


<div class="registerDiv">
	<div class="registerErrorMessageDiv">
		<div class="alert alert-danger" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
		  	<span class="errorMessage"></span>
		</div>		
	</div>

	
	<table class="registerTable" align="center">
		<tr>
			<td  class="registerTip registerTableLeftTD">设置用户名</td>
			<td></td>
		</tr>
		<tr>
			<td class="registerTableLeftTD">昵称</td>
			<td  class="registerTableRightTD"><input id="name" name="name" placeholder="用户名不允许重复" onblur="checkName()" /> </td>
			<td><div id="name_prompt"></div></td> 
		</tr>
		<tr>
			<td class="registerTableLeftTD">手机号</td>
			<td  class="registerTableRightTD"><input id="mobile" name="mobile" placeholder="手机号一旦设置成功，无法修改" onblur="checkMobile()" /> </td>
		    <td><div id="mobile_prompt">11位手机号</div></td> 
		</tr>
		<tr>
			<td  class="registerTip registerTableLeftTD">设置登录密码</td>
			<td  class="registerTableRightTD">登录时验证，保护账号信息</td>
		</tr>		
		<tr>
			<td class="registerTableLeftTD">登录密码</td>
			<td class="registerTableRightTD"><input id="password" name="password" type="password"  placeholder="设置你的登录密码"  onblur="checkPwd()" /> </td>
		    <td><div id="pwd_prompt">密码长度 6-16 位，必须包括字母、数字、特殊符号</div></td>
		</tr>
		<tr>
			<td class="registerTableLeftTD">密码确认</td>
			<td class="registerTableRightTD"><input id="repeatpassword" type="password"   placeholder="请再次输入你的密码"  onblur="checkRepwd()" /> </td>
		    <td><div id="repwd_prompt"></div></td>
		</tr>
				
		<tr>
			<td colspan="2" class="registerButtonTD">
				<a href="registerSuccess.jsp"><button type="submit">提   交</button></a>
			</td>
		</tr>				
	</table>
</div>
</form>