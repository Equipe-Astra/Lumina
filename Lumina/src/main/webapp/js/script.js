document.addEventListener("DOMContentLoaded", function () {
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
});

document.addEventListener('DOMContentLoaded', () => {
  document.querySelectorAll('.open-modal').forEach(botao => {
    botao.addEventListener('click', () => {
      const statusTexto = botao.getAttribute('data-status'); // Ex: "Não Iniciado"
      const statusId = botao.getAttribute('data-status-id');  // Ex: "1"

      const inputTitulo = document.getElementById('modalTituloInput');
      const statusInput = document.getElementById('statusProjeto');

      const novoTexto = `ADICIONE UM TÍTULO - ${statusTexto.toUpperCase()}`;
      inputTitulo.placeholder = novoTexto;
      inputTitulo.value = '';  // Limpa para o usuário digitar

      statusInput.value = statusId;  // Atualiza o input hidden com o id correto
    });
  });
});



// Upload de imagem
const imageInput = document.getElementById('image-upload');
if (imageInput) {
  const imageName = document.getElementById('image-name');
  imageInput.addEventListener('change', function () {
    if (imageInput.files.length > 0) {
      imageName.textContent = imageInput.files[0].name;
    } else {
      imageName.textContent = "Adicione uma imagem";
    }
  });
}

// Upload de projeto
const projectInput = document.getElementById('file-upload');
if (projectInput) {
  const projectName = document.getElementById('file-name');
  projectInput.addEventListener('change', function () {
    if (projectInput.files.length > 0) {
      projectName.textContent = projectInput.files[0].name;
    } else {
      projectName.textContent = "Faça o upload do seu projeto aqui";
    }
  });
}

const btnNovoPost = document.getElementById('btn-novo-post');
if (btnNovoPost) {
  btnNovoPost.addEventListener('click', function () {
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
  togglePassword.addEventListener("click", function () {
    const type = password.type === "password" ? "text" : "password";
    password.type = type;
    this.classList.toggle("bi-eye");
    this.classList.toggle("bi-eye-slash");
  });
}

if (togglePassword2 && confpassword) {
  togglePassword2.addEventListener("click", function () {
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

    let isValid = true; // controle de validação
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

const participantesSelecionadosDiv = document.getElementById("participantesSelecionados");
const inputsParticipantesDiv = document.getElementById("inputsParticipantes");

document.querySelectorAll(".participante-item").forEach(item => {
    item.addEventListener("click", function(e) {
        e.preventDefault();
        const id = this.dataset.id;
        const nome = this.dataset.nome;
        const foto = this.dataset.foto;

        if (document.getElementById("foto-" + id)) return;

        // Mostrar foto no modal
        const img = document.createElement("img");
        img.src = foto;
        img.alt = nome;
        img.className = "foto rounded-circle shadow me-1";
        img.id = "foto-" + id;
        img.style.width = "40px";
        img.style.height = "40px";
        img.style.cursor = "pointer";

        img.addEventListener("click", function() {
            img.remove();
            document.getElementById("input-" + id).remove();
        });

        participantesSelecionadosDiv.appendChild(img);

        // Criar input hidden para o participante
        const input = document.createElement("input");
        input.type = "hidden";
        input.name = "participantes";  // mesmo nome para criar array no backend
        input.value = id;
        input.id = "input-" + id;
        inputsParticipantesDiv.appendChild(input);
    });
});



