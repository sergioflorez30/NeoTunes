package model;

import java.util.Calendar;
import java.util.ArrayList;


public class Standard extends Consumer implements ICreatePlaylist, IEditPlaylist{
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
                    boolean audiorepit= thePlaylist.searchAudio(audioName);
                    if(audiorepit == false){
                        thePlaylist.getAudios().add(audio);
                        msj="audio added";
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
                    boolean audiorepit= thePlaylist.searchAudio(audioName);
                    if(audiorepit == false){
                        thePlaylist.getAudios().add(audio);
                        msj="audio added";
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
                
                    boolean audiorepit= thePlaylist.searchAudio(audioName);
                    if(audiorepit == false){
                        thePlaylist.getAudios().add(audio);
                        msj="audio added";
                    }
                    else{
                        msj="the audio is repit";
                    }
            }
         }
        
        return msj;

    }

    @Override
    public String delateAudio(Audio auido, String namePlaylist, String audioName){

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