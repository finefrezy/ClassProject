package com.elife.classproject.player;

import java.io.Serializable;

/**
 * Created by tzhang on 2016/9/15.
 *
 * Parceable与Serializable课后去看
 */


public class SongModel implements Serializable{


    private String fileName;
    private String title;
    private int duration;
    private String singer;
    private String album;
    private String year;
    private String type;
    private String size;
    private String fileUrl;

    private String albumImagePath;

    public SongModel() {
    }

    public SongModel(String album, int duration, String fileName, String fileUrl, String singer, String size, String title, String type, String year, String albumImagePath) {
        this.album = album;
        this.duration = duration;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.singer = singer;
        this.size = size;
        this.title = title;
        this.type = type;
        this.year = year;
        this.albumImagePath = albumImagePath;
    }

    
    @Override
    public String toString() {
        return "SongModel{" +
                "album='" + album + '\'' +
                ", fileName='" + fileName + '\'' +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", singer='" + singer + '\'' +
                ", year='" + year + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }

    public String getAlbumImagePath() {
        return albumImagePath;
    }

    public void setAlbumImagePath(String albumImagePath) {
        this.albumImagePath = albumImagePath;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
