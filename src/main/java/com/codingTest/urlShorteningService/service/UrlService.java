package com.codingTest.urlShorteningService.service;

import com.codingTest.urlShorteningService.model.Url;
import com.codingTest.urlShorteningService.model.UrlDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UrlService {
    public Url generateShortUrl(UrlDto urlDtoObj);

    public Url saveShortUrl(Url urlObj);

    public Url getShortUrl(String urlVal);

    public void removeShortUrl(Url urlVal);

    public List<Url> findAllUrls();

}
