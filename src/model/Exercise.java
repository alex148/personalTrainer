package model;


import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.repackaged.com.google.common.base.Flag;
import utils.DatabaseInfo;

/**
 * Created by Robin on 19/01/2016.
 */
public class Exercise {
    private int hour;
    private int minute;
    private int seconde;
    private String title;
    private String description;
    private int numero;
    private Key key;
    private Key parentKey;

    public Exercise(Entity entity) {
        this.hour =(int) entity.getProperty(DatabaseInfo.EXERCISE_HOUR);
        this.minute = (int) entity.getProperty(DatabaseInfo.EXERCISE_MINUTE);
        this.seconde = (int) entity.getProperty(DatabaseInfo.EXERCISE_SECONDE);
        this.numero = (int) entity.getProperty(DatabaseInfo.EXERCISE_NUMERO);
        this.title = entity.getProperty(DatabaseInfo.EXERCISE_TITLE).toString();
        this.description = entity.getProperty(DatabaseInfo.EXERCISE_DESCRIPTION).toString();
        key=entity.getKey();
    }

    public Exercise(){
        key = null;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSeconde() {
        return seconde;
    }

    public void setSeconde(int seconde) {
        this.seconde = seconde;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Key getParentKey() {
        return parentKey;
    }

    public void setParentKey(Key parentKey) {
        this.parentKey = parentKey;
    }
}
