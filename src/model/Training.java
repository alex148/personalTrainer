package model;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import utils.DatabaseInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 19/01/2016.
 */
public class Training implements  Serializable{
    private String title;
    private String description;
    private String domain;
    private String userMail;
    private String totalDuration;
    private List<Exercise> exercises;
    private Key key;

    public Training(Entity entity) {
        this.title = entity.getProperty(DatabaseInfo.TRAINING_TITLE).toString();
        this.description = entity.getProperty(DatabaseInfo.TRAINING_DESCRIPTION).toString();
        this.domain = entity.getProperty(DatabaseInfo.TRAINING_DOMAIN).toString();
        key=entity.getKey();
        exercises=new ArrayList<>();

    }

    public Training(){
        key = null;
        exercises = new ArrayList<>();
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
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

    public void setExercises(List<Exercise> ex){
        this.exercises = ex;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(String totalDuration) {
        this.totalDuration = totalDuration;
    }
}
