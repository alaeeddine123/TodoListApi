package com.CHRESTAPI.todolist.services;

import com.CHRESTAPI.todolist.dto.UserDto;
import com.CHRESTAPI.todolist.entities.User;
import com.CHRESTAPI.todolist.enums.Role;
import com.CHRESTAPI.todolist.repositories.UserRepository;
import com.CHRESTAPI.todolist.services.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
     void itShouldCheckIfUserIsNotFoundById(){
        //given
        Long id = 1L;
        //when
        Optional<User> result = userService.finById(id);
        //then
        assertFalse(result.isPresent());
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
     void itShouldCheckIfUserIsNotFoundByUsernameOrEmail(){
        //given
          String  username = "Jhon";
          String email = "Jhon@example.com";
        //when
        Optional<User> result = userService.findByusernameOrEmail(username,email);
        //then
        assertFalse(result.isPresent());
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
     void iTShouldCheckIfUserIsNotFoundByEmail(){

         //given
         String email = "Jhon@example.com";
        //when
        Optional<User> result = userService.findByEmail(email);
        //then
        assertFalse(result.isPresent());

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
     void iTShouldCheckIfUserIsNotFoundByUsername(){
             //given
          String  username = "Jhon";
        //when
        Optional<User> result = userService.findByUsername(username);
        //then
        assertFalse(result.isPresent());

     }

      @Test
      void iTShouldCheckIfUserIsCreated() {
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
    void iTShouldCheckIfUserIsNotCreated() {
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

        // Simulate a failure during user creation by returning null when userRepository.save() is called
        when(userRepository.save(user)).thenReturn(null);

        // when
        userService.createUser(user);

        // then
        // Verify that userRepository.save() was called once with the user object
        verify(userRepository, times(1)).save(user);
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

   @Test void itShouldCheckIfUserIsSaved(){
        User user = User.builder()
                .id(1L)
                .username("john_doe")
                .password("password123")
                .email("john@example.com")
                .firstName("John")
                .lastName("Doe")
                .role(Role.USER)
                .build();
        // Create ArgumentCaptor to capture the User object passed to userRepository.save()
    ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

    // Mock the userRepository.save() method and capture the argument
    when(userRepository.save(userCaptor.capture())).thenReturn(user);

    // Call the service method
    userService.save(UserDto.fromEntity(user));

    // Verify the userRepository.save() method was called with the correct User object
    verify(userRepository).save(userCaptor.capture());
    User capturedUser = userCaptor.getValue();

    // Assert the capturedUser object without comparing the password field
        assertEquals(user.getUsername(), capturedUser.getUsername());
        assertEquals(user.getEmail(), capturedUser.getEmail());
        assertEquals(user.getFirstName(), capturedUser.getFirstName());
        assertEquals(user.getLastName(), capturedUser.getLastName());
        assertEquals(user.getRole(), capturedUser.getRole());

    // Verify that no other interactions occurred with userRepository
    verifyNoMoreInteractions(userRepository);

   }


}

