package ru.job4j.cars.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.postgresql.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class downloadFotoServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
//        name = "image1.png";
        File downloadFile = null;
        for (File file : new File("c:\\projects\\job4j_cars\\images\\").listFiles()) {
//            if (name.equals(file.getName())) {
            String[] subStr = file.getName().split("\\.");
            if (name.equals(file.getName())) {
                downloadFile = file;
                break;
            }
        }

        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFile.getName() + "\"");
        try (FileInputStream stream = new FileInputStream(downloadFile)){
            resp.getOutputStream().write(stream.readAllBytes());
        }


//        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
//        PrintWriter writer = resp.getWriter();
//
//        List<File> files = Arrays.asList(new File("c:\\projects\\job4j_cars\\images\\").listFiles());
//        File downloadFile = files.get(0);
//
//        try (FileInputStream stream = new FileInputStream(downloadFile)){
//
//            byte[] arr = stream.readAllBytes();
//            String Base64 = DatatypeConverter.printBase64Binary(arr);
//
//            String json = GSON.toJson(Base64);
//            writer.println(json);
//            writer.flush();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
