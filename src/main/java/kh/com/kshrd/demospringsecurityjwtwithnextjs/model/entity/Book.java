package kh.com.kshrd.demospringsecurityjwtwithnextjs.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.response.AppUserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"appUserResponse"})
public class Book {

    private Long bookId;
    private String title;
    private String description;
    private String author;
    private AppUserResponse appUserResponse;

}
