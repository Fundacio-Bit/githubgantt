<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.context.i18n.LocaleContextHolder"
%><%@ page language="java" 
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>
<div class="clear"></div>
<div class="spacer"></div>
<div>
<center>
<!-- 
<img src="<c:url value="/img/app-logo.png"/>"  alt="GithubGantt" title="GithubGantt"/>
 -->
<br/>
<h2>GANTTs Disponibles</h2>
<br/>
<table  class="table table-bordered table-striped" >

<thead>
<tr>
<!--  <td>ID</td>  -->
<td>Nom</td>
<td>Project</td>
<td>Repository</td>
<td>Organitzation</td>
<td>Gantt</td>
</tr>
</thead>
<tbody>

       <c:forEach items="${gantts}" var="gantt">
         
<tr>
<!--  <td>${gantt.ganttID}</td>  -->
<td>${gantt.nom}</td>
<td>  <a href="<c:url value="/public/project/${gantt.ganttID}" />" target="_blank"> ${gantt.projectNom} </a></td>
<td><a href="https://github.com/${gantt.organization}/${gantt.repository}" target="_blank">${gantt.repository}</a></td>
<td> <a href="https://github.com/${gantt.organization}" target="_blank">${gantt.organization}</a></td>
<td><a href="<c:url value="/public/gantt/${gantt.ganttID}" />" target="_blank"><img src="<c:url value="/img/gantticon.png"/>" /></a>
</tr>

</c:forEach> 
</tbody>
</table>
<br/>
</center>
 
</div>

<br/>
