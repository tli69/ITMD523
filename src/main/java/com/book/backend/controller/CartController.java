package com.book.backend.controller;

import com.book.backend.common.ApiResponse;
import com.book.backend.dto.cart.AddToCartDto;
import com.book.backend.dto.cart.CartDto;
import com.book.backend.exceptions.AuthenticationFailException;
import com.book.backend.exceptions.CartItemNotExistException;
import com.book.backend.exceptions.BookNotExistException;
import com.book.backend.model.Books;
import com.book.backend.model.User;
import com.book.backend.service.AuthenticationService;
import com.book.backend.service.CartService;
import com.book.backend.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private BooksService booksService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token) throws AuthenticationFailException, BookNotExistException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Books book = booksService.getBookById(addToCartDto.getBookId());
        System.out.println("Book to add"+  book.getTitle());
        cartService.addToCart(addToCartDto, book, user);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);

    }
    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
    }
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @Valid AddToCartDto cartDto,
                                                      @RequestParam("token") String token) throws AuthenticationFailException, BookNotExistException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Books product = booksService.getBookById(cartDto.getBookId());
        cartService.updateCartItem(cartDto, user,product);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemID,@RequestParam("token") String token) throws AuthenticationFailException, CartItemNotExistException {
        authenticationService.authenticate(token);
        int userId = authenticationService.getUser(token).getId();
        cartService.deleteCartItem(itemID, userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }

}
