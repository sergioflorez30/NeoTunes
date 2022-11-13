package model;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;


public class MusicAppController {

    private ArrayList <User> users;
    private ArrayList <Audio> audios;
    private ArrayList<Shop> shops;
    public static final int ROWS = 6; 
    public static final int COLUMNS = 6; 

    public MusicAppController() {
        users= new ArrayList<User>();
        audios= new ArrayList<Audio>();
        shops = new ArrayList<Shop>(); 
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

    public String buySong(String nickname, String nameSong){

        String msj = ""; 
        User user = searchUser(nickname);

        if(user == null){
            msj = "this user doesnt exist";
        }
        else{
            Audio newAudio = searchAudio(nameSong);
            if(newAudio == null){
                msj = "the audio doesnt exist";
            }

            else{
                if(newAudio instanceof Song){
                    Song newSong = ( (Song)(newAudio) );
                    if(user instanceof Standard){
                        int numBuys = countBuysForUser(nickname);
                        if(numBuys<100){
                            Shop newShop = new Shop(dateActual(), nickname, nameSong); 
                            shops.add(newShop); 
                            newSong.setNumberSales(newSong.getNumberSales()+1);

                        }else{
                            msj = "the purchasing limit has been reached "; 
                        }

                    } else if( user instanceof Premium){
                        Shop newShop = new Shop(dateActual(), nickname, nameSong); 
                        shops.add(newShop); 
                        newSong.setNumberSales(newSong.getNumberSales()+1);

                    }
                }
                else if(newAudio instanceof Podcast){
                    msj = "is not posible buy a podcast";
                    
                }
                else{
                    msj = "must enter a user type consumer"; 
                }
            } 

        }
        return msj; 

    }

    public int countBuysForUser(String nickname){
        int count =0; 
        if(shops.size() != 0){

            for(int i = 0; i <shops.size(); i++ ){
                shops.get(i).getNickname().equalsIgnoreCase(nickname);
                count++;
            }

        }
        return count; 

    } 
    public String infoTotalViews(){
        String msj = ""; 
        int totalViews=0;
        if(audios.size()!=0){
            for(int i=0;i<audios.size();i++){
            totalViews+=audios.get(i).getView();
            }
        }
        msj = "the total views is  " + totalViews ;
        return msj;
    }

    public String infoMostViewSong(String nickname){
        String msj = ""; 
        User user = searchUser(nickname);

        if(user == null){
            msj = "this user doesnt exist";
        }
        else{

            if(user instanceof Standard){
                Standard standard = ((Standard)(user)); 
                 msj=mostSongViews() + standard.mostSongViews();
            }
            else if(user instanceof Premium){
                Premium premium = ((Premium)(user)); 
                 msj=mostSongViews() + premium.mostSongViews();

            }
            else{
                msj="you must enter a user type consumer";
            }
        }
    return msj;

    }

    public String mostSongViews(){
        String msj ="";
        int[]  geners ={0,0,0,0};
        int position =0;
        if(audios.size()!=0){
            for(int i =0; i< audios.size(); i++){
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
            int mayor =0;
            for(int i =0; i<4; i++){
                if(geners[i]> mayor){
                    position =i; 
                }
            }
            switch(position){
             case 0:
             msj="\n the most listened to genre  rock \n"+"views: "+geners[position];
             break;
             case 1:
             msj="\n the most listened to genre: pop \n"+"views: "+geners[position];
             break;
             case 2:
             msj="\n the most listened to genre : trap \n"+"views: "+geners[position];
             break;
             case 3:
             msj="\n the most listened to genre: house \n"+"views: "+geners[position];
             break;
             case 4:
             msj="the dont exist song";
             break;
            }

        }
        else{
            msj = "the plataform dont have audios";
        }
        return msj; 
    }

    public String infoMostViewPodcast(String nickname){
        String msj = ""; 
        User user = searchUser(nickname);

        if(user == null){
            msj = "this user doesnt exist";
        }
        else{

            if(user instanceof Standard){
                Standard standard = (Standard) user; 
                 msj=mostPodcastViews() + standard.mostPodcastViews();
            }
            else if(user instanceof Premium){
                Premium premium = ( Premium) user; 
                msj=mostPodcastViews() + premium.mostPodcastViews();

            }
            else{
                msj="you must enter a user type consumer";
            }
        }
        return msj;
    }

    public String mostPodcastViews(){
    String msj="";
    int [] geners= {0,0,0,0};
    int position=4;
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
         msj="the most listened to genre: Politic \n"+"views: "+geners[position];
         break;
         case 1:
         msj="the most listened to genre: Entertaiment \n"+"views: "+geners[position];
         break;
         case 2:
         msj="the most listened to genre : Fashion \n"+"views: "+geners[position];
         break;
         case 3:
         msj="the most listened to genre: Videogame \n"+"views: "+geners[position];
         break;
         case 4:
         msj="the dont exist podcast";
         break;
       }
       
     }
     else{
       msj="the plataform dont have audios";
     }
    return msj;
   }
   public String topArtist(){
    String msj="";
    int top1 = 0;
    int top2 = 0;
    int top3 = 0;
    int top4 = 0;
    int top5 = 0;
    String name1 = " ";
    String name2 = " ";
    String name3 = " ";
    String name4 = " ";
    String name5 = " "; 
    if(users.size()!=0){
        for(int i = 0; i<users.size(); i++){
           if(users.get(i) instanceof Artist){
            Artist obj = (Artist) users.get(i); 
            if( obj.getTotalViews() > top1){
                
                top5 = top4;
                top4= top3;
                top3= top2;
                top2 = top1;
                top1 = obj.getTotalViews();
                name5 = name4;
                name4 = name3;
                name3 = name2;
                name2= name1; 
                name1 =obj.getName(); 
                
            } else if(obj.getTotalViews() > top2){
                
                top5 = top4;
                top4= top3;
                top3= top2;
                top2 = obj.getTotalViews(); 
                name5 = name4;
                name4 = name3;
                name3 = name2;
                name2= obj.getName();

            }else if( obj.getTotalViews() > top3){

                top5 = top4;
                top4= top3;
                top3= obj.getTotalViews(); 
                name5 = name4;
                name4 = name3;
                name3 = obj.getName();

            } else if( obj.getTotalViews() > top4){

                top5 = top4;
                top4= obj.getTotalViews(); 
                name5 = name4;
                name4 = obj.getName();

            }else if( obj.getTotalViews() > top5){

                top5 = obj.getTotalViews();
                name5 =  obj.getName(); 
        
            }
        }

      if(top1==0){
        msj="there are no registered Artist";
      }
      else{
        msj = "top 5 artist \n" +
        "1."+ name1 + ": " + top1 + "\n"+
        "2."+ name2 + ": " + top2 + "\n"+
        "3."+ name3 + ": " + top3 + "\n"+
        "4."+ name4 + ": " + top4 + "\n"+
        "5."+ name5 + ": " + top5 + "\n"; 
      }

        }
       }
    else{
        msj="there are no registered users";
    }
    return msj;
   }
   public String topCreator(){
    String msj="";
    int top1 = 0;
    int top2 = 0;
    int top3 = 0;
    int top4 = 0;
    int top5 = 0;
    String name1 = " ";
    String name2 = " ";
    String name3 = " ";
    String name4 = " ";
    String name5 = " "; 
       if(users.size()!=0){
        for(int i = 0; i<users.size(); i++){
           if(users.get(i) instanceof Creator){
            Creator obj = (Creator) users.get(i); 
            if( obj.getTotalViews() > top1){
                
                top5 = top4;
                top4= top3;
                top3= top2;
                top2 = top1;
                top1 = obj.getTotalViews();
                name5 = name4;
                name4 = name3;
                name3 = name2;
                name2= name1; 
                name1 =obj.getName(); 
                
            } else if(obj.getTotalViews() > top2){
                
                top5 = top4;
                top4= top3;
                top3= top2;
                top2 = obj.getTotalViews(); 
                name5 = name4;
                name4 = name3;
                name3 = name2;
                name2= obj.getName();

            }else if( obj.getTotalViews() > top3){

                top5 = top4;
                top4= top3;
                top3= obj.getTotalViews(); 
                name5 = name4;
                name4 = name3;
                name3 = obj.getName();

            } else if( obj.getTotalViews() > top4){

                top5 = top4;
                top4= obj.getTotalViews(); 
                name5 = name4;
                name4 = obj.getName();

            }else if( obj.getTotalViews() > top5){

                top5 = obj.getTotalViews();
                name5 =  obj.getName(); 
        
            }
        }

      if(top1==0){
        msj="there are no registered Creator";
      }
      else{
        msj = "top 5 Creator \n" +
        "1."+ name1 + ": " + top1 + "\n"+
        "2."+ name2 + ": " + top2 + "\n"+
        "3."+ name3 + ": " + top3 + "\n"+
        "4."+ name4 + ": " + top4 + "\n"+
        "5."+ name5 + ": " + top5 + "\n"; 
      }

        }
       }
    else{
        msj="there are no registered users";
    }
    return msj;
   }

