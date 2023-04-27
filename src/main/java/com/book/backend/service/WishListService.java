package com.item.backend.service;

import com.item.backend.model.WishList;
import com.item.backend.repository.WishListRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class WishListService {

    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public void createWishlist(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<WishList> readWishList(Integer userId) {
        return wishListRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
    }

    public void deleteByItemId(int id) {
        List<WishList> list = wishListRepository.findAll();
        for(WishList wishList: list){
            if(wishList.getItem().getId()==id){
                wishListRepository.deleteById(wishList.getId());
            }
        }
    }
//    public void deleteWishlist(Integer bookId) {
//        boolean  wishList = wishListRepository.findById(bookId).isPresent();
//        System.out.println("Is present"+wishList);
//        //wishListRepository.delete(wishList);
//    }
}
