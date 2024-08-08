package com.likelion.manage_book.Repository;

import com.likelion.manage_book.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    BookEntity findByBookName(String bookName);

    @Query(value = "SELECT * FROM books WHERE CREATED_AT BETWEEN TO_DATE(?1,'YYYY-MM-DD') AND TO_DATE(?2,'YYYY-MM-DD')", nativeQuery = true)

    List<BookEntity> findByCreate(String from, String to);

    @Query(value = "SELECT * FROM books ORDER BY ID OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY", nativeQuery = true)

    List<BookEntity> paginate(int page, int limit);

}
