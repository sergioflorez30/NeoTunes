package model;

public interface ICreatePlaylist{

    public boolean addPlaylist(String name, int[][] matriz, String code,int option);
    public Playlist searchPlaylist(String name);

    
}