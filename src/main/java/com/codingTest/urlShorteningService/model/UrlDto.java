package com.codingTest.urlShorteningService.model;

public class UrlDto {

    private String givenUrl;

    private String dateExpiredGiven;

    public UrlDto(String givenUrl, String dateExpiredGiven) {
        this.givenUrl = givenUrl;
        this.dateExpiredGiven = dateExpiredGiven;
    }

    public UrlDto() {

    }

    public String getGivenUrl() {
        return givenUrl;
    }

    public void setGivenUrl(String givenUrl) {
        this.givenUrl = givenUrl;
    }

    public String getDateExpiredGiven() {
        return dateExpiredGiven;
    }

    public void setDateExpiredGiven(String dateExpiredGiven) {
        this.dateExpiredGiven = dateExpiredGiven;
    }


}
