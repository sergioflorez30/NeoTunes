package model;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;


public class MusicAppController {

    private ArrayList <User> users;
    private ArrayList <Audio> audios;

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
                    artist.getSongs().add((Song)audios.get(-1));
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
                    creator.getPodcasts().add((Podcast)audios.get(-1));
                }
             }
             else{
                msj="the user isnot a creator";
             }
        }
        return msj;

    }
}