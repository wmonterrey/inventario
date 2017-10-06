<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- BEGIN CORE PLUGINS -->
<spring:url value="/resources/plugins/respond.min.js" var="Respond" />
<spring:url value="/resources/plugins/excanvas.min.js" var="Excanvas" />
<!--[if lt IE 9]>
<script src="${Respond}"></script>
<script src="${Excanvas}"></script> 
<![endif]-->
<!-- jQuery -->
<spring:url value="/resources/plugins/jquery-1.10.2.min.js" var="jQuery" />
<script src="${jQuery}" type="text/javascript"></script>
<!-- jQuery Migrate-->
<spring:url value="/resources/plugins/jquery-migrate-1.2.1.min.js" var="jQueryMigrate" />
<script src="${jQueryMigrate}" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<!-- jQuery UI-->
<spring:url value="/resources/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" var="jQueryUI" />
<script src="${jQueryUI}" type="text/javascript"></script>
<!-- Bootstrap-->
<spring:url value="/resources/plugins/bootstrap/js/bootstrap.min.js" var="Bootstrap" />
<script src="${Bootstrap}" type="text/javascript"></script>
<!-- Bootstrap Hover Dropdown-->
<spring:url value="/resources/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" var="BootstrapHover" />
<script src="${BootstrapHover}" type="text/javascript"></script>
<!-- Bootstrap Hover Dropdown-->
<spring:url value="/resources/plugins/jquery-slimscroll/jquery.slimscroll.min.js" var="SlimScroll" />
<script src="${SlimScroll}" type="text/javascript"></script>
<!-- jQuery Block UI-->
<spring:url value="/resources/plugins/jquery.blockui.min.js" var="jQueryBlockUI" />
<script src="${jQueryBlockUI}" type="text/javascript"></script>
<!-- jQuery Cookie-->
<spring:url value="/resources/plugins/jquery.cokie.min.js" var="jQueryCookie" />
<script src="${jQueryCookie}" type="text/javascript"></script>
<!-- jQuery Uniform-->
<spring:url value="/resources/plugins/uniform/jquery.uniform.min.js" var="jQueryUniform" />
<script src="${jQueryUniform}" type="text/javascript"></script>
<!-- jQuery Idle Time Out-->
<spring:url value="/resources/plugins/jquery-idle-timeout/jquery.idletimeout.js" var="idleTimeout" />
<script src="${idleTimeout}" type="text/javascript"></script>
<spring:url value="/resources/plugins/jquery-idle-timeout/jquery.idletimer.js" var="idleTimer" />
<script src="${idleTimer}" type="text/javascript"></script>
<!-- Bootstrap Toastr-->
<spring:url value="/resources/plugins/bootstrap-toastr/toastr.min.js" var="toastR" />
<script type="text/javascript" src="${toastR}"></script>
<!-- END CORE PLUGINS -->