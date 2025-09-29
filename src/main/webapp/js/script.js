
document.querySelectorAll("[data-bs-target='#meuModal']").forEach(botao => {
  botao.addEventListener("click", function() {
    const statusTexto = this.getAttribute("data-status") || "";
    const statusId = this.getAttribute("data-status-id") || "";

    const inputTitulo = document.getElementById("modalTituloInputCriar");
    const statusInput = document.getElementById("statusProjeto");

    if (inputTitulo) {
      inputTitulo.placeholder = `ADICIONE UM TÍTULO - ${statusTexto.toUpperCase()}`;
      inputTitulo.value = "";
    }
    if (statusInput) {
      statusInput.value = statusId;
    }

    const participantesSelecionadosCriar = document.getElementById('participantesSelecionados');
    const inputsParticipantesCriar = document.getElementById('inputsParticipantes');
    participantesSelecionadosCriar.innerHTML = '';
    inputsParticipantesCriar.innerHTML = '';
    document.querySelectorAll('.participante-item').forEach(item => item.style.display = 'block');
  });
});

const btnNovoPost = document.getElementById('btn-novo-post');
if (btnNovoPost) {
  btnNovoPost.addEventListener('click', function() {
    const navbar = document.getElementById('navbarNav');
    const isMobile = window.innerWidth < 992;
    if (isMobile && navbar.classList.contains('show')) {
      const bsCollapse = bootstrap.Collapse.getInstance(navbar) || new bootstrap.Collapse(navbar);
      bsCollapse.hide();
    }
  });
}

document.addEventListener("DOMContentLoaded", () => {
  const consulta = document.querySelector('.consulta');
  if (consulta) {
    consulta.style.maxHeight = `${consulta.scrollHeight}px`;
  }
});

const togglePassword = document.querySelector('#togglePass');
const togglePassword2 = document.querySelector('#togglePass2');
const password = document.querySelector('#password');
const confpassword = document.querySelector('#confpassword');

if (togglePassword && password) {
  togglePassword.addEventListener("click", function() {
    const type = password.type === "password" ? "text" : "password";
    password.type = type;
    this.classList.toggle("bi-eye");
    this.classList.toggle("bi-eye-slash");
  });
}

if (togglePassword2 && confpassword) {
  togglePassword2.addEventListener("click", function() {
    const type = confpassword.type === "password" ? "text" : "password";
    confpassword.type = type;
    this.classList.toggle("bi-eye");
    this.classList.toggle("bi-eye-slash");
  });
}

document.querySelectorAll('input[data-js]').forEach(($input) => {
  const field = $input.dataset.js;
  $input.addEventListener('input', (e) => {
    if (masks[field]) {
      e.target.value = masks[field](e.target.value);
    }
  }, false);
});

const button = document.getElementById('cadastrar');
if (button) {
  button.addEventListener('click', (event) => {
    event.preventDefault();

    let isValid = true;
    const form = document.querySelector('form');

    const matricula = document.getElementById('matricula');
    const nome = document.getElementById('nome');
    const cargo = document.getElementById('cargo');
    const email = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const confpasswordInput = document.getElementById('confpassword');

    if (matricula.value === '') {
      matricula.classList.add("errorInput");
      isValid = false;
    }
    if (nome.value === '') {
      nome.classList.add("errorInput");
      isValid = false;
    }
    if (cargo.value === '') {
      cargo.classList.add("errorInput");
      isValid = false;
    }
    if (email.value === '') {
      email.classList.add("errorInput");
      isValid = false;
    }
    if (passwordInput.value === '') {
      passwordInput.classList.add("errorInput");
      isValid = false;
    }
    if (confpasswordInput.value === '') {
      confpasswordInput.classList.add("errorInput");
      isValid = false;
    }

    if (email.value.indexOf("@") === -1 || email.value.indexOf(".") === -1 || (email.value.indexOf(".") - email.value.indexOf("@") === 1)) {
      email.classList.add("errorInput");
      isValid = false;
    } else {
      email.classList.remove("errorInput");
    }

    if (isValid) {
      form.submit();
    }
  });
}


document.querySelectorAll('input').forEach(($input) => {
  $input.addEventListener('input', (e) => {
    e.target.classList.remove("errorInput");
  });
});

