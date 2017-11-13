<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row border-bottom">
	<nav class="navbar navbar-fixed-top gray-bg" role="navigation"
		style="margin-bottom: 0">
		<div class="navbar-header">
			<span minimaliza-sidebar></span>
			<!--  
			<form role="search" class="navbar-form-custom" method="post"
				action="">
				<div class="form-group">
					<input type="text" placeholder="Vamos a buscar..."
						class="form-control" name="top-search" id="top-search">
				</div>
			</form>
			-->
		</div>
		<ul class="nav navbar-top-links navbar-right">
			<li><a href="<c:url value="/logout" />"> <i
					class="fa fa-sign-out"></i> Salir
			</a></li>
		</ul>
	</nav>
</div>