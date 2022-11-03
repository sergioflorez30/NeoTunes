package model;

import java.util.Calendar;
import java.util.ArrayList;

public class Premium extends Consumer{

    private ArrayList <Playlist> playlist;

    public Premium(String nickname, String id, Calendar bondingdate) {
        super(nickname, id, bondingdate);
        playlist= new ArrayList<Playlist>();

    }