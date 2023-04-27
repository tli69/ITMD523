package com.book.backend.controller;


import com.book.backend.common.ApiResponse;
import com.book.backend.dto.books.BooksDto;
import com.book.backend.exceptions.AuthenticationFailException;
import com.book.backend.exceptions.CartItemNotExistException;
import com.book.backend.model.Author;
import com.book.backend.service.AuthenticationService;
import com.book.backend.service.AuthorService;
import com.book.backend.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BooksService booksService;

    @Autowired
    AuthorService authorService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/")
    public ResponseEntity<List<BooksDto>> getAllBooks() {
        List<BooksDto> body = booksService.listAllBooks();
        System.out.println(body.toString());
        return new ResponseEntity<List<BooksDto>>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addBook(@RequestBody BooksDto booksDto) {
        Optional<Author> optionalAuthor = authorService.readAuthor(booksDto.getAuthorId());
        if (!optionalAuthor.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "author is invalid"), HttpStatus.CONFLICT);
        }
        Author author = optionalAuthor.get();
        booksService.addBook(booksDto, author);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable("id") Integer bookId, @RequestBody @Valid BooksDto booksDto) {
        Optional<Author> optionalAuthor = authorService.readAuthor(booksDto.getAuthorId());
        if (!optionalAuthor.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "author is invalid"), HttpStatus.CONFLICT);
        }
        Author author = optionalAuthor.get();
        booksService.updateBook(bookId, booksDto, author);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Book has been updated"), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("bookId") int bookId,@RequestParam("token") String token) throws AuthenticationFailException, CartItemNotExistException {
        authenticationService.authenticate(token);
        int userId = authenticationService.getUser(token).getId();
        booksService.deleteBook(bookId, userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Book has been removed"), HttpStatus.OK);
    }
}
