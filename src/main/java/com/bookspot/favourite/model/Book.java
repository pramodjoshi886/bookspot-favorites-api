package com.bookspot.favourite.model;

public class Book {

    private String id;
    private VolumeInfo volumeInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", volumeInfo=" + volumeInfo +
                '}';
    }
}
