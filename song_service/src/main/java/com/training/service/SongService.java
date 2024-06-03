package com.training.service;

import com.training.model.Song;
import com.training.model.SongDto;
import com.training.repository.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class SongService {


    SongRepository songRepository;

    @Autowired
    SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Transactional
    public Song store(SongDto songDto) throws IOException {
        Song song = new Song(songDto.getName(), songDto.getArtist(), songDto.getAlbum(), songDto.getLength(), songDto.getResourceId(), songDto.getYear());
        return songRepository.save(song);
    }

    public Song getSongMetadata(Integer id) {
        return songRepository.findSongByResourceID(id);
    }

    @Transactional
    public void removeSongs(List<Integer> ids) {
        songRepository.deleteAllSongsById(ids);
    }


}
