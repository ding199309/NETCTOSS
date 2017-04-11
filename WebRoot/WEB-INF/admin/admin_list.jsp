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
            //重置密码
            function resetPwd() {
            	//取得选中的checkbox
               var checkobjs=$(":checkbox[name='check_admin']:checked")
               if(checkobjs.length==0){
            	   alert("至少选择一项");
            	   return;            	   
               }
               var r=window.confirm("确定要删除吗？");
               if(!r){
            	   return;
               }
                   //遍历checkbox
              	var ids=new Array();
                for(var i=0;i<checkobjs.length;i++){
                	//获取每一个checkbox
                	var checkobj=checkobjs.eq(i);//jquery的方法，直接封装成json
                	//获取checkobj的爷爷，即它所在的行
                	var trObj=checkobj.parent().parent();
                	//取得该行下，第二个孩子td
                	var tdObj=trObj.children().eq(1);
                	//获取该td下的内容即 id值
                	ids.push(tdObj.text());
                	
                }
                //修改密码
                $.post(
                	"resetPassword.do",
                	{"ids":ids.toString()},
                	function(d){
                		alert(d.message);
                	}
                
                );
                
		
            }

            //删除
            function deleteAdmin(id) {
                var r = window.confirm("确定要删除此管理员吗？");
                //document.getElementById("operate_result_info").style.display = "block";
                if(r){
                	location.href="deleteAdmin.do?id="+id;
                }
            }
            //全选
            function selectAdmins(inputObj) {
                var inputArray = document.getElementById("datalist").getElementsByTagName("input");
                for (var i = 1; i < inputArray.length; i++) {
                    if (inputArray[i].type == "checkbox") {
                        inputArray[i].checked = inputObj.checked;
                    }
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
            <form action="findAdmin.do" method="post">
                <!--查询-->
              <input type="hidden" name="currentPage" value="1"/>
                <div class="search_add">
                    <div>
                        模块：
                        <select id="selModules" class="select_search" name="moduleId" >
                            <option value=""></option>
                            <c:forEach items="${modules}" var="s">
                            <option value="${s.module_id}" <c:if test="${s.module_id==adminPage.moduleId}">selected</c:if>>${s.name}</option>
                            </c:forEach>                           
                          
                        </select>
                    </div>
                    <div>角色：<input type="text"  name="roleName" value="${adminPage.roleName}" class="text_search width200" /></div>
                    <div><input type="submit" value="搜索" class="btn_search"/></div>
                    <input type="button" value="密码重置" class="btn_add" onclick="resetPwd();" />
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toAddAdmin.do';" />
                </div>
                <!--删除和密码重置的操作提示-->
                <div id="operate_result_info" class="operate_fail">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span>删除失败！数据并发错误。</span><!--密码重置失败！数据并发错误。-->
                </div> 
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th class="th_select_all">
                                <input type="checkbox" onclick="selectAdmins(this);" />
                                <span>全选</span>
                            </th>
                            <th>管理员ID</th>
                            <th>姓名</th>
                            <th>登录名</th>
                            <th>电话</th>
                            <th>电子邮件</th>
                            <th>授权日期</th>
                            <th class="width100">拥有角色</th>
                            <th></th>
                        </tr>  
                        <c:forEach items="${admins}" var="c">                  
                        <tr>
                            <td><input type="checkbox"  name="check_admin"/></td>
                            <td>${c.admin_id}</td>
                            <td>${c.name}</td>
                            <td>${c.admin_code}</td>
                            <td>${c.telephone}</td>
                            <td>${c.email}</td>
                            <td><fmt:formatDate value="${c.enrolldate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                            <td>
                                <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">
                                <c:forEach items="${c.roles}" var="t" varStatus="m">
                                	<c:choose>  
                                	<c:when test="${m.first}">
                               		 ${t.name}
                                	</c:when>                                	
                                	<c:when test="${m.index==1}">
                               			...
                                	</c:when>                                   	                          
                                	</c:choose>    
                                </c:forEach>                                                            
                                </a>
                                <!--浮动的详细信息-->
                                <div class="detail_info">
                                   <c:forEach items="${c.roles}" var="t" varStatus="m">
                                           <c:choose >
                                           		<c:when test="${m.last}">
                                           		${t.name}
                                           		</c:when> 
                                           		 <c:otherwise>
                                           		 ${t.name},
                                          		 </c:otherwise>                                        
                                           </c:choose>                                                                                                         
                                   </c:forEach>
                                </div>
                            </td>
                            <td class="td_modi">
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateAdmin.do?id=${c.admin_id}';" />
                                <input type="button" value="删除" class="btn_delete" onclick="deleteAdmin(${c.admin_id});" />
                            </td>
                        </tr>
                     	</c:forEach>  
    
                    </table>
                </div>
                <!--分页-->
                <div id="pages">
                
                <c:if test="${adminPage.currentPage==1}">
        	        <a href="javascript:;">上一页</a>
        	    </c:if>
        	       <c:if test="${adminPage.currentPage!=1}">
        	        <a href="findAdmin.do?currentPage=${adminPage.currentPage-1}">上一页</a>
        	    </c:if>
        	    
        	    <c:forEach begin="1" end="${adminPage.totalPage}" var="s">
        	    <c:if test="${s==adminPage.currentPage}">
                    <a href="findAdmin.do?currentPage=${s}" class="current_page">${s}</a> 
                </c:if> 
                 <c:if test="${s!=adminPage.currentPage}">
                    <a href="findAdmin.do?currentPage=${s}" class="current_page">${s}</a> 
                </c:if> 
                </c:forEach> 
                 
                  <c:if test="${adminPage.currentPage==adminPage.totalPage}">
        	        <a href="javascript:;">下一页</a>
        	    </c:if>
        	       <c:if test="${adminPage.currentPage!=adminPage.totalPage}">
        	        <a href="findAdmin.do?currentPage=${adminPage.currentPage+1}">下一页</a>
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
