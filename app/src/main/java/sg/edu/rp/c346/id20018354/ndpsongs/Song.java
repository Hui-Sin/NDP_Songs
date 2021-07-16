package sg.edu.rp.c346.id20018354.ndpsongs;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String songTitle;
    private String singers;
    private int year;
    private String stars;

    public Song( int id, String songTitle,String singers, int year, String stars ) {
        this.id = id;
        this.songTitle = songTitle;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int getId() {  return id;  }

    public String getSongTitle() { return songTitle; }
    public String getSingers() { return singers; }
    public int getYear() {  return year;  }
    public String getStars() {  return stars;  }

    public void setSongContent(String songTitle,String singers, int year, String stars) {
        this.songTitle = songTitle;
        this.singers = singers;
        this.year = year;
        this.stars = stars;;
    }

    @Override
    public String toString() { return songTitle +"\n"+ singers
                                +"\n"+year+"\n"+stars;  }

}