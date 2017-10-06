<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<jsp:include page="fragments/headTag.jsp" />
<head>
<spring:url value="/resources/css/pages/login.css" var="login" />
<link href="${login}" rel="stylesheet" type="text/css" />
</head>
<!-- BEGIN BODY -->
<body class="login">
	<!-- BEGIN LOGO -->
	<div class="logo hidden-xs">
		<spring:url value="/resources/img/logo-144.png" var="logo" />
		<img src="${logo}" alt="<spring:message code="login.page" />" />
	</div>
	<div class="logo hidden-sm hidden-md hidden-lg">
		<spring:url value="/resources/img/logo-72.png" var="logo" />
		<img src="${logo}" alt="<spring:message code="login.page" />" />
	</div>
	<!-- END LOGO -->
	<!-- BEGIN LOGIN -->
	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form class="login-form"
			action="<c:url value='j_spring_security_check' />" method="post">
			<h3 align="center" class="form-title">
				<spring:message code="title" /><br/>
				<small><spring:message code="heading" /></small>
			</h3>
			<h5 align="center" class="form-title">
				<spring:message  code="login.msg" />
			</h5>
			<c:if test="${not empty error}">
			<div class="alert alert-danger">
			<c:choose>
				<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message=='Bad credentials'}">
					<spring:message  code="login.badCredentials" />
				</c:when>
				<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message=='User account is locked'}">
					<spring:message  code="login.accountLocked" />
				</c:when>
				<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message=='User account has expired'}">
					<spring:message  code="login.accountExpired" />
				</c:when>
				<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message=='User credentials have expired'}">
					<spring:message  code="login.credentialsExpired" />
				</c:when>
				<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message=='User is disabled'}">
					<spring:message  code="login.userDisabled" />
				</c:when>
				<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message=='Maximum sessions of 3 for this principal exceeded'}">
					<spring:message  code="login.maxSessionsOut" />
				</c:when>
				<c:otherwise>
					${SPRING_SECURITY_LAST_EXCEPTION.message}
				</c:otherwise>
			</c:choose>
			</div>
			</c:if>
			<div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9"><spring:message code="login.username"/></label>
				<div class="input-icon">
					<i class="fa fa-user"></i> <input
						class="form-control placeholder-no-fix" type="text"
						autocomplete="off"
						placeholder="<spring:message code="login.username" />"
						name="j_username" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9"><spring:message code="login.password"/></label>
				<div class="input-icon">
					<i class="fa fa-lock"></i> <input
						class="form-control placeholder-no-fix" type="password"
						autocomplete="off"
						placeholder="<spring:message code="login.password" />"
						name="j_password" />
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" type="submit" class="btn btn-info pull-right">
					<spring:message code="login"/></button>
			</div>
			<a href="<spring:url value="/" htmlEscape="true "/>" class="actEng">English</a>|<a href="<spring:url value="/" htmlEscape="true "/>" class="actEsp">Español</a>
		</form>
		<!-- END LOGIN FORM -->
	</div>
	<!-- END LOGIN -->
	<!-- BEGIN COPYRIGHT -->
	<div class="copyright">2016 &copy; <spring:message code="login.footer"/>.</div>
	<!-- END COPYRIGHT -->
	<jsp:include page="fragments/corePlugins.jsp" />
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<spring:url value="/resources/plugins/jquery-validation/dist/jquery.validate.min.js" var="jQueryValidation" />
	<script src="${jQueryValidation}" type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<spring:url value="/resources/scripts/app.js" var="App" />
	<spring:url value="/resources/scripts/login.js" var="Login" />
	<script src="${App}" type="text/javascript"></script>
	<script src="${Login}" type="text/javascript"></script>
	<c:choose>
		<c:when test="${cookie.invLang.value == null}">
			<c:set var="lenguaje" value="es"/>
		</c:when>
		<c:otherwise>
			<c:set var="lenguaje" value="${cookie.invLang.value}"/>
		</c:otherwise>
	</c:choose>
	<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
		<spring:param name="language" value="${lenguaje}" />
	</spring:url>
					
	<script src="${jQValidationLoc}"/></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<script>
		jQuery(document).ready(function() {
			App.init();
			Login.init();
		});
		
		$(".actEng").click(function(){ 
			var d = new Date();
		    d.setTime(d.getTime() + (365*24*60*60*1000));
		    var expires = "expires="+d.toUTCString();
			document.cookie="invLang=en;"+expires+"; path=/inventario/";
			location.reload();
         });
		
		$(".actEsp").click(function(){ 
			var d = new Date();
			d.setTime(d.getTime() + (365*24*60*60*1000));
		    var expires = "expires="+d.toUTCString();
			document.cookie="invLang=es;"+expires+"; path=/inventario/";
			location.reload();
         });
		
		
	</script>
	<!-- END JAVASCRIPTS -->
</body>
</html>