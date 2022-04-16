package com.example.petfinder.entity.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private String id;
    private String userId;
    private PostCategory postCategory;
    private String description;
    private Boolean active;
    private LocalDateTime date;
    private String photo;
    private Location location;
}
