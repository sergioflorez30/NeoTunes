package model;

import java.util.Calendar;

public abstract class Producer extends User{

    private String url;
    private String name;
    private int totalViews;
    private int totalPLayedTime;

    
    public Producer(String nickname, String id, Calendar bondingdate, String url, String name) {
        super(nickname, id, bondingdate);
        this.url = url;
        this.name = name;
        totalViews =0;
        totalPLayedTime =0;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getTotalViews() {
        return totalViews;
    }


    public void setTotalViews(int totalViews) {
        this.totalViews = totalViews;
    }


    public int getTotalPLayedTime() {
        return totalPLayedTime;
    }


    public void setTotalPLayedTime(int totalPLayedTime) {
        this.totalPLayedTime = totalPLayedTime;
    }

    

    
}