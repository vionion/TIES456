package com.ties456.service.music;

import com.ties456.model.songs.Song;

import java.util.List;

/**
 * Created by V.Tsybulko on 30.09.2016.
 */
public interface SongService {

    List<Song> getAll();

    Song getById(long id);

    boolean isSongExist(long id);

    Song saveSong(Song song);

    void updateSong(Song song);

    void deleteSongById(long id);

    void deleteAllSongs();
    
}
