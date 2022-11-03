package model; 

public class Podcast extends Audio{

    private String description;
    private Type_Podcast typePodcast;


    public Podcast(String name, String url, String duration,String description,int option) {
        super(name,url,duration);
        this.description = description;

        switch(option){
            case 1:
            typePodcast=Type_Podcast.POLITIC;
            case 2:
            typePodcast=Type_Podcast.ENTERTAIMENT;
            case 3:
            typePodcast=Type_Podcast.FASHION;
            case 4:
            typePodcast=Type_Podcast.VIDEOGAME;

        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type_Podcast getTypePodcast() {
        return typePodcast;
    }

    public void setTypePodcast(Type_Podcast typePodcast) {
        this.typePodcast = typePodcast;
    }

}