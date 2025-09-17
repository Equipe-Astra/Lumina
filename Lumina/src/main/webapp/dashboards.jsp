<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lumina</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=League+Spartan:wght@100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/dashboards.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>

<body>
	<header class="color-light shadow">
		<nav class="navbar d-lg-flex background-nav ps-4 pe-4">
			<div class="container-fluid">
				<a class="navbar logo" href="#"> <img class="logoHeader"
					src="<%=request.getContextPath()%>/assets/logo.svg" alt="Logo">
				</a>
			</div>
		</nav>
	</header>
	<div class="barra-header"></div>
	<div class="wrapper"></div>
	<main
		class="container-fluid d-flex justify-content-center flex-column align-items-center">
		<div class="scroll-dashboard-container">
			<section class="container container-cards">
				<div class="row g-4">
					<div class="col-md-6">
						<div
							class="card card-tarefa mb-2 shadow border-top-0 border-start-0 border-end-0">
							<div
								class="card-header card-header-tarefa bg-white d-flex justify-content-between align-items-center">
								<p id="titulo-grafico1"
									class="fw-semibold mt-2 titulo-projeto color-gradient mb-0">
									<span id="titulo-grafico1">LUCRO X ÁREA</span>
								</p>
								<div
									class="d-flex justify-content-center flex-row align-items-center">
									<button type="button"
										class="d-flex justify-content-center align-items-center border-0 bg-transparent text-grey"
										id="dropdownMenuButtonLucro" data-bs-toggle="dropdown"
										aria-expanded="false">
										<i class="bi bi-funnel filtro"></i>
									</button>
									<button type="button"
										class="d-flex justify-content-center align-items-center border-0 bg-transparent text-grey"
										id="resetFilterButtonLucro">
										<i class="bi bi-arrow-counterclockwise"></i>
									</button>
									<ul class="dropdown-menu shadow"
										aria-labelledby="dropdownMenuButtonLucro"
										style="width: 210px;">
										<c:forEach items="${todasAsAreas}" var="area">
											<li class="px-2 py-2">
												<div class="d-flex flex-row align-items-center">
													<button type="button"
														class="d-flex align-items-center border-0 bg-transparent btn-filtro"
														data-id-area="${area.idArea}"
														data-descricao-area="${area.descricao}">
														<p class="texto-area fw-medium mb-0 ms-2">${area.descricao}</p>
													</button>
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
							</div>
							<div class="card-body">
								<div class="chart-container">
									<canvas id="grafico1"></canvas>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div
							class="card card-tarefa mb-2 shadow border-top-0 border-start-0 border-end-0">
							<div
								class="card-header card-header-tarefa bg-white d-flex justify-content-between align-items-center">
								<p id="titulo-grafico2"
									class="fw-semibold mt-2 titulo-grafico2 color-gradient mb-0">STATUS
									X ÁREA</p>
								<div
									class="d-flex justify-content-center flex-row align-items-center">
									<button type="button"
										class="d-flex justify-content-center align-items-center border-0 bg-transparent text-grey"
										id="dropdownMenuButtonStatus" data-bs-toggle="dropdown"
										aria-expanded="false">
										<i class="bi bi-funnel filtro"></i>
									</button>
									<button type="button"
										class="d-flex justify-content-center align-items-center border-0 bg-transparent text-grey"
										id="resetFilterButtonStatus">
										<i class="bi bi-arrow-counterclockwise"></i>
									</button>
									<ul class="dropdown-menu shadow"
										aria-labelledby="dropdownMenuButtonStatus"
										style="width: 210px;">
										<c:forEach items="${todasAsAreas}" var="area">
											<li class="px-2 py-2">
												<div class="d-flex flex-row align-items-center">
													<button type="button"
														class="d-flex align-items-center border-0 bg-transparent btn-filtro"
														data-id-area="${area.idArea}"
														data-descricao-area="${area.descricao}">
														<p class="texto-area fw-medium mb-0 ms-2">${area.descricao}</p>
													</button>
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
							</div>
							<div class="card-body">
								<div class="chart-container">
									<canvas id="grafico2"></canvas>
								</div>
							</div>
						</div>
					</div>


					<div class="col-md-6">
						<div
							class="card card-tarefa mb-2 shadow border-top-0 border-start-0 border-end-0">
							<div
								class="card-header card-header-tarefa bg-white d-flex justify-content-between align-items-center">
								<p class="fw-semibold mt-2 titulo-projeto color-gradient mb-0">PROJETO
									X ÁREA</p>
							</div>
							<div class="card-body">
								<div class="chart-container">
									<canvas id="grafico3"></canvas>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div
							class="card card-tarefa mb-2 shadow border-top-0 border-start-0 border-end-0">
							<div
								class="card-header card-header-tarefa bg-white d-flex justify-content-between align-items-center">
								<p id="titulo-grafico4" class="fw-semibold mt-2 titulo-grafico4 color-gradient mb-0">EVOLUÇÃO
									MENSAL</p>
								<div
									class="d-flex justify-content-center flex-row align-items-center">
									<button type="button"
										class="d-flex justify-content-center align-items-center border-0 bg-transparent text-grey"
										id="dropdownMenuButtonEvolucao" data-bs-toggle="dropdown"
										aria-expanded="false">
										<i class="bi bi-funnel filtro"></i>
									</button>
									<button type="button"
										class="d-flex justify-content-center align-items-center border-0 bg-transparent text-grey"
										id="resetFilterButtonEvolucao">
										<i class="bi bi-arrow-counterclockwise"></i>
									</button>
									<ul class="dropdown-menu shadow"
										aria-labelledby="dropdownMenuButtonEvolucao"
										style="width: 210px;">
										<c:forEach items="${todasAsAreas}" var="area">
											<li class="px-2 py-2">
												<div class="d-flex flex-row align-items-center">
													<button type="button"
														class="d-flex align-items-center border-0 bg-transparent btn-filtro"
														data-id-area="${area.idArea}"
														data-descricao-area="${area.descricao}">
														<p class="texto-area fw-medium mb-0 ms-2">${area.descricao}</p>
													</button>
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
							</div>
							<div class="card-body">
								<canvas id="grafico4"></canvas>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</main>

	<footer
		class="d-flex justify-content-center align-items-center flex-column mb-0">
		<nav
			class="navbar navbar-light bg-white shadow ps-3 pe-3 navbar-bottom d-flex fixed d-flex justify-content-around align-items-center">
			<a href="<%=request.getContextPath()%>/feedGerente.html"
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
			</a> <a href="<%=request.getContextPath()%>/Lumina/ProjetosServlet"
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
			</a> <a href="<%=request.getContextPath()%>/Dashboards"
				class="text-center text-decoration-none text-primary">
				<div
					class="d-flex justify-content-center flex-column align-items-center">
					<div
						class="icon-wrapper d-flex justify-content-center align-items-center">
						<i
							class="bi bi-bar-chart-line d-flex justify-content-center align-items-center color-gradient"></i>
					</div>
					<small class="color-gradient mt-1">Dashboards</small>
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
		<p class="mt-2 text-grey footer">© 2025 Lumina from Astra</p>
	</footer>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.1"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.2.0"></script>

	<script>
	    document.addEventListener("DOMContentLoaded", function () {
	
	
	        const iconesPorArea = {
	            "DATA": '<i class="bi bi-database fs-5 d-flex color-gradient"></i>',
	            "DIGITAL HEALTH": '<i class="bi bi-bandaid fs-5 d-flex color-gradient"></i>',
	            "OPEN INNOVATION": '<i class="bi bi-lightbulb fs-5 d-flex color-gradient"></i>',
	            "SIX SIGMA": '<i class="fs-5 color-gradient" style="font-style: normal; margin-right: 6px; margin-bottom: 0px">&Sigma;</i>',
	
	            "VENTURES": '<i class="bi bi-currency-dollar fs-5 d-flex color-gradient"></i>'
	        };
	
	        const botoesFiltro = document.querySelectorAll('.btn-filtro');
	        botoesFiltro.forEach(botao => {
	            const descricao = botao.getAttribute('data-descricao-area');
	            const iconeHTML = iconesPorArea[descricao];
	            if (iconeHTML) {
	                
	                const tempDiv = document.createElement('div');
	                tempDiv.innerHTML = iconeHTML;
	                const iconeElement = tempDiv.firstChild;
	
	                const pElemento = botao.querySelector('p.texto-area');
	                if (pElemento) {
	                    botao.insertBefore(iconeElement, pElemento);
	                }
	            }
	        });
	
	   
	        const labelsIniciais = [
	            <c:forEach items="${lucrosPorArea}" var="lucro" varStatus="loop">
	                '${lucro.titulo}'${!loop.last ? ',' : ''}
	            </c:forEach>
	        ];
	
	        const valoresIniciais = [
	            <c:forEach items="${lucrosPorArea}" var="lucro" varStatus="loop">
	                ${lucro.lucro}${!loop.last ? ',' : ''}
	            </c:forEach>
	        ];
	
	        let chart1;
	
	        const tituloGrafico1 = document.getElementById("titulo-grafico1");
	
	        function criarGrafico(labels, data, labelTexto) {
	            if (chart1) {
	                chart1.destroy();
	            }
	
	            const ctx1 = document.getElementById("grafico1").getContext('2d');
	            chart1 = new Chart(ctx1, {
	                type: 'bar',
	                data: {
	                    labels: labels,
	                    datasets: [{
	                        label: labelTexto,
	                        data: data,
	                        backgroundColor: '#6D5CE6',
	                        borderRadius: 4,
	                        borderSkipped: false
	                    }]
	                },
	                options: {
	                    responsive: true,
	                    maintainAspectRatio: false,
	                    plugins: {
	                        legend: {
	                            display: false
	                        },
	                        tooltip: {
	                            enabled: true
	                        }
	                    },
	                    scales: {
	                        y: {
	                            beginAtZero: true,
	                            ticks: {
	                                display: false 
	                            },
	                            grid: {
	                                display: false
	                            }
	                        },
	                        x: {
	                            ticks: {
	                                color: '#999999',
	                                font: { size: 11 }
	                            },
	                            grid: {
	                                display: false
	                            },
	                            
	                            barPercentage: 0.5, 
	                            categoryPercentage: 0.6 
	                        }
	                    },
	                    animation: {
	                        onComplete: () => {
	                            const chartInstance = chart1;
	                            const ctx = chartInstance.ctx;
	                            ctx.font = '12px Poppins, sans-serif';
	                            ctx.fillStyle = '#333333';
	                            ctx.textAlign = 'center';
	                            ctx.textBaseline = 'bottom';
	
	                            chartInstance.data.datasets.forEach((dataset, i) => {
	                                const meta = chartInstance.getDatasetMeta(i);
	                                meta.data.forEach((bar, index) => {
	                                    const val = dataset.data[index];
	                                    const formattedVal = 'R$' + val.toLocaleString('pt-BR', {minimumFractionDigits: 2, maximumFractionDigits: 2});
	                                    let yPos = bar.y - 8;
	                                    if (yPos < chartInstance.chartArea.top + 12) {
	                                      yPos = bar.y + 14;  
	                                      ctx.textBaseline = 'top';  
	                                    } else {
	                                      ctx.textBaseline = 'bottom';  
	                                    }
	
	                                    ctx.fillText(formattedVal, bar.x, yPos);
	                                });
	                            });
	                        }
	                    }
	                }
	            });
	        }
	
	        criarGrafico(labelsIniciais, valoresIniciais, 'Lucro Total por Área (R$)');
	
	        const filterContainer = document.querySelector('.dropdown-menu[aria-labelledby="dropdownMenuButtonLucro"]');
	
	        if (!filterContainer) {
	            console.error("Contêiner de filtro não encontrado.");
	            return;
	        }
	
	        filterContainer.addEventListener('click', function(event) {
	            const clickedElement = event.target;
	            const btnFiltro = clickedElement.closest('.btn-filtro');
	
	            if (!btnFiltro) {
	                console.warn("Clique fora dos botões de filtro.");
	                return;
	            }
	
	            const idAreaRaw = btnFiltro.getAttribute('data-id-area');
	
	            if (!idAreaRaw) {
	                console.warn("data-id-area não encontrado no botão clicado.");
	                return;
	            }
	
	            const idArea = parseInt(idAreaRaw);
	
	            if (isNaN(idArea)) {
	                console.warn("idArea não é um número válido:", idAreaRaw);
	                return;
	            }
	
	            const nomeArea = btnFiltro.querySelector('p')?.innerText || "Área desconhecida";
	
	            if (tituloGrafico1) {
					tituloGrafico1.innerText = `LUCRO X PROJETO - `+ nomeArea; 
				}
	
	            const url = '<%=request.getContextPath()%>/Dashboards?action=filtrarPorArea&idArea=' + idArea;
	            console.log("URL usada no fetch:", url);
	
	            fetch(url)
	                .then(res => {
	                    if (!res.ok) {
	                        throw new Error('Erro na resposta da rede: ' + res.statusText);
	                    }
	                    return res.json();
	                })
	                .then(data => {
	                    console.log("Dados recebidos:", data);
	                    const labels = data.map(d => d.titulo);
	                    const valores = data.map(d => d.lucro);
	
	                    criarGrafico(labels, valores, `Lucro dos Projetos em ` +nomeArea + ` (R$)`);
	                })
	                .catch(error => {
	                    console.error('Erro ao buscar dados filtrados:', error);
	
	                    if (tituloGrafico1) {
	                        tituloGrafico1.innerText = `LUCRO X ÁREA`;
	                    }
	                });
	        });
	
	        const resetFilterButton = document.getElementById("resetFilterButtonLucro");
	        resetFilterButton.addEventListener("click", function () {
	            tituloGrafico1.innerText = "LUCRO X ÁREA"; 
	            criarGrafico(labelsIniciais, valoresIniciais, 'Lucro Total por Área (R$)');  
	        });
	    });
	    
    </script>
	<script>
		document.addEventListener("DOMContentLoaded", function () {
		    const coresStatus = {
		        "CONCLUÍDO": '#6260FF',
		        "EM ANDAMENTO": '#9660FF',
		        "NÃO INICIADO": '#5CCEE6'
		    };
		
		    let chartStatus;
		    const tituloGraficoStatus = document.getElementById("titulo-grafico2");
		
		    function getLegendPosition() {
		        return window.innerWidth <= 768 ? 'top' : 'left';
		    }
		    
		    const statusIniciais = [
		        <c:forEach items="${statusTotalList}" var="status" varStatus="loop">
		            { status: '${status.status}', quantidade: +${status.quantidade} }${!loop.last ? ',' : ''} 
		        </c:forEach>
		    ];
		
		    console.log("Dados iniciais para o gráfico de status:", statusIniciais);
		
		    function criarGraficoStatus(data) {
		        if (chartStatus) {
		            chartStatus.destroy();
		        }
		
		        const totalProjetos = data.reduce((sum, current) => sum + current.quantidade, 0);
		
		        const ctx = document.getElementById("grafico2").getContext('2d');
		        const labels = data.map(d => d.status);
		        const valores = data.map(d => d.quantidade);
		        const backgroundColors = data.map(d => coresStatus[d.status] || '#cccccc');
		
		        chartStatus = new Chart(ctx, {
		            type: 'pie',
		            data: {
		                labels: labels,
		                datasets: [{
		                    data: valores,
		                    backgroundColor: backgroundColors,
		                    borderWidth: 0
		                }]
		            },
		            options: {
		                responsive: true,
		                maintainAspectRatio: false,
		                plugins: {
		                    legend: {
		                        position: getLegendPosition(), 
		                        labels: {
		                            padding: 10,
		                            boxWidth: 20,
		                        },
		                    },
		                    tooltip: {
		                        enabled: true,
		                        callbacks: {
		                            label: function (context) {
		                                const label = context.label || '';
		                                const value = context.raw || 0;
		                                const totalProjetos = context.chart.data.datasets[0].data.reduce((sum, currentValue) => sum + currentValue, 0);
		                                const percentage = totalProjetos ? ((value / totalProjetos) * 100).toFixed(0) : 0;
		                                return percentage + "%" + " (" + value + ")";
		                            }
		                        }
		                    },
		                    datalabels: {
		                        color: '#fff',
		                        font: {
		                            weight: 'bold',
		                            size: 14
		                        },
		                        formatter: function(value, context) {
		                            const totalProjetos = context.chart.data.datasets[0].data.reduce((sum, currentValue) => sum + currentValue, 0);
		                            const percentage = totalProjetos ? ((value / totalProjetos) * 100).toFixed(0) : 0;
		                            return percentage + "%";
		                        },
		                        anchor: 'end',
		                        align: 'start',
		                        offset: 10,
		                    }
		                }
		            },
		            plugins: [ChartDataLabels]
		        });
		    }
		
		    criarGraficoStatus(statusIniciais);
		
		
		    window.addEventListener('resize', () => {
		        if (chartStatus) {
		            chartStatus.options.plugins.legend.position = getLegendPosition();
		            chartStatus.update();
		        }
		    });
		
		    const filterContainerStatus = document.querySelector('.dropdown-menu[aria-labelledby="dropdownMenuButtonStatus"]');
		    if (filterContainerStatus) {
		        filterContainerStatus.addEventListener('click', function (event) {
		            const btnFiltro = event.target.closest('.btn-filtro');
		            if (btnFiltro) {
		                const idAreaRaw = btnFiltro.getAttribute('data-id-area');
		                if (idAreaRaw) {
		                    const idArea = parseInt(idAreaRaw);
		                    const nomeArea = btnFiltro.querySelector('p')?.innerText || "Área desconhecida";
		
		                    if (tituloGraficoStatus) {
		                        tituloGraficoStatus.innerText = `STATUS X PROJETO - ` + nomeArea;
		                    }
		
		                    function atualizarGraficoStatusPorArea(idArea) {
		                        const url = '<%=request.getContextPath()%>/Dashboards?action=filtrarStatusPorArea&idArea=' + idArea;
		
		                        fetch(url)
		                            .then(res => res.json())
		                            .then(data => {
		                                criarGraficoStatus(data);
		                            })
		                            .catch(error => {
		                                console.error('Erro ao buscar dados filtrados:', error);
		                                if (tituloGraficoStatus) {
		                                    tituloGraficoStatus.innerText = 'STATUS X PROJETO';
		                                }
		                            });
		                    }
		                    atualizarGraficoStatusPorArea(idArea);
		                }
		            }
		        });
		    }
		
		    const resetFilterButton = document.getElementById("resetFilterButtonStatus");
		    resetFilterButton.addEventListener("click", function () {
		        tituloGraficoStatus.innerText = "STATUS X ÁREA";
		        criarGraficoStatus(statusIniciais);
		    });
		});
	</script>
	<script>
		document.addEventListener("DOMContentLoaded", function () {
		
		    const labelsIniciaisGrafico3 = [
		        <c:forEach items="${quantidadeProjetosList}" var="item" varStatus="loop">
		            '${item.area}'${!loop.last ? ',' : ''}
		        </c:forEach>
		    ];
		
		    const valoresIniciaisGrafico3 = [
		        <c:forEach items="${quantidadeProjetosList}" var="item" varStatus="loop">
		            ${item.quantidade}${!loop.last ? ',' : ''}
		        </c:forEach>
		    ];
		
		    let chart3;
		
		    function criarGrafico3(labels, data) {
		        if (chart3) {
		            chart3.destroy();
		        }
		
		        const ctx3 = document.getElementById("grafico3").getContext('2d');
		        chart3 = new Chart(ctx3, {
		            type: 'bar',
		            data: {
		                labels: labels,
		                datasets: [{
		                    label: 'Quantidade de Projetos',
		                    data: data,
		                    backgroundColor: '#6292FF',
		                    borderRadius: 4,
		                    borderSkipped: false
		                }]
		            },
		            options: {
		                indexAxis: 'y', 
		                responsive: true,
		                maintainAspectRatio: false,
		                plugins: {
		                    legend: { display: false },
		                    tooltip: { enabled: true }
		                },
		                scales: {
		                    x: { 
		                        beginAtZero: true,
		                        ticks: {
		                             display: false
		                        },
		                        grid: { display: false }
		                    },
		                    y: { 
		                        ticks: {
		                            color: '#999999',
		                            font: { size: 11 }
		                        },
		                        grid: { display: false }
		                    }
		                },
		                animation: {
		                	onComplete: () => {
		                        const chartInstance = chart3;
		                        const ctx = chartInstance.ctx;
		                        ctx.font = '12px Poppins, sans-serif';
		                        ctx.fillStyle = '#333333';
		                        ctx.textAlign = 'left';
		                        ctx.textBaseline = 'middle';
		                        chartInstance.data.datasets.forEach((dataset, i) => {
		                            const meta = chartInstance.getDatasetMeta(i);
		                            meta.data.forEach((bar, index) => {
		                                const val = dataset.data[index];
		                                ctx.fillText(val, bar.x + 10, bar.y);
		                            });
		                        });
		                    }
		                }
		            }
		        });
		    }
		
		    criarGrafico3(labelsIniciaisGrafico3, valoresIniciaisGrafico3);
		});
	</script>
