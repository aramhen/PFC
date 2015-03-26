<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="LP_title" /></title>

<style>
body {
	padding-top: 50px;
}

.center-template {
	padding: 15px 15px;
}

.center {
	text-align: center;
}

div.dataTables_info {
	white-space: normal !important;
}

.form-control {
	width: auto !important;
}

table.dataTable.hover tbody tr:hover, table.dataTable.hover tbody tr.odd:hover,
	table.dataTable.hover tbody tr.even:hover, table.dataTable.display tbody tr:hover,
	table.dataTable.display tbody tr.odd:hover, table.dataTable.display tbody tr.even:hover
	{
	background-color: whitesmoke;
}
td.uniqueAnswer_column{
	text-align: center;
	font-size: 24px;
}
/*Fix that on open modal windows page moves*/
body.modal-open {
	overflow: inherit;
	padding-right: inherit !important;
}

.modal-header {
    background-color: #f0f0f0;
 }
</style>

<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap-3.3.2.min.css" />
<link rel="stylesheet" type="text/css" href="resources/datatables/plugins/bootstrap/dataTables.bootstrap.css">
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/responsive/1.0.4/css/dataTables.responsive.css">

<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.11.2.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/datatables/js/jquery.dataTables-1.10.5.js" />"></script>
<script type="text/javascript" src="//cdn.datatables.net/responsive/1.0.4/js/dataTables.responsive.js"></script>
<script type="text/javascript" src="resources/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<script type="text/x-mathjax-config">
  MathJax.Hub.Config({
    tex2jax: {
      inlineMath: [["$","$"],["\\(","\\)"]]
    }
  });
  MathJax.Hub.Config({showMathMenu: false});

</script>
<script type="text/javascript" src="//cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS_HTML-full"></script>

<script type="text/javascript">
	$(document).ready(function() {
		var userLang = navigator.language || navigator.userLanguage;
		var tableLang;
		if((userLang.split('-')[0]).toLowerCase() == 'es') {
			tableLang = "../equationsapp/resources/datatables/i18n/Spanish.json";
		}else{
			tableLang = "../equationsapp/resources/datatables/i18n/English.json";
		}

		$("#problems").dataTable({
			//Set the language
			"language" : {
				"url" : tableLang
			},
			//Makes the datatable responsive
			"responsive" : true,
			//Show a Processing message while data is processing
			"bProcessing" : true,
			"sort" : "position",
			"order" : [ [ 0, "asc" ], [ 1, "asc" ] ],
			"sAjaxSource" : "listproblempagination.htm",
			"aoColumns" : [ {
				"mData" : "problemTitle"
			}, {
				"mData" : "teacherName"
			}, {
				"mData" : "methodName"
			}, {

				"mData" : "initDate"
			}, {
				"mData" : "endDate"
			}, {
				"mData" : "uniqueAnswer", "orderable" : false, "sClass": "uniqueAnswer_column", "mRender": function(data, type, row){
					if(data == "false"){
						return "<span class='glyphicon glyphicon-remove-circle' aria-hidden='true'></span>";
					}else{
						return "<span class='glyphicon glyphicon-ok-circle' aria-hidden='true'></span>";
					}}
			}, {
				"mData" : "null",
				"defaultContent" : "<button type='button' class='btn btn-default btn-mg equations'><span class='glyphicon glyphicon-zoom-in' aria-hidden='true'></span> "+ $('#button_view').val() +"</button>",
				"orderable" : false
			}, {
				"mData" : "null",
				"defaultContent" : "<button type='button' class='btn btn-default btn-mg solution'><span class='glyphicon glyphicon-zoom-in' aria-hidden='true'></span> "+ $('#button_view').val() +"</button>",
				"orderable" : false
			}, {
				"mData" : "null",
				"defaultContent" : "<button type='button' class='btn btn-danger btn-mg delete'><span class='glyphicon glyphicon-remove' aria-hidden='true'></span> "+ $('#button_delete').val() +"</button>",
				"orderable" : false
			},]
		});
		$('#problems tbody ').on('click', 'button.equations', function (event) {
			var table = $("#problems").DataTable();
		    var row;
			if($(this).closest('tr').hasClass('child')){
				row = $(this).closest('tr').prev().get(0);
			}else{
				row = $(this).closest("tr").get(0);
			}
		    var aData = table.row(row).data();
		    $('#myModalLabel').html(aData["problemTitle"] + " "+ $('#equation').val());
		   	// $("div.modal-body").innerHTML=aData["steps"];
		    //UpdateMath(aData["steps"]);
		    //var math = MathJax.Hub.getAllJax("modal-body")[0];
       		//MathJax.Hub.Queue(["Text", math, "modal-body"]);
       		$('#mathSpteps').html(aData["equations"]);
	    	//reprocess the MathOutput Element
	    	MathJax.Hub.Queue(["Typeset",MathJax.Hub,"mathSpteps"]);
		 	$('#viewModal').modal('show');
		 	event.stopImmediatePropagation();  //prevents the other on click from firing that fires up the inline editor
		});
		$('#problems tbody ').on('click', 'button.solution', function (event) {
			var table = $("#problems").DataTable();
		    var row;
			if($(this).closest('tr').hasClass('child')){
				row = $(this).closest('tr').prev().get(0);
			}else{
				row = $(this).closest("tr").get(0);
			}
		    var aData = table.row(row).data();
		    $('#myModalLabel').html(aData["problemTitle"] + " "+ $('#solution').val());
		   	// $("div.modal-body").innerHTML=aData["steps"];
		    //UpdateMath(aData["steps"]);
		    //var math = MathJax.Hub.getAllJax("modal-body")[0];
       		//MathJax.Hub.Queue(["Text", math, "modal-body"]);
       		$('#mathSpteps').html(aData["solution"]);
	    	//reprocess the MathOutput Element
	    	MathJax.Hub.Queue(["Typeset",MathJax.Hub,"mathSpteps"]);
		 	$('#viewModal').modal('show');
		 	event.stopImmediatePropagation();  //prevents the other on click from firing that fires up the inline editor
		});
		$('#problems tbody ').on('click', 'button.delete', function (event) {
			var table = $("#problems").DataTable();
		    var row;
			if($(this).closest('tr').hasClass('child')){
				row = $(this).closest('tr').prev().get(0);
			}else{
				row = $(this).closest("tr").get(0);
			}
		    var aData = table.row(row).data();
		    $('#myDeleteModalLabel').html($('#delete').val() + " " + aData["problemTitle"]);
		    
		    $('#idProblem').val(aData["idProblem"]);
		 	$('#deleteModal').modal('show');
		 	event.stopImmediatePropagation();  //prevents the other on click from firing that fires up the inline editor
		});
	});
