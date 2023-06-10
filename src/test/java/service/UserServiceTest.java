package service;

import net.noir1915.exception.ResourceNotFoundException;
import net.noir1915.model.User;
import net.noir1915.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void getByNameTest() {
        User userBd = userService.getUserByUsername("Manager");

        assertAll(
                () -> assertEquals("Manager", userBd.getUsername()),
                () -> assertEquals("$2y$10$0iw5joKWfZs60TICdRjoD.Ca1O9vZRVsZ768nGZHlGWu83KBo0kqe", userBd.getPassword()),
                () -> assertEquals("manager@mail.com", userBd.getEmail())
        );
    }

    @Test
    void getAllTest() {
        int size = userService.findAllUsers().size();
        assertEquals(3, size);
    }

    @Test
    void createUpdateTest() {
        User newUser = new User();
        newUser.setUsername("newUser");
        newUser.setPassword("newPass");
        newUser.setEmail("newEmail");

        User savedUser = userService.createOrUpdate(newUser);

        assertEquals(newUser.getUsername(), savedUser.getUsername());
        assertEquals(newUser.getPassword(), savedUser.getPassword());
        assertEquals(newUser.getEmail(), savedUser.getEmail());

        savedUser.setUsername("Renamed username");

        User updatedUser = userService.createOrUpdate(savedUser);

        assertEquals(savedUser.getUsername(), updatedUser.getUsername());
    }

    @Test
    void deleteTest() {
        User user = userService.getUserByUsername("TEST");

        assertAll(
                () -> assertEquals(3L, user.getId()),
                () -> assertEquals("manager@mail.com", user.getEmail())
        );
        userService.remove(user.getId());

        assertThrows(ResourceNotFoundException.class,
                () -> userService.getUserByUsername("TEST"));

    }
}
