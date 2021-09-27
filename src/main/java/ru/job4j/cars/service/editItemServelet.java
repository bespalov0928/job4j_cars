package ru.job4j.cars.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class editItemServelet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //прочитать параметры Json

        //добавляем параметр id в запрос

        //делаем перенаправление запроса, forward

        //в create.jsp устанавливаем option значение selected

        String idString = req.getParameter("id");
        int id = Integer.valueOf(idString);
//        String path = String.format("/pages/create.jsp?id=%s", id);
        String path = "/pages/create.jsp";
        req.getRequestDispatcher(path).forward(req, resp);
        //resp.sendRedirect(req.getContextPath() + "/index.do");
        //super.doPost(req, resp);
    }

}