</script>
</head>
<body>
	<!-- Topnav -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only"><spring:message code="Toggle_Navigation" /></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<span class="navbar-brand"><spring:message code="LP_title" /></span>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="home.htm"><spring:message code="Home" /></a></li>
					<li class="active"><a href="listproblem.htm"><spring:message code="LP_title" /></a></li>
					<li><a href="listanswer.htm"><spring:message code="LA_title" /></a></li>
					<li><a href="createproblem.htm"><spring:message code="CP_title" /></a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container body_width_eq">
		<div class="center-template">
			<h1 class="page-header center"><spring:message code="LP_title" /></h1>
			<form:form action="" method="GET">
				<h2 class="center">
					<spring:message code="LP_subtitle" /><br> <br>
				</h2>
				<table id="problems" class="table table-striped table-bordered hover" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th><spring:message code="LP_table_problem" /></th>
							<th><spring:message code="LP_table_teacher" /></th>
							<th><spring:message code="LP_table_method" /></th>
							<th><spring:message code="LP_table_initDate" /></th>
							<th><spring:message code="LP_table_endDate" /></th>
							<th><spring:message code="LP_table_multianswer" /></th>
							<th><spring:message code="LP_table_equations" /></th>
							<th><spring:message code="LP_table_solutions" /></th>
							<th><spring:message code="LP_table_actions" /></th>
						</tr>
					</thead>
				</table>
			</form:form>
		</div>
	</div>
	<!-- /.container -->

	<!-- Modal HTML to view equations and solution-->
	<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body">
					<div id="mathSpteps"></div>
				</div>
				<div class="modal-footer" id="modal-body">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal HTML to delete problem-->
	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myDeleteModalLabel"></h4>
				</div>
				<form:form method="post" commandName="Problem">
					<div class="modal-body">
						<span><spring:message code="LP_modal_message" /></span>	
						<form:hidden path="idProblems" id="idProblem"/>
					</div>
					<div class="modal-footer">				
						<input class="btn btn-danger" type="submit" value=<spring:message code="LP_modal_button_delete" /> id="submit"> 
						<button type="button" name="delete" class="btn btn-default" data-dismiss="modal"><spring:message code="LP_modal_button_close" /></button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	
	<input id="equation" type="hidden" value=<spring:message code="LP_modal_equation" />>
	<input id="solution" type="hidden" value=<spring:message code="LP_modal_solution" />>
	<spring:message code="LP_modal_delete" var="LP_modal_delete"/>
	<input id="delete" type="hidden" value="${LP_modal_delete}">
	<input id="button_view" type="hidden" value=<spring:message code="LP_table_button_view" />>
	<input id="button_delete" type="hidden" value=<spring:message code="LP_table_button_delete" />>
	
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap-3.3.2.min.js" />"></script>
</body>
</html>
