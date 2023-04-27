package com.book.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.backend.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

	Author findByEmail(String email);

}
