package com.book.backend.controller;


import com.book.backend.common.ApiResponse;
import com.book.backend.dto.books.BooksDto;
import com.book.backend.model.Books;
import com.book.backend.model.User;
import com.book.backend.model.WishList;
import com.book.backend.service.AuthenticationService;
import com.book.backend.service.BooksService;
import com.book.backend.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

        @Autowired
        private WishListService wishListService;

        @Autowired
        private AuthenticationService authenticationService;

        @GetMapping("/{token}")
        public ResponseEntity<List<BooksDto>> getWishList(@PathVariable("token") String token) {
                int user_id = authenticationService.getUser(token).getId();
                List<WishList> body = wishListService.readWishList(user_id);
                List<BooksDto> books = new ArrayList<BooksDto>();
                for (WishList wishList : body) {
                        books.add(BooksService.getDtoFromBook(wishList.getBook()));
                }

                return new ResponseEntity<List<BooksDto>>(books, HttpStatus.OK);
        }

        @PostMapping("/add")
        public ResponseEntity<ApiResponse> addWishList(@RequestBody Books book, @RequestParam("token") String token) {
                authenticationService.authenticate(token);
                User user = authenticationService.getUser(token);
                WishList wishList = new WishList(user, book);
                wishListService.createWishlist(wishList);
                return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Add to wishlist"), HttpStatus.CREATED);

        }


}
