package model;

public abstract class Audio{
    private String name;
    private String url;
    private String duration;
    private int view;


    public Audio(String name, String url, String duration) {
        this.name = name;
        this.url = url;
        this.duration = duration;
        view = 0;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public String getDuration() {
        return duration;
    }


    public void setDuration(String duration) {
        this.duration = duration;
    }


    public int getView() {
        return view;
    }


    public void setView(int view) {
        this.view = view;
    }

    



    
}