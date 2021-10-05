<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@ page import="ru.job4j.cars.persistence.HbmStore" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="ru.job4j.cars.controller.*" %>
<%@ page import="ru.job4j.cars.persistence.PsqlStore" %>

<html>
<head>
    <title>photoUpload</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Новое объявление</title>
</head>

<body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script>
    function validate() {
        valid = true;
        return valid;
    }

    function saveItem() {
        alert("saveItem")
        console.log(document.getElementById("id").value);
        id = document.getElementById("id").value;
        brend = document.getElementById("brend").value;
        model = document.getElementById("model").value;
        typeBody = document.getElementById("typeBody").value;
        descr = document.getElementById("descr").value;
        sale = document.getElementById("sale").value;
        var table = document.getElementById("table");
        var len = document.getElementById("table").rows.length;
        var arrNameFile = [3];
        for (var i = 0; i < len; i++) {
            arrNameFile[i] = table.rows[i].cells[1].innerHTML;
        }

        var str = {
            "id": id,
            "brend": brend,
            "model": model,
            "typeBody": typeBody,
            "descr": descr,
            "arrNameFile": arrNameFile,
            "sale": sale
        };
        var jsonStr = JSON.stringify(str);
        console.log(jsonStr);

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/cars/saveItem.do',
            data: {jsondata: JSON.stringify(str)},
            dataType: 'json'
        }).done(function (date) {
            alert(date)
            var inputId = document.getElementById("id");
            inputId.setAttribute('value', date);

        }).fail(function (err) {
            alert(err)
        })
    }


</script>

<%
    String id = request.getParameter("id");
    String nameFile_ = request.getParameter("nameFile_");
    System.out.println("id: " + id);
    Item item = new Item();
    List<Brend> brends = null;
    List<Model> models = null;
    List<TypeBody> typeBodys = null;
    List<Foto> fotos = null;
    if (id != null && !id.equals("null") && id != "") {
        System.out.println("id != null");
        item = HbmStore.instOf().findItemId(Integer.valueOf(id));
//        устанавливаем option значение selected для brend, model, typeBody
        brends = Arrays.asList(new Brend[]{item.getCar().getBrend()});
        models = Arrays.asList(new Model[]{item.getCar().getModel()});
        typeBodys = Arrays.asList(new TypeBody[]{item.getCar().getTypeBody()});
        fotos = HbmStore.instOf().findFotosId(Integer.valueOf(id));
    } else {
        System.out.println("id == null");
        brends = (List<Brend>) HbmStore.instOf().findAllBrend();
        models = (List<Model>) HbmStore.instOf().findAllModel();
        typeBodys = (List<TypeBody>) HbmStore.instOf().findAllBody();
    }

    request.setAttribute("item", item);
    request.setAttribute("brends", brends);
    request.setAttribute("models", models);
    request.setAttribute("typeBodys", typeBodys);
    request.setAttribute("fotos", fotos);
    request.setAttribute("nameFile_", nameFile_);
%>

<a class="nav-link" href="index.jsp">На главную</a>

<div class="container">

    <div class="row pt-3">
        <h3 id='heder'>
            <%if (id == null) {%>
            <li>Новое объявление</li>
            <%} else {%>
            <li>Редактирование объявления</li>
            <%}%>
        </h3>
    </div>

    <div class="row">
        <form name="contact_form" action="<%=request.getContextPath()%>/onloadFoto.do?id=<%=id%>" method="post"
              enctype="multipart/form-data" onsubmit="return validate();">

            <div class="form-group">
                <label for="brend">Марка</label>
                <select class="form-control" name="brend" id="brend">
                    <c:forEach items="${brends}" var="brend">
                        <option value=${brend.id}>${brend.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="model">Модель</label>
                <select class="form-control" name="model" id="model">
                    <c:forEach items="${models}" var="model">
                        <option value=${model.id}>${model.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="typeBody">Тип кузова</label>
                <select class="form-control" name="typeBody" id="typeBody">
                    <c:forEach items="${typeBodys}" var="typeBody">
                        <option value=${typeBody.id}>${typeBody.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="descr">Описание</label>
                <input type="text" class="form-control" name="descr" id="descr" placeholder="Описание"
                       value=<%=item.getDescription()%>
                               </div>
            </div>

            <div class="form-group">
                <label for="sale">Продано</label>
                <select class="form-control" name="sale" id="sale">
                    <c:if test="${item.sold == true}">
                        <option value=true>да</option>
                        <option value=false>нет</option>
                    </c:if>
                    <c:if test="${!item.sold}">
                        <option value=false>нет</option>
                        <option value=true>да</option>
                    </c:if>
                </select>
            </div>

            <div class="form-group" hidden = "true">
                <label for="id">id</label>
                <input type="text" class="form-control" name="id" id="id" placeholder="id" value=<%=item.getId()%>>
            </div>

            <div class="form-group">
                <button type="button" class="btn btn-success" onclick="saveItem()">Сохранить объявление</button>
                <br>
                <br>
                <br>
                <br>
            </div>

            <div class="form-group">
                <input type="file" name="file1">
                <br/>
                <button type="submit" class="btn btn-success">Загрузить фото</button>
            </div>

        </form>


    </div>

    <div class="card-body">
        <table class="table" id="table">
            <tbody id="tbody">
            <c:forEach items="${fotos}" var="post">
                <tr>
                    <td>
                        <img src="<c:url value='/downloadFoto.do?name=${post.pathName}'/>" width="100px"
                             height="100px"/>
                    </td>
                    <td hidden="true">
                        <c:out value="${post.pathName}"></c:out>
                    </td>
                </tr>
            </c:forEach>
            <%if (nameFile_ != null) {%>
            <tr>
                <td>
                    <img src="<c:url value='/downloadFoto.do?name=${nameFile_}'/>" width="100px"
                         height="100px"/>
                </td>
                <td hidden="true">
                    <c:out value="${nameFile_}"/>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>

</div>

</body>
</html>
