package com.dev.homework.university.apierreservice.dto;

import com.dev.homework.university.apierreservice.entity.User;
import lombok.Data;

@Data
public class UserMinDTO {
    private Long id;
    private String username;

    public UserMinDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
