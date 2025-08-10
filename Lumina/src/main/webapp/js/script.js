
document.addEventListener("DOMContentLoaded", function () {
  const currentPage = window.location.pathname.split("/").pop();

  document.querySelectorAll("nav a").forEach(link => {
    const linkPage = link.getAttribute("href").split("/").pop();
    const iconDiv = link.querySelector("div.icon-wrapper");

    if (iconDiv) {
      // Remove a classe de todos
      iconDiv.classList.remove("area-card");
    }

    // Se for a página atual, adiciona a classe de borda
    if (linkPage === currentPage && iconDiv) {
      iconDiv.classList.add("area-card");
    }
  });
});

// Upload de imagem
const imageInput = document.getElementById('image-upload');
const imageName = document.getElementById('image-name');

imageInput.addEventListener('change', function () {
  if (imageInput.files.length > 0) {
    imageName.textContent = imageInput.files[0].name;
  } else {
    imageName.textContent = "Adicione uma imagem";
  }
});

// Upload de projeto
const projectInput = document.getElementById('file-upload');
const projectName = document.getElementById('file-name');

projectInput.addEventListener('change', function () {
  if (projectInput.files.length > 0) {
    projectName.textContent = projectInput.files[0].name;
  } else {
    projectName.textContent = "Faça o upload do seu projeto aqui";
  }
});

document.getElementById('btn-novo-post').addEventListener('click', function () {
    const navbar = document.getElementById('navbarNav');
    const isMobile = window.innerWidth < 992;

    // Se o menu está aberto (tem classe show), e for mobile
    if (isMobile && navbar.classList.contains('show')) {
      const bsCollapse = bootstrap.Collapse.getInstance(navbar) || new bootstrap.Collapse(navbar);
      bsCollapse.hide();
    }
  });

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

togglePassword.addEventListener("click", function () {
    const type = password.type === "password" ? "text" : "password";
    password.type = type;
    this.classList.toggle("bi-eye");
    this.classList.toggle("bi-eye-slash");
});

togglePassword2.addEventListener("click", function () {
    const type = confpassword.type === "password" ? "text" : "password";
    confpassword.type = type;
    this.classList.toggle("bi-eye");
    this.classList.toggle("bi-eye-slash");
});


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

button.addEventListener('click', (event) => {
    event.preventDefault();
    
    const matricula = document.getElementById('matricula');

    if (matricula.value === '') {
        matricula.classList.add("errorInput");
    }

    const nome = document.getElementById('nome');

    if (nome.value === '') {
        nome.classList.add("errorInput");
    }

    const cargo = document.getElementById('cargo');

    if (cargo.value === '') {
        cargo.classList.add("errorInput");
    }
    
    const email = document.getElementById('email');

    if (email.value === '') {
        email.classList.add("errorInput");
    }

    const password = document.getElementById('password');

    if (password.value === '') {
        password.classList.add("errorInput");
    }

    const confpassword = document.getElementById('confpassword');

    if (confpassword.value === '') {
        confpassword.classList.add("errorInput");
    }

    if (email.value.indexOf("@") === -1 || email.value.indexOf(".") === -1 || (email.value.indexOf(".") - email.value.indexOf("@") === 1)) {
        email.classList.add("errorInput");
    } else {
        email.classList.remove("errorInput");
    }
});

document.querySelectorAll('input').forEach(($input) => {
    $input.addEventListener('input', (e) => {
        e.target.classList.remove("errorInput");
    });
});
