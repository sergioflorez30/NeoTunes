package model;

import java.util.Calendar;

public abstract class Consumer extends User {

    public Consumer(String nickname, String id,Calendar bondingdate) {
        super(nickname, id, bondingdate);
    
    }

    public abstract String sharePlaylistMatriz(String namePlaylist);
    public abstract String sharePlaylist(String namePlaylist);
    public abstract String printMatrix(int[][] matrix);

}