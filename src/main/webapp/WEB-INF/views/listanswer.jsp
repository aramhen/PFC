<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="LA_title" /></title>

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
/*Fix that on open modal windows page moves*/
body.modal-open {
	overflow: inherit;
	padding-right: inherit !important;
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
			"order" : [ [ 0, "asc" ], [ 1, "asc" ] ],
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
				"mData" : "null",
				"defaultContent" : "<button type='button' class='btn btn-default btn-mg'><span class='glyphicon glyphicon-zoom-in' aria-hidden='true'></span> View++</button>",
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
		    $('#myModalLabel').html(aData["studentName"] + " Answer");
		   	// $("div.modal-body").innerHTML=aData["steps"];
		    //UpdateMath(aData["steps"]);
		    //var math = MathJax.Hub.getAllJax("modal-body")[0];
       		//MathJax.Hub.Queue(["Text", math, "modal-body"]);
       		$('#mathSpteps').html(aData["steps"]);
	    	//reprocess the MathOutput Element
	    	MathJax.Hub.Queue(["Typeset",MathJax.Hub,"mathSpteps"]);
		 	$('#basicModal').modal('show');
		 	event.stopImmediatePropagation();  //prevents the other on click from firing that fires up the inline editor
		});
	});
</script>
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">List Answers</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
					<li><a href="#contact">Contact</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container body_width_eq">
		<div class="center-template">
			<h1 class="page-header center">List Answers++</h1>
			<form:form action="" method="GET">
				<h2 class="center">
					Spring MVC pagination using data tables<br> <br>
				</h2>
				<table id="answers" class="table table-striped table-bordered hover" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>problem</th>
							<th>student</th>
							<th>answerDate</th>
							<th>solution</th>
							<th>steps</th>
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
				<div class="modal-body">
					<div id="mathSpteps"></div>
				</div>
				<div class="modal-footer" id="modal-body">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap-3.3.2.min.js" />"></script>
</body>
</html>
