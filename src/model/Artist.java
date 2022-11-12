package model;

import java.util.Calendar;
import java.util.ArrayList;


public class Artist extends Producer{
    private ArrayList <Song> songs;
    

    public Artist(String nickname, String id, Calendar bondingdate, String url, String name) {
            super(nickname, id, bondingdate, url, name);
            songs= new ArrayList<Song>();
        }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    
    public boolean searchAudioAutor(Song song){
        boolean isFound= false;
        for(int i=0;i<songs.size() && !isFound ;i++){
            if( songs.get(i) == song){
                isFound= true;
            }
         }
    
        return isFound;
    }


       
}