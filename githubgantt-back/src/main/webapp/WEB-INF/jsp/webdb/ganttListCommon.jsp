<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${ganttFilterForm.contexte}"/>
  <c:set var="formName" value="gantt" />
  <c:set var="__theFilterForm" value="${ganttFilterForm}" />
  <c:if test="${empty ganttFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="gantt.gantt"/>
  </c:if>
  <c:if test="${not empty ganttFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${ganttFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty ganttFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="gantt.gantt"/>
  </c:if>
  <c:if test="${not empty ganttFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${ganttFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.gantt.submit();  
  }
</script>
