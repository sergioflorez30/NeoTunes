package model;

import java.util.Calendar;
import java.util.ArrayList;

public class Premium extends Consumer implements ICreatePlaylist{

    private ArrayList <Playlist> playlist;

    public Premium(String nickname, String id, Calendar bondingdate) {
        super(nickname, id, bondingdate);
        playlist= new ArrayList<Playlist>();

    }

    @Override
    public boolean addPlaylist(String name, int[][] matriz, String code,int option){

        boolean msj = true;
        Playlist newPlaylist= searchPlaylist(name);

        if (newPlaylist != null) {
            msj = false;
        } 
        else {
          playlist.add(new Playlist(name, matriz, code, option));       
             }

        return msj;

    }

    @Override
    public Playlist searchPlaylist(String name){
        Playlist newPlaylist=null;
        boolean validation= false;
         for(int i=0;i<playlist.size() && !validation ;i++){
            if( playlist.get(i).getName().equalsIgnoreCase(name)){
                newPlaylist=playlist.get(i);
                validation= true;
            }
         }
    
        return newPlaylist;
    }

      

}