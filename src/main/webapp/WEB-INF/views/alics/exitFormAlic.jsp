<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!--[if IE 8]> <html class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <jsp:include page="../fragments/headTag.jsp"/>
    <!-- BEGIN PAGE LEVEL STYLES -->
    <spring:url value="/resources/plugins/select2/select2_conquer.css" var="sel2css"/>
    <link rel="stylesheet" type="text/css" href="${sel2css}"/>
    <spring:url value="/resources/plugins/jquery-multi-select/css/multi-select.css" var="jqmscss"/>
    <link rel="stylesheet" type="text/css" href="${jqmscss}"/>
    <spring:url value="/resources/plugins/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss"/>
    <link rel="stylesheet" href="${dtttcss}"/>
    <!-- END PAGE LEVEL STYLES -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-fixed page-footer-fixed">
<!-- BEGIN HEADER -->
<jsp:include page="../fragments/bodyHeader.jsp"/>
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <jsp:include page="../fragments/bodyNavigation.jsp"/>
    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <div class="page-content-wrapper">
            <div class="page-content">
                <!-- BEGIN STYLE CUSTOMIZER -->
                <jsp:include page="../fragments/bodyCustomizer.jsp"/>
                <!-- BEGIN PAGE HEADER-->
                <div class="row">
                    <div class="col-md-12">
                        <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                        <h3 class="page-title">
                            <spring:message code="alic_output"/>
                        </h3>
                        <ul class="page-breadcrumb breadcrumb">
                            <li>
                                <i class="fa fa-home"></i>
                                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home"/></a>
                                <i class="fa fa-angle-right"></i> <a
                                    href="<spring:url value="/alicoutput/exitForm" htmlEscape="true "/>"><spring:message
                                    code="alic_output"/></a>
                            </li>
                        </ul>
                        <!-- END PAGE TITLE & BREADCRUMB-->
                    </div>
                </div>
                <!-- END PAGE HEADER-->
                <!-- BEGIN PAGE CONTENT-->

                <div class="row">
                    <div class="col-md-12">
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
                                <form action="#" autocomplete="off" id="search-form" class="form-horizontal">

                                    <div class="form-body">
                                        <div class="alert alert-danger display-hide">
                                            <button class="close" data-close="alert"></button>
                                            <spring:message code="form.errors"/>
                                        </div>

                                        <div class="form-group">
                                            <label style="text-align: left"
                                                   class="control-label col-md-2"><spring:message code="boxStudy"/>:
                                                <span class="required">
											 *
										</span>
                                            </label>
                                            <div class="col-md-5">
                                                <select data-placeholder="<spring:message code="select" /> <spring:message code="boxStudy" />"
                                                        name="boxStudy" id="boxStudy" class="form-control">
                                                    <option value=""></option>
                                                    <c:forEach items="${estudios}" var="estudio">
                                                        <c:choose>
                                                            <c:when test="${estudio.estudio.studyCode eq alic.aliBox.boxStudy.studyCode}">
                                                                <option selected
                                                                        value="${estudio.estudio.studyCode}">
                                                                    <spring:message
                                                                            code="${estudio.estudio.studyName}"/></option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${estudio.estudio.studyCode}">
                                                                    <spring:message
                                                                            code="${estudio.estudio.studyName}"/></option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                        </div>

                                        <legend style="font-size: medium"><spring:message code="searchalic"/></legend>

                                        <div class="form-group">
                                            <label style="text-align: left"
                                                   class="control-label col-md-2"><spring:message code="aliCode"/>:
                                                <span class="required">
                                                *
										</span>
                                            </label>
                                            <div class="col-md-5">
                                                <div class="input-group">
                                                    <input id="aliCode"  name="aliCode" type="text"
                                                           placeholder="<spring:message code="please.enter" /> <spring:message code="aliCode" />"
                                                           class="form-control"/>
                                                    <span class="input-group-addon">
													<i class="fa fa-barcode"></i>
												</span>
                                                </div>
                                            </div>

                                            <div class="col-md-2">
                                                <button disabled id="btnSearch" name="btnSearch" class="btn btn-primary">
                                                    <spring:message code="search"/></button>

                                            </div>

                                        </div>

                                    </div>

                                </form>

                                <form action="#" autocomplete="off" id="upload-form" class="form-horizontal">
                                    <div class="form-body">
                                        <div class="alert alert-danger display-hide">
                                            <button class="close" data-close="alert"></button>
                                            <spring:message code="form.errors"/>
                                        </div>

                                        <legend style="font-size: medium"><spring:message code="import_file"/></legend>

                                        <div class="form-group">
                                            <label style="text-align: left"
                                                   class="control-label col-md-1"><spring:message code="Filas"/>:
                                                <span class="required">
                                                *
										</span>
                                            </label>
                                            <div class="col-md-2">
                                                <div class="input-group">
                                                    <input id="rows"  name="rows" type="number"
                                                           class="form-control"/>
                                                    <span class="input-group-addon">
													<i class="fa fa-sort-numeric-asc"></i>
												</span>
                                                </div>
                                            </div>


                                            <label style="text-align: left"
                                                   class="control-label col-md-2"><spring:message code="rackColumns"/>:
                                                <span class="required">
                                                *
										</span>
                                            </label>
                                            <div class="col-md-2">
                                                <div class="input-group">
                                                    <input id="columns"  name="columns" type="number"
                                                           class="form-control"/>
                                                    <span class="input-group-addon">
													<i class="fa fa-sort-numeric-asc"></i>
												</span>
                                                </div>
                                            </div>


                                                <div id="dImportFile"  class="col-md-4 file-loading display-hide">
                                                    <input type="file" data-url="uploadFile" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" class="btn btn-default display-hide" name="file1" id="file1">
                                                </div>

                                </div>




                                </div>

                                </form>

                            </div>
                        </div>
                    </div>


                </div>

                <div class="row display-hide" id="dTable" >
                    <div class="col-md-12">
                        <div  class="portlet">
                            <div class="portlet-title">
                                <div class="caption">
                                    <i class="fa fa-database"></i>
                                    <spring:message code="alic_found"/>
                                </div>
                                <div class="tools">
                                    <a href="javascript:;" class="collapse"></a>
                                </div>
                            </div>
                            <div class="portlet-body">

                                <div class="table-responsive">
                                    <table class="table table-striped table-hover table-bordered" id="lista_alic">
                                        <thead>
                                        <tr>
                                            <th><spring:message code="aliCode"/></th>
                                            <th><spring:message code="studyCode"/></th>
                                            <th><spring:message code="equipCode"/></th>
                                            <th><spring:message code="rackCode"/></th>
                                            <th><spring:message code="boxCode"/></th>
                                            <th><spring:message code="aliPosition"/></th>
                                            <th><spring:message code="pos_neg"/></th>
                                            <th><spring:message code="aliVol"/></th>
                                            <th><spring:message code="tempDate"/></th>
                                            <th><spring:message code="username"/></th>
                                            <th><spring:message code="delete"/></th>
                                        </tr>
                                        </thead>

                                    </table>
                                </div>
                            </div>
                        </div>

                                    <div  class="portlet">
                                        <div class="portlet-title">
                                            <div class="caption">
                                                <i class="fa fa-database"></i>
                                                <spring:message code="alic_not_found"/>
                                            </div>
                                            <div class="tools">
                                                <a href="javascript:;" class="collapse"></a>
                                            </div>
                                        </div>
                                        <div class="portlet-body">

                                    <table class="table table-striped table-hover table-bordered" id="lista_alic2">
                                        <thead>
                                        <tr>
                                            <th><spring:message code="aliCode"/></th>
                                            <th><spring:message code="studyCode"/></th>
                                            <th><spring:message code="delete"/></th>
                                        </tr>
                                        </thead>

                                    </table>
                                </div>

                            </div>

                        <div  class="portlet">
                            <div class="portlet-title">
                                <div class="caption">
                                    <i class="fa fa-file"></i>
                                    <spring:message code="alic_output"/>
                                </div>
                                <div class="tools">
                                    <a href="javascript:;" class="collapse"></a>
                                </div>
                            </div>
                            <div class="portlet-body form">


                                <form action="#" autocomplete="off" id="exit_form1"
                                      class="form-horizontal ">


                                    <div class="form-body">
                                        <div class="alert alert-danger display-hide">
                                            <button class="close" data-close="alert"></button>
                                            <spring:message code="form.errors"/>
                                        </div>
                                        <div class="form-group">
                                            <label style="text-align: left"
                                                   class="control-label col-md-2"><spring:message
                                                    code="enic"/>:
                                                <span class="required">
                                                *
										</span>
                                            </label>
                                            <div class="col-md-4">
                                                <div class="input-group">
                                                    <input id="enic" name="enic" type="text"
                                                           placeholder="<spring:message code="please.enter" /> <spring:message code="enic" />"
                                                           class="form-control"/>
                                                    <span class="input-group-addon">
													<i class="fa fa-sort-alpha-asc"></i>
												</span>
                                                </div>
                                            </div>

                                            <div class="col-md-2">

                                                <label class="control-label"><spring:message
                                                        code="transportation"/>:
                                                    <span class="required">
                                                *
										</span>
                                                </label>
                                            </div>
                                            <div class="col-md-3">
                                                <select data-placeholder="<spring:message code="select" /> <spring:message code="transportation" />"
                                                        class="form-control" id="transportation"
                                                        name="transportation">
                                                    <option value=""></option>
                                                    <c:forEach items="${msj}" var="cond">
                                                        <option value="${cond.messageKey}"><spring:message
                                                                code="${cond.spanish}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>


                                        </div>


                                        <div class="form-group">

                                            <div class="col-md-2">
                                                <label style="text-align: left"
                                                       class="control-label"><spring:message
                                                        code="containerNum"/>:
                                                    <span class="required">
                                                *
										</span>

                                                </label>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="input-group">
                                                    <input id="containerNum" name="containerNum" type="text"
                                                           placeholder="<spring:message code="please.enter" /> <spring:message code="containerNum" />"
                                                           class="form-control"/>
                                                    <span class="input-group-addon">
													<i class="fa fa-sort-alpha-asc"></i>
												</span>
                                                </div>
                                            </div>


                                            <div class="col-md-2">
                                                <label class="control-label"><spring:message
                                                        code="carrier"/>:
                                                    <span class="required">
                                                *
										</span>
                                                </label>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="input-group">
                                                    <input id="carrier" name="carrier" type="text"
                                                           placeholder="<spring:message code="please.enter" /> <spring:message code="carrier" />"
                                                           class="form-control"/>
                                                    <span class="input-group-addon">
													<i class="fa fa-sort-alpha-asc"></i>
												</span>
                                                </div>
                                            </div>

                                        </div>


                                        <div class="form-group">
                                            <label style="text-align: left"
                                                   class="control-label col-md-2"><spring:message
                                                    code="sendDate"/>:
                                                <span class="required">
                                                *
										</span>

                                            </label>
                                            <div class="col-md-4">
                                                <div class="input-group">
                                                    <input id="sendDate" name="sendDate"
                                                           placeholder="<spring:message code="please.enter" /> <spring:message code="sendDate" />"
                                                           type="date" class="form-control"/>
                                                    <span class="input-group-addon">
													<i class="fa fa-calendar"></i>
												</span>
                                                </div>
                                            </div>

                                            <div class="col-md-2">
                                                <label class="control-label"><spring:message
                                                        code="destination"/>:
                                                    <span class="required">
                                                *
										</span>
                                                </label>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="input-group">
                                                    <input id="destination"
                                                           placeholder="<spring:message code="please.enter" /> <spring:message code="destination" />"
                                                           name="destination" type="text"
                                                           class="form-control"/>
                                                    <span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
                                                </div>
                                            </div>

                                        </div>

                                        <div class="form-group">

                                            <div class="col-md-2">
                                                <label style="text-align: left"
                                                       class="control-label"><spring:message
                                                        code="searchManager"/>:
                                                    <span class="required">
                                                *
										</span>

                                                </label>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="input-group">
                                                    <input id="searchManager" name="searchManager" type="text"
                                                           placeholder="<spring:message code="please.enter" /> <spring:message code="searchManager" />"
                                                           class="form-control"/>
                                                    <span class="input-group-addon">
													<i class="fa fa-sort-alpha-asc"></i>
												</span>
                                                </div>
                                            </div>


                                            <div class="col-md-2">
                                                <label class="control-label"><spring:message
                                                        code="packingManager"/>:
                                                    <span class="required">
                                                *
										</span>
                                                </label>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="input-group">
                                                    <input id="packingManager" name="packingManager" type="text"
                                                           placeholder="<spring:message code="please.enter" /> <spring:message code="packingManager" />"
                                                           class="form-control"/>
                                                    <span class="input-group-addon">
													<i class="fa fa-sort-alpha-asc"></i>
												</span>
                                                </div>
                                            </div>

                                        </div>


                                        <div class="form-group">

                                            <div class="col-md-2">
                                                <label style="text-align: left"
                                                       class="control-label"><spring:message
                                                        code="boxNum"/>:
                                                    <span class="required">
                                                *
										</span>

                                                </label>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="input-group">
                                                    <input id="boxNum" name="boxNum" type="text"
                                                           placeholder="<spring:message code="please.enter" /> <spring:message code="boxNum" />"
                                                           class="form-control"/>
                                                    <span class="input-group-addon">
													<i class="fa fa-sort-alpha-asc"></i>
												</span>
                                                </div>
                                            </div>


                                            <div class="col-md-2">
                                                <label class="control-label"><spring:message
                                                        code="requestBy"/>:
                                                    <span class="required">
                                                *
										</span>
                                                </label>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="input-group">
                                                    <input id="requestBy" name="requestBy" type="text"
                                                           placeholder="<spring:message code="please.enter" /> <spring:message code="requestBy" />"
                                                           class="form-control"/>
                                                    <span class="input-group-addon">
													<i class="fa fa-sort-alpha-asc"></i>
												</span>
                                                </div>
                                            </div>

                                        </div>

                                        <div class="form-group">

                                            <div class="col-md-2">
                                                <label style="text-align: left"
                                                       class="control-label"><spring:message
                                                        code="approveBy"/>:
                                                    <span class="required">
                                                *
										</span>

                                                </label>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="input-group">
                                                    <input id="approveBy" name="approveBy" type="text"
                                                           placeholder="<spring:message code="please.enter" /> <spring:message code="approveBy" />"
                                                           class="form-control"/>
                                                    <span class="input-group-addon">
													<i class="fa fa-sort-alpha-asc"></i>
												</span>
                                                </div>
                                            </div>


                                            <div class="col-md-2">
                                                <label class="control-label"><spring:message
                                                        code="purpose"/>:
                                                    <span class="required">
                                                *
										</span>
                                                </label>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="input-group">
                                                    <input id="purpose" name="purpose" type="text"
                                                           placeholder="<spring:message code="please.enter" /> <spring:message code="purpose" />"
                                                           class="form-control"/>
                                                    <span class="input-group-addon">
													<i class="fa fa-sort-alpha-asc"></i>
												</span>
                                                </div>
                                            </div>

                                        </div>

                                    </div>


                                    <div class="form-actions fluid">
                                        <div class="col-md-offset-5 col-md-6">
                                            <button id="btnSave" name="btnSave" class="btn btn-primary">
                                                <spring:message
                                                        code="process"/></button>
                                        </div>
                                    </div>
                                </form>


                                <!-- END PAGE CONTENT -->

                            </div>

                        </div>
                    </div>

                    <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" id="mi-modal">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-header" style="background-color: #428bca">
                                    <h5 class="modal-title"> <spring:message code="conf_msg" /></h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <h5 class="modal-title"> <spring:message code="msg_content" /></h5>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" id="modal-btn-si"><spring:message code="msg_yes"/></button>
                                    <button type="button" class="btn btn-primary" id="modal-btn-no"><spring:message code="msg_no"/></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END CONTENT -->
        </div>
    </div>
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<jsp:include page="../fragments/bodyFooter.jsp"/>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<jsp:include page="../fragments/corePlugins.jsp"/>
<jsp:include page="../fragments/bodyUtils.jsp"/>
<!-- BEGIN PAGE LEVEL PLUGINS -->
<spring:url value="/resources/plugins/select2/select2.min.js" var="Select2"/>
<script type="text/javascript" src="${Select2}"></script>
<spring:url value="/resources/plugins/jquery-multi-select/js/jquery.multi-select.js"
            var="jQueryMultiSelect"/>
