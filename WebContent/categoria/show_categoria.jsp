<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Dettagli categoria</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
			${successMessage}
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
			Esempio di operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
			Aggiungere d-none nelle class per non far apparire
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Dettagli categoria</h5>
				<hr>
				<h6>
					ID: ${requestScope.categoriaAttribute.id }<br>
					Descrizione: ${requestScope.categoriaAttribute.descrizione }
				</h6>
			</div>
			<div class='card-body'>
		<h5><span class="badge badge-primary">Lista articoli appartenenti</span></h5>

			<c:if test="${!empty requestScope.listaArticoliAttribute}">

				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Id</th>
								<th>Codice</th>
								<th>Descrizione</th>
								<th>Prezzo</th>
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="articolo"
								items="${requestScope.listaArticoliAttribute}">

								<tr>
									<td><c:out value="${articolo.id}" /></td>
									<td><c:out value="${articolo.codice}" /></td>
									<td><c:out value="${articolo.descrizione}" /></td>
									<td><c:out value="${articolo.prezzo}" />€</td>
									<td>
										<a class="btn  btn-sm btn-outline-secondary"
											href="VisualizzaArticoloServlet?idDaInviareComeParametro=
											<c:out value="${articolo.id}"/>">Visualizza
										</a>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				</c:if>
				<c:if test="${empty requestScope.listaArticoliAttribute}">
				<p class="text-center">Non ci sono risultati.</p>
				</c:if>
				<!-- end card-body -->
			</div>
			<div class='card-footer'>
				<button onclick="window.history.go(-1); return false;" type="submit" value="back"
					class='btn btn-outline-secondary' style='width: 85px'>Indietro
				</button>
			</div>
		</div>

		<!-- end container -->
	</main>

	<jsp:include page="../footer.jsp" />

</body>

</html>