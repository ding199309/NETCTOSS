<%@page  pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <ul id="menu">
  
  		<c:if test="${currentModuleId==0}">
  		<li><a href="${pageContext.request.contextPath}/login/toIndex.do" class="index_on"></a></li>
  		</c:if>
  		<c:if test="${currentModuleId!=0}">
  		<li><a href="${pageContext.request.contextPath}/login/toIndex.do" class="index_off"></a></li>
  		</c:if>
  		
  		<c:forEach items="${adminModules}" var="i">
  		
              <c:if test="${i.module_id==1}">              
  		      	 <c:if test="${currentModuleId==1}">       
               			 <li><a href="${pageContext.request.contextPath}/role/findRole.do?currentPage=1" class="role_on"></a></li>
              	 </c:if>                
              	 <c:if test="${currentModuleId!=1}">                              
                 		 <li><a href="${pageContext.request.contextPath}/role/findRole.do?currentPage=1" class="role_off"></a></li>
              	 </c:if> 
              </c:if> 
              
              
              <c:if test="${i.module_id==2}">
              	<c:if test="${currentModuleId==2}">
                   <li><a href="${pageContext.request.contextPath}/admin/findAdmin.do?currentPage=1" class="admin_on"></a></li>
                </c:if>             
              	<c:if test="${currentModuleId!=2}">
                   <li><a href="${pageContext.request.contextPath}/admin/findAdmin.do?currentPage=1" class="admin_off"></a></li>
                </c:if>
              </c:if>
              
              <c:if test="${i.module_id==3}">
                <c:if test="${currentModuleId==3}">
              		 <li><a href="${pageContext.request.contextPath}/cost/findCost.do?currentPage=1" class="fee_on"></a></li>
                </c:if>        
                <c:if test="${currentModuleId!=3}">          
               		<li><a href="${pageContext.request.contextPath}/cost/findCost.do?currentPage=1" class="fee_off"></a></li>
                </c:if>
              </c:if>
              
               <c:if test="${i.module_id==4}">
              		<c:if test="${currentModuleId==4}">              
               			 <li><a href="${pageContext.request.contextPath}/account/findAccount.do?currentPage=1" class="account_on"></a></li>
                	</c:if>
              		<c:if test="${currentModuleId!=4}">              
                		<li><a href="${pageContext.request.contextPath}/account/findAccount.do?currentPage=1" class="account_off"></a></li>
                	</c:if>
                </c:if>
                
                <c:if test="${i.module_id==5}">
               		 <c:if test="${currentModuleId==5}">
                		<li><a href="${pageContext.request.contextPath}/service/findService.do?currentPage=1" class="service_on"></a></li>
                	 </c:if>
                	 <c:if test="${currentModuleId!=5}">
                		<li><a href="${pageContext.request.contextPath}/service/findService.do?currentPage=1" class="service_off"></a></li>
                     </c:if>
                </c:if>
                
                <c:if test="${i.module_id==6}">
                <li><a href="bill/bill_list.html" class="bill_off"></a></li>
                </c:if>
                <c:if test="${i.module_id==7}">
                <li><a href="report/report_list.html" class="report_off"></a></li>
                </c:if>                                         
        </c:forEach>
            <li><a href="user/user_info.html" class="information_off"></a></li>
            <li><a href="user/user_modi_pwd.html" class="password_off"></a></li>
               
 </ul>