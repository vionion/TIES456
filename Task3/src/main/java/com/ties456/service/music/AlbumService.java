package com.ties456.service.music;

import com.ties456.model.songs.Album;

import java.util.List;

/**
 * Created by V.Tsybulko on 30.09.2016.
 */
public interface AlbumService {

    List<Album> getAll();

    Album getById(long id);

    boolean isAlbumExist(long id);

    Album saveAlbum(Album album);

    void updateAlbum(Album album);

    void deleteAlbumById(long id);

    void deleteAllAlbums();
    
}
