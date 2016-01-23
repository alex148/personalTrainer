package servlet;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.repackaged.com.google.gson.Gson;
import dao.ExerciseDao;
import dao.TrainingDao;
import model.Exercise;
import model.Training;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 21/01/2016.
 */
public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("ha-result-detail-screen.html");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExerciseDao exerciseDao=new ExerciseDao();
        List<Exercise> exercises;

        String id=req.getParameter("id");
        String kind=req.getParameter("kind");

        Long idLong=Long.parseLong(id);

        if(kind.equals("EXERCISE")){
            String parent=req.getParameter("parent");
            Long parentLong=Long.parseLong(parent);
            Key parentKey= KeyFactory.createKey("TRAINING",parentLong);
            Key key= KeyFactory.createKey(parentKey,kind,idLong);
            exercises=new ArrayList<>();
            exercises.add(exerciseDao.getByKey(key));
        }
        else{
            Key key= KeyFactory.createKey(kind,idLong);
            exercises=exerciseDao.getExercisesFromParentKey(key);
        }
        Gson gson = new Gson();
        String exercisesJson = gson.toJson(exercises);
        String finalJson="{\"exercisesList\":"+exercisesJson+"}";

        //Send the Json object to the web browser
        PrintWriter out= resp.getWriter();
        out.write(finalJson);
    }
}
