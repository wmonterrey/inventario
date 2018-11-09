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
                            <spring:message code="search_request"/>
                        </h3>
                        <ul class="page-breadcrumb breadcrumb">
                            <li>
                                <i class="fa fa-home"></i>
                                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home"/></a>
                                <i class="fa fa-angle-right"></i> <a
                                    href="<spring:url value="/requestsheet/enterForm" htmlEscape="true "/>"><spring:message
                                    code="search_request"/></a>
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
                                <form action="#" autocomplete="off" id="add-alic-use" class="form-horizontal">

                                    <div class="form-body">
                                        <div class="alert alert-danger display-hide">
                                            <button class="close" data-close="alert"></button>
                                            <spring:message code="form.errors"/>
                                        </div>

                                        <div class="form-group">
                                            <label style="text-align: left"
                                                   class="control-label col-md-1"><spring:message code="initDate"/>:
                                                <span class="required">
											 *
										</span>
                                            </label>
                                            <div class="col-md-3">
                                                <div class="input-group date date-picker" data-date-format="dd/mm/yyyy" data-date-end-date="+0d">
                                                    <input type="text" class="form-control" name="initDate" id="initDate">
                                                    <span class="input-group-btn">
													<button class="btn btn-default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
                                                </div>
                                            </div>


                                            <label style="text-align: left"
                                                   class="control-label col-md-1"><spring:message code="endDate"/>:
                                                <span class="required">
											 *
										</span>
                                            </label>
                                            <div class="col-md-3">
                                                <div class="input-group date date-picker" data-date-format="dd/mm/yyyy" data-date-end-date="+0d">
                                                    <input type="text" class="form-control" name="endDate" id="endDate">
                                                    <span class="input-group-btn">
													<button class="btn btn-default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
                                                </div>
                                            </div>

                                            <div class="col-md-offset-2 col-md-2">
                                                <button id="btnSearch" type="submit" class="btn btn-success"><spring:message code="search" /></button>
                                            </div>

                                            </div>


                                        <div id="dTable" class="dataTables_scrollBody display-hide" style="position: relative; overflow: auto; width: 100%;">
                                            <table class="table table-striped table-hover display nowrap dataTable no-footer table-bordered" id="reqList" style="width: 100%;" role="grid">
                                                <thead>
                                                <tr>
                                                    <th></th>
                                                    <th><spring:message code="idRequest"/></th>
                                                    <th><spring:message code="request_date"/></th>
                                                    <th><spring:message code="equipResp"/></th>
                                                    <th><spring:message code="approvedBy"/></th>
                                                    <th></th>


                                                </tr>
                                                </thead>

                                            </table>
                                        </div>

                                        <div id="bTable" class="form-actions fluid display-hide">
                                            <div class="col-md-offset-6 col-md-6">
                                                <button id="btnSave" type="button" class="btn btn-success"><spring:message code="save_request" /></button>
                                            </div>
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
<jsp:include page="../fragments/bodyFooter.jsp"/>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<jsp:include page="../fragments/corePlugins.jsp"/>
<jsp:include page="../fragments/bodyUtils.jsp"/>
<!-- BEGIN PAGE LEVEL PLUGINS -->
<c:choose>
    <c:when test="${cookie.invLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.invLang.value}"/>
    </c:otherwise>
