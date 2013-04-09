package com.acme.repository;

import org.springframework.data.repository.CrudRepository;

import com.acme.model.examination.Examination;
import com.acme.model.geography.Language;

public interface LanguageRepository  extends CrudRepository<Language, Long> {

}
