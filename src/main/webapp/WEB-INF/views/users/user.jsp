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
<jsp:include page="../fragments/headTag.jsp" />
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
<jsp:include page="../fragments/bodyHeader.jsp" />
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
<jsp:include page="../fragments/bodyNavigation.jsp" />
<!-- BEGIN CONTENT -->
<spring:url value="/users/editUser" var="editUrl" />
<spring:url value="/users/chgpass" var="chgpassUrl" />
<div class="page-content-wrapper">
	<div class="page-content-wrapper">
		<div class="page-content">
			<jsp:include page="../fragments/bodyCustomizer.jsp" />
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					<spring:message code="title" /> <small><spring:message code="profile" /></small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/users/profile" htmlEscape="true "/>"><spring:message code="profile" /></a>
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
								<i class="fa fa-user"></i>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="btn-group pull-right">
									<button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><spring:message code="actions" /> <i class="fa fa-angle-down"></i>
									</button>
									<ul class="dropdown-menu pull-right">
										<li>
											<a href="${fn:escapeXml(editUrl)}"><i class="fa fa-edit"></i> <spring:message code="edit" /></a>
										</li>
										<li>
											<a href="${fn:escapeXml(chgpassUrl)}"><i class="fa fa-key"></i> <spring:message code="changepass" /></a>
										</li>
									</ul>
								</div>
							</div>
							<!-- BEGIN FORM-->
							<form class="form-horizontal">
								<div class="form-body">
									<h3 class="form-section"><c:out value="${user.username}" /></h3>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="userdesc" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${user.completeName}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="useremail" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${user.email}" />
													</p>
												</div>
											</div>
										</div>
									</div>
									<!--/row-->
									<div class="row">
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="enabled" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														<c:choose>
															<c:when test="${user.enabled}">
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
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="locked" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														<c:choose>
															<c:when test="${user.accountNonLocked}">
																<spring:message code="no" />
															</c:when>
															<c:otherwise>
																<spring:message code="yes" />
															</c:otherwise>
														</c:choose>
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="usercred" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														<c:choose>
															<c:when test="${user.credentialsNonExpired}">
																<spring:message code="no" />
															</c:when>
															<c:otherwise>
																<spring:message code="yes" />
															</c:otherwise>
														</c:choose>
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="userexp" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														<c:choose>
															<c:when test="${user.accountNonExpired}">
																<spring:message code="no" />
															</c:when>
															<c:otherwise>
																<spring:message code="yes" />
															</c:otherwise>
														</c:choose>
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="createdBy" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${user.createdBy}" />
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
														<c:out value="${user.created}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="modifiedBy" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${user.modifiedBy}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="dateModified" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														<c:out value="${user.modified}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
									<!--/row-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="lastAccess" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														 <c:out value="${user.lastAccess}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-3"><spring:message code="dateCredentials" />:</label>
												<div class="col-md-9">
													<p class="form-control-static">
														<c:out value="${user.lastCredentialChange}" />
													</p>
												</div>
											</div>
										</div>
										<!--/span-->
									</div>
									<!--/row-->
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
								<i class="fa fa-group"></i><spring:message code="userroles" />
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
							<table class="table table-striped table-hover table-bordered" id="lista_roles">
							<thead>
								<tr>
									<th><spring:message code="userroles" /></th>
									<th><spring:message code="enabled" /></th>
									<th><spring:message code="addedBy" /></th>
									<th><spring:message code="dateAdded" /></th>
								</tr>
							</thead>
							<c:forEach items="${rolesusuario}" var="rol">
								<tr>
									<td><spring:message code="${rol.rol.authority}" /></td>
									<c:choose>
										<c:when test="${rol.pasive=='0'.charAt(0)}">
											<td><span class="label label-success"><spring:message code="yes" /></span></td>
										</c:when>
										<c:otherwise>
											<td><span class="label label-danger"><spring:message code="no" /></span></td>
										</c:otherwise>
									</c:choose>
									<td><c:out value="${rol.recordUser}" /></td>
									<td><c:out value="${rol.recordDate}" /></td>
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
			<!-- START ROW -->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN TABLE PORTLET-->
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-group"></i><spring:message code="supercenters" />
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
							<table class="table table-striped table-hover table-bordered" id="lista_centros">
							<thead>
								<tr>
									<th><spring:message code="supercenters" /></th>
									<th><spring:message code="enabled" /></th>
									<th><spring:message code="addedBy" /></th>
									<th><spring:message code="dateAdded" /></th>
								</tr>
							</thead>
							<c:forEach items="${centrosusuario}" var="centro">
								<tr>
									<td><spring:message code="${centro.userCenterId.center}" /></td>
									<c:choose>
										<c:when test="${centro.pasive=='0'.charAt(0)}">
											<td><span class="label label-success"><spring:message code="yes" /></span></td>
										</c:when>
										<c:otherwise>
											<td><span class="label label-danger"><spring:message code="no" /></span></td>
										</c:otherwise>
									</c:choose>
									<td><c:out value="${centro.recordUser}" /></td>
									<td><c:out value="${centro.recordDate}" /></td>
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
			<!-- START ROW -->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN TABLE PORTLET-->
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-key"></i><spring:message code="access" />
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
							<table class="table table-striped table-hover table-bordered" id="lista_accesos">
							<thead>
								<tr>
									<th class="hidden-xs"><spring:message code="session" /></th>
									<th class="hidden-xs"><spring:message code="ipaddress" /></th>
									<th><spring:message code="logindate" /></th>
									<th><spring:message code="logoutdate" /></th>
									<th class="hidden-xs"><spring:message code="logouturl" /></th>
								</tr>
							</thead>
							<c:forEach items="${accesses}" var="acceso">
								<tr>
									<td class="hidden-xs"><c:out value="${acceso.sessionId}" /></td>
									<td class="hidden-xs"><c:out value="${acceso.remoteIpAddress}" /></td>
									<td><c:out value="${acceso.loginDate}" /></td>
									<td><c:out value="${acceso.logoutDate}" /></td>
									<td class="hidden-xs"><c:out value="${acceso.logoutRefererUrl}" /></td>
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
			<!-- END PAGE CONTENT-->
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
<spring:url value="/resources/plugins/data-tables/jquery.dataTables.js" var="jQueryDataTables" />
<script type="text/javascript" src="${jQueryDataTables}"></script>
<spring:url value="/resources/plugins/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>
<spring:url value="/resources/plugins/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>
<spring:url value="/resources/plugins/data-tables/TableTools/swf/copy_csv_xls_pdf.swf" var="dataTablesTTSWF" />
<spring:url value="/resources/plugins/data-tables/i18n/label_{language}.json" var="dataTablesLang">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>	
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    $(function () {
    	$("li.start").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		$('#lista_accesos').DataTable({
            "oLanguage": {
    			"sUrl": "${dataTablesLang}"
            }
        });
		$('#lista_cambios').DataTable({
            "oLanguage": {
    			"sUrl": "${dataTablesLang}"
            }
        });
	});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>