package model; 

import java.util.ArrayList;

public class Playlist{

    private String name;
    private ArrayList <Audio> audios;
    private int matriz[][];
    private String code;
    private TypePlaylist typePlaylist;


    public Playlist(String name, int[][] matriz, String code,int option) {
        this.name = name;
        this.matriz = matriz;
        this.code = code;
        audios= new ArrayList<Audio>();
        switch(option){
            case 1:
            typePlaylist=TypePlaylist.SONG ;
            break;
            case 2:
            typePlaylist=TypePlaylist.PODCAST ;
            break;
            case 3:
            typePlaylist=TypePlaylist.SONGPODCAST;
            break;
        }
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<Audio> getAudios() {
        return audios;
    }


    public void setAudios(ArrayList<Audio> audios) {
        this.audios = audios;
    }


    public int[][] getMatriz() {
        return matriz;
    }


    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public TypePlaylist getTypePlaylist() {
        return typePlaylist;
    }


    public void setTypePlaylist(TypePlaylist typePlaylist) {
        this.typePlaylist = typePlaylist;
    }


    public int typePlaylist(){
        switch (typePlaylist) {
            case SONG:
                return 1;
            case PODCAST:
                return 2;
            case SONGPODCAST:
                return 3;
            default:
             return 0;
     }   
    }
    public boolean searchAudio(String name) {

        Audio obj = null;
        boolean search = false;
        if (audios != null) {
            for (int i = 0; i < audios.size() && !search; i++) {
                if (audios.get(i).getName().equalsIgnoreCase(name)) {
                    obj = audios.get(i);
                    search = true;
                }
            }
        }

        return search;

    }


}