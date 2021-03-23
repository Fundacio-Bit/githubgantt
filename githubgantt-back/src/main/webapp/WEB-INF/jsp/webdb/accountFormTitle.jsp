<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(accountForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(accountForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty accountForm.titleCode}">
    <fmt:message key="${accountForm.titleCode}" >
      <fmt:param value="${accountForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty accountForm.entityNameCode}">
      <fmt:message var="entityname" key="account.account"/>
    </c:if>
    <c:if test="${not empty accountForm.entityNameCode}">
      <fmt:message var="entityname" key="${accountForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${accountForm.nou?'genapp.createtitle':(accountForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty accountForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(accountForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(accountForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${accountForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>