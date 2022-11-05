package model;

import java.util.Calendar;
import java.util.ArrayList;

public class Premium extends Consumer implements ICreatePlaylist, IEditPlaylist{

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

    @Override
    public String addAudioPlaylist(String namePlaylist,int typeAudio, Audio audio, String audioName){
        String msj="";
        Playlist thePlaylist=searchPlaylist(namePlaylist);
        if(thePlaylist==null){
            msj="dont exist the playslist";
         }
         else{
            if(thePlaylist.typePlaylist()==1){
                if(thePlaylist.typePlaylist()==typeAudio){
                    boolean audiorepit=thePlaylist.searchAudio(audioName);
                    if(audiorepit== false){
                        thePlaylist.getAudios().add(audio);
                    }
                    else{
                        msj="the audio is repit";
                    }
                }
                else{
                    msj="you cant add this audio beacuse is diferent type of playlist";
                }
            }
            if(thePlaylist.typePlaylist()==2){
                if(thePlaylist.typePlaylist()==typeAudio){
                    boolean audiorepit=thePlaylist.searchAudio(audioName);
                    if(audiorepit== false){
                        thePlaylist.getAudios().add(audio);
                    }
                    else{
                        msj="the audio is repit";
                    }
                }
                else{
                    msj="you cant add this audio beacuse is difertent type of playlist";
                }
            }
            if(thePlaylist.typePlaylist()==3){
                
                    boolean audiorepit=thePlaylist.searchAudio(audioName);
                    if(audiorepit == false){
                        thePlaylist.getAudios().add(audio);
                    }
                    else{
                        msj="the audio is repit";
                    }
            }
         }
        
        return msj;

    }

    @Override
    public String delateAudio(Audio auido, String namePlaylist, String audioName ){
        String msj="";
        Playlist thePlaylist=searchPlaylist(namePlaylist);
        if(thePlaylist==null){
             msj="dont exist the playslist";
        }
        else{
            boolean audiorepit=thePlaylist.searchAudio(audioName);
            if(audiorepit==true){
            thePlaylist.getAudios().remove(auido);
            msj="has been removed successfully";
          }
          else{
            msj="no such audio found";
          }

          
       }
       return msj;

    }

      

}