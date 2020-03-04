<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link rel="icon" href="${pageContext.request.contextPath}/resources/assets/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/index.css">

    <script src="${pageContext.request.contextPath}/resources/js/report/edit.js"></script>

    <title>Doom Map</title>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Doom Map</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">
                    Ocorrências
                    <span class="sr-only">(current)</span>
                </a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Pesquisar</button>
        </form>
    </div>
</nav>
<div class="container">
    <div class="mt-4">
        <a href="/report" class="btn btn-outline-primary my-2 my-sm-0">Voltar</a>
    </div>

    <form class="mt-4" id="form-new-report" onsubmit="submitEditReport(event)">
        <div class="form-group">
            <label for="select-crimes">Crime</label>
            <select class="form-control" id="select-crimes">
                <option selected disabled value="undefined">Selecione um crime</option>
                <option value="1">157 - Roubo seguido de morte</option>
                <option value="2">213 - Estupro</option>
                <option value="3">121 - Assassinato</option>
            </select>
        </div>

        <div class="form-group">
            <label for="input-datetime-local-time-of-event">Hora</label>
            <input
                    class="form-control"
                    type="datetime-local"
                    value="2011-08-19T13:45:00"
                    id="input-datetime-local-time-of-event"
            />
        </div>

        <div class="form-group">
            <label for="input-text-cep">CEP</label>
            <input class="form-control" placeholder="Informe o CEP" type="text" id="input-text-cep">
        </div>

        <div class="mt-4">
            <button type="button" onclick="prependCrime()" class="btn btn-outline-primary">
                Adicionar
            </button>
        </div>

        <div id="crime-cards">
            <c:forEach items="${report.getReportCrimes()}" var="reportCrime" varStatus="loop">
                <script>
                    overrideCardIndex(<c:out value="${loop.index}" />);
                    prependCrimeWithoutHtml(
                        '<c:out value="${reportCrime.getCrime().getId()}" />',
                        '<c:out value="${reportCrime.getTimeOfEvent()}" />',
                        '<c:out value="${reportCrime.getAddress().getCep()}" />'
                    );
                </script>
                <div class="card mt-4" id="crime-card-number-<c:out value="${loop.index}" />">
                    <h5 class="card-header">
                        Crime
                        <button
                            type="button"
                            class="close"
                            aria-label="Fechar"
                            onclick="removeCard(<c:out value="${loop.index}" />)"
                        >
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </h5>
                    <div class="card-body">
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Crime</label>
                            <div class="col-sm-10">
                                <p class="form-control-static">
                                    <c:out value="${reportCrime.getCrime().getCode()}" /> - <c:out value="${reportCrime.getCrime().getName()}" />
                                </p>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Hora</label>
                            <div class="col-sm-10">
                                <p class="form-control-static">
                                    <c:out value="${reportCrime.getTimeOfEvent()}" />
                                </p>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">CEP</label>
                            <div class="col-sm-10">
                                <p class="form-control-static">
                                    <c:out value="${reportCrime.getAddress().getCep()}" />
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="mt-4">
            <button
                    type="submit"
                    class="btn btn-outline-primary"
                    id="button-create-report"
                    disabled
            >
                Atualizar
            </button>
        </div>
    </form>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

<!-- Ajax -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</body>

</html>
