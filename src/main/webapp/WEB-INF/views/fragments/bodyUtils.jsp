<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<spring:url value="/users/checkcredential" var="checkCredentialUrl"></spring:url>
<spring:url value="/users/checkcenter" var="checkCenterUrl"></spring:url>
<spring:url value="/logout"	var="logout"/>
<spring:url value="/keepsession" var="keepsession"/>
<spring:url value="/users/forcechgpass" var="forcechgpass"/>
<spring:url value="/users/center" var="forcechgcenter"/>
<c:set var="notif"><spring:message code="not" /></c:set>
<c:set var="sessionExpiring"><spring:message code="session.expiring" /></c:set>
<c:set var="sessionExpiringTime"><spring:message code="session.expiring.time" /></c:set>
<c:set var="sessionExpiringConfirm"><spring:message code="session.expiring.confirm" /></c:set>
<c:set var="seconds"><spring:message code="seconds" /></c:set>
<c:set var="keepSession"><spring:message code="session.keep" /></c:set>
<c:set var="logoutSession"><spring:message code="logout" /></c:set>
<c:set var="checkCredentialUser"><sec:authentication property="principal.username" /></c:set>
<spring:url value="/resources/scripts/users/check-credentials.js" var="checkCredential" />
<script src="${checkCredential}" type="text/javascript"></script>
<spring:url value="/resources/scripts/users/check-center.js" var="checkCenter" />
<script src="${checkCenter}" type="text/javascript"></script>
<spring:url value="/resources/scripts/idle-timeout.js" var="idleTimeOutJs" />
<script src="${idleTimeOutJs}" type="text/javascript"></script>
<script>
	jQuery(document).ready(function() {
		// initialize session timeout settings
		var parametros = {logoutUrl: "${logout}",
				keepSessionUrl: "${keepsession}",
				notif: "${notif}",
				sesionExp:"${sessionExpiring}",
				sesionExpTime:"${sessionExpiringTime}",
				sesionExpConf:"${sessionExpiringConfirm}",
				seconds:"${seconds}",
				keepsession:"${keepSession}",
				logoutsession:"${logoutSession}"
				};
		// initialize check credentials settings
		var parametrosCred = {
				url:"${checkCredentialUrl}",
				urlChgPass:"${forcechgpass}",
				username:"${checkCredentialUser}"
				};
		// initialize check center settings
		var parametrosCenter = {
				url:"${checkCenterUrl}",
				urlChgCenter:"${forcechgcenter}",
				username:"${checkCredentialUser}"
				};
		CheckCredentials.init(parametrosCred);
		CheckCenter.init(parametrosCenter);
		IdleTimeout.init(parametros);
	});
</script>