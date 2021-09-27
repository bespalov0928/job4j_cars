<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--&lt;%&ndash;<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>&ndash;%&gt;--%>
<%--<html>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>--%>
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
    <%--<script src="js/indexOld.js"></script>--%>
</head>

<%--<body>--%>
<body onload="updateDate()">

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
    function update() {
        updateDate();
    }

    function updateDate() {
        // alert("updateDate");

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
            // var row = 3;

            var tbody = document.getElementById('tbody');
            for (r = 0; r < row; r++) {
                console.log(r);
                var obj = data[r];
                console.log(obj);

                var tr = document.createElement('tr');
                tr.setAttribute("id", r + '/tr');

                //номер строки
                // var td = document.createElement('td');
                // td.setAttribute('id', r + '/nomer');
                // td.innerHTML = r + 1;
                // tr.appendChild(td);


                var td = document.createElement('td');
                var a = document.createElement('a');
                var i = document.createElement('i');
                i.setAttribute('class', "fa fa-edit mr-3");
                <%--var desiredLink = "<%=request.getContextPath()%>/create.do?id="+1+"";--%>

                a.setAttribute('href', "<%=request.getContextPath()%>/create.do?id=" + obj.id + "");

                // <i class="fa fa-edit mr-3"></i>
                // a.innerHTML = desiredLink;
                a.appendChild(i);
                td.appendChild(a);
                tr.appendChild(td);
                <%--<a href="<%=request.getContextPath()%>/create.do?id=1">--%>
                <%--<i class="fa fa-edit mr-3"></i>--%>
                <%--</a>--%>
                <%--</td>--%>


                //дата
                var td = document.createElement('td');
                td.setAttribute('id', r + '/dateItem');
                // new Date('December 17, 1995 03:24:00');
                var dateItem = new Date(obj.dateItem);
                var dateItem1 = dateItem.toLocaleDateString();
                // var dateItemf = dateItem.format("dd.mm.yyyy");
                // console.log(obj.dateItem);
                // console.log(dateItem);
                // console.log(dateItem1)
                // console.log(dateItemf);
                // td.innerHTML = obj.dateItem;
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
                // td.innerHTML = obj.sold;
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
                // td.setAttribute('id', r + '/carFoto');
                td.setAttribute('id', 'carFoto');
                var img = document.createElement('img');
                // img.src = 'c:\\projects\\job4j_cars\\images\\image1.png';
                // console.log(obj.imgBase64);
                // img.setAttribute("value", obj.imgBase64);
                // img.setAttribute("src", 'data:image/png;base64,' + obj.imgBase64);
                <%--img.setAttribute("src", "<c:url value='/updateFoto.do?id=" + obj.id + "'/>");--%>
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
            //alert(err);
        });

    }

    function createItem() {
        // window.location = window.location.href+'pages/create.html';
        // window.location = window.location.href + 'create.jsp';
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/cars/create.do',
            dataType: 'json'
        }).done(function (data) {
            // alert(data)
        }).fail(function (err) {
            alert("err")
        })
    }

    function editItem() {
        // alert("editItem");
        console.log(document.getElementById("id").value);
        var id = document.getElementById("id").value;
        window.location = window.location.href + 'pages/create.jsp?id=' + id;

        // $.ajax({
        //     type: 'POST',
        //     url: 'http://localhost:8080/cars/forwardCreate.do',
        //     data:JSON.stringify({
        //        id:document.getElementById("1").value
        //     }),
        //     dataType: 'json'
        // }).done(function (data) {
        //     alert(data)
        // }).fail(function (err) {
        //     alert(err)
        // })
    }
</script>

<div class="container">
    <a class="nav-link" href="login.jsp">Войти</a>
    <a class="nav-link" href="<%=request.getContextPath()%>/logout.do"> <c:out value="${user.userName}"/>|Выйти</a>
    <%--<li>--%>
    <%--<a class="nav-link" href="login.jsp">Войти</a>--%>
    <%--</li>--%>
    <%--<li>--%>
    <%--<a class="nav-link" href="<%=request.getContextPath()%>/auto.do"> <c:out value="${user.name}"/>|Выйти</a>--%>
    <%--</li>--%>

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
                <!--<th style="display:none;">ИД</th>-->
                <th>ИД</th>
            </tr>

            </thead>
            <tbody id="tbody">
            <tr>
                <td>
                    <!--<a href="/cars/pages/create.jsp;" onclick="editItem();">-->
                    <a href="<%=request.getContextPath()%>/create.do?id=1">
                        <%--<a href="/cars/create.jsp?id=1">--%>
                        <!--<a href="/cars/pages/create.jsp?id=1">-->
                        <i class="fa fa-edit mr-3"></i>
                    </a>

                </td>
                <td>2021.08.15</td>
                <td>да</td>
                <td>ГАЗ</td>
                <td>3102</td>
                <td id='carFoto'>
                    <%--<img src="<c:url value='/updateFoto.do?id=1'/>" width="100px" height="100px"/>--%>
                    <img src="updateFoto.do?id=1" height="120" width="120" alt="foto">
                    <!--<img-->
                    <!--src = data:image/png;base64,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>-->
                    <!--</img>-->
                </td>
                <td>Тачка огонь</td>
                <td>Рассказчиков</td>
                <!--<td style="display:none;">ИД</td>-->
                <td id="1">ИД</td>
            </tr>

            <%--<c:forEach items="${fotos}" var="post">--%>
            <%--<tr>--%>
            <%--<td>--%>
            <%--<img src="<c:url value='/downloadFoto.do?name=${post.pathName}'/>" width="100px"--%>
            <%--height="100px"/>--%>
            <%--</td>--%>
            <%--<td hidden="true">--%>
            <%--<c:out value="${post.pathName}"></c:out>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--</c:forEach>--%>

            </tbody>
        </table>
    </div>
    <form name="createItem" action="<%=request.getContextPath()%>/create.do" method="get" \>
        <button type="submit" class="btn btn-success" onclick="createItem()">Добавить объявление</button>
    </form>
    <%--<div class="row float-none">--%>
    <%--&lt;%&ndash;<button type="button" class="btn btn-success" onclick="create.do">Добавить объявление</button>&ndash;%&gt;--%>
    <%--<button type="button" class="btn btn-success" onclick="createItem()">Добавить объявление</button>--%>
    <%--<!--<h4>window.location.href</h4>-->--%>
    <%--<!--<button type="button" onclick="window.location.href+'pages/create.html'">Добавить объявление</button>-->--%>
    <%--</div>--%>
    <br><br>
    <div class="row float-none">
        <button type="button" class="btn btn-success" onclick="updateDate();">Обновить</button>
    </div>
</div>

<a class="nav-link" href="<%=request.getContextPath()%>/test.jsp">Test</a>

</body>
</html>
