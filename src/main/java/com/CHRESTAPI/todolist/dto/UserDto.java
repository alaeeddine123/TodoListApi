package com.CHRESTAPI.todolist.dto;

import com.CHRESTAPI.todolist.entities.User;
import com.CHRESTAPI.todolist.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class UserDto {


        private Long id;
        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private Role role;

        public static UserDto fromEntity(User user) {
                if (user == null) return null;
                return UserDto.builder().id(user.getId())
                        .username(user.getUsername())
                        .lastName(user.getLastName())
                        .firstName(user.getFirstName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build();
        }

        public  static  User toEntity(UserDto userDto){
                if (userDto == null) return null;

               return  User.builder()
                       .id(userDto.getId())
                       .username(userDto.getUsername())
                       .email(userDto.getEmail())
                       .password(userDto.getPassword())
                       .firstName(userDto.getFirstName())
                       .lastName(userDto.getLastName())
                       .role(userDto.getRole()).build();
        }

    }
