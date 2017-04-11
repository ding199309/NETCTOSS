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
            //显示角色详细信息
            function showDetail(flag, a) {
                var detailDiv = a.parentNode.getElementsByTagName("div")[0];
                if (flag) {
                    detailDiv.style.display = "block";
                }
                else
                    detailDiv.style.display = "none";
            }
            //删除
            function deleteAccount() {
                var r = window.confirm("确定要删除此业务账号吗？删除后将不能恢复。");
                document.getElementById("operate_result_info").style.display = "block";
            }
            //开通或暂停
            function setState() {
                var r = window.confirm("确定要开通此业务账号吗？");
                document.getElementById("operate_result_info").style.display = "block";
            }
            
		
  function  start_service(id){
            	
            	var r=window.confirm("确定要开通吗？");
            	//alert(11);
            	if(r){
            				//发起异步请求，实现开通
            		//post函数的3个参数，1是url，2是本次
            		//请求携带的参数，3是执行成功后的回调函数
            		$.post(
            			"startService.do",
            			{"id":id},
            			function (data){
            				//回调函数的参数，接收Controller方法返回的数据
            				//{"success":true,"message":"开通成功."}
            				//给予提示
            				$("#operate_msg").text(data.message);
            				$("#operate_result_info").show();
            			
            				setTimeout(function(){
            					//关闭提示框
            					if(data.success) {
            						//执行成功，刷新页面即可
            						location.href = "findService.do";
            					} else {
            						//执行失败，不用刷新页面，但是要关闭提示框
            						$("#operate_result_info").hide();
            					}
            				},1000);            				            				            					            				            				
            			}            			           			
            		);            		            		
            	}            	
            }     
  
        function pause_service(id){
        	var r=window.confirm("确定要暂停吗?");
        	if(r){
        		$.post("pauseService.do",
        				{"id":id},
        				function(x){
        					$("#operate_msg").text(x.message);
        					$("#operate_result_info").show();
        					
        					setTimeout(function(){
        						if(x.success){
        							location.href="findService.do";
        						}else{
        							$("#operate_result_info").hide();
        						}
        						
        					},1000);
        					
        				}
        		);        		        		
        	}       	
        } 
         function delete_service(id){
        	var r=window.confirm("确定要删除吗?");
        	if(r){
        		$.post("deleteService.do",
        				{"id":id},
        				function(x){
        					$("#operate_msg").text(x.message);
        					$("#operate_result_info").show();
        					
        					setTimeout(function(){
        						if(x.success){
        							location.href="findService.do";
        						}else{
        							$("#operate_result_info").hide();
        						}
        						
        					},1000);
        					
        				}
        		);        		        		
        	}       	
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
            <form action="findService.do" method="post">
                <!--查询-->
                <div class="search_add">   
                	<input type="hidden" name="currentPage" value="1"/>                       
                    <div>OS 账号：<input type="text"  name="osUserName" value="${servicePage.osUserName}" class="width100 text_search" /></div>                            
                    <div>服务器 IP：<input type="text" name="unixHost" value="${servicePage.unixHost}" class="width100 text_search" /></div>
                    <div>身份证：<input type="text"   name="idcardNo" value="${servicePage.idcardNo}" class="text_search" /></div>
                    <div>状态：
                        <select class="select_search" name="status">
                            <option></option>
                            <option  value="0" <c:if test="${servicePage.status=='0'}">selected</c:if>>开通</option>
                            <option  value="1" <c:if test="${servicePage.status=='1'}">selected</c:if>>暂停</option>
                            <option  value="2" <c:if test="${servicePage.status=='2'}">selected</c:if>>删除</option>
                        </select>
                    </div>
                    <div><input type="submit" value="搜索" class="btn_search"  /></div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toAddService.do';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    	<span id="operate_msg">删除成功</span>
                </div>   
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                    <tr>
                        <th class="width50">业务ID</th>
                        <th class="width70">账务账号ID</th>
                        <th class="width150">身份证</th>
                        <th class="width70">姓名</th>
                        <th>OS 账号</th>
                        <th class="width50">状态</th>
                        <th class="width100">服务器 IP</th>
                        <th class="width100">资费</th>                                                        
                        <th class="width200"></th>
                    </tr>
                    <c:forEach items="${services}" var="s">
                    <tr>
                        <td><a href="service_detail.html" title="查看明细">${s.SERVICE_ID}</a></td>
                        <td>${s.ACCOUNT_ID}</td>
                        <td>${s.IDCARD_NO}</td>
                        <td>${s.REAL_NAME}</td>
                        <td>${s.OS_USERNAME}</td>
                        <td>
                        	<c:if test="${s.STATUS==0}">
                        		开通
                        	</c:if>
                        	<c:if test="${s.STATUS==1}">
                        		暂停
                        	</c:if>
                        	<c:if test="${s.STATUS==2}">
                        		删除
                        	</c:if>
                        </td>
                        <td>${s.UNIX_HOST}</td>
                        <td>
                            <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">${s.NAME}</a>
                            <!--浮动的详细信息-->
                            <div class="detail_info">
                                ${s.DESCR}
                            </div>
                        </td>                            
                        <td class="td_modi">
                        	<c:if test="${s.STATUS==0}">
	                            <input type="button" value="暂停" class="btn_pause" onclick="pause_service(${s.SERVICE_ID});" />
	                            <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateService.do?id=${s.SERVICE_ID}';" />
	                            <input type="button" value="删除" class="btn_delete" onclick="delete_service(${s.SERVICE_ID});" />
                        	</c:if>
                        	<c:if test="${s.STATUS==1}">
                        		<input type="button" value="开通" class="btn_start" onclick="start_service(${s.SERVICE_ID});" />
	                            <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateService.do?id=${s.SERVICE_ID}';" />
	                            <input type="button" value="删除" class="btn_delete" onclick="delete_service(${s.SERVICE_ID});" />
                        	</c:if>
                        </td>
                    </tr>
                    </c:forEach>                                                           
                </table>                
                <p>业务说明：<br />
                1、创建即开通，记载创建时间；<br />
                2、暂停后，记载暂停时间；<br />
                3、重新开通后，删除暂停时间；<br />
                4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br />
                5、业务账号不设计修改密码功能，由用户自服务功能实现；<br />
                6、暂停和删除状态的账务账号下属的业务账号不能被开通。</p>
                </div>                    
                <!--分页-->
                <div id="pages">
                                                
                	<c:if test="${servicePage.currentPage==1}">
                    <a href="javascript:;">&lt;&lt;</a>
                    </c:if>
                    <c:if test="${servicePage.currentPage!=1}">
                    <a href="findService.do?currentPage=1">&lt;&lt;</a>
                    </c:if>
                                       
                    <c:if test="${servicePage.currentPage==1}">
        	        <a href="javascript:;">&lt;</a>
        	        </c:if>
        	         <c:if test="${servicePage.currentPage!=1}">
        	        <a href="findService.do?currentPage=${servicePage.currentPage-1}">&lt;</a>
        	        </c:if>
        	               	                	        
        	        <c:forEach begin="1" end="${servicePage.totalPage}" var="i">       	        
        	        <c:if test="${i==servicePage.currentPage}">
                    <a href="findService.do?currentPage=${i}" class="current_page">${i}</a>
                    </c:if>
                    <c:if test="${i!=servicePage.currentPage}">
                    <a href="findService.do?currentPage=${i}">${i}</a>
                    </c:if>
                    </c:forEach>
 
                    <c:if test="${servicePage.currentPage==servicePage.totalPage}">                                                                           
                    <a href="javascript:;"> &gt;</a>
                    </c:if>  
                    <c:if test="${servicePage.currentPage!=servicePage.totalPage}">                                                                           
                    <a href="findService.do?currentPage=${servicePage.currentPage+1}">&gt;</a>
                    </c:if>  
                    
                    <c:if test="${servicePage.currentPage==servicePage.totalPage}">                                                                           
                    <a href="javascript:;">&gt;&gt;</a>
                    </c:if> 
                     <c:if test="${servicePage.currentPage!=servicePage.totalPage}">                                                                           
                    <a href="findService.do?currentPage=${servicePage.totalPage}">&gt;&gt;</a>
                    </c:if>  
                </div>                    
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
