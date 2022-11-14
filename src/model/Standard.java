package model;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.Random;


public class Standard extends Consumer implements ICreatePlaylist, IEditPlaylist, IPlaying{
    private Playlist[] playlist;
    private ArrayList <Audio> audios;
    private final int SIZE = 20; 

    public Standard(String nickname, String id, Calendar bondingdate) {

        super(nickname, id, bondingdate);
        playlist= new Playlist[SIZE];
        audios= new ArrayList<Audio>();
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
    /**
     * SharePlaylist: this method share a specific  users playlist. 
     * @param namePlaylist: String: this is de playlist name.
     * @return  msj: string: String: the playlist. 
     */

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
    /**
     * sharePlaylistMatriz: this method share a matriz playlist  for user.
     * @param namePlaylist: String: this is de playlist name.
     * @return   msj: string: String: the playlist. 
     */

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
    /**
     * printMatrix: this method print the matriz playlist  for user.
     * @param matrix: int[][]: this is the matrix.
     * @return   print : string: String: the playlists matrix. 
     */

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

    /**
     * generateNumber: this method generate a random number
     * @return num : int: a random number. 
     */
    public int generateNumber(){

        Random r= new Random();
       
         int value = r.nextInt(2 + 1) + 1;
       
        return value;
    }
    /**
     * play: this method plays an audio. 
     * @param audio: Audio: the audios name.
     * @return  msj: String: a confirm message.
     */
    
    @Override
    public String play(Audio audio){
        String msj = "." + "\n"+ "." + "\n"+ "." +  "\n" + " the audio is playing" + "." + "\n";   ; 
        if(audios.size()==0){

        }
        else{
            int reproduction =audios.size();
            String ad1 = "nike -Just Do It" + "\n";
            String ad2 = "Coca-Cola -Open Happiness" + "\n";
            String ad3= "M&Ms -  Melts in your moth" + "\n";

            if(reproduction %2 ==0){
                int number = generateNumber();
                switch(number){
                case 1: 
                    msj = ad1;
                    break;
                case 2:
                    msj = ad2;
                    break;
                case 3:
                    msj = ad3;
                    break;
                }
            }
            else{
                audios.add(audio);
            }
        }

        return msj;
    }
    /**
     * mostSongViews: this method inform the most view song in the app. 
     * @return msj: String : a confirmation message.
     */

    public String mostSongViews(){
      String msj="";
      int [] geners= {0,0,0,0};
      int position=0;
       if(audios.size()!=0){
         for(int i=0; i<audios.size();i++){
           if(audios.get(i) instanceof Song){
             Song song = ( (Song)(audios.get(i)) );
             switch(song.typeSong()){
               case 1:
                geners[0]++;
                break;
               case 2:
               geners[1]++;
                break;
               case 3:
               geners[2]++;
                break;
               case 4:
               geners[3]++;
                break;
               default:
                break;
             }
           }
         }
         int mayor=0;
          for(int i=0; i<4;i++){
           if(geners[i]>mayor){
             position=i;
           }
          }
         switch(position){
           case 0:
           msj="the most listened to genre for this user: rock \n"+"views: "+geners[position];
           break;
           case 1:
           msj="the most listened to genre for this user: pop \n"+"views: "+geners[position];
           break;
           case 2:
           msj="the most listened to genre for this user: trap \n"+"views: "+geners[position];
           break;
           case 3:
           msj="the most listened to genre for this user: house \n"+"views: "+geners[position];
           break;
           case 4:
           msj="the dont exist song";
           break;
         }
         
       }
       else{
         msj="the user dont have reproduction";
       }
      return msj;
     }

     /**
     * mostPodcastViews: this method inform the most view podcast in the app. 
     * @return msj: String : a confirmation message.
     */

     public String mostPodcastViews(){
      String msj="";
      int [] geners= {0,0,0,0};
      int position=0;
       if(audios.size()!=0){
         for(int i=0; i<audios.size();i++){
           if(audios.get(i) instanceof Podcast){
            Podcast podcast = ( (Podcast)(audios.get(i)) );
             switch(podcast.typePodcast()){
               case 1:
                geners[0]++;
                break;
               case 2:
               geners[1]++;
                break;
               case 3:
               geners[2]++;
                break;
               case 4:
               geners[3]++;
                break;
               default:
                break;
             }
           }
         }
         int mayor=0;
          for(int i=0; i<4;i++){
           if(geners[i]>mayor){
             position=i;
           }
          }
         switch(position){
           case 0:
           msj="the most listened to genre for this user: Politic \n"+"views: "+geners[position];
           break;
           case 1:
           msj="the most listened to genre for this user: Entertaiment \n"+"views: "+geners[position];
           break;
           case 2:
           msj="the most listened to genre for this user: Fashion \n"+"views: "+geners[position];
           break;
           case 3:
           msj="the most listened to genre for this user: Videogame \n"+"views: "+geners[position];
           break;
           case 4:
           msj="the dont exist podcast";
           break;
         }
         
       }
       else{
         msj="the user dont have reproduction";
       }
      return msj;
     }

}