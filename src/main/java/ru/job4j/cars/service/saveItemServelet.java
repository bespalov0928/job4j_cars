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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class saveItemServelet extends HttpServlet {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
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

        if (id.equals("") || id.equals("0")) {
            item = new Item(descr, car, false, account, new Date(System.currentTimeMillis()));
        } else {
            item = HbmStore.instOf().findItemId(Integer.valueOf(id));
        }

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

        if (id.equals("") || id.equals("0")) {
            item = HbmStore.instOf().createItem(item);
        } else {
            item = HbmStore.instOf().updateItem(item);
        }

        String json = GSON.toJson(item.getId());
        out.println(json);
        out.flush();

    }
}
