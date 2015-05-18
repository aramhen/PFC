<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="FE_title" /></title>

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
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container">
		<div class="center-template">
			<h1 class="page-header">
				<spring:message code="FE_subtitle" />
			</h1>
			<p class="lead"><spring:message code="FE_text" /></p>
		</div>
	</div>
	<!-- /.container -->

	<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.11.2.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap-3.3.2.min.js" />"></script>
</body>
</html>
