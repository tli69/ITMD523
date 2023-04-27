package com.book.backend.repository;

import com.book.backend.model.OrderedBooks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedBooksRepository extends JpaRepository<OrderedBooks, Long> {
}
