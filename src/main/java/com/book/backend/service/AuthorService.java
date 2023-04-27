package com.book.backend.service;

import com.book.backend.exceptions.AuthorNotExistException;
import com.book.backend.model.Author;
import com.book.backend.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService {

	private final AuthorRepository authorRepository;

	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	public List<Author> listAuthors() {
		return authorRepository.findAll();
	}

	public void createAuthor(Author author) {
		authorRepository.save(author);
	}

	public Author readAuthor(String email) {
		return authorRepository.findByEmail(email);
	}

	public Optional<Author> readAuthor(Integer authorId) {
		return authorRepository.findById(authorId);
	}

	public void updateAuthor(Integer authorId, Author newAuthor) {
		Author author = authorRepository.findById(authorId).get();
		author.setEmail(newAuthor.getEmail());
		author.setName(newAuthor.getName());
		author.setBooks(newAuthor.getBooks());
		//author.setImageUrl(newAuthor.getImageUrl());
		authorRepository.save(author);
	}
	public void deleteAuthor(int id, int userId) throws AuthorNotExistException {
		if (!authorRepository.existsById(id))
			throw new AuthorNotExistException("Author id is invalid : " + id);
		authorRepository.deleteById(id);
	}
}
