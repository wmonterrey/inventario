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

<spring:url value="/catalog/all/saveCatalogo" var="saveCatalogoUrl"></spring:url>
<spring:url value="/catalog/all/" var="catalogosUrl"/>
<div class="page-content-wrapper">
	<div class="page-content-wrapper">
		<div class="page-content">
			<jsp:include page="../../fragments/bodyCustomizer.jsp" />
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					<spring:message code="title" /> <small><spring:message code="catalogall" /></small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
							<i class="fa fa-angle-right"></i> <a href="<spring:url value="/catalog/all/" htmlEscape="true "/>"><spring:message code="catalogall" /></a>
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
								<i class="fa fa-info"></i>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<form action="#" autocomplete="off" id="edit-mensaje-form">                      
								<div class="form-group row">
			                      <label class="col-md-3 col-form-label" for="messageKey"><strong><spring:message code="ident" /></strong></label>
			                      <div class="col-md-9">
			                      	<div class="input-group">
			                      		<span class="input-group-addon"><i class="fa fa-key"></i></span>
			                        	<input type="text" id="messageKey" readonly name="messageKey" value="${catalogo.messageKey}" class="form-control" placeholder="<spring:message code="messageKey" />">
			                        	<span class="input-group-addon"><i class="fa fa-sort-alpha-asc"></i></span>
			                        </div>
			                        <span class="help-block"></span>
			                      </div>
			                    </div>
			                    <div class="form-group row">
			                      <label class="col-md-3 col-form-label" for="spanish"><strong><spring:message code="spanish" /></strong></label>
			                      <div class="col-md-9">
			                      	<div class="input-group">
			                      		<span class="input-group-addon"><i class="fa fa-flag"></i></span>
			                        	<input type="text" id="spanish" name="spanish" value="${catalogo.spanish}" class="form-control" placeholder="<spring:message code="spanish" />">
			                        	<span class="input-group-addon"><i class="fa fa-sort-alpha-asc"></i></span>
			                        </div>
			                        <span class="help-block"></span>
			                      </div>
			                    </div>
			                    <div class="form-group row">
			                      <label class="col-md-3 col-form-label" for="english"><strong><spring:message code="english" /></strong></label>
			                      <div class="col-md-9">
			                      	<div class="input-group">
			                      		<span class="input-group-addon"><i class="fa fa-flag"></i></span>
			                        	<input type="text" id="english" name="english" value="${catalogo.english}" class="form-control" placeholder="<spring:message code="english" />">
			                        	<span class="input-group-addon"><i class="fa fa-sort-alpha-asc"></i></span>
			                        </div>
			                        <span class="help-block"></span>
			                      </div>
			                    </div>
			                    <input type="text" hidden id="isCat" name="isCat" value="${catalogo.isCat}">
			                    <input type="text" hidden id="pasive" name="pasive" value="${catalogo.pasive}">
			                    <input type="text" hidden id="catRoot" name="catRoot" value="${catalogo.catRoot}">
			                    <input type="text" hidden id="catKey" name="pasive" value="${catalogo.catKey}">
			                    <input type="text" hidden id="order" name="order" value="${catalogo.order}">
		                        <div class="form-group">
		                          <button type="submit" class="btn btn-primary" id="guardar"><i class="fa fa-save"></i>&nbsp;<spring:message code="save" /></button>
								  <a href="${fn:escapeXml(catalogosUrl)}" class="btn btn-danger"><i class="fa fa-undo"></i>&nbsp;<spring:message code="end" /></a>
		                        </div>
		                      </form>
						</div>
					</div>
				</div>
			</div>
			<!-- END ROW -->
			
			<!-- START ROW -->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN FORM PORTLET-->
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-info"></i>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<table id="lista_respuestas" class="table table-striped table-bordered datatable" width="100%">
				                <thead>
				                	<tr>
				                		<th><spring:message code="order" /></th>
					                    <th><spring:message code="ident" /></th>
										<th><spring:message code="spanish" /></th>
										<th><spring:message code="english" /></th>
										<th><spring:message code="catKey" /></th>
										<th><spring:message code="enabled" /></th>
										<th></th>
				                	</tr>
				                </thead>
				                <tbody>
				                	<c:forEach items="${opciones}" var="respuesta">
									<tr>
										<spring:url value="/admin/catalogos/disableRespuesta/{messageKey}/" var="disableRespuestaUrl">
			                               <spring:param name="messageKey" value="${respuesta.messageKey}" />
			                            </spring:url>
			                            <spring:url value="/admin/catalogos/enableRespuesta/{messageKey}/" var="enableRespuestaUrl">
			                               <spring:param name="messageKey" value="${respuesta.messageKey}" />
			                            </spring:url>
										<td><c:out value="${respuesta.order}" /></td>
										<td><c:out value="${respuesta.messageKey}" /></td>
										<td><c:out value="${respuesta.spanish}" /></td>
										<td><c:out value="${respuesta.english}" /></td>
										<td><c:out value="${respuesta.catKey}" /></td>
										<c:choose>
											<c:when test="${respuesta.pasive=='0'.charAt(0)}">
												<td><span class="badge badge-success"><spring:message code="yes" /></span></td>
											</c:when>
											<c:otherwise>
												<td><span class="badge badge-danger"><spring:message code="no" /></span></td>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${respuesta.pasive=='0'.charAt(0)}">
												<td><a href="#" data-toggle="modal" data-whatever="${fn:escapeXml(disableRespuestaUrl)}" class="btn btn-outline-primary btn-sm desact"><i class="fa fa-trash-o"></i></a></td>
											</c:when>
											<c:otherwise>
												<td><a href="#" data-toggle="modal" data-whatever="${fn:escapeXml(enableRespuestaUrl)}" class="btn btn-outline-primary btn-sm act"><i class="fa fa-check"></i></a></td>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>
				                </tbody>
				            </table>
				            <spring:url value="/catalog/all/addRespuesta/{messageKey}/" var="addRespuestaUrl"><spring:param name="messageKey" value="${catalogo.messageKey}" /></spring:url>
				            <a href="${fn:escapeXml(addRespuestaUrl)}" class="btn btn-primary"><i class="fa fa-plus"></i>&nbsp;<spring:message code="add" /></a>
						</div>
						
					</div>
				</div>
			</div>
			<!-- END ROW -->
			

			<!-- END PAGE CONTENT-->
		</div>
	</div>
