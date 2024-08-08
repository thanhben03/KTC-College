package com.likelion.manage_book.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class BookEntity extends BaseEntity{
    @Column(name = "book_name", unique = true, nullable = false)
    @NonNull
    private String bookName;

    @Column(name = "book_price", nullable = false)
    @NonNull
    private int bookPrice;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public @NonNull String getBookName() {
        return bookName;
    }

    public void setBookName(@NonNull String bookName) {
        this.bookName = bookName;
    }

    public @NonNull int getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(@NonNull int bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
