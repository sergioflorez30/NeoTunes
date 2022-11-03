package model;

import java.util.Calendar;
import java.util.ArrayList;


public class Standard extends Consumer{
    private ArrayList <Playlist> playlist;
    private final int SIZE = 20; 

    public Standard(String nickname, String id, Calendar bondingdate) {
        super(nickname, id, bondingdate);
        playlist= new ArrayList<Playlist>(20);
    }
}