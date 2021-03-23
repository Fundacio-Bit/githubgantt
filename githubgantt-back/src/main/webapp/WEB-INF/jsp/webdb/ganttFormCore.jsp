<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="GanttFields" className="org.fundaciobit.githubgantt.model.fields.GanttFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,GanttFields.NOM)}">
        <tr id="gantt_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[GanttFields.NOM])?'gantt.nom':__theForm.labels[GanttFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[GanttFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GanttFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="gantt.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,GanttFields.NOM)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,GanttFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="gantt.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,GanttFields.ACCOUNTID)}">
        <tr id="gantt_accountID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[GanttFields.ACCOUNTID])?'gantt.accountID':__theForm.labels[GanttFields.ACCOUNTID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[GanttFields.ACCOUNTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GanttFields.ACCOUNTID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="gantt.accountID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,GanttFields.ACCOUNTID)}" >
          <form:hidden path="gantt.accountID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.gantt.accountID,__theForm.listOfAccountForAccountID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,GanttFields.ACCOUNTID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="gantt_accountID"  onchange="if(typeof onChangeAccountID == 'function') {  onChangeAccountID(this); };"  cssClass="form-control col-md-8" path="gantt.accountID">
            <c:forEach items="${__theForm.listOfAccountForAccountID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,GanttFields.ORGANIZATION)}">
        <tr id="gantt_organization_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[GanttFields.ORGANIZATION])?'gantt.organization':__theForm.labels[GanttFields.ORGANIZATION]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[GanttFields.ORGANIZATION]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GanttFields.ORGANIZATION]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="gantt.organization" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,GanttFields.ORGANIZATION)}" >
          <form:hidden path="gantt.organization"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.gantt.organization,__theForm.listOfValuesForOrganization)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,GanttFields.ORGANIZATION)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="gantt_organization"  onchange="if(typeof onChangeOrganization == 'function') {  onChangeOrganization(this); };"  cssClass="form-control col-md-8" path="gantt.organization">
            <c:forEach items="${__theForm.listOfValuesForOrganization}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,GanttFields.REPOSITORY)}">
        <tr id="gantt_repository_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[GanttFields.REPOSITORY])?'gantt.repository':__theForm.labels[GanttFields.REPOSITORY]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[GanttFields.REPOSITORY]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GanttFields.REPOSITORY]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="gantt.repository" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,GanttFields.REPOSITORY)}" >
          <form:hidden path="gantt.repository"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.gantt.repository,__theForm.listOfValuesForRepository)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,GanttFields.REPOSITORY)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="gantt_repository"  onchange="if(typeof onChangeRepository == 'function') {  onChangeRepository(this); };"  cssClass="form-control col-md-8" path="gantt.repository">
            <c:forEach items="${__theForm.listOfValuesForRepository}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,GanttFields.PROJECTNOM)}">
        <tr id="gantt_projectNom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[GanttFields.PROJECTNOM])?'gantt.projectNom':__theForm.labels[GanttFields.PROJECTNOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[GanttFields.PROJECTNOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GanttFields.PROJECTNOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="gantt.projectNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,GanttFields.PROJECTNOM)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,GanttFields.PROJECTNOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="gantt.projectNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,GanttFields.PROJECT)}">
        <tr id="gantt_project_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[GanttFields.PROJECT])?'gantt.project':__theForm.labels[GanttFields.PROJECT]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[GanttFields.PROJECT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GanttFields.PROJECT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="gantt.project" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,GanttFields.PROJECT)}" >
          <form:hidden path="gantt.project"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.gantt.project,__theForm.listOfValuesForProject)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,GanttFields.PROJECT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="gantt_project"  onchange="if(typeof onChangeProject == 'function') {  onChangeProject(this); };"  cssClass="form-control col-md-8" path="gantt.project">
            <c:forEach items="${__theForm.listOfValuesForProject}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,GanttFields.STARTDATE)}">
        <tr id="gantt_startDate_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[GanttFields.STARTDATE])?'gantt.startDate':__theForm.labels[GanttFields.STARTDATE]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[GanttFields.STARTDATE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GanttFields.STARTDATE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="gantt.startDate" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="gantt_startDate" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,GanttFields.STARTDATE)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#gantt_startDate" path="gantt.startDate" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,GanttFields.STARTDATE)}" >
                    <div class="input-group-append"  data-target="#gantt_startDate"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#gantt_startDate').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
          </script>        </div>
      </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,GanttFields.NUMEROPROGRAMADORS)}">
        <tr id="gantt_numeroProgramadors_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[GanttFields.NUMEROPROGRAMADORS])?'gantt.numeroProgramadors':__theForm.labels[GanttFields.NUMEROPROGRAMADORS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[GanttFields.NUMEROPROGRAMADORS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GanttFields.NUMEROPROGRAMADORS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="gantt.numeroProgramadors" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,GanttFields.NUMEROPROGRAMADORS)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,GanttFields.NUMEROPROGRAMADORS)? ' uneditable-input' : ''}"  style=""  path="gantt.numeroProgramadors"   />

           </td>
        </tr>
        </c:if>
        
