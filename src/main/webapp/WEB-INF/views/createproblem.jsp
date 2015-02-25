<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="CP_title" /></title>
<style>
.error {
	color: red;
}

body {
	padding-top: 50px;
}

.starter-template {
	padding: 40px 15px;
	text-align: center;
}

.alert_eq {
	padding: 6px 18px;
	margin-bottom: 3px;
	border: 1px solid transparent;
	border-radius: 4px;
	float: left;
	max-width: 32%;
}

.body_width_eq {
	max-width: 900px;
}

.row {
	margin-right: 0px !important;
}

.subrow{
	margin-left: 3%; 
	margin-right: -3%;
	margin-bottom: 12px;
}
</style>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<link id="bs-css" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="resources/bootstrap/datepicker3.css" type="text/css" />

<script type="text/javascript" src="<c:url value="/resources/bootstrap/bootstrap-datepicker.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/locale/bootstrap-datepicker.es.js" />"></script>

</head>
<body>
	<spring:message code="CP_idMethodSelect" var="CP_idMethodSelect" />
	<spring:message code="CP_numVariablesSelect" var="CP_numVariablesSelect" />

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Create Equations++</a>
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
		<div class="starter-template">
			<h1 class="page-header">Create Problem++</h1>
			<div class="panel panel-default">
				<div class="panel-heading">Choose the options to create a problem:++</div>
				<form:form method="post" commandName="createProblem" style="text-align:left; margin-left:15px;">
					<div class="form-group">
						<div class="row col-xs-12">
							<label>idMethods:</label>
						</div>
						<div class="row">
							<div class="col-xs-8">
								<form:select path="idMethod" class="form-control">
									<form:option value="0" label="${CP_idMethodSelect}"></form:option>
									<form:options items="${methodList}" itemLabel="methodName" itemValue="idMethods" />
								</form:select>
							</div>
							<div>
								<form:errors path="idMethod" cssClass="alert_eq alert-danger" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row col-xs-12">
							<label>numVariables:</label>
						</div>
						<div class="row">
							<div class="col-xs-8">
								<form:select path="numVariables" class="form-control">
									<form:option value="0" label="${CP_numVariablesSelect}"></form:option>
									<form:options items="${numVariablesList}" />
								</form:select>
							</div>
							<div>
								<form:errors path="numVariables" cssClass="alert_eq alert-danger" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row col-xs-12">
							<label>equations:</label>
						</div>
						<div class="row">
							<div class="col-xs-1 subrow">
								<label class="col-md-offset-2 subfield"><spring:message code="CP_equation1" /></label>
							</div>
							<div class="col-xs-7">
								<form:input path="equation1" class="form-control" />
							</div>
							<div>
								<form:errors path="equation1" cssClass="alert_eq alert-danger" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-1 subrow">
								<label class="col-md-offset-2 subfield"><spring:message code="CP_equation2" /></label>
							</div>
							<div class="col-xs-7">
								<form:input path="equation2" class="form-control" />
							</div>
							<div>
								<form:errors path="equation2" cssClass="alert_eq alert-danger" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-1 subrow">
								<label class="col-md-offset-2 subfield"><spring:message code="CP_equation3" /></label>
							</div>
							<div class="col-xs-7">
								<form:input path="equation3" class="form-control" />
							</div>
							<div>
								<form:errors path="equation3" cssClass="alert_eq alert-danger" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row col-xs-12">
							<label>initDate:</label>
						</div>
						<div class="row">
							<div class="col-xs-8">
								<div id="sandbox-container-initDate">
									<div class="input-group date">
										<form:input path="initDate" type="text" class="form-control" placeholder="dd/mm/yyyy" />
										<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
									</div>
								</div>
							</div>
							<div>
								<form:errors path="initDate" cssClass="alert_eq alert-danger" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row col-xs-12">
							<label>endDate:</label>
						</div>
						<div class="row">
							<div class="col-xs-8">
								<div id="sandbox-container-initDate">
									<div class="input-group date">
										<form:input path="endDate" type="text" class="form-control" placeholder="dd/mm/yyyy" />
										<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
									</div>
								</div>
							</div>
							<div>
								<form:errors path="endDate" cssClass="alert_eq alert-danger" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="row">
							<div class="col-xs-8">
								<label class="checkbox-inline"><label><spring:message code="CP_uniqueAnswerCheckbox" /></label><form:checkbox path="uniqueAnswer" /></label>
							</div>
							<div>
								<form:errors path="uniqueAnswer" cssClass="alert_eq alert-danger" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-xs-8">
								<label class="checkbox-inline"><label><spring:message code="CP_solutionCheckbox" /></label><form:checkbox path="solutionCheck" /></label>
							</div>
							<div>
								<form:errors path="solutionCheck" cssClass="alert_eq alert-danger" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-xs-1 subrow">
								<label class="col-md-offset-2 subfield"><spring:message code="CP_variableX" /></label>
							</div>
							<div class="col-xs-7">
								<form:input path="variableX" disabled="true" class="form-control" />
							</div>
							<div>
								<form:errors path="variableX" cssClass="alert_eq alert-danger" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-1 subrow">
								<label class="col-md-offset-2 subfield"><spring:message code="CP_variableY" /></label>
							</div>
							<div class="col-xs-7">
								<form:input path="variableY" disabled="true" class="form-control" />
							</div>
							<div>
								<form:errors path="variableY" cssClass="alert_eq alert-danger" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-1 subrow">
								<label class="col-md-offset-2 subfield"><spring:message code="CP_variableZ" /></label>
							</div>
							<div class="col-xs-7">
								<form:input path="variableZ" disabled="true" class="form-control" />
							</div>
							<div>
								<form:errors path="variableZ" cssClass="alert_eq alert-danger" />
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-default">Create</button>
				</form:form>
				<div class="panel-body"></div>
			</div>

		</div>
	</div>
	<!-- /.container -->

	<script type="text/javascript">
		$('#sandbox-container-initDate .input-group.date').datepicker({
			format : "dd/mm/yyyy",
			weekStart : 1,
			startDate : "-1d",
			todayBtn : "linked",
			language : "es",
			orientation : "top auto",
			autoclose : true,
			todayHighlight : true
		});
		$('#sandbox-container-endDate .input-group.date').datepicker({
			format : "dd/mm/yyyy",
			weekStart : 1,
			startDate : "new Date()",
			todayBtn : "linked",
			language : "es",
			orientation : "top auto",
			autoclose : true,
			todayHighlight : true
		});
		$(document).ready(function() {
			$("#numVariables").change(function() {
				$("select option:selected").each(function() {
					if ($(this).prop("value") == "2") {
						$('#equation1').removeAttr("disabled");
						$('#equation2').removeAttr("disabled");
						$('#equation3').prop('disabled', true);
					} else if ($(this).prop("value") == "3") {
						$('#equation1').removeAttr("disabled");
						$('#equation2').removeAttr("disabled");
						$('#equation3').removeAttr("disabled");
					} else {
						$('#equation1').prop('disabled', true);
						$('#equation2').prop('disabled', true);
						$('#equation3').prop('disabled', true);
					}
				});
			}).change();
			$("#solutionCheck1").click(function() {
				$("#variableX").attr("disabled", !this.checked);
				$("#variableY").attr("disabled", !this.checked);
				if ($('#numVariables').find(":selected").text() == "3") {
					$("#variableZ").attr("disabled", !this.checked);
				}
			});
		});
	</script>
</body>
</html>
