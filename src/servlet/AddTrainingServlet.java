package servlet;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.appengine.repackaged.com.google.api.client.json.Json;
import com.google.appengine.repackaged.com.google.api.client.json.JsonParser;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alex on 19/01/2016.
 */
public class AddTrainingServlet extends HttpServlet {

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String DOMAINE = "domaine";
    public static final String EXERCICES = "exercises";

    private TrainingDao trainingDao;

    @Override
    public void init() throws ServletException {
        super.init();
        trainingDao = new TrainingDao();
    }

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
           String domaine = req.getParameter(DatabaseInfo.TRAINING_DOMAIN);
           String exercises = req.getParameter(EXERCICES);
           JSONArray jsonArray = new JSONArray(exercises);
           List<Exercise> exerciseList = new ArrayList<>();
           for(int i = 0; i<jsonArray.length();i++){
               JSONObject jsonObject = jsonArray.getJSONObject(i);
               Exercise ex = new Exercise();
               ex.setDescription((String)jsonObject.get(DatabaseInfo.EXERCISE_DESCRIPTION));
               ex.setTitle((String)jsonObject.get(DatabaseInfo.EXERCISE_TITLE));
               ex.setNumero(Integer.parseInt((String)jsonObject.get(DatabaseInfo.EXERCISE_NUMERO)));
               String duration = (String)jsonObject.get(DatabaseInfo.EXERCISE_DURATION);
               ex.setHour(Integer.parseInt(duration.substring(0,2)));
               ex.setMinute(Integer.parseInt(duration.substring(3,5)));
               ex.setSeconde(Integer.parseInt(duration.substring(6,8)));
               exerciseList.add(ex);
           }

           Training training = new Training();
           training.setDescription(desc);
           training.setTitle(titre);
           training.setDomain(domaine);
           training.setExercises(exerciseList);

            trainingDao.add(training);

       }catch(Exception e){
           System.out.println(e.getMessage());
       }

    }
}
