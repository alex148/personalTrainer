package servlet;

import com.google.appengine.api.datastore.*;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import model.Training;
import utils.DatabaseInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Thomas on 20/01/2016.
 */
public class PersonalServlet extends HttpServlet {

    private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONArray tableau= new JSONArray();

        Query q=new Query("TRAINING");
        PreparedQuery prepq = datastore.prepare(q);

        for (Entity result : prepq.asIterable()) {

            String date =  (String)result.getProperty("date");
            String planTitle = (String) result.getProperty("titre");
            String status = (String) result.getProperty("status");
            String duration = (String) result.getProperty("duration");
            String timeExpected = (String) result.getProperty("timeExpected");

            JSONObject trainingData = new JSONObject();
            try {

                trainingData.put("date", date);
                trainingData.put("titre", planTitle);
                trainingData.put("status", status);
                trainingData.put("duration", duration);
                trainingData.put("timeExpected", timeExpected);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            tableau.put(trainingData);


        }
        resp.getWriter().print(tableau.toString());
    }

}
