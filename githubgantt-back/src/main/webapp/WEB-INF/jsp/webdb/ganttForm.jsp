
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="ganttFormTitle.jsp" %>


<form:form modelAttribute="ganttForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${ganttForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="ganttFormCorePre.jsp" %>
  <%@include file="ganttFormCore.jsp" %>

  <%@include file="ganttFormCorePost.jsp" %>

  <%@include file="ganttFormButtons.jsp" %>

  <c:if test="${ganttForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/ganttFormModificable.jsp" %>
  </c:if>

</form:form>


