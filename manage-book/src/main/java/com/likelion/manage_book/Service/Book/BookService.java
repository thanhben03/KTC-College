package com.likelion.manage_book.Service.Book;

import com.likelion.manage_book.Entity.BookEntity;
import com.likelion.manage_book.Entity.BookSoldEntity;
import com.likelion.manage_book.Model.BookResponse;
import com.likelion.manage_book.Model.BookTemp;
import com.likelion.manage_book.Repository.BookRepository;
import com.likelion.manage_book.Repository.BookSoldRepository;
import com.likelion.manage_book.Service.BaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class BookService implements BaseServiceInterface<BookEntity> {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookSoldRepository bookSoldRepository;

    @Override
    public BookEntity create(BookEntity bookEntity) {
        BookEntity bookExist = bookRepository.findByBookName(bookEntity.getBookName());
        if (bookExist != null) {
            bookExist.setQuantity(bookExist.getQuantity() + bookEntity.getQuantity());
            return bookRepository.save(bookExist);
        }
        bookEntity.setCreatedAt(LocalDate.now());
        return bookRepository.save(bookEntity);
    }

    @Override
    public BookEntity update(int id, BookEntity bookEntity) {

        Optional<BookEntity> book = bookRepository.findById(id);

        BookEntity bookUpdate = null;
        if (book.isPresent()) {
            bookUpdate = book.get();

            bookUpdate.setBookName(bookEntity.getBookName());
            bookUpdate.setBookPrice(bookEntity.getBookPrice());
            bookUpdate.setQuantity(bookEntity.getQuantity());
        }

        return bookUpdate;
    }

    @Override
    public void delete(BookEntity bookEntity) {
        bookRepository.delete(bookEntity);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public Iterable<BookEntity> findAll() {
        return bookRepository.findAll();
    }


    @Override
    public Optional<BookEntity> findById(int id) {
        return bookRepository.findById(id);
    }

    public BookEntity sell(int id) {
        BookEntity bookExist = bookRepository.findById(id).get();
        BookSoldEntity bookSold = new BookSoldEntity();
        bookSold.setBookId(id);
        bookSold.setUserId(1);
        bookSoldRepository.save(bookSold);

        return bookExist;
    }

    public Iterable<BookEntity> sort(String type, String orderBy) {
        List<BookEntity> books = new ArrayList<>();
        // Order by price

        if (type.equals("price")) {

            if (orderBy.equals("asc")) {
                books = bookRepository.findAll().stream().sorted((s1, s2) ->
                        s1.getBookPrice() - s2.getBookPrice()
                ).collect(Collectors.toList());
            } else {
                books = bookRepository.findAll().stream().sorted((s1, s2) ->
                        s2.getBookPrice() - s1.getBookPrice()
                ).collect(Collectors.toList());
            }

        }

        // Sap xep theo so luong ban ra
        if (type.equals("trend")) {
            Map<Integer, BookTemp> map;

            map = getBooksSold();

            Map<Integer, Integer> sortedBook = new TreeMap<>();

            sortedBook = sortedBook(map);

            List<Integer> bookIds = new ArrayList<>();
            for (Map.Entry<Integer, Integer> i : sortedBook.entrySet()) {
                System.out.println(i.getKey() + "/" + i.getValue());

                Optional<BookEntity> bookSorted = bookRepository.findById(i.getValue());

                books.add(bookSorted.get());
            }


            if (orderBy.equals("desc")) {
                Collections.reverse(books);
            }

        }

        return books;
    }

    public Map<Integer, BookTemp> getBooksSold() {
        Map<Integer, BookTemp> map = new HashMap<>();
        Iterable<BookSoldEntity> booksSold = bookSoldRepository.findAll();

        booksSold.forEach(bookSoldEntity -> {
            int bookId = bookSoldEntity.getBookId();
            Optional<BookEntity> findBook = bookRepository.findById(bookId);

            boolean isExist = map.containsKey(bookId);

            // Neu khong co trong map
            if (!isExist) {
                BookTemp bookTemp = new BookTemp();
                map.put(bookId, bookTemp);
            } else {
                // neu ton tai
                BookTemp bookTemp = map.get(bookId);
                bookTemp.setQuantitySold(bookTemp.getQuantitySold() + 1);
            }
        });

        return map;
    }

    public Map<Integer, Integer> sortedBook(Map<Integer, BookTemp> map) {
        Map<Integer, Integer> sortedBook = new TreeMap<>();

        for (Map.Entry<Integer, BookTemp> entry : map.entrySet()) {
            BookTemp bookTemp = entry.getValue();
            if (bookTemp != null) {
                sortedBook.put(bookTemp.getQuantitySold(), entry.getKey());
            }
        }

        return sortedBook;
    }

    public List<BookResponse> report() {
        List<BookEntity> books = bookRepository.findAll();

        List<BookResponse> booksReponse = new ArrayList<>();

        Map<Integer, BookTemp> map = getBooksSold();
        Map<Integer, Integer> sortedBook = sortedBook(map);

        books.forEach(bookEntity -> {
            BookResponse a = new BookResponse();
            a.setBookName(bookEntity.getBookName());
            a.setBookPrice(bookEntity.getBookPrice());
            a.setQuantity(bookEntity.getQuantity());
            sortedBook.forEach((key, value) -> {
                if (value == bookEntity.getId()) {
                    a.setQuantitySold(key);
                }
            });
            booksReponse.add(a);

        });

        return booksReponse;

    }

    public BookEntity findByBookName(String bookName) {
        return bookRepository.findByBookName(bookName);
    }

    public List<BookEntity> findByCreate(String from, String to) {
        return bookRepository.findByCreate(from, to);
    }

    public List<BookResponse> getFiveBookSeller() {
        Map<Integer, BookTemp> map = getBooksSold();
        Map<Integer, Integer> sortedBook = sortedBook(map);
        List<BookEntity> books = bookRepository.findAll();
        List<BookResponse> booksReponse = new ArrayList<>();

        AtomicInteger count = new AtomicInteger(0);
        books.forEach(bookEntity -> {
            if (count.get() == 2)
                return;
            count.getAndIncrement();
            BookResponse a = new BookResponse();
            a.setBookName(bookEntity.getBookName());
            a.setBookPrice(bookEntity.getBookPrice());
            a.setQuantity(bookEntity.getQuantity());
            sortedBook.forEach((key, value) -> {
                if (value == bookEntity.getId()) {
                    a.setQuantitySold(key);
                }
            });
            booksReponse.add(a);
        });

        return booksReponse;
    }

    public List<BookEntity> paginate(int page, int limit) {
        int currPage = 0;
        if (page >= 2) {
            currPage = (page - 1) * limit;
        }
        return bookRepository.paginate(currPage, limit);
    }
}
