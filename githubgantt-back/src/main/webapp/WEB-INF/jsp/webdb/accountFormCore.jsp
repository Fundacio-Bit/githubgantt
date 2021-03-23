<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AccountFields" className="org.fundaciobit.githubgantt.model.fields.AccountFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccountFields.NOM)}">
        <tr id="account_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccountFields.NOM])?'account.nom':__theForm.labels[AccountFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[AccountFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccountFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="account.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccountFields.NOM)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,AccountFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="account.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccountFields.USERNAME)}">
        <tr id="account_username_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccountFields.USERNAME])?'account.username':__theForm.labels[AccountFields.USERNAME]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[AccountFields.USERNAME]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccountFields.USERNAME]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="account.username" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccountFields.USERNAME)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,AccountFields.USERNAME)? ' uneditable-input' : ''}"  style="" maxlength="255" path="account.username"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccountFields.TOKEN)}">
        <tr id="account_token_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccountFields.TOKEN])?'account.token':__theForm.labels[AccountFields.TOKEN]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[AccountFields.TOKEN]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccountFields.TOKEN]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="account.token" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccountFields.TOKEN)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,AccountFields.TOKEN)? ' uneditable-input' : ''}"  style="" maxlength="255" path="account.token"   />

           </td>
        </tr>
        </c:if>
        
