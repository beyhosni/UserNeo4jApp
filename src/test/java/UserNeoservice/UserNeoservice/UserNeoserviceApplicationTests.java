package UserNeoservice.UserNeoservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import UserNeoservice.model.User;
import UserNeoservice.repository.UserRepository;
import UserNeoservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserNeoserviceApplicationTests {
	private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService();
        userService = new UserService(userRepository);
    }

    @Test
    void createUser_ShouldCreateUser() {
        User user = new User("testUser", "test@example.com", "password123");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser("testUser", "test@example.com", "password123");

        assertNotNull(createdUser);
        assertEquals("testUser", createdUser.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void findByUsername_ShouldReturnUser() {
        User user = new User("testUser", "test@example.com", "password123");
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findByUsername("testUser");

        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.get().getUsername());
    }

}
