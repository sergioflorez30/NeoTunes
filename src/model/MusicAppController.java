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

	public Calendar dateActual(){
        
        Calendar calendar=new GregorianCalendar(2022,Calendar.NOVEMBER,8);
        return calendar ;
         
    }

    public int[][] generateMatriz(){

        int matriz[][]=new int[ROWS][COLUMNS];

        for(int i=0;i<ROWS;i++){
            for(int j=0;i<COLUMNS;i++){
                matriz[i][j]=generateNumber();
            }
        }

        return matriz;

    }
    public int generateNumber(){

        int num=0;
        Random r= new Random();
        num= (int) (r.nextInt()* 9) + 0; 
       
        return num;
    }
    public String generateCode(int option,int[][]matriz){
        String code=null;
        switch(option){
            case 1:
             for(int i=5;i<0;i--){
                code+=matriz[i][0];
             }
             for(int j=1, h=1;j>4 && h>4;j++,h++){
                code+=matriz[j][h];
             }
             for(int k=5;k<0;k--){
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
             for(int k=5;k<0;k--){
                code+=matriz[k][3];
             }
             for(int u=3;u>5;u++){
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
}