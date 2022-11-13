package model; 

public class Podcast extends Audio{

    private String description;
    private TypePodcast typePodcast;


    public Podcast(String name, String url, int duration,String description,int option) {
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
    public int typePodcast(){
        switch (typePodcast) {
            case POLITIC:
                return 1;
            case ENTERTAIMENT:
                return 2;
            case FASHION:
                return 3;
            case VIDEOGAME:
                return 4;
            default:
             return 0;
     }  

    }

    public String typeStringPodcast(){
        String msj = ""; 
        switch(typePodcast){
        case POLITIC:
             msj = "politic";
            return msj;
        case ENTERTAIMENT:
             msj = "entertaiment";
            return msj;
        case FASHION:
             msj = "fashion";
            return msj;
        case VIDEOGAME:
             msj = "videogame";
            return msj;
        default:
            return null;  

        }
    }

}