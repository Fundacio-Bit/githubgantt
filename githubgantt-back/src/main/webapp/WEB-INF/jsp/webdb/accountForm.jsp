
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="accountFormTitle.jsp" %>


<form:form modelAttribute="accountForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${accountForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="accountFormCorePre.jsp" %>
  <%@include file="accountFormCore.jsp" %>

  <%@include file="accountFormCorePost.jsp" %>

  <%@include file="accountFormButtons.jsp" %>

  <c:if test="${accountForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/accountFormModificable.jsp" %>
  </c:if>

</form:form>


