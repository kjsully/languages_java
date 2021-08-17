package com.kyle.languages.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kyle.languages.models.Language;

public interface LanguageRepository extends CrudRepository<Language, Long> {
	//Find all languages
	List<Language> findAll();
}
