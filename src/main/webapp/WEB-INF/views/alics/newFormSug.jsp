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
					<spring:message code="addalic" />
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/addalic/newAlic" htmlEscape="true "/>"><spring:message code="addalic" /></a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			
			<div class="row">
				<div class="col-md-4">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-plus"></i>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body form">
						<form action="#" autocomplete="off" id="add-alic-form" class="form-horizontal">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										<spring:message code="form.errors" />
									</div>
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="boxStudy" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-8">
											<select data-placeholder="<spring:message code="select" /> <spring:message code="boxStudy" />" name="boxStudy" id="boxStudy" class="form-control">
												<option value=""></option>
												<c:forEach items="${estudios}" var="estudio">
													<option value="${estudio.estudio.studyCode}"><spring:message code="${estudio.estudio.studyName}" /></option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="boxResultType" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-8">
											<select data-placeholder="<spring:message code="select" /> <spring:message code="boxResultType" />" name="boxResultType" id="boxResultType" class="form-control">
												<option value=""></option>
												<c:forEach items="${resultados}" var="resultado">
													<option value="${resultado.catKey}"><spring:message code="${resultado.messageKey}" /></option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="aliCode" />
										<span class="required">
										</span>
										</label>
										<div class="col-md-8">
											<div class="input-group">
												<input value="${alic.aliCode}" id="aliCode" name="aliCode" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="aliCode" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-barcode"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="alicTypeName" />
										<span class="required">
										</span>
										</label>
										<div class="col-md-8">
											<div class="col-md-4">
												<input value="${alic.alicTypeName}" readonly id="alicTypeName" name="alicTypeName" type="text" class="form-control"/>
											</div>
											<div class="col-md-4">
												<input value="${alic.alicTypeUse}" readonly id="alicTypeUse" name="alicTypeUse" type="text" class="form-control"/>
											</div>
											<div class="col-md-4">
												<input value="${alic.alicTypeTemp}" readonly id="alicTypeTemp" name="alicTypeTemp" type="text" class="form-control"/>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="aliBox" />:
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-8">
											<select data-placeholder="<spring:message code="select" /> <spring:message code="aliBox" />" name="boxResults" id="boxResults" class="form-control">
												<option value=""></option>
												
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="rackEquip" />
										<span class="required">
										</span>
										</label>
										<div class="col-md-8">
											<div class="input-group">
												<input value="${alic.rackEquip}" id="rackEquip" name="rackEquip" readonly type="text" placeholder="<spring:message code="rackEquip" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="boxRack" />
										<span class="required">
										</span>
										</label>
										<div class="col-md-8">
											<div class="input-group">
												<input value="${alic.boxRack}" id="boxRack" name="boxRack" readonly type="text" placeholder="<spring:message code="boxRack" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="aliBox" />
										<span class="required">
										</span>
										</label>
										<div class="col-md-8">
											<div class="input-group">
												<input value="${alic.aliBox}" id="aliBox" name="aliBox" readonly type="text" placeholder="<spring:message code="aliBox" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="aliPosition" />
										<span class="required">
										</span>
										</label>
										<div class="col-md-8">
											<div class="input-group">
												<input value="${alic.aliPosition}" id=aliPosition name="aliPosition" readonly type="text" placeholder="<spring:message code="aliPosition" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="aliVol" />
										<span class="required">
										</span>
										</label>
										<div class="col-md-8">
											<div class="input-group">
												<input value="${alic.aliVol}" id="aliVol" name="aliVol" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="aliVol" />" class="form-control"/>
												<span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="boxObs" />
										<span class="required">
										</span>
										</label>
										<div class="col-md-8">
											<div class="input-group">
												<input value="${alic.aliObs}" id="aliObs" name="aliObs" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="aliObs" />" class="form-control"/>
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
				<div class="col-md-8">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-plus"></i>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body form">
							<div class="grid" id="caja" >
								
							</div>
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
<spring:url value="/resources/scripts/alics/process-alic.js" var="boxScript" />
<script src="${boxScript}" type="text/javascript"></script>
<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>				
<script src="${jQValidationLoc}"/></script>
<spring:url value="/resources/plugins/select2/select2_locale_{language}.js" var="Select2Loc">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>				
<script src="${Select2Loc}"/></script>
<spring:url value="/resources/plugins/isotope/isotope.pkgd.js" var="isotope" />
<script type="text/javascript" src="${isotope}"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<spring:url value="/addalic/saveAlic" var="saveAlicUrl"/>
<spring:url value="/addalic/getAlicStudy" var="getAlicUrl"/>
<spring:url value="/addalic/getPosAlic" var="getPosUrl"/>
<c:set var="successmessage"><spring:message code="process.success" /></c:set>
<c:set var="errormessage"><spring:message code="process.errors" /></c:set>
<c:set var="aliNotPattern"><spring:message code="aliNotPattern" /></c:set>
<c:set var="aliNotPattern2"><spring:message code="aliNotPattern2" /></c:set>
<c:set var="aliNotInList"><spring:message code="aliNotInList" /></c:set>
<c:set var="regExpInv"><spring:message code="regExpInv" /></c:set>
<c:set var="regExpInv2"><spring:message code="regExpInv2" /></c:set>
<c:set var="noAlicStudy"><spring:message code="noAlicStudy" /></c:set>
<script>
    $(function () {
    	$("li.addalic").removeClass("addalic").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros = {saveAlicUrl: "${saveAlicUrl}",
				getAlicUrl: "${getAlicUrl}",
				getPosUrl: "${getPosUrl}",
				successmessage: "${successmessage}",
				aliNotPattern: "${aliNotPattern}",
				aliNotPattern2: "${aliNotPattern2}",
				aliNotInList: "${aliNotInList}",
				regExpInv: "${regExpInv}",
				regExpInv2: "${regExpInv2}",
				noAlicStudy: "${noAlicStudy}",
				errormessage: "${errormessage}"};
		CreateAlic.init(parametros);
		var ancho = "${100/9}"+"%";
        $('.grid-item').css({"width":ancho});
        $('.grid-item').css({"position":"relative"});
        $('.grid-item').css({"float":"left"});
        $('.grid-item').css({"height":"100px"});
        $('.grid-item').css({"background":"#FFFFFF"});
        $('.grid-item').css({"border":"1px solid #333"});
        $('.grid-item').css({"border-color":"hsla(0, 0%, 0%, 0.2)"});
        $('.grid').isotope({
        	  // options
        	  itemSelector: '.grid-item',
        	  layoutMode: 'fitRows'
        	});
	});	
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>