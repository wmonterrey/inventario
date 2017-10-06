<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!-- BEGIN HEADER -->
<div class="header navbar navbar-inverse navbar-fixed-top">
	<!-- BEGIN TOP NAVIGATION BAR -->
	<div class="header-inner">
		<!-- BEGIN LOGO -->
		<a class="navbar-brand" href="<spring:url value="/" htmlEscape="true "/>">
		<spring:url value="/resources/img/logo-inv.png" var="logo" />
		<spring:url value="/resources/img/us.png" var="usflag" />
		<spring:url value="/resources/img/es.png" var="spflag" />
		<img src="${logo}" alt="logo" class="img-responsive"/>
		</a>
		<!-- END LOGO -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		<spring:url value="/resources/img/menu-toggler.png" var="menutoggler" />
		<img src="${menutoggler}" alt=""/>
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		<!-- BEGIN TOP NAVIGATION MENU -->
		<ul class="nav navbar-nav pull-right">
			<li class="dropdown user">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
				<i class="fa fa-building"></i>
				<span class="username">
					 ${usuario.userCenter.centerName}
				</span>
				<i class="fa fa-angle-down"></i>
				</a>
				<ul class="dropdown-menu">
					<li>
						<a href="<spring:url value="/users/center" htmlEscape="true" />"><i class="fa fa-building"></i> <spring:message code="change" /> <spring:message code="class ni.org.ics.lab.inventario.domain.Center" /></a>
					</li>
				</ul>
			</li>
			<li class="devider">
				 &nbsp;
			</li>
			<li>
				<a href="?lang=en"><img src="${usflag}" class="img-responsive"/></a>
			</li>
			<li>
				<a href="?lang=es"><img src="${spflag}" class="img-responsive"/></a>
			</li>
			<li class="devider">
				 &nbsp;
			</li>
			<!-- BEGIN USER LOGIN DROPDOWN -->
			<li class="dropdown user">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
				<i class="fa fa-user"></i>
				<span class="username">
					 <sec:authentication property="principal.username"/>
				</span>
				<i class="fa fa-angle-down"></i>
				</a>
				<ul class="dropdown-menu">
					<li>
						<a href="<spring:url value="/users/profile" htmlEscape="true" />"><i class="fa fa-user"></i> <spring:message code="profile" /></a>
					</li>
					<li>
						<a href="<spring:url value="/users/chgpass" htmlEscape="true" />"><i class="fa fa-key"></i> <spring:message code="changepass" /></a>
					</li>
					<li>
						<a href="<spring:url value="/logout" htmlEscape="true" />"><i class="fa fa-sign-out"></i> <spring:message code="logout" /></a>
					</li>
				</ul>
			</li>
		<!-- END USER LOGIN DROPDOWN -->
		</ul>
	<!-- END TOP NAVIGATION MENU -->
	</div>
<!-- END TOP NAVIGATION BAR -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>