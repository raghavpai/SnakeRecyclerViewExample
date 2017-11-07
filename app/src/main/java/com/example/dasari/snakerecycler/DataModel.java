package com.example.dasari.snakerecycler;

/**
 * Created by Dasari on 25-10-2017.
 */

public class DataModel {

    private String mNumber;
    private String mUUID;
    private String mImageUrl;
    private TileType mTileType;

    public DataModel(String mNumber, String mUUID, String mImageUrl, TileType mTileType) {
        this.mNumber = mNumber;
        this.mUUID = mUUID;
        this.mImageUrl = mImageUrl;
        this.mTileType = mTileType;
    }

    public enum TileType {
        PLUS,
        REGULAR
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public String getUUID() {
        return mUUID;
    }

    public void setUUID(String mUUID) {
        this.mUUID = mUUID;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public TileType getTileType() {
        return mTileType;
    }

    public void setTileType(TileType mTileType) {
        this.mTileType = mTileType;
    }

}