   public String topSong(){
    String msj = ""; 
    int top1 = 0;
    int top2 = 0;
    int top3 = 0;
    int top4 = 0;
    int top5 = 0;
    int top6 = 0;
    int top7 = 0;
    int top8 = 0;
    int top9 = 0;
    int top10 = 0;
    String type1 = "";
    String type2 = "";
    String type3 = "";
    String type4 = "";
    String type5 = "";
    String type6 = "";
    String type7 = "";
    String type8 = "";
    String type9 = "";
    String type10 = "";
    String name1 = "";
    String name2 = "";
    String name3 = "";
    String name4 = "";
    String name5 = "";
    String name6 = "";
    String name7 = "";
    String name8 = "";
    String name9 = "";
    String name10 = ""; 

    if(audios.size()!=0){
        for(int i = 0; i<audios.size(); i++){
           if(audios.get(i) instanceof Song){
            Song obj = (Song) audios.get(i); 
            if( obj.getView() > top1){
                
                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = top4;
                top4= top3;
                top3= top2;
                top2 = top1;
                top1 = obj.getView();
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 = name4;
                name4 = name3;
                name3 = name2;
                name2= name1; 
                name1 =obj.getName(); 
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = type4;
                type4 = type3;
                type3 = type2;
                type2 = type1;
                type1 = obj.typeStringSong(); 
                
            } else if(obj.getView() > top2){
                
                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = top4;
                top4= top3;
                top3= top2;
                top2 = obj.getView(); 
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 = name4;
                name4 = name3;
                name3 = name2;
                name2= obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = type4;
                type4 = type3;
                type3 = type2;
                type2 = obj.typeStringSong();

            }else if( obj.getView() > top3){

                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = top4;
                top4= top3;
                top3= obj.getView(); 
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 = name4;
                name4 = name3;
                name3 = obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = type4;
                type4 = type3;
                type3 = obj.typeStringSong(); 


            } else if( obj.getView() > top4){

                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = top4;
                top4= obj.getView(); 
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 = name4;
                name4 = obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = type4;
                type4 = obj.typeStringSong();


            }else if( obj.getView() > top5){

                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = obj.getView();
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 =  obj.getName(); 
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = obj.typeStringSong();
        
            } else if(obj.getView() > top6){
                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = obj.getView();
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = obj.typeStringSong();

            } else if( obj.getView()>top7){
                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= obj.getView();
                name10= name9;
                name9= name8;
                name8= name7;
                name7= obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = obj.typeStringSong();
            } else if(obj.getView()> top8){
                top10=top9; 
                top9 = top8;
                top8= obj.getView();
                name10= name9;
                name9= name8;
                name8= obj.getName();
                type10 = type9;
                type9 = type8;
                type8= obj.typeStringSong(); 
            } else if(obj.getView()> top9){
                top10=top9; 
                top9 = obj.getView();
                name10= name9;
                name9= obj.getName();
                type10 = type9;
                type9 = obj.typeStringSong();
            } else if(obj.getView()>10){
                top10 = obj.getView();
                name10 = obj.getName();
                type10 = obj.typeStringSong(); 
            }
        }

      if(top1==0){
        msj="there are not registered a song :( ";
      }
      else{
        msj = "top 10 songs \n" +
        "1."+ name1 + ": views " + top1 + " genre : "+ type1 + "\n"+
        "2."+ name2 + ": views " + top2 + " genre : "+ type2 +"\n"+ 
        "3."+ name3 + ": views " + top3 + " genre : "+ type3 +"\n"+
        "4."+ name4 + ": views " + top4 + " genre : "+ type4 +"\n"+
        "5."+ name5 + ": views " + top5 + " genre : "+ type5 +"\n" +
        "6."+ name6 + ": views " + top6 + " genre : "+ type6 +"\n" +
        "7."+ name7 + ": views " + top7 + " genre : "+ type7 +"\n" +
        "8."+ name8 + ": views " + top8 + " genre : "+ type8 +"\n" +
        "9."+ name9 + ": views " + top9 + " genre : "+ type9 +"\n" +
        "10."+ name10 + ": views " + top10 + " genre : "+ type10 +"\n";

      }

        }
       }
    else{
        msj="there are no registered songs";
    }
    return msj;

   }

