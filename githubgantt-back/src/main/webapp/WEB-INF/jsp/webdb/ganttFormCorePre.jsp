<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="GanttFields" className="org.fundaciobit.githubgantt.model.fields.GanttFields"/>
  
  <c:set var="__theForm" value="${ganttForm}"/>

  <div class="module_content">
    <div class="tab_container">
    
    <c:if test="${not __theForm.view}">    <c:forEach items="${__theForm.hiddenFields}" var="hiddenFieldF" >
      <c:set  var="hiddenField" value="${hiddenFieldF.javaName}" />
      <c:if test="${gen:hasProperty(__theForm.gantt,hiddenField)}">
        <form:errors path="gantt.${hiddenField}" cssClass="errorField alert alert-danger" />
        <form:hidden path="gantt.${hiddenField}"/>
      </c:if>
    </c:forEach>
    </c:if>

    <form:errors cssClass="errorField alert alert-danger" delimiter="&lt;p/&gt;" />
    <table class="tdformlabel table-condensed table table-bordered table-striped marTop10  " > 
    <tbody>      

