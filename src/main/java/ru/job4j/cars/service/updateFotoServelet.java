package ru.job4j.cars.service;

import ru.job4j.cars.controller.Foto;
import ru.job4j.cars.controller.Item;
import ru.job4j.cars.persistence.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class updateFotoServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
//        String id = "1";
        Item item = HbmStore.instOf().findItemId(Integer.valueOf(id));
        File downloadFile = null;
        List<Foto> listFoto = item.getFotos();
        String nameFile = "";

        if (item != null){

            listFoto = item.getFotos();
        }
        if (listFoto.size() != 0) {
            nameFile = listFoto.get(0).getPathName();
        }

        for (File file : new File("c:\\projects\\job4j_cars\\images\\").listFiles()) {
            String[] subStr = file.getName().split("\\.");
            if (nameFile.equals(file.getName())) {
                downloadFile = file;
                break;
            }
        }

        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFile.getName() + "\"");
        try (FileInputStream stream = new FileInputStream(downloadFile)){
            resp.getOutputStream().write(stream.readAllBytes());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
