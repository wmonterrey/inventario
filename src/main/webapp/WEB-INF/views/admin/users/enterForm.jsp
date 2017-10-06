<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!--[if IE 8]> <html class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<jsp:include page="../../fragments/headTag.jsp" />
<!-- BEGIN PAGE LEVEL STYLES -->
<spring:url value="/resources/plugins/select2/select2_conquer.css" var="sel2css" />
<link rel="stylesheet" type="text/css" href="${sel2css}"/>
<spring:url value="/resources/plugins/jquery-multi-select/css/multi-select.css" var="jqmscss" />
<link rel="stylesheet" type="text/css" href="${jqmscss}"/>
<!-- END PAGE LEVEL STYLES -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-fixed page-footer-fixed">
<!-- BEGIN HEADER -->
<jsp:include page="../../fragments/bodyHeader.jsp" />
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
<jsp:include page="../../fragments/bodyNavigation.jsp" />
<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN STYLE CUSTOMIZER -->
			<jsp:include page="../../fragments/bodyCustomizer.jsp" />
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					<spring:message code="title" /> <small><spring:message code="adminusers" /></small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/users/" htmlEscape="true "/>"><spring:message code="adminusers" /></a> <i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/users/editUser/${user.username}/" htmlEscape="true "/>">${user.username}</a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<spring:url value="/admin/users/{username}/"
				var="usuarioUrl">
				<spring:param name="username" value="${user.username}" />
			</spring:url>
			<c:set var="userUpdated"><spring:message code="user.updated" /></c:set>
			<c:set var="errorProcess"><spring:message code="process.error" /></c:set>
			
			<div class="row">
				<div class="col-md-12">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-user"></i>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body form">
						<form action="#" autocomplete="off" id="edit-user-form" class="form-horizontal">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										<spring:message code="form.errors" />
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="username" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input id="username" name="username" type="text" readonly value="${user.username}" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="userdesc" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input id="completeName" name="completeName" type="text" value="${user.completeName}" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="useremail" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input id="email" name="email" type="text" value="${user.email}" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-envelope"></i>
												</span>
											</div>
										</div>
									</div>
									<div id="divPass" class="form-group password-strength">
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
									<div id="divPassRepeat" class="form-group">
										<label class="control-label col-md-3"><spring:message code="password.repeat" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<input id="confirm_password" name="confirm_password" type="password" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="userroles" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<select multiple="multiple" class="multi-select" id="authorities" name="authorities">
											<c:forEach items="${rolesusuario}" var="auth" varStatus="stat">
												<c:set var="rolesUsuario" value="${stat.first ? '' : rolesUsuario} ${auth.authId.authority}" />
											</c:forEach>
											<c:forEach items="${roles}" var="rol">
												<c:choose> 
													<c:when test="${fn:contains(rolesUsuario, rol.authority)}">
														<option selected value="${rol.authority}"><spring:message code="${rol.authority}" /></option>
													</c:when>
													<c:otherwise>
														<option value="${rol.authority}"><spring:message code="${rol.authority}" /></option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="supercenters" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<select multiple="multiple" class="multi-select" id="centers" name="centers">
											<c:forEach items="${centrosusuario}" var="center" varStatus="stat">
												<c:set var="centrosUsuario" value="${stat.first ? '' : centrosUsuario} ${center.userCenterId.center}" />
											</c:forEach>
											<c:forEach items="${centros}" var="centro">
												<c:choose> 
													<c:when test="${fn:contains(centrosUsuario, centro.centro.centerCode)}">
														<option selected value="${centro.centro.centerCode}"><spring:message code="${centro.centro.centerName}" /></option>
													</c:when>
													<c:otherwise>
														<option value="${centro.centro.centerCode}"><spring:message code="${centro.centro.centerName}" /></option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-6 col-md-6">
										<button id="guardar" type="submit" class="btn btn-success"><spring:message code="save" /></button>
						            	<a href="${fn:escapeXml(usuarioUrl)}" class="btn btn-danger"><spring:message code="end" /></a>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- END PAGE CONTENT -->
		</div>
	</div>
</div>
<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<jsp:include page="../../fragments/bodyFooter.jsp" />
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<jsp:include page="../../fragments/corePlugins.jsp" />
<jsp:include page="../../fragments/bodyUtils.jsp" />
<!-- BEGIN PAGE LEVEL PLUGINS -->
<c:choose>
	<c:when test="${cookie.invLang.value == null}">
		<c:set var="lenguaje" value="es"/>
	</c:when>
	<c:otherwise>
		<c:set var="lenguaje" value="${cookie.invLang.value}"/>
	</c:otherwise>
</c:choose>
<spring:url value="/resources/plugins/select2/select2.min.js" var="Select2" />
<script type="text/javascript" src="${Select2}"></script>
<spring:url value="/resources/plugins/jquery-multi-select/js/jquery.multi-select.js" var="jQueryMultiSelect" />
<script type="text/javascript" src="${jQueryMultiSelect}"></script>
<spring:url value="/resources/plugins/jquery-validation/dist/jquery.validate.min.js" var="jQValidation" />
<script type="text/javascript" src="${jQValidation}"></script>
<spring:url value="/resources/plugins/jquery-validation/dist/additional-methods.min.js" var="jQValidationAdd" />
<script type="text/javascript" src="${jQValidationAdd}"></script>
<!-- jQuery Password Meter-->
<spring:url value="/resources/plugins/jquery.pwstrength.bootstrap/src/pwstrength.js" var="pwStrength" />
<script src="${pwStrength}" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/users/process-user.js" var="editUserScript" />
<script src="${editUserScript}" type="text/javascript"></script>
<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>				
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/plugins/select2/select2_locale_{language}.js" var="Select2Loc">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>				
<script src="${Select2Loc}"></script>
<spring:url value="/admin/users/saveUser" var="saveUserUrl"></spring:url>
<spring:url value="/admin/users/" var="usuarioUrl"></spring:url>
<c:set var="successmessage"><spring:message code="process.success" /></c:set>
<c:set var="errormessage"><spring:message code="process.errors" /></c:set>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    $(function () {
    	$("li.admin").removeClass("admin").addClass("active");
        $("li.adminusers").removeClass("adminusers").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros = {saveUserUrl: "${saveUserUrl}", successmessage: "${successmessage}",
				errormessage: "${errormessage}", selectMessage: "${selectMessage}"
				, editando: "${editando}", agregando:"${agregando}", usuarioUrl: "${usuarioUrl}" 
		};
		EditUser.init(parametros);
	});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>