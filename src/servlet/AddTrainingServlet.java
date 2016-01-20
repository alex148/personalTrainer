package servlet;

import com.google.appengine.api.datastore.DatastoreService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 19/01/2016.
 */
public class AddTrainingServlet extends HttpServlet {

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String DOMAINE = "domaine";
    public static final String EXERCICES = "exercises";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("ha-addTraining.html");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try{
           String titre = req.getParameter(TITLE);
           String desc = req.getParameter(DESCRIPTION);
           String domaine = req.getParameter(DOMAINE);
           String exercises = req.getParameter(EXERCICES);
           System.out.println("ok");
       }catch(Exception e){
           System.out.println(e.getMessage());
       }

    }
}
