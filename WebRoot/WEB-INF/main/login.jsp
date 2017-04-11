<%@page  pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script type="text/javascript" src="../js/jquery-1.4.3.js"></script> 
        <script type="text/javascript">
        	function check(){
        		var reg=/^\w{1,20}$/;
        		var loginword=$("#loginword").val();
        		if(!reg.test(loginword)){
        			$("#login_msg").text("请输入1-30位的字母、数字、下划线");
        			return;        			      			
        		}
        		var password=$("#password").val();
        		if(!reg.test(password)){
        			$("#password_msg").text("请输入1-30位的字母、数字、下划线"); 
        			return;
        		}
        		var userCode=$("#userCode").val();
        		if(userCode==""){
        			$("#user_code_msg").text("请输入验证码.");
        			return;
        			
        		}
        		
        		
        		$.post("checkLogin.do",
        				{"name":loginword,
        					"pwd":password,
        					"userCode":userCode},
        				function(data){
        					
        					if(data==3){
        						$("#login_msg").text("账号不存在");        						
        					}
        					if(data==1){
        						location.href="toIndex.do";
        					}if(data==2){
        						//密码错误
        						$("#password_msg").text("密码错误");    
        					}else if(data==4){
        						//验证码错误
        						$("#user_code_msg").text("验证码错误");
        					}
        					
        				}
        			
        			);       		
        	}
        	function clear_msg(id){
        		
        		$("#"+id).text("");
        	}
   
        </script>
    </head>
    <body class="index">
        <div class="login_box">
            <table>
                <tr>
                    <td class="login_info">账号：</td>
                    <td colspan="2"><input name=""  id="loginword" type="text"  onfocus="clear_msg('login_msg');" class="width150" /></td>
                    <td class="login_error_info"><span class="required" id="login_msg"></span></td>
                </tr>
                <tr>
                    <td class="login_info">密码：</td>
                    <td colspan="2"><input name="" id="password" type="password" onfocus="clear_msg('password_msg');" class="width150" /></td>
                    <td><span class="required" id="password_msg"></span></td>
                </tr>
                <tr>
                    <td class="login_info">验证码：</td>
                    <td class="width70"><input name="" id="userCode" type="text" class="width70" onfocus="clear_msg('user_code_msg')"/></td>
                    <td><img src="createImage.do" alt="验证码" title="点击更换" 
                    		onclick="this.src='createImage.do?date='+new Date().getTime()"/></td>  
                    <td><span class="required" id="user_code_msg"></span></td>              
                </tr>            
                <tr>
                    <td></td>
                    <td class="login_button" colspan="2">
                        <a href="javascript:check();"><img src="../images/login_btn.png" /></a>
                    </td>    
                    <td><span class="required"></span></td>                
                </tr>
            </table>
        </div>
    </body>
</html>
