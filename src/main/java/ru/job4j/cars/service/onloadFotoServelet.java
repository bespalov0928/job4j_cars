package ru.job4j.cars.service;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class onloadFotoServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = (String) req.getAttribute("id");
        String nameFile_ = (String)req.getAttribute("nameFile_");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/create.jsp");
        String path = req.getContextPath() + "/create.jsp?id=" + id+"&nameFile_="+nameFile_;
        resp.sendRedirect(path);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String nameFile = "";

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        String nameFile_ = "";
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("c:\\projects\\job4j_cars\\images\\");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    String raschFile = "";
                    if (item.getName().indexOf(".") > 0) {
                        raschFile = item.getName().substring(item.getName().lastIndexOf("."));
                        nameFile_ = item.getName();
                    }
                    File file = new File(folder + File.separator + nameFile_);
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(item.getFieldName() + " : " + new String(item.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        req.setAttribute("id", id);
        req.setAttribute("nameFile_", nameFile_);

        doGet(req, resp);

    }
}
