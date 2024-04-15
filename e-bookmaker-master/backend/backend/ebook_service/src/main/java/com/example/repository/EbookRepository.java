package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Ebook;
@Repository
public interface EbookRepository extends JpaRepository<Ebook, String>{
	
	
}

