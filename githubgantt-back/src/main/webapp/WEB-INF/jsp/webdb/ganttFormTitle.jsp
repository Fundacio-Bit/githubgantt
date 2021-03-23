<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(ganttForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(ganttForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty ganttForm.titleCode}">
    <fmt:message key="${ganttForm.titleCode}" >
      <fmt:param value="${ganttForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty ganttForm.entityNameCode}">
      <fmt:message var="entityname" key="gantt.gantt"/>
    </c:if>
    <c:if test="${not empty ganttForm.entityNameCode}">
      <fmt:message var="entityname" key="${ganttForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${ganttForm.nou?'genapp.createtitle':(ganttForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty ganttForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(ganttForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(ganttForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${ganttForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>