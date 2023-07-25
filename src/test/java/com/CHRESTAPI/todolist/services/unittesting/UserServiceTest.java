package com.CHRESTAPI.todolist.services.unittesting;

import com.CHRESTAPI.todolist.dto.UserDto;
import com.CHRESTAPI.todolist.entities.User;
import com.CHRESTAPI.todolist.enums.Role;
import com.CHRESTAPI.todolist.repositories.UserRepository;
import com.CHRESTAPI.todolist.services.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willReturn;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

     private UserRepository userRepository;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp(){
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
     void itShouldCheckIfUserIsFoundById(){

        //given
         Long userId = 1L;
         User user = User.builder()
                         .id(1L)
                         .build();
         //when
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Optional<User> result = userService.finById(userId);
        System.out.println("result ="+result );
         //then
        assertEquals(user, result.get());


     }

     @Test
     void iTShouldCheckIfUserIsFoundByUsernameOrEmail(){

        //given
        String  username = "Jhon";
        String email = "Jhon@example.com";

        User user = User.builder()
                        .username("jhon")
                        .email("jhon@example.com")
                        .build();
        userRepository.save(user);
        //when
         when(userRepository.findByUsernameOrEmail(username,email)).thenReturn(Optional.of(user));
         Optional<User> result = userService.findByusernameOrEmail(username,email);
         //then
         System.out.println("result get is : "+result.get());
         assertEquals(user,result.get());

     }

        @Test
     void iTShouldCheckIfUserIsFoundByEmail(){

        //given
        String  username = "Jhon";
        String email = "Jhon@example.com";

        User user = User.builder()
                        .username("jhon")
                        .email("jhon@example.com")
                        .build();
        userRepository.save(user);
        //when
         when(userRepository.findByUsernameOrEmail(username,email)).thenReturn(Optional.of(user));
         Optional<User> result = userService.findByusernameOrEmail(username,email);
         //then
         System.out.println("result get is : "+result.get());
         assertEquals(user,result.get());

     }

           @Test
     void iTShouldCheckIfUserIsFoundByUsername(){

        //given
        String  username = "Jhon";

        User user = User.builder()
                        .username("jhon")

                        .build();
        userRepository.save(user);
        //when
         when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
         Optional<User> result = userService.findByUsername(username);
         //then
         System.out.println("result get is : "+result.get());
         assertEquals(user,result.get());

     }


      @Test
    void testCreateUser_ValidUser_CallsSaveMethod() {
        // given
        User user = User.builder()
                .id(1L)
                .username("john_doe")
                .password("password123")
                .email("john@example.com")
                .firstName("John")
                .lastName("Doe")
                .role(Role.USER)
                .build();

         // when
        when(userRepository.save(user)).thenReturn(user);
        userService.createUser(user);

        // then
        verify(userRepository).save(user);
        verifyNoMoreInteractions(userRepository);
    }

   @Test
    void itShouldFindAllUsers(){
        //given
       User user1 = User.builder()
                .id(1L)
                .username("john_doe")
                .password("password123")
                .email("john@example.com")
                .firstName("John")
                .lastName("Doe")
                .role(Role.USER)
                .build();
        User user2 = User.builder()
                .id(1L)
                .username("john_doe")
                .password("password123")
                .email("john@example.com")
                .firstName("John")
                .lastName("Doe")
                .role(Role.USER)
                .build();
        List<User> mockUsers = Arrays.asList(user1, user2);


        // Given: Set up the mock behavior
      //  userRepository.findAll().willReturn(mockUsers);
         when(userRepository.findAll()).thenReturn(mockUsers);


        // When: Call the method to be tested
        List<UserDto> result = userService.findAll();

        // Then: Verify the interactions and assertions
        then(userRepository).should(times(1)).findAll();

        // Map the entities to DTOs manually for comparison
        List<UserDto> expected = mockUsers.stream().map(UserDto::fromEntity).collect(Collectors.toList());

        // Assert the result
        assertEquals(expected, result);
   }

    /*

    public List<UserDto> findAll();
    void createUser(User user);
    UserDto save(UserDto userDto);
    UserDto updateUser(UserDto userDto);*/
}