</c:choose>
<!-- bootstrap datepicker -->
<spring:url value="/resources/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" var="jQDate" />
<script type="text/javascript" src="${jQDate}"></script>
<spring:url value="/resources/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.es.js" var="jQDateLoc"/>
<script src="${jQDateLoc}" charset="UTF-8"></script>
<spring:url value="/resources/plugins/select2/select2.min.js" var="Select2"/>
<script type="text/javascript" src="${Select2}"></script>
<spring:url value="/resources/plugins/jquery-multi-select/js/jquery.multi-select.js" var="jQueryMultiSelect"/>
<script type="text/javascript" src="${jQueryMultiSelect}"></script>
<spring:url value="/resources/plugins/jquery-validation/dist/jquery.validate.min.js" var="jQValidation"/>
<script type="text/javascript" src="${jQValidation}"></script>
<spring:url value="/resources/plugins/jquery-validation/dist/additional-methods.min.js" var="jQValidationAdd"/>
<script type="text/javascript" src="${jQValidationAdd}"></script>
<spring:url value="/resources/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js" var="inputmask"/>
<script type="text/javascript" src="${inputmask}"></script>

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
<spring:url value="/resources/scripts/alics/requestSheet.js" var="reqSheetScript"/>
<script src="${reqSheetScript}" type="text/javascript"></script>
<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}"/>
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/plugins/select2/select2_locale_{language}.js" var="Select2Loc">
    <spring:param name="language" value="${lenguaje}"/>
</spring:url>
<script src="${Select2Loc}"></script>

<spring:url value="/resources/plugins/isotope/isotope.pkgd.js" var="isotope"/>
<script type="text/javascript" src="${isotope}"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<spring:url value="/requestsheet/searchRequests" var="getReqUrl"/>
<spring:url value="/requestsheet/searchRequest" var="getRequestUrl"/>
<spring:url value="/requestsheet/requestSheet" var="getDownloadExcelUrl"/>

<c:set var="successmessage"><spring:message code="process.success"/></c:set>
<c:set var="errormessage"><spring:message code="process.errors"/></c:set>
<c:set var="aliNotInList"><spring:message code="aliNotInList"/></c:set>
<c:set var="regExpInv"><spring:message code="regExpInv"/></c:set>

<c:set var="msgNotFound"><spring:message code="404.notfound"/></c:set>
<c:set var="infoMsg"><spring:message code="exceeded_volume"/></c:set>
<c:set var="aliNotPattern"><spring:message code="aliNotPattern"/></c:set>


<c:set var="code"><spring:message code="code"/></c:set>
<c:set var="aliCode"><spring:message code="aliCode"/></c:set>
<c:set var="aliVol2"><spring:message code="aliVol2"/></c:set>
<c:set var="subAliVol"><spring:message code="subAliVol"/></c:set>
<c:set var="boxStudy"><spring:message code="boxStudy"/></c:set>
<c:set var="alicTypeSample"><spring:message code="alicTypeSample"/></c:set>
<c:set var="samplingDate"><spring:message code="sampling_date"/></c:set>
<c:set var="purposeRequest"><spring:message code="purpose_request"/></c:set>
<c:set var="username"><spring:message code="username"/></c:set>
<c:set var="destination"><spring:message code="destination"/></c:set>
<c:set var="centerObs"><spring:message code="centerObs"/></c:set>


<script>
    $(function () {
        $("li.addalic").removeClass("requestSheet").addClass("active");
        $("li.requestSheet").removeClass("requestSheet").addClass("active");
    });
</script>
<script>
    jQuery(document).ready(function () {
        App.init();
        var parametros = {
            getReqUrl: "${getReqUrl}",
            getRequestUrl: "${getRequestUrl}",
            successmessage: "${successmessage}",
            aliNotPattern: "${aliNotPattern}",
            aliNotInList: "${aliNotInList}",
            regExpInv: "${regExpInv}",
            msgNotFound: "${msgNotFound}",
            infoMsg: "${infoMsg}",
            lenguaje: "${lenguaje}",
            code: "${code}",
            aliCode: "${aliCode}",
            aliVol2: "${aliVol2}",
            subAliVol: "${subAliVol}",
            boxStudy: "${boxStudy}",
            alicTypeSample: "${alicTypeSample}",
            samplingDate: "${samplingDate}",
            purposeRequest: "${purposeRequest}",
            username: "${username}",
            destination: "${destination}",
            centerObs: "${centerObs}",
            getDownloadExcelUrl: "${getDownloadExcelUrl}",
            errormessage: "${errormessage}"
        };

        RequestSheet.init(parametros);

    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>