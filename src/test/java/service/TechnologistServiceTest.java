package service;


import net.noir1915.dto.TechnologistDto;
import net.noir1915.service.TechnologistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TechnologistServiceTest {
    @Autowired
    private TechnologistService technologistService;

    @Test
    public void getTechnologistByIdTest() {
        var tech = technologistService.getById(1L);
        assertAll(
                () -> assertEquals(1L, tech.getId()),
                () -> assertEquals("Ivan", tech.getName()),
                () -> assertEquals("ivan@mail.ru", tech.getEmail())
        );
    }

    @Test
    public void getTechnologistByNameTest() {
        var tech = technologistService.getByName("Andrey");
        assertAll(
                () -> assertEquals("Andrey", tech.getName()),
                () -> assertEquals("andrey@mail.ru", tech.getEmail()));
    }

    @Test
    public void findAllTest() {
        long count = technologistService.getAll().size();

        assertTrue(count > 0);
    }

    @Test
    public void createTest() {
        var techDto = new TechnologistDto();
        techDto.setName("new technologist");
        techDto.setEmail("new@mail.ru");
        var tech = technologistService.create(techDto);

        assertAll(
                () -> assertEquals("new technologist", tech.getName()),
                () -> assertEquals("new@mail.ru", tech.getEmail())
        );

    }

    @Test
    public void updateTest() {
        var tech = technologistService.getById(1L);
        assertAll(
                () -> assertEquals("Ivan", tech.getName()),
                () -> assertEquals("ivan@mail.ru", tech.getEmail())
        );

        var techDto = new TechnologistDto();
        techDto.setId(1L);
        techDto.setName("updated Ivan");
        techDto.setEmail("updated@mail.ru");

        technologistService.update(techDto);
        var updatedTech = technologistService.getById(1L);

        assertAll(
                () -> assertNotEquals("Ivan", updatedTech.getName()),
                () -> assertNotEquals("ivan@mail.ru", updatedTech.getEmail()),

                () -> assertEquals("updated Ivan", updatedTech.getName()),
                () -> assertEquals("updated@mail.ru", updatedTech.getEmail())
        );

    }

}
