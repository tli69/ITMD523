package com.item.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.item.backend.common.ApiResponse;
import com.item.backend.dto.items.ItemsDto;
import com.item.backend.model.Items;
import com.item.backend.model.User;
import com.item.backend.model.WishList;
import com.item.backend.service.AuthenticationService;
import com.item.backend.service.ItemsService;
import com.item.backend.service.WishListService;

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
        public ResponseEntity<ApiResponse> addWishList(@RequestBody Items item, @RequestParam("token") String token) {
                authenticationService.authenticate(token);
                User user = authenticationService.getUser(token);
                WishList wishList = new WishList(user, item);
                wishListService.createWishlist(wishList);
                return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Add to wishlist"), HttpStatus.CREATED);

        }


}
