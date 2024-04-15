package com.ebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebook.model.BookContent;

//import com.example.model.User;

public interface BookContentRepository extends  JpaRepository<BookContent, String>{

}