const buttonlogin = document.getElementById('entrar');
if (buttonlogin) {
  buttonlogin.addEventListener('click', (event) => {
    event.preventDefault();

    let isValid = true;
    const form = document.querySelector('form');

    const email = document.getElementById('email');
    const passwordLogin = document.getElementById('password');

    if (email.value === '') {
      email.classList.add("errorInput");
      isValid = false;
    }
    if (passwordLogin.value === '') {
      passwordLogin.classList.add("errorInput");
      isValid = false;
    }
    if (email.value.indexOf("@") === -1 || email.value.indexOf(".") === -1 || (email.value.indexOf(".") - email.value.indexOf("@") === 1)) {
      email.classList.add("errorInput");
      isValid = false;
    } else {
      email.classList.remove("errorInput");
    }

    if (isValid) {
      form.submit();
    }
  });
}

document.querySelectorAll('input').forEach(($input) => {
  $input.addEventListener('input', (e) => {
    e.target.classList.remove("errorInput");
  });
});

const inputsParticipantesCriar = document.getElementById('inputsParticipantes');
const participantesSelecionadosCriar = document.getElementById('participantesSelecionados');
const dropdownMenuCriar = document.querySelector('#meuModal .dropdown-menu');

const participantesAdicionadosCriar = new Set();

if (dropdownMenuCriar) {
    dropdownMenuCriar.addEventListener('click', (event) => {
        const item = event.target.closest('.participante-item');
        if (!item) return;

        const id = item.getAttribute('data-id');
        const nome = item.getAttribute('data-nome');
        const foto = item.getAttribute('data-foto');

        if (participantesAdicionadosCriar.has(id)) {
            return;
        }

        const img = document.createElement('img');
        img.src = foto;
        img.alt = nome;
        img.title = nome;
        img.id = 'foto-criacao-' + id;
        img.className = 'foto rounded-circle shadow me-1';
        img.style.width = '40px';
        img.style.height = '40px';
        img.style.cursor = 'pointer';

        img.addEventListener("click", function() {
            img.remove();
            const inputParaRemover = document.getElementById("input-criacao-" + id);
            if (inputParaRemover) {
                inputParaRemover.remove();
            }
            item.style.display = 'block';
            participantesAdicionadosCriar.delete(id); 
            atualizarDropdownCriacao();
        });

        participantesSelecionadosCriar.appendChild(img);

        const inputHidden = document.createElement('input');
        inputHidden.type = 'hidden';
        inputHidden.name = 'participantes';
        inputHidden.value = id;
        inputHidden.id = 'input-criacao-' + id;
        inputsParticipantesCriar.appendChild(inputHidden);
        
        item.style.display = 'none';
        participantesAdicionadosCriar.add(id); 
        atualizarDropdownCriacao();
    });
}

function atualizarDropdownCriacao() {
    const itens = document.querySelectorAll('#meuModal .participante-item');
    itens.forEach(item => {
        const id = item.getAttribute('data-id');
        if (participantesAdicionadosCriar.has(id)) {
            item.style.display = 'none';
        }
    });
}

const inputBuscarCriar = document.getElementById('inputBuscarParticipanteCriacao');
if (inputBuscarCriar) {
  inputBuscarCriar.addEventListener('input', function () {
    const termoBusca = this.value.toLowerCase();
    const itens = dropdownMenuCriar.querySelectorAll('.participante-item');

    itens.forEach(item => {
      const nome = item.getAttribute('data-nome').toLowerCase();
      const email = item.querySelector('.cargo').textContent.toLowerCase();
      const idParticipante = item.getAttribute('data-id');

      if ((nome.includes(termoBusca) || email.includes(termoBusca)) && !participantesAdicionadosCriar.has(idParticipante)) {
        item.style.display = 'block';
      } else {
        item.style.display = 'none';
      }
    });
  });
}

const participantesAdicionados = new Set();
const participantesSelecionadosEditar = document.getElementById("participantesSelecionadosEditar");
const inputsParticipantesEditar = document.getElementById("inputsParticipantesEditar");

function atualizarDropdownParticipantes() {
  document.querySelectorAll('.participante-item-editar').forEach(item => {
    const idParticipante = item.getAttribute('data-id');
    if (participantesAdicionados.has(idParticipante)) {
      item.style.display = 'none';
    } else {
      item.style.display = 'block';
    }
  });
}

