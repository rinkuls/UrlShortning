package com.codingTest.urlShorteningService.service;

import com.codingTest.urlShorteningService.model.Url;
import com.codingTest.urlShorteningService.model.UrlDto;
import com.codingTest.urlShorteningService.repo.UrlRepository;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ListIterator;

@Component
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository urlRepository;
    Url urlAfterSave = null;

    @Override
    public Url generateShortUrl(UrlDto urlVal) {


        boolean flag = false;
        if (StringUtils.isNotEmpty(urlVal.getGivenUrl())) {
            List<Url> listOfExistingUrls = findAllUrls();
            if ((!CollectionUtils.isEmpty(listOfExistingUrls))) {
                ListIterator<Url> listIterator = listOfExistingUrls.listIterator();
                while (listIterator.hasNext()) {
                    Url urlToUpdate = listIterator.next();
                    if (urlToUpdate.getActualUrl().equals(urlVal.getGivenUrl())) {

                        urlToUpdate.setNumberOfRequestToShortUrl(urlToUpdate.getNumberOfRequestToShortUrl() + 1);
                        urlAfterSave = urlRepository.saveAndFlush(urlToUpdate);
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    urlAfterSave = createAndSaveShortUrl(urlVal);
                }
            } else {
                urlAfterSave = createAndSaveShortUrl(urlVal);
            }
            if (urlAfterSave != null)
                return urlAfterSave;

            return null;
        }
        return null;
    }


    private Url createAndSaveShortUrl(UrlDto urlVal) {
        String shortUrlValue = shortenUrl(urlVal.getGivenUrl());
        Url urlSave = new Url();
        urlSave.setActualUrl(urlVal.getGivenUrl());
        urlSave.setDateCreated(LocalDateTime.now());
        urlSave.setDateExpired(getExpDate(urlVal.getDateExpiredGiven(), urlSave.getDateCreated()));
        urlSave.setShortenUrl(shortUrlValue);
        urlSave.setNumberOfRequestToShortUrl(1);
        urlAfterSave = saveShortUrl(urlSave);

        return urlAfterSave;
    }

    private String shortenUrl(String givenUrl) {

        String url = "";
        LocalDateTime time = LocalDateTime.now();
        url = Hashing.murmur3_32().hashString(givenUrl.concat(time.toString()), StandardCharsets.UTF_8).toString();

        return url;
    }

    private LocalDateTime getExpDate(String expDate, LocalDateTime givenDate) {

        if (StringUtils.isBlank(expDate)) {
            return givenDate.plusSeconds(300);
        }

        LocalDateTime expirationDateToRet = LocalDateTime.parse(expDate);
        return expirationDateToRet;

    }


    @Override
    public Url saveShortUrl(Url url) {
        Url urlToRet = urlRepository.save(url);
        return urlToRet;
    }

    @Override
    public Url getShortUrl(String url) {
        Url urlToRet = urlRepository.findByShortenUrl(url);
        urlToRet.setNumberOfShortenUrlAccessed(urlToRet.getNumberOfShortenUrlAccessed() + 1);
        urlRepository.saveAndFlush(urlToRet);
        return urlToRet;

    }

    @Override
    public void removeShortUrl(Url url) {
        urlRepository.delete(url);
    }

    @Override
    public List<Url> findAllUrls() {
        return urlRepository.findAll();
    }



}
