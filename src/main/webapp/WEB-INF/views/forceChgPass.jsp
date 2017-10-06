<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!--[if IE 8]> <html class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<jsp:include page="fragments/headTag.jsp" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
<spring:url value="/users/chgPass" var="chgPassUrl"></spring:url>
<spring:url value="/logout"	var="logoutUrl"/>
<c:set var="passUpdated"><spring:message code="pass.updated" /></c:set>
<c:set var="errorProcess"><spring:message code="process.error" /></c:set>
<c:set var="userName"><sec:authentication property="principal.username" /></c:set>
<c:set var="notif"><spring:message code="not" /></c:set>
<c:set var="sessionExpiring"><spring:message code="session.expiring" /></c:set>
<c:set var="sessionExpiringTime"><spring:message code="session.expiring.time" /></c:set>
<c:set var="sessionExpiringConfirm"><spring:message code="session.expiring.confirm" /></c:set>
<c:set var="seconds"><spring:message code="seconds" /></c:set>
<c:set var="keepSession"><spring:message code="session.keep" /></c:set>
<c:set var="logoutSession"><spring:message code="logout" /></c:set>
<h3 align="center" class="page-title">
	<spring:message code="login.accountLocked" />
</h3>
<div class="row">
	<div class="col-md-3">
	</div>
	<div class="col-md-6">
		<div class="portlet">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-user"></i><spring:message code="changepass" />
				</div>
			</div>
			<div class="portlet-body form">
			<form action="#" autocomplete="off" id="chg-pass-form" class="form-horizontal">
					<div class="form-header">
						<p align="center">
							<spring:message code="credentials.expired" />
						</p>
					</div>
					<div class="form-body">
						<div class="alert alert-danger display-hide">
							<button class="close" data-close="alert"></button>
							<spring:message code="form.errors" />
						</div>
						<div class="form-group">
							<label class="control-label col-md-3"><spring:message code="login.username" />
							<span class="required">
								 *
							</span>
							</label>
							<div class="col-md-5">
								<input id="username" name="username" type="text" readonly value="${userName}" class="form-control"/>
							</div>
						</div>
						
						<div class="form-group password-strength">
							<label class="control-label col-md-3"><spring:message code="login.password" />
							<span class="required">
								 *
							</span>
							</label>
							<div class="col-md-5">
								<input id="password" name="password" type="password" class="form-control"/>
								<span class="help-block">
									 <spring:message code="Pattern.password.format" />
								</span>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3"><spring:message code="password.repeat" />
							<span class="required">
								 *
							</span>
							</label>
							<div class="col-md-5">
								<input id="confirm_password" name="confirm_password" type="password" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-6 col-md-6">
							<button id="guardar" type="submit" class="btn btn-success"><spring:message code="save" /></button>
			            	<a href="${fn:escapeXml(logoutUrl)}" class="btn btn-danger"><spring:message code="cancel" /></a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="col-md-3">
	</div>
</div>
<!-- END PAGE CONTENT -->
		
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<jsp:include page="fragments/corePlugins.jsp" />
<!-- BEGIN PAGE LEVEL PLUGINS -->
<spring:url value="/resources/plugins/jquery-validation/dist/jquery.validate.min.js" var="jQValidation" />
<script type="text/javascript" src="${jQValidation}"></script>
<spring:url value="/resources/plugins/jquery-validation/dist/additional-methods.min.js" var="jQValidationAdd" />
<script type="text/javascript" src="${jQValidationAdd}"></script>
<spring:url value="/resources/scripts/idle-timeout.js" var="idleTimeOutJs" />
<script src="${idleTimeOutJs}" type="text/javascript"></script>

<!-- jQuery Password Meter-->
<spring:url value="/resources/plugins/jquery.pwstrength.bootstrap/src/pwstrength.js" var="pwStrength" />
<script src="${pwStrength}" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/users/chgpass-user.js" var="chgPassScript" />
<script src="${chgPassScript}" type="text/javascript"></script>
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
		var parameters = {chgPassUrl: "${chgPassUrl}",
				usuarioUrl: "${logoutUrl}",
				};
		ChgPass.init(parameters);
		$('#password').focus();
		// initialize session timeout settings
		var parametros = {logoutUrl: "${logoutUrl}",
				keepSessionUrl: "${keepsession}",
				notif: "${notif}",
				sesionExp:"${sessionExpiring}",
				sesionExpTime:"${sessionExpiringTime}",
				sesionExpConf:"${sessionExpiringConfirm}",
				seconds:"${seconds}",
				keepsession:"${keepSession}",
				logoutsession:"${logoutSession}"
				};
		IdleTimeout.init(parametros);
	});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>