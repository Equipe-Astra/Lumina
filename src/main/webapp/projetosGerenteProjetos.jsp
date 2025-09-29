<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lumina</title>
<!-- Google Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=League+Spartan:wght@100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">
<!-- Minha CSS -->
<link rel="stylesheet" href="./css/projetos.css">
<!-- Chamada da CSS do BS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet" crossorigin="anonymous">

<!-- BS Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
</head>
<body>
	<header class="color-light shadow">
		<nav class="navbar d-lg-flex background-nav ps-4 pe-4">
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
							<form class="d-flex position-relative" role="search">
								<span
									class="input-group-text rounded-5 bg-white barra-pesquisar">
									<i class="bi bi-search"></i> <input type="search"
									class="form-control border-0 barra-search"
									placeholder="PESQUISAR" aria-label="Search">

									<div
										class="d-flex justify-content-center flex-column align-items-center">
										<button type="button"
											class="d-flex justify-content-center align-items-center border-0 bg-transparent text-grey"
											id="dropdownMenuButton" data-bs-toggle="dropdown"
											aria-expanded="false">
											<i class="bi bi-funnel filtro"></i>
										</button>

									</div>
								</span>
							</form>
						</div>
					</ul>
				</div>

			</div>
		</nav>
	</header>
	<div class="barra-header"></div>
	<div class="wrapper"></div>
	<main
		class="container-fluid d-flex justify-content-center flex-column align-items-center">
		<div
			class="scroll-cards-container d-flex justify-content-center align-items-center flex-column">
			<section class="container container-cards">
				<div class="row d-flex justify-content-center flex-wrap colunas">
					<div class="col-md-3">
						<div class="card shadow rounded-3 border-0">
							<div
								class="card-header card-header-status bg-white d-flex justify-content-between align-items-center rounded-top-3">
								<p class="fw-semibold texto-status color-gradient m-0">NÃO
									INICIADO</p>
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<button type="button"
										class="area open-modal d-flex justify-content-center align-items-center"
										data-bs-toggle="modal" data-bs-target="#meuModal"
										data-status-id="1" data-status="Não Iniciado">
										<i class="bi bi-plus fs-4 mt-1 color-gradient"></i>
									</button>
								</div>
							</div>
							<div class="card-body">
								<c:forEach var="projeto" items="${projetos}">
									<c:if test="${projeto.status.descricao == 'Não Iniciado'}">
										<div class="card card-tarefa mb-3 shadow-sm projeto-card-clicavel"
											data-id="${projeto.idProjeto}"
											data-area="${projeto.idArea.idArea}" 
											data-titulo="${projeto.titulo}"
										    data-descricao="${projeto.descricao}"
										    data-bs-toggle="modal" 
										    data-bs-target="#meuModal"
										    draggable="true"
											data-participantes="<c:forEach var="p" items="${projeto.participantes}" varStatus="loop">${p.id}|${p.nome}|data:image/png;base64,${p.foto}<c:if test="${!loop.last}">|</c:if></c:forEach>"
										    >

											<!-- Header do Card -->
											<div
												class="card-header card-header-tarefa bg-white d-flex justify-content-between align-items-center">
												<!-- Título -->
												<p class="fw-semibold mt-1 titulo-projeto mb-0">${projeto.titulo}</p>
												<!-- Ações (empurradas para a direita) -->
												<div class="d-flex gap-2 ms-auto">
													<!-- Botão editar -->
													<button class="border-0 bg-transparent pe-0 btn-editar"
														data-bs-toggle="modal" data-bs-target="#modalEdicao"
														data-id="${projeto.idProjeto}"
														data-titulo="${projeto.titulo}"
														data-descricao="${projeto.descricao}">
														<i class="bi bi-pencil text-grey"></i>
													</button>

													<!-- Botão excluir -->
													<form action="ProjetosServlet" method="post"
														style="display: inline;">
														<input type="hidden" name="deletarId"
															value="${projeto.idProjeto}">
														<button type="submit"
															class="border-0 bg-transparent btn-excluir">
															<i class="bi bi-trash text-grey"></i>
														</button>
													</form>
												</div>
											</div>


											<!-- Body do Card -->
											<div class="card-body">
												<p class="text-muted small">${projeto.descricao}</p>

												<!-- Participantes -->
												<div
													class="d-flex justify-content-between align-items-center">
													<span class="small text-grey fw-medium">Participantes</span>
													<div class="d-flex gap-2">
														<c:forEach var="participante"
															items="${projeto.participantes}">
															<img src="data:image/png;base64,${participante.foto}"
																alt="${participante.nome}"
																class="foto rounded-circle shadow"
																style="width: 35px; height: 35px;"
																title="${participante.nome}"
																data-id="${participante.id}">
														</c:forEach>
													</div>
												</div>

												<!-- Formulário escondido para atualizar status -->
												<form method="post" class="form-status">
													<input type="hidden" name="projetoId"
														value="${projeto.idProjeto}"> <input type="hidden"
														name="statusId" class="statusProjeto"
														value="${projeto.status.id}">
												</form>

											</div>
										</div>
									</c:if>
								</c:forEach>
							</div>

						</div>
					</div>
					<div class="col-md-3">
						<div class="card shadow rounded-4 border-0">
							<div
								class="card-header card-header-status bg-white d-flex justify-content-between align-items-center rounded-top-3">
								<p class="fw-semibold texto-status color-gradient m-0">EM
									ANDAMENTO</p>
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<button type="button"
										class="area open-modal d-flex justify-content-center align-items-center"
										data-bs-toggle="modal" data-bs-target="#meuModal"
										data-status-id="2" data-status="Em andamento">
										<i class="bi bi-plus fs-4 mt-1 color-gradient"></i>
									</button>
								</div>
							</div>
							<div class="card-body">
								<c:forEach var="projeto" items="${projetos}">
									<c:if test="${projeto.status.descricao == 'Em andamento'}">
										<div class="card card-tarefa mb-3 shadow-sm projeto-card-clicavel"
											data-id="${projeto.idProjeto}"
											data-area="${projeto.idArea.idArea}" 
											data-titulo="${projeto.titulo}"
										    data-descricao="${projeto.descricao}"
										    data-bs-toggle="modal" 
										    data-bs-target="#meuModal"
										    draggable="true"
											data-participantes="<c:forEach var="p" items="${projeto.participantes}" varStatus="loop">${p.id}|${p.nome}|data:image/png;base64,${p.foto}<c:if test="${!loop.last}">|</c:if></c:forEach>"
										    >

											<!-- Header do Card -->
											<div
												class="card-header card-header-tarefa bg-white d-flex justify-content-between align-items-center">
												<!-- Título -->
												<p class="fw-semibold mt-1 titulo-projeto mb-0">${projeto.titulo}</p>

												<!-- Ações (empurradas para a direita) -->
												<div class="d-flex gap-2 ms-auto">
													<!-- Botão editar -->
													<button class="border-0 bg-transparent pe-0 btn-editar"
														data-bs-toggle="modal" data-bs-target="#modalEdicao"
														data-id="${projeto.idProjeto}"
														data-titulo="${projeto.titulo}"
														data-descricao="${projeto.descricao}">
														<i class="bi bi-pencil text-grey"></i>
													</button>

													<!-- Botão excluir -->
													<form action="ProjetosServlet" method="post"
														style="display: inline;">
														<input type="hidden" name="deletarId"
															value="${projeto.idProjeto}">
														<button type="submit"
															class="border-0 bg-transparent btn-excluir">
															<i class="bi bi-trash text-grey"></i>
														</button>
													</form>
												</div>
											</div>

											<!-- Body do Card -->
											<div class="card-body">
												<p class="text-muted small">${projeto.descricao}</p>

												<!-- Participantes -->
												<div
													class="d-flex justify-content-between align-items-center">
													<span class="small text-grey fw-medium">Participantes</span>
													<div class="d-flex gap-2">
														<c:forEach var="participante"
															items="${projeto.participantes}">
															<img src="data:image/png;base64,${participante.foto}"
																alt="${participante.nome}"
																class="foto rounded-circle shadow"
																style="width: 36px; height: 36px;"
																title="${participante.nome}"
																data-id="${participante.id}">
														</c:forEach>
													</div>
												</div>

												<!-- Formulário escondido para atualizar status -->
												<form method="post" class="form-status">
													<input type="hidden" name="projetoId"
														value="${projeto.idProjeto}"> <input type="hidden"
														name="statusId" class="statusProjeto"
														value="${projeto.status.id}">
												</form>

											</div>
										</div>
									</c:if>
								</c:forEach>
							</div>

						</div>
					</div>
					<!-- COLUNA: CONCLUÍDO -->
					<div class="col-md-3">
						<div class="card shadow rounded-4 border-0">
							<div
								class="card-header card-header-status bg-white d-flex justify-content-between align-items-center rounded-top-3">
								<p class="fw-semibold texto-status color-gradient m-0">CONCLUÍDO</p>
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<button type="button"
										class="area open-modal d-flex justify-content-center align-items-center"
										data-bs-toggle="modal" data-bs-target="#meuModal"
										data-status-id="3" data-status="Concluído">
										<i class="bi bi-plus fs-4 mt-1 color-gradient"></i>
									</button>
								</div>
							</div>
							<div class="card-body">
								<c:forEach var="projeto" items="${projetos}">
									<c:if test="${projeto.status.descricao == 'Concluído'}">
										<div class="card card-tarefa mb-3 shadow-sm projeto-card-clicavel"
											data-id="${projeto.idProjeto}"
											data-area="${projeto.idArea.idArea}" 
											data-titulo="${projeto.titulo}"
										    data-descricao="${projeto.descricao}"
										    data-bs-toggle="modal" 
										    data-bs-target="#meuModal"
										    draggable="true"
											data-participantes="<c:forEach var="p" items="${projeto.participantes}" varStatus="loop">${p.id}|${p.nome}|data:image/png;base64,${p.foto}<c:if test="${!loop.last}">|</c:if></c:forEach>"
										    >

											<!-- Header do Card -->
											<div
												class="card-header card-header-tarefa bg-white d-flex justify-content-between align-items-center">
												<!-- Título -->
												<p class="fw-semibold mt-1 titulo-projeto mb-0">${projeto.titulo}</p>

												<!-- Ações (empurradas para a direita) -->
												<div class="d-flex gap-2 ms-auto">
													<!-- Botão editar -->
													<button class="border-0 bg-transparent pe-0 btn-editar"
														data-bs-toggle="modal" data-bs-target="#modalEdicao"
														data-id="${projeto.idProjeto}"
														data-titulo="${projeto.titulo}"
														data-descricao="${projeto.descricao}">
														<i class="bi bi-pencil text-grey"></i>
													</button>

													<!-- Botão excluir -->
													<form action="ProjetosServlet" method="post"
														style="display: inline;">
														<input type="hidden" name="deletarId"
															value="${projeto.idProjeto}">
														<button type="submit"
															class="border-0 bg-transparent btn-excluir">
															<i class="bi bi-trash text-grey"></i>
														</button>
													</form>

												</div>
											</div>

											<!-- Body do Card -->
											<div class="card-body">
												<p class="text-muted small">${projeto.descricao}</p>

												<!-- Participantes -->
												<div
													class="d-flex justify-content-between align-items-center">
													<span class="small text-grey fw-medium">Participantes</span>
													<div class="d-flex gap-2">
														<c:forEach var="participante"
															items="${projeto.participantes}">
															<img src="data:image/png;base64,${participante.foto}"
																alt="${participante.nome}"
																class="foto rounded-circle shadow"
																style="width: 35px; height: 35px;"
																title="${participante.nome}"
																data-id="${participante.id}">
														</c:forEach>
													</div>
												</div>

												<!-- Formulário escondido para atualizar status -->
												<form method="post" class="form-status">
													<input type="hidden" name="projetoId"
														value="${projeto.idProjeto}"> <input type="hidden"
														name="statusId" class="statusProjeto"
														value="${projeto.status.id}">
												</form>

											</div>
										</div>
									</c:if>
								</c:forEach>
							</div>

						</div>
					</div>
				</div>
			</section>
			<!-- Swiper Wrapper -->
			<div class="swiper mySwiper d-block d-md-none px-2">
				<div class="swiper-wrapper">
					<div class="swiper-slide">
						<div class="card shadow rounded-3 border-0">
							<div
								class="card-header card-header-status bg-white d-flex justify-content-between align-items-center rounded-top-3">
								<p class="fw-semibold texto-status color-gradient m-0">NÃO
									INICIADO</p>
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<button type="button" id="btn-novo-post"
										class="area open-modal d-flex justify-content-center align-items-center"
										data-bs-toggle="modal" data-bs-target="#meuModal"
										data-status="Não Iniciado">
										<i class="bi bi-plus fs-4 mt-1 color-gradient"></i>
									</button>
								</div>
							</div>
							<div class="card-body">
								<c:forEach var="projeto" items="${projetos}">
									<c:if test="${projeto.status.descricao == 'Não Iniciado'}">
										<div class="card card-tarefa mb-3 shadow-sm"
											data-id="${projeto.idProjeto}"
											data-area="${projeto.idArea.idArea}">
											<div
												class="card-header card-header-tarefa bg-white d-flex justify-content-between align-items-center">
												<p class="fw-semibold mt-1 titulo-projeto mb-0">${projeto.titulo}</p>
												<div class="d-flex gap-2 ms-auto">
													<button class="border-0 bg-transparent pe-0 btn-editar"
														data-bs-toggle="modal" data-bs-target="#modalEdicao"
														data-id="${projeto.idProjeto}"
														data-titulo="${projeto.titulo}"
														data-descricao="${projeto.descricao}">
														<i class="bi bi-pencil text-grey"></i>
													</button>
													<form action="ProjetosServlet" method="post"
														style="display: inline;">
														<input type="hidden" name="deletarId"
															value="${projeto.idProjeto}">
														<button type="submit"
															class="border-0 bg-transparent btn-excluir">
															<i class="bi bi-trash text-grey"></i>
														</button>
													</form>
													<div class="dropdown">
														<button class="border-0 bg-transparent pe-0" type="button"
															data-bs-toggle="dropdown" aria-expanded="false">
															<i class="bi bi-three-dots-vertical text-grey"></i>
														</button>
														<ul class="dropdown-menu">
															<li><a class="dropdown-item btn-mover" href="#"
																data-status-id="1">Mover para Não Iniciado</a></li>
															<li><a class="dropdown-item btn-mover" href="#"
																data-status-id="2">Mover para Em Andamento</a></li>
															<li><a class="dropdown-item btn-mover" href="#"
																data-status-id="3">Mover para Concluído</a></li>
														</ul>
													</div>
												</div>
											</div>

											<form action="ProjetosServlet" method="post"
												class="form-status" style="display: none;">
												<input type="hidden" name="acao" value="atualizarStatus">
												<input type="hidden" name="projetoId"
													value="${projeto.idProjeto}"> <input type="hidden"
													name="statusId" class="statusProjeto" value="">
											</form>

											<div class="card-body">
												<p class="text-muted small">${projeto.descricao}</p>
												<div
													class="d-flex justify-content-between align-items-center">
													<span class="small text-grey fw-medium">Participantes</span>
													<div class="d-flex gap-2">
														<c:forEach var="participante"
															items="${projeto.participantes}">
															<img src="data:image/png;base64,${participante.foto}"
																alt="${participante.nome}" data-id="${participante.id}"
																class="foto rounded-circle shadow"
																style="width: 35px; height: 35px;"
																title="${participante.nome}">
														</c:forEach>
													</div>
												</div>
											</div>
										</div>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="swiper-slide">
						<div class="card shadow rounded-4 border-0">
							<div
								class="card-header card-header-status bg-white d-flex justify-content-between align-items-center rounded-top-3">
								<p class="fw-semibold texto-status color-gradient m-0">EM
									ANDAMENTO</p>
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<button type="button" id="btn-novo-post"
										class="area open-modal d-flex justify-content-center align-items-center"
										data-bs-toggle="modal" data-bs-target="#meuModal"
										data-status="Em andamento">
										<i class="bi bi-plus fs-4 mt-1 color-gradient"></i>
									</button>
								</div>
							</div>
							<div class="card-body">
								<c:forEach var="projeto" items="${projetos}">
									<c:if test="${projeto.status.descricao == 'Em andamento'}">
										<div class="card card-tarefa mb-3 shadow-sm"
											data-id="${projeto.idProjeto}"
											data-area="${projeto.idArea.idArea}">
											<div
												class="card-header card-header-tarefa bg-white d-flex justify-content-between align-items-center">
												<p class="fw-semibold mt-1 titulo-projeto mb-0">${projeto.titulo}</p>
												<div class="d-flex gap-2">
													<button class="border-0 bg-transparent pe-0 btn-editar"
														data-bs-toggle="modal" data-bs-target="#modalEdicao"
														data-id="${projeto.idProjeto}"
														data-titulo="${projeto.titulo}"
														data-descricao="${projeto.descricao}">
														<i class="bi bi-pencil text-grey"></i>
													</button>
													<form action="ProjetosServlet" method="post"
														style="display: inline;">
														<input type="hidden" name="deletarId"
															value="${projeto.idProjeto}">
														<button type="submit"
															class="border-0 bg-transparent btn-excluir">
															<i class="bi bi-trash text-grey"></i>
														</button>
													</form>
													<div class="dropdown">
														<button class="border-0 bg-transparent pe-0" type="button"
															data-bs-toggle="dropdown" aria-expanded="false">
															<i class="bi bi-three-dots-vertical text-grey"></i>
														</button>
														<ul class="dropdown-menu">
															<li><a class="dropdown-item btn-mover" href="#"
																data-status-id="1">Mover para Não Iniciado</a></li>
															<li><a class="dropdown-item btn-mover" href="#"
																data-status-id="2">Mover para Em Andamento</a></li>
															<li><a class="dropdown-item btn-mover" href="#"
																data-status-id="3">Mover para Concluído</a></li>
														</ul>
													</div>
												</div>
											</div>

											<form action="ProjetosServlet" method="post"
												class="form-status" style="display: none;">
												<input type="hidden" name="acao" value="atualizarStatus">
												<input type="hidden" name="projetoId"
													value="${projeto.idProjeto}"> <input type="hidden"
													name="statusId" class="statusProjeto" value="">
											</form>

											<div class="card-body">
												<p class="text-muted small">${projeto.descricao}</p>
												<div
													class="d-flex justify-content-between align-items-center">
													<span class="small text-grey fw-medium">Participantes</span>
													<div class="d-flex gap-2">
														<c:forEach var="participante"
															items="${projeto.participantes}">
															<img src="data:image/png;base64,${participante.foto}"
																alt="${participante.nome}" data-id="${participante.id}"
																class="foto rounded-circle shadow"
																style="width: 35px; height: 35px;"
																title="${participante.nome}">
														</c:forEach>
													</div>
												</div>
											</div>
										</div>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="swiper-slide">
						<div class="card shadow rounded-4 border-0">
							<div
								class="card-header card-header-status bg-white d-flex justify-content-between align-items-center rounded-top-3">
								<p class="fw-semibold texto-status color-gradient m-0">CONCLUÍDO</p>
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<button type="button" id="btn-novo-post"
										class="area open-modal d-flex justify-content-center align-items-center"
										data-bs-toggle="modal" data-bs-target="#meuModal"
										data-status="Concluído">
										<i class="bi bi-plus fs-4 mt-1 color-gradient"></i>
									</button>
								</div>
							</div>
							<div class="card-body">
								<c:forEach var="projeto" items="${projetos}">
									<c:if test="${projeto.status.descricao == 'Concluído'}">
										<div class="card card-tarefa mb-3 shadow-sm"
											data-id="${projeto.idProjeto}"
											data-area="${projeto.idArea.idArea}">
											<div
												class="card-header card-header-tarefa bg-white d-flex justify-content-between align-items-center">
												<p class="fw-semibold mt-1 titulo-projeto mb-0">${projeto.titulo}</p>
												<div class="d-flex gap-2">
													<button class="border-0 bg-transparent pe-0 btn-editar"
														data-bs-toggle="modal" data-bs-target="#modalEdicao"
														data-id="${projeto.idProjeto}"
														data-titulo="${projeto.titulo}"
														data-descricao="${projeto.descricao}">
														<i class="bi bi-pencil text-grey"></i>
													</button>
													<form action="ProjetosServlet" method="post"
														style="display: inline;">
														<input type="hidden" name="deletarId"
															value="${projeto.idProjeto}">
														<button type="submit"
															class="border-0 bg-transparent btn-excluir">
															<i class="bi bi-trash text-grey"></i>
														</button>
													</form>
													<div class="dropdown">
														<button class="border-0 bg-transparent pe-0 me-0"
															type="button" data-bs-toggle="dropdown"
															aria-expanded="false">
															<i class="bi bi-three-dots-vertical text-grey"></i>
														</button>
														<ul class="dropdown-menu">
															<li><a class="dropdown-item btn-mover" href="#"
																data-status-id="1">Mover para Não Iniciado</a></li>
															<li><a class="dropdown-item btn-mover" href="#"
																data-status-id="2">Mover para Em Andamento</a></li>
															<li><a class="dropdown-item btn-mover" href="#"
																data-status-id="3">Mover para Concluído</a></li>
														</ul>
													</div>
												</div>
											</div>

											<form action="ProjetosServlet" method="post"
												class="form-status" style="display: none;">
												<input type="hidden" name="acao" value="atualizarStatus">
												<input type="hidden" name="projetoId"
													value="${projeto.idProjeto}"> <input type="hidden"
													name="statusId" class="statusProjeto" value="">
											</form>

											<div class="card-body">
												<p class="text-muted small">${projeto.descricao}</p>
												<div
													class="d-flex justify-content-between align-items-center">
													<span class="small text-grey fw-medium">Participantes</span>
													<div class="d-flex gap-2">
														<c:forEach var="participante"
															items="${projeto.participantes}">
															<img src="data:image/png;base64,${participante.foto}"
																alt="${participante.nome}" data-id="${participante.id}"
																class="foto rounded-circle shadow"
																style="width: 35px; height: 35px;"
																title="${participante.nome}">
														</c:forEach>
													</div>
												</div>
											</div>
										</div>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<div class="swiper-pagination mt-3"></div>
			</div>
			<div class="swiper-button-next color-purple"></div>
			<div class="swiper-button-prev color-purple"></div>
		</div>


	</main>

	<footer
		class="d-flex justify-content-center align-items-center flex-column mb-0">
		<nav
			class="navbar navbar-light bg-white shadow ps-3 pe-3 navbar-bottom d-flex fixed d-flex justify-content-around align-items-center">
			<a href="/Lumina/feedGerenteProjetos"
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
			</a> <a href="/Lumina/ProjetosServlet"
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
			</a> 
			<div class="d-flex justify-content-center flex-column align-items-center">
				<form action="Logout" method="post" class="mb-0">
					<button type="submit" class="color-gradient border-0 d-flex flex-column align-items-center">
						<div class="icon-wrapper d-flex justify-content-center align-items-center">
							<i class="bi bi-box-arrow-right color-gradient"></i>
						</div>
						<small class="color-gradient">Sair</small>
					</button>
				</form>
			</div>
		</nav>
		<p class="mt-2 text-grey footer">© 2025 Lumina from Astra</p>
	</footer>
	<div class="modal fade" id="meuModal" tabindex="-1"
		aria-labelledby="meuModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content rounded-4 border-end-0 border-bottom-0">
				<!-- Transformei o modal em FORM -->
				<form id="formNovoProjeto" action="/Lumina/ProjetosServlet"
					method="post">
					<div
						class="modal-header d-flex justify-content-between align-items-center">
						<input type="text" name="titulo" id="modalTituloInputCriar"
							class="form-control titulo-modal border-0 m-0 p-0 fw-semibold text-grey"
							placeholder="ADICIONE UM TÍTULO">
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body m-0">
						<!-- Campo oculto para enviar o status da coluna -->
						<input type="hidden" name="statusId" id="statusProjeto" value="" />
						<div class="mb-3">
							<label for="descricao"
								class="form-label fw-semibold color-gradient">DESCRIÇÃO</label>
							<textarea name="descricao" class="form-control rounded-4 p-3"
								id="descricao" rows="4" placeholder="Digite aqui..."></textarea>
						</div>
						<div class="mb-3">
							<label class="form-label fw-semibold color-gradient">PARTICIPANTES</label>
							<div class="d-flex align-items-center gap-2"
								id="containerParticipantes">
								<div id="participantesSelecionados"
									class="d-flex flex-wrap gap-1"></div>
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<button type="button"
										class="area-participantes d-flex justify-content-center align-items-center"
										id="dropdownMenuButton" data-bs-toggle="dropdown"
										aria-expanded="false">
										<i class="bi bi-plus fs-4 mt-1 color-gradient"></i>
									</button>
									<ul class="dropdown-menu shadow"
										aria-labelledby="dropdownMenuButton" style="width: 250px;">
										<div class="px-3">
											<span
												class="input-group-text rounded-3 bg-white barra-pesquisar-participantes">
												<i class="bi bi-search"></i> <input type="search"
												id="inputBuscarParticipanteCriacao"
												class="form-control border-0 barra-search-participantes"
												placeholder="Buscar por e-mail" aria-label="Search">
											</span>
										</div>
										<c:forEach var="colaborador" items="${colaboradores}">
											<li class="px-3 participante-item"
												data-id="${colaborador.id}" data-nome="${colaborador.nome}"
												data-foto="data:image/png;base64,${colaborador.foto}"
												style="cursor: pointer;">
												<div
													class="nome-email d-flex align-items-center flex-row mb-3 mt-3">
													<figure class="mb-0">
														<img class="foto rounded-circle shadow"
															src="data:image/png;base64,${colaborador.foto}"
															alt="Foto de perfil">
													</figure>
													<div
														class="nome d-flex justify-content-start flex-column ms-3 mb-0">
														<p class="fw-medium mb-0 text-grey text-uppercase">${colaborador.nome}</p>
														<p class="fw-medium cargo text-grey mt-1 mb-0">${colaborador.email}</p>
													</div>
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
							</div>
							<!-- CONTAINER PARA OS INPUTS HIDDEN -->
							<div id="inputsParticipantes"></div>
						</div>
					</div>
					<div class="modal-footer border-0 pt-0">
						<input type="button" id="btnAdicionar"
							class="btn btn-secondary adicionar border-0" value="ADICIONAR" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modalEdicao" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content rounded-4 border-end-0 border-bottom-0">
				<form id="formEdicaoProjeto" action="/Lumina/ProjetosServlet"
					method="post">
					<div
						class="modal-header d-flex justify-content-between align-items-center">
						<input type="text" name="titulo" id="modalTituloInputEditar"
							class="form-control titulo-modal border-0 m-0 p-0 fw-semibold text-grey"
							placeholder="ADICIONE UM TÍTULO">
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
					<div class="modal-body m-0">
						<input type="hidden" name="projetoId" id="idProjetoEditar">

						<div class="mb-3">
							<label for="descricaoEditar"
								class="form-label fw-semibold color-gradient">DESCRIÇÃO</label>
							<textarea name="descricao" class="form-control rounded-4 p-3"
								id="descricaoEditar" rows="4" placeholder="Digite aqui..."></textarea>
						</div>

						<div class="mb-3">
							<label class="form-label fw-semibold color-gradient">PARTICIPANTES</label>
							<div class="d-flex align-items-center gap-2"
								id="containerParticipantesEditar">
								<div id="participantesSelecionadosEditar"
									class="d-flex flex-wrap gap-1"></div>
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<button type="button"
										class="area-participantes d-flex justify-content-center align-items-center"
										id="dropdownMenuButtonEditar" data-bs-toggle="dropdown"
										aria-expanded="false">
										<i class="bi bi-plus fs-4 mt-1 color-gradient"></i>
									</button>
									<ul class="dropdown-menu shadow"
										aria-labelledby="dropdownMenuButtonEditar"
										style="width: 250px;">
										<div class="px-3">
											<span
												class="input-group-text rounded-3 bg-white barra-pesquisar-participantes">
												<i class="bi bi-search"></i> <input type="search"
												id="inputBuscarParticipante"
												class="form-control border-0 barra-search-participantes"
												placeholder="Buscar por e-mail" aria-label="Search">
											</span>
										</div>
										<div id="listaParticipantesDropdown">
											<c:forEach var="colaborador" items="${colaboradores}">
												<li class="px-3 participante-item-editar"
													data-id="${colaborador.id}" data-nome="${colaborador.nome}"
													data-foto="data:image/png;base64,${colaborador.foto}"
													style="cursor: pointer;">
													<div
														class="nome-email d-flex align-items-center flex-row mb-3 mt-3">
														<figure class="mb-0">
															<img class="foto rounded-circle shadow"
																src="data:image/png;base64,${colaborador.foto}"
																alt="Foto de perfil">
														</figure>
														<div
															class="nome d-flex justify-content-start flex-column ms-3 mb-0">
															<p class="fw-medium mb-0 text-grey text-uppercase">${colaborador.nome}</p>
															<p class="fw-medium cargo text-grey mt-1 mb-0">${colaborador.email}</p>
														</div>
													</div>
												</li>
											</c:forEach>
										</div>
									</ul>
								</div>
							</div>
							<div id="inputsParticipantesEditar"></div>
						</div>
					</div>
					<div class="modal-footer border-0 pt-0">
						<button type="submit" class="btn btn-secondary adicionar border-0">SALVAR</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Chamada JS do BS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>

	<script src="./js/script.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
	<script>
	
	const swiper = new Swiper('.mySwiper', { 
					slidesPerView: 1, spaceBetween: 30, 
					loop: true, pagination: { el: '.swiper-pagination', clickable: true, }, 
					navigation: { nextEl: '.swiper-button-next', prevEl: '.swiper-button-prev', }, 
					breakpoints: { 576: { slidesPerView: 1.2, }, 768: { slidesPerView: 2, }, 
					992: { slidesPerView: 3, } } }); 
	
	document.addEventListener("DOMContentLoaded", function () {
		  // Pega o caminho da URL, remove a barra final e converte para minúsculas
		  const currentPath = window.location.pathname.replace(/\/+$/, "").toLowerCase();

		  document.querySelectorAll("nav a[href]").forEach(link => {
		    const iconDiv = link.querySelector(".icon-wrapper");
		    if (!iconDiv) return;

		    // Pega o caminho do link, remove a barra final e converte para minúsculas
		    const linkPath = link.getAttribute("href").replace(/\/+$/, "").toLowerCase();

		    // Remove a classe de todos os links antes de adicionar ao ativo
		    iconDiv.classList.remove("pagina-atual");

		    // Adiciona a classe apenas se os caminhos forem idênticos
		    if (currentPath === linkPath) {
		      iconDiv.classList.add("pagina-atual");
		    }
		  });
		});
	
	function criarParticipanteFoto(id, nome, fotoBase64) {
        // Função utilitária para criar o elemento da foto do participante
        const img = document.createElement('img');
        img.src = `data:image/png;base64,${fotoBase64}`;
        img.alt = nome;
        img.className = 'foto rounded-circle shadow';
        img.style.cssText = 'width: 40px; height: 40px;';
        img.title = nome;
        img.setAttribute('data-id', id);
        return img;
    }

    document.addEventListener('DOMContentLoaded', function() {
        const meuModal = document.getElementById('meuModal');
        const btnAdicionar = document.getElementById('btnAdicionar');
        const participantesSelecionadosContainer = document.getElementById('participantesSelecionados');
        const inputTitulo = document.getElementById('modalTituloInputCriar');
        const textareaDescricao = document.getElementById('descricao');
        const btnAddParticipantes = meuModal.querySelector('.area-participantes');


        // 1. Lógica para ABRIR O MODAL E OCULTAR O BOTÃO DE ENVIO (clique no CARD)
        const cardsClicaveis = document.querySelectorAll('.projeto-card-clicavel');
        
        cardsClicaveis.forEach(card => {
            card.addEventListener('click', function(event) {
                
                // Impedir que o clique nos botões de Editar ou Excluir acione essa lógica
                if (event.target.closest('.btn-editar') || event.target.closest('.btn-excluir') || event.target.closest('.dropdown')) {
                    return; 
                }

                // 🌟 FORÇA A ABERTURA DO MODAL
                const modalInstance = bootstrap.Modal.getOrCreateInstance(meuModal);
                modalInstance.show();

                // 1. OCULTA: O botão "ADICIONAR" (envio)
                if (btnAdicionar) {
                    btnAdicionar.style.display = 'none';
                }

                // 2. OCULTA: O botão de adicionar novos participantes
                if (btnAddParticipantes) {
                    btnAddParticipantes.style.display = 'none';
                }
                
                // 3. Preenche o modal com os dados do projeto
                inputTitulo.value = this.getAttribute('data-titulo');
                inputTitulo.placeholder = ''; 
                textareaDescricao.value = this.getAttribute('data-descricao');
                
                // 4. Desabilita campos para visualização
                inputTitulo.setAttribute('readonly', 'true');
                textareaDescricao.setAttribute('readonly', 'true');
                
                // 5. EXIBE: Participantes do Projeto
                participantesSelecionadosContainer.innerHTML = ''; // Limpa antes de preencher
                
                const participantesDataString = this.getAttribute('data-participantes');
                if (participantesDataString) {
                    // Divide a string em blocos de 3 (ID, Nome, Foto)
                    const parts = participantesDataString.split('|');
                    
                    for (let i = 0; i < parts.length; i += 3) {
                        const id = parts[i];
                        const nome = parts[i + 1];
                        const foto = parts[i + 2];
                        
                        // Garante que todos os 3 campos existem
                        if (id && nome && foto) {
                            const fotoElement = criarParticipanteFoto(id, nome, foto);
                            participantesSelecionadosContainer.appendChild(fotoElement);
                        }
                    }
                }
            });
        });

        // 2. Lógica para ABRIR O MODAL E MOSTRAR O BOTÃO DE ENVIO (clique no botão '+')
        const btnsCriar = document.querySelectorAll('.open-modal');
        btnsCriar.forEach(btn => {
            btn.addEventListener('click', function() {
                // 1. MOSTRA: O botão "ADICIONAR"
                if (btnAdicionar) {
                    btnAdicionar.style.display = 'block';
                }
                
                // 2. MOSTRA: O botão de adicionar novos participantes (para criação)
                if(btnAddParticipantes) {
                    btnAddParticipantes.style.display = 'flex';
                }
                
                // 3. Remove atributos de visualização/edição e limpa campos
                inputTitulo.removeAttribute('readonly');
                textareaDescricao.removeAttribute('readonly');
                
                inputTitulo.placeholder = 'ADICIONE UM TÍTULO';
                inputTitulo.value = '';
                textareaDescricao.value = '';
                
                // Limpa participantes exibidos e os inputs hidden
                document.getElementById('inputsParticipantes').innerHTML = '';
                participantesSelecionadosContainer.innerHTML = '';

                // Seta o statusId
                const statusId = this.getAttribute('data-status-id');
                document.getElementById('statusProjeto').value = statusId;
            });
        });

        // 3. Lógica para limpar e resetar o modal ao fechar
        meuModal.addEventListener('hidden.bs.modal', function() {
            // Reseta o estado de "visualização"
            inputTitulo.removeAttribute('readonly');
            textareaDescricao.removeAttribute('readonly');
            
            // Limpa os dados preenchidos
            inputTitulo.value = '';
            textareaDescricao.value = '';
            document.getElementById('inputsParticipantes').innerHTML = ''; 
            participantesSelecionadosContainer.innerHTML = '';
        });
    });

	</script>
</body>
</html>
