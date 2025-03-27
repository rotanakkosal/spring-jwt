package kh.com.kshrd.demospringsecurityjwtwithnextjs.repository;

import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.entity.Book;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.request.BookRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookRepository {

    @Results(id = "bookMapper", value = {
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "appUserResponse", column = "user_id", one = @One(
                    select = "kh.com.kshrd.demospringsecurityjwtwithnextjs.repository.AppUserRepository.getUserById"
            ))
    })
    @Select("""
                INSERT INTO books
                VALUES (default, #{request.title}, #{request.description}, #{request.author}, #{userId})
                RETURNING *;
            """)
    Book saveBook(@Param("request") BookRequest request, Long userId);

    @ResultMap("bookMapper")
    @Select("""
                SELECT * FROM books
                WHERE user_id = #{userId};
            """)
    List<Book> getAllBooks(Long userId);

    @ResultMap("bookMapper")
    @Select("""
                SELECT * FROM books
                WHERE book_id = #{bookId} AND user_id = #{userId};
            """)
    Book getBookById(Long bookId, Long userId);

    @ResultMap("bookMapper")
    @Select("""
                UPDATE books
                SET title = #{request.title}, description = #{request.description}, author = #{request.author}, user_id = #{userId}
                WHERE book_id = #{bookId} AND user_id = #{userId}
                RETURNING *;
            """)
    Book updateBookById(Long bookId, @Param("request") BookRequest request, Long userId);

    @Delete("""
                DELETE FROM books
                WHERE book_id = #{bookId} AND user_id = #{userId};
            """)
    void deleteBookById(Long bookId, Long userId);

}
