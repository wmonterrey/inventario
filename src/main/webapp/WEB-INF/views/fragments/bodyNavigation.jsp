<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- BEGIN SIDEBAR -->
<div class="page-sidebar-wrapper">
    <div class="page-sidebar-wrapper">
        <div class="page-sidebar navbar-collapse collapse">
            <!-- BEGIN SIDEBAR MENU -->
            <ul class="page-sidebar-menu">
                <li class="sidebar-toggler-wrapper">
                    <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                    <div class="sidebar-toggler">
                    </div>
                    <div class="clearfix">
                    </div>
                    <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                </li>
                <li class="start">
                    <a href="<spring:url value="/" htmlEscape="true "/>">
                        <i class="fa fa-home"></i>
                        <span class="title">
						<spring:message code="home"/>
					</span>
                        <span class="selected">
					</span>
                    </a>
                </li>
                <sec:authorize url="/addalic/">
                    <li class="addalic">
                        <a href="javascript:;">
                            <i class="fa fa-plus"></i>
                            <span class="title">
						<spring:message code="addalic"/>
					</span>
                            <span class="arrow ">
					</span>
                        </a>
                        <ul class="sub-menu">
                            <li class="newalicsug">
                                <a href="<spring:url value="/addalic/newAlicSug" htmlEscape="true "/>">
                                    <i class="fa fa-calculator"></i>
                                    <spring:message code="newalicsug"/></a>
                            </li>
                            <li class="newalicman">
                                <a href="<spring:url value="/addalic/location" htmlEscape="true "/>">
                                    <i class="fa fa-hand-o-up"></i>
                                    <spring:message code="newalicman"/></a>
                            </li>
                            <li class="newalicuse">
                                <a href="<spring:url value="/alicUse/enterForm" htmlEscape="true "/>">
                                    <i class="fa fa-edit"></i>
                                    <spring:message code="alic_use_menu"/></a>
                            </li>
                            <li class="exitalic">
                                <a href="<spring:url value="/alicoutput/exitForm" htmlEscape="true "/>">
                                    <i class="fa fa-sign-out"></i>
                                    <spring:message code="alic_output"/></a>
                            </li>

                            <li class="transferAlic">
                                <a href="<spring:url value="/alictransfer/transferAlic" htmlEscape="true "/>">
                                    <i class="fa fa-plane"></i>
                                    <spring:message code="transfer_alic"/></a>
                            </li>

                        </ul>
                    </li>
                </sec:authorize>
                <sec:authorize url="/simlab/">
                    <li class="simlab">
                        <a href="javascript:;">
                            <i class="fa fa-database"></i>
                            <span class="title">
						<spring:message code="simlab_menu"/>
					</span>
                            <span class="arrow ">
					</span>
                        </a>
                        <ul class="sub-menu">
                            <li class="searchAlic">
                                <a href="<spring:url value="/searchalic/enterForm" htmlEscape="true "/>">
                                    <i class="fa fa-search"></i>
                                    <spring:message code="searchalic"/></a>
                            </li>

                        </ul>
                    </li>
                </sec:authorize>
                <sec:authorize url="/alics/">
                    <li class="alics">
                        <a href="javascript:;">
                            <i class="fa fa-eyedropper"></i>
                            <span class="title">
						<spring:message code="alics"/>
					</span>
                            <span class="arrow ">
					</span>
                        </a>
                        <ul class="sub-menu">
                            <li class="searchalic">
                                <a href="<spring:url value="/alics/search/" htmlEscape="true "/>">
                                    <i class="fa fa-search"></i>
                                    <spring:message code="searchalic"/></a>
                            </li>
                            <sec:authorize url="/alics/use/">
                                <li class="usealic">
                                    <a href="<spring:url value="/alics/use/" htmlEscape="true "/>">
                                        <i class="fa fa-check"></i>
                                        <spring:message code="usealic"/></a>
                                </li>
                            </sec:authorize>
                            <sec:authorize url="/alics/send/">
                                <li class="sendalic">
                                    <a href="<spring:url value="/alics/send/" htmlEscape="true "/>">
                                        <i class="fa fa-send"></i>
                                        <spring:message code="sendalic"/></a>
                                </li>
                            </sec:authorize>
                        </ul>
                    </li>
                </sec:authorize>
                <sec:authorize url="/catalog/">
                    <li class="catalog">
                        <a href="javascript:;">
                            <i class="fa fa-th-large"></i>
                            <span class="title">
						<spring:message code="catalog"/>
					</span>
                            <span class="arrow ">
					</span>
                        </a>
                        <ul class="sub-menu">
                            <li class="catalogalic">
                                <a href="<spring:url value="/catalog/alics/" htmlEscape="true "/>">
                                    <i class="fa fa-th"></i>
                                    <spring:message code="catalogalic"/></a>
                            </li>
                            <li class="catalogall">
                                <a href="<spring:url value="/catalog/all/" htmlEscape="true "/>">
                                    <i class="fa fa-info-circle"></i>
                                    <spring:message code="catalogall"/></a>
                            </li>
                        </ul>
                    </li>
                </sec:authorize>
                <sec:authorize url="/temps/">
                    <li class="temprecord">
                        <a href="<spring:url value="/temps/" htmlEscape="true "/>">
                            <i class="fa fa-thermometer-half"></i>
                            <spring:message code="temprecord"/></a>
                    </li>
                </sec:authorize>
                <sec:authorize url="/translation/">
                    <li class="translation">
                        <a href="<spring:url value="/translation/" htmlEscape="true "/>">
                            <i class="fa fa-flag"></i>
                            <spring:message code="translation"/></a>
                    </li>
                </sec:authorize>
                <sec:authorize url="/super/">
                    <li class="super">
                        <a href="javascript:;">
                            <i class="fa fa-wrench"></i>
                            <span class="title">
						<spring:message code="super"/>
					</span>
                            <span class="arrow ">
					</span>
                        </a>
                        <ul class="sub-menu">
                            <li class="supercenters">
                                <a href="<spring:url value="/super/centers/" htmlEscape="true "/>">
                                    <i class="fa fa-building"></i>
                                    <spring:message code="supercenters"/></a>
                            </li>
                            <li class="superstudies">
                                <a href="<spring:url value="/super/studies/" htmlEscape="true "/>">
                                    <i class="fa fa-briefcase"></i>
                                    <spring:message code="superstudies"/></a>
                            </li>
                            <li class="superrooms">
                                <a href="<spring:url value="/super/rooms/" htmlEscape="true "/>">
                                    <i class="fa fa-university"></i>
                                    <spring:message code="superrooms"/></a>
                            </li>
                        </ul>
                    </li>
                </sec:authorize>
                <sec:authorize url="/admin/">
                    <li class="admin">
                        <a href="javascript:;">
                            <i class="fa fa-cogs"></i>
                            <span class="title">
						<spring:message code="admin"/>
					</span>
                            <span class="arrow ">
					</span>
                        </a>
                        <ul class="sub-menu">
                            <li class="adminusers">
                                <a href="<spring:url value="/admin/users/" htmlEscape="true "/>">
                                    <i class="fa fa-group"></i>
                                    <spring:message code="adminusers"/></a>
                            </li>
                            <li class="adminequips">
                                <a href="<spring:url value="/admin/equips/" htmlEscape="true "/>">
                                    <i class="fa fa-columns"></i>
                                    <spring:message code="adminequips"/></a>
                            </li>
                            <li class="adminracks">
                                <a href="<spring:url value="/admin/racks/" htmlEscape="true "/>">
                                    <i class="fa fa-database"></i>
                                    <spring:message code="adminracks"/></a>
                            </li>
                            <li class="adminboxes">
                                <a href="<spring:url value="/admin/boxes/" htmlEscape="true "/>">
                                    <i class="fa fa-cubes"></i>
                                    <spring:message code="adminboxes"/></a>
                            </li>
                        </ul>
                    </li>
                </sec:authorize>
                <li class="last ">
                    <a href="<spring:url value="/logout" htmlEscape="true" />">
                        <i class="fa fa-sign-out"></i>
                        <span class="title">
						<spring:message code="logout"/>
					</span>
                    </a>
                </li>
            </ul>
            <!-- END SIDEBAR MENU -->
        </div>
    </div>
</div>
<!-- END SIDEBAR -->
