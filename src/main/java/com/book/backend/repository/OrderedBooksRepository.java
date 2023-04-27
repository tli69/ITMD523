package com.item.backend.repository;

import com.item.backend.model.OrderedItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedItemsRepository extends JpaRepository<OrderedItems, Long> {
}
