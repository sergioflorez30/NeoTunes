package model;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;


public class MusicApp {

    private ArrayList <User> users;
    private ArrayList <Audio> audios;

    public MusicApp() {
        users= new ArrayList<User>();
        audios= new ArrayList<Audio>();
    }