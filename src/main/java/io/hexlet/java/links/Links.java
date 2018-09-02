package io.hexlet.java.links;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class Links {

    @Id
    private ObjectId _id;
    private String longUrl;
    private String shortUrl;
    private Date createdDate;

    public Links(ObjectId _id, String longUrl, String shortUrl, Date createdDate) {
        this._id = _id;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.createdDate = createdDate;
    }

    public String getId() {
        return _id.toHexString();
    }

    public String getLongUrl() {
        return longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
