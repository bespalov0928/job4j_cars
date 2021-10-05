<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

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

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <title>Объявления</title>
</head>

<body onload="updateDate()">

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
    function update() {
        updateDate();
    }

    function updateDate() {

        var elmtTable = document.getElementById('tbody');
        var tableRows = elmtTable.getElementsByTagName('tr');
        var rowCount = tableRows.length;
        for (var x=rowCount-1; x>0; x--) {
            elmtTable.removeChild(tableRows[x]);
        }

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/cars/update.do',
            dataType: 'json'
        }).done(function (data) {
            // alert("data");
            console.log(data)

            var row = data.length;

            var tbody = document.getElementById('tbody');
            for (r = 0; r < row; r++) {
                console.log(r);
                var obj = data[r];
                console.log(obj);

                var tr = document.createElement('tr');
                tr.setAttribute("id", r + '/tr');

                var td = document.createElement('td');
                var a = document.createElement('a');
                var i = document.createElement('i');
                i.setAttribute('class', "fa fa-edit mr-3");
                a.setAttribute('href', "<%=request.getContextPath()%>/create.do?id=" + obj.id + "");
                a.appendChild(i);
                td.appendChild(a);
                tr.appendChild(td);

                //дата
                var td = document.createElement('td');
                td.setAttribute('id', r + '/dateItem');
                var dateItem = new Date(obj.dateItem);
                var dateItem1 = dateItem.toLocaleDateString();
                td.innerHTML = dateItem1;
                tr.appendChild(td);

                //Продано
                var td = document.createElement('td');
                td.setAttribute('id', r + '/sold');
                if (obj.sold) {
                    td.innerHTML = "да"
                }else {
                    td.innerHTML = "нет"
                }
                tr.appendChild(td);

                //марка
                var td = document.createElement('td');
                td.setAttribute('id', r + '/carName');
                td.innerHTML = obj.car.name;
                tr.appendChild(td);

                //модель
                var td = document.createElement('td');
                td.setAttribute('id', r + '/carModel');
                td.innerHTML = obj.car.model.name;
                tr.appendChild(td);

                //фото
                var td = document.createElement('td');
                td.setAttribute('id', 'carFoto');
                var img = document.createElement('img');
                img.setAttribute("src", 'updateFoto.do?id=' + obj.id);
                img.setAttribute("height", "120");
                img.setAttribute("width", "120");
                img.setAttribute("alt", "foto");
                td.appendChild(img);
                tr.appendChild(td);

                //описание
                var td = document.createElement('td');
                td.setAttribute('id', r + '/carDesc');
                td.innerHTML = obj.description;
                tr.appendChild(td);

                //автор
                var td = document.createElement('td');
                td.setAttribute('id', r + '/author');
                td.innerHTML = obj.account.userName;
                tr.appendChild(td);

                //ИД
                var td = document.createElement('td');
                td.setAttribute('id', obj.id);
                td.innerHTML = obj.id;
                tr.appendChild(td);

                tbody.appendChild(tr);
            }

        }).fail(function (err) {
            alert("fail");
            console.log(err);
        });

    }

    function createItem() {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/cars/create.do',
            dataType: 'json'
        }).done(function (data) {
        }).fail(function (err) {
            alert("err")
        })
    }

    function editItem() {
        console.log(document.getElementById("id").value);
        var id = document.getElementById("id").value;
        window.location = window.location.href + 'pages/create.jsp?id=' + id;
    }
</script>

<div class="container">
    <a class="nav-link" href="login.jsp">Войти</a>
    <a class="nav-link" href="<%=request.getContextPath()%>/logout.do"> <c:out value="${user.userName}"/>|Выйти</a>

    <div class="row pt-3">
        <h4>
            Объявления о продаже
        </h4>
        <table class="table table-bordered" id="table">
            <thead id="thead">
            <tr>
                <th style="width: 20px;"></th>
                <th style="width: 40px;">Дата</th>
                <th style="width: 20px;">Продано</th>
                <th>Марка</th>
                <th>Модель</th>
                <th>Фото</th>
                <th>Описание</th>
                <th>Автор</th>
                <th>ИД</th>
            </tr>

            </thead>
            <tbody id="tbody">
            </tbody>
        </table>
    </div>
    <form name="createItem" action="<%=request.getContextPath()%>/create.do" method="get" \>
        <button type="submit" class="btn btn-success" onclick="createItem()">Добавить объявление</button>
    </form>
</div>

</body>
</html>
