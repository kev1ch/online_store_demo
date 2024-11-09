package com.pavlov.onlinestore.controllers.rest;

import org.junit.jupiter.api.Test;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductController productController;

    @Test
    public void contextLoads() {
        assert mockMvc != null;
        assert productController != null;
    }

    @Test
    @SneakyThrows
    public void testTestSum() {
        mockMvc.perform(get("/product/sum/2/3")).andExpect(status().isOk());
    }

    @Test
    public void voidTest() {

    }

}
