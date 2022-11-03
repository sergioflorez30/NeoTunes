package model; 

import java.util.Calendar;

public abstract class User {

    private String nickname;
    private String id;
    private Calendar bondingdate;


    public User(String nickname, String id,Calendar bondingdate) {
        this.nickname = nickname;
        this.id = id;
        this.bondingdate = bondingdate;
    }


    public String getNickname() {
        return nickname;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public Calendar getBondingdate() {
        return bondingdate;
    }


    public void setBondingdate(Calendar bondingdate) {
        this.bondingdate = bondingdate;
    }



     
    
}