package model;

import java.util.Calendar;
import java.util.ArrayList;

public class Premium extends Consumer implements ICreatePlaylist, IEditPlaylist{

    private ArrayList <Playlist> playlist;

    public Premium(String nickname, String id, Calendar bondingdate) {
        super(nickname, id, bondingdate);
        playlist= new ArrayList<Playlist>();

    }
    /**
     * addPlaylist: this method add a playlist for the user. 
     * @param name: Stringthe playlists name.
     * @param matriz: int[][]. the matriz for the playlist
     * @param code: String. the code of the matriz
     * @param option: the type of the playlist
     * @return msj : a boolean for confirmation. 
     */

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
    /**
     * searchPlaylist: This method compares the names of existing playlist to see if there is already one.
     * @param name: String: the playlist name.
     * @return newPlaylist: a object of playlist audio. 
     */

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
    /**
     * addAudioPlaylist: this method add a audio for a specific playlist.
     * @param namePlaylist: String: the playlist name
     * @param typeAudio: int: the audios type.
     * @param audio:  Audio: the object audio. 
     * @param audioName: String: the audios name
     * @return msj : msj: String: a confirmation message 
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
    /**
     * delateAudio: this method delate a audio for a specific playlist.
     * @param audio:  Audio: the object audio.
     * @param namePlaylist: String: the playlist name 
     * @param audioName: String: the audios name
     * @return msj : msj: String: a confirmation message 
     */

    @Override
    public String delateAudio(Audio audio, String namePlaylist, String audioName ){
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