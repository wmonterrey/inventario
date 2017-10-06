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
<c:set var="equipEnabledLabel"><spring:message code="equip.enabled" /></c:set>
<c:set var="equipDisabledLabel"><spring:message code="equip.disabled" /></c:set>
<c:set var="equipNotEmptyMessage"><spring:message code="equipNotEmpty" /></c:set>
<c:set var="habilitar"><spring:message code="enable" /></c:set>
<c:set var="deshabilitar"><spring:message code="disable" /></c:set>
<c:set var="confirmar"><spring:message code="confirm" /></c:set>

<spring:url value="/admin/equips/editEquip/{equipment}/"	var="editUrl">
	<spring:param name="equipment" value="${equipment.equipCode}" />
</spring:url>
<spring:url value="/admin/equips/act/disable2/{equipment}/" var="disableUrl">
	<spring:param name="equipment" value="${equipment.equipCode}" />
</spring:url>
<spring:url value="/admin/equips/act/enable2/{equipment}/"	var="enableUrl">
	<spring:param name="equipment" value="${equipment.equipCode}" />
</spring:url>

<div class="page-content-wrapper">
	<div class="page-content-wrapper">
		<div class="page-content">
			<jsp:include page="../../fragments/bodyCustomizer.jsp" />
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					<spring:message code="title" /> <small><spring:message code="adminequips" /></small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/equips/" htmlEscape="true "/>"><spring:message code="adminequips" /></a> <i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/equips/viewEquip/${equipment.equipCode}/" htmlEscape="true "/>">${equipment.equipCode}</a>
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
					<!-- BEGIN FORM PORTLET-->
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
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="btn-group">
									<spring:url value="/admin/equips/"	var="listEquip"/>
									<button id="lista_equipos" onclick="location.href='${fn:escapeXml(listEquip)}'" class="btn btn-info">
									<spring:message code="adminequips" /> <i class="fa fa-reply"></i>
									</button>
								</div>
								<div class="btn-group pull-right">
									<button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><spring:message code="actions" /> <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right">
										<li>
											<a href="${fn:escapeXml(editUrl)}"><i class="fa fa-edit"></i> <spring:message code="edit" /></a>
										</li>
										<c:choose>
											<c:when test="${equipment.pasive=='0'.charAt(0)}">
												<li>
													<a data-toggle="modal" data-equipo="${equipment.equipName}" data-id= "${fn:escapeXml(disableUrl)}" class="desact"><i class="fa fa-trash-o"></i> <spring:message code="disable" /></a>
												</li>
											</c:when>
											<c:otherwise>
												<li>
													<a data-toggle="modal" data-equipo="${equipment.equipName}" data-id= "${fn:escapeXml(enableUrl)}" class="act"><i class="fa fa-check"></i> <spring:message code="enable" /></a>
												</li>
											</c:otherwise>
										</c:choose>
									</ul>
								</div>
							</div>
							<!-- BEGIN FORM-->
							<form class="form-horizontal">
								<div class="form-body">
									<h3 class="form-section"><spring:message code="equipCode" />: <c:out value="${equipment.equipCode}" /></h3>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="equipName" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${equipment.equipName}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="enabled" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														<c:choose>
															<c:when test="${equipment.pasive=='0'.charAt(0)}">
																<spring:message code="yes" />
															</c:when>
															<c:otherwise>
																<spring:message code="no" />
															</c:otherwise>
														</c:choose>
													</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="equipUse" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${equipment.equipUse}" />
													</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="equipType" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${equipment.equipType}" />
													</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="equipRoom" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${equipment.equipRoom}" />
													</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="equipCapacity" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${equipment.equipCapacity}" />
													</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="equipRows" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${equipment.equipRows}" />
													</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="equipColumns" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${equipment.equipColumns}" />
													</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="equipTempMin" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${equipment.equipTempMin}" />
													</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="equipTempMax" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${equipment.equipTempMax}" />
													</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="equipBrand" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${equipment.equipBrand}" />
													</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="equipModel" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${equipment.equipModel}" />
													</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="equipSerie" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${equipment.equipSerie}" />
													</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="equipPriority" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${equipment.equipPriority}" />
													</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="equipResp" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${equipment.equipResp}" />
													</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="equipObs" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${equipment.equipObs}" />
													</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="createdBy" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${equipment.recordUser}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="dateCreated" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														<c:out value="${equipment.recordDate}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
								</div>
							</form>
							<!-- END FORM-->
						</div>
					</div>
					<!-- END FORM PORTLET-->
				</div>
			</div>
			<!-- END ROW -->
			<!-- START ROW -->
			<div class="row">
				
			</div>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN TABLE PORTLET-->
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-bars"></i><spring:message code="dx" />
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<c:choose>
								<c:when test="${espaciosusados/totalespacios*100 >= 90}">
									<c:set var="clase" value="progress-bar progress-bar-danger" />
								</c:when>
								<c:when test="${espaciosusados/totalespacios*100 >= 75}">
									<c:set var="clase" value="progress-bar progress-bar-warning" />
								</c:when>
								<c:when test="${espaciosusados/totalespacios*100 >= 50}">
									<c:set var="clase" value="progress-bar progress-bar-info" />
								</c:when>
								<c:otherwise>
									<c:set var="clase" value="progress-bar progress-bar-success" />
								</c:otherwise>
							</c:choose>
							<div class="progress progress-striped">
								<div class="<c:out value="${clase}" />" data-role="progressbar"
									style="width: <c:out value="${espaciosusados/totalespacios*100}" />%">
								</div>
							</div>
							<div class="grid">
								<c:forEach begin="1" end="${equipment.equipCapacity}" varStatus="loop">
									<div class="grid-item">
								    <p class="number"><c:out value="${loop.count}"/></p>
								    <c:forEach items="${racks}" var="rack">
								    	<spring:url value="/admin/racks/viewRack/{rack}/"
											var="rackUrl">
											<spring:param name="rack" value="${rack.rackCode}" />
										</spring:url>
								    	<c:choose>
											<c:when test="${rack.rackPosition==loop.count}">
											    <p class="symbol"><a href="${fn:escapeXml(rackUrl)}"><c:out value="${rack.rackName}" /></a></p>
											    <p class="name"><c:out value="${rack.rackCapacity}" /></p>
											</c:when>
										</c:choose>
									</c:forEach>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<!-- END TABLE PORTLET-->
				</div>
			</div>
			<!-- END ROW -->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN TABLE PORTLET-->
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-pencil"></i><spring:message code="audittrail" />
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar2">
								<div class="btn-group">
								</div>
							</div>
							<div class="table-responsive">
							<table class="table table-striped table-hover table-bordered" id="lista_cambios">
							<thead>
								<tr>
									<th><spring:message code="entityClass" /></th>
									<th><spring:message code="entityName" /></th>
									<th><spring:message code="entityProperty" /></th>
									<th><spring:message code="entityPropertyOldValue" /></th>
									<th><spring:message code="entityPropertyNewValue" /></th>
									<th><spring:message code="modifiedBy" /></th>
									<th><spring:message code="dateModified" /></th>
								</tr>
							</thead>
							<c:forEach items="${bitacora}" var="cambio">
								<tr>
									<td><spring:message code="${cambio.entityClass}" /></td>
									<td><c:out value="${cambio.entityName}" /></td>
									<td><c:out value="${cambio.entityProperty}" /></td>
									<td><c:out value="${cambio.entityPropertyOldValue}" /></td>
									<td><c:out value="${cambio.entityPropertyNewValue}" /></td>
									<td><c:out value="${cambio.username}" /></td>
									<td><c:out value="${cambio.operationDate}" /></td>
								</tr>
							</c:forEach>
							</table>
							</div>
						</div>
					</div>
					<!-- END TABLE PORTLET-->
				</div>
			</div>
			<!-- END ROW -->
			<div class="modal fade" id="basic" tabindex="-1" data-role="basic" data-backdrop="static" data-aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" data-aria-hidden="true"></button>
									<div id="titulo"></div>
								</div>
								<div class="modal-body">
									<input type="hidden" id="accionUrl"/>
									<div id="cuerpo"></div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
									<button type="button" class="btn btn-info" onclick="ejecutarAccion()"><spring:message code="ok" /></button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
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
<spring:url value="/resources/plugins/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>
<spring:url value="/resources/plugins/data-tables/i18n/label_{language}.json" var="dataTablesLang">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/resources/plugins/isotope/isotope.pkgd.js" var="isotope" />
<script type="text/javascript" src="${isotope}"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    $(function () {
    	$("li.admin").removeClass("admin").addClass("active");
        $("li.adminequips").removeClass("adminequips").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		
		$('#lista_cambios').DataTable({"aaSorting": [[ 6, "desc" ]] ,"oLanguage": {"sUrl": "${dataTablesLang}"}});
		
		if ("${equipoHabilitado}"){
			toastr.success("${equipEnabledLabel}", "${nombreEquipo}" );
		}
		if ("${equipoDeshabilitado}"){
			toastr.error("${equipDisabledLabel}", "${nombreEquipo}" );
		}
		
		if ("${equipNotEmpty}"){
			toastr.error("${equipNotEmptyMessage}");
		}
		$(".act").click(function(){ 
			$('#accionUrl').val($(this).data('id'));
        	$('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
        	$('#cuerpo').html('<h3>'+"${habilitar}"+' '+$(this).data('equipo')+'?</h3>');
        	$('#basic').modal('show');
        });
        
        $(".desact").click(function(){ 
        	$('#accionUrl').val($(this).data('id'));
        	$('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
        	$('#cuerpo').html('<h3>'+"${deshabilitar}"+' '+$(this).data('equipo')+'?</h3>');
        	$('#basic').modal('show');
        });
        
        var ancho = "${100/equipment.equipColumns}"+"%";
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
	
    function ejecutarAccion() {
		window.location.href = $('#accionUrl').val();		
	}
    
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>