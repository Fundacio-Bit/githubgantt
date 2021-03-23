<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AccountFields" className="org.fundaciobit.githubgantt.model.fields.AccountFields"/>
  
  <c:set var="__theForm" value="${accountForm}"/>

  <div class="module_content">
    <div class="tab_container">
    
    <c:if test="${not __theForm.view}">    <c:forEach items="${__theForm.hiddenFields}" var="hiddenFieldF" >
      <c:set  var="hiddenField" value="${hiddenFieldF.javaName}" />
      <c:if test="${gen:hasProperty(__theForm.account,hiddenField)}">
        <form:errors path="account.${hiddenField}" cssClass="errorField alert alert-danger" />
        <form:hidden path="account.${hiddenField}"/>
      </c:if>
    </c:forEach>
    </c:if>

    <form:errors cssClass="errorField alert alert-danger" delimiter="&lt;p/&gt;" />
    <table class="tdformlabel table-condensed table table-bordered table-striped marTop10  " > 
    <tbody>      

