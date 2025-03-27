package kh.com.kshrd.demospringsecurityjwtwithnextjs.repository;

import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.entity.AppUser;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.request.AppUserRequest;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.response.AppUserResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AppUserRepository {

    @Results(id = "appUserMapper", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "fullName", column = "full_name"),
    })
    @Select("""
                SELECT * FROM app_users
                WHERE email = #{email};
            """)
    AppUser getUserByEmail(String email);

    @Select("""
                INSERT INTO app_users
                VALUES (default, #{request.fullName}, #{request.email}, #{request.password})
                RETURNING *
            """)
    @ResultMap("appUserMapper")
    AppUser register(@Param("request") AppUserRequest request);

    @Result(property = "userId", column = "user_id")
    @Result(property = "fullName", column = "full_name")
    @Select("""
                SELECT * FROM app_users
                WHERE user_id = #{userId};
            """)
    AppUserResponse getUserById(Long userId);


}
