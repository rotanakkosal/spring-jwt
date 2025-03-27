package kh.com.kshrd.demospringsecurityjwtwithnextjs.service;


import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.entity.Book;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.request.BookRequest;

import java.util.List;

public interface BookService {

    Book saveBook(BookRequest request);

    List<Book> getAllBooks();

    Book getBookById(Long bookId);

    Book updateBookById(Long bookId, BookRequest request);

    void deleteBookById(Long bookId);

}
