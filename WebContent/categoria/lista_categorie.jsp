<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Lista categorie</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}"
			role="alert">
			${successMessage}
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
			Esempio di operazione fallita!
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
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
				<h5>Lista delle categorie</h5>
			</div>
			<div class='card-body'>
				<c:if test="${sessionScope.user.ruolo eq 'admin' || sessionScope.user.ruolo eq 'operator'}">
					<a class="btn btn-primary " href="PrepareInsertCategoriaServlet">Aggiungi
						categoria
					</a>
				</c:if>
				
				<c:if test="${!empty requestScope.listaCategorieAttribute}">
					<div class='table-responsive'>
						<table class='table table-striped '>
							<thead>
								<tr>
									<th>Id</th>
									<th>Descrizione</th>
									<th>Azioni</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="categoria" items="${requestScope.listaCategorieAttribute}">
									<tr>
										<td><c:out value="${categoria.id }" /></td>
										<td><c:out value="${categoria.descrizione }" /></td>
										<td>
											<a class="btn  btn-sm btn-outline-secondary ml-2 mr-2"
												href="VisualizzaCategoriaServlet?idDaInviareComeParametro=
												<c:out value="${categoria.id}"/>">
												Visualizza categoria
											</a> 
											<c:if test="${sessionScope.user.ruolo eq 'admin' || sessionScope.user.ruolo eq 'operator'}">
												<a class="btn  btn-sm btn-outline-primary ml-2 mr-2"
													href="PrepareUpdateCategoriaServlet?idDaInviareComeParametro=
													<c:out value="${categoria.id}"/>">Modifica
												</a>
											</c:if> 
											<c:if test="${sessionScope.user.ruolo eq 'admin'}">
												<button class="btn btn-outline-danger btn-sm"
													data-toggle="modal"
													data-target="#myModal<c:out value="${categoria.id}"/>">
													Elimina
												</button>
											</c:if>
										</td>
	
									</tr>
	
									<!-- MODAL DELETE -->
									<div class="modal fade"
										id="myModal<c:out value="${categoria.id}"/>" tabindex="-1"
										role="dialog" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">Vuoi
														eliminare la categoria?
													</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body" id="idar" id="idar">
													${categoria.descrizione}
													<c:out value="" />
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-dismiss="modal">Annulla
													</button>
													<a type="button" class="btn btn-danger"
														href="DeleteCategoriaServlet?idDaInviareComeParametro=
														<c:out value="${categoria.id}"/>">Delete
													</a>
												</div>
											</div>
										</div>
									</div>
									<!-- END  MODAL DELETE -->
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
				<c:if test="${empty requestScope.listaCategorieAttribute}">
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