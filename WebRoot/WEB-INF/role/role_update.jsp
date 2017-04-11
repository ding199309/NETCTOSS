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
        <script language="javascript" type="text/javascript">
            //保存成功的提示消息
            function showResult() {
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }
            
            function check_id(){
            	var r=$(":checkbox[name='modulesId']:checked");
            	//alert(r);
            	
            	if(r.length==0){
            		$("#A").text("至少选择一个权限").addClass("error_msg");
            		return false;
            		
            	}else{
            		$("#A").text("").removeClass("error_msg");
            		return true;
            	}           	
            }     
            var nameResult;
            function check_name(){
            	nameResult=null;
            		var r=$("#name").val();
            		//alert(r);  		
            		var reg=/^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$/;
            		if(!reg.test(r)){
            			$("#name_msg").text("不能为空，且为20长度的字母、数字和汉字的组合")
            						  .addClass("error_msg");
            			nameResult=false;
            			return;
            			
            		}
            		$.post("checkRoleName.do",
            				{"name":r},
            				function(data){
            					if(data){
            						$("#name_msg").text("角色名有效").removeClass("error_msg");
            						nameResult= true;
            						
            					}else{
            						alert(111);
            						$("#name_msg").text("已存在").addClass("error_msg");
            						nameResult=false;
            						
            					}           					
            				});            	
            }  
            function save(){
            	
            	if(!check_id()){
            		return;
            	}
            	//再调用异步请求的校验方法
            	check_name();
            	
            	var timer=setInterval(function(){
            		
            		if(nameResult!=null){
            			clearInterval(timer);
            		}
            		if(nameResult){
            			document.forms[0].submit();
            		}
            		
            	},1000);
            	
            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
          <jsp:include page="/WEB-INF/main/menu.jsp"/>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">           
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存成功！</div>
            <form action="updateRole.do" method="post" class="main_form">
            	<input type="hidden" name="role_id" value="${roles.role_id}"/>
                <div class="text_info clearfix"><span>角色名称：</span></div>
                <div class="input_info">
                    <input type="text" class="width200"  id="name" name="name" value="${roles.name}"  onblur="check_name();"/>
                    <span class="required">*</span>
                    <div class="validate_msg_medium" id="name_msg">不能为空，且为20长度的字母、数字和汉字的组合</div>
                </div>                    
                <div class="text_info clearfix"><span>设置权限：</span></div>
                <div class="input_info_high">
                    <div class="input_info_scroll">
                        <ul>
                        	<c:forEach items="${modules}" var="r">
                            <li><input type="checkbox" name="modulesId"  value="${r.module_id}"                          
	                           		<c:forEach items="${roles.modules}" var="t">
	                           			<c:if test="${t.module_id==r.module_id}">checked</c:if>
                            		</c:forEach>
                               onclick="check_id();"/>${r.name}
                             </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <span class="required">*</span>
                    <div class="validate_msg_tiny"  id="A"></div>
                </div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save"  onclick="save();" />
                    <input type="button" value="取消" class="btn_save"  onclick="history.back();"/>
                </div>
            </form> 
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)加拿大达内IT培训集团公司 </span>
        </div>
    </body>
</html>
