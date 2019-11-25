package com.belatrixsf.integracion_scraping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belatrixsf.integracion_scraping.dto.modelo.ScrapingWeb;
 

@Repository
public interface ScrapingWebRepository extends JpaRepository<ScrapingWeb, Long>{

 
}
