<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${accountFilterForm.contexte}"/>
  <c:set var="formName" value="account" />
  <c:set var="__theFilterForm" value="${accountFilterForm}" />
  <c:if test="${empty accountFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="account.account"/>
  </c:if>
  <c:if test="${not empty accountFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${accountFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty accountFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="account.account"/>
  </c:if>
  <c:if test="${not empty accountFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${accountFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.account.submit();  
  }
</script>
