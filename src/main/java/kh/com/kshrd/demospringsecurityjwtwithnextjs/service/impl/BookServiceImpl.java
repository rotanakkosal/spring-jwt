package kh.com.kshrd.demospringsecurityjwtwithnextjs.service.impl;

import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.entity.Book;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.request.BookRequest;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.repository.BookRepository;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.service.AppUserService;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AppUserService appUserService;

    @Override
    public Book saveBook(BookRequest request) {
        Long userId = appUserService.getCurrentUserId();
        return bookRepository.saveBook(request, userId);
    }

    @Override
    public List<Book> getAllBooks() {
        Long userId = appUserService.getCurrentUserId();
        return bookRepository.getAllBooks(userId);
    }

    @Override
    public Book getBookById(Long bookId) {
        Long userId = appUserService.getCurrentUserId();
        return bookRepository.getBookById(bookId, userId);
    }

    @Override
    public Book updateBookById(Long bookId, BookRequest request) {
        Long userId = appUserService.getCurrentUserId();
        return bookRepository.updateBookById(bookId, request, userId);
    }

    @Override
    public void deleteBookById(Long bookId) {
        Long userId = appUserService.getCurrentUserId();
        bookRepository.deleteBookById(bookId, userId);
    }
}
