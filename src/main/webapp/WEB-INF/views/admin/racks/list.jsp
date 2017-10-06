<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!--[if IE 8]> <html class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<jsp:include page="../../fragments/headTag.jsp" />
<!-- BEGIN PAGE LEVEL STYLES -->
<spring:url value="/resources/plugins/data-tables/DT_bootstrap.css" var="dtbootcss" />
<link rel="stylesheet" href="${dtbootcss}"/>
<spring:url value="/resources/plugins/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss" />
<link rel="stylesheet" href="${dtttcss}"/>
<spring:url value="/resources/plugins/select2/select2_conquer.css" var="sel2css" />
<link rel="stylesheet" type="text/css" href="${sel2css}"/>
<!-- END PAGE LEVEL STYLES -->
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
<c:set var="habilitar"><spring:message code="enable" /></c:set>
<c:set var="deshabilitar"><spring:message code="disable" /></c:set>
<c:set var="simon"><spring:message code="yes" /></c:set>
<c:set var="nelson"><spring:message code="no" /></c:set>
<c:set var="confirmar"><spring:message code="confirm" /></c:set>
<c:set var="exportar"><spring:message code="export" /></c:set>
<c:set var="rackEnabledLabel"><spring:message code="rack.enabled" /></c:set>
<c:set var="rackDisabledLabel"><spring:message code="rack.disabled" /></c:set>
<c:set var="rackNotEmptyMessage"><spring:message code="rackNotEmpty" /></c:set>
<div class="page-content-wrapper">
	<div class="page-content-wrapper">
		<div class="page-content">
			<jsp:include page="../../fragments/bodyCustomizer.jsp" />
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					<spring:message code="title" /> <small><spring:message code="adminracks" /></small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/racks/" htmlEscape="true "/>"><spring:message code="adminracks" /></a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<!-- START ROW -->
			<div class="row">
				<div class="col-md-12">
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-database"></i>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body form">
						<form action="#" autocomplete="off" id="select-equip-form" class="form-horizontal">
							<div class="form-body">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
									<spring:message code="form.errors" />
								</div>
								<div class="form-group">
									<label class="control-label col-md-3"><spring:message code="equipName" />:
									<span class="required">
										 *
									</span>
									</label>
									<div class="col-md-5">
										<select data-placeholder="<spring:message code="select" /> <spring:message code="equipName" />" name="equipCode" id="equipCode" class="form-control">
											<option value=""></option>
											<c:forEach items="${equipos}" var="equipo">
												<option value="${equipo.equipCode}">${equipo.equipName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3"><spring:message code="rackName" />:
									</label>
									<div class="col-md-5">
										<div class="input-group">
											<input type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="rackName" />" class="form-control" id="rackName" name="rackName"/>
											<span class="input-group-addon">
												<i class="fa fa-keyboard-o"></i>
											</span>
										</div>
									</div>
								</div>
							</div>
						</form>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN TABLE PORTLET-->
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-database"></i>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="btn-group">
									<spring:url value="/admin/racks/newRack"	var="newRack"/>
									<button id="lista_racks_new" onclick="location.href='${fn:escapeXml(newRack)}'" class="btn btn-success">
									<spring:message code="add" /> <i class="fa fa-plus"></i>
									</button>
								</div>
							</div>
							<div class="table-responsive">
							<table class="table table-striped table-hover table-bordered" id="lista_racks">
							<thead>
								<tr>
									<th><spring:message code="rackCode" /></th>
									<th><spring:message code="rackName" /></th>
									<th><spring:message code="rackPosition" /></th>
									<th><spring:message code="rackCapacity" /></th>
									<th><spring:message code="rackPriority" /></th>
									<th><spring:message code="active" /></th>
									<th><spring:message code="createdBy" /></th>
									<th><spring:message code="dateCreated" /></th>
									<th></th>
								</tr>
							</thead>
							</table>
							</div>
						</div>
					</div>
					<!-- END TABLE PORTLET-->
				</div>
			</div>
			<!-- END ROW -->
			<!-- END PAGE CONTENT-->
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
<spring:url value="/resources/plugins/data-tables/jquery.dataTables.js" var="jQueryDataTables" />
<script type="text/javascript" src="${jQueryDataTables}"></script>
<spring:url value="/resources/plugins/select2/select2.min.js" var="Select2" />
<script type="text/javascript" src="${Select2}"></script>
<spring:url value="/resources/plugins/isotope/isotope.pkgd.js" var="isotope" />
<script type="text/javascript" src="${isotope}"></script>
<spring:url value="/resources/plugins/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>
<spring:url value="/resources/plugins/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>
<spring:url value="/resources/plugins/data-tables/TableTools/swf/copy_csv_xls_pdf.swf" var="dataTablesTTSWF" />
<spring:url value="/resources/plugins/data-tables/i18n/label_{language}.json" var="dataTablesLang">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>	
<spring:url value="/resources/plugins/select2/select2_locale_{language}.js" var="Select2Loc">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>				
<script src="${Select2Loc}"/></script>
<spring:url value="/resources/plugins/jquery-validation/dist/jquery.validate.min.js" var="jQValidation" />
<script type="text/javascript" src="${jQValidation}"></script>
<spring:url value="/resources/plugins/jquery-validation/dist/additional-methods.min.js" var="jQValidationAdd" />
<script type="text/javascript" src="${jQValidationAdd}"></script>
<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>				
<script src="${jQValidationLoc}"/></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/racks/search-rack.js" var="rackScript" />
<script src="${rackScript}" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<spring:url value="/admin/racks/racks" var="racksUrl"/>
<spring:url value="/admin/racks/racksxname" var="racksNameUrl"/>
<spring:url value="/admin/racks/viewRack" var="rackUrl"/>
<spring:url value="/admin/racks/editRack" var="editUrl"/>
<spring:url value="/admin/racks/act/enable1/" var="enableUrl"/>
<spring:url value="/admin/racks/act/disable1/" var="disableUrl"/>
<c:set var="notFound"><spring:message code="noResults" /></c:set>
<script>
    $(function () {
    	$("li.admin").removeClass("admin").addClass("active");
        $("li.adminracks").removeClass("adminracks").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var parametros = {racksUrl: "${racksUrl}",
				racksNameUrl: "${racksNameUrl}",
				rackUrl: "${rackUrl}",
				editUrl: "${editUrl}",
				enableUrl: "${enableUrl}",
				disableUrl: "${disableUrl}",
				simon: "${simon}",
				nelson: "${nelson}",
				notFound: "${notFound}",
			dataTablesLang: "${dataTablesLang}",
			successmessage: "${successmessage}",
			errormessage: "${errormessage}"};
		SearchRack.init(parametros);
	});
    	
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>