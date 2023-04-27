package com.item.backend.service;

import com.item.backend.dto.items.ItemsDto;
import com.item.backend.exceptions.ItemNotExistException;
import com.item.backend.exceptions.CartItemNotExistException;
import com.item.backend.model.Items;
import com.item.backend.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemsService {
    @Autowired
    private ItemsRepository itemsRepository;
    @Autowired
    WishListService wishListService;

    public List<ItemsDto> listAllItems() {
        List<Items> items = itemsRepository.findAll();
        List<ItemsDto> itemsDto = new ArrayList<>();
        for(Items item : items) {
            ItemsDto itemsDtos = getDtoFromItem(item);
            itemsDto.add(itemsDtos);
        }
        return itemsDto;
    }

    public static ItemsDto getDtoFromItem(Items item) {
        ItemsDto itemsDto = new ItemsDto(item);
        return itemsDto;
    }

    public static Items getItemFromDto(ItemsDto itemsDto) {
        Items item = new Items(itemsDto);
        return item;
    }

    public void addItem(ItemsDto itemsDto) {
        Items item = getItemFromDto(itemsDto);
        itemsRepository.save(item);
    }

    public void updateItem(Integer itemId, ItemsDto itemsDto) {
        Items item = getItemFromDto(itemsDto);
        item.setId(itemId);
        itemsRepository.save(item);
    }
    public Items getItemById(Integer itemId) throws ItemNotExistException {
        Optional<Items> optionalItem = itemsRepository.findById(itemId);
        if (!optionalItem.isPresent())
            throw new ItemNotExistException("Item id is invalid " + itemId);
        return optionalItem.get();
    }

    public void deleteItem(int id, int userId) throws CartItemNotExistException {
        if (!itemsRepository.existsById(id))
            throw new ItemNotExistException("Item id is invalid : " + id);
        wishListService.deleteByItemId(id);
        itemsRepository.deleteById(id);
    }
}
