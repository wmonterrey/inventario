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
                                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/addloc/location" htmlEscape="true "/>"><spring:message code="addalic" /></a>
                            </li>
                        </ul>
                        <!-- END PAGE TITLE & BREADCRUMB-->
                    </div>
                </div>
                <!-- END PAGE HEADER-->
                <!-- BEGIN PAGE CONTENT-->

                <div class="row">
                    <div class="col-md-5">
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
                                            <label class="control-label col-md-4"><spring:message code="rackEquip" />
                                                <span class="required">
											 *
										</span>
                                            </label>
                                            <div class="col-md-8">
                                                <select data-placeholder="<spring:message code="select" /> <spring:message code="rackEquip" />" name="rackEquip" id="rackEquip" class="form-control">
                                                    <option value=""></option>
                                                    <c:forEach items="${equipos}" var="equipo">
                                                        <c:choose>
                                                            <c:when test="${equipo.equipCode eq rack.rackEquip.equipCode}">
                                                                <option selected value="${equipo.equipCode}"><spring:message code="${equipo.equipName}" /></option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${equipo.equipCode}"><spring:message code="${equipo.equipName}" /></option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4"><spring:message code="rackName" />:
                                                <span class="required">
											 *
										</span>
                                            </label>
                                            <div class="col-md-8">
                                                <select data-placeholder="<spring:message code="select" /> <spring:message code="rackName" />" name="rackCode" id="rackCode" class="form-control">
                                                    <option value=""></option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-md-4"><spring:message code="boxName" />:
                                                <span class="required">
											 *
										</span>
                                            </label>
                                            <div class="col-md-8">
                                                <select data-placeholder="<spring:message code="select" /> <spring:message code="boxName" />" name="boxName" id="boxName" class="form-control">
                                                    <option value=""></option>

                                                </select>
                                            </div>
                                        </div>


                                    </div>

                                    <div class="form-actions fluid">
                                        <div class="col-md-offset-5 col-md-6">
                                            <button id="btnLoad" name="btnLoad" class="btn btn-primary"><spring:message code="ok" /></button>
                                            <a href="${fn:escapeXml(boxUrl)}" class="btn btn-danger"><spring:message code="end" /></a>
                                        </div>
                                    </div>


                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-7">
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
                            <div class="display-hide form-actions" id="legend" >

                                <div class="col-sm-3">
                                    <input style="background-color: #428bca" class="form-control">
                                    <label><spring:message code="available" />
                                    </label>
                                </div>

                                <div class="col-sm-3">

                                    <input style="background-color: #d9534f" class="form-control">
                                    <label><spring:message code="notAvailable" />
                                    </label>
                                </div>
                            </div>
                            <div class="portlet-body form">
                                <div class="grid" id="caja" >

                                </div>

                            </div>


                        </div>
                    </div>

                    <!-- Modal -->
                    <div class="modal fade" id="alicModal" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header" style="background-color: #428bca">
                                    <h5 class="modal-title" style="font: bold"> <spring:message code="addalic" /></h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="#" autocomplete="off" id="save-alic-form" class="form-horizontal">
                                        <input type="text" class="display-hide" name="boxId" id="boxId">
                                        <input type="text" class="display-hide" name="pos" id="pos">
                                        <input type="text" class="display-hide" name="type" id="type">
                                        <div class="form-body">
                                            <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>
                                                <spring:message code="form.errors" />
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-md-4"><spring:message code="aliCode" />
                                                    <span class="required">
											 *
										</span>
                                                </label>
                                                <div class="col-md-8">
                                                    <div class="input-group">
                                                        <input  id="aliCode" name="aliCode" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="aliCode" />" class="form-control"/>
                                                        <span class="input-group-addon">
													<i class="fa fa-barcode"></i>
												</span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-md-4"><spring:message code="alicTypeName" />
                                                    <span class="required">
											 *
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
                                                <label class="control-label col-md-4"><spring:message code="aliVol" />
                                                    <span class="required">
											 *
										</span>
                                                </label>
                                                <div class="col-md-8">
                                                    <div class="input-group">
                                                        <input id="vol" name="vol" type="text" placeholder="<spring:message code="please.enter" /> <spring:message code="aliVol" />" class="form-control"/>
                                                        <span class="input-group-addon">
													<i class="fa fa-keyboard-o"></i>
												</span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-md-4"><spring:message code="aliCond" />
                                                    <span class="required">
											 *
										</span>
                                                </label>
                                                <div class="col-md-8">
                                                    <select data-placeholder="<spring:message code="select" /> <spring:message code="aliCond" />"
                                                            class="form-control" id="aliCond" name="aliCond">
                                                        <option value=""></option>
                                                        <c:forEach items="${msj}" var="cond">
                                                            <option value="${cond.messageKey}"><spring:message
                                                                    code="${cond.spanish}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-md-4"><spring:message code="centerObs" />
                                                </label>

                                                <div class="col-md-8">
                                                    <textarea placeholder="<spring:message code="please.enter" /> <spring:message code="centerObs" />" class="form-control" id="obs" name="obs" rows="3"></textarea>
                                                </div>

                                            </div>


                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" id="btnClose" name="btnClose" class="btn btn-secondary" data-dismiss="modal"><spring:message code="cancel" /></button>
                                            <button id="guardar" id="btnSave" class="btn btn-success"><spring:message code="save" /></button>
                                        </div>


                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>

                    <!-- Modal -->
                    <div class="modal fade" id="alicModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header" style="background-color: #428bca">
                                    <h5 class="modal-title"> <spring:message code="aliquotData" /></h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="#" autocomplete="off" id="alic-data" class="form-horizontal">
                                        <input type="text" class="display-hide" name="boxId2" id="boxId2">

                                        <div class="form-body">
                                            <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>
                                                <spring:message code="form.errors" />
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-4"><spring:message code="aliPosition" />:
                                                </label>
                                                <div class="col-md-8">
                                                    <input type="text"  class="form-control" disabled name="pos2" id="pos2">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-md-4"><spring:message code="aliCode" />

                                                </label>
                                                <div class="col-md-8">
                                                        <input type="text" class="form-control" disabled  id="aliCode2" name="aliCode2"  />
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-md-4"><spring:message code="aliVol" />

                                                </label>
                                                <div class="col-md-8">
                                                        <input disabled id="vol2" type="text" class="form-control" name="vol2" type="text"  />
                                                    </div>
                                                </div>


                                            <div class="form-group">
                                                <label class="control-label col-md-4"><spring:message code="aliCond" />

                                                </label>

                                                <div class="col-md-8">
                                                    <select  disabled data-placeholder="<spring:message code="select" /> <spring:message code="aliCond" />"
                                                            class="form-control" id="condition2" name="condition2">
                                                        <option value=""></option>
                                                        <c:forEach items="${msj}" var="cond">
                                                            <option value="${cond.messageKey}"><spring:message
                                                                    code="${cond.spanish}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                            </div>



                                            <div class="form-group">
                                                <label class="control-label col-md-4"><spring:message code="centerObs" />
                                                </label>

                                                <div class="col-md-8">
                                                    <textarea  class="form-control" disabled id="obs2" name="obs2" rows="3"></textarea>
                                                </div>

                                            </div>


                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="cancel" /></button>
                                        </div>


                                    </form>
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
<spring:url value="/resources/scripts/alics/process-loc.js" var="locScript" />
<script src="${locScript}" type="text/javascript"></script>
<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/plugins/select2/select2_locale_{language}.js" var="Select2Loc">
<spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${Select2Loc}"></script>

