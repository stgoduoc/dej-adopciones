<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/jspf/head.jsp" />
        <title>Crear animal</title>
    </head>
    <body>
        <div class="container">

            <jsp:include page="/WEB-INF/jspf/menu.jsp" />
            <br />
            
            <!-- errores -->
            <c:if test="${!empty errores}">
                <div class="alert alert-danger" role="alert">
                    <ul>
                        <c:forEach items="${errores}" var="e">
                            <li>${e}</li>
                            </c:forEach>
                    </ul>
                </div>
            </c:if>

            <!-- mensajes -->
            <c:if test="${!empty mensajes}">
                <div class="alert alert-info" role="alert">
                    <ul>
                        <c:forEach items="${mensajes}" var="m">
                            <li>${m}</li>
                            </c:forEach>
                    </ul>
                </div>
            </c:if>
            
            <div class="row">
                <div class="col col-lg-6">
                    <h1>Cree un Animal en la BD</h1>
                    <form method="post" action="GuardarServlet">
                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese el nombre del animal en adopción" aria-describedby="nombre-help">
                            <small id="nombre-help" class="form-text text-muted">Ejemplo: Rocky</small>
                        </div>
                        <div class="form-group">
                            <label for="tipo">Tipo</label>
                            <select class="form-control" id="tipo" name="tipo">
                                <option>perro</option>
                                <option>gato</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="genero">Género</label>
                            <select class="form-control" id="genero" name="genero">
                                <option>macho</option>
                                <option>hembra</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="fecha-nacimiento">Fecha de Nacimiento</label>
                            <input type="text" class="form-control" id="fecha-nacimiento" name="fecha-nacimiento" placeholder="" aria-describedby="fecha-nacimiento-help">
                            <small id="fecha-nacimiento-help" class="form-text text-muted">Fecha en formato yyyy-mm-dd Ejemplo: 2014-12-25</small>
                        </div>

                        <button type="submit" class="btn btn-primary">Guardar</button>
                    </form>
                </div>
            </div><!-- end col-->
        </div><!-- end row-->

    </div>
    
    <script type="text/javascript">
        $( "#fecha-nacimiento" ).datepicker({
            changeMonth: true
            , changeYear: true
            , dateFormat: 'yy-mm-dd'
            , minDate: new Date(1990, 1, 1)
            , maxDate: new Date()
        });
    </script>
</body>
</html>