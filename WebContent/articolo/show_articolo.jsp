<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Dettagli articolo</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

</head>

<c:set var="articolo" scope="page"
	value="${requestScope.articoloDaInviareAPaginaVisualizza }" />

<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class='card'>
			<div class='card-header'>Dettagli articolo</div>


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
					<dt class="col-sm-3 text-right">Categoria:</dt>
					<dd class="col-sm-9">
						<c:out value="${articolo.categoria.descrizione}" />
					</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Prezzo:</dt>
					<dd class="col-sm-9">
						<c:out value="${articolo.prezzo}€" />
					</dd>
				</dl>

			</div>

			<div class='card-footer'>
				<button onclick="window.history.go(-1); return false;" type="submit" value="back"
					class='btn btn-outline-secondary' style='width: 85px'>Indietro
				</button>
			</div>
		</div>

		<!-- end main container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>