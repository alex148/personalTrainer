package servlet;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;
import model.Exercise;
import model.Training;
import utils.DatabaseInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Robin on 19/01/2016.
 */
public class SearchServlet extends HttpServlet {
    public static String SEARCH_BAR="searchBar";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search=req.getParameter(SEARCH_BAR);
       resp.sendRedirect("/Result?search="+search);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("ha-search-screen.html");
        view.forward(req, resp);
    }
}
