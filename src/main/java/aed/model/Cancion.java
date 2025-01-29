package aed.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String artist;
    private int count;

    @OneToMany(mappedBy = "song")
    private List<CancionReproducida> songRecords;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CancionReproducida> getSongRecords() {
        return songRecords;
    }

    public void setSongRecords(List<CancionReproducida> songRecords) {
        this.songRecords = songRecords;
    }
}