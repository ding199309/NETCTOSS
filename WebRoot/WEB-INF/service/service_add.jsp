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
        <script type="text/javascript"  src="../js/jquery-1.4.3.js"></script>
        <script language="javascript" type="text/javascript">
            //保存成功的提示信息
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

            //自动查询账务账号
            function searchAccounts(txtObj) {
                //document.getElementById("a1").innerHTML = txtObj.value;
            }
            function seach(){
            	
            	//重置
            	$("#account_id").val("");
            	$("#login_name").val("");
            	var idcard_no =$("#idcard_no").val();
            //	alert(idcard_no);
            	var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
            	if(idcard_no==""){
            		$("#idcard_no_msg").text("身份证不能为空").addClass("error_msg");
            		return;            		
            	}if(!reg.test(idcard_no)){
            		$("#idcard_no_msg").text("身份证格式不正确").addClass("error_msg");
            		return;
            		}
            //查询账务账号
				$.post(
					"searchAccount.do",
					{"idcardNo":idcard_no},
					function(data) {
						//{"account_id":1011,"login_name":"zs"...}
						if(data=="") {//此处返回字符串，不会为null
							//没有查询到数据，给予错误提示
							$("#idcard_no_msg").text("没有此账务账号.").addClass("error_msg");
						} else {
							//查询到了数据，设置默认值
							//将account_id存入hidden
							$("#account_id").val(data.account_id);
							//将login_name存入账务账号框
							$("#login_name").val(data.login_name);
							//给予正确提示，并移除错误样式
							$("#idcard_no_msg").text("有效的身份证号.").removeClass("error_msg");
						}
					}
				);
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
          
            <!--保存操作的提示信息-->
            <div id="save_result_info" class="save_fail">保存失败！192.168.0.23服务器上已经开通过 OS 账号 “mary”。</div>
            <form action="addService.do" method="post" class="main_form">
                <!--内容项-->
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                	<!-- 身份证是用来查找account_id的，
                		不需要保存，不用写name属性 -->
                    <input type="text" id="idcard_no" class="width180" />
                    <!-- 保存时需要提交account_id -->
                    <input type="hidden" name="account_id" id="account_id"/>
                    <input type="button" value="查询账务账号" class="btn_search_large" onclick="seach();"/>
                    <span class="required">*</span>
                    <div class="validate_msg_short" id="idcard_no_msg">没有此身份证号，请重新录入。</div>
                </div>
                <div class="text_info clearfix"><span>账务账号：</span></div>
                <div class="input_info">
                	<!-- 账务账号框只是用来何时身份证查询的结果
                		是否正确的，不需要提交，不用写name属性 -->
                    <input type="text" id="login_name" readonly="readonly" class="readonly" onkeyup="searchAccounts(this);" />
                    <span class="required">*</span>
                    <div class="validate_msg_long">没有此账务账号，请重新录入。</div>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info">
                    <select name="cost_id">
                    	<c:forEach items="${costs}" var="c">
                        	<option value="${c.cost_id}">${c.name}</option>
                        </c:forEach>
                    </select>                        
                </div> 
                <div class="text_info clearfix"><span>服务器 IP：</span></div>
                <div class="input_info">
                    <input type="text" value=""  name="unix_host" />
                    <span class="required">*</span>
                    <div class="validate_msg_long">15 长度，符合IP的地址规范</div>
                </div>                   
                <div class="text_info clearfix"><span>登录 OS 账号：</span></div>
                <div class="input_info">
                    <input type="text" value="" name="os_username" />
                    <span class="required">*</span>
                    <div class="validate_msg_long">8长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>密码：</span></div>
                <div class="input_info">
                    <input type="password"  name="login_passwd" />
                    <span class="required">*</span>
                    <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复密码：</span></div>
                <div class="input_info">
                    <input type="password"  />
                    <span class="required">*</span>
                    <div class="validate_msg_long">两次密码必须相同</div>
                </div>     
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="submit" value="保存" class="btn_save" />
                    <input type="button" value="取消" class="btn_save"  onclick="history.back()"/>
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
