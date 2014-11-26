<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="utf-8" />
<title><fmt:message key="title" /></title>
<style>
.error {
	color: red;
}
</style>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<link id="bs-css" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" href="resources/bootstrap/datepicker3.css" type="text/css" />

<script type="text/javascript" src="<c:url value="/resources/bootstrap/bootstrap-datepicker.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/locale/bootstrap-datepicker.es.js" />"></script>
</head>
<body>


	<h1>In</h1>
	<spring:message code="prueba" />
	<p>
		<fmt:message key="greeting" />
		<c:out value="${now}" />
	</p>

	<spring:message code="CP_idMethodSelect" var="CP_idMethodSelect" />
	<spring:message code="CP_numVariablesSelect" var="CP_numVariablesSelect" />


	<form:form method="post" commandName="createProblem">
		<table>
			<tr>
				<td align="right" width="20%">idMethod:</td>
				<td width="40%"><form:select path="idMethod">
						<form:option value="0" label="${CP_idMethodSelect}"></form:option>
						<form:options items="${methodList}" itemLabel="methodName" itemValue="idMethods" />
					</form:select></td>
				<td width="40%"><form:errors path="idMethod" cssClass="error" /></td>
			</tr>
			<tr>
				<td align="right" width="20%">numVariables:</td>
				<td width="40%"><form:select path="numVariables">
						<form:option value="0" label="${CP_numVariablesSelect}"></form:option>
						<form:options items="${numVariablesList}" />
					</form:select></td>
				<td width="40%"><form:errors path="numVariables" cssClass="error" /></td>
			</tr>
			<tr>
				<td align="right" width="20%">Ecuations:</td>
				<td width="40%">
					<div class="span5 col-md-5" id="equations">
						<spring:message code="CP_equation1" />
						<form:input path="equation1" />
						<br />
						<spring:message code="CP_equation2" />
						<form:input path="equation2" />
						<br />
						<spring:message code="CP_equation3" />
						<form:input path="equation3" />
					</div>
				</td>
				<td width="40%"><form:errors path="equation1" cssClass="error" /></td>
			</tr>
			<tr>
				<td align="right" width="20%">initDate:</td>
				<td width="40%">
					<div class="span5 col-md-5" id="sandbox-container-initDate">
						<div class="input-group date">
							<form:input path="initDate" type="text" class="form-control" placeholder="dd/mm/yyyy" />
							<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
						</div>
					</div>
				</td>
				<td width="40%"><form:errors path="initDate" cssClass="error" /></td>
			</tr>
			<tr>
				<td align="right" width="20%">endDate:</td>
				<td width="40%">
					<div class="span5 col-md-5" id="sandbox-container-endDate">
						<div class="input-group date">
							<form:input path="endDate" type="text" class="form-control" placeholder="dd/mm/yyyy" />
							<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
						</div>
					</div>
				</td>
				<td width="40%"><form:errors path="endDate" cssClass="error" /></td>
			</tr>
			<tr>
				<td align="right" width="20%"><spring:message code="CP_uniqueAnswerCheckbox" /></td>
				<td width="40%"><form:checkbox path="uniqueAnswer" /></td>
				<td width="40%"><form:errors path="uniqueAnswer" cssClass="error" /></td>
			</tr>
			<tr>
				<td align="right" width="20%"><spring:message code="CP_solutionCheckbox" /></td>
				<td width="40%"><form:checkbox path="solutionCheck" /></td>
				<td width="40%"><form:errors path="solutionCheck" cssClass="error" /></td>
			</tr>
			<tr>
				<td align="right" width="20%"></td>
				<td width="40%">
					<div class="span5 col-md-5" id="equations">
						<spring:message code="CP_variableX" />
						<form:input path="variableX" disabled="true" />
						<br />
						<spring:message code="CP_variableY" />
						<form:input path="variableY" disabled="true" />
						<br />
						<spring:message code="CP_variableZ" />
						<form:input path="variableZ" disabled="true" />
					</div>
				</td>
				<td width="40%"><form:errors path="variableX" cssClass="error" /></td>
			</tr>
		</table>
		<br />
		<input type="submit" value="Execute">
	</form:form>
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
