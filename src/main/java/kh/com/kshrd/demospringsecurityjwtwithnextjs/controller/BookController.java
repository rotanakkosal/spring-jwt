package kh.com.kshrd.demospringsecurityjwtwithnextjs.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.entity.Book;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.request.BookRequest;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.response.ApiResponse;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Book> saveBook(@RequestBody BookRequest request) {
        Book savedBook = bookService.saveBook(request);
        return ApiResponse.<Book>builder()
                .message("Successfully created book")
                .status(HttpStatus.CREATED)
                .code(HttpStatus.CREATED.value())
                .payload(savedBook)
                .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ApiResponse.<List<Book>>builder()
                .message("Successfully fetched all books")
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .payload(books)
                .build();
    }

    @GetMapping("/{book-id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Book> getBookById(@PathVariable("book-id") Long bookId) {
        Book book = bookService.getBookById(bookId);
        return ApiResponse.<Book>builder()
                .message("Successfully fetched book")
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .payload(book)
                .build();
    }

    @PutMapping("/{book-id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Book> updateBookById(@PathVariable("book-id") Long bookId, @RequestBody BookRequest request) {
        Book updatedBook = bookService.updateBookById(bookId, request);
        return ApiResponse.<Book>builder()
                .message("Successfully updated book")
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .payload(updatedBook)
                .build();
    }

    @DeleteMapping("/{book-id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<String> deleteBookById(@PathVariable("book-id") Long bookId) {
        bookService.deleteBookById(bookId);
        return ApiResponse.<String>builder()
                .message("Successfully deleted book")
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .payload(null)
                .build();
    }
}