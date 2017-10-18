<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Crear animal</title>
        <jsp:include page="/WEB-INF/jspf/head.jsp" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/jspf/menu.jsp" />
            <h1>Crear animal</h1>
            <c:if test="${!empty mensaje}">
                <div class="alert alert-primary">
                    ${mensaje}
                </div>
            </c:if>
            <form action="CrearServlet" method="POST">
                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" class="form-control" id="nombre" name="nombre"
                           placeholder="Nombre del animal">
                </div>
                <div class="form-group">
                    <label for="tipo">Tipo</label>
                    <select class="form-control" id="tipo" name="tipo">
                        <option>PERRO</option>
                        <option>GATO</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="genero">Género</label>
                    <select class="form-control" id="genero" name="genero">
                        <option>MACHO</option>
                        <option>HEMBRA</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="fechanacimiento">Fecha de Nacimiento</label>
                    <input type="text" class="form-control" id="fechanacimiento" name="fechanacimiento"
                           placeholder="yyyy-mm-dd">
                </div>                
                <button type="submit" class="btn btn-primary">Guardar</button>
            </form>
        </div>

        <script type="text/javascript">
            jQuery("#fechanacimiento").datepicker({
                changeMonth: true
                , changeYear: true
                , dateFormat: 'yy-mm-dd'
                , minDate: new Date(1990, 1, 1)
                , maxDate: new Date()
            });
        </script>
    </body>
</html>
