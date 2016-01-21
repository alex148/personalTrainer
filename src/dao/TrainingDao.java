package dao;

import com.google.appengine.api.datastore.DatastoreApiHelper;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.repackaged.com.google.api.client.util.Data;
import com.google.appengine.api.datastore.*;
import model.Exercise;
import model.Training;
import utils.DatabaseInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 20/01/2016.
 */
public class TrainingDao implements InterfaceDao<Training> {

    private ExerciseDao exerciseDao;

    public TrainingDao(){
        exerciseDao = new ExerciseDao();
    }

    @Override
    public Training getByKey(Key key) {
        return null;
    }

    @Override
    public boolean deleteByKey(Key key) {
        return false;
    }

    @Override
    public boolean update(Training object) {
        return false;
    }

    @Override
    public Key add(Training object) {
        try{
            Entity training = new Entity(DatabaseInfo.TRAINING_DATABASE);
            training.setProperty(DatabaseInfo.TRAINING_TITLE, object.getTitle());
            training.setProperty(DatabaseInfo.TRAINING_DOMAIN, object.getDomain());
            training.setProperty(DatabaseInfo.TRAINING_DESCRIPTION, object.getDescription());
            training.setProperty(DatabaseInfo.TRAINING_USER_MAIL,object.getUserMail());
            training.setProperty(DatabaseInfo.TRAINING_TOTAL_DURATION,object.getTotalDuration());
            Key trainingKey = dataStore.put(training);

            if(object.getExercises() != null && !object.getExercises().isEmpty()){
                for (Exercise ex: object.getExercises()) {
                    ex.setParentKey(trainingKey);
                    exerciseDao.add(ex);
                }
            }
            return trainingKey;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
      return null;
    }

    @Override
    public List<Training> getAll(String entityName) {
        return null;
    }

    public ArrayList<Training> trainingSearch(String search){
        ArrayList<Training> trainings=new ArrayList<>();
        DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();

        Query.Filter titleFilter =
                new Query.FilterPredicate(DatabaseInfo.TRAINING_TITLE,
                        Query.FilterOperator.EQUAL,
                        search);

        Query.Filter descriptionFilter =
                new Query.FilterPredicate(DatabaseInfo.TRAINING_DESCRIPTION,
                        Query.FilterOperator.EQUAL,
                        search);

        //Use CompositeFilter to combine multiple filters
        Query.Filter allFilter = Query.CompositeFilterOperator.or(titleFilter,descriptionFilter);


        Query q=new Query(DatabaseInfo.TRAINING_DATABASE).setFilter(allFilter);
        PreparedQuery pq=datastore.prepare(q);

        for(Entity e:pq.asIterable()){
            trainings.add(new Training(e));
        }
        return trainings;
    }
}
