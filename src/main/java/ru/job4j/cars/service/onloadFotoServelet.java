package ru.job4j.cars.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;
import ru.job4j.cars.controller.*;
import ru.job4j.cars.persistence.HbmStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class onloadFotoServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<String> images = new ArrayList<>();
//        String image = (String) req.getAttribute("image");
//        for (File name : new File("c:\\projects\\job4j_cars\\images\\").listFiles()) {
//            images.add("c:\\projects\\job4j_cars\\images\\" + name.getName());
//        }
//        req.setAttribute("images", images);
//        req.setAttribute("image", "c:\\projects\\job4j_cars\\images\\" + image);
//        req.setAttribute("id", req.getAttribute("id"));
        String id = (String) req.getAttribute("id");
        String nameFile_ = (String)req.getAttribute("nameFile_");
//        req.setAttribute("nameFile_", nameFile_);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/create.html");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/create.jsp");
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
//        dispatcher.forward(req, resp);
        String path = req.getContextPath() + "/create.jsp?id=" + id+"&nameFile_="+nameFile_;
        resp.sendRedirect(path);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
//        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
//        String in = br.readLine();
//        JSONObject jsonObject = new JSONObject(in);
//        Map<String, Object> map = jsonObject.toMap();
//        int id = (int) map.get("id");
//        String Brend = req.getParameter("brend");
//        String Model = req.getParameter("model");
//        String TypeBody = req.getParameter("typeBody");
//        String descr = req.getParameter("descr");
//        String id = req.getParameter("id");
        String id = req.getParameter("id");
//        String id = (String) req.getAttribute("id");

        String nameFile = "";

//        int idBrend = Brend == null ? 0 : Integer.valueOf(Brend);
//        int idModel = Model == null ? 0 : Integer.valueOf(Model);
//        int idTypeBody = TypeBody == null ? 0 : Integer.valueOf(TypeBody);

//        Account account = (Account) req.getSession().getAttribute("user");

//        Item item = null;

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
//        String id = req.getParameter("id");
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
//                    File file = new File(folder + File.separator + item.getName());
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
//                        req.setAttribute("image", item.getName());
//                        nameFile = item.getName();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
//                    byte[] x = item.getInputStream().readAllBytes();
//                    if (item.getFieldName().equals("id")) {
//                        id = new String(item.getInputStream().readAllBytes());
//                    }
                    System.out.println(item.getFieldName() + " : " + new String(item.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

//        Foto foto = HbmStore.instOf().findFotoByName(nameFile_);
//        if (foto == null){
//            Foto fotoNew = new Foto(nameFile_);
//            HbmStore.instOf().createItem(fotoNew);
//        }




        //Поиск объявления
//        if (id != null) {
//            item = HbmStore.instOf().findItemId(Integer.valueOf(id));
//        }


        //если не нашли , то сохраняем
//        if (item == null) {
//            item = (Item) saveItem(idBrend, idModel, idTypeBody, descr, account);
//        }

        //загружаем файл
//        String path = onloadFoto(req);

        //сохраняем путь к файлу в таблицу
//        Foto foto = saveFoto(path);
//        Foto fotoNew = new Foto("c:\\projects\\job4j_cars\\images\\" + path);
//        Foto foto = HbmStore.instOf().createItem(fotoNew);

        //устанавливаем соответствие между файлом и объявлением
//        ArrayList<Foto> fotos = new ArrayList<>();
//        fotos.add(foto);
//        item.setFotos(fotos);
//        HbmStore.instOf().createItem(item);

        req.setAttribute("id", id);
        req.setAttribute("nameFile_", nameFile_);

        doGet(req, resp);

    }

    private Item saveItem(int idBrend, int idModel, int idTypeBody, String descr, Account account) throws IOException {

//        PrintWriter out = new PrintWriter(resp.getOutputStream());

//        req.setCharacterEncoding("UTF-8");
//        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
//        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
//        String in = br.readLine();
//        JSONObject jsonObject = new JSONObject(in);
//        Map<String, Object> map = jsonObject.toMap();
//        int idBrend = (int) map.get("brend");
//        int idModel = (int) map.get("model");
//        int idTypeBody = (int) map.get("typeBody");
//        String descr = (String) map.get("descr");
        //String id = (String) map.get("id");
        //String idCar = (String) map.get("idCar");

//        String idBrend = req.getParameter("brend");
//        String idModel = req.getParameter("model");
//        String idTypeBody = req.getParameter("typeBody");
//        String descr = req.getParameter("descr");
//        String id = req.getParameter("id");

        Brend brend = HbmStore.instOf().findBrendId(idBrend);
        Model model = HbmStore.instOf().findModelId(idModel);
        TypeBody body = HbmStore.instOf().findBodyId(idTypeBody);
        Car car = new Car(brend, model, body);
        Car carRsl = (Car) HbmStore.instOf().createItem(car);

//        Account account = (Account) req.getSession().getAttribute("user");

        Item item = new Item(descr, carRsl, false, account, new Date(System.currentTimeMillis()));
        Item rsl = null;
        try {
            rsl = HbmStore.instOf().createItem(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
//        String json = GSON.toJson(rsl.getId());
//        out.println(json);
//        out.flush();
        //super.doPost(req, resp);


    }

    private String onloadFoto(HttpServletRequest req) throws IOException {
        System.out.println("doPost");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
//        String id = req.getParameter("id");
        String nameFile = "";
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("c:\\projects\\job4j_cars\\images\\");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    File file = new File(folder + File.separator + item.getName());
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                        req.setAttribute("image", item.getName());
                        nameFile = item.getName();
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
//        doGet(req, resp);
        return nameFile;
    }

    private Foto saveFoto(String path) {
        Foto fotoNew = new Foto("c:\\projects\\job4j_cars\\images\\" + path);
        Foto foto = HbmStore.instOf().createItem(fotoNew);
        return foto;
    }


}
