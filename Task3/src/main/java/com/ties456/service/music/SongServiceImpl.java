package com.ties456.service.music;

import com.ties456.model.songs.Song;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by V.Tsybulko on 30.09.2016.
 */
@Service("songService")
@Transactional
public class SongServiceImpl implements SongService {

    @Override
    public List<Song> getAll() {
        return null;
    }

    @Override
    public Song getById(long id) {
        return null;
    }

    @Override
    public boolean isSongExist(long id) {
        return false;
    }

    @Override
    public Song saveSong(Song song) {
        return null;
    }

    @Override
    public void updateSong(Song song) {

    }

    @Override
    public void deleteSongById(long id) {

    }

    @Override
    public void deleteAllSongs() {

    }
}