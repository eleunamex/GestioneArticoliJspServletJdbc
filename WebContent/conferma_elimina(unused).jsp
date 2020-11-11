<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="./header.jsp" />
<title>Conferma elimina</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>

<c:set var="articolo" scope="page"
	value="${requestScope.articoloDaInviareAPaginaConfermaElimina }" />
<body>
	<jsp:include page="./navbar.jsp" />

	<main role="main" class="container">

		<div class='card'>
			<div class='card-header'>Vuoi eliminare questo articolo?</div>

			<div class='card-body'>
				<dl class="row">
					<dt class="col-sm-3 text-right">ID:</dt>
					<dd class="col-sm-9">
						<c:out value="${articolo.id}" />
					</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Codice:</dt>
					<dd class="col-sm-9">
						<c:out value="${articolo.codice}" />
					</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Descrizione:</dt>
					<dd class="col-sm-9">
						<c:out value="${articolo.descrizione}" />
					</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Prezzo:</dt>
					<dd class="col-sm-9">
						<c:out value="${articolo.prezzo}" />
					</dd>
				</dl>

			</div>

			<div class='card-footer'>
				<a href="${pageContext.request.contextPath}/ListArticoliServlet" 
					class='btn btn-outline-secondary' style='width: 80px'></a> 
				<i class='fa fa-chevron-left'></i> 
					Annulla 
				<a href="DeleteArticoloServlet?idDaInviareComeParametro=<c:out value="${articolo.id}"/>"
					class='btn btn-outline-danger' style='width: 80px'></a> 
				<i class='fa fa-chevron-left'></i> 
					Elimina
			</div>
		</div>

		<!-- end main container -->
	</main>
	<jsp:include page="./footer.jsp" />
</body>
</html>