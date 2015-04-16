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

.center-template {
	padding: 15px 15px;
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

.subrow {
	margin-left: 3%;
	margin-right: -3%;
	margin-bottom: 12px;
}

.panel-heading {
	margin-bottom: 5px;
}

.font_checkbox_eq {
	font-weight: 700;
}

.margin_chexkbox_eq {
	margin: 0px !important;
}
</style>

<link rel="stylesheet" href="resources/bootstrap/css/bootstrap-3.3.2.min.css" type="text/css" />
<link rel="stylesheet" href="resources/bootstrap/datepicker-plugin/datepicker3.css" type="text/css" />

</head>
<body>
	<!-- Topnav -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only"><spring:message code="Toggle_Navigation" /></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<span class="navbar-brand"><spring:message code="CP_title" /></span>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="home.htm"><spring:message code="Home" /></a></li>
					<li><a href="listproblem.htm"><spring:message code="LP_title" /></a></li>
					<li><a href="listanswer.htm"><spring:message code="LA_title" /></a></li>
					<li class="active"><a href="createproblem.htm"><spring:message code="CP_title" /></a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	
	<spring:message code="CP_idMethodSelect" var="CP_idMethodSelect" />
	<spring:message code="CP_numVariablesSelect" var="CP_numVariablesSelect" />

	<div class="container body_width_eq">
		<div class="center-template">
			<h1 class="page-header"><spring:message code="CP_title" /></h1>
			<div class="panel panel-default">
				<div class="panel-heading"><spring:message code="CP_subtitle" /></div>
				<form:form method="post" commandName="createProblemDTO" style="text-align:left; margin-left:15px;">
					<div class="form-group">
						<div class="row col-xs-12">
							<label><spring:message code="CP_problemTitle" /></label>
						</div>
						<div class="row">
							<div class="col-xs-8">
								<form:input path="title" class="form-control" />
							</div>
							<div>
								<form:errors path="title" cssClass="alert_eq alert-danger" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row col-xs-12">
							<label><spring:message code="CP_idMethods" /></label>
						</div>
						<div class="row">
							<div class="col-xs-8">
								<form:select path="idMethod" class="form-control">
									<form:option value="0" label="${CP_idMethodSelect}"></form:option>
									<form:options items="${methodList}" itemLabel="name" itemValue="idMethods" />
								</form:select>
							</div>
							<div>
								<form:errors path="idMethod" cssClass="alert_eq alert-danger" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row col-xs-12">
							<label><spring:message code="CP_numVariables" /></label>
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
							<label><spring:message code="CP_equations" /></label>
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
							<label><spring:message code="CP_InitDate" /></label>
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
							<label><spring:message code="CP_EndDate" /></label>
						</div>
						<div class="row">
							<div class="col-xs-8">
								<div id="sandbox-container-endDate">
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
								<div class="checkbox margin_chexkbox_eq">
									<label> <form:checkbox path="uniqueAnswer" /><span class="font_checkbox_eq"><spring:message code="CP_uniqueAnswerCheckbox" /></span></label>
								</div>
							</div>
							<div>
								<form:errors path="uniqueAnswer" cssClass="alert_eq alert-danger" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-xs-8">
								<div class="checkbox margin_chexkbox_eq" id="solutionCheckdiv">
									<label> <form:checkbox path="solutionCheck" /><span class="font_checkbox_eq"><spring:message code="CP_solutionCheckbox" /></span></label>
								</div>
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
								<form:input path="variableX" class="form-control" />
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
								<form:input path="variableY" class="form-control" />
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
								<form:input path="variableZ" class="form-control" />
							</div>
							<div>
								<form:errors path="variableZ" cssClass="alert_eq alert-danger" />
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-default"><spring:message code="CP_button_create" /></button>
				</form:form>
				<div class="panel-body"></div>
			</div>
		</div>
	</div>
	<!-- /.container -->

	<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.11.2.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap-3.3.2.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/bootstrap/datepicker-plugin/bootstrap-datepicker.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/bootstrap/datepicker-plugin/locale/bootstrap-datepicker.es.js" />"></script>

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
				if ($(this).prop("value") == "1") {
					$('#equation1').removeAttr("disabled");
					$('#equation2').prop('disabled', true);
					$('#equation3').prop('disabled', true);
					$('#solutionCheckdiv').removeClass("disabled");
					$("#solutionCheck1").removeAttr("disabled");
					$("#variableY").attr("disabled", true);
					$("#variableZ").attr("disabled", true);
				}else if ($(this).prop("value") == "2") {
					$('#equation1').removeAttr("disabled");
					$('#equation2').removeAttr("disabled");
					$('#equation3').prop('disabled', true);
					$('#solutionCheckdiv').removeClass("disabled");
					$("#solutionCheck1").removeAttr("disabled");
					if ($('#solutionCheck1').is(":checked")) {
						$('#variableY').removeAttr("disabled");
					}
					$("#variableZ").attr("disabled", true);
				} else if ($(this).prop("value") == "3") {
					$('#equation1').removeAttr("disabled");
					$('#equation2').removeAttr("disabled");
					$('#equation3').removeAttr("disabled");
					$('#solutionCheckdiv').removeClass("disabled");
					$("#solutionCheck1").removeAttr("disabled");
					if ($('#solutionCheck1').is(":checked")) {
						$('#variableY').removeAttr("disabled");
						$('#variableZ').removeAttr("disabled");
					}
				} else {
					$('#equation1').prop('disabled', true);
					$('#equation2').prop('disabled', true);
					$('#equation3').prop('disabled', true);
					$("#solutionCheck1").prop('checked', false);
					$("#variableX").prop("disabled", true);
					$("#variableY").prop("disabled", true);
					$("#variableZ").prop("disabled", true);
					$("#solutionCheckdiv").addClass("disabled");
					$("#solutionCheck1").prop("disabled", true);					
				}
			}).change();
			$("#solutionCheck1").click(function() {
				var selected = parseInt($('#numVariables').find(":selected").text());
				$("#variableX").prop("disabled", !this.checked);
				if(selected > 1){
					$("#variableY").prop("disabled", !this.checked);
				}
				if (selected > 2) {
					$("#variableZ").prop("disabled", !this.checked);
				}
			});
		});
	</script>
</body>
</html>
