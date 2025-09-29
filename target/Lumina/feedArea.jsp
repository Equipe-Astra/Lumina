<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">

<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lumina</title>

<!--  Google Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=League+Spartan:wght@100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">

<!-- Minha CSS -->
<link rel="stylesheet" href="./css/feedArea.css">

<!-- Chamada da CSS do BS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

<!-- BS Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>

<body>
	<header class="color-light shadow">
		<nav class="navbar d-lg-flex background-nav ps-4 pe-4"
			style="z-index: 1">
			<div class="container-fluid">
				<a class="navbar-brand logo" href="#"> <img class="logoHeader"
					src="./assets/logo.svg" alt="Logo">
				</a>
				<button class="navbar-toggler d-lg-none" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false"
					aria-label="Toggle navigation">
					<i class="bi bi-filter-right"></i>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<div class="d-flex justify-content-start flex-column">
							<form class="d-flex mb-0" role="search">
								<span
									class="input-group-text rounded-5 bg-white barra-pesquisar">
									<i class="bi bi-search"></i> <input type="search"
									class="form-control border-0 barra-search"
									placeholder="PESQUISAR" aria-label="Search"> <i
									class="bi bi-funnel"></i>
								</span>
							</form>
							<div class="perfil-mobile mt-1">
								<div class="area-row d-flex align-items-center flex-row mb-4"
									data-points="100">
									<div class="d-flex flex-column align-items-center">
										<button type="button"
											class="area d-flex justify-content-center align-items-center">
											<i
												class="bi bi-database fs-5 d-flex justify-content-center align-items-center color-gradient"></i>
										</button>
									</div>
									<div class="texto flex-column">
										<p class="texto-area fw-medium mb-0 ms-3">DATA</p>
									</div>
								</div>
								<div
									class="nome d-flex justify-content-start flex-column ms-3 mb-0">
									<p class="fw-medium mb-0 color-gradient text-uppercase">${nome}</p>
									<p class="fw-medium cargo text-grey mt-1 mb-0">Executivo</p>
								</div>
							</div>
							<div class="opcoes-menu">
							
								<div class="area-row d-flex align-items-center flex-row mb-4"
									data-points="100">
									<div class="d-flex flex-column align-items-center">
										<button type="button"
											class="area d-flex justify-content-center align-items-center">
											<i
												class="bi bi-database fs-5 d-flex justify-content-center align-items-center color-gradient"></i>
										</button>
									</div>
									<div class="texto flex-column">
										<p class="texto-area fw-medium mb-0 ms-3">DATA</p>
									</div>
								</div>
								<div class="area-row d-flex align-items-center flex-row mb-4"
									data-points="100">
									<div
										class="d-flex justify-content-center flex-column align-items-center">
										<button type="button"
											class="area d-flex justify-content-center align-items-center">
											<i
												class="bi bi-bandaid fs-5 d-flex justify-content-center align-items-center color-gradient"></i>
										</button>
									</div>
									<div class="texto flex-column">
										<p class="texto-area fw-medium mb-0 ms-3">DIGITAL HEALTH</p>
									</div>
								</div>
								<div class="area-row d-flex align-items-center flex-row mb-4"
									data-points="100">
									<div
										class="d-flex justify-content-center flex-column align-items-center">
										<button type="button"
											class="area d-flex justify-content-center align-items-center">
											<i
												class="bi bi-lightbulb fs-5 d-flex justify-content-center align-items-center color-gradient"></i>
										</button>
									</div>
									<div class="texto flex-column">
										<p class="texto-area fw-medium mb-0 ms-3">OPEN INNOVATION</p>
									</div>
								</div>
								<div class="area-row d-flex align-items-center flex-row mb-4"
									data-points="100">
									<div class="d-flex flex-column align-items-center">
										<button type="button"
											class="area d-flex justify-content-center align-items-center">
											<span
												class="sigma-icon texto-area fs-5 mt-1 fw-medium color-gradient d-flex justify-content-center align-items-center">&Sigma;</span>
										</button>
									</div>
									<div class="texto flex-column">
										<p class="texto-area fw-medium mb-0 ms-3">SIX SIGMA</p>
									</div>
								</div>
								<div class="area-row d-flex align-items-center flex-row mb-3"
									data-points="100">
									<div
										class="d-flex justify-content-center flex-column align-items-center">
										<button type="button"
											class="area d-flex justify-content-center align-items-center">
											<i
												class="bi bi-currency-dollar fs-5 d-flex justify-content-center align-items-center color-gradient"></i>
										</button>
									</div>
									<div class="texto flex-column">
										<p class="texto-area fw-medium mb-0 ms-3">VENTURES</p>
									</div>
								</div>
							</div>
						</div>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<div class="barra-header"></div>
	<main class="container-fluid">
		<div class="row">
			<div class="col-md-3 p-0">
				<div class="sidebar">
					<div class="perfil color-light d-flex justify-content-center align-items-center flex-column shadow rounded">
						<div class="area-row d-flex align-items-center flex-row mb-4"
							data-points="100">
							<div class="d-flex flex-column align-items-center">
								<button type="button"
									class="area d-flex justify-content-center align-items-center">
									<i
										class="bi bi-database d-flex justify-content-center align-items-center color-gradient"></i>
								</button>
							</div>
						</div>
						<div
							class="nome d-flex justify-content-center flex-column align-items-center">
							<p class="fw-medium mb-0 color-gradient text-uppercase">DATA</p>
							<p class="fw-medium cargo text-grey mt-2">20 Colaboradores</p>
						</div>
						<section
							class="d-flex justify-content-center align-items-baseline flex-column">
							<div
								class="nivel-row d-flex justify-content-center align-items-center flex-row"
								data-points="100">
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<div
										class="pontuacao d-flex justify-content-center align-items-center color-gradient">
										<i class="icon bi-check-lg color-gradient"></i>
									</div>
									<i
										class="bi d-flex justify-content-center align-items-center bi-three-dots-vertical separacao fs-4"></i>
								</div>
								<div class="texto flex-column ms-2">
									<p class="nivel fw-medium mb-0">NÍVEL INICIANTE</p>
									<p class="pontos fw-light" data-points="100">100 PONTOS</p>
								</div>
							</div>
							<div
								class="nivel-row d-flex justify-content-center align-items-center flex-row"
								data-points="200">
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<div
										class="pontuacao d-flex justify-content-center align-items-center color-orange">
										<i class="bi bi-lock-fill color-gradient"></i>
									</div>
									<i
										class="bi d-flex justify-content-center align-items-center bi-three-dots-vertical separacao fs-4"></i>
								</div>
								<div class="texto flex-column ms-2">
									<p class="nivel fw-medium mb-0">NÍVEL BRONZE</p>
									<p class="pontos fw-light" data-points="200">200 PONTOS</p>
								</div>
							</div>
							<div
								class="nivel-row d-flex justify-content-center align-items-center flex-row"
								data-points="300">
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<div
										class="pontuacao d-flex justify-content-center align-items-center color-orange">
										<i class="bi bi-lock-fill color-gradient"></i>
									</div>
									<i
										class="bi d-flex justify-content-center align-items-center bi-three-dots-vertical separacao fs-4"></i>
								</div>
								<div class="texto flex-column ms-2">
									<p class="nivel fw-medium mb-0">NÍVEL PRATA</p>
									<p class="pontos fw-light" data-points="300">300 PONTOS</p>
								</div>
							</div>
							<div
								class="nivel-row d-flex justify-content-center align-items-center flex-row"
								data-points="400">
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<div
										class="pontuacao d-flex justify-content-center align-items-center color-orange">
										<i class="bi bi-lock-fill color-gradient"></i>
									</div>
									<i
										class="bi d-flex justify-content-center align-items-center bi-three-dots-vertical separacao fs-4"></i>
								</div>
								<div class="texto flex-column ms-2">
									<p class="nivel fw-medium mb-0">NÍVEL OURO</p>
									<p class="pontos fw-light">400 PONTOS</p>
								</div>
							</div>
							<div
								class="nivel-row d-flex justify-content-center align-items-center flex-row"
								data-points="500">
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<div
										class="pontuacao d-flex justify-content-center align-items-center color-orange">
										<i class="bi bi-lock-fill color-gradient"></i>
									</div>
									<i
										class="bi d-flex justify-content-center align-items-center bi-three-dots-vertical text-light fs-4"></i>
								</div>
								<div class="texto flex-column ms-2">
									<p class="nivel fw-medium mb-0">NÍVEL DIAMANTE</p>
									<p class="pontos fw-light" data-points="500">500 PONTOS</p>
								</div>
							</div>
							<button class="pt-2 pb-2 ps-3 pe-3 d-flex justify-content-center align-items-center color-purple rounded border-0 text-light consultoraatual">
								<i class="bi bi-people-fill"></i>
								<p class="nomeconsultora fw-medium mb-0 ms-3">
									COLABORADORES<br>
								</p>
							</button>
						</section>
					</div>
				</div>
			</div>
			<div class="modal fade" id="meuModal" tabindex="-1"
				aria-labelledby="meuModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content rounded-4 border-end-0 border-bottom-0">
						<form action="Gemini" method="post" enctype="multipart/form-data"
							accept-charset="UTF-8">
							<div
								class="modal-header d-flex justify-content-center flex-column align-items-center">
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
								<label for="image-upload"
									class="upload-label mt-2 d-flex justify-content-center flex-column align-items-center">
									<i class="bi bi-card-image fs-3 color-gradient"></i>
									<p class="texto-adiciona-foto modal-title mb-0"
										id="meuModalLabel">
										<span id="image-name">Adicione uma imagem</span>
									</p> <input type="file" id="image-upload" name="imagemProjeto"
									accept="image/*" hidden>
								</label>
							</div>

							<div class="modal-body ps-3 pe-3 m-3 mt-2">
								<p class="texto-area-post fw-medium  color-gradient mt-0 mb-2">SELECIONE
									SEU PROJETO</p>
								<div class="mb-4">
									<select class="form-select" name="idProjeto" id="projeto"
										aria-label="Selecione o projeto">
										<option value="" disabled selected hidden>PROJETO</option>
										<c:forEach var="p" items="${projetos}">
											<option value="${p.idProjeto}">${p.titulo}</option>
										</c:forEach>

									</select>
								</div>

								<div class="gemini d-flex align-items-center flex-row">
									<figure>
										<img src="./assets/gemini.svg" alt="">
									</figure>
									<p class="texto-area-post fw-medium ms-2 color-gradient">GEMINI
										FAZ SEU POST PARA VOCÊ</p>
								</div>

								<label for="file-upload"
									class="custom-upload-box justify-content-between"
									id="file-label"> <span id="file-name"
									class="text-truncate d-inline-block" style="max-width: 600px;">Faça
										o upload do seu projeto aqui</span>
									<button type="submit" name="acao" value="gemini"
										class="border-0 bg-transparent">
										<i class="bi bi-upload me-2 color-gradient"></i>
									</button> <input id="file-upload" type="file" name="arquivo"
									accept=".pdf,.csv,.xls,.xlsx,.doc,.docx,.pptx" hidden />
								</label>
								<p class="texto-area-post fw-medium  color-gradient mt-3 mb-2">O
									PROJETO</p>
								<div class="input-group">
									<textarea class="form-control" name="descricao"
										placeholder="Digite aqui...">${descricao}</textarea>
								</div>

								<p class="texto-area-post fw-medium  color-gradient mt-3 mb-2">OBJETIVOS</p>
								<div class="input-group">
									<textarea class="form-control" name="objetivos"
										placeholder="Digite aqui...">${objetivos}</textarea>
								</div>

								<p class="texto-area-post fw-medium  color-gradient mt-3 mb-2">RESULTADOS</p>
								<div class="input-group">
									<textarea class="form-control" name="resultados"
										placeholder="Digite aqui...">${resultados}</textarea>
								</div>

								<p class="texto-area-post fw-medium  color-gradient mt-3 mb-2">LUCROS</p>
								<div class="input-group">
									<textarea class="form-control" name="lucro"
										placeholder="Digite aqui...">${lucro}</textarea>
								</div>
							</div>

							<div class="modal-footer border-0">
								<button type="submit" name="acao" value="manual"
									class="btn btn-secondary publicar border-0">PUBLICAR</button>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-md-9 d-flex justify-content-center align-items-center flex-column mt-5">
			  <div class="scrollable-cards w-100">
			    <div class="row w-100">
			      <div class="col-md-6 mb-4 d-flex justify-content-center">
			        <div class="card shadow" style="width: 100%;">
			        	<figure>
			        		<img class="img-fluid w-100 post" src="./assets/teste.png" alt="Foto de perfil">
			        	</figure>
			        </div>
			      </div>
			      <div class="col-md-6 mb-4 d-flex justify-content-center">
			        <div class="card shadow" style="width: 100%;">
			        	<figure>
			        		<img class="img-fluid w-100 post" src="./assets/teste.png" alt="Foto de perfil">
			        	</figure>
			        </div>
			      </div>
			      <div class="col-md-6 mb-4 d-flex justify-content-center">
			        <div class="card shadow" style="width: 100%;">
			        	<figure>
			        		<img class="img-fluid w-100 post" src="./assets/teste.png" alt="Foto de perfil">
			        	</figure>
			        </div>
			      </div>
			      <div class="col-md-6 mb-4 d-flex justify-content-center">
			        <div class="card shadow" style="width: 100%;">
			        	<figure>
			        		<img class="img-fluid w-100 post" src="./assets/teste.png" alt="Foto de perfil">
			        	</figure>
			        </div>
			      </div>
			      <div class="col-md-6 mb-4 d-flex justify-content-center">
			        <div class="card shadow" style="width: 100%;">
			        	<figure>
			        		<img class="img-fluid w-100 post" src="./assets/teste.png" alt="Foto de perfil">
			        	</figure>
			        </div>
			      </div>
			      <div class="col-md-6 mb-4 d-flex justify-content-center">
			        <div class="card shadow" style="width: 100%;">
			        	<figure>
			        		<img class="img-fluid w-100 post" src="./assets/teste.png" alt="Foto de perfil">
			        	</figure>
			        </div>
			      </div>
			    </div>
			  </div>
			
			  <footer>
			    <p class="mt-2 text-grey">© 2025 Lumina from Astra</p>
			  </footer>
			</div>
			</div>
	</main>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-...your-integrity-hash..." crossorigin="anonymous"></script>
	<script src="./js/script.js"></script>

	<c:if test="${not empty abrirModal}">
		<script>
    var meuModal = new bootstrap.Modal(document.getElementById('meuModal'));
    meuModal.show();
</script>
	</c:if>

</body>

</html>