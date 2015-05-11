<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="SSP_title" /></title>

<style>
body {
	padding-top: 50px;
}

.center-template {
	padding: 15px 15px;
	text-align: center;
}

.dropdown-margin {
	margin-bottom: 15px;
}
</style>

<link rel="stylesheet" href="resources/bootstrap/css/bootstrap-3.3.2.min.css" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.11.2.min.js" />"></script>


<!-- Latest Sortable -->
<script src="http://rubaxa.github.io/Sortable/Sortable.js"></script>
<script type="text/x-mathjax-config">
  MathJax.Hub.Config({
    tex2jax: {
      inlineMath: [["$","$"],["\\(","\\)"]]
    }
  });
  MathJax.Hub.Config({showMathMenu: false});

</script>
<script type="text/javascript" src="//cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS_HTML-full"></script>
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

	<div class="container body_width_eq">

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

	<div class="container body_width_eq">
		<div class="center-template">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="SSP_form_header" />
				</div>
				<form:form method="post" commandName="studentSolveProblemDTO" class="form-horizontal" style="margin-left:28px;">
					<div class="panel-body" style="text-align: left;">
						<div class="row">
							<div class="form-group">
								<div class="row col-xs-12" style="margin-left: 0px">
									<label>Introduzca la solución:</label>
								</div>
								<div class="form-group">
									<div class="row" style="margin-right: 60px">
										<div class="col-xs-1" style="text-align: right;">
											<label class="col-md-offset-2 subfield"><spring:message code="CP_variableX" /></label>
										</div>
										<div class="col-xs-2">
											<form:input path="variableX" class="form-control" />
										</div>
										<div class="col-xs-1" style="text-align: right;">
											<label class="col-md-offset-2 subfield"><spring:message code="CP_variableY" /></label>
										</div>
										<div class="col-xs-2">
											<form:input path="variableY" class="form-control" />
										</div>
										<div class="col-xs-1" style="text-align: right;">
											<label class="col-md-offset-2 subfield"><spring:message code="CP_variableZ" /></label>
										</div>
										<div class="col-xs-2">
											<form:input path="variableZ" class="form-control" />
										</div>
										<div style="text-align: right">
											<button type="submit" class="btn btn-primary">
												<spring:message code="CP_button_create" />
											</button>
										</div>

									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="row col-xs-3" style="border-right: 1px solid #ccc;">
								<label>Inserta los elementos necesarios de la lista de abajo:</label>
								<div class="dropdown dropdown-margin">
									<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
										Matrices <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" style="text-align: center;">
										<div class="btn-group btn-group-vertical" style="vertical-align: bottom">
											<a class="btn btn-default" href="#">1x2</a><a class="btn btn-default" href="#">1x3</a><a class="btn btn-default" href="#">1x4</a>
										</div>
										<div class="btn-group btn-group-vertical">
											<a class="btn btn-default" href="#">2x1</a> <a class="btn btn-default" href="#">2x2</a> <a class="btn btn-default" href="#">2x3</a><a
												class="btn btn-default" href="#">2x4</a>
										</div>
										<div class="btn-group btn-group-vertical">
											<a class="btn btn-default" href="#">3x1</a> <a class="btn btn-default" href="#">3x2</a> <a class="btn btn-default" href="#">3x3</a> <a
												class="btn btn-default" href="#">3x4</a>
										</div>
									</ul>
								</div>
								<div class="dropdown dropdown-margin">
									<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
										Fórmulas <span class="caret"></span>
									</button>
									<ul id="formula-dropdown" class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
										<li role="presentation"><a role="menuitem" tabindex="-1" id="1-formula">1 Fórmula</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1" id="2-formula">2 Fórmulas</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1" id="3-formula">3 Fórmulas</a></li>
									</ul>
								</div>
							</div>
							<div class="row col-xs-9" style="margin-left: 15px;">
								<!-- List with handle -->
								<div id="stepsList" class="list-group">
									<div class="list-group-item">
										<div class="row" style="text-align: right; margin-down: 5px;">
											<span class="glyphicon glyphicon-sort" aria-hidden="true" style="color: #2e6da4; cursor: move; margin-right: 15px;"></span><span
												class="glyphicon glyphicon-trash" aria-hidden="true" style="color: #d9534f; cursor: pointer; margin-right: 15px;"></span>
										</div>
										When $a \ne 0$, there are two solutions to \(ax^2 + bx + c = 0\) and they are $$x = {-b \pm \sqrt{b^2-4ac} \over 2a}.$$ $\begin{matrix} a &
										b \\ c & d \end{matrix}$When $a \ne 0$, there are two solutions to \(ax^2 + bx + c = 0\) and they are $$x = {-b \pm \sqrt{b^2-4ac} \over
										2a}.$$ $\begin{matrix} a & b \\ c & d \end{matrix}$
										<math xmlns="http://www.w3.org/1998/Math/MathML" display="block">  <mrow>    <mover accent="true">      <mrow>        <mo>&#x2207;</mo>      </mrow>      <mrow>        <mo>&#x2192;</mo>      </mrow>    </mover>    <mo>&#xD7;</mo>    <mover
												accent="true">      <mrow>        <mi>F</mi>      </mrow>      <mrow>        <mo>&#x2192;</mo>      </mrow>    </mover>    <mo>=</mo>    <mrow>      <mo>(</mo>      <mfrac>        <mrow>          <mo>&#x2202;</mo>          <msub>            <mrow>              <mi>F</mi>            </mrow>            <mrow>              <mi>z</mi>            </mrow>          </msub>        </mrow>        <mrow>          <mo>&#x2202;</mo>          <mi>y</mi>        </mrow>      </mfrac>      <mo>&#x2212;</mo>      <mfrac>        <mrow>          <mo>&#x2202;</mo>          <msub>            <mrow>              <mi>F</mi>            </mrow>            <mrow>              <mi>y</mi>            </mrow>          </msub>        </mrow>        <mrow>          <mo>&#x2202;</mo>          <mi>z</mi>        </mrow>      </mfrac>      <mo>)</mo>    </mrow>    <mstyle
												mathvariant="bold" mathsize="normal">      <mrow>        <mi>i</mi>      </mrow>    </mstyle>    <mo>+</mo>    <mrow>      <mo>(</mo>      <mfrac>        <mrow>          <mo>&#x2202;</mo>          <msub>            <mrow>              <mi>F</mi>            </mrow>            <mrow>              <mi>x</mi>            </mrow>          </msub>        </mrow>        <mrow>          <mo>&#x2202;</mo>          <mi>z</mi>        </mrow>      </mfrac>      <mo>&#x2212;</mo>      <mfrac>        <mrow>          <mo>&#x2202;</mo>          <msub>            <mrow>              <mi>F</mi>            </mrow>            <mrow>              <mi>z</mi>            </mrow>          </msub>        </mrow>        <mrow>          <mo>&#x2202;</mo>          <mi>x</mi>        </mrow>      </mfrac>      <mo>)</mo>    </mrow>    <mstyle
												mathvariant="bold" mathsize="normal">      <mrow>        <mi>j</mi>      </mrow>    </mstyle>    <mo>+</mo>    <mrow>      <mo>(</mo>      <mfrac>        <mrow>          <mo>&#x2202;</mo>          <msub>            <mrow>              <mi>F</mi>            </mrow>            <mrow>              <mi>y</mi>            </mrow>          </msub>        </mrow>        <mrow>          <mo>&#x2202;</mo>          <mi>x</mi>        </mrow>      </mfrac>      <mo>&#x2212;</mo>      <mfrac>        <mrow>          <mo>&#x2202;</mo>          <msub>            <mrow>              <mi>F</mi>            </mrow>            <mrow>              <mi>x</mi>            </mrow>          </msub>        </mrow>        <mrow>          <mo>&#x2202;</mo>          <mi>y</mi>        </mrow>      </mfrac>      <mo>)</mo>    </mrow>    <mstyle
												mathvariant="bold" mathsize="normal">      <mrow>        <mi>k</mi>      </mrow>    </mstyle>  </mrow></math>
									</div>
									<div class="list-group-item">
										<div class="row" style="text-align: right; margin-down: 5px;">
											<span class="glyphicon glyphicon-sort" aria-hidden="true" style="color: #2e6da4; cursor: move; margin-right: 15px;"></span><span
												class="glyphicon glyphicon-trash" aria-hidden="true" style="color: #d9534f; cursor: pointer; margin-right: 15px;"></span>
										</div>
										You can select text
									</div>
									<div class="list-group-item">
										<div class="row" style="text-align: right; margin-down: 5px;">
											<span class="glyphicon glyphicon-sort" aria-hidden="true" style="color: #2e6da4; cursor: move; margin-right: 15px;"></span><span
												class="glyphicon glyphicon-trash" aria-hidden="true" style="color: #d9534f; cursor: pointer; margin-right: 15px;"></span>
										</div>
										Best of both worlds!
									</div>
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- /.container -->

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
			jQuery("#1-formula").click(function(e) {
				$("<div class='list-group-item'><div class='row' style='text-align: right; margin-down: 5px;'> <span class='glyphicon glyphicon-sort' aria-hidden='true' style='color: #2e6da4; cursor: move; margin-right: 15px;'></span><span class='glyphicon glyphicon-trash' aria-hidden='true' style='color: #d9534f; cursor: pointer; margin-right: 15px;'></span></div>Best of both worlds!</div>")
				.hide().appendTo("#stepsList").slideDown("slow");
				e.preventDefault();
			});
		});
	</script>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap-3.3.2.min.js" />"></script>
</body>
</html>
