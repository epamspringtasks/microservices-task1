package com.training.repository;

import com.training.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    @Query(value = " SELECT * FROM SONG WHERE resource_id = ?1 ", nativeQuery = true)
    Song findSongByResourceID(Integer id);

    @Modifying
    @Query(value = " DELETE FROM SONG WHERE resource_id in (:id) ", nativeQuery = true)
    void deleteAllSongsById(@Param("id") List<Integer> id);
}
