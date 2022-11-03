package model;

import java.util.Calendar;
import java.util.ArrayList;


public class Standard extends Consumer{
    private ArrayList <Playlist> playlist;

    public Standard(String nickname, String id, Calendar bondingdate) {
        super(nickname, id, bondingdate);
        playlist= new ArrayList<Playlist>(20);
    }