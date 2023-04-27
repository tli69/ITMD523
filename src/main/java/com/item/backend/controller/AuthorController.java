package com.book.backend.controller;

import com.book.backend.common.ApiResponse;
import com.book.backend.exceptions.AuthenticationFailException;
import com.book.backend.exceptions.CartItemNotExistException;
import com.book.backend.model.Author;
import com.book.backend.service.AuthenticationService;
import com.book.backend.service.AuthorService;
import com.book.backend.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/author")

public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@Autowired
	AuthenticationService authenticationService;

	@GetMapping("/")
    public ResponseEntity<List<Author>> getAuthors() {
        List<Author> body = authorService.listAuthors();
        return new ResponseEntity<List<Author>>(body, HttpStatus.OK);
    }

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createAuthor(@Valid @RequestBody Author author) {
		if (Helper.notNull(authorService.readAuthor(author.getEmail()))) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "author already exists"), HttpStatus.CONFLICT);
		}
		authorService.createAuthor(author);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "the author"), HttpStatus.CREATED);
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateAuthor(@PathVariable("id") Integer authorId, @Valid @RequestBody Author author) {
		// Check to see if the author exists.
		if (Helper.notNull(authorService.readAuthor(authorId))) {
			// If the author exists then update it.
			authorService.updateAuthor(authorId, author);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated the author"), HttpStatus.OK);
		}
		// If the author doesn't exist then return a response of unsuccessful.
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "author does not exist"), HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("/delete/{authorId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("authorId") int bookId,@RequestParam("token") String token) throws AuthenticationFailException, CartItemNotExistException {
		authenticationService.authenticate(token);
		int userId = authenticationService.getUser(token).getId();
		authorService.deleteAuthor(bookId, userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Author has been removed"), HttpStatus.OK);
	}
}
