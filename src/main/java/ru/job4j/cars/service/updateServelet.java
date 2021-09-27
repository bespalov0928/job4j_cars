package ru.job4j.cars.service;

import com.google.gson.*;
import ru.job4j.cars.controller.Foto;
import ru.job4j.cars.controller.Item;
import ru.job4j.cars.persistence.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class updateServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        resp.setContentType("text/plain, charset=UTF-8");
//        resp.setContentType("text/plain");
//        resp.setContentType("UTF-8");
        PrintWriter writer = resp.getWriter();
//        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        List<Item> items = (ArrayList<Item>) HbmStore.instOf().findAll();
        for (Item item : items) {
            String pathName = "";
            String base64 = "";
            List<Foto> listFoto = item.getFotos();
//            if (listFoto.size() != 0) {
//                Foto foto = listFoto.get(0);
//                pathName = foto.getPathName();
//                File file = new File(pathName);
//                FileInputStream stream = new FileInputStream(file);
//                byte[] arr = stream.readAllBytes();
//                base64 = DatatypeConverter.printBase64Binary(arr);
//            }
            item.imgBase64 = base64;
        }
        String json = GSON.toJson(items.toArray());
//        writer.write(json);
        writer.println(json);
//        writer.println(json.getBytes("UTF-8"));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        super.doPost(req, resp);
    }
}
