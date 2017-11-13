<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="images" value="/static/images" />

<nav class="navbar-default navbar-static-side" role="navigation">
	<div class="sidebar-collapse">
		<ul side-navigation class="nav metismenu" id="side-menu">
			<li class="nav-header">

				<div class="profile-element" uib-dropdown>
					<img ui-sref="home.welcome" alt="image" class="img-circle"
						src="${images}/chick_2.png" /> <a uib-dropdown-toggle href="">
						<span class="clear"> <span class="block m-t-xs"> <strong
								class="font-bold">Eloy</strong>
						</span> <span class="text-muted text-xs block">Opciones<b
								class="caret"></b>
						</span>
					</span>
					</a>
					<ul uib-dropdown-menu="" class="animated fadeInRight m-t-xs">
						<li><a href="<c:url value="/logout" />">Salir</a></li>
					</ul>
				</div>
				<div class="logo-element">DC</div>

			</li>
			<!--<security:authorize access="hasAuthority('ROLE_ADMIN')">
            <li ui-sref-active="active">
                <a ui-sref="home.main"><i class="fa fa-address-book-o"></i> <span class="nav-label">Usuarios</span> </a>
            </li>
            </security:authorize>-->
			<li ng-class="{active: $state.includes('home.page1')}"><a
				href="#"><i class="fa fa-address-book"></i> <span
					class="nav-label">Jerarquía</span> <span class="fa arrow"></span></a>
				<ul class="nav nav-second-level collapse"
					ng-class="{in: $state.includes('home.page1')}">
					<li ui-sref-active="active"><a ui-sref="home.page1">Usuarios</a></li>
					<li ui-sref-active="active"><a ui-sref="#">Nuevo usuario</a></li>
				</ul></li>
			<li ui-sref-active="active"><a ui-sref="#"><i
					class="fa fa-desktop"></i> <span class="nav-label">Un
						vistaso</span></a></li>
		</ul>
	</div>
</nav>