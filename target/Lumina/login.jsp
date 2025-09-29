<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lumina</title>

    <!--  Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=League+Spartan:wght@100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

     <!-- Minha CSS -->
     <link rel="stylesheet" href="./css/login.css">

     <!-- Chamada da CSS do BS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

    <!-- BS Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>
    <main class="container-fluid">
        <div class="container-login d-flex justify-content-center align-items-center">
            <div class="ladoBranco d-flex justify-content-center align-items-center">
                <div class="conteiner-ladoBranco d-flex justify-content-center align-items-center flex-column">
                    <figure>
                        <img class="logo" src="./assets/logo.svg">
                    </figure>
                    <div class="subtitle">
                        <p>Onde a ciência encontra a inovação</p>
                    </div>
                    
                     <%-- MENSAGEM DE ERRO --%>
                    <% if (request.getAttribute("erro") != null) { %>
                        <div class="alert alert-danger text-center w-100" role="alert">
                            <%= request.getAttribute("erro") %>
                        </div>
                    <% } %>
                    
                    <form action="Login" method="post" class="d-flex flex-column justify-content-center align-items-center mt-4" id="loginForm">
                        <div class="mb-5 mt-3">
                            <input type="hidden" id="tipoUsuario" name="tipoUsuario" value="">
                            <input type="email" name="email" value = "<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" class="form-control" id="email" placeholder="E-MAIL CORPORATIVO" autocomplete="off" aria-describedby="emailHelp">
                        </div>
                        <div class="mb-5 mt-3">
                            <div class="inputsenha d-flex justify-content-end align-items-center">
                                <input type="password" name="password" class="form-control" placeholder="SENHA" id="password">
                                <i class="bi bi-eye me-3" id="togglePass"></i>
                            </div>
                        </div>
                        <div class="mb-5 d-flex justify-content-center align-items-center">
                            <button type="submit" id="entrar" class="btn pt-2 pb-2 ps-5 pe-5 fw-medium text-light">ENTRAR</button>
                        </div>
                    </form>
                </div>
            </div>
            <figure class="d-flex justify-content-center align-items-center">
                <img class="login position-absolute figure-img img-fluid" src="./assets/tela-login.svg">
            </figure>
            <div class="ladoGradient"></div>
        </div>
    </main>
    <!-- Chamada JS do BS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="./js/script.js"></script>
</body>
</html>
