package dao;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import model.Exercise;
import model.Training;
import utils.DatabaseInfo;

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
}
