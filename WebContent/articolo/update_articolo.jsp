<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Modifica articolo</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/js/jquery-3.4.1.min.js"></script>
<script>
	$(function() {
		$("#submit").click(function() {
			validateForm();
		});

		function validateForm() {
			var codice = $('#codice').val();
			var descrizione = $('#descrizione').val();
			var prezzo = $('#prezzo').val();

			if (codice == "") {
				alert('Codice non è valido');
				stopSubmit();
			}
			if (descrizione == "") {
				alert('Descrizione non è valida');
				stopSubmit();
			}
			if (prezzo == "" || !$.isNumeric(prezzo) || prezzo < 1  )  {
				alert('Prezzo non è valido');
				stopSubmit();
			}
			location.reload();
		}
		
		function stopSubmit() {
			$("#form").submit(function(e){
		        e.preventDefault();
		    });
		}
	});
</script>
</head>
<c:set var="articolo" scope="page" value="${requestScope.articoloDaInviareAPaginaModifica}" />

<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
			Operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Modifica articolo</h5>
			</div>
			<div class='card-body'>

				<h6 class="card-title">
					I campi con <span class="text-danger">*</span> sono obbligatori
				</h6>

				<form method="post" action="ExecuteUpdateArticoloServlet" novalidate="novalidate" id="form">

					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Codice 
								<span class="text-danger">*</span>
							</label> 
							<input
								type="text" name="codice" id="codice" class="form-control"
								value="<c:out value="${articolo.codice}"/>" required
							>
						</div>

						<div class="form-group col-md-6">
							<label>Descrizione
								<span class="text-danger">*</span>
							</label> 
							<input
								type="text" name="descrizione" id="descrizione"
								class="form-control"
								value="<c:out value="${articolo.descrizione}"/>" required
							>
						</div>
					</div>

					<div class="form-row">

					<div class="form-group col md-6">
						<label for="exampleFormControlSelect1">Categoria</label> <select
							class="form-control" id="idCategoria" name="idCategoria">
							<c:forEach items="${requestScope.listaCategorieAttribute}" var="categoria">
								<option value="${categoria.id}" ${categoria.id == articolo.categoria.id ? 'selected' : ''}>
									${categoria.descrizione}
								</option>
							</c:forEach>
						</select>
					</div>


						<div class="form-group col-md-3">
							<label>Prezzo 
								<span class="text-danger">*</span>
							</label> 
							<input
								type="number" class="form-control" name="prezzo" id="prezzo"
								value="<c:out value="${articolo.prezzo}"/>" required
							>
						</div>

					</div>
					<input type="hidden" class="form-control" name="id" id="id"
						value="<c:out value="${articolo.id}"/>" readonly
					>
					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Aggiorna
					</button>

				</form>

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