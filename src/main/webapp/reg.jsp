<%@ page import="ru.job4j.cars.controller.User" %>
<%@ page import="ru.job4j.cars.controller.Account" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.09.2021
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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


</head>
<body>
<%
    Account acc = (Account) request.getAttribute("user");
%>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <li>Регистрация</li>
                <li>
                    <a class="nav-link" href="<%=request.getContextPath()%>/auto.do"> <c:out value="${acc.userName}"/>|Выйти</a>
                </li>
                <%--<li>--%>
                <%--<a> class="nav-link" href="<%=request.getContextPath()%>/reg.jsp">Регистрация</a>--%>
                <%--</li>--%>
            </div>
            <div class="card-body">
                <form name="contact_form" action="<%=request.getContextPath()%>/reg.do" method="post"
                      onsubmit="return validate();">
                    <div class="form-group">
                        <label>Имя</label>
                        <input type="text" class="form-control" name="name" id="name">
                    </div>
                    <div class="form-group">
                        <label>Пароль</label>
                        <input type="text" class="form-control" name="password" id="pas">
                    </div>
                    <div class="form-group">
                        <label>Почта</label>
                        <input type="text" class="form-control" name="email" id="email">
                    </div>
                    <button type="submit" class="btn btn-primary">Зарегистрировать</button>
                    <%if (acc != null) {%>
                    <li><c:out value="${acc.userName}"/>|уже зарегистрирован</a></li>
                    <%}%>

                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>