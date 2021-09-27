package ru.job4j.cars.service;

import org.json.HTTP;
import ru.job4j.cars.controller.Account;
import ru.job4j.cars.controller.User;
import ru.job4j.cars.persistence.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class authorizationServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Account acc = HbmStore.instOf().findAccountByEmail(email);
        if (acc == null){
            resp.sendRedirect("reg.jsp");
            return;
        }
        if (acc.getEmail().equals(email) && acc.getPassword().equals(password)) {
//        if ("root@local".equals(email) && "root".equals(password)) {
            HttpSession sc = req.getSession();
//            Account admin = new Account();
//            admin.setName("Admin");
//            admin.setEmail(email);
            sc.setAttribute("user", acc);
            resp.sendRedirect(req.getContextPath() + "/posts.do");
        } else {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
