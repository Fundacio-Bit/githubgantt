  <c:if test="${empty ganttItems}">
     <%@include file="ganttListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty ganttItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="ganttListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="ganttListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="ganttListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="gantt" items="${ganttItems}">

        <tr id="gantt_rowid_${gantt.ganttID}">
          <%@include file="ganttListCoreMultipleSelect.jsp" %>

          <%@include file="ganttListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="ganttListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
