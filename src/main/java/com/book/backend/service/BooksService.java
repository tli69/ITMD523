package com.book.backend.service;

import com.book.backend.dto.items.BooksDto;
import com.book.backend.exceptions.BookNotExistException;
import com.book.backend.exceptions.CartItemNotExistException;
import com.book.backend.model.Author;
import com.book.backend.model.Books;
import com.book.backend.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    WishListService wishListService;

    public List<BooksDto> listAllBooks() {
        List<Books> books = booksRepository.findAll();
        List<BooksDto> booksDto = new ArrayList<>();
        for(Items item : items) {  // 
            BooksDto itemsDtos = getDtoFromBook(item);
            booksDto.add(itemsDtos);
        }
        return booksDto;
    }

    public static BooksDto getDtoFromBook(Books book) {
        BooksDto booksDto = new BooksDto(book);
        return booksDto;
    }

    public static Books getBookFromDto(BooksDto booksDto, Author author) {
        Books book = new Books(booksDto, author);
        return book;
    }

    public void addBook(BooksDto booksDto, Author author) {
        Books book = getBookFromDto(booksDto, author);
        booksRepository.save(book);
    }

    public void updateBook(Integer bookId, BooksDto booksDto, Author author) {
        Books book = getBookFromDto(booksDto, author);
        book.setId(bookId);
        booksRepository.save(book);
    }
    public Books getBookById(Integer bookId) throws BookNotExistException {
        Optional<Books> optionalBook = booksRepository.findById(bookId);
        if (!optionalBook.isPresent())
            throw new BookNotExistException("Book id is invalid " + bookId);
        return optionalBook.get();
    }

    public void deleteBook(int id, int userId) throws CartItemNotExistException {
        if (!booksRepository.existsById(id))
            throw new BookNotExistException("Book id is invalid : " + id);
        wishListService.deleteByBookId(id);
        booksRepository.deleteById(id);
    }
}
