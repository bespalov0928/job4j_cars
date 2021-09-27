package ru.job4j.cars.service;


import ru.job4j.cars.controller.Account;
import ru.job4j.cars.controller.User;
import ru.job4j.cars.persistence.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class regServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        HttpSession hs = req.getSession();
        Account accStart = new Account(name, email, "", password);

        Account acc = HbmStore.instOf().findAccountByEmail(email);
        if (acc != null){
            hs.setAttribute("user", acc);

            resp.sendRedirect(req.getContextPath() + "/reg.do");
            return;
        }
        acc = HbmStore.instOf().createItem(accStart);
        hs.setAttribute("user", acc);
        resp.sendRedirect("index.jsp");
    }
}
