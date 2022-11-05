package model;

import java.util.Calendar;
import java.util.ArrayList;


public class Standard extends Consumer implements ICreatePlaylist{
    private Playlist[] playlist;
    private final int SIZE = 20; 

    public Standard(String nickname, String id, Calendar bondingdate) {

        super(nickname, id, bondingdate);
        playlist= new Playlist[SIZE];
    }
    @Override
    public boolean addPlaylist(String name, int[][] matriz, String code,int option){

        boolean validation= true;
        Playlist newPlaylist= searchPlaylist(name);     
        if(newPlaylist!=null){
            validation=false;
         }
          else{
            boolean otherValidation=availablePlaylist();
            if(otherValidation==true){
                newPlaylist = new Playlist( name, matriz, code,option);
                otherValidation=false;
                for(int i=0;i<20 && !otherValidation ;i++){
                    if(playlist[i]==null){
                        playlist[i]=newPlaylist;
                        otherValidation=true;
                    }

                }
       
            }   

         }
        return validation;
    }
    @Override
    public Playlist searchPlaylist(String name){
        Playlist newPlaylist=null;
        boolean validation= false;
         for(int i=0;i<20 && !validation ;i++){
            if(playlist[i]!=null && playlist[i].getName().equals(name)){
                newPlaylist=playlist[i];
                validation= true;
            }
         }
    
        return newPlaylist;
    }

    public boolean availablePlaylist(){
        boolean validation= true;
        if(playlist[19]!=null){
            validation=false;
        }
     
        return validation;
    }



}