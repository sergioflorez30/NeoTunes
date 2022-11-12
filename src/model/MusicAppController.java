package model;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;


public class MusicAppController {

    private ArrayList <User> users;
    private ArrayList <Audio> audios;
    public static final int ROWS = 6; 
    public static final int COLUMNS = 6; 

    public MusicAppController() {
        users= new ArrayList<User>();
        audios= new ArrayList<Audio>();
    }
    /**
     * searchUser: This method compares the nicknames of existing users to see if there is already one.
     * @param nickname: String: the nickname user.
     * @return pos: a object of type user. 
     */
   
    public User searchUser(String nickname){
		User user = null; 
		boolean isFound = false; 
		for(int i = 0; i < users.size() && !isFound; i++ ){
			if(users.get(i).getNickname().equalsIgnoreCase(nickname)){
				user = users.get(i); 
				isFound = true; 
			} 
		}
		return user; 
	}
    /**
     * searchAudio: This method compares the names of existing audios to see if there is already one.
     * @param name: String: the audios name.
     * @return pos: a object of type audio. 
     */

	public Audio searchAudio(String name){
        Audio audio=null;
        boolean isFound= false;
         for(int i=0;i<audios.size() && !isFound ;i++){
            if( audios.get(i).getName().equalsIgnoreCase(name)){
                audio=audios.get(i);
                isFound= true;
            }
         }
    
        return audio;
    }
    /**
     * dateActual: this method give the actual date.
     * @return calendar: the day actual. 
     */

	public Calendar dateActual(){
        
        Calendar calendar=new GregorianCalendar(2022,Calendar.NOVEMBER,8);
        return calendar ;
         
    }
    /**
     * generateMatriz: this method generate a matriz.
     * @return matriz : a matriz of int. 
     */

    public int[][] generateMatriz(){

        int matriz[][]=new int[ROWS][COLUMNS];

        for(int i=0;i<ROWS;i++){
            for(int j=0;i<COLUMNS;i++){
                matriz[i][j]=generateNumber();
            }
        }

        return matriz;

    }
    /**
     * generateNumber: this method generate a random number
     * @return num : int: a random number. 
     */

    public int generateNumber(){

        Random r= new Random();
       
         int value = r.nextInt(8 + 1) + 1;
       
        return value;
    }
    /**
     * generateCode: this method generate a code depended of the playlist type
     * @param option: int: the playlist type.
     * @param matriz: int[][]: the playlist matriz.
     * @return code : a msj of the matriz. 
     */
    public String generateCode(int option,int[][]matriz){
        String code=null;
        switch(option){
            case 1:
             for(int i=5;i>0;i--){
                code+=matriz[i][0];
             }
             for(int j=1;j<5;j++){
                code+=matriz[j][j];
             }
             for(int k=5;k>0;k--){
                code+=matriz[k][5];
             }
            break;

            case 2:
             for(int i=0;i<2;i++){
                code+=matriz[0][i];
             }
             for(int j=1;j<5;j++){
                code+=matriz[j][2];
             } 
             for(int k=5;k>0;k--){
                code+=matriz[k][3];
             }
             for(int u=3;u<5;u++){
                code+=matriz[0][u];
             }

             break;
               
             case 3:
             for (int i=5;i>=0;i--){
                for(int j=5;j>=0;j--){
                    int sum = i+j;
                    if (sum%2!=0){
                        if(sum!=1){
                            code+=matriz[i][j]+" ";
                        }
                    }

                }
            }
             break;


           }

      return code;

    }
    /**
     * registerProducer: this method register a producer.
     * @param nickname: String: this is the producers nickname.
     * @param id: String: this is de producers id.
     * @param url: String: this is the image url.
     * @param name: String: this is the real producer name.
     * @param option: int: the producer type. 
     * @return msj: String: a confirm message.
     */

