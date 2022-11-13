package model; 

import java.util.Calendar;

public class Shop{

	 private Calendar date;
	 private String nickname;
	 private String nameSong; 

	public Shop(Calendar date, String nickname, String nameSong){

		this.date = date;
		this.nickname = nickname;
		this.nameSong = nameSong; 

	}

	public String getNickname() {
        return nickname;
    }

    public String getNameSong() {
        return nameSong;
    }

    public Calendar getDate(){
    	return date; 
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }


}