<spring:url value="/resources/plugins/isotope/isotope.pkgd.js" var="isotope" />
<script type="text/javascript" src="${isotope}"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<spring:url value="/admin/racks/racks" var="racksUrl"/>
<spring:url value="/admin/boxes/boxes" var="boxesUrl"/>
<spring:url value="/addloc/getBox" var="boxUrl"/>

<spring:url value="/addloc/saveAliLoc" var="saveAlicUrl"/>
<spring:url value="/addloc/getAlicStudyByBox" var="getAlicUrl"/>
<spring:url value="/addloc/getActiveAliquots" var="getActiveAliquotsUrl"/>
<spring:url value="/addloc/getAliquot" var="getAliquotUrl"/>

<c:set var="successmessage"><spring:message code="process.success" /></c:set>
<c:set var="errormessage"><spring:message code="process.errors" /></c:set>
<c:set var="aliNotPattern"><spring:message code="aliNotPattern" /></c:set>
<c:set var="aliNotInList"><spring:message code="aliNotInList" /></c:set>
<c:set var="regExpInv"><spring:message code="regExpInv" /></c:set>

<script>
    $(function () {
        $("li.addalic").removeClass("addalic").addClass("active");
        $("li.newalicman").removeClass("newalicman").addClass("active");
    });
</script>
<script>
    jQuery(document).ready(function() {
        $('#aliCond').select2({
            dropdownParent: $('#alicModal')
        });
        App.init();
        var parametros = {saveAlicUrl: "${saveAlicUrl}",
            getAlicUrl: "${getAlicUrl}",
            successmessage: "${successmessage}",
            aliNotPattern: "${aliNotPattern}",
            aliNotInList: "${aliNotInList}",
            regExpInv: "${regExpInv}",
            racksUrl: "${racksUrl}",
            boxesUrl: "${boxesUrl}",
            boxLocUrl: "${boxUrl}",
            saveAlicUrl: "${saveAlicUrl}",
            getActiveAliquotsUrl: "${getActiveAliquotsUrl}",
            getAliquotUrl: "${getAliquotUrl}",
            errormessage: "${errormessage}"};

        CreateLocation.init(parametros);

    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>