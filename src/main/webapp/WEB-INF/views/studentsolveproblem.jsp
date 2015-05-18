<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="SSP_title" /></title>

<link rel="stylesheet" href="resources/bootstrap/css/bootstrap-3.3.2.min.css" type="text/css" />
<link rel="stylesheet" href="resources/css/custom.css" type="text/css" />

</head>
<body>
	<!-- Topnav -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only"><spring:message code="Toggle_Navigation" /></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<span class="navbar-brand"><spring:message code="SSP_title" /></span>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="studenthome.htm"><spring:message code="SH_title" /></a></li>
					<li><a href="studentlistproblem.htm"><spring:message code="SLP_title" /></a></li>
					<li><a href="studentlistanswer.htm"><spring:message code="SLA_title" /></a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	
	<spring:message code="SSP_placeholder_X" var="SSP_placeholder_X" />
	<spring:message code="SSP_placeholder_Y" var="SSP_placeholder_Y" />
	<spring:message code="SSP_placeholder_Z" var="SSP_placeholder_Z" />

	<div class="container">

		<div class="center-template">
			<h1 class="page-header">
				<spring:message code="SSP_subtitle" />
			</h1>
			<div class="row">
				<div class="row">
					<div class="row col-xs-6">
						<label><spring:message code="SSP_problem" />&nbsp;</label>${Problem.title}</div>
					<div class="row col-xs-6">
						<label><spring:message code="SSP_teacher" />&nbsp;</label>${Problem.teacherRef.name}</div>
				</div>
				<div class="row">

					<div class="row col-xs-6">
						<label><spring:message code="SSP_init_date" />&nbsp;</label>${Problem.initDate}</div>
					<div class="row col-xs-6">
						<label><spring:message code="SSP_end_date" />&nbsp;</label>${Problem.endDate}</div>
				</div>
				<div class="row">
					<div class="row col-xs-6">
						<label><spring:message code="SSP_method" />&nbsp;</label>${Problem.methodRef.name}</div>

					<div class="row col-xs-6">
						<label><spring:message code="SSP_multianswer" />&nbsp;</label>
						<c:choose>
							<c:when test="${Problem.uniqueAnswer}">
								<span class='glyphicon glyphicon-remove-circle' aria-hidden='true' style='color: #d9534f; font-size: 15px !important;'></span>
								<br />
							</c:when>
							<c:otherwise>
								<span class='glyphicon glyphicon-ok-circle' aria-hidden='true' style='color: #5cb85c; font-size: 15px !important;'></span>
								<br />
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="row">
					<div class="row col-xs-6">
						<label><spring:message code="SSP_numvariables" />&nbsp;</label>${Problem.numVariables}</div>
					<div class="row">
						<div class="col-xs-3" style="text-align: right;">
							<label><spring:message code="SSP_equations" /></label>
						</div>
						<div class="col-xs-3" style="text-align: left;">
							<div class="row">
								<div class="col-xs-12">
									<div class="mini-box">${Equation1}</div>
								</div>
								<c:if test="${Problem.numVariables > 1}">
									<div class="col-xs-12">
										<div class="mini-box">${Equation2}</div>
									</div>
								</c:if>
								<c:if test="${Problem.numVariables > 2}">
									<div class="col-xs-12">
										<div class="mini-box">${Equation3}</div>
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- /.container -->

	<div class="container">
		<div class="center-template">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="SSP_form_header" />
				</div>
				<form:form method="post" commandName="studentSolveProblemDTO" class="form-horizontal" style="margin-left:28px;">				
					<input type="text" value="${Problem.idProblems}" hidden="true" name="idProblem" id="idProblem">
					<input type="text" value="${idStudent}" hidden="true" name="idStudent" id="idStudent">
					<div id="steps-inputs"></div>
					<div class="panel-body" style="text-align: left;">
						<div class="row">
							<div class="form-group">
								<div class="row col-xs-12" style="margin-left: 0px">
									<label><spring:message code="SSP_insert_solution" /></label>
								</div>
								<div class="form-group">
									<div class="row" style="margin-right: 60px">
										<div class="col-xs-1" style="text-align: right;">
											<label class="col-md-offset-2 subfield"><spring:message code="SSP_variableX" /></label>
										</div>
										<div class="col-xs-2 form-group">
											<form:input path="variableX" class="form-control" placeholder="${SSP_placeholder_X}" />
										</div>
										<c:if test="${Problem.numVariables > 1}">
											<div class="col-xs-1" style="text-align: right;">
												<label class="col-md-offset-2 subfield"><spring:message code="SSP_variableY" /></label>
											</div>
											<div class="col-xs-2 form-group">
												<form:input path="variableY" class="form-control" placeholder="${SSP_placeholder_Y}" />
											</div>
										</c:if>
										<c:if test="${Problem.numVariables > 2}">
											<div class="col-xs-1" style="text-align: right;">
												<label class="col-md-offset-2 subfield"><spring:message code="SSP_variableZ" /></label>
											</div>
											<div class="col-xs-2 form-group">
												<form:input path="variableZ" class="form-control" placeholder="${SSP_placeholder_Z}" />
											</div>
										</c:if>
										
										<div style="text-align: right">
											<button type="submit" class="btn btn-primary">
												<spring:message code="SSP_button_create" />
											</button>
										</div>

									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="row col-xs-3" style="border-right: 1px solid #ccc;">
								<label><spring:message code="SSP_elements" /></label>
								<div class="dropdown dropdownmargin">
									<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
										<spring:message code="SSP_matrices" /> <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" style="text-align: right; max-width: 84%;">
										<div class="btn-group btn-group" style="margin-right: 2%;">
											<a class="btn btn-default" id="1x2-matrix"><spring:message code="SSP_1x2" /></a><a class="btn btn-default" id="1x3-matrix"><spring:message code="SSP_1x3" /></a><a class="btn btn-default" id="1x4-matrix"><spring:message code="SSP_1x4" /></a>
										</div>
										<div class="btn-group btn-group" style="margin-right: 2%;">
											<a class="btn btn-default" id="2x1-matrix"><spring:message code="SSP_2x1" /></a> <a class="btn btn-default" id="2x2-matrix"><spring:message code="SSP_2x2" /></a> <a class="btn btn-default" id="2x3-matrix"><spring:message code="SSP_2x3" /></a><a
												class="btn btn-default" id="2x4-matrix"><spring:message code="SSP_2x4" /></a>
										</div>
										<div class="btn-group btn-group" style="margin-right: 2%;">
											<a class="btn btn-default" id="3x1-matrix"><spring:message code="SSP_3x1" /></a> <a class="btn btn-default" id="3x2-matrix"><spring:message code="SSP_3x2" /></a> <a class="btn btn-default" id="3x3-matrix"><spring:message code="SSP_3x3" /></a> <a
												class="btn btn-default" id="3x4-matrix"><spring:message code="SSP_3x4" /></a>
										</div>
									</ul>
								</div>
								<div class="dropdown dropdownmargin">
									<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
										<spring:message code="SSP_formulas" /> <span class="caret"></span>
									</button>
									<ul id="formula-dropdown" class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
										<li role="presentation"><a role="menuitem" tabindex="-1" id="1-formula"><spring:message code="SSP_1_formula" /></a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1" id="2-formula"><spring:message code="SSP_2_formula" /></a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1" id="3-formula"><spring:message code="SSP_3_formula" /></a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1" id="resta-formula"><spring:message code="SSP_resta_formula" /></a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1" id="suma-formula"><spring:message code="SSP_suma_formula" /></a></li>
									</ul>
								</div>
							</div>
							<div class="row col-xs-9" style="margin-left: 15px;">
								<!-- List with handle -->
								<div id="stepsList" class="list-group">
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- /.container -->
	
	<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.11.2.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jquery/sortable/sortable.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jquery/validation/jquery.validate.min.js" />"></script>
	
	<script type="text/x-mathjax-config">
		MathJax.Ajax.config.path["Contrib"] = "//cdn.mathjax.org/mathjax/contrib";
		MathJax.Hub.Config({
			extensions : [ "[Contrib]/forminput/forminput.js" ],
			styles : {".MathJax_Input" : {"margin" : "0 2px"},".red_background" : {"background-color" : "#F88"}}
		});
	</script>
	<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
	<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap-3.3.2.min.js" />"></script>

	<script>
		// List with handle
		Sortable.create(stepsList, {
			handle : '.glyphicon-sort',
			animation : 150
		});
		//Delete row
		$(document).ready(function() {
			$("#stepsList").on('click', '.glyphicon-trash', function() {
				$(this).closest(".list-group-item").remove();
			});
			var cnt = 1;
			var divo = "<div class='list-group-item'><div class='row formulabuttons'> <span class='glyphicon glyphicon-sort' aria-hidden='true' style='color: #2e6da4; cursor: move; margin-right: 15px;'></span><span class='glyphicon glyphicon-trash' aria-hidden='true' style='color: #d9534f; cursor: pointer; margin-right: 15px;'></span></div>";
			var divc = "</div></div>";
			var finput = "<input type='text' value='' class='form-control formulainput' maxlength='35' placeholder='<spring:message code='SSP_placeholder_formula' />'>";
			jQuery("#1-formula").click(function(e) {
				var divf1 = "<div id=1form_row" + cnt + ">";
				$(divo + divf1 + finput + divc).hide().appendTo("#stepsList").slideDown("slow");
				cnt = cnt + 1;
				e.preventDefault();
			});
			jQuery("#2-formula").click(function(e) {
				var divf2 = "<div id=2form_row" + cnt + ">";
				$(divo + divf2 + finput + finput + divc).hide().appendTo("#stepsList").slideDown("slow");
				cnt = cnt + 1;
				e.preventDefault();
			});
			jQuery("#3-formula").click(function(e) {
				var divf3 = "<div id=3form_row" + cnt + ">";
				$(divo + divf3 + finput + finput + finput + divc).hide().appendTo("#stepsList").slideDown("slow");
				cnt = cnt + 1;
				e.preventDefault();
			});
			jQuery("#resta-formula").click(function(e) {
				var resta = "<label>-</label>";
				var resultado = "<div style='border-bottom: 1px solid rgb(204, 204, 204); margin-bottom: 1%; width: 39%;'></div>";
				var divrestform = "<div id=restform_row" + cnt + ">";
				$(divo + divrestform + finput + resta + finput + resultado + finput + divc).hide().appendTo("#stepsList").slideDown("slow");
				cnt = cnt + 1;
				e.preventDefault();
			});
			jQuery("#suma-formula").click(function(e) {
				var resta = "<label>+</label>";
				var resultado = "<div style='border-bottom: 1px solid rgb(204, 204, 204); margin-bottom: 1%; width: 39%;'></div>";
				var divrestform = "<div id=sumform_row" + cnt + ">";
				$(divo + divrestform + finput + resta + finput + resultado + finput + divc).hide().appendTo("#stepsList").slideDown("slow");
				cnt = cnt + 1;
				e.preventDefault();
			});
			jQuery("#1x2-matrix").click(function(e) {
				var matrix = "\\begin{pmatrix} {\\FormInput[][form-control-math]{aa" + cnt + "}} & {\\FormInput[][form-control-math]{ab" + cnt + "}} \\end{pmatrix}";
				var divId = "1x2m_row" + cnt; 
				var tmp = $(divo + "<div id='"+ divId +"'>" + matrix +"</div>" + divc).appendTo("#stepsList").hide();
				MathJax.Hub.Queue(["Typeset",MathJax.Hub,divId]);
				MathJax.Hub.Queue(function () {
					$("#"+divId).find(".MathJax_Input").attr('maxlength','9');
						tmp.slideDown("slow");
				});
				cnt = cnt + 1;
				e.preventDefault();
			});
			jQuery("#1x3-matrix").click(function(e) {
				var matrix = "\\begin{pmatrix} {\\FormInput[][form-control-math]{aa" + cnt + "}} & {\\FormInput[][form-control-math]{ab" + cnt + "}} & {\\FormInput[][form-control-math]{ac" + cnt + "}} \\end{pmatrix}";
				var divId = "1x3m_row" + cnt; 
				var tmp = $(divo + "<div id='"+ divId +"'>" + matrix +"</div>" + divc).appendTo("#stepsList").hide();
				MathJax.Hub.Queue(["Typeset",MathJax.Hub,divId]);
				MathJax.Hub.Queue(function () {
					$("#"+divId).find(".MathJax_Input").attr('maxlength','9');
						tmp.slideDown("slow");
				});
				cnt = cnt + 1;
				e.preventDefault();
			});
			jQuery("#1x4-matrix").click(function(e) {
				var matrix = "\\begin{pmatrix} {\\FormInput[][form-control-math]{aa" + cnt + "}} & {\\FormInput[][form-control-math]{ab" + cnt + "}} & {\\FormInput[][form-control-math]{ac" + cnt + "}} & {\\FormInput[][form-control-math]{ad" + cnt + "}} \\end{pmatrix}";
				var divId = "1x4m_row" + cnt; 
				var tmp = $(divo + "<div id='"+ divId +"'>" + matrix +"</div>" + divc).appendTo("#stepsList").hide();
				MathJax.Hub.Queue(["Typeset",MathJax.Hub,divId]);
				MathJax.Hub.Queue(function () {
					$("#"+divId).find(".MathJax_Input").attr('maxlength','9');
						tmp.slideDown("slow");
				});
				cnt = cnt + 1;
				e.preventDefault();
			});
			jQuery("#2x1-matrix").click(function(e) {
				var matrix = "\\begin{pmatrix} {\\FormInput[][form-control-math]{aa" + cnt + "}} \\\\ {\\FormInput[][form-control-math]{ba" + cnt + "}} \\end{pmatrix}";
				var divId = "2x1m_row" + cnt; 
				var tmp = $(divo + "<div id='"+ divId +"'>" + matrix +"</div>" + divc).appendTo("#stepsList").hide();
				MathJax.Hub.Queue(["Typeset",MathJax.Hub,divId]);
				MathJax.Hub.Queue(function () {
					$("#"+divId).find(".MathJax_Input").attr('maxlength','9');
						tmp.slideDown("slow");
				});
				cnt = cnt + 1;
				e.preventDefault();
			});
			jQuery("#2x2-matrix").click(function(e) {
				var matrix = "\\begin{pmatrix} {\\FormInput[][form-control-math]{aa" + cnt + "}} & {\\FormInput[][form-control-math]{ab" + cnt + "}} \\\\ {\\FormInput[][form-control-math]{ba" + cnt + "}} & {\\FormInput[][form-control-math]{bb" + cnt + "}} \\end{pmatrix}";
				var divId = "2x2m_row" + cnt; 
				var tmp = $(divo + "<div id='"+ divId +"'>" + matrix +"</div>" + divc).appendTo("#stepsList").hide();
				MathJax.Hub.Queue(["Typeset",MathJax.Hub,divId]);
				MathJax.Hub.Queue(function () {
				$("#"+divId).find(".MathJax_Input").attr('maxlength','9');
					tmp.slideDown("slow");
				});
				cnt = cnt + 1;
				e.preventDefault();
			});
			jQuery("#2x3-matrix").click(function(e) {
				var matrix = "\\begin{pmatrix} {\\FormInput[][form-control-math]{aa" + cnt + "}} & {\\FormInput[][form-control-math]{ab" + cnt + "}} & {\\FormInput[][form-control-math]{ac" + cnt + "}} \\\\ {\\FormInput[][form-control-math]{ba" + cnt + "}} & {\\FormInput[][form-control-math]{bb" + cnt + "}} & {\\FormInput[][form-control-math]{bc" + cnt + "}} \\end{pmatrix}";
				var divId = "2x3m_row" + cnt; 
				var tmp = $(divo + "<div id='"+ divId +"'>" + matrix +"</div>" + divc).appendTo("#stepsList").hide();
				MathJax.Hub.Queue(["Typeset",MathJax.Hub,divId]);
				MathJax.Hub.Queue(function () {
					$("#"+divId).find(".MathJax_Input").attr('maxlength','9');
						tmp.slideDown("slow");
				});
				cnt = cnt + 1;
				e.preventDefault();
			});	
			jQuery("#2x4-matrix").click(function(e) {
				var matrix = "\\begin{pmatrix} {\\FormInput[][form-control-math]{aa" + cnt + "}} & {\\FormInput[][form-control-math]{ab" + cnt + "}} & {\\FormInput[][form-control-math]{ac" + cnt + "}} & {\\FormInput[][form-control-math]{ad" + cnt + "}} \\\\ {\\FormInput[][form-control-math]{ba" + cnt + "}} & {\\FormInput[][form-control-math]{bb" + cnt + "}} & {\\FormInput[][form-control-math]{bc" + cnt + "}} & {\\FormInput[][form-control-math]{bd" + cnt + "}} \\end{pmatrix}";
				var divId = "2x4m_row" + cnt; 
				var tmp = $(divo + "<div id='"+ divId +"'>" + matrix +"</div>" + divc).appendTo("#stepsList").hide();
				MathJax.Hub.Queue(["Typeset",MathJax.Hub,divId]);
				MathJax.Hub.Queue(function () {
					$("#"+divId).find(".MathJax_Input").attr('maxlength','9');
						tmp.slideDown("slow");
				});
				cnt = cnt + 1;
				e.preventDefault();
			});
			jQuery("#3x1-matrix").click(function(e) {
				var matrix = "\\begin{pmatrix} {\\FormInput[][form-control-math]{aa" + cnt + "}} \\\\ {\\FormInput[][form-control-math]{ba" + cnt + "}} \\\\ {\\FormInput[][form-control-math]{ca" + cnt + "}} \\end{pmatrix}";
				var divId = "3x1m_row" + cnt; 
				var tmp = $(divo + "<div id='"+ divId +"'>" + matrix +"</div>" + divc).appendTo("#stepsList").hide();
				MathJax.Hub.Queue(["Typeset",MathJax.Hub,divId]);
				MathJax.Hub.Queue(function () {
					$("#"+divId).find(".MathJax_Input").attr('maxlength','9');
						tmp.slideDown("slow");
				});
				cnt = cnt + 1;
				e.preventDefault();
			});	
			jQuery("#3x2-matrix").click(function(e) {
				var matrix = "\\begin{pmatrix} {\\FormInput[][form-control-math]{aa" + cnt + "}} & {\\FormInput[][form-control-math]{ab" + cnt + "}} \\\\ {\\FormInput[][form-control-math]{ba" + cnt + "}} & {\\FormInput[][form-control-math]{bb" + cnt + "}} \\\\ {\\FormInput[][form-control-math]{ca" + cnt + "}} & {\\FormInput[][form-control-math]{cb" + cnt + "}} \\end{pmatrix}";
				var divId = "3x2m_row" + cnt; 
				var tmp = $(divo + "<div id='"+ divId +"'>" + matrix +"</div>" + divc).appendTo("#stepsList").hide();
				MathJax.Hub.Queue(["Typeset",MathJax.Hub,divId]);
				MathJax.Hub.Queue(function () {
					$("#"+divId).find(".MathJax_Input").attr('maxlength','9');
						tmp.slideDown("slow");
				});
				cnt = cnt + 1;
				e.preventDefault();
			});	
			jQuery("#3x3-matrix").click(function(e) {
				var matrix = "\\begin{pmatrix} {\\FormInput[][form-control-math]{aa" + cnt + "}} & {\\FormInput[][form-control-math]{ab" + cnt + "}} & {\\FormInput[][form-control-math]{ac" + cnt + "}} \\\\ {\\FormInput[][form-control-math]{ba" + cnt + "}} & {\\FormInput[][form-control-math]{bb" + cnt + "}} & {\\FormInput[][form-control-math]{bc" + cnt + "}} \\\\ {\\FormInput[][form-control-math]{ca" + cnt + "}} & {\\FormInput[][form-control-math]{cb" + cnt + "}} & {\\FormInput[][form-control-math]{cc" + cnt + "}} \\end{pmatrix}";
				var divId = "3x3m_row" + cnt; 
				var tmp = $(divo + "<div id='"+ divId +"'>" + matrix +"</div>" + divc).appendTo("#stepsList").hide();
				MathJax.Hub.Queue(["Typeset",MathJax.Hub,divId]);
				MathJax.Hub.Queue(function () {
					$("#"+divId).find(".MathJax_Input").attr('maxlength','9');
						tmp.slideDown("slow");
				});
				cnt = cnt + 1;
				e.preventDefault();
			});	
			jQuery("#3x4-matrix").click(function(e) {
				var matrix = "\\begin{pmatrix} {\\FormInput[][form-control-math]{aa" + cnt + "}} & {\\FormInput[][form-control-math]{ab" + cnt + "}} & {\\FormInput[][form-control-math]{ac" + cnt + "}} & {\\FormInput[][form-control-math]{ad" + cnt + "}} \\\\ {\\FormInput[][form-control-math]{ba" + cnt + "}} & {\\FormInput[][form-control-math]{bb" + cnt + "}} & {\\FormInput[][form-control-math]{bc" + cnt + "}} & {\\FormInput[][form-control-math]{bd" + cnt + "}} \\\\ {\\FormInput[][form-control-math]{ca" + cnt + "}} & {\\FormInput[][form-control-math]{cb" + cnt + "}} & {\\FormInput[][form-control-math]{cc" + cnt + "}} & {\\FormInput[][form-control-math]{cd" + cnt + "}} \\end{pmatrix}";
				var divId = "3x4m_row" + cnt; 
				var tmp = $(divo + "<div id='"+ divId +"'>" + matrix +"</div>" + divc).appendTo("#stepsList").hide();
				MathJax.Hub.Queue(["Typeset",MathJax.Hub,divId]);
				MathJax.Hub.Queue(function () {
					$("#"+divId).find(".MathJax_Input").attr('maxlength','9');
						tmp.slideDown("slow");
				});
				cnt = cnt + 1;
				e.preventDefault();
			});	
		});
	</script>
	
	
