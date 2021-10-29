package com.codingTest.urlShorteningService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDateTime;

@Entity
public class Url {
    @Id
    @GeneratedValue
    private long id;
    @Lob
    private String actualUrl;
    private String shortenUrl;
    private LocalDateTime dateCreated;
    private LocalDateTime dateExpired;
    private int numberOfRequestToShortUrl;


    public Url() {
    }


    public Url(long id, String actualUrl, String shortenUrl, LocalDateTime dateCreated, LocalDateTime dateExpired, int numberOfRequestToShortUrl, int numberOfShortenUrlAccessed) {
        this.id = id;
        this.actualUrl = actualUrl;
        this.shortenUrl = shortenUrl;
        this.dateCreated = dateCreated;
        this.dateExpired = dateExpired;
        this.numberOfRequestToShortUrl = numberOfRequestToShortUrl;
        this.numberOfShortenUrlAccessed = numberOfShortenUrlAccessed;
    }

    public int getNumberOfRequestToShortUrl() {
        return numberOfRequestToShortUrl;
    }

    public void setNumberOfRequestToShortUrl(int numberOfRequestToShortUrl) {
        this.numberOfRequestToShortUrl = numberOfRequestToShortUrl;
    }

    public int getNumberOfShortenUrlAccessed() {
        return numberOfShortenUrlAccessed;
    }

    public void setNumberOfShortenUrlAccessed(int numberOfShortenUrlAccessed) {
        this.numberOfShortenUrlAccessed = numberOfShortenUrlAccessed;
    }

    private int numberOfShortenUrlAccessed;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActualUrl() {
        return actualUrl;
    }

    public  void setActualUrl(String actualUrl) {
        this.actualUrl = actualUrl;
    }

    public String getShortenUrl() {
        return shortenUrl;
    }

    public void setShortenUrl(String shortenUrl) {
        this.shortenUrl = shortenUrl;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }




    public LocalDateTime getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(LocalDateTime dateExpired) {
        this.dateExpired = dateExpired;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", actualUrl='" + actualUrl + '\'' +
                ", shortenUrl='" + shortenUrl + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateExpired=" + dateExpired +
                '}';
    }
}
