package controller;

import service.SkierService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "skiers", value = "/skiers/*")
public class SkierServlet extends HttpServlet {

    private SkierService skierService;

    public SkierServlet() {
        this.skierService = new SkierService();
    }

//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        if (req.getMethod().equalsIgnoreCase("POST")) {
//            skierService.createSkierHistory(req, resp);
//        } else {
//            skierService.getTotalVertical(req, resp);
//        }
//    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        skierService.createSkierHistory(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        skierService.getTotalVertical(req, resp);
    }
}
