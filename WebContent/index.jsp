<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>

<jsp:include page="./header.jsp" />

<!-- Custom styles for this template -->
<link href="./assets/css/global.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 3.5rem;
}
</style>

<title>Gestione articoli</title>
</head>

<body>

	<jsp:include page="./navbar.jsp"></jsp:include>

	<main role="main">
		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<div class="container">
				<h1 class="display-3">
					Gestionale articoli 
					<br>
				</h1>
					<div class="display-4">
						Bentornato ${sessionScope.user.nome}!
					</div>
				<p>This is a template for a simple marketing or informational
					website. It includes a large callout called a jumbotron and three
					supporting pieces of content. Use it as a starting point to create
					something more unique.
				</p>
				<div class="row">
					<p>
						<a class="btn btn-primary btn-lg" href="ListArticoliServlet"
							role="button">Lista degli Articoli &raquo;
						</a>
					</p>
					&nbsp;
					<p>
						<a class="btn btn-primary btn-lg" href="ListCategorieServlet"
							role="button">Lista delle categorie &raquo;
						</a>
					</p>
					&nbsp;
					<p>
						<a class="btn btn-secondary btn-lg" href="articolo/cerca_articolo.jsp"
							role="button">Cerca articolo &raquo;
						</a>
					</p>
					&nbsp;
					<p>
						<a class="btn btn-secondary btn-lg" href="categoria/cerca_categoria.jsp"
							role="button">Cerca categoria &raquo;
						</a>
					</p>
				</div>
			</div>
		</div>

		<div class="container" >
			<!-- Example row of columns -->
			<div class="row">
				<div class="col-md-4">
					<h2>
						Guests
						<c:if test="${sessionScope.user.ruolo eq 'guest'}">
							<span class="badge badge-warning">(You)</span>
						</c:if>
					</h2>
					<p>Sono limitati ad osservare.</p>
				</div>
				<div class="col-md-4">
					<h2>
						Operators
						<c:if test="${sessionScope.user.ruolo eq 'operator'}">
							<span class="badge badge-warning">(You)</span>
						</c:if>
					</h2>
					<p>Possono vedere, inserire e modificare tutto.</p>
				</div>
				<div class="col-md-4">
					<h2>
						Administrators
						<c:if test="${sessionScope.user.ruolo eq 'admin'}">
							<span class="badge badge-warning">(You)</span>
						</c:if>
					</h2>
					<p>Sono onnipotenti.</p>

				</div>
			</div>

			<hr>

		</div>
		<!-- /container -->
	</main>

	<jsp:include page="./footer.jsp" />
</body>
</html>