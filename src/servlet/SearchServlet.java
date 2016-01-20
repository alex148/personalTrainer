package servlet;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;
import model.Exercise;
import model.Training;
import utils.DatabaseInfo;

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
        String search=req.getAttribute(SEARCH_BAR).toString();
        ArrayList<Training> trainings=trainingSearch(search);
        ArrayList<Exercise> exercises=exerciseSearch(search);

    }

    private ArrayList<Training> trainingSearch(String search){
        ArrayList<Training> trainings=new ArrayList<>();
        DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();

        Filter titleFilter =
                new FilterPredicate(DatabaseInfo.TRAINING_TITLE,
                        FilterOperator.IN,
                        search);

        Filter descriptionFilter =
                new FilterPredicate(DatabaseInfo.TRAINING_DESCRIPTION,
                        FilterOperator.IN,
                        search);

        //Use CompositeFilter to combine multiple filters
        Filter allFilter = CompositeFilterOperator.or(titleFilter,descriptionFilter);


        Query q=new Query(DatabaseInfo.TRAINING_DATABASE).setFilter(allFilter);
        PreparedQuery pq=datastore.prepare(q);

        for(Entity e:pq.asIterable()){
            trainings.add(new Training(e));
        }
        return trainings;
    }
    private ArrayList<Exercise> exerciseSearch(String search){
        ArrayList<Exercise> exercises=new ArrayList<>();
        DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();

        Filter titleFilter =
                new FilterPredicate(DatabaseInfo.EXERCISE_TITLE,
                        FilterOperator.IN,
                        search);

        Filter descriptionFilter =
                new FilterPredicate(DatabaseInfo.EXERCISE_DESCRIPTION,
                        FilterOperator.IN,
                        search);

        //Use CompositeFilter to combine multiple filters
        Filter allFilter = CompositeFilterOperator.or(titleFilter,descriptionFilter);


        Query q=new Query(DatabaseInfo.EXERCISE_DATABASE).setFilter(allFilter);
        PreparedQuery pq=datastore.prepare(q);

        for(Entity e:pq.asIterable()){
            exercises.add(new Exercise(e));
        }
        return exercises;
    }
}
