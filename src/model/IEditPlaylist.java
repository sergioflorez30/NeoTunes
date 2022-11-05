package model;

public interface IEditPlaylist{

    public String addAudioPlaylist(String namePlaylist,int typeAudio, Audio audio, String audioName);
    public String delateAudio(Audio auido, String namePlaylist,String audioName );
    
}