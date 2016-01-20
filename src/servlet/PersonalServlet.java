package servlet;

import com.google.appengine.api.datastore.*;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONArray tableau= new JSONArray();

        Query q=new Query("");

    }
/*
    private ArrayList<Training> trainingSearch(){

       DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();

        Query.Filter titleFilter =
                new Query.FilterPredicate(DatabaseInfo.TRAINING_TITLE,
                        Query.FilterOperator.IN,
                        search);

        Query.Filter descriptionFilter =
                new Query.FilterPredicate(DatabaseInfo.TRAINING_DESCRIPTION,
                        Query.FilterOperator.IN,
                        search);

        //Use CompositeFilter to combine multiple filters
        Query.Filter allFilter = Query.CompositeFilterOperator.or(titleFilter, descriptionFilter);


        Query q=new Query(DatabaseInfo.TRAINING_DATABASE).setFilter(allFilter);
        PreparedQuery pq=datastore.prepare(q);

        for(Entity e:pq.asIterable()){
            trainings.add(new Training(e));
        }
        return trainings;
    }*/
}
