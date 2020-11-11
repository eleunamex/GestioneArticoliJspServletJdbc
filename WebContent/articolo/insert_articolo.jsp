<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Inserisci nuovo</title>

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
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Inserisci nuovo elemento</h5>
			</div>
			<div class='card-body'>

				<h6 class="card-title">
					I campi con <span class="text-danger">*</span> sono obbligatori
				</h6>

				<form method="post" action="ExecuteInsertArticoloServlet"
					novalidate="novalidate" id="form">

					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Codice 
								<span class="text-danger">*</span>
							</label> <input
								type="text" name="codice" id="codice" class="form-control"
							placeholder="Inserire il codice" required>
						</div>

						<div class="form-group col-md-6">
							<label>Descrizione 
								<span class="text-danger">*</span>
							</label> 
							<input
								type="text" name="descrizione" id="descrizione"
								class="form-control" placeholder="Inserire la descrizione"
							required>
						</div>
					</div>

					<div class="form-row">

						<div class="form-group col md-6">
							<label for="exampleFormControlSelect1">Categoria</label> 
							<select class="form-control" id="idCategoria" name="idCategoria">
								<c:forEach items="${requestScope.listaCategorieAttribute}" var="categoria">
									<option value="${categoria.id}">${categoria.descrizione}</option>
								</c:forEach>
							</select>
						</div>


						<div class="form-group col-md-6">
							<label>Prezzo 
								<span class="text-danger">*</span>
							</label> <input
								type="number" class="form-control" name="prezzo" id="prezzo"
								placeholder="Inserire prezzo" required>
						</div>

					</div>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Conferma
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