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
					<spring:message code="adminequips" />
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/equips/" htmlEscape="true "/>"><spring:message code="adminequips" /></a> <i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/equips/editEquip/${equipment.equipCode}/" htmlEscape="true "/>">${equipment.equipCode}</a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<spring:url value="/admin/equips/newEquip" var="newEquipUrl"></spring:url>
			<spring:url value="/admin/equips/" var="equipUrl"></spring:url>
			<c:set var="equipCreated"><spring:message code="process.success" /></c:set>
			<c:set var="errorProcess"><spring:message code="process.errors" /></c:set>
			
			<div class="row">
				<div class="col-md-12">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-columns"></i>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body form">
						<form action="#" autocomplete="off" id="add-equip-form" class="form-horizontal">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										<spring:message code="form.errors" />
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="equipCode" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${equipment.equipCode}" id="equipCode" name="equipCode" readonly type="text" placeholder="<spring:message code="equipCode" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="equipName" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${equipment.equipName}" id="equipName" name="equipName" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="equipName" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="equipUse" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<select multiple="multiple" class="multi-select" id="equipUse" name="equipUse">
											<c:forEach items="${usosequipo}" var="uso">
												<c:choose> 
													<c:when test="${fn:contains(equipment.equipUse, uso.catKey)}">
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
										<label class="control-label col-md-3"><spring:message code="equipType" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<select data-placeholder="<spring:message code="select" /> <spring:message code="equipType" />" name="equipType" id="equipType" class="form-control">
												<option value=""></option>
												<c:forEach items="${tiposequipo}" var="tipo">
													<c:choose> 
														<c:when test="${tipo.catKey eq equipment.equipType}">
															<option selected value="${tipo.catKey}"><spring:message code="${tipo.messageKey}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${tipo.catKey}"><spring:message code="${tipo.messageKey}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="equipRoom" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<select data-placeholder="<spring:message code="select" /> <spring:message code="equipRoom" />" name="equipRoom" id="equipRoom" class="form-control">
												<option value=""></option>
												<c:forEach items="${cuartos}" var="cuarto">
													<c:choose> 
														<c:when test="${cuarto.roomCode eq equipment.equipRoom.roomCode}">
															<option selected value="${cuarto.roomCode}"><spring:message code="${cuarto.roomName}" /></option>
														</c:when>
														<c:otherwise>
															<option value="${cuarto.roomCode}"><spring:message code="${cuarto.roomName}" /></option>
														</c:otherwise>
													</c:choose> 
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="equipRows" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${equipment.equipRows}" id="equipRows" name="equipRows" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="equipRows" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-sort-numeric-asc"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="equipColumns" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${equipment.equipColumns}" id="equipColumns" name="equipColumns" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="equipColumns" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="equipCapacity" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${equipment.equipCapacity}" id="equipCapacity" readonly name="equipCapacity" type="text" placeholder="<spring:message code="equipCapacity" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="equipTempMin" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${equipment.equipTempMin}" id="equipTempMin" name="equipTempMin" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="equipTempMin" />" class="form-control"/>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="equipTempMax" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<input id="equipTempMax" type="text" value="${equipment.equipTempMax}" name="equipTempMax" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="equipBrand" />:
										<span class="required">
											 
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${equipment.equipBrand}" id="equipBrand" name="equipBrand" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="equipBrand" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="equipModel" />:
										<span class="required">
											 
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${equipment.equipModel}" id="equipModel" name="equipModel" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="equipModel" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="equipSerie" />:
										<span class="required">
											 
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${equipment.equipSerie}" id="equipSerie" name="equipSerie" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="equipSerie" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="equipPriority" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${equipment.equipPriority}" id="equipPriority" name="equipPriority" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="equipPriority" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="equipResp" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${equipment.equipResp}" id="equipResp" name="equipResp" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="equipResp" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="equipObs" />
										<span class="required">
											 
										</span>
										</label>
										<div class="col-md-5">
											<div class="input-group">
												<input value="${equipment.equipObs}" id="equipObs" name="equipObs" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="equipObs" />" class="form-control"/>
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
										<a href="${fn:escapeXml(equipUrl)}" class="btn btn-danger"><spring:message code="end" /></a>
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
<spring:url value="/resources/scripts/equips/process-equipment.js" var="equipScript" />
<script src="${equipScript}" type="text/javascript"></script>
<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>				
<script src="${jQValidationLoc}"/></script>
<spring:url value="/resources/plugins/select2/select2_locale_{language}.js" var="Select2Loc">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>				
<script src="${Select2Loc}"/></script>
<!-- END PAGE LEVEL SCRIPTS -->
<spring:url value="/admin/equips/saveEquip" var="saveEquipUrl"/>
<c:set var="successmessage"><spring:message code="process.success" /></c:set>
<c:set var="errormessage"><spring:message code="process.errors" /></c:set>
<script>
    $(function () {
    	$("li.admin").removeClass("admin").addClass("active");
        $("li.adminequips").removeClass("adminequips").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros = {saveEquipUrl: "${saveEquipUrl}",
				successmessage: "${successmessage}",
				errormessage: "${errormessage}"};
		CreateEquip.init(parametros);
		$('#equipName').focus();
	});	
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>