document.querySelectorAll(".btn-editar").forEach(botao => {
  botao.addEventListener("click", function () {
    const id = this.getAttribute("data-id");
    const titulo = this.getAttribute("data-titulo");
    const descricao = this.getAttribute("data-descricao");

    participantesSelecionadosEditar.innerHTML = '';
    inputsParticipantesEditar.innerHTML = '';
    participantesAdicionados.clear();
    
    document.getElementById("modalTituloInputEditar").value = titulo;
    document.getElementById("descricaoEditar").value = descricao;
    document.getElementById("idProjetoEditar").value = id;

    const card = this.closest(".card-tarefa");
    const fotosParticipantesNoCard = card.querySelectorAll(".d-flex.gap-2 img");

    fotosParticipantesNoCard.forEach(fotoOriginal => {
      const idParticipante = fotoOriginal.getAttribute("data-id"); 
      const nomeParticipante = fotoOriginal.getAttribute("alt");
      const fotoSrc = fotoOriginal.src;

      const img = document.createElement("img");
      img.src = fotoSrc;
      img.alt = nomeParticipante;
      img.title = nomeParticipante;
      img.id = "foto-edicao-" + idParticipante;
      img.className = "foto rounded-circle shadow me-1";
      img.style.width = "40px";
      img.style.height = "40px";
      img.style.cursor = "pointer";

      const inputHidden = document.createElement("input");
      inputHidden.type = "hidden";
      inputHidden.name = "participantes";
      inputHidden.value = idParticipante;
      inputHidden.id = "input-edicao-" + idParticipante;

      img.addEventListener("click", function() {
        img.remove();
        inputHidden.remove();
        participantesAdicionados.delete(idParticipante);
        atualizarDropdownParticipantes(); 
      });

      participantesSelecionadosEditar.appendChild(img);
      inputsParticipantesEditar.appendChild(inputHidden);

      participantesAdicionados.add(idParticipante);
    });
    
    atualizarDropdownParticipantes();
  });
});

const modalEdicao = document.getElementById('modalEdicao');
if (modalEdicao) {
    modalEdicao.addEventListener('show.bs.modal', function () {
        atualizarDropdownParticipantes();
    });
}

const dropdownMenuEditar = document.querySelector('#modalEdicao .dropdown-menu');

if (dropdownMenuEditar) {
  const inputBuscar = document.getElementById('inputBuscarParticipante');
  if (inputBuscar) {
    inputBuscar.addEventListener('input', function() {
      const termoBusca = this.value.toLowerCase();
      const itens = dropdownMenuEditar.querySelectorAll('.participante-item-editar');
      
      itens.forEach(item => {
        const nome = item.getAttribute('data-nome').toLowerCase();
        const email = item.querySelector('.cargo').textContent.toLowerCase();
        const idParticipante = item.getAttribute('data-id');

        if ((nome.includes(termoBusca) || email.includes(termoBusca)) && !participantesAdicionados.has(idParticipante)) {
          item.style.display = 'block';
        } else {
          item.style.display = 'none';
        }
      });
    });
  }

  dropdownMenuEditar.addEventListener('click', (event) => {
    const item = event.target.closest('.participante-item-editar');
    if (!item) return;

    const id = item.getAttribute('data-id');
    const nome = item.getAttribute('data-nome');
    const foto = item.getAttribute('data-foto');

    if (participantesAdicionados.has(id)) {
        return;
    }

    const img = document.createElement('img');
    img.src = foto;
    img.alt = nome;
    img.title = nome;
    img.id = "foto-edicao-" + id;
    img.className = 'foto rounded-circle shadow me-1';
    img.style.width = '40px';
    img.style.height = '40px';
    img.style.cursor = 'pointer';
    
    img.addEventListener("click", function() {
      img.remove();
      const inputParaRemover = document.getElementById("input-edicao-" + id);
      if (inputParaRemover) {
        inputParaRemover.remove();
      }
      participantesAdicionados.delete(id);
      atualizarDropdownParticipantes();
    });

    participantesSelecionadosEditar.appendChild(img);

    const inputHidden = document.createElement('input');
    inputHidden.type = 'hidden';
    inputHidden.name = 'participantes';
    inputHidden.value = id;
    inputHidden.id = "input-edicao-" + id;
    inputsParticipantesEditar.appendChild(inputHidden);
    
    participantesAdicionados.add(id);
    atualizarDropdownParticipantes();
  });
}

const formNovoProjeto = document.getElementById('formNovoProjeto');
const btnAdicionar = document.getElementById('btnAdicionar');
if (btnAdicionar) {
  btnAdicionar.addEventListener('click', function(event) {
    if (formNovoProjeto) {
      formNovoProjeto.submit();
    }
  });
}

document.addEventListener("DOMContentLoaded", function () {
    const columns = document.querySelectorAll(".card-body"); 

    columns.forEach(coluna => {
        coluna.addEventListener("drop", function (event) {
            event.preventDefault();
            const card = document.querySelector(".dragging");

            if (card) {
                coluna.appendChild(card);

                let novoStatus;
                if (coluna.closest(".card").querySelector(".texto-status").innerText.includes("NÃO INICIADO")) {
                    novoStatus = 1;
                } else if (coluna.closest(".card").querySelector(".texto-status").innerText.includes("EM ANDAMENTO")) {
                    novoStatus = 2;
                } else if (coluna.closest(".card").querySelector(".texto-status").innerText.includes("CONCLUÍDO")) {
                    novoStatus = 3;
                }

                let form = card.querySelector(".form-status");
                form.querySelector(".statusProjeto").value = novoStatus;

                form.submit();
            }
        });

        coluna.addEventListener("dragover", function (event) {
            event.preventDefault();
        });
    });

    document.querySelectorAll("[draggable='true']").forEach(card => {
        card.addEventListener("dragstart", () => {
            card.classList.add("dragging");
        });
        card.addEventListener("dragend", () => {
            card.classList.remove("dragging");
        });
    });
});

const searchBar = document.querySelector(".barra-search");
const botoesFiltro = document.querySelectorAll("button[data-area]");
const projetos = document.querySelectorAll(".card-tarefa");
const colunas = document.querySelectorAll(".col-md-3");

let termoPesquisa = "";
let areaSelecionada = "";

const mensagensNoResults = new Map();
colunas.forEach(coluna => {
    const cardBody = coluna.querySelector(".card-body");
    if (cardBody) {
        const messageDiv = document.createElement("div");
        messageDiv.className = "no-results-message text-center text-muted p-3";
        messageDiv.textContent = "NENHUM PROJETO ENCONTRADO";
        messageDiv.style.display = "none";
        coluna.querySelector(".card").insertBefore(messageDiv, cardBody);
        mensagensNoResults.set(coluna, messageDiv);
    }
});

const aplicarFiltros = () => {
  const colunasComResultados = new Set();

  projetos.forEach(projeto => {
    const titulo = projeto.querySelector(".titulo-projeto").textContent.toLowerCase();
    const descricao = projeto.querySelector(".card-body p").textContent.toLowerCase();

    const projetoArea = (projeto.getAttribute("data-area") || "").toLowerCase();
    const nomeArea = (projeto.getAttribute("data-area-nome") || "").toLowerCase(); 

    const participantesImgs = projeto.querySelectorAll(".d-flex.gap-2 img");
    const participantesNomes = Array.from(participantesImgs).map(img => img.alt.toLowerCase());
    const participantesEmails = Array.from(participantesImgs).map(img => img.title.toLowerCase());

    const colunaPai = projeto.closest(".col-md-3");

    const correspondeAoTermo =
      titulo.includes(termoPesquisa) ||
      descricao.includes(termoPesquisa) ||
      projetoArea.includes(termoPesquisa) ||  
      nomeArea.includes(termoPesquisa) ||    
      participantesNomes.some(nome => nome.includes(termoPesquisa)) ||
      participantesEmails.some(email => email.includes(termoPesquisa));

    const correspondeAArea = (areaSelecionada === "") || (projetoArea === areaSelecionada);

    if (correspondeAoTermo && correspondeAArea) {
      projeto.style.display = "block";
      colunasComResultados.add(colunaPai);
    } else {
      projeto.style.display = "none";
    }
  });

  colunas.forEach(coluna => {
    const mensagem = mensagensNoResults.get(coluna);
    if (mensagem) {
      mensagem.style.display = colunasComResultados.has(coluna) ? "none" : "block";
    }
  });
};



searchBar.addEventListener("input", (e) => {
    termoPesquisa = e.target.value.toLowerCase().trim();
    aplicarFiltros();
});

botoesFiltro.forEach(botao => {
    botao.addEventListener("click", () => {
        const novaArea = botao.getAttribute("data-area");

        if (areaSelecionada === novaArea) {
            areaSelecionada = "";
        } else {
            areaSelecionada = novaArea;
        }
        
        aplicarFiltros();
    });
});

aplicarFiltros();

document.querySelectorAll('.swiper-slide .btn-mover').forEach(button => {
  button.addEventListener('click', (e) => {
      e.preventDefault(); 
      
      const newStatusId = e.target.dataset.statusId;
      const cardElement = e.target.closest('.card-tarefa');

      if (cardElement) {
          const form = cardElement.querySelector('.form-status');
          const statusInput = form.querySelector('.statusProjeto');
          
          if (form && statusInput) {
              statusInput.value = newStatusId;
              form.submit();
          }
      }
  });
});