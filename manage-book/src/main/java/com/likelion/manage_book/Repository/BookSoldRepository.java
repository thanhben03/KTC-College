package com.likelion.manage_book.Repository;

import com.likelion.manage_book.Entity.BookEntity;
import com.likelion.manage_book.Entity.BookSoldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookSoldRepository extends JpaRepository<BookSoldEntity, Integer> {

}
