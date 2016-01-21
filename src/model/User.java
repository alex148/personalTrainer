package model;

import java.io.Serializable;

/**
 * Created by Alex on 20/01/2016.
 */
public class User implements Serializable{


    private String name;
    private String mail;
    private String id_token;

    public User(){

    }
    public User(String name,String mail,String token){
        this.mail = mail;
        this.name = name;
        this.id_token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
