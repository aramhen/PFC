<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="SLP_title" /></title>

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
td.column_centered{
	text-align: center;
}
/*Fix that on open modal windows page moves*/
body.modal-open {
	overflow: inherit;
	padding-right: inherit !important;
}
.remove_icon{
	font-size: 24px;
}
</style>

<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap-3.3.2.min.css" />
<link rel="stylesheet" type="text/css" href="resources/datatables/plugins/bootstrap/dataTables.bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/datatables/plugins/responsive-1.0.5/css/dataTables.responsive.css">

<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.11.2.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/datatables/js/jquery.dataTables-1.10.5.js" />"></script>
<script type="text/javascript" src="resources/datatables/plugins/responsive-1.0.5/js/dataTables.responsive.js"></script>
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
<!-- <script type="text/javascript" src="resources/mathjax/MathJax.js?config=TeX-AMS_HTML-full"></script> -->

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
			"sAjaxSource" : "studentlistproblempagination.htm",
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
						return "<span class='glyphicon glyphicon-ok-circle' aria-hidden='true' style='color:#5cb85c;'></span>";
					}else{
						return "<span class='glyphicon glyphicon-remove-circle' aria-hidden='true' style='color:#d9534f;'></span>";
					}}
			}, {
				"mData" : "null","sClass": "column_centered",
				"defaultContent" : "<button type='button' class='btn btn-default btn-mg equations'><span class='glyphicon glyphicon-zoom-in' aria-hidden='true'></span> <spring:message code='SLP_table_button_view' /></button>",
				"orderable" : false
			}, {
				"mData" : "uniqueAnswer", "orderable" : false, "sClass": "uniqueAnswer_column", "mRender": function(data, type, row){
					if(data == "false"){
						return "<form id='Problem' action='/equationsapp/studentlistproblem.htm' method='post' style='margin: 0px;'><input type='hidden' value='" + row['idProblem'] + "' name='idProblems'>" + "<button type='input' class='btn btn-primary btn-mg'><span class='glyphicon glyphicon-chevron-right' aria-hidden='true'></span> <spring:message code='SLP_table_button_solve' /></button>" + "</form>";
					}else{
						return "<button type='button' class='btn btn-primary btn-mg solveModal'><span class='glyphicon glyphicon-chevron-right' aria-hidden='true'></span> <spring:message code='SLP_table_button_solve' /></button>";
					}}
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
		    if((userLang.split('-')[0]).toLowerCase() == 'es') {
		    	$('#myModalLabel').html("<spring:message code='SLP_modal_equation' />" + aData["problemTitle"]);
			}else{
				$('#myModalLabel').html(aData["problemTitle"] + " <spring:message code='SLP_modal_equation' />");
			}
		   	// $("div.modal-body").innerHTML=aData["steps"];
		    //UpdateMath(aData["steps"]);
		    //var math = MathJax.Hub.getAllJax("modal-body")[0];
       		//MathJax.Hub.Queue(["Text", math, "modal-body"]);
       		$('#mathSteps').html(aData["equations"]);
	    	//reprocess the MathOutput Element
	    	MathJax.Hub.Queue(["Typeset",MathJax.Hub,"mathSteps"]);
		 	$('#viewModal').modal('show');
		 	event.stopImmediatePropagation();  //prevents the other on click from firing that fires up the inline editor
		});
		$('#problems tbody ').on('click', 'button.solveModal', function (event) {
			var table = $("#problems").DataTable();
		    var row;
			if($(this).closest('tr').hasClass('child')){
				row = $(this).closest('tr').prev().get(0);
			}else{
				row = $(this).closest("tr").get(0);
			}
		    var aData = table.row(row).data();
		    $('#mySolveModalLabel').html("<spring:message code='SLP_modal_solve' /> " + aData["problemTitle"]);
		    
		    $('#idProblem_modal').val(aData["idProblem"]);
		 	$('#solveModal').modal('show');
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
				<span class="navbar-brand"><spring:message code="SLP_title" /></span>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="studenthome.htm"><spring:message code="SH_title" /></a></li>
					<li class="active"><a href="studentlistproblem.htm"><spring:message code="SLP_title" /></a></li>
					<li><a href="studentlistanswer.htm"><spring:message code="SLA_title" /></a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container body_width_eq">
		<div class="center-template">
			<h1 class="page-header center"><spring:message code="SLP_title" /></h1>
			<form:form action="" method="GET">
				<h2 class="center">
					<spring:message code="SLP_subtitle" /><br> <br>
				</h2>
				<table id="problems" class="table table-striped table-bordered hover" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th><spring:message code="SLP_table_problem" /></th>
							<th><spring:message code="SLP_table_teacher" /></th>
							<th><spring:message code="SLP_table_method" /></th>
							<th><spring:message code="SLP_table_initDate" /></th>
							<th><spring:message code="SLP_table_endDate" /></th>
							<th><spring:message code="SLP_table_multianswer" /></th>
							<th><spring:message code="SLP_table_equations" /></th>
							<th><spring:message code="SLP_table_actions" /></th>
						</tr>
					</thead>
				</table>
			</form:form>
		</div>
	</div>
	<!-- /.container -->
	
	<!-- Modal HTML to view equations-->
	<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body">
					<div id="mathSteps"></div>
				</div>
				<div class="modal-footer" id="modal-body">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal HTML to solve problems no multianswer-->
	<div class="modal fade" id="solveModal" tabindex="-1" role="dialog" aria-labelledby="solveModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="mySolveModalLabel"></h4>
				</div>
				<form:form method="post" commandName="Problem" action="/equationsapp/studentlistproblem.htm">
					<div class="modal-body">
						<span><spring:message code="SLP_modal_message" /></span>	
						<form:hidden path="idProblems" id="idProblem_modal"/>
					</div>
					<div class="modal-footer">				
						<input class="btn btn-primary" type="submit" value=<spring:message code="SLP_modal_button_solve" /> id="submit"> 
						<button type="button" name="solve" class="btn btn-default" data-dismiss="modal"><spring:message code="SLP_modal_button_close" /></button>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap-3.3.2.min.js" />"></script>
</body>
</html>
