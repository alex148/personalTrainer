package dao;

import com.google.appengine.api.datastore.*;
import com.google.appengine.repackaged.com.google.api.client.util.Data;
import model.Exercise;
import utils.DatabaseInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 20/01/2016.
 */
public class ExerciseDao implements InterfaceDao<Exercise> {

    @Override
    public Exercise getByKey(Key key) {
        return null;
    }

    @Override
    public boolean deleteByKey(Key key) {
        return false;
    }

    @Override
    public boolean update(Exercise object) {
        return false;
    }

    @Override
    public Key add(Exercise object) {
        try{
            Entity exercise;
            if(object.getParentKey() != null) {
                exercise = new Entity(DatabaseInfo.EXERCISE_DATABASE, object.getParentKey());
            }else {
                exercise = new Entity(DatabaseInfo.EXERCISE_DATABASE);
            }

            exercise.setProperty(DatabaseInfo.EXERCISE_NUMERO,object.getNumero());
            exercise.setProperty(DatabaseInfo.EXERCISE_TITLE,object.getTitle());
            exercise.setProperty(DatabaseInfo.EXERCISE_DESCRIPTION,object.getDescription());
            exercise.setProperty(DatabaseInfo.EXERCISE_HOUR,object.getHour());
            exercise.setProperty(DatabaseInfo.EXERCISE_MINUTE,object.getMinute());
            exercise.setProperty(DatabaseInfo.EXERCISE_SECONDE,object.getSeconde());

            return dataStore.put(exercise);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Exercise> getAll(String entityName) {
        return null;
    }
    public ArrayList<Exercise> exerciseSearch(String search){
        ArrayList<Exercise> exercises=new ArrayList<>();
        DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();

        Query.Filter titleFilter =
                new Query.FilterPredicate(DatabaseInfo.EXERCISE_TITLE,
                        Query.FilterOperator.EQUAL,
                        search);

        Query.Filter descriptionFilter =
                new Query.FilterPredicate(DatabaseInfo.EXERCISE_DESCRIPTION,
                        Query.FilterOperator.EQUAL
                        ,
                        search);

        //Use CompositeFilter to combine multiple filters
        Query.Filter allFilter = Query.CompositeFilterOperator.or(titleFilter,descriptionFilter);


        Query q=new Query(DatabaseInfo.EXERCISE_DATABASE).setFilter(allFilter);
        PreparedQuery pq=datastore.prepare(q);

        for(Entity e:pq.asIterable()){
            exercises.add(new Exercise(e));
        }
        return exercises;
    }

}