    public String registerProducer(String nickname , String id,String url, String name,int option){
    	
    	String msj = ""; 
    	User user = searchUser(nickname);

    	 if (user != null) {

            msj = "the user exist... :/";
        } else {
            Calendar bondingdate=dateActual();
            if(option==1){
                users.add(new Creator(nickname,id,bondingdate,url,name));
                msj = "creator producer created";
            }
            else{
                users.add(new Artist(nickname,id,bondingdate,url,name));
                msj = "artist producer created";
            }
            
        }
        return msj;

    }
    /**
     * registerConsumer: this method register a consumer.
     * @param nickname: String: this is the consumer nickname.
     * @param id: String: this is de consumer id.
     * @param option: int: the consumer type. 
     * @return msj: String: a confirm message.
     */
    public String registerConsumer(String nickname , String id ,int option){

    	String msj = "";
        User objU = searchUser(nickname);

        if (objU != null) {
            msj = "the user exist";
        } else {
            Calendar bondingdate=dateActual();
            if(option==1){
                users.add(new Standard(nickname,id,bondingdate));
                msj = "Standard Consumer created";
            }
            else{
                users.add(new Premium(nickname,id,bondingdate));
                msj = "Premium Consumer created"; 
            }
        
        }
        return msj;

    
    }
    /**
     * registerSong: this method register a song.
     * @param nickname: String: this is the autor nickname.
     * @param name: String: this is the real song name.
     * @param url: String: this is the image url.
     * @param duration: int : the song duration.
     * @param album: String: the album. 
     * @param price: double: the price in dollars of the song. 
     * @param typeSong: int: the song type. 
     * @return msj: String: a confirm message.
     */
    public String registerSong(String nickname,String  name, String  url, int duration, String  album, double price, int  typeSong){

    	String msj = "song created";
        User user = searchUser(nickname);

        if (user == null) {
            msj = "the user doesn't exist";
        } else {
             if(user instanceof Artist){
               Audio song= searchAudio(name);
                 if(song!= null){
                     msj="the song exist";
                 }

                 else{
                    audios.add(new Song(name, url, duration, album, price, typeSong));
                    Artist artist = ( (Artist)(user) );
                    artist.getSongs().add(new Song(name, url, duration, album, price, typeSong));
                 }
             }
             else{
                msj="the user isnot a Artist";
             }
        }
        return msj;

    }
    /**
     * registerPodcast: this method register a podcast.
     * @param nickname: String: this is the autor nickname.
     * @param name: String: this is the real podcast name.
     * @param url: String: this is the image url.
     * @param duration: int : the podcast duration. 
     * @param description: String: the description of the podcast. 
     * @param typePodcast: int: the podcast type. 
     * @return msj: String: a confirm message.
     */
    public String registerPodcast(String nickname,String name, String  url, int duration, String description,  int typePodcast){
    	String msj = "podcast created";
        User user = searchUser(nickname);

        if (user == null) {
            msj = "the user doesn't exist";
        } else {

            if(user instanceof Creator){
               Audio podcast= searchAudio(name);
                if(podcast!= null){
                    msj="the podcast exist";
                } else{
                    audios.add(new Podcast(name, url, duration, description, typePodcast));
                    Creator creator = ( (Creator)(user) );
                    creator.getPodcasts().add(new Podcast(name, url, duration, description, typePodcast));
                }
             }
             else{
                msj="the user isnot a creator";
             }
        }
        return msj;

    }
     /**
     * registerPlaylist: this method register a playlist.
     * @param nickname: String: this is the user nickname.
     * @param name: String: this is de playlist name.
     * @param option: int: the playlist type. 
     * @return msj: String: a confirm message.
     */
    public String registerPlaylist(String nickname,String name,int option){

        String msj = "Playslist created";
        User user = searchUser(nickname);

        if (user == null) {
            msj = "the user doesn't exist";
        } else {
            if(user instanceof Standard){
                int [][]matriz=generateMatriz();
                String code= generateCode(option, matriz);
                Standard standard = ( (Standard)(user) );
                boolean validation = standard.addPlaylist(name, matriz, code, option);
                if(validation ==false){
                  msj="the playlist exist";
                }
          }
          else if(user instanceof Premium){
            int [][]matriz=generateMatriz();
            String code= generateCode(option, matriz);
            Premium premium = ( (Premium)(user) );
            boolean validation = premium.addPlaylist(name, matriz, code, option);
            if(validation ==false){
              msj="the playlist exist";
            }
          }
          else{
            msj="this user is not standard or premium";
          }
        
       }
       return msj;

    }
    /**
     * editAudioPlaylist: this method add a audio to playlist.
     * @param option: int: the playlist type. 
     * @param nickname: String: this is the user nickname.
     * @param namePlaylist: String: this is de playlist name.
     * @param audio: String: the audios name. 
     * @return msj: String: a confirm message.
     */
    public String editAudioPlaylist(int option,String nickname,String namePlaylist, String audio){
        String msj = ""; 
        Audio newAudio = searchAudio(audio);
        if(newAudio == null){
            msj = "this song dont exist";
        }
        else{
            int type;
            if(newAudio instanceof Song){
                type =1;
            }
            else{
                type =2;
            }
            User theUser = searchUser(nickname);
            if(theUser == null){
                msj = "this user dont exist"; 
            }
            else{
                if(option ==1){

                    if(theUser instanceof Standard){
                        Standard newStandart = ((Standard)(theUser));
                        msj = newStandart.addAudioPlaylist(namePlaylist, type, newAudio,audio); 
                    }
                    else if(theUser instanceof Premium){
                        Premium newPremium = ((Premium)(theUser));
                        msj = newPremium.addAudioPlaylist(namePlaylist,type,newAudio, audio);
                    }
                    else{
                        msj = "this user isnot premium or standard";
                    }
                }
                if(option == 2){

                    if(theUser instanceof Standard){
                        Standard newStandart = ((Standard)(theUser));
                        msj = newStandart.delateAudio(newAudio, namePlaylist, audio);
                    }
                    else if(theUser instanceof Premium){
                        Premium newPremium = ((Premium)(theUser));
                        msj = newPremium.delateAudio(newAudio, namePlaylist, audio);
                    }
                    else{
                        msj = "this user isnot premium or standard";
                    }

                }
            }
        }

        return msj;

    }

