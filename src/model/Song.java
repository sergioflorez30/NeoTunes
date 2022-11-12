package model; 

public class Song extends Audio{

    private String album;
    private double price;
    private int numberSales;
    private TypeSong typeSong;
  

    public Song(String name, String url, int duration,String album, double price, int option){
        super(name,url,duration);
        this.album = album;
        this.price = price;
        this.numberSales = 0;

        switch(option){
            case 1:
            typeSong=TypeSong.ROCK;
            case 2:
            typeSong=TypeSong.POP;
            case 3:
            typeSong=TypeSong.TRAP;
            case 4:
            typeSong=TypeSong.HOUSE;

        }
    }


    public String getAlbum() {
        return album;
    }


    public void setAlbum(String album) {
        this.album = album;
    }


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public int getNumberSales() {
        return numberSales;
    }


    public void setNumberSales(int numberSales) {
        this.numberSales = numberSales;
    }


    public TypeSong getTypeSong() {
        return typeSong;
    }


    public void setTypeSong(TypeSong typeSong) {
        this.typeSong = typeSong;
    }

}