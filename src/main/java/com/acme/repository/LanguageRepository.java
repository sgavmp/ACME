package com.acme.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.acme.model.geography.Language;

public interface LanguageRepository  extends PagingAndSortingRepository<Language, Long> {

}
