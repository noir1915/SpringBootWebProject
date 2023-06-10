package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.noir1915.dto.UserDto;
import net.noir1915.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "test@gmail.com", password = "test", roles = {"ADMIN"})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addUserTest() throws Exception {
        UserDto dto = new UserDto();
        dto.setId(1L);
        dto.setUsername("testUser");
        dto.setPassword("pass");
        Role role = new Role();
        role.setId(3l);
        role.setRoleName("testRole");
        dto.setRoles(List.of(role));

        mockMvc.perform(post("/api/v1/users")
                        .content(asJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    void getAllUserTest() throws Exception {

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void deleteUserTest() throws Exception {

        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
