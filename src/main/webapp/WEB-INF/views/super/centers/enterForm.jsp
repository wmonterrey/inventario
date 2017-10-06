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
					<spring:message code="supercenters" />
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/super/centers/" htmlEscape="true "/>"><spring:message code="supercenters" /></a> <i class="fa fa-angle-right"></i> <a href="<spring:url value="/super/centers/editCenter/${center.centerCode}/" htmlEscape="true "/>">${center.centerCode}</a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<spring:url value="/super/centers/newCenter" var="newCenterUrl"></spring:url>
			<spring:url value="/super/centers/" var="centerUrl"></spring:url>
			<c:set var="centerCreated"><spring:message code="process.success" /></c:set>
			<c:set var="errorProcess"><spring:message code="process.errors" /></c:set>
			
			<div class="row">
				<div class="col-md-12">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-building"></i>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body form">
						<form action="#" autocomplete="off" id="add-center-form" class="form-horizontal">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										<spring:message code="form.errors" />
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="centerCode" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${center.centerCode}" id="centerCode" name="centerCode" readonly type="text" placeholder="<spring:message code="centerCode" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="centerName" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${center.centerName}" id="centerName" name="centerName" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="centerName" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="centerContact" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${center.centerContact}" id="centerContact" name="centerContact" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="centerContact" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="centerAddress" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${center.centerAddress}" id="centerAddress" name="centerAddress" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="centerAddress" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="centerPhoneNumber" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${center.centerPhoneNumber}" id="centerPhoneNumber" name="centerPhoneNumber" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="centerPhoneNumber" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="centerEmail" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${center.centerEmail}" id="centerEmail" name="centerEmail" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="centerEmail" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="centerObs" />
										<span class="required">
											 
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${center.centerObs}" id="centerObs" name="centerObs" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="centerObs" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="adminusers" />
										</label>
										<div class="col-md-5">
											<select multiple="multiple" class="multi-select" id="users" name="users">
												<c:forEach items="${usuarioscentro}" var="user" varStatus="stat">
													<c:set var="usuariosCentro" value="${stat.first ? '' : usuariosCentro} ${user.userCenterId.username}" />
												</c:forEach>
												<c:forEach items="${usuarios}" var="usuario">
													<c:choose> 
														<c:when test="${fn:contains(usuariosCentro, usuario.username)}">
															<option selected value="${usuario.username}">${usuario.username}</option>
														</c:when>
														<c:otherwise>
															<option value="${usuario.username}">${usuario.username}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="superstudies" />
										</label>
										<div class="col-md-5">
											<select multiple="multiple" class="multi-select" id="studies" name="studies">
												<c:forEach items="${estudioscentro}" var="study" varStatus="stat">
													<c:set var="estudiosCentro" value="${stat.first ? '' : estudiosCentro} ${study.estudioCentroId.estudio}" />
												</c:forEach>
												<c:forEach items="${estudios}" var="estudio">
													<c:choose> 
														<c:when test="${fn:contains(estudiosCentro, estudio.studyCode)}">
															<option selected value="${estudio.studyCode}">${estudio.studyName}</option>
														</c:when>
														<c:otherwise>
															<option value="${estudio.studyCode}">${estudio.studyName}</option>
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
										<a href="${fn:escapeXml(centerUrl)}" class="btn btn-danger"><spring:message code="end" /></a>
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
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/centers/process-center.js" var="centerScript" />
<script src="${centerScript}" type="text/javascript"></script>
<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>				
<script src="${jQValidationLoc}"/></script>
<spring:url value="/resources/plugins/select2/select2_locale_{language}.js" var="Select2Loc">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>				
<script src="${Select2Loc}"/></script>
<!-- END PAGE LEVEL SCRIPTS -->
<spring:url value="/super/centers/saveCenter" var="saveCenterUrl"/>
<c:set var="successmessage"><spring:message code="process.success" /></c:set>
<c:set var="errormessage"><spring:message code="process.errors" /></c:set>
<script>
    $(function () {
    	$("li.super").removeClass("super").addClass("active");
        $("li.supercenters").removeClass("supercenters").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros = {saveCenterUrl: "${saveCenterUrl}",
				successmessage: "${successmessage}",
				errormessage: "${errormessage}"};
		CreateCenter.init(parametros);
		$('#centerName').focus();
	});	
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>