<script>
document.addEventListener("DOMContentLoaded", function () {

    const coresStatus = {
        "Não Iniciado": '#5CCEE6',
        "Em andamento": '#9660FF',
        "Concluído": '#6260FF'
    };

    const meses = ["Janeiro","Fevereiro","Março","Abril","Maio","Junho",
                   "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"];

    const naoIniciado = Array(12).fill(0);
    const emAndamento = Array(12).fill(0);
    const concluido = Array(12).fill(0);

    // Variável única para evitar erro de "already declared"
    let indiceMes;

    // Preencher com dados do servidor via JSP
    <c:forEach items="${evolucaoMensalList}" var="evo">
        indiceMes = meses.indexOf("${evo.mes}");
        if (indiceMes >= 0) {
            naoIniciado[indiceMes] = ${evo.naoIniciado};
            emAndamento[indiceMes] = ${evo.emAndamento};
            concluido[indiceMes] = ${evo.concluido};
        }
    </c:forEach>

    const ctx = document.getElementById('grafico4').getContext('2d');

    let chartEvolucao = new Chart(ctx, {
        type: 'line',
        data: {
            labels: meses,
            datasets: [
                {
                    label: 'NÃO INICIADO',
                    data: naoIniciado,
                    borderColor: coresStatus["Não Iniciado"],
                    backgroundColor: coresStatus["Não Iniciado"],
                    tension: 0.3,
                    fill: false,
                    pointRadius: 6,
                    pointHoverRadius: 8
                },
                {
                    label: 'EM ANDAMENTO',
                    data: emAndamento,
                    borderColor: coresStatus["Em andamento"],
                    backgroundColor: coresStatus["Em andamento"],
                    tension: 0.3,
                    fill: false,
                    pointRadius: 6,
                    pointHoverRadius: 8
                },
                {
                    label: 'CONCLUÍDO',
                    data: concluido,
                    borderColor: coresStatus["Concluído"],
                    backgroundColor: coresStatus["Concluído"],
                    tension: 0.3,
                    fill: false,
                    pointRadius: 6,
                    pointHoverRadius: 8
                }
            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'right',
                    labels: {
                        usePointStyle: true,
                        pointStyle: 'line',
                        font: {
                            size: 12
                        }
                    }
                },
                
                tooltip: {
                    enabled: true
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: 'Número de Projetos'
                    },
                    ticks: {
                        stepSize: 1
                    }
                },
                x: {
                    grid: {
                        drawOnChartArea: true
                    }
                }
            }
        }    });

    const tituloGrafico = document.getElementById("titulo-grafico4");

    // Filtro por área
    const filterContainer = document.querySelector('.dropdown-menu[aria-labelledby="dropdownMenuButtonEvolucao"]');
    if (filterContainer) {
        filterContainer.addEventListener('click', function (event) {
            const btnFiltro = event.target.closest('.btn-filtro');
            if (!btnFiltro) return;

            const idAreaRaw = btnFiltro.getAttribute('data-id-area');
            if (!idAreaRaw) return;
            const idArea = parseInt(idAreaRaw);
            const nomeArea = btnFiltro.querySelector('p')?.innerText || "Área desconhecida";

            if (tituloGrafico) tituloGrafico.innerText = `EVOLUÇÃO MENSAL - ` + nomeArea;

            fetch('<%=request.getContextPath()%>/Dashboards?action=filtrarEvolucaoMensal&idArea=' + idArea)
                .then(res => res.json())
                .then(data => {
                    let novaNaoIniciado = Array(12).fill(0);
                    let novaEmAndamento = Array(12).fill(0);
                    let novaConcluido = Array(12).fill(0);

                    data.forEach(d => {
                        const indice = meses.indexOf(d.mes);
                        if (indice >= 0) {
                            novaNaoIniciado[indice] = d.naoIniciado;
                            novaEmAndamento[indice] = d.emAndamento;
                            novaConcluido[indice] = d.concluido;
                        }
                    });

                    chartEvolucao.data.datasets[0].data = novaNaoIniciado;
                    chartEvolucao.data.datasets[1].data = novaEmAndamento;
                    chartEvolucao.data.datasets[2].data = novaConcluido;
                    chartEvolucao.update();
                })
                .catch(err => {
                    console.error('Erro ao buscar dados filtrados:', err);
                    if (tituloGrafico) tituloGrafico.innerText = 'EVOLUÇÃO MENSAL';
                });
        });
    }

    // Botão de reset
    const resetButton = document.getElementById("resetFilterButtonEvolucao");
    if (resetButton) {
        resetButton.addEventListener('click', function () {
            if (tituloGrafico) tituloGrafico.innerText = "EVOLUÇÃO MENSAL";

            chartEvolucao.data.datasets[0].data = naoIniciado;
            chartEvolucao.data.datasets[1].data = emAndamento;
            chartEvolucao.data.datasets[2].data = concluido;
            chartEvolucao.update();
        });
    }

});
</script>

</body>
</html>