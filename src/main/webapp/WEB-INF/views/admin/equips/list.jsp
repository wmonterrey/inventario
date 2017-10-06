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
<c:set var="habilitar"><spring:message code="enable" /></c:set>
<c:set var="deshabilitar"><spring:message code="disable" /></c:set>
<c:set var="confirmar"><spring:message code="confirm" /></c:set>
<c:set var="exportar"><spring:message code="export" /></c:set>
<c:set var="equipNotEmptyMessage"><spring:message code="equipNotEmpty" /></c:set>
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
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/equips/" htmlEscape="true "/>"><spring:message code="adminequips" /></a>
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
					<!-- BEGIN TABLE PORTLET-->
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
									<spring:url value="/admin/equips/newEquip"	var="newEquip"/>
									<button id="lista_equipos_new" onclick="location.href='${fn:escapeXml(newEquip)}'" class="btn btn-success">
									<spring:message code="add" /> <i class="fa fa-plus"></i>
									</button>
								</div>
							</div>
							<div class="table-responsive">
							<table class="table table-striped table-hover table-bordered" id="lista_equipos">
							<thead>
								<tr>
									<th><spring:message code="equipCode" /></th>
									<th><spring:message code="equipName" /></th>
									<th><spring:message code="equipRoom" /></th>
									<th><spring:message code="equipTempMin" /></th>
									<th><spring:message code="equipTempMax" /></th>
									<th><spring:message code="equipCapacity" /></th>
									<th><spring:message code="equipUse" /></th>
									<th><spring:message code="equipPriority" /></th>
									<th><spring:message code="active" /></th>
									<th><spring:message code="createdBy" /></th>
									<th><spring:message code="dateCreated" /></th>
									<th></th>
								</tr>
							</thead>
							<c:forEach items="${equipos}" var="equipo">
								<tr>
									<spring:url value="/admin/equips/viewEquip/{equip}/"
										var="equipoUrl">
										<spring:param name="equip" value="${equipo.equipCode}" />
									</spring:url>
									<spring:url value="/admin/equips/editEquip/{equip}/"
										var="editUrl">
										<spring:param name="equip" value="${equipo.equipCode}" />
									</spring:url>
									<spring:url value="/admin/equips/act/disable1/{equip}/"
										var="disableUrl">
										<spring:param name="equip" value="${equipo.equipCode}" />
									</spring:url>
									<spring:url value="/admin/equips/act/enable1/{equip}/"
										var="enableUrl">
										<spring:param name="equip" value="${equipo.equipCode}" />
									</spring:url>
									<td><a href="${fn:escapeXml(equipoUrl)}"><c:out value="${equipo.equipCode}" /></a></td>
									<td><a href="${fn:escapeXml(equipoUrl)}"><c:out value="${equipo.equipName}" /></a></td>
									<td><c:out value="${equipo.equipRoom}" /></td>
									<td><c:out value="${equipo.equipTempMin}" /></td>
									<td><c:out value="${equipo.equipTempMax}" /></td>
									<td><c:out value="${equipo.equipCapacity}" /></td>
									<td><c:out value="${equipo.equipUse}" /></td>
									<td><c:out value="${equipo.equipPriority}" /></td>
									<c:choose>
										<c:when test="${equipo.pasive=='0'.charAt(0)}">
											<td><span class="label label-success"><spring:message code="yes" /></span></td>
										</c:when>
										<c:otherwise>
											<td><span class="label label-danger"><spring:message code="no" /></span></td>
										</c:otherwise>
									</c:choose>
									<td><c:out value="${equipo.recordUser}" /></td>
									<td><c:out value="${equipo.recordDate}" /></td>
									<td>
										<a href="${fn:escapeXml(equipoUrl)}" class="btn btn-default btn-xs"><i class="fa fa-search"></i></a>
										<a href="${fn:escapeXml(editUrl)}" class="btn btn-default btn-xs"><i class="fa fa-edit"></i></a>
										<c:choose>
										<c:when test="${equipo.pasive=='0'.charAt(0)}">
											<a data-toggle="modal" data-equipo="${equipo.equipName}" data-id= "${fn:escapeXml(disableUrl)}" class="btn btn-default btn-xs desact"><i class="fa fa-trash-o"></i></a>
										</c:when>
										<c:otherwise>
											<a data-toggle="modal" data-equipo="${equipo.equipName}" data-id= "${fn:escapeXml(enableUrl)}" class="btn btn-default btn-xs act"><i class="fa fa-check"></i></a>
										</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
							</table>
							</div>
						</div>
					</div>
					<!-- END TABLE PORTLET-->
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
    	$("li.admin").removeClass("admin").addClass("active");
        $("li.adminequips").removeClass("adminequips").addClass("active");
    });
</script>
<script>
	jQuery(document).ready(function() {
		App.init();
		var table  = $('#lista_equipos').DataTable({
            // set the initial value
            "iDisplayLength": 10,
        	"aaSorting": [[ 1, "asc" ]],
            "oLanguage": {
    			"sUrl": "${dataTablesLang}"
            },
            "aoColumnDefs": [
                           {"aTargets": [0],
                               "bVisible": false,
                               "bSearchable": true}
                         ]
        });
		
		var tt = new $.fn.dataTable.TableTools( table, {
        	"sSwfPath": "${dataTablesTTSWF}",
        	"aButtons": [
        	                {
        	                    "sExtends":    "collection",
        	                    "sButtonText": "${exportar}",
        	                    "aButtons": [
        	                                 {
        	                                     "sExtends": "csv",
        	                                     "oSelectorOpts": { filter: 'applied', order: 'current' },
        	                                     "mColumns": [ 0, 1, 2, 3, 4 ]
        	                                 },
        	                                 {
        	                                     "sExtends": "pdf",
        	                                     "oSelectorOpts": { filter: 'applied', order: 'current' },
        	                                     "mColumns": [ 0, 1, 2, 3, 4 ],
        	                                     "sPdfOrientation": "landscape",
        	                                 }
        	                                 ]
        	                }
        	            ]
        } );
		 
	    $( tt.fnContainer() ).insertBefore('div.table-toolbar');
		
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
	});
	
	
    function ejecutarAccion() {
		window.location.href = $('#accionUrl').val();		
	}
    	
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>