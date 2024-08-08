package com.likelion.manage_book.Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.likelion.manage_book.Entity.BookEntity;
import com.likelion.manage_book.Model.BookResponse;
import com.likelion.manage_book.Service.Book.BookService;
import com.likelion.manage_book.Util.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookService bookService;

    // create
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody BookEntity bookEntity) {
        BookEntity book = bookService.create(bookEntity);

        return ResponseHandler
                .generateResponse(
                        HttpStatus.ACCEPTED,
                        false, "Created Success !",
                        book
                );
    }

    // edit
    @GetMapping("/{id}")
    public ResponseEntity<Object> edit(@PathVariable("id") int id) {
        try {
            BookEntity book = bookService.findById(id).get();

            return ResponseHandler
                    .generateResponse(
                            HttpStatus.ACCEPTED, false, "Get Success !", book
                    );
        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.BAD_REQUEST, true, e.getMessage(), ""
                    );
        }
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") int id) {
        try {
            BookEntity book = bookService.findById(id).get();

            return ResponseHandler
                    .generateResponse(
                            HttpStatus.ACCEPTED, false, "Get Success !", book
                    );
        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.BAD_REQUEST, true, e.getMessage(), ""
                    );
        }
    }

    // Find all
    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            Iterable<BookEntity> books = bookService.findAll();

            return ResponseHandler
                    .generateResponse(
                            HttpStatus.ACCEPTED, false, "Get Success !", books
                    );
        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.BAD_REQUEST, true, e.getMessage(), ""
                    );
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        try {
            BookEntity book = bookService.findById(id).get();
            bookService.delete(book);

            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, false, "Deleted Success !", book
                    );
        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.BAD_REQUEST, true, e.getMessage(), ""
                    );
        }
    }

    @GetMapping("/sell/{id}")
    public ResponseEntity<Object> sell(@PathVariable("id") int id) {
        try {
            BookEntity bookSold = bookService.sell(id);
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, false, "Sold Success !", bookSold
                    );
        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.BAD_REQUEST, true, e.getMessage(), ""
                    );
        }
    }

    @GetMapping("/filter/{type}")
    public ResponseEntity<Object> filter(@PathVariable("type") String type, @RequestParam String orderBy) {
        try {
            Iterable<BookEntity> books = bookService.sort(type, orderBy);
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, false, "Filtered Success!", books
                    );
        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, true, e.getMessage(), ""
                    );
        }
    }

    @GetMapping("/report")
    public ResponseEntity<Object> report() {
        try {
            List<BookResponse> reports = bookService.report();
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, false, "Report Success!", reports
                    );
        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, true, e.getMessage(), ""
                    );
        }
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<Object> findById(@RequestParam int id) {
        try {

            Optional<BookEntity> book = bookService.findById(id);
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, false, "Report Success!", book.get()
                    );

        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, true, e.getMessage(), ""
                    );
        }
    }

    @GetMapping("/find-by-bookname")
    public ResponseEntity<Object> findById(@RequestParam String bn) {
        try {
            System.out.println(bn);
            BookEntity book = bookService.findByBookName(bn);
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, false, "Report Success!", book
                    );

        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, true, e.getMessage(), ""
                    );
        }
    }

    @PostMapping("/find-by-date")
    public ResponseEntity<Object> findByDate(@RequestBody Map<String, Object> payload) {
        try {
            List<BookEntity> books = bookService.findByCreate(payload.get("from").toString(), payload.get("to").toString());
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, false, "Report Success!", books
                    );
        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, true, e.getMessage(), ""
                    );
        }
    }


    @GetMapping("/get-top-seller")
    public ResponseEntity<Object> getTopSeller() {
        try {
            List<BookResponse> books = bookService.getFiveBookSeller();
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, false, "Report Success!", books
                    );
        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, true, e.getMessage(), ""
                    );
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Object> paginate(@RequestParam int page, @RequestParam int limit) {
        try {

            List<BookEntity> books = bookService.paginate(page, limit);
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, false, "Report Success!", books
                    );
        } catch (Exception e) {
            return ResponseHandler
                    .generateResponse(
                            HttpStatus.OK, true, e.getMessage(), ""
                    );
        }
    }
}
