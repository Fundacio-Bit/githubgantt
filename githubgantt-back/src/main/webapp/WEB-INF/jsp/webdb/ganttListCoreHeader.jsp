<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="GanttFields" className="org.fundaciobit.githubgantt.model.fields.GanttFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${ghg:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.GANTTID)}">
        <th>${ghg:getSortIcons(__theFilterForm,GanttFields.GANTTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.NOM)}">
        <th>${ghg:getSortIcons(__theFilterForm,GanttFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.ACCOUNTID)}">
        <th>${ghg:getSortIcons(__theFilterForm,GanttFields.ACCOUNTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.ORGANIZATION)}">
        <th>${ghg:getSortIcons(__theFilterForm,GanttFields.ORGANIZATION)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.REPOSITORY)}">
        <th>${ghg:getSortIcons(__theFilterForm,GanttFields.REPOSITORY)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.PROJECTNOM)}">
        <th>${ghg:getSortIcons(__theFilterForm,GanttFields.PROJECTNOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.PROJECT)}">
        <th>${ghg:getSortIcons(__theFilterForm,GanttFields.PROJECT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.STARTDATE)}">
        <th>${ghg:getSortIcons(__theFilterForm,GanttFields.STARTDATE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.NUMEROPROGRAMADORS)}">
        <th>${ghg:getSortIcons(__theFilterForm,GanttFields.NUMEROPROGRAMADORS)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${ghg:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

