<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="H_title" /></title>

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
				<span class="navbar-brand"><spring:message code="H_title" /></span>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="home.htm"><spring:message code="H_title" /></a></li>
					<li><a href="listproblem.htm"><spring:message code="LP_title" /></a></li>
					<li><a href="listanswer.htm"><spring:message code="LA_title" /></a></li>
					<li><a href="createproblem.htm"><spring:message code="CP_title" /></a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container body_width_eq">
		<div class="center-template">
			<h1 class="page-header">
				<spring:message code="H_subtitle" />
				${model.teacherName}
			</h1>
			<p class="lead">
				<spring:message code="H_text" />
			</p>
		</div>
	</div>
	<!-- /.container -->

	<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.11.2.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap-3.3.2.min.js" />"></script>
</body>
</html>
