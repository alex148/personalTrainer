package servlet;

import com.google.appengine.api.datastore.*;
import com.google.appengine.repackaged.com.google.gson.Gson;
import dao.ExerciseDao;
import dao.TrainingDao;
import model.Exercise;
import model.Training;
import utils.DatabaseInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Robin on 20/01/2016.
 */
public class ResultServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("ha-result-screen.html");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExerciseDao exerciseDao=new ExerciseDao();
        TrainingDao trainingDao=new TrainingDao();
        String search=req.getParameter("search");//req.getAttribute(SEARCH_BAR).toString();
        ArrayList<Training> trainings=trainingDao.trainingSearch(search);
        ArrayList<Exercise> exercises=exerciseDao.exerciseSearch(search);
        Gson gson = new Gson();

        String trainingsJson = gson.toJson(trainings);
        String exercisesJson = gson.toJson(exercises);
        String finalJson="{\"trainingsList\":"+trainingsJson+",\"exercisesList\":"+exercisesJson+"}";

        //Send the Json object to the web browser
        PrintWriter out= resp.getWriter();
        out.write(finalJson);
    }
}
