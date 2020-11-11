<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Cerca categoria</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/assets/js/jquery-3.4.1.min.js"></script>
<script>
$(function() {
	
	$("#submit").click(function() {
		validateForm();
	});

	
	function validateForm() {
	var descrizione = $('#descrizione').val();

		if (descrizione == "") {
			alert('Inserisci la descrizione');
			$("#form").submit(function(e){
		        e.preventDefault();
		    });
			location.reload();
		}
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
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"role="alert">
				${errorMessage}
			<button type="button" class="close" data-dismiss="alert"aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Cerca categoria</h5>
			</div>
			<div class='card-body'>


				<form method="GET" action="${pageContext.request.contextPath}/CercaCategoriaServlet" novalidate="novalidate" id="form">

					<div class="form-row">
						<div class="form-group col-md-12">
							<label>Descrizione 
							</label> 
							<input type="text" name="descrizione" id="descrizione"
								class="form-control" placeholder="Elettronica"
							required>
						</div>
					</div>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Cerca
					</button>
				</form>
			</div>

			<!-- end card-body -->
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