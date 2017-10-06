<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!--

-->
<meta charset="utf-8"/>
<title><spring:message code="title" /> | <spring:message code="heading" /></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<meta name="MobileOptimized" content="320">
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<spring:url value="/resources/plugins/font-awesome/css/font-awesome.min.css" var="fontawesome" />
<link href="${fontawesome}" rel="stylesheet" type="text/css"/>
<spring:url value="/resources/plugins/bootstrap/css/bootstrap.min.css" var="bootstrap" />
<link href="${bootstrap}" rel="stylesheet" type="text/css"/>
<spring:url value="/resources/plugins/uniform/css/uniform.default.css" var="uniform" />
<link href="${uniform}" rel="stylesheet" type="text/css"/>
<spring:url value="/resources/plugins/bootstrap-toastr/toastr.min.css" var="toastcss" />
<link rel="stylesheet" type="text/css" href="${toastcss}"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN THEME STYLES -->
<spring:url value="/resources/css/style-conquer.css" var="styleconquer" />
<link href="${styleconquer}" rel="stylesheet" type="text/css"/>
<spring:url value="/resources/css/style.css" var="style" />
<link href="${style}" rel="stylesheet" type="text/css"/>
<spring:url value="/resources/css/style-responsive.css" var="sresponsive" />
<link href="${sresponsive}" rel="stylesheet" type="text/css"/>
<spring:url value="/resources/css/plugins.css" var="plugins" />
<link href="${plugins}" rel="stylesheet" type="text/css"/>
<spring:url value="/resources/css/pages/tasks.css" var="tasks" />
<link href="${tasks}" rel="stylesheet" type="text/css"/>
<spring:url value="/resources/css/themes/default.css" var="defaults" />
<link href="${defaults}" rel="stylesheet" type="text/css" id="style_color"/>
<spring:url value="/resources/css/custom.css" var="customs" />
<link href="${customs}" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<spring:url value="/resources/img/favicon/lab.png" var="favicon" />
<link rel="shortcut icon" href="${favicon}"/>