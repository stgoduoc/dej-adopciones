<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="index.jsp">Adopciones</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item${pageContext.request.servletPath=='/index.jsp'?' active ':''}">
                <a class="nav-link" href="index.jsp">Home</a>
            </li>
            <li class="nav-item${pageContext.request.servletPath=='/install.jsp'?' active ':''}">
                <a class="nav-link" href="install.jsp">Instalación</a>
            </li>
            <li class="nav-item${pageContext.request.servletPath=='/WEB-INF/jsp/listar.jsp'?' active ':''}">
                <a class="nav-link" href="listar">Listar</a>
            </li>
            <li class="nav-item${pageContext.request.servletPath=='/crear.jsp'?' active ':''}">
                <a class="nav-link" href="crear.jsp">Crear</a>
            </li>
        </ul>
    </div>
</nav>