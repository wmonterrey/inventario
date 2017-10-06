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
					<spring:message code="adminboxes" />
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/boxes/" htmlEscape="true "/>"><spring:message code="adminboxes" /></a> <i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/boxes/editBox/${box.boxCode}/" htmlEscape="true "/>">${box.boxCode}</a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<spring:url value="/admin/boxes/newBox" var="newBoxUrl"></spring:url>
			<spring:url value="/admin/boxes/" var="boxUrl"></spring:url>
			<c:set var="boxCreated"><spring:message code="process.success" /></c:set>
			<c:set var="errorProcess"><spring:message code="process.errors" /></c:set>
			
			<div class="row">
				<div class="col-md-12">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cubes"></i>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body form">
						<form action="#" autocomplete="off" id="add-box-form" class="form-horizontal">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										<spring:message code="form.errors" />
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="boxStudy" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<select data-placeholder="<spring:message code="select" /> <spring:message code="boxStudy" />" name="boxStudy" id="boxStudy" class="form-control">
												<option value=""></option>
												<c:forEach items="${estudios}" var="estudio">
													<c:choose> 
														<c:when test="${estudio.estudio.studyCode eq box.boxStudy.studyCode}">
															<option selected value="${estudio.estudio.studyCode}"><spring:message code="${estudio.estudio.studyName}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${estudio.estudio.studyCode}"><spring:message code="${estudio.estudio.studyName}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="boxAlicUse" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<select data-placeholder="<spring:message code="select" /> <spring:message code="boxAlicUse" />" name="boxAlicUse" id="boxAlicUse" class="form-control">
												<option value=""></option>
												<c:forEach items="${usosequipo}" var="uso">
													<c:choose> 
														<c:when test="${uso.catKey eq box.boxAlicUse}">
															<option selected value="${uso.catKey}"><spring:message code="${uso.messageKey}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${uso.catKey}"><spring:message code="${uso.messageKey}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="boxTemp" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<input id="boxTemp" type="text" value="${box.boxTemp}" name="boxTemp" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="boxAlicType" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<select data-placeholder="<spring:message code="select" /> <spring:message code="boxAlicType" />" name="boxAlicType" id="boxAlicType" class="form-control select2" multiple>
												<c:forEach items="${tiposalicuota}" var="tipo">
													<c:choose> 
														<c:when test="${fn:contains(box.boxAlicType, tipo.tipoAlicuota.alicTypeName)}">
															<option selected value="${tipo.tipoAlicuota.alicTypeName}"><spring:message code="${tipo.tipoAlicuota.alicTypeName}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${tipo.tipoAlicuota.alicTypeName}"><spring:message code="${tipo.tipoAlicuota.alicTypeName}" /></option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="rackEquip" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<select data-placeholder="<spring:message code="select" /> <spring:message code="rackEquip" />" name="rackEquip" id="rackEquip" class="form-control">
												<option value=""></option>
												<c:forEach items="${equipos}" var="equipo">
													<c:choose> 
														<c:when test="${equipo.equipCode eq box.boxRack.rackEquip.equipCode}">
															<option selected value="${equipo.equipCode}"><spring:message code="${equipo.equipName}" /></option>
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
										<label class="control-label col-md-3"><spring:message code="boxRack" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<select data-placeholder="<spring:message code="select" /> <spring:message code="boxRack" />" name="boxRack" id="boxRack" class="form-control">
												<option value=""></option>
												<c:forEach items="${racks}" var="rack">
													<c:choose> 
														<c:when test="${rack.rackCode eq box.boxRack.rackCode}">
															<option selected value="${rack.rackCode}"><spring:message code="${rack.rackName}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${rack.rackCode}"><spring:message code="${rack.rackName}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="boxPosition" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<select data-placeholder="<spring:message code="select" /> <spring:message code="boxPosition" />" name="boxPosition" id="boxPosition" class="form-control">
												<option value="${box.boxPosition}">${box.boxPosition}</option>
												<c:forEach items="${posiciones}" var="posicion">
													<option value="${posicion}"><spring:message code="${posicion}" /></option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="boxCode" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${box.boxCode}" id="boxCode" name="boxCode" readonly type="text" placeholder="<spring:message code="boxCode" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="boxName" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${box.boxName}" id="boxName" name="boxName" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="boxName" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="boxResultType" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<select data-placeholder="<spring:message code="select" /> <spring:message code="boxResultType" />" name="boxResult" id="boxResult" class="form-control">
												<option value=""></option>
												<c:forEach items="${resultados}" var="resultado">
													<c:choose> 
														<c:when test="${resultado.catKey eq box.boxResult}">
															<option selected value="${resultado.catKey}"><spring:message code="${resultado.messageKey}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${resultado.catKey}"><spring:message code="${resultado.messageKey}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="boxRows" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${box.boxRows}" id="boxRows" name="boxRows" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="boxRows" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-sort-numeric-asc"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="boxColumns" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${box.boxColumns}" id="boxColumns" name="boxColumns" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="boxColumns" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="boxCapacity" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${box.boxCapacity}" id="boxCapacity" readonly name="boxCapacity" type="text" placeholder="<spring:message code="boxCapacity" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="boxPriority" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${box.boxPriority}" id="boxPriority" name="boxPriority" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="boxPriority" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="boxObs" />
										<span class="required">
											 
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${box.boxObs}" id="boxObs" name="boxObs" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="boxObs" />" class="form-control"/>
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
										<a href="${fn:escapeXml(boxUrl)}" class="btn btn-danger"><spring:message code="end" /></a>
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
<spring:url value="/resources/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js" var="inputmask" />
<script type="text/javascript" src="${inputmask}"></script>
<spring:url value="/resources/plugins/bootstrap-touchspin/bootstrap.touchspin.js" var="touchspin" />
<script type="text/javascript" src="${touchspin}"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/boxes/process-box.js" var="boxScript" />
<script src="${boxScript}" type="text/javascript"></script>
<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>				
<script src="${jQValidationLoc}"/></script>
<spring:url value="/resources/plugins/select2/select2_locale_{language}.js" var="Select2Loc">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>				
<script src="${Select2Loc}"/></script>
<!-- END PAGE LEVEL SCRIPTS -->
<spring:url value="/admin/boxes/saveBox" var="saveBoxUrl"/>
<spring:url value="/catalog/alics/alictypeactivos" var="alicsUrl"/>
<spring:url value="/admin/equips/equiposactivos" var="equipsUrl"/>
<spring:url value="/admin/racks/racksactivos" var="racksUrl"/>
<spring:url value="/admin/boxes/positions" var="posicionesUrl"/>
<c:set var="successmessage"><spring:message code="process.success" /></c:set>
<c:set var="errormessage"><spring:message code="process.errors" /></c:set>
<script>
    $(function () {
    	$("li.admin").removeClass("admin").addClass("active");
        $("li.adminboxes").removeClass("adminboxes").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros = {saveBoxUrl: "${saveBoxUrl}",
				alicsUrl: "${alicsUrl}",
				equipsUrl: "${equipsUrl}",
				racksUrl: "${racksUrl}",
				posicionesUrl: "${posicionesUrl}",
				successmessage: "${successmessage}",
				errormessage: "${errormessage}"};
		CreateBox.init(parametros);
		$('#rackEquip').focus();
	});	
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>