package com.item.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.item.backend.common.ApiResponse;
import com.item.backend.dto.items.ItemsDto;
import com.item.backend.exceptions.AuthenticationFailException;
import com.item.backend.exceptions.CartItemNotExistException;
import com.item.backend.model.Author;
import com.item.backend.service.AuthenticationService;
import com.item.backend.service.AuthorService;
import com.item.backend.service.ItemsService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemsService itemsService;

    @Autowired
    AuthorService authorService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/")
    public ResponseEntity<List<ItemsDto>> getAllItems() {
        List<ItemsDto> body = itemsService.listAllItems();
        System.out.println(body.toString());
        return new ResponseEntity<List<ItemsDto>>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addItem(@RequestBody ItemsDto itemsDto) {
        Optional<Author> optionalAuthor = authorService.readAuthor(itemsDto.getAuthorId());
        if (!optionalAuthor.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "author is invalid"), HttpStatus.CONFLICT);
        }
        Author author = optionalAuthor.get();
        itemsService.addItem(itemsDto, author);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateItem(@PathVariable("id") Integer itemId, @RequestBody @Valid ItemsDto itemsDto) {
        Optional<Author> optionalAuthor = authorService.readAuthor(itemsDto.getAuthorId());
        if (!optionalAuthor.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "author is invalid"), HttpStatus.CONFLICT);
        }
        Author author = optionalAuthor.get();
        itemsService.updateItem(itemId, itemsDto, author);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been updated"), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("itemId") int itemId,@RequestParam("token") String token) throws AuthenticationFailException, CartItemNotExistException {
        authenticationService.authenticate(token);
        int userId = authenticationService.getUser(token).getId();
        itemsService.deleteItem(itemId, userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }
}
