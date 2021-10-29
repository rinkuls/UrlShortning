package com.codingTest.urlShorteningService.model;

import java.time.LocalDateTime;

public class SucessResponce {

    private String actualUrl;
    private String shortenValue;
    private LocalDateTime dateExpired;
    private Integer NumberOfUrlShortenServiceRequest;


    public SucessResponce() {
    }

    public SucessResponce(String actualUrl, String shortenValue, LocalDateTime dateExpired, Integer numberOfUrlShortenServiceRequest, Integer numberOfShortenUrlAccessed) {
        this.actualUrl = actualUrl;
        this.shortenValue = shortenValue;
        this.dateExpired = dateExpired;
        NumberOfUrlShortenServiceRequest = numberOfUrlShortenServiceRequest;
        NumberOfShortenUrlAccessed = numberOfShortenUrlAccessed;
    }

    public Integer getNumberOfShortenUrlAccessed() {
        return NumberOfShortenUrlAccessed;
    }

    public void setNumberOfShortenUrlAccessed(Integer numberOfShortenUrlAccessed) {
        NumberOfShortenUrlAccessed = numberOfShortenUrlAccessed;
    }

    private Integer NumberOfShortenUrlAccessed;


    public Integer getNumberOfUrlShortenServiceRequest() {
        return NumberOfUrlShortenServiceRequest;
    }

    public void setNumberOfUrlShortenServiceRequest(Integer numberOfUrlShortenServiceRequest) {
        NumberOfUrlShortenServiceRequest = numberOfUrlShortenServiceRequest;
    }


    public String getActualUrl() {
        return actualUrl;
    }

    public void setActualUrl(String actualUrl) {
        this.actualUrl = actualUrl;
    }

    public String getShortenValue() {
        return shortenValue;
    }

    public void setShortenValue(String shortenValue) {
        this.shortenValue = shortenValue;
    }

    public LocalDateTime getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(LocalDateTime dateExpired) {
        this.dateExpired = dateExpired;
    }
}