</div>
<!-- END CONTENT -->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<jsp:include page="../../fragments/bodyFooter.jsp" />
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<jsp:include page="../../fragments/corePlugins.jsp" />
<jsp:include page="../../fragments/bodyUtils.jsp" />
<!-- BEGIN PAGE LEVEL PLUGINS -->	
<spring:url value="/resources/scripts/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
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

<spring:url value="/resources/plugins/jquery-validation/dist/jquery.validate.min.js" var="jQValidation" />
<script type="text/javascript" src="${jQValidation}"></script>
<spring:url value="/resources/plugins/jquery-validation/dist/additional-methods.min.js" var="jQValidationAdd" />
<script type="text/javascript" src="${jQValidationAdd}"></script>
<spring:url value="/resources/plugins/bootstrap-touchspin/bootstrap.touchspin.js" var="touchspin" />
<script type="text/javascript" src="${touchspin}"></script>

<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>				

<script src="${jQValidationLoc}"/></script>
<spring:url value="/resources/plugins/data-tables/i18n/label_{language}.json" var="dataTablesLang">
	<spring:param name="language" value="${lenguaje}" />
</spring:url>	

<!-- END PAGE LEVEL SCRIPTS -->
<script>
    $(function () {
    	$("li.catalog").removeClass("catalog").addClass("active");
        $("li.catalogall").removeClass("catalogall").addClass("active");
    });
</script>
<script>
jQuery(document).ready(function() {
	App.init();

	$('.datatable').DataTable({
        "oLanguage": {
            "sUrl": "${dataTablesLang}"
        },
        "scrollX": true,
        "lengthMenu": [[5,10, 25, 50], [5,10, 25, 50]]
    });
	  $('.datatable').attr('style', 'border-collapse: collapse !important');


	$.validator.setDefaults( {
	    submitHandler: function () {
	    	processMensaje();
	    }
	  } );	
  $( '#edit-mensaje-form' ).validate( {
    rules: {
      spanish: {
    	  minlength: 1,
          maxlength: 255,
          required: true
      },
      english: {
    	  minlength: 1,
          maxlength: 255,
          required: true
      }
    },
    errorElement: 'em',
    errorPlacement: function ( error, element ) {
      // Add the `help-block` class to the error element
      error.addClass( 'form-control-feedback' );
      if ( element.prop( 'type' ) === 'checkbox' ) {
        error.insertAfter( element.parent( 'label' ) );
      } else {
        error.insertAfter( element );
      }
    },
    highlight: function ( element, errorClass, validClass ) {
      $( element ).addClass( 'form-control-danger' ).removeClass( 'form-control-success' );
      $( element ).parents( '.form-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );
    },
    unhighlight: function (element, errorClass, validClass) {
      $( element ).addClass( 'form-control-success' ).removeClass( 'form-control-danger' );
      $( element ).parents( '.form-group' ).addClass( 'has-success' ).removeClass( 'has-danger' );
    }
  });		

  function processMensaje(){
	  App.blockUI();
	    $.post( "${saveCatalogoUrl}"
	            , $( '#edit-mensaje-form' ).serialize()
	            , function( data )
	            {
	    			mensaje = JSON.parse(data);
	    			if (mensaje.messageKey === undefined) {
	    				data = data.replace(/u0027/g,"");
	    				toastr.options = {
	    						  "closeButton": true,
	    						  "onclick": null,
	    						  "showDuration": "300",
	    						  "hideDuration": "1000",
	    						  "timeOut": 0,
	    						  "extendedTimeOut": 0,
	    						  "tapToDismiss": false
	    						};
	    				toastr["error"](data, "Error!!"); 
					}
					else{
						App.blockUI();
						$('#messageKey').val(mensaje.messageKey); 
						toastr.options = {
	    						  "closeButton": true,
	    						  "onclick": null,
	    						  "showDuration": "200",
	    						  "hideDuration": "500",
	    						  "timeOut": 1000,
	    						  "extendedTimeOut": 0,
	    						  "tapToDismiss": false
	    						};
	    				toastr["success"](mensaje.messageKey, "success!!");
						App.unblockUI();
					}
	            }
	            , 'text' )
		  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
		    		alert( "error:" + errorThrown);
		    		App.unblockUI();
		  		});
	    App.blockUI();
	}	
	
    
});
	    
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>