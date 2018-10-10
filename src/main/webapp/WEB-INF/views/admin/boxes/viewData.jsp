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
<c:set var="boxEnabledLabel"><spring:message code="box.enabled" /></c:set>
<c:set var="boxDisabledLabel"><spring:message code="box.disabled" /></c:set>
<c:set var="boxNotEmptyMessage"><spring:message code="boxNotEmpty" /></c:set>
<c:set var="habilitar"><spring:message code="enable" /></c:set>
<c:set var="deshabilitar"><spring:message code="disable" /></c:set>
<c:set var="confirmar"><spring:message code="confirm" /></c:set>

<spring:url value="/admin/boxes/editBox/{box}/"	var="editUrl">
	<spring:param name="box" value="${box.boxCode}" />
</spring:url>
<spring:url value="/admin/boxes/act/disable2/{box}/" var="disableUrl">
	<spring:param name="box" value="${box.boxCode}" />
</spring:url>
<spring:url value="/admin/boxes/act/enable2/{box}/"	var="enableUrl">
	<spring:param name="box" value="${box.boxCode}" />
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
					<spring:message code="title" /> <small><spring:message code="adminboxes" /></small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/boxes/" htmlEscape="true "/>"><spring:message code="adminboxes" /></a> <i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/boxes/viewBox/${box.boxCode}/" htmlEscape="true "/>">${box.boxCode}</a>
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
								<i class="fa fa-cubes"></i>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="btn-group">
									<spring:url value="/admin/boxes/"	var="listBox"/>
									<button id="lista_boxes" onclick="location.href='${fn:escapeXml(listBox)}'" class="btn btn-info">
									<spring:message code="adminboxes" /> <i class="fa fa-reply"></i>
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
											<c:when test="${box.pasive=='0'.charAt(0)}">
												<li>
													<a data-toggle="modal" data-box="${box.boxName}" data-id= "${fn:escapeXml(disableUrl)}" class="desact"><i class="fa fa-trash-o"></i> <spring:message code="disable" /></a>
												</li>
											</c:when>
											<c:otherwise>
												<li>
													<a data-toggle="modal" data-box="${box.boxName}" data-id= "${fn:escapeXml(enableUrl)}" class="act"><i class="fa fa-check"></i> <spring:message code="enable" /></a>
												</li>
											</c:otherwise>
										</c:choose>
									</ul>
								</div>
							</div>
							<!-- BEGIN FORM-->
							<form class="form-horizontal">
								<div class="form-body">
									<h3 class="form-section"><spring:message code="boxCode" />: <c:out value="${box.boxCode}" /></h3>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="boxName" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${box.boxName}" />
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
															<c:when test="${box.pasive=='0'.charAt(0)}">
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
												<label class="control-label col-md-3"><spring:message code="equipRoom" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${box.boxRack.rackEquip.equipRoom}" />
													</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="rackEquip" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${box.boxRack.rackEquip}" />
													</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="boxRack" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${box.boxRack}" />
													</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="boxPosition" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${box.boxPosition}" />
													</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="boxRows" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${box.boxRows}" />
													</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="boxColumns" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${box.boxColumns}" />
													</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="boxCapacity" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${box.boxCapacity}" />
													</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="boxPriority" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${box.boxPriority}" />
													</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="boxStudy" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${box.boxStudy}" />
													</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="boxAlicType" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${box.boxAlicType}" />
													</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="boxAlicUse" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${box.boxAlicUse}" />
													</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="boxTemp" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${box.boxTemp}" />
													</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="boxResultType" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${box.boxResult}" />
													</p>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="boxObs" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${box.boxObs}" />
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
														 <c:out value="${box.recordUser}" />
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
														<c:out value="${box.recordDate}" />
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
								<c:forEach begin="1" end="${box.boxCapacity}" varStatus="loop">
									<div class="grid-item">
								    <p class="number"><c:out value="${loop.count}"/></p>
								    <c:forEach items="${alics}" var="alic">
								    	<spring:url value="/alics/viewAlic/{alic}/"
											var="alicUrl">
											<spring:param name="alic" value="${alic.aliCode}" />
										</spring:url>
								    	<c:choose>
											<c:when test="${alic.aliPosition==loop.count}">
											    <p class="symbol"><a href="${fn:escapeXml(alicUrl)}"><c:out value="${alic.aliCode}" /></a></p>
											    <p class="name"><c:out value="${alic.aliVol}" /></p>
											    <p class="weight"><c:out value="${alic.aliCond}" /></p>
											    <p class="resultado"><c:out value="${alic.aliRes}" /></p>
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
			<!-- START ROW -->
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
        $("li.adminboxes").removeClass("adminboxes").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		
		$('#lista_cambios').DataTable({"aaSorting": [[ 6, "desc" ]] ,"oLanguage": {"sUrl": "${dataTablesLang}"}});
		
		if ("${cajaHabilitado}"){
			toastr.success("${boxEnabledLabel}", "${nombreCaja}" );
		}
		if ("${cajaDeshabilitado}"){
			toastr.error("${boxDisabledLabel}", "${nombreCaja}" );
		}
		
		if ("${boxNotEmpty}"){
			toastr.error("${boxNotEmptyMessage}");
		}
		
		$(".act").click(function(){ 
			$('#accionUrl').val($(this).data('id'));
        	$('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
        	$('#cuerpo').html('<h3>'+"${habilitar}"+' '+$(this).data('box')+'?</h3>');
        	$('#basic').modal('show');
        });
        
        $(".desact").click(function(){ 
        	$('#accionUrl').val($(this).data('id'));
        	$('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
        	$('#cuerpo').html('<h3>'+"${deshabilitar}"+' '+$(this).data('box')+'?</h3>');
        	$('#basic').modal('show');
        });
        
        var ancho = "${100/box.boxColumns}"+"%";
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