<script type="text/javascript" src="${jQueryMultiSelect}"></script>
<spring:url value="/resources/plugins/jquery-validation/dist/jquery.validate.min.js"
            var="jQValidation"/>
<script type="text/javascript" src="${jQValidation}"></script>
<spring:url value="/resources/plugins/jquery-validation/dist/additional-methods.min.js"
            var="jQValidationAdd"/>
<script type="text/javascript" src="${jQValidationAdd}"></script>
<spring:url value="/resources/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js" var="inputmask"/>
<script type="text/javascript" src="${inputmask}"></script>

<c:choose>
    <c:when test="${cookie.invLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.invLang.value}"/>
    </c:otherwise>
</c:choose>
<spring:url value="/resources/plugins/data-tables/jquery.dataTables.js" var="jQueryDataTables"/>
<script type="text/javascript" src="${jQueryDataTables}"></script>
<spring:url value="/resources/plugins/data-tables/DT_bootstrap.js" var="dataTablesBS"/>
<script type="text/javascript" src="${dataTablesBS}"></script>
<spring:url value="/resources/plugins/data-tables/TableTools/js/dataTables.tableTools.js"
            var="dataTablesTT"/>
<script type="text/javascript" src="${dataTablesTT}"></script>
<spring:url value="/resources/plugins/data-tables/TableTools/swf/copy_csv_xls_pdf.swf"
            var="dataTablesTTSWF"/>
