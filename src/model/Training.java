package model;

import com.google.appengine.api.datastore.Entity;
import utils.DatabaseInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 19/01/2016.
 */
public class Training {
    private String title;
    private String description;
    private String domain;
    private List<Exercise> exercises;

    public Training(Entity entity) {
        this.title = entity.getProperty(DatabaseInfo.TRAINING_TITLE).toString();
        this.description = entity.getProperty(DatabaseInfo.TRAINING_DESCRIPTION).toString();
        this.domain = entity.getProperty(DatabaseInfo.TRAINING_DOMAIN).toString();
        exercises=new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void addExercise(Exercise exercise){
        exercises.add(exercise);
    }
}
