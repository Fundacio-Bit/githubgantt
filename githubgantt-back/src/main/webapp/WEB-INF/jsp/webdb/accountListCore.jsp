  <c:if test="${empty accountItems}">
     <%@include file="accountListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty accountItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="accountListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="accountListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="accountListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="account" items="${accountItems}">

        <tr id="account_rowid_${account.accountID}">
          <%@include file="accountListCoreMultipleSelect.jsp" %>

          <%@include file="accountListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="accountListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
