package com.codingTest.urlShorteningService.repo;

import com.codingTest.urlShorteningService.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    public Url findByShortenUrl(String shortenUrl);
}
