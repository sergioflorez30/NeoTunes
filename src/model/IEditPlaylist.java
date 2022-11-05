package model;
//* */
public interface IEditplaylist{

    public String addAudioPlaylist(String namePlaylist,int typeAudio, Audio audio);
    public boolean searchAudioPlaylist(Audio audio,Playlist playlist);
    public String eliminateAudio(Audio auido, String namePlaylist );
    
}