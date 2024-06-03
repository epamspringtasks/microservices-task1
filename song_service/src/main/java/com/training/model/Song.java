package com.training.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "SONG")
@NoArgsConstructor
public class Song {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    public Song(String name, String artist, String album, String length, String resourceId, String year) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.length = length;
        this.resourceId = resourceId;
        this.year = year;
    }

    private String artist;
    private String album;

    private String length;
    private String resourceId;

    private String year;


}
