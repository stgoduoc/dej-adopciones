<%-- 
    Document   : install.jsp
    Created on : Sep 24, 2017, 4:59:24 PM
    Author     : Santiago Neira <sant.neira@profesor.duoc.cl>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/jspf/head.jsp" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/jspf/menu.jsp" />

            <%-- llamo al servlet en caso que pase directo por el JSP --%>
            <jsp:include page="install" />

            <br /><br />
            <%-- si hay mensajes para mostrar, los muestro --%>
            <c:if test="${!empty mensaje}">
                <div class="alert alert-primary" role="alert">
                    ${mensaje}    
                </div>
            </c:if>

            <div class="card">
                <div class="card-body">
                    <form method="get" action="">
                        <button name="operacion" value="instalar" class="btn btn-primary">Instalar</button><br /><br />
                        <button name="operacion" value="desinstalar" class="btn btn-warning">Desinstalar</button><br /><br />
                        <button name="operacion" value="cargar" class="btn btn-success">Cargar Datos</button><br /><br />
                    </form>
                </div>
            </div>

        </div>
    </body>
</html>