    public String sharePlaylist(String nickname, String namePlaylist){
        String code = ""; 
        User user = searchUser(nickname);

        if(user == null){
            code = "this user doesnt exist";
        } else{
            if(user instanceof Standard){
                Standard newStandart = ( (Standard)(user) );
                code = newStandart.sharePlaylist(namePlaylist)+ "\n" + newStandart.sharePlaylistMatriz(namePlaylist);
            }
            else if(user instanceof Premium){
                Premium newPremium = ( (Premium)(user) );
                code = newPremium.sharePlaylist(namePlaylist) + "\n" +  newPremium.sharePlaylistMatriz(namePlaylist);
            }
            else{
                code = "must enter a user type consumer"; 
            }
        }

        return code; 
    }
    public String playingAudio(String nickname, String audio){

        String msj = ""; 
        User user = searchUser(nickname);

        if(user == null){
            msj = "this user doesnt exist";
        }
        else{
            Audio newAudio = searchAudio(audio);
            if(newAudio == null){
                msj = "the audio doesnt exist";
            }
               else{
                if(user instanceof Standard){
                    Standard newStandart = ( (Standard)(user) );
                    msj = newStandart.play(newAudio);
                    updateState(newAudio);
                }
                else if(user instanceof Premium){
                    Premium newPremium = ( (Premium)(user) );
                    msj = newPremium.play(newAudio);
                    updateState(newAudio);
                }
                else{
                    msj = "must enter a user type consumer"; 
                }
            } 
        }

         

        return msj;

    }

    public void updateState(Audio audio){
    if(audio instanceof Song){
        Song song = ( (Song)(audio) );
        boolean val=false;
        for(int i=0;i<users.size() && !val;i++){
            if(users.get(i) instanceof Artist){
                Artist artist = ( (Artist) (users.get(i)) );
                if(artist.searchAudioAutor(song)){
                    artist.setTotalViews(artist.getTotalPLayedTime()+1);
                    artist.setTotalPLayedTime(song.getDuration()+artist.getTotalPLayedTime());
                    song.setView(song.getView()+1);
                    val=true;
                }
            }
        }

    }
    else if(audio instanceof Podcast){
       Podcast podcast = ( ( Podcast)(audio) );
           boolean val=false;
           for(int i=0;i<users.size() && !val;i++){
               if(users.get(i) instanceof Creator){
                   Creator creator = ( (Creator)(users.get(i)) );
                   if(creator.searchAudioAutor(podcast)){
                       creator.setTotalViews(creator.getTotalPLayedTime()+1);
                       creator.setTotalPLayedTime(podcast.getDuration()+creator.getTotalPLayedTime());
                       podcast.setView(podcast.getView()+1);
                       val=true;
                   }
               }
            }

        }
    }


    

}
