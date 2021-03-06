<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="LA_title" /></title>

<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap-3.3.2.min.css" />
<link rel="stylesheet" type="text/css" href="resources/datatables/plugins/bootstrap/dataTables.bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/datatables/plugins/responsive-1.0.5/css/dataTables.responsive.css">
<link rel="stylesheet" type="text/css" href="resources/css/custom.css" />

<script type="text/javascript" src="resources/jquery/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="resources/datatables/js/jquery.dataTables-1.10.5.js"></script>
<script type="text/javascript" src="resources/datatables/plugins/responsive-1.0.5/js/dataTables.responsive.js"></script>
<script type="text/javascript" src="resources/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="resources/datatables/plugins/date-sorting/moment-2.8.4.min.js"></script>
<script type="text/javascript" src="resources/datatables/plugins/date-sorting/datetime-moment.js"></script>
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
		$.fn.dataTable.moment('DD/MM/YYYY HH:mm');
		var userLang = navigator.language || navigator.userLanguage;
		var tableLang;
		if((userLang.split('-')[0]).toLowerCase() == 'es') {
			tableLang = "../equationsapp/resources/datatables/i18n/Spanish.json";
		}else{
			tableLang = "../equationsapp/resources/datatables/i18n/English.json";
		}

		$("#answers").dataTable({
			//Set the language
			"language" : {
				"url" : tableLang
			},
			//Makes the datatable responsive
			"responsive" : true,
			//Show a Processing message while data is processing
			"bProcessing" : true,
			"sort" : "position",
			"order" : [ 2, "desc" ],
			"sAjaxSource" : "listanswerpagination.htm",
			"aoColumns" : [ {
				"mData" : "problemTitle"
			}, {
				"mData" : "studentName"
			}, {
				"mData" : "answerDate"
			}, {
				"mData" : "solution"
			}, {
				"mData" : "null","sClass": "column_centered",
				"defaultContent" : "<button type='button' class='btn btn-default btn-mg'><span class='glyphicon glyphicon-zoom-in' aria-hidden='true'></span> <spring:message code='LA_table_button_view' /></button>",
				"orderable" : false
			},]
		});
		$('#answers tbody ').on('click', 'button', function (event) {
			var table = $("#answers").DataTable();
		    var row;
			if($(this).closest('tr').hasClass('child')){
				row = $(this).closest('tr').prev().get(0);
			}else{
				row = $(this).closest("tr").get(0);
			}
		    var aData = table.row(row).data();
		    
		    if((userLang.split('-')[0]).toLowerCase() == 'es') {
		    	 $('#myModalLabel').html("<spring:message code='LA_modal_answer'/>" + aData["studentName"]);
			}else{
				 $('#myModalLabel').html(aData["studentName"] + " <spring:message code='LA_modal_answer'/>");
			}		   
		   	// $("div.modal-body").innerHTML=aData["steps"];
		    //UpdateMath(aData["steps"]);
		    //var math = MathJax.Hub.getAllJax("modal-body")[0];
       		//MathJax.Hub.Queue(["Text", math, "modal-body"]);
       		$('#mathSteps').html(aData["steps"]);
	    	//reprocess the MathOutput Element
	    	MathJax.Hub.Queue(["Typeset",MathJax.Hub,"mathSteps"]);
		 	$('#basicModal').modal('show');
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
				<span class="navbar-brand"><spring:message code="LA_title" /></span>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="home.htm"><spring:message code="Home" /></a></li>
					<li><a href="listproblem.htm"><spring:message code="LP_title" /></a></li>
					<li class="active"><a href="listanswer.htm"><spring:message code="LA_title" /></a></li>
					<li><a href="createproblem.htm"><spring:message code="CP_title" /></a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container">
		<div class="init-template">
			<h1 class="page-header center"><spring:message code="LA_title" /></h1>
			<form:form action="" method="GET">
				<h2 class="center">
					<spring:message code="LA_subtitle" /><br> <br>
				</h2>
				<table id="answers" class="table table-striped table-bordered hover" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th><spring:message code="LA_table_problem" /></th>
							<th><spring:message code="LA_table_student" /></th>
							<th><spring:message code="LA_table_answerDate" /></th>
							<th><spring:message code="LA_table_solution" /></th>
							<th><spring:message code="LA_table_steps" /></th>
						</tr>
					</thead>
				</table>
			</form:form>
		</div>
	</div>
	<!-- /.container -->

	<!-- Modal HTML -->
	<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body center">
					<div id="mathSteps"></div>
				</div>
				<div class="modal-footer" id="modal-body">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="LA_modal_button_close" /></button>
				</div>
			</div>
		</div>
	</div>

	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap-3.3.2.min.js" />"></script>
</body>
</html>