<spring:url value="/resources/plugins/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}"/>
</spring:url>

<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<spring:url value="/resources/scripts/app.js" var="App"/>
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/alics/alic-output-process.js" var="exitScript"/>
<script src="${exitScript}" type="text/javascript"></script>
<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js"
            var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}"/>
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/plugins/select2/select2_locale_{language}.js" var="Select2Loc">
    <spring:param name="language" value="${lenguaje}"/>
</spring:url>
<script src="${Select2Loc}"></script>

<spring:url value="/resources/plugins/isotope/isotope.pkgd.js" var="isotope"/>
<script type="text/javascript" src="${isotope}"></script>

<!-- JQUERY FILE UPLOAD -->
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="https://blueimp.github.io/JavaScript-Load-Image/js/load-image.all.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="https://blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script>
<spring:url value="/resources/plugins/jQuery-File-Upload/jquery.iframe-transport.js" var="jqueryIframeTrans" />
<script src="${jqueryIframeTrans}"></script>
<spring:url value="/resources/plugins/jQuery-File-Upload/jquery.fileupload.js" var="jqueryFileupload" />
<script src="${jqueryFileupload}"></script>
<spring:url value="/resources/plugins/jQuery-File-Upload/jquery.fileupload-process.js" var="jqueryFileuploadProc" />
<script src="${jqueryFileuploadProc}"></script>
<spring:url value="/resources/plugins/jQuery-File-Upload/jquery.fileupload-image.js" var="jqueryFileuploadImage" />
<script src="${jqueryFileuploadImage}"></script>
<spring:url value="/resources/plugins/jQuery-File-Upload/jquery.fileupload-validate.js" var="jqueryFileuploadValidate" />
<script src="${jqueryFileuploadValidate}"></script>


