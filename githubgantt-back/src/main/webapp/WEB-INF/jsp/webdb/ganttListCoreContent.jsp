<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="GanttFields" className="org.fundaciobit.githubgantt.model.fields.GanttFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[gantt.ganttID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.GANTTID)}">
          <td>
          ${gantt.ganttID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.NOM)}">
          <td>
          ${gantt.nom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.ACCOUNTID)}">
          <td>
          <c:set var="tmp">${gantt.accountID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfAccountForAccountID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.ORGANIZATION)}">
          <td>
          <c:set var="tmp">${gantt.organization}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForOrganization[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.REPOSITORY)}">
          <td>
          <c:set var="tmp">${gantt.repository}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForRepository[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.PROJECTNOM)}">
          <td>
          ${gantt.projectNom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.PROJECT)}">
          <td>
          <c:set var="tmp">${gantt.project}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForProject[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.STARTDATE)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${gantt.startDate}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,GanttFields.NUMEROPROGRAMADORS)}">
          <td>
          ${gantt.numeroProgramadors}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[gantt.ganttID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


