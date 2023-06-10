package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.annotation.IT;
import net.noir1915.dto.PaymentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IT
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void addPaymentTest() throws Exception {
        var dto = new PaymentDto();
        dto.setOrderId(1L);
        dto.setCustomerId(1L);
        dto.setAmount(new BigDecimal(1000));

        mockMvc.perform(post("/api/v1/payments")
                        .content(asJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    void getAllPaymentTest() throws Exception {

        mockMvc.perform(get("/api/v1/payments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));
    }
    @Test
    void deletePaymentTest() throws Exception {

        mockMvc.perform(delete("/api/v1/payments/1"))
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