<!-- END PAGE LEVEL SCRIPTS -->
<spring:url value="/alicUse/getAlic" var="getAlicUrl"/>
<spring:url value="/addalic/getAlicStudy" var="getAlicStudyUrl"/>
<spring:url value="/alicoutput/saveExit" var="saveAlicUrl"/>
<spring:url value="/alicoutput/uploadFile" var="uploadFileUrl"/>

<c:set var="successmessage">
    <spring:message code="process.success"/>
</c:set>
<c:set var="errormessage">
    <spring:message code="process.errors"/>
</c:set>
<c:set var="aliNotInList">
    <spring:message code="aliNotInList"/>
</c:set>
<c:set var="regExpInv">
    <spring:message code="regExpInv"/>
</c:set>

<c:set var="msgNotFound">
    <spring:message code="404.notfound"/>
</c:set>
<c:set var="msgRepeated">
    <spring:message code="repeated_alic"/>
</c:set>
<c:set var="infoMsg">
    <spring:message code="exceeded_volume"/>
</c:set>
<c:set var="aliNotPattern">
    <spring:message code="aliNotPattern"/>
</c:set>
<c:set var="addedAlic">
    <spring:message code="added_alic"/>
</c:set>
<c:set var="msgRepeated2">
    <spring:message code="repeated_alics"/>
</c:set>






<script>
    $(function () {
        $("li.addalic").removeClass("addalic").addClass("active");
        $("li.exitalic").removeClass("exitalic").addClass("active");
    });
</script>
<script>
    jQuery(document).ready(function () {

        App.init();
        var parametros = {
            saveAlicUrl: "${saveAlicUrl}",
            getAlicUrl: "${getAlicUrl}",
            getAlicStudyUrl: "${getAlicStudyUrl}",
            successmessage: "${successmessage}",
            aliNotPattern: "${aliNotPattern}",
            aliNotInList: "${aliNotInList}",
            regExpInv: "${regExpInv}",
            msgNotFound: "${msgNotFound}",
            msgRepeated: "${msgRepeated}",
            msgRepeated2: "${msgRepeated2}",
            infoMsg: "${infoMsg}",
            dataTablesLang: "${dataTablesLang}",
            uploadFileUrl: "${uploadFileUrl}",
            addedAlic: "${addedAlic}",
            errormessage: "${errormessage}"
        };

        CreateExit.init(parametros);

    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>