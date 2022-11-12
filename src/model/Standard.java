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
    /**
     * addPlaylist: this method add a playlist for the user. 
     * @param name: Stringthe playlists name.
     * @param matriz: int[][]. the matriz for the playlist
     * @param code: String. the code of the matriz
     * @param option: the type of the playlist
     * @return validation : a boolean for confirmation. 
     */
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
    /**
     * searchPlaylist: This method compares the names of existing playlist to see if there is already one.
     * @param name: String: the playlist name.
     * @return newPlaylist: a object of playlist audio. 
     */
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
    /**
     * availablePlaylist: searh a available position in the array 
     * @return validation: a boolean of found or not found
     */
    
    public boolean availablePlaylist(){
        boolean validation= true;
        if(playlist[19]!=null){
            validation=false;
        }
     
        return validation;
    }
     /**
     * addAudioPlaylist: this method add a audio for a specific playlist.
     * @param namePlaylist: String: the playlist name
     * @param typeAudio: int: the audios type.
     * @param audio:  Audio: the object audio. 
     * @param audioName: String: the audios name
     * @return msj: String: a confirmation message 
     */

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
    /**
     * delateAudio: this method delate a audio for a specific playlist.
     * @param audio:  Audio: the object audio.
     * @param namePlaylist: String: the playlist name 
     * @param audioName: String: the audios name
     * @return msj: String: a confirmation message 
     */

    @Override
    public String delateAudio(Audio audio, String namePlaylist, String audioName){

        String msj="";
        Playlist thePlaylist=searchPlaylist(namePlaylist);
        if(thePlaylist==null){
            msj="dont exist the playslist";
        }
        else{
            boolean audiorepit=thePlaylist.searchAudio(audioName);
          if(audiorepit==true){
            thePlaylist.getAudios().remove(audio);
            msj="has been removed successfully";
            }
            else{
            msj="no such audio found";
            }

          
       }
       return msj;

    }

    @Override
    public String sharePlaylist(String namePlaylist){
        String msj="";
        Playlist playlist=searchPlaylist(namePlaylist);
        if(playlist==null){
           msj="dont exist the playslist";
        }
        else{
           msj=playlist.getCode();
        }
    
       return msj;
    }

    @Override
    public String sharePlaylistMatriz(String namePlaylist){
        String msj="";
        Playlist playlist=searchPlaylist(namePlaylist);
        if(playlist==null){
            msj="";
        }
        else{
            msj= printMatrix(playlist.getMatriz());
        }

          return msj;

    }
    @Override
    public String printMatrix(int[][] matrix){
        String print = "";
        for (int i = 0; i < matrix.length; i++) { // filas numbers.length
        for (int j = 0; j < matrix[0].length; j++) { // columnas numbers[0].length
          print += matrix[i][j] + " ";
        }
        print += "\n";
      }
  
      return print;

    }




}