   public String topPodcast(){
    String msj = ""; 
    int top1 = 0;
    int top2 = 0;
    int top3 = 0;
    int top4 = 0;
    int top5 = 0;
    int top6 = 0;
    int top7 = 0;
    int top8 = 0;
    int top9 = 0;
    int top10 = 0;
    String type1 = "";
    String type2 = "";
    String type3 = "";
    String type4 = "";
    String type5 = "";
    String type6 = "";
    String type7 = "";
    String type8 = "";
    String type9 = "";
    String type10 = "";
    String name1 = "";
    String name2 = "";
    String name3 = "";
    String name4 = "";
    String name5 = "";
    String name6 = "";
    String name7 = "";
    String name8 = "";
    String name9 = "";
    String name10 = ""; 

    if(audios.size()!=0){
        for(int i = 0; i<audios.size(); i++){
           if(audios.get(i) instanceof Podcast){
            Podcast obj = (Podcast) audios.get(i); 
            if( obj.getView() > top1){
                
                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = top4;
                top4= top3;
                top3= top2;
                top2 = top1;
                top1 = obj.getView();
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 = name4;
                name4 = name3;
                name3 = name2;
                name2= name1; 
                name1 =obj.getName(); 
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = type4;
                type4 = type3;
                type3 = type2;
                type2 = type1;
                type1 = obj.typeStringPodcast(); 
                
            } else if(obj.getView() > top2){
                
                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = top4;
                top4= top3;
                top3= top2;
                top2 = obj.getView(); 
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 = name4;
                name4 = name3;
                name3 = name2;
                name2= obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = type4;
                type4 = type3;
                type3 = type2;
                type2 = obj.typeStringPodcast();

            }else if( obj.getView() > top3){

                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = top4;
                top4= top3;
                top3= obj.getView(); 
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 = name4;
                name4 = name3;
                name3 = obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = type4;
                type4 = type3;
                type3 = obj.typeStringPodcast(); 


            } else if( obj.getView() > top4){

                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = top4;
                top4= obj.getView(); 
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 = name4;
                name4 = obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = type4;
                type4 = obj.typeStringPodcast();


            }else if( obj.getView() > top5){

                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = top5;
                top5 = obj.getView();
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= name5;
                name5 =  obj.getName(); 
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = type5;
                type5 = obj.typeStringPodcast();
        
            } else if(obj.getView() > top6){
                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= top6;
                top6 = obj.getView();
                name10= name9;
                name9= name8;
                name8= name7;
                name7= name6;
                name6= obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = type6;
                type6 = obj.typeStringPodcast();

            } else if( obj.getView()>top7){
                top10=top9; 
                top9 = top8;
                top8= top7;
                top7= obj.getView();
                name10= name9;
                name9= name8;
                name8= name7;
                name7= obj.getName();
                type10 = type9;
                type9 = type8;
                type8= type7;
                type7 = obj.typeStringPodcast();
            } else if(obj.getView()> top8){
                top10=top9; 
                top9 = top8;
                top8= obj.getView();
                name10= name9;
                name9= name8;
                name8= obj.getName();
                type10 = type9;
                type9 = type8;
                type8= obj.typeStringPodcast(); 
            } else if(obj.getView()> top9){
                top10=top9; 
                top9 = obj.getView();
                name10= name9;
                name9= obj.getName();
                type10 = type9;
                type9 = obj.typeStringPodcast();
            } else if(obj.getView()>10){
                top10 = obj.getView();
                name10 = obj.getName();
                type10 = obj.typeStringPodcast(); 
            }
        }

      if(top1==0){
        msj="there are not registered a podcast :( ";
      }
      else{
        msj = "top 10 podcasts \n" +
        "1."+ name1 + ": views " + top1 + " genre : "+ type1 + "\n"+
        "2."+ name2 + ": views " + top2 + " genre : "+ type2 +"\n"+ 
        "3."+ name3 + ": views " + top3 + " genre : "+ type3 +"\n"+
        "4."+ name4 + ": views " + top4 + " genre : "+ type4 +"\n"+
        "5."+ name5 + ": views " + top5 + " genre : "+ type5 +"\n" +
        "6."+ name6 + ": views " + top6 + " genre : "+ type6 +"\n" +
        "7."+ name7 + ": views " + top7 + " genre : "+ type7 +"\n" +
        "8."+ name8 + ": views " + top8 + " genre : "+ type8 +"\n" +
        "9."+ name9 + ": views " + top9 + " genre : "+ type9 +"\n" +
        "10."+ name10 + ": views " + top10 + " genre : "+ type10 +"\n";

      }

        }
       }
    else{
        msj="there are no registered podcasts ";
    }
    return msj;


   }
   public String infoSongsSold(){
    String msj = ""; 
    int countRock = 0;
    int countPop =0;
    int countTrap =0;
    int countHouse =0; 
    if(audios.size()!=0){
            for(int i =0; i< audios.size(); i++){
                if(audios.get(i) instanceof Song){
                    Song song = ( (Song)(audios.get(i)) );
                    switch(song.typeSong()){
                     case 1:
                      countRock += song.getNumberSales(); 
                      break;
                     case 2:
                     countPop += song.getNumberSales(); 
                      break;
                     case 3:
                     countTrap += song.getNumberSales(); 
                      break;
                     case 4:
                     countHouse += song.getNumberSales(); 
                      break;

                     default:
                      break;
                   }

                }
            }
        }else{
             msj="the plataform dont have audios";
        }

        msj = "the number sales for genre is: \n" +
            "Rock: " + countRock + "\n"+
            "Pop: " +  countPop + "\n"+
            "Trap: " + countTrap +"\n"+
            "House: "+ countHouse + "\n"; 
        return msj; 

   }

   public String totalSalesSongs(){
    String msj = ""; 
    double totalSales = 0.0; 
    if(audios.size()!=0){
            for(int i =0; i< audios.size(); i++){
                if(audios.get(i) instanceof Song){
                    Song song = ( (Song)(audios.get(i)) );
                    totalSales += song.getNumberSales() *  song.getPrice(); 
                }
            }
        }else{
            msj="the plataform dont have audios";
        }
        msj = " the total sales value of the songs is... " + totalSales; 
        return msj; 
   }
   public String mostSoldSong(){
    String msj = ""; 
    int solds =0;
    String name = "";
    double totalSales =0.0; 

    if(audios.size() !=0){
        for(int i =0; i< audios.size(); i++){
            if(audios.get(i) instanceof Song){
                Song song = ( (Song)(audios.get(i)) );
                if(song.getNumberSales() > solds){
                    solds = song.getNumberSales();
                    name = song.getName(); 
                    totalSales = song.getNumberSales() * song.getPrice(); 
                }
            }
        }

    } else{
        msj = "the plataform dont have audios"; 
      }

      msj = "the most selling song is  " +  name + " to these sales " + solds + " and the total sales value " + totalSales; 
      return msj; 
   }


}
