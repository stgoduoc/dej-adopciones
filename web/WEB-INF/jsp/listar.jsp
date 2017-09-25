<%-- 
    Document   : index
    Created on : Sep 24, 2017, 4:58:30 PM
    Author     : Santiago Neira <sant.neira@profesor.duoc.cl>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/jspf/head.jsp" />
        <title>Adopciones</title>
    </head>
    <body>
        <div class="container">

            <jsp:include page="/WEB-INF/jspf/menu.jsp" />

            <br /><br />

            <c:if test="${empty animales}">
                No hay animales para mostrar
            </c:if>

            <c:if test="${!empty mensaje}">
                <div class="alert alert-primary" role="alert">
                    ${mensaje}
                </div>
            </c:if>

            <c:if test="${!empty animales}">
                <table class="table table-striped">
                    <thead class="thead-inverse">
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Tipo</th>
                            <th>Género</th>
                            <th>Edad</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${animales}" var="a">
                            <tr>
                                <td>${a.id}</td>
                                <td>${a.nombre}</td>
                                <td>${a.tipo}</td>
                                <td>${a.genero}</td>
                                <td>${a.edad}</td>
                                <td>${a.adoptado?"NO disponible":"Disponible"}</td>
                                <td>
                                    <form method="get" action="borrar">
                                        <input type="hidden" name="id" value="${a.id}" />
                                        <button class="btn btn-danger">Eliminar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

        </div>
    </body>
</html>