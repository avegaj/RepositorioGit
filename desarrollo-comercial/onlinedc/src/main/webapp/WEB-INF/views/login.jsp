<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>Desarrollo Comercial</title>

<!-- Bootstrap -->
<link href="static/css/bootstrap/bootstrap.min.css" rel="stylesheet">

<!-- Font awesome -->
<link href="static/font-awesome/css/font-awesome.css" rel="stylesheet">

<!-- Main Inspinia CSS files -->
<link href="static/css/animate.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">

</head>
<!--Fondo para el login-->
<body style="background-color: #f3f3f4">

	<div class="loginColumns animated fadeInDown">
		<div class="row">

			<div class="col-md-6">
				<h2 class="font-bold">Bienvenido a PROMETEO</h2>

				<p>Diseñado con el proposito de responder a las necesidades con
					eficiencia y calidad.</p>

				<p>Trasformado ideas.</p>

			</div>
			<div class="col-md-6">
				<div class="ibox-content">
					<c:url var="loginUrl" value="/login" />
					<form class="m-t" role="form" action="${loginUrl}" method="post">
						<c:if test="${param.error != null}">
							<div class="alert-danger">
								<p class="text-center">¡Algo salió mal!</p>
							</div>
						</c:if>
						<c:if test="${param.logout != null}">
							<div class="alert-success">
								<p class="text-center">¡Nos vemos!</p>
							</div>
						</c:if>
						<div class="form-group">
							<label for="username"><i></i></label> <input type="text"
								class="form-control" id="username" name="username"
								placeholder="Usuario" required>
						</div>
						<div class="form-group">
							<label for="password"><i></i></label> <input type="password"
								class="form-control" id="password" name="password"
								placeholder="Contraseña" required>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<button type="submit" class="btn btn-primary block full-width m-b">Inicio</button>
						<p>
							<a href="#"> <small>¿No recuerdas la contraseña?</small>
							</a>
						</p>
						<!-- <p class="text-muted text-center">
							<small>¿No tienes cuenta?</small>
						</p>
						<a class="btn btn-sm btn-white btn-block" href="#">Crear una
							cuenta</a> -->
					</form>
					<p class="m-t">
						<small>Desarrollo Comercial</small>
					</p>
				</div>
			</div>
		</div>
		<hr />
		<div class="row">
			<div class="col-md-6">By @eloyus @alex @oty</div>
			<div class="col-md-6 text-right">
				<small>2017-2018</small>
			</div>
		</div>
	</div>
</body>
</html>
