package model;

import java.util.Calendar;
import java.util.ArrayList;

public class Creator extends Producer{
    private ArrayList <Podcast> podcasts;

    public Creator(String nickname, String id, Calendar bondingdate, String url, String name) {
        super(nickname, id, bondingdate, url, name);
        podcasts= new ArrayList<Podcast>();
    }

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }
    /**
     * searchAudioAutor: this method search a audio for this creator. 
     * @param podcast: Podcast: the obj podcast
     * @return isFoud : boolean: if is found o isnot found. 
     */
    public boolean searchAudioAutor(Podcast podcast){
        boolean isFound= false;
        for(int i=0;i<podcasts.size() && !isFound ;i++){
            if( podcasts.get(i) == podcast){
                isFound= true;
            }
         }
    
        return isFound;
        
    }

}