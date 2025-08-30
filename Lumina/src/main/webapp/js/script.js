document.addEventListener("DOMContentLoaded", function() {
  const currentPage = window.location.pathname.split("/").pop();

  document.querySelectorAll("nav a").forEach(link => {
    const linkPage = link.getAttribute("href").split("/").pop();
    const iconDiv = link.querySelector("div.icon-wrapper");

    if (iconDiv) {
      iconDiv.classList.remove("pagina-atual");
    }

    if (linkPage === currentPage && iconDiv) {
      iconDiv.classList.add("pagina-atual");
    }
  });

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

  // Toggle senha
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

  // Validação
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

  // Delegação de eventos para o dropdown de criação
  const inputsParticipantes = document.getElementById('inputsParticipantes');
  const participantesSelecionados = document.getElementById('participantesSelecionados');
  const dropdownMenuCriar = document.querySelector('#meuModal .dropdown-menu');
  if (dropdownMenuCriar) {
      dropdownMenuCriar.addEventListener('click', (event) => {
          const item = event.target.closest('.participante-item');
          if (!item) return;

          const id = item.getAttribute('data-id');
          const nome = item.getAttribute('data-nome');
          const foto = item.getAttribute('data-foto');

          const fotoExistente = document.getElementById('foto-criacao-' + id);
          if (fotoExistente) return;

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
          });

          participantesSelecionados.appendChild(img);

          const inputHidden = document.createElement('input');
          inputHidden.type = 'hidden';
          inputHidden.name = 'participantes';
          inputHidden.value = id;
          inputHidden.id = 'input-criacao-' + id;
          inputsParticipantes.appendChild(inputHidden);
          
          item.style.display = 'none';
      });
  }

  // --- LÓGICA DE EDIÇÃO REVISADA ---
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

  // A lógica para o modal de edição
  document.querySelectorAll(".btn-editar").forEach(botao => {
    botao.addEventListener("click", function () {
      const id = this.getAttribute("data-id");
      const titulo = this.getAttribute("data-titulo");
      const descricao = this.getAttribute("data-descricao");

      // Limpa tudo e reinicia o Set
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
          atualizarDropdownParticipantes(); // Atualiza o dropdown ao remover
        });

        participantesSelecionadosEditar.appendChild(img);
        inputsParticipantesEditar.appendChild(inputHidden);
        
        participantesAdicionados.add(idParticipante);
      });
      
      // Chamada da função de filtro quando o modal de edição é aberto
      atualizarDropdownParticipantes();
    });
  });

  // Adiciona evento de clique no botão de + para atualizar o dropdown
  const dropdownButtonEditar = document.getElementById("dropdownMenuButtonEditar");
  if (dropdownButtonEditar) {
    dropdownButtonEditar.addEventListener("click", function() {
        atualizarDropdownParticipantes();
    });
  }
  
  const modalEdicao = document.getElementById('modalEdicao');
  if (modalEdicao) {
      modalEdicao.addEventListener('show.bs.modal', function () {
          atualizarDropdownParticipantes();
      });
  }
  
  // Delegação de eventos para o dropdown de edição
  const dropdownMenuEditar = document.querySelector('#modalEdicao .dropdown-menu');
  
  if (dropdownMenuEditar) {
    // Adiciona o filtro de busca ao campo de texto
    const inputBuscar = document.getElementById('inputBuscarParticipante');
    if (inputBuscar) {
      inputBuscar.addEventListener('input', function() {
        const termoBusca = this.value.toLowerCase();
        const itens = dropdownMenuEditar.querySelectorAll('.participante-item-editar');
        
        itens.forEach(item => {
          const nome = item.getAttribute('data-nome').toLowerCase();
          const email = item.querySelector('.cargo').textContent.toLowerCase();
          const idParticipante = item.getAttribute('data-id');

          // Verifica se o item corresponde ao termo de busca E se ele ainda não foi adicionado
          if ((nome.includes(termoBusca) || email.includes(termoBusca)) && !participantesAdicionados.has(idParticipante)) {
            item.style.display = 'block';
          } else {
            item.style.display = 'none';
          }
        });
      });
    }

    // Adiciona o participante quando um item da lista é clicado
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
});

// A lógica de Drag and Drop
document.addEventListener('DOMContentLoaded', () => {
    const cards = document.querySelectorAll('.container-cards .card-tarefa');
    const columns = document.querySelectorAll('.container-cards .col-md-3 .card-body');

    let draggedCard = null;

    cards.forEach(card => {
        card.setAttribute('draggable', true);

        card.addEventListener('dragstart', () => {
            draggedCard = card;
            card.classList.add('dragging');
        });

        card.addEventListener('dragend', () => {
            draggedCard = null;
            card.classList.remove('dragging');
        });
    });

    columns.forEach(column => {
        column.addEventListener('dragover', e => {
            e.preventDefault();
            column.classList.add('drag-over');
        });

        column.addEventListener('dragleave', () => {
            column.classList.remove('drag-over');
        });

        column.addEventListener('drop', () => {
            if (!draggedCard) return;

            column.appendChild(draggedCard);
            column.classList.remove('drag-over');

            const cardColumn = column.closest('.col-md-3');
            const statusHeader = cardColumn.querySelector('.card-header-status .texto-status');
            const statusText = statusHeader ? statusHeader.textContent.trim().toUpperCase() : '';

            let statusId = 1;
            if (statusText.toLowerCase() === 'em andamento') {
                statusId = 2;
            } else if (statusText.toLowerCase() === 'concluído') {
                statusId = 3;
            }

            const form = draggedCard.querySelector('.form-status');
            const input = form.querySelector('input.statusProjeto');

            if (input) {
                input.value = statusId;
                setTimeout(() => form.submit(), 300);
            }
        });
    });
});