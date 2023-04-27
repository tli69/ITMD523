package com.book.backend.controller;


import com.book.backend.common.ApiResponse;
import com.book.backend.dto.items.BooksDto;
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
        public ResponseEntity<List<ItemsDto>> getWishList(@PathVariable("token") String token) {
                int user_id = authenticationService.getUser(token).getId();
                List<WishList> body = wishListService.readWishList(user_id);
                List<ItemsDto> items = new ArrayList<ItemsDto>();
                for (WishList wishList : body) {
                        items.add(ItemsService.getDtoFromItem(wishList.getItem()));
                }

                return new ResponseEntity<List<ItemsDto>>(items, HttpStatus.OK);
        }

        @PostMapping("/add")
        public ResponseEntity<ApiResponse> addWishList(@RequestBody items item, @RequestParam("token") String token) {
                authenticationService.authenticate(token);
                User user = authenticationService.getUser(token);
                WishList wishList = new WishList(user, item);
                wishListService.createWishlist(wishList);
                return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Add to wishlist"), HttpStatus.CREATED);

        }


}
