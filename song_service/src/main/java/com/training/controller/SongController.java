package com.training.controller;

import com.training.model.Song;
import com.training.model.SongDto;
import com.training.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/")
public class SongController {

    @Autowired
    SongService songService;

    @Transactional
    @PostMapping(path = "songs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveSongMetadata(@RequestBody SongDto song) {
        try {
            Song savedSong = songService.store(song);

            return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("id", savedSong.getId().toString()));
        } catch (IOException e) {
            throw new RuntimeException("Exception while saving the Song Metadata. ");
        }
    }


    @GetMapping(path = "songs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Song> getSongMetadata(@PathVariable Integer id) {
        try {
            Song songMetadata = songService.getSongMetadata(id);
            if (songMetadata == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(songMetadata);
        } catch (Exception e) {
            throw new RuntimeException("Exception while fetching the Song Metadata. ");
        }
    }

    @Transactional
    @DeleteMapping(path = "songs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteSongs(@RequestParam(value = "id") List<Integer> id) {
        songService.removeSongs(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id);
    }
}
