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
<link rel="stylesheet" href="./css/feed.css">

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
							<form class="d-flex" role="search">
								<span
									class="input-group-text rounded-5 bg-white barra-pesquisar">
									<i class="bi bi-search"></i> <input type="search"
									class="form-control border-0 barra-search"
									placeholder="PESQUISAR" aria-label="Search"> <i
									class="bi bi-funnel"></i>
								</span>
							</form>
							<div class="perfil-mobile mt-1">
								<figure class="mb-0">
									<img class="foto rounded-circle"
										src="./assets/foto-gerente-projetos.png" alt="Foto de perfil">
								</figure>
								<div
									class="nome d-flex justify-content-start flex-column ms-3 mb-0">
									<p class="fw-medium mb-0 color-gradient text-uppercase">${nome}</p>
									<p class="fw-medium cargo text-grey mt-1 mb-0">Gerente de
										Projetos</p>
								</div>
							</div>
							<div class="opcoes-menu">
								<div
									class="area-row d-flex align-items-center flex-row mb-4 mt-3"
									data-points="100">
									<div
										class="d-flex justify-content-center flex-column align-items-center">
										<button type="button" id="btn-novo-post"
											class="area d-flex justify-content-center align-items-center"
											data-bs-toggle="modal" data-bs-target="#meuModal">
											<i class="bi bi-plus fs-4 mt-1 color-gradient"></i>
										</button>
									</div>
									<div class="texto flex-column">
										<p class="texto-area fw-medium mb-0 ms-3 color-gradient">NOVO
											POST</p>
									</div>
								</div>
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
												class="sigma-icon texto-area fw-medium color-gradient d-flex justify-content-center align-items-center">&Sigma;</span>
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
					<div
						class="perfil color-light d-flex justify-content-center align-items-center flex-column shadow rounded">
						<form action="AtualizaPerfil" method="post"
							enctype="multipart/form-data">
							<figure class="mb-0 position-relative">
								<img id="fotoPerfil" class="foto rounded-circle"
									src= "data:image/png;base64,${caminhoFotoPerfil}" alt="Foto de perfil">
								<label for="perfilUpload"
									class="position-absolute bottom-0 end-0 rounded-circle bg-white p-1"
									style="cursor: pointer;"> <i class="bi bi-camera-fill"></i>
								</label>
								<input type="file" id="perfilUpload" name="fotoPerfil"
									accept="image/*" hidden onchange="this.form.submit()">
							</figure>
						</form>
						<div
							class="nome d-flex justify-content-center flex-column align-items-center">
							<p class="fw-medium mb-0 color-gradient text-uppercase">${nome}</p>
							<p class="fw-medium cargo text-grey mt-2">Gerente de Projetos</p>
						</div>
						<section
							class="d-flex justify-content-center align-items-baseline flex-column">
							<div
								class="area-row d-flex justify-content-center align-items-center flex-row mb-4"
								data-points="100">
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<button type="button"
										class="area d-flex justify-content-center align-items-center"
										data-bs-toggle="modal" data-bs-target="#meuModal">
										<i class="bi bi-plus fs-3 color-gradient"></i>
									</button>
								</div>
								<div class="texto flex-column">
									<p class="texto-area fw-medium mb-0 ms-3 color-gradient">NOVO
										POST</p>
								</div>
							</div>
							<div
								class="area-row d-flex justify-content-center align-items-center flex-row mb-4"
								data-points="100">
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<button type="button"
										class="area d-flex justify-content-center align-items-center">
										<i
											class="bi bi-database d-flex justify-content-center align-items-center color-gradient"></i>
									</button>
								</div>
								<div class="texto flex-column">
									<p class="texto-area fw-medium mb-0 ms-3">DATA</p>
								</div>
							</div>
							<div
								class="area-row d-flex justify-content-center align-items-center flex-row mb-4"
								data-points="100">
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<button type="button"
										class="area d-flex justify-content-center align-items-center">
										<i
											class="bi bi-bandaid d-flex justify-content-center align-items-center color-gradient"></i>
									</button>
								</div>
								<div class="texto flex-column">
									<p class="texto-area fw-medium mb-0 ms-3">DIGITAL HEALTH</p>
								</div>
							</div>
							<div
								class="area-row d-flex justify-content-center align-items-center flex-row mb-4"
								data-points="100">
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<button type="button"
										class="area d-flex justify-content-center align-items-center">
										<i
											class="bi bi-lightbulb d-flex justify-content-center align-items-center color-gradient"></i>
									</button>
								</div>
								<div class="texto flex-column">
									<p class="texto-area fw-medium mb-0 ms-3">OPEN INNOVATION</p>
								</div>
							</div>
							<div
								class="area-row d-flex justify-content-center align-items-center flex-row mb-4"
								data-points="100">
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<button type="button"
										class="area d-flex justify-content-center align-items-center">
										<span
											class="sigma-icon fs-5 texto-area fw-medium color-gradient d-flex justify-content-center align-items-center">&Sigma;</span>
									</button>
								</div>
								<div class="texto flex-column">
									<p class="texto-area fw-medium mb-0 ms-3">SIX SIGMA</p>
								</div>
							</div>
							<div
								class="area-row d-flex justify-content-center align-items-center flex-row mb-3"
								data-points="100">
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<button type="button"
										class="area d-flex justify-content-center align-items-center">
										<i
											class="bi bi-currency-dollar d-flex justify-content-center align-items-center color-gradient"></i>
									</button>
								</div>
								<div class="texto flex-column">
									<p class="texto-area fw-medium mb-0 ms-3">VENTURES</p>
								</div>
							</div>
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

			<div
				class="col-md-9 main-content d-flex flex-column justify-content-center align-items-center mb-0">
				<div class="scrollable-cards w-100 p-3">
					<c:forEach var="publicacao" items="${publicacoes}">
						<div
							class="card rounded-3 border-top-0 border-start-0 border-end-0 shadow mb-3">
							<div class="card-body">
								<div
									class="area-row d-flex justify-content-start align-items-center flex-row mb-3 card-header"
									data-points="100">
									<div class="d-flex justify-content-center align-items-center">
										<div
											class="area-card d-flex justify-content-center align-items-center">

										</div>
									</div>
									<div class="texto flex-column">
										<p class="texto-area fw-medium mb-0 ms-3">${publicacao.area}</p>
										<p class="publicado fw-medium mb-0 ms-3">Publicado por
											${publicacao.funcionario}</p>
									</div>
								</div>

								<figure class="mb-0">
									<img src="data:image/png;base64,${publicacao.imagemBase64}"
										class="card-img-bottom ps-2 pe-2" alt="">
								</figure>

								<div
									class="reacoes ms-2 mt-3 mb-0 d-flex justify-content-start align-items-center flex-row">
									<div
										class="d-flex justify-content-center align-items-center flex-column">
										<i type="button" class="bi bi-heart me-3"></i>
										<p class="me-3 mb-0 quant-reacoes">10</p>
									</div>
									<div
										class="d-flex justify-content-center align-items-center flex-column">
										<i type="button" class="bi bi-chat me-3"
											data-bs-toggle="modal"
											data-bs-target="#modalComentario-${publicacao.idPublicacao}"
											data-id="${publicacao.idPublicacao}"
											data-area="${publicacao.area}"
											data-funcionario="${publicacao.funcionario}"
											data-descricao="${publicacao.descricao}"
											data-imagem="data:image/png;base64,${publicacao.imagemBase64}"></i>
										<p class="me-3 mb-0 quant-reacoes">5</p>
									</div>
									<div
										class="d-flex justify-content-center align-items-center flex-column">
										<i type="button" class="bi bi-emoji-laughing"></i>
										<p class="mb-0 quant-reacoes">2</p>
									</div>
								</div>

								<p
									class="texto-area-post fw-medium mt-2 ms-2 color-gradient mb-0">O
									PROJETO</p>
								<p class="card-text ps-2 pe-2 mb-0 resumo-projeto">
									${publicacao.descricao.length() > 100 ? publicacao.descricao.substring(0,160) : publicacao.descricao}
									<span class="dots">...</span>
								</p>

								<div class="conteudo-oculto" style="display: none;">
									<p class="card-text ps-2 pe-2 mb-0">${publicacao.descricao.length() > 100 ? publicacao.descricao.substring(100) : ""}</p>
									<p
										class="texto-area-post fw-medium mt-2 ms-2 color-gradient mb-0">
										OBJETIVOS</p>
									<p class="card-text ps-2 pe-2 mb-0">${publicacao.objetivos}</p>
									<p
										class="texto-area-post fw-medium mt-2 ms-2 color-gradient mb-0">
										RESULTADOS</p>
									<p class="card-text ps-2 pe-2 mb-0">${publicacao.resultados}</p>
								</div>

								<input type="button" class="fw-medium ver-mais" value="VER MAIS"
									onclick="toggleVerMais(this)">
							</div>
						</div>
						<div class="modal fade"
							id="modalComentario-${publicacao.idPublicacao}" tabindex="-1"
							aria-labelledby="meuModalComentarioLabel" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered">
								<div
									class="modal-content rounded-4 border-end-0 border-bottom-0">
									<div
										class="modal-header d-flex justify-content-start align-items-center">
										<div
											class="area-row d-flex justify-content-start align-items-center flex-row card-header"
											data-points="100">
											<div class="d-flex justify-content-center align-items-center">
												<div
													class="area-card d-flex justify-content-center align-items-center">
												</div>
											</div>
											<div class="texto flex-column">
												<p class="texto-area fw-medium mb-0 ms-3"></p>
												<p class="publicado fw-medium mb-0 ms-3"></p>
											</div>
										</div>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<figure class="mb-0">
											<img src="./assets/foto-publicacao.png"
												class="modal-img-bottom rounded-4 ps-1 pe-1" alt="">
										</figure>
										<div
											class="reacoes ms-2 mt-3 mb-0 d-flex justify-content-start align-items-center flex-row">
											<div
												class="d-flex justify-content-center align-items-center flex-column">
												<i type="button" class="bi bi-heart me-3"></i>
												<p class="me-3 mb-0 quant-reacoes">10</p>
											</div>
											<div
												class="d-flex justify-content-center align-items-center flex-column">
												<i type="button" class="bi bi-chat me-3"></i>
												<p class="me-3 mb-0 quant-reacoes">5</p>
											</div>
											<div
												class="d-flex justify-content-center align-items-center flex-column">
												<i type="button" class="bi bi-emoji-laughing"></i>
												<p class="mb-0 quant-reacoes">2</p>
											</div>
										</div>
										<div class="scrollable-modal w-100 mt-3 mb-3">
											<c:forEach var="comentarios" items="${comentarios}">
												<c:if
													test="${comentarios.idPublicacao == publicacao.idPublicacao}">
													<div class="perfil-modal mb-3">
														<figure class="mb-0">
															<img class="foto-modal rounded-circle"
																src="data:image/png;base64,${comentarios.imagemBase64}"
																alt="Foto de perfil">
														</figure>
														<div
															class="nome-modal d-flex justify-content-start flex-column ms-3 mb-0">
															<p class="fw-medium mb-0 color-gradient text-uppercase">${comentarios.nomeFuncionario}</p>
															<p class="fw-medium cargo-modal mt-1 mb-0">${comentarios.descricao}</p>
														</div>
													</div>
												</c:if>
											</c:forEach>
										</div>
										<form action="CadastraComentario" method="post"
											accept-charset="UTF-8">
											<div class="input-fazer-comentario">
												<div class="input-group">
													<input type="hidden" name="publicacao" value=""> <input
														type="text" name="descricao"
														class="form-control fazer-comentario"
														placeholder="Adicione um comentário"
														aria-label="Recipient’s username"
														aria-describedby="button-addon2">
													<button class="btn btn-outline-secondary" type="submit"
														id="button-addon2">Postar</button>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>

					</c:forEach>

				</div>

				<nav
					class="navbar navbar-light bg-white shadow ps-3 pe-3 navbar-bottom d-flex fixed d-flex justify-content-around align-items-center">
					<a href="/feedGerenteProjetos.html"
						class="text-center text-decoration-none text-primary">
						<div
							class="d-flex justify-content-center flex-column align-items-center">
							<div
								class="icon-wrapper d-flex justify-content-center align-items-center">
								<i
									class="bi bi-house d-flex justify-content-center align-items-center color-gradient"></i>
							</div>
							<small class="color-gradient mt-1">Feed</small>
						</div>
					</a> <a href="./projetos.html"
						class="text-center text-decoration-none text-primary">
						<div
							class="d-flex justify-content-center flex-column align-items-center">
							<div
								class="icon-wrapper d-flex justify-content-center align-items-center">
								<i
									class="bi bi-clipboard d-flex justify-content-center align-items-center color-gradient"></i>
							</div>
							<small class="color-gradient mt-1">Meus Projetos</small>
						</div>
					</a> <a href="#" class="text-center text-decoration-none text-primary">
						<div
							class="d-flex justify-content-center flex-column align-items-center">
							<div
								class="icon-wrapper d-flex justify-content-center align-items-center">
								<i
									class="bi bi-box-arrow-right d-flex justify-content-center align-items-center color-gradient"></i>
							</div>
							<small class="color-gradient mt-1">Sair</small>
						</div>
					</a>
				</nav>
				<footer>
					<p class="mt-2 text-grey">© 2025 Lumina from Astra</p>
				</footer>
			</div>
		</div>
	</main>
	<!-- Chamada JS do BS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-...your-integrity-hash..." crossorigin="anonymous"></script>
	<script src="./js/script.js"></script>
	<script>
		window.addEventListener('load', function() {
			const userPoints = parseInt('${pontos}', 10);

			console.log('User Points:', userPoints);

			function updateIcons() {
				document.querySelectorAll('.area-row')
						.forEach(
								function(nivelRow) {
									const pointsRequired = parseInt(nivelRow
											.getAttribute('data-points'), 10);
									const icon = nivelRow
											.querySelector('.pontuacao i');

									console.log('Points Required:',
											pointsRequired);
									console.log('Current Icon Classes:',
											icon.classList);

									if (userPoints >= pointsRequired) {
										icon.classList.remove('bi-lock-fill');
										icon.classList.add('bi-check-lg');
									} else {
										icon.classList.remove('bi-check-lg');
										icon.classList.add('bi-lock-fill');
									}
								});
			}

			updateIcons();

			document.querySelectorAll('.card').forEach(
					function(card) {
						card.addEventListener('click', function() {
							console.log('Card clicked');
							const categoria = this
									.getAttribute('data-categoria');
							window.location.href = 'produtoCliente?categoria='
									+ encodeURIComponent(categoria);
						});
					});
		});

		function toggleVerMais(btn) {
		    const card = btn.closest('.card-body');
		    const conteudo = card.querySelector('.conteudo-oculto');
		    const dots = card.querySelector('.dots');

		    if (conteudo.style.display === "none") {
		        conteudo.style.display = "block";
		        dots.style.display = "none";
		        btn.value = "VER MENOS";
		    } else {
		        conteudo.style.display = "none";
		        dots.style.display = "inline";
		        btn.value = "VER MAIS";
		    }
		}
		
		document.addEventListener("DOMContentLoaded", function() {
		    const publicacoes = document.querySelectorAll('.card-body');

		    publicacoes.forEach(pub => {
		        const areaNome = pub.querySelector('.texto-area').textContent.trim();
		        const iconeContainer = pub.querySelector('.area-card');

		        switch(areaNome) {
		            case 'SIX SIGMA':
		                iconeContainer.innerHTML = '<span class="sigma-icon fs-5 texto-area fw-medium color-gradient d-flex justify-content-center align-items-center">&Sigma;</span>';
		                break;
		            case 'DATA':
		                iconeContainer.innerHTML = '<i class="bi bi-database d-flex justify-content-center align-items-center color-gradient"></i>';
		                break;
		            case 'DIGITAL HEALTH':
		                iconeContainer.innerHTML = '<i class="bi bi-bandaid d-flex justify-content-center align-items-center color-gradient"></i>';
		                break;
		            case 'OPEN INNOVATION':
		                iconeContainer.innerHTML = '<i class="bi bi-lightbulb d-flex justify-content-center align-items-center color-gradient"></i>';
		                break;
		                
		            case 'VENTURES':
		                iconeContainer.innerHTML = '<i class="bi bi-currency-dollar d-flex justify-content-center align-items-center color-gradient"></i>';
		                break;
		                
		            default:
		                iconeContainer.innerHTML = '<i class="bi bi-question fs-5 color-gradient"></i>';
		        }
		    });
		});
		document.addEventListener("DOMContentLoaded", function () {
			  document.querySelectorAll('.modal').forEach(modal => {
			    modal.addEventListener('show.bs.modal', function (event) {
			      const button = event.relatedTarget;
			      const id = button.getAttribute('data-id');
			      const area = button.getAttribute('data-area');
			      const funcionario = button.getAttribute('data-funcionario');
			      const descricao = button.getAttribute('data-descricao');
			      const imagem = button.getAttribute('data-imagem');
			      
			     
			      modal.querySelector('.texto-area').textContent = area;
			      modal.querySelector('.publicado').textContent = 'Publicado por ' + funcionario;
			      modal.querySelector('.modal-body img').src = imagem;

			      modal.querySelector('#button-addon2').dataset.id = id;
			      modal.querySelector('input[name="publicacao"]').value = id;
			     

			      const iconeContainer = modal.querySelector('.area-card');
			      switch(area) {
			        case 'SIX SIGMA':
			          iconeContainer.innerHTML = '<span class="sigma-icon fs-5 texto-area fw-medium color-gradient d-flex justify-content-center align-items-center">&Sigma;</span>';
			          break;
			        case 'DATA':
			          iconeContainer.innerHTML = '<i class="bi bi-database d-flex justify-content-center align-items-center color-gradient"></i>';
			          break;
			        case 'DIGITAL HEALTH':
			          iconeContainer.innerHTML = '<i class="bi bi-bandaid d-flex justify-content-center align-items-center color-gradient"></i>';
			          break;
			        case 'OPEN INNOVATION':
			          iconeContainer.innerHTML = '<i class="bi bi-lightbulb d-flex justify-content-center align-items-center color-gradient"></i>';
			          break;
			        case 'VENTURES':
			          iconeContainer.innerHTML = '<i class="bi bi-currency-dollar d-flex justify-content-center align-items-center color-gradient"></i>';
			          break;
			        default:
			          iconeContainer.innerHTML = '<i class="bi bi-question fs-5 color-gradient"></i>';
			      }
			    });
			  });
			});
		
		document.getElementById('perfilUpload').addEventListener('change', function() {
		    const file = this.files[0];
		    if (!file) return;

		    const formData = new FormData();
		    formData.append('fotoPerfil', file);

		    fetch('AtualizaPerfil', {
		        method: 'POST',
		        body: formData
		    })
		    .then(res => res.json())
		    .then(data => {
		        if (data.sucesso) {
		            document.getElementById('fotoPerfil').src = data.novaImagem;
		        } else {
		            alert('Erro ao atualizar foto');
		        }
		    })
		    .catch(err => console.error(err));
		});



	</script>

</body>

</html>