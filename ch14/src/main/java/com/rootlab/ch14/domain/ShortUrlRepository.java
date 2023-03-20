package com.rootlab.ch14.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    ShortUrl findByUrl(String shortUrl);

    ShortUrl findByOrgUrl(String originalUrl);
}