<script type="text/javascript">
	$(document).ready(function () {
		jQuery.validator.addMethod("answer", function(value, element) {
			return this.optional(element) || /^[a-z0-9.,/]+$/i.test(value);
		}, "");

		$("#studentSolveProblemDTO").validate({
			rules: {
				variableX: {
					required: true,
					answer: true,
					maxlength: 30
				},
				variableY: {
					required: true,
					answer: true,
					maxlength: 30
				},
				variableZ: {
					required: true,
					answer: true,
					maxlength: 30
				}
			},
			messages: {
				variableX: {
					required: "<spring:message code='SSP_validation_required' />",
					maxlength: "<spring:message code='SSP_validation_lenght' />",
					answer: "<spring:message code='SSP_validation_character' />"
				},
				variableY: {
					required: "<spring:message code='SSP_validation_required' />",
					maxlength: "<spring:message code='SSP_validation_lenght' />",
					answer: "<spring:message code='SSP_validation_character' />"
				},
				variableZ: {
					required: "<spring:message code='SSP_validation_required' />",
					maxlength: "<spring:message code='SSP_validation_lenght' />",
					answer: "<spring:message code='SSP_validation_character' />"
				}
			},
			highlight: function(element) {
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight: function(element) {
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			},
			submitHandler: function(form) {
				serializeSteps();
				form.submit();
			}
		});

	});
	function serializeSteps(){
		$("#steps-inputs").empty();
		$("#stepsList").find('.list-group-item').each(function(i, obj) {
			 var serialized = $(":input(:button, :submit)", this).map(function(i, el) {return el.value;}).get().join("|");
			 var iddiv = $(this).find("div:eq(1)").attr("id");
			 var valuestep = iddiv + "|" + serialized;
			 var inputlist = "<input type='text' hidden='true' value='" + valuestep + "' name='stepsList[" + i + "]' id='stepsList" + i + "'>";
			 $(inputlist).appendTo("#steps-inputs");
		});
	}
</script>
</body>
</html>
