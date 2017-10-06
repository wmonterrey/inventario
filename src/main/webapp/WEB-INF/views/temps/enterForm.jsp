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
<jsp:include page="../fragments/headTag.jsp" />
<!-- BEGIN PAGE LEVEL STYLES -->
<spring:url value="/resources/plugins/select2/select2_conquer.css" var="sel2css" />
<link rel="stylesheet" type="text/css" href="${sel2css}"/>
<spring:url value="/resources/plugins/jquery-multi-select/css/multi-select.css" var="jqmscss" />
<link rel="stylesheet" type="text/css" href="${jqmscss}"/>
<spring:url value="/resources/plugins/bootstrap-datepicker/css/datepicker.css" var="dpcss" />
<link rel="stylesheet" type="text/css" href="${dpcss}"/>
<spring:url value="/resources/plugins/bootstrap-datetimepicker/css/datetimepicker.css" var="dtpcss" />
<link rel="stylesheet" type="text/css" href="${dtpcss}"/>

<!-- END PAGE LEVEL STYLES -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-fixed page-footer-fixed">
<!-- BEGIN HEADER -->
<jsp:include page="../fragments/bodyHeader.jsp" />
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
<jsp:include page="../fragments/bodyNavigation.jsp" />
<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN STYLE CUSTOMIZER -->
			<jsp:include page="../fragments/bodyCustomizer.jsp" />
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					<spring:message code="temprecord" />
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/temps/" htmlEscape="true "/>"><spring:message code="temprecord" /></a> <i class="fa fa-angle-right"></i> <a href="<spring:url value="/temps/editTemp/${temp.tempCode}/" htmlEscape="true "/>">${temp.tempCode}</a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<spring:url value="/temps/newTemp" var="newTempUrl"></spring:url>
			<spring:url value="/temps/" var="tempUrl"></spring:url>
			
			<div class="row">
				<div class="col-md-12">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-thermometer-half"></i>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body form">
						<form action="#" autocomplete="off" id="add-temp-form" class="form-horizontal">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										<spring:message code="form.errors" />
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="tempCode" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${temp.tempCode}" id="tempCode" name="tempCode" readonly type="text" placeholder="<spring:message code="tempCode" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="tempEquip" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<select data-placeholder="<spring:message code="select" /> <spring:message code="equipCode" />" name="equipCode" id="equipCode" class="form-control">
												<option value=""></option>
												<c:forEach items="${equipos}" var="equipo">
													<c:choose> 
														<c:when test="${equipo.equipCode eq temp.tempEquip.equipCode}">
															<option selected value="${equipo.equipCode}"><spring:message code="${equipo.equipName}" /></option>
															<c:set var="valorMinimo">${equipo.equipTempMin}</c:set>
															<c:set var="valorMaximo">${equipo.equipTempMax}</c:set>
														</c:when>
														<c:otherwise>
															<option value="${equipo.equipCode}"><spring:message code="${equipo.equipName}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="tempDate" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-3">
											<div class="input-group date form_datetime">
												<input type="text" size="16" readonly class="form-control" value="${temp.tempDate}" name="tempDate" id="tempDate">
												<div class="input-group-btn">
													<button class="btn btn-success date-reset" type="button"><i class="fa fa-times"></i></button>
													<button class="btn btn-success date-set" type="button"><i class="fa fa-calendar"></i></button>
												</div>
											</div>
											<!-- /input-group -->
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="tempTemp" />
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${temp.tempRecord}" id="tempRecord" name="tempRecord" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="tempTemp" />" class="form-control"/>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="fueraRango" /></label>
										<div class="col-md-5">
											<c:choose>
												<c:when test="${temp.fueraRango=='1'.charAt(0)}">
													<input type="checkbox" id="fueraRango" name="fueraRango" checked="checked">
												</c:when>
												<c:otherwise>
													<input type="checkbox" id="fueraRango" name="fueraRango">
												</c:otherwise>
											</c:choose>
											 
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="malEstado" /></label>
										<div class="col-md-5">
											<c:choose>
												<c:when test="${temp.malEstado=='1'.charAt(0)}">
													<input type="checkbox" id="malEstado" name="malEstado" checked="checked">
												</c:when>
												<c:otherwise>
													<input type="checkbox" id="malEstado" name="malEstado">
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<div class="form-group last">
										<label class="control-label col-md-3"><spring:message code="tempObs" />
										<span class="required">
											 
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${temp.tempObs}" id="tempObs" name="tempObs" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="tempObs" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-6 col-md-6">
										<button id="guardar" type="submit" class="btn btn-success"><spring:message code="save" /></button>
										<a href="${fn:escapeXml(tempUrl)}" class="btn btn-danger"><spring:message code="end" /></a>
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
<jsp:include page="../fragments/bodyFooter.jsp" />
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<jsp:include page="../fragments/corePlugins.jsp" />
<jsp:include page="../fragments/bodyUtils.jsp" />
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
<spring:url value="/resources/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js" var="inputmask" />
<script type="text/javascript" src="${inputmask}"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/temps/process-temp.js" var="tempScript" />
<script src="${tempScript}" type="text/javascript"></script>
<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>				
<script src="${jQValidationLoc}"/></script>
<spring:url value="/resources/plugins/select2/select2_locale_{language}.js" var="Select2Loc">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>				
<script src="${Select2Loc}"/></script>
<spring:url value="/resources/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" var="jQDateTime" />
<script src="${jQDateTime}"/></script>
<spring:url value="/resources/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" var="jQDate" />
<script type="text/javascript" src="${jQDate}"></script>
<spring:url value="/resources/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.es.js" var="jQDateTimeLoc"/>
<script src="${jQDateTimeLoc}" charset="UTF-8"/></script>
<spring:url value="/resources/plugins/bootstrap-touchspin/bootstrap.touchspin.js" var="touchspin" />
<script type="text/javascript" src="${touchspin}"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<spring:url value="/temps/saveTemp" var="saveTempUrl"/>
<spring:url value="/admin/equips/equipo" var="getEquipUrl"/>
<c:set var="successmessage"><spring:message code="process.success" /></c:set>
<c:set var="errormessage"><spring:message code="process.errors" /></c:set>
<script>
    $(function () {
    	$("li.temprecord").removeClass("temprecord").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros = {saveTempUrl: "${saveTempUrl}",
				getEquipUrl: "${getEquipUrl}",
				lenguaje: "${lenguaje}",
				successmessage: "${successmessage}",
				errormessage: "${errormessage}",
				minimo:"${valorMinimo}",
				maximo:"${valorMaximo}"};
		CreateTemp.init(parametros);
	});	
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>