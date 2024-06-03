package com.training.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SongDto {

    private String name;
    private String artist;
    private String album;
    private String length;
    private String resourceId;
    private String year;


}
