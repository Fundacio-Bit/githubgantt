<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.context.i18n.LocaleContextHolder"
%><%@ page language="java" 
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>

<c:if test="${ not empty errors}">

<c:forEach items="${errors}" var="error">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
  <strong>ERROR !</strong> ${error}
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>    
</c:forEach>

</c:if>


<c:if test="${ empty errors}">

<link rel="stylesheet" type="text/css" href="<c:url value="/css/dhtmlxgantt.css"/>">
<script src="<c:url value="/js/dhtmlxgantt.js"/>"></script>

 <style>
.weekend { background: #dddddd;}
</style> 

<style>
.currentdate { background: #ffdddd;}
</style>


<c:forEach items="${warnings}" var="avis">
<div class="alert alert-warning alert-dismissible fade show" role="alert">
  <strong>AVIS ! </strong> ${avis}.
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>
</c:forEach>
 



<small>
<b>
startDate: ${gantt.startDate}
 ||  organization: ${gantt.organization}
 ||  repository: ${gantt.repository}
 ||  projectName: ${gantt.projectNom}
 ||  #programadors: ${gantt.numeroProgramadors}
 &nbsp; &nbsp; &nbsp;
 <button onclick="javascript: gantt.config.min_column_width =  gantt.config.min_column_width +5;gantt.init('gantt_here');" >Ample Columna +</button>
 <button onclick="javascript: gantt.config.min_column_width =  gantt.config.min_column_width -5;gantt.init('gantt_here');" >Ample Columna -</button>
</b>
</small>



 <div id="gantt_here" style="width:100%; height:100%;"></div>
 

 
 <script type="text/javascript">
gantt.config.xml_date = "%Y-%m-%d %H:%i";


gantt.config.autosize = "xy";



gantt.config.min_column_width = 75;


gantt.plugins({         tooltip: true     }); 

gantt.config.columns=[
    {name:"text",       label:"Nom Tasca",  tree:true, width:'*' },    
    {name:"duration",   label:"Duració",   align: "center", hide: true },
    
];


//gantt.config.start_date = new Date(2019, 7, 1);
var holidays = [//USA, DC holidays
		new Date(2021, 4, 1),
		new Date(2021, 4, 2)
		/*,
		new Date(2019, 3, 16),
		new Date(2019, 4, 12),
		new Date(2019, 4, 27),
		new Date(2019, 5, 16),
		new Date(2019, 6, 4),
		new Date(2019, 7, 2),
		new Date(2019, 7, 6),
		new Date(2019, 10 , 6),
		new Date(2020, 10, 5) */
	];

	for (var i = 0; i < holidays.length; i++) {
		gantt.setWorkTime({
			date: holidays[i],
			hours: false
		});
	}

gantt.config.duration_unit = "hour";//an hour
gantt.config.duration_step = 1; // ${step}; 

// 0 refers to Sunday, 6 - to Saturday
gantt.templates.scale_cell_class = function(date){
    return columnColor(null, date);
};


gantt.templates.timeline_cell_class = function(task,date){
    return columnColor(task, date);
};


function columnColor(task, date) {
	if(date.getDay()==0||date.getDay()==6){
        return "weekend";
    }
	
	var day_month = date.getDate();
	var month = date.getMonth(); // Since getMonth() returns month from 0-11 not 1-12
	var year = date.getFullYear();

    
    if(day_month == ${now_day_month} && month == ${now_month} && year == ${now_year} ) {
        return "currentdate";
    }
}


gantt.config.open_tree_initially = true;

gantt.config.readonly = true;

gantt.init("gantt_here");
 
gantt.attachEvent("onTaskClick", function(id,e){
    //alert("Click on tasca " + id)
    //console.log(e);
    if (id == 1) {

    	 /// XYZ ZZZ Aquí fa falta Enllaç de Projecte
    	var win = window.open("${urlBase}/${organization}/${repository}/projects/${projectNumber}");
    	 
    	 // XYZ ZZZ  Això es el codi per augmentar tamany de columna
    	gantt.config.min_column_width = gantt.config.min_column_width + 5;
    	
    	gantt.init("gantt_here");
    	 
    	 return true;
    }
    
    if (e.toElement) {
    	if (e.toElement.className == 'gantt_task_content') {
    		var win = window.open("${urlBase}/${organization}/${repository}/issues/" + id, '_blank');
        }
         
    } else {
    	var win = window.open("${urlBase}/${organization}/${repository}/issues/" + id, '_blank');
    }
    return true;
});


gantt.parse({
  data: [
    ${addRows}
  ] ,
  links:[
	${addLinks}
 ]
});

  </script>
  
</c:if>
 

