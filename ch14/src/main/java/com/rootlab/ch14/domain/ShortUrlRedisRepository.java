package com.rootlab.ch14.domain;

import com.rootlab.ch14.dto.ShortUrlResponseDto;
import org.springframework.data.repository.CrudRepository;

public interface ShortUrlRedisRepository extends CrudRepository<ShortUrlResponseDto, String> {
}