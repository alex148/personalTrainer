package servlet;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.appengine.repackaged.com.google.api.client.json.Json;
import com.google.appengine.repackaged.com.google.api.client.json.JsonParser;
import dao.TrainingDao;
import model.Exercise;
import model.Training;
import model.User;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;
import utils.DatabaseInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Alex on 19/01/2016.
 */
public class AddTrainingServlet extends HttpServlet {

    public static final String USER = "USER";

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
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
       User u = getUserFromCache();
       try{
           String titre = req.getParameter(TITLE);
           String desc = req.getParameter(DESCRIPTION);
           String domaine = req.getParameter(DatabaseInfo.TRAINING_DOMAIN);
           String exercises = req.getParameter(EXERCICES);
           String durationTotal = req.getParameter(DatabaseInfo.TRAINING_TOTAL_DURATION);
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
           training.setTotalDuration(durationTotal);
           if(u != null && u.getMail() != null){
               training.setUserMail(u.getMail());
           }else{
               training.setUserMail("");
           }
            trainingDao.add(training);

       }catch(Exception e){
           System.out.println(e.getMessage());
       }

    }

    public User getUserFromCache(){
        Cache cache = null;
        Map props = new HashMap();

        props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
        props.put(MemcacheService.SetPolicy.ADD_ONLY_IF_NOT_PRESENT, true);

        try {
            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            cache = cacheFactory.createCache(props);

        } catch (net.sf.jsr107cache.CacheException e) {
            e.printStackTrace();
        }
        if (cache.get(USER) != null) {
            User u = (User)cache.get(USER);
            return u;
        }
        return null;
    }
}
