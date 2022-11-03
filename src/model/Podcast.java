package model; 

public class Podcast extends Audio{

    private String description;
    private TypePodcast typePodcast;


    public Podcast(String name, String url, String duration,String description,int option) {
        super(name,url,duration);
        this.description = description;

        switch(option){
            case 1:
            typePodcast=TypePodcast.POLITIC;
            case 2:
            typePodcast=TypePodcast.ENTERTAIMENT;
            case 3:
            typePodcast=TypePodcast.FASHION;
            case 4:
            typePodcast=TypePodcast.VIDEOGAME;

        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypePodcast getTypePodcast() {
        return typePodcast;
    }

    public void setTypePodcast(TypePodcast typePodcast) {
        this.typePodcast = typePodcast;
    }

}