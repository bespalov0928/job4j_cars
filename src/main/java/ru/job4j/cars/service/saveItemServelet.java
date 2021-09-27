package ru.job4j.cars.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import ru.job4j.cars.controller.*;
import ru.job4j.cars.persistence.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class saveItemServelet extends HttpServlet {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");

        PrintWriter out = new PrintWriter(resp.getOutputStream());

        String jsonStr = req.getParameter("jsondata");
        JSONObject jsonObject = new JSONObject(jsonStr);

        Map<String, Object> map = jsonObject.toMap();
        String id = (String) map.get("id");
        String idBrend = (String) map.get("brend");
        String idModel = (String) map.get("model");
        String idTypeBody = (String) map.get("typeBody");
        String descr = (String) map.get("descr");
        String sale = (String) map.get("sale");
        Account account = (Account) req.getSession().getAttribute("user");

        ArrayList<String> arrNameFile = (ArrayList<String>) map.get("arrNameFile");
        for (int i = 0; i < arrNameFile.size(); i++) {
            String nameFile = arrNameFile.get(i).replace("\n", "");
            nameFile = nameFile.replace(" ", "");
            arrNameFile.set(i, nameFile);
        }


        Item item = null;
        Car car = null;

        Brend brend = HbmStore.instOf().findBrendId(Integer.valueOf(idBrend));
        Model model = HbmStore.instOf().findModelId(Integer.valueOf(idModel));
        TypeBody body = HbmStore.instOf().findBodyId(Integer.valueOf(idTypeBody));

        car = HbmStore.instOf().findCar(brend, model, body);
        if (car == null) {
            car = new Car(brend, model, body);
            HbmStore.instOf().createItem(car);
        }

        //находим объявление
        if (id.equals("") || id.equals("0")) {
            item = new Item(descr, car, false, account, new Date(System.currentTimeMillis()));
        } else {
            item = HbmStore.instOf().findItemId(Integer.valueOf(id));
        }


        //заполняем поля объявления
        List<Foto> fotos = new ArrayList<>();
        for (String nameFoto : arrNameFile) {
            if (id.equals("") || id.equals("0")) {
                item.getFotos().add(new Foto(nameFoto));
                continue;
            }

            Foto foto = HbmStore.instOf().findFotoByNameId(nameFoto, Integer.valueOf(id));
            if (foto == null) {
                item.getFotos().add(new Foto(nameFoto));
            }
        }

        if (sale.equals("true")) {
            item.setSold(true);
        } else {
            item.setSold(false);
        }

        //записываем объявление
        if (id.equals("") || id.equals("0")) {
            item = HbmStore.instOf().createItem(item);
        } else {
            item = HbmStore.instOf().updateItem(item);
        }

        String json = GSON.toJson(item.getId());
        out.println(json);
        out.flush();

//        StringBuilder sb = new StringBuilder();
//        BufferedReader br = req.getReader();
//        String str;
//        while( (str = br.readLine()) != null ){
//            sb.append(str);
//        }
//        JSONObject jObj = new JSONObject(sb.toString());


//        PrintWriter out = new PrintWriter(resp.getOutputStream());
//
//        req.setCharacterEncoding("UTF-8");
//        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
////        GSON.fromJson(req.getReader(), Str);
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(), "utf-8"));
////        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
//        String in = br.readLine();
//        JSONObject jsonObject = new JSONObject(in);
//        Map<String, Object> map = jsonObject.toMap();
//        String id = (String) map.get("id");
//        String idBrend = (String) map.get("brend");
//        String idModel = (String) map.get("model");
//        String idTypeBody = (String) map.get("typeBody");
//        String descr = (String) map.get("descr");
//        Account account = (Account) req.getSession().getAttribute("user");
//
//        ArrayList<String> arrNameFile = (ArrayList<String>) map.get("arrNameFile");
//        for (int i = 0; i < arrNameFile.size(); i++) {
//            String nameFile = arrNameFile.get(i).replace("\n", "");
//            nameFile = nameFile.replace(" ", "");
//            arrNameFile.set(i, nameFile);
//        }
//
//        Item item = null;
//        Car car = null;
//
//        Brend brend = HbmStore.instOf().findBrendId(Integer.valueOf(idBrend));
//        Model model = HbmStore.instOf().findModelId(Integer.valueOf(idModel));
//        TypeBody body = HbmStore.instOf().findBodyId(Integer.valueOf(idTypeBody));
//
//        car = HbmStore.instOf().findCar(brend, model, body);
//        if (car == null){
//            car = new Car(brend, model, body);
//            HbmStore.instOf().createItem(car);
//        }
//
//        //находим объявление
//        if (id == ""){
//            item = new Item(descr, car, false, account, new Date(System.currentTimeMillis()));
//        }else {
//            item = HbmStore.instOf().findItemId(Integer.valueOf(id));
//        }
//
//        //заполняем поля объявления
//        List<Foto> fotos = new ArrayList<>();
//        for (String nameFoto: arrNameFile) {
//            Foto foto = HbmStore.instOf().findFotoByNameId(nameFoto, Integer.valueOf(id));
//            if (foto == null){
//                item.getFotos().add(new Foto(nameFoto));
//            }
//        }
//
//        //записываем объявление
//        if (id == ""){
//            HbmStore.instOf().createItem(item);
//        }else {
////            HbmStore.instOf().updateItem(item, Integer.valueOf(id));
//            HbmStore.instOf().updateItem(item);
//        }
//
//
////        if (item == null) {
////        Car car = new Car(brend, model, body);
////        Car carRsl = (Car) HbmStore.instOf().createItem(car);
////        }
//
//        //String id = (String) map.get("id");
//        //String idCar = (String) map.get("idCar");
//
////        Brend brend = HbmStore.instOf().findBrendId(Integer.valueOf(idBrend));
////        Model model = HbmStore.instOf().findModelId(Integer.valueOf(idModel));
////        TypeBody body = HbmStore.instOf().findBodyId(Integer.valueOf(idTypeBody));
////        Car car = new Car(brend, model, body);
////        Car carRsl = (Car) HbmStore.instOf().createItem(car);
////
////        Account account = (Account) req.getSession().getAttribute("user");
////
////        Item item = new Item(descr, carRsl, false, account, new Date(System.currentTimeMillis()));
////        Item rsl = null;
////        try {
////            rsl = HbmStore.instOf().createItem(item);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        String json = GSON.toJson(rsl.getId());
////        out.println(json);
////        out.flush();
//        //super.doPost(req, resp);
    }
}
