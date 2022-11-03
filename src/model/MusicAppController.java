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
}