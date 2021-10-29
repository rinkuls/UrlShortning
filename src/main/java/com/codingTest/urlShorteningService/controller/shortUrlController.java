package com.codingTest.urlShorteningService.controller;

import com.codingTest.urlShorteningService.model.ErrorReponce;
import com.codingTest.urlShorteningService.model.SucessResponce;
import com.codingTest.urlShorteningService.model.Url;
import com.codingTest.urlShorteningService.model.UrlDto;
import com.codingTest.urlShorteningService.service.UrlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class shortUrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/generateShortUrl")
    public ResponseEntity<?> createShortValue(@RequestBody UrlDto urlVal) {
        Url urlSave = null;


        urlSave = urlService.generateShortUrl(urlVal);


        if (urlSave != null) {

            SucessResponce urlResponse = new SucessResponce();
            urlResponse.setActualUrl(urlSave.getActualUrl());
            urlResponse.setDateExpired(urlSave.getDateExpired());
            urlResponse.setShortenValue(urlSave.getShortenUrl());
            urlResponse.setNumberOfUrlShortenServiceRequest(urlSave.getNumberOfRequestToShortUrl());
            urlResponse.setNumberOfShortenUrlAccessed(urlSave.getNumberOfShortenUrlAccessed());
            return new ResponseEntity<SucessResponce>(urlResponse, HttpStatus.OK);
        }

        ErrorReponce urlErrorResponse = new ErrorReponce();
        urlErrorResponse.setStatus("404");
        urlErrorResponse.setError("There was an error processing your request. please try again.");
        return new ResponseEntity<ErrorReponce>(urlErrorResponse, HttpStatus.OK);
    }


    @GetMapping("/{searchByShortenUrl}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String searchByShortenUrl, HttpServletResponse response) throws IOException {

        if (StringUtils.isEmpty(searchByShortenUrl)) {
            ErrorReponce inValidUrlErrorRes = new ErrorReponce();
            inValidUrlErrorRes.setError("Invalid Url or null");
            inValidUrlErrorRes.setStatus("400");
            return new ResponseEntity<ErrorReponce>(inValidUrlErrorRes, HttpStatus.OK);
        }
        Url urlRedirection = urlService.getShortUrl(searchByShortenUrl);

        if (urlRedirection == null) {
            ErrorReponce urlNotExistErrorRes = new ErrorReponce();
            urlNotExistErrorRes.setError("Url does not exist in DB Sorrry!!");
            urlNotExistErrorRes.setStatus("400");
            return new ResponseEntity<ErrorReponce>(urlNotExistErrorRes, HttpStatus.OK);
        }
        //can implement delete as well on expiration on link like below

        if (urlRedirection.getDateExpired().isBefore(LocalDateTime.now())) {
            urlService.removeShortUrl(urlRedirection);
            ErrorReponce urlErrorResponseDto = new ErrorReponce();
            urlErrorResponseDto.setError("Url Expired create new shortcut  one.");
            urlErrorResponseDto.setStatus("200");
            return new ResponseEntity<ErrorReponce>(urlErrorResponseDto, HttpStatus.OK);
        }
        response.sendRedirect(urlRedirection.getActualUrl());
        return null;